package br.com.ms_entregas.gateway;


import br.com.ms_entregas.gateway.entity.enums.StatusPedidoEnum;
import reactor.core.publisher.Mono;

public interface IPedidoGateway {
    Mono<Void> entregarPedidoFila(Long pedidoId);

    Mono<Void> atualizarStatusPedido(Long pedidoId, StatusPedidoEnum statusPedidoEnum);
}
