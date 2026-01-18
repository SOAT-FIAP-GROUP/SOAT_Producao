package br.com.ms_entregas.controller;

import br.com.ms_entregas.controller.mapper.dto.response.FilaResponse;
import br.com.ms_entregas.entity.FilaPedidosPreparacao;
import br.com.ms_entregas.usecase.IFilaPedidosPreparacaoUseCase;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Test
    void deveBuscarPedidoNaFilaComSucesso() {
        Long codigoPedido = 1L;
        FilaPedidosPreparacao fila = new FilaPedidosPreparacao(1L, 1L);
        when(filaPedidosPreparacaoUseCase.findByPedidoPorId(codigoPedido))
                .thenReturn(Mono.just(fila));

        filaPedidosController.buscarPedidoNaFila(codigoPedido);

        verify(filaPedidosPreparacaoUseCase).findByPedidoPorId(codigoPedido);
    }

    @Test
    void deveListarPedidosNaFilaComSucesso() {
        FilaPedidosPreparacao item1 = new FilaPedidosPreparacao(1L, 1L);
        FilaPedidosPreparacao item2 = new FilaPedidosPreparacao(2L, 2L);
        List<FilaPedidosPreparacao> fila = new ArrayList<>();
        fila.add(item1);
        fila.add(item2);

        when(filaPedidosPreparacaoUseCase.listarPedidosNaFila())
                .thenReturn(fila);

        List<FilaResponse> resposta = filaPedidosController.listarPedidosNaFila();
        verify(filaPedidosPreparacaoUseCase).listarPedidosNaFila();
        Assertions.assertEquals(1L, resposta.get(0).id());
        Assertions.assertEquals(2L, resposta.get(1).id());

    }
}