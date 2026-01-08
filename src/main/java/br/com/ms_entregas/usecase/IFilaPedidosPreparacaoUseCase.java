package br.com.ms_entregas.usecase;


import br.com.ms_entregas.entity.FilaPedidosPreparacao;
import reactor.core.publisher.Mono;

public interface IFilaPedidosPreparacaoUseCase {
    Mono<FilaPedidosPreparacao> salvar(Long idPedido);

    Mono<FilaPedidosPreparacao> findByPedidoPorId(Long id);

    Mono<Void> removerPedidoDaFila(Long idPedido);
}
