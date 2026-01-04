package br.com.ms_entregas.usecase.impl;

import br.com.ms_entregas.entity.Entrega;
import br.com.ms_entregas.gateway.IEntregaGateway;
import br.com.ms_entregas.gateway.IPedidoGateway;
import br.com.ms_entregas.gateway.entity.enums.StatusPedidoEnum;
import br.com.ms_entregas.usecase.IEntregaUseCase;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class EntregaUseCase implements IEntregaUseCase {

    private final IPedidoGateway pedidoGateway;
    private final IEntregaGateway entregaGateway;

    public EntregaUseCase(IPedidoGateway pedidoGateway,IEntregaGateway entregaGateway) {
        this.pedidoGateway = pedidoGateway;
        this.entregaGateway = entregaGateway;
    }

    @Override
    public Mono<Entrega> entregarPedido(Entrega entrega) {

        return pedidoGateway
                .atualizarStatusPedido(entrega.pedido(), StatusPedidoEnum.FINALIZADO)
                .then(
                        Mono.fromCallable(() ->
                                entregaGateway.save(
                                        new Entrega(null,
                                                entrega.pedido(),
                                                StatusPedidoEnum.FINALIZADO,
                                                entrega.dataHoraEntrega())
                                )
                        ).subscribeOn(Schedulers.boundedElastic())
                )
                .flatMap(entregaSalva ->
                        pedidoGateway.entregarPedidoFila(entrega.pedido())
                                .thenReturn(entregaSalva));
    }
}