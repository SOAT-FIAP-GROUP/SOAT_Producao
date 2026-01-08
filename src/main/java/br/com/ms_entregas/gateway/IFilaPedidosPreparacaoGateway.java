package br.com.ms_entregas.gateway;

import br.com.ms_entregas.entity.FilaPedidosPreparacao;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface IFilaPedidosPreparacaoGateway {

    FilaPedidosPreparacao save(FilaPedidosPreparacao entity);

    Optional<FilaPedidosPreparacao> findById(Long id);

    Optional<FilaPedidosPreparacao> findByPedidocodigoId(Long id);

    Mono<Void> removerPedidoDaFila(FilaPedidosPreparacao entity);

}
