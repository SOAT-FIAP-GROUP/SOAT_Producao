package br.com.ms_entregas.usecase.impl;

import br.com.ms_entregas.entity.Entrega;
import br.com.ms_entregas.gateway.IEntregaGateway;
import br.com.ms_entregas.gateway.IFilaPedidosPreparacaoGateway;
import br.com.ms_entregas.gateway.IPedidoGateway;
import br.com.ms_entregas.gateway.entity.enums.StatusPedidoEnum;
import br.com.ms_entregas.usecase.IEntregaUseCase;
import br.com.ms_entregas.usecase.IFilaPedidosPreparacaoUseCase;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class EntregaUseCase implements IEntregaUseCase {

    private final IPedidoGateway pedidoGateway;
    private final IEntregaGateway entregaGateway;
    private final IFilaPedidosPreparacaoUseCase filaPedidosPreparacaoUseCase;

    public EntregaUseCase(IPedidoGateway pedidoGateway,IEntregaGateway entregaGateway, IFilaPedidosPreparacaoUseCase filaPedidosPreparacaoUseCase) {
        this.pedidoGateway = pedidoGateway;
        this.entregaGateway = entregaGateway;
        this.filaPedidosPreparacaoUseCase = filaPedidosPreparacaoUseCase;

    }

    @Override
    public Mono<Entrega> entregarPedido(Entrega entrega) {

        return filaPedidosPreparacaoUseCase
                .findByPedidoPorId(entrega.pedido())
                .flatMap(fila ->
                        pedidoGateway
                                .atualizarStatusPedido(entrega.pedido(), StatusPedidoEnum.FINALIZADO)
                                .then(Mono.fromCallable(() -> entregaGateway.save(
                                                        new Entrega(
                                                                null,
                                                                entrega.pedido(),
                                                                StatusPedidoEnum.FINALIZADO,
                                                                entrega.dataHoraEntrega()
                                                        )
                                                )
                                        ).subscribeOn(Schedulers.boundedElastic())
                                )
                                .flatMap(entregaSalva ->
                                        filaPedidosPreparacaoUseCase
                                                .removerPedidoDaFila(entrega.pedido())
                                                .thenReturn(entregaSalva)
                                )
                );
    }


}