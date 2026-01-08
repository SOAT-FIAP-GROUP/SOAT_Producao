package br.com.ms_entregas.gateway.impl.http.client;

import br.com.ms_entregas.gateway.entity.enums.StatusPedidoEnum;
import br.com.ms_entregas.gateway.impl.http.dto.response.PedidoResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static reactor.netty.http.HttpConnectionLiveness.log;

@Component
public class PedidoClient {
    private final WebClient webClient;

    public PedidoClient(WebClient webClient) {
        this.webClient = webClient;
    }

    private final String BASEURL = "/api/pedido";

    public Mono<Void> removerPedidoDaFilaDePreparo(Long id) {
        return webClient.delete()
                .uri(uriBuilder -> uriBuilder
                        .path(BASEURL + "/remover/fila/{codigoPedido}")
                        .build(id)
                )
                .retrieve()
                .bodyToMono(Void.class)
                .doOnSuccess(v ->
                        log.info("Status do pedido {} atualizado com sucesso", id)
                )
                .doOnError(e ->
                        log.error("Erro ao atualizar status do pedido {} ",
                                id, e)
                );
    }

    public Mono<Void> atualizarStatusPedido(Long codigo, StatusPedidoEnum status) {
        return webClient.put()
                .uri(uriBuilder -> uriBuilder
                        .path(BASEURL + "/status/{codigo}")
                        .queryParam("status", status)
                        .build(codigo)
                )
                .retrieve()
                .bodyToMono(Void.class)
                .doOnSuccess(v ->
                        log.info("Status do pedido {} atualizado com sucesso para {}", codigo, status)
                )
                .doOnError(e ->
                        log.error("Erro ao atualizar status do pedido {} para {}",
                                codigo, status, e)
                );
    }

    public Mono<PedidoResponse> buscarPedidoPorId(Long codigo) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(BASEURL + "/buscar/{codigoPedido}")
                        .build(codigo)
                )
                .retrieve()
                .bodyToMono(PedidoResponse.class)
                .doOnSuccess(v ->
                        log.info("Sucesso ao buscar Pedido com id {}.", codigo)
                )
                .doOnError(e ->
                        log.error("Erro ao buscar Pedido com id {}. Error={}",
                                codigo, e)
                );
    }


}

