package br.com.ms_entregas.usecase.impl;

import br.com.ms_entregas.entity.FilaPedidosPreparacao;
import br.com.ms_entregas.exception.EntityNotFoundException;
import br.com.ms_entregas.gateway.IFilaPedidosPreparacaoGateway;
import br.com.ms_entregas.gateway.IPedidoGateway;
import br.com.ms_entregas.gateway.impl.http.dto.response.PedidoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FilaPedidosPreparacaoUseCaseTest {
    private IFilaPedidosPreparacaoGateway filaPedidosPreparacaoGateway;
    private IPedidoGateway pedidoGateway;
    private FilaPedidosPreparacaoUseCase filaPedidosPreparacaoUseCase;

    @BeforeEach
    void setup() {
        filaPedidosPreparacaoGateway = mock(IFilaPedidosPreparacaoGateway.class);
        pedidoGateway = mock(IPedidoGateway.class);
        filaPedidosPreparacaoUseCase = new FilaPedidosPreparacaoUseCase(filaPedidosPreparacaoGateway, pedidoGateway);
    }

    @Test
    void deveSalvarPedidoNaFilaComSucesso() {

        Long pedidoId = 1L;

        PedidoResponse pedidoMock = mock(PedidoResponse.class);
        FilaPedidosPreparacao filaSalva =
                new FilaPedidosPreparacao(10L, pedidoId);

        // GIVEN
        when(pedidoGateway.buscarPedidoPorId(pedidoId))
                .thenReturn(Mono.just(pedidoMock));

        when(filaPedidosPreparacaoGateway.save(any(FilaPedidosPreparacao.class)))
                .thenReturn(filaSalva);

        // WHEN
        Mono<FilaPedidosPreparacao> resultado = filaPedidosPreparacaoUseCase.salvar(pedidoId);

        // THEN
        StepVerifier.create(resultado)
                .assertNext(fila -> {
                    assertNotNull(fila);
                    assertEquals(filaSalva.id(), fila.id());
                    assertEquals(pedidoId, fila.pedido());
                })
                .verifyComplete();

        verify(pedidoGateway).buscarPedidoPorId(pedidoId);
        verify(filaPedidosPreparacaoGateway).save(any(FilaPedidosPreparacao.class));
    }

    @Test
    void devePropagarErroQuandoPedidoNaoExisteAoSalvar() {

        Long pedidoId = 1L;

        when(pedidoGateway.buscarPedidoPorId(pedidoId))
                .thenReturn(Mono.error(new EntityNotFoundException(PedidoResponse.class, pedidoId)));

        StepVerifier.create(filaPedidosPreparacaoUseCase.salvar(pedidoId))
                .expectError(EntityNotFoundException.class)
                .verify();

        verify(pedidoGateway).buscarPedidoPorId(pedidoId);
        verify(filaPedidosPreparacaoGateway, never()).save(any());
    }

    @Test
    void deveEncontrarPedidoNaFilaComSucesso() {

        Long pedidoId = 1L;

        FilaPedidosPreparacao fila =
                new FilaPedidosPreparacao(10L, pedidoId);

        when(filaPedidosPreparacaoGateway.findByPedidocodigoId(pedidoId))
                .thenReturn(Optional.of(fila));

        StepVerifier.create(filaPedidosPreparacaoUseCase.findByPedidoPorId(pedidoId))
                .expectNext(fila)
                .verifyComplete();

        verify(filaPedidosPreparacaoGateway).findByPedidocodigoId(pedidoId);
    }

    @Test
    void deveRetornarErroQuandoPedidoNaoExisteNaFila() {

        Long pedidoId = 1L;

        when(filaPedidosPreparacaoGateway.findByPedidocodigoId(pedidoId))
                .thenReturn(Optional.empty());

        StepVerifier.create(filaPedidosPreparacaoUseCase.findByPedidoPorId(pedidoId))
                .expectError(EntityNotFoundException.class)
                .verify();

        verify(filaPedidosPreparacaoGateway).findByPedidocodigoId(pedidoId);
    }

    @Test
    void deveRemoverPedidoDaFilaComSucesso() {

        Long pedidoId = 1L;

        FilaPedidosPreparacao fila =
                new FilaPedidosPreparacao(10L, pedidoId);

        when(filaPedidosPreparacaoGateway.findByPedidocodigoId(pedidoId))
                .thenReturn(Optional.of(fila));

        when(filaPedidosPreparacaoGateway.removerPedidoDaFila(fila))
                .thenReturn(Mono.empty());

        StepVerifier.create(filaPedidosPreparacaoUseCase.removerPedidoDaFila(pedidoId))
                .verifyComplete();

        verify(filaPedidosPreparacaoGateway).findByPedidocodigoId(pedidoId);
        verify(filaPedidosPreparacaoGateway).removerPedidoDaFila(fila);
    }

    @Test
    void devePropagarErroAoRemoverPedidoInexistenteDaFila() {

        Long pedidoId = 1L;

        when(filaPedidosPreparacaoGateway.findByPedidocodigoId(pedidoId))
                .thenReturn(Optional.empty());

        StepVerifier.create(filaPedidosPreparacaoUseCase.removerPedidoDaFila(pedidoId))
                .expectError(EntityNotFoundException.class)
                .verify();

        verify(filaPedidosPreparacaoGateway).findByPedidocodigoId(pedidoId);
        verify(filaPedidosPreparacaoGateway, never()).removerPedidoDaFila(any());
    }
}