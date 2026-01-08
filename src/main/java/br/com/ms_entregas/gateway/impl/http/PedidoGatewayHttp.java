package br.com.ms_entregas.gateway.impl.http;

import br.com.ms_entregas.gateway.IPedidoGateway;
import br.com.ms_entregas.gateway.entity.enums.StatusPedidoEnum;
import br.com.ms_entregas.gateway.impl.http.client.PedidoClient;
import br.com.ms_entregas.gateway.impl.http.dto.response.PedidoResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class PedidoGatewayHttp implements IPedidoGateway {

    private final PedidoClient pedidoClient;

    public PedidoGatewayHttp(PedidoClient pedidoClient) {
        this.pedidoClient = pedidoClient;
    }

    @Override
    public Mono<Void> atualizarStatusPedido(Long pedidoId, StatusPedidoEnum statusPedidoEnum) {
        return pedidoClient.atualizarStatusPedido(pedidoId, statusPedidoEnum);
    }

    @Override
    public Mono<PedidoResponse> buscarPedidoPorId(Long idPedido) {
        return pedidoClient.buscarPedidoPorId(idPedido);
    }

}
