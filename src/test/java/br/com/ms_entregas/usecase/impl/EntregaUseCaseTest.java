package br.com.ms_entregas.usecase.impl;

import br.com.ms_entregas.entity.Entrega;
import br.com.ms_entregas.entity.FilaPedidosPreparacao;
import br.com.ms_entregas.gateway.IEntregaGateway;
import br.com.ms_entregas.gateway.IPedidoGateway;
import br.com.ms_entregas.gateway.entity.enums.StatusPedidoEnum;
import br.com.ms_entregas.usecase.IFilaPedidosPreparacaoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EntregaUseCaseTest {
    private IPedidoGateway pedidoGateway;
    private IEntregaGateway entregaGateway;
    private IFilaPedidosPreparacaoUseCase filaPedidosPreparacaoUseCase;
    private EntregaUseCase entregaUseCase;

    @BeforeEach
    public void setup() {
        entregaGateway = mock(IEntregaGateway.class);
        pedidoGateway = mock(IPedidoGateway.class);
        filaPedidosPreparacaoUseCase = mock(IFilaPedidosPreparacaoUseCase.class);
        entregaUseCase = new EntregaUseCase(pedidoGateway, entregaGateway, filaPedidosPreparacaoUseCase);
    }

    @Test
    void deveEntregarPedidoComSucesso() {

        Long pedidoId = 1L;
        LocalDateTime dataEntrega = LocalDateTime.now();

        Entrega entregaEntrada = new Entrega(
                null,
                pedidoId,
                StatusPedidoEnum.EM_PREPARACAO,
                dataEntrega
        );

        FilaPedidosPreparacao filaMock = mock(FilaPedidosPreparacao.class);

        Entrega entregaSalva = new Entrega(
                10L,
                pedidoId,
                StatusPedidoEnum.FINALIZADO,
                dataEntrega
        );

        // GIVEN
        when(filaPedidosPreparacaoUseCase.findByPedidoPorId(pedidoId))
                .thenReturn(Mono.just(filaMock));

        when(pedidoGateway.atualizarStatusPedido(pedidoId, StatusPedidoEnum.FINALIZADO))
                .thenReturn(Mono.empty());

        when(entregaGateway.save(any(Entrega.class)))
                .thenReturn(entregaSalva);

        when(filaPedidosPreparacaoUseCase.removerPedidoDaFila(pedidoId))
                .thenReturn(Mono.empty());

        // WHEN
        Mono<Entrega> resultado = entregaUseCase.entregarPedido(entregaEntrada);

        // THEN
        StepVerifier.create(resultado)
                .assertNext(entrega -> {
                    assertNotNull(entrega);
                    assertEquals(entregaSalva.pedido(), entrega.pedido());
                    assertEquals(StatusPedidoEnum.FINALIZADO, entrega.statusPedidoEnum());
                    assertEquals(pedidoId, entrega.pedido());
                })
                .verifyComplete();

        verify(filaPedidosPreparacaoUseCase).findByPedidoPorId(pedidoId);
        verify(pedidoGateway).atualizarStatusPedido(pedidoId, StatusPedidoEnum.FINALIZADO);
        verify(entregaGateway).save(any(Entrega.class));
        verify(filaPedidosPreparacaoUseCase).removerPedidoDaFila(pedidoId);
    }

}