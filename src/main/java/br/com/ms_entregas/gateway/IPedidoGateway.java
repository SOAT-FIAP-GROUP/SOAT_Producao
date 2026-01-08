package br.com.ms_entregas.gateway;


import br.com.ms_entregas.gateway.entity.enums.StatusPedidoEnum;
import br.com.ms_entregas.gateway.impl.http.dto.response.PedidoResponse;
import reactor.core.publisher.Mono;

public interface IPedidoGateway {

    Mono<Void> atualizarStatusPedido(Long pedidoId, StatusPedidoEnum statusPedidoEnum);

    Mono<PedidoResponse> buscarPedidoPorId(Long idPedido);
}
