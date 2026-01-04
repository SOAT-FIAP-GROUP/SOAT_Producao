package br.com.ms_entregas.gateway.impl.http;

import br.com.ms_entregas.gateway.IPedidoGateway;
import br.com.ms_entregas.gateway.entity.enums.StatusPedidoEnum;
import br.com.ms_entregas.gateway.impl.http.client.PedidoClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class PedidoGatewayHttp implements IPedidoGateway {

    private final PedidoClient pedidoClient;

    public PedidoGatewayHttp(PedidoClient pedidoClient) {
        this.pedidoClient = pedidoClient;
    }

    @Override
    public Mono<Void> entregarPedidoFila(Long pedidoId) {
        return pedidoClient.removerPedidoDaFilaDePreparo(pedidoId);
    }

    @Override
    public Mono<Void> atualizarStatusPedido(Long pedidoId, StatusPedidoEnum statusPedidoEnum) {
        return pedidoClient.atualizarStatusPedido(pedidoId, statusPedidoEnum);
    }
}
