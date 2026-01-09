package br.com.ms_entregas.controller;

import br.com.ms_entregas.entity.FilaPedidosPreparacao;
import br.com.ms_entregas.usecase.IFilaPedidosPreparacaoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class FilaPedidosControllerTest {
    private IFilaPedidosPreparacaoUseCase filaPedidosPreparacaoUseCase;
    private FilaPedidosController filaPedidosController;

    @BeforeEach
    void setup() {
        filaPedidosPreparacaoUseCase = mock(IFilaPedidosPreparacaoUseCase.class);
        filaPedidosController = new FilaPedidosController(filaPedidosPreparacaoUseCase);
    }

    @Test
    void deveRemoverPedidoDaFilaComSucesso() {

        Long codigoPedido = 1L;

        // GIVEN
        when(filaPedidosPreparacaoUseCase.removerPedidoDaFila(codigoPedido))
                .thenReturn(Mono.empty());

        // WHEN
        Mono<Void> resultado = filaPedidosController.removerPedidoDaFila(codigoPedido);

        // THEN
        StepVerifier.create(resultado)
                .verifyComplete();

        verify(filaPedidosPreparacaoUseCase).removerPedidoDaFila(codigoPedido);
    }

    @Test
    void deveSalvarPedidoNaFilaComSucesso() {

        Long codigoPedido = 1L;
        FilaPedidosPreparacao filaMock = mock(FilaPedidosPreparacao.class);

        // GIVEN
        when(filaPedidosPreparacaoUseCase.salvar(codigoPedido))
                .thenReturn(Mono.just(filaMock));

        // WHEN
        Mono<FilaPedidosPreparacao> resultado =
                filaPedidosController.salvarPedidoNaFila(codigoPedido);

        // THEN
        StepVerifier.create(resultado)
                .expectNext(filaMock)
                .verifyComplete();

        verify(filaPedidosPreparacaoUseCase).salvar(codigoPedido);
    }
}