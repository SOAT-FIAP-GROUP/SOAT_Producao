package br.com.ms_entregas.usecase.impl;


import br.com.ms_entregas.entity.FilaPedidosPreparacao;
import br.com.ms_entregas.exception.EntityNotFoundException;
import br.com.ms_entregas.gateway.IFilaPedidosPreparacaoGateway;
import br.com.ms_entregas.gateway.IPedidoGateway;
import br.com.ms_entregas.usecase.IFilaPedidosPreparacaoUseCase;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class FilaPedidosPreparacaoUseCase implements IFilaPedidosPreparacaoUseCase {

    private final IFilaPedidosPreparacaoGateway filaPedidosPreparacaoGateway;
    private final IPedidoGateway pedidoGateway;

    public FilaPedidosPreparacaoUseCase(IFilaPedidosPreparacaoGateway filaPedidosPreparacaoGateway, IPedidoGateway pedidoGateway) {
        this.filaPedidosPreparacaoGateway = filaPedidosPreparacaoGateway;
        this.pedidoGateway = pedidoGateway;
    }
    @Override
    public Mono<FilaPedidosPreparacao> salvar(Long idPedido) {
        return pedidoGateway.buscarPedidoPorId(idPedido)
                .flatMap(pedido -> {
                    FilaPedidosPreparacao fila =
                            new FilaPedidosPreparacao(null, idPedido);

                    return Mono.fromCallable(() ->
                            filaPedidosPreparacaoGateway.save(fila)
                    );
                });
    }

    @Override
    public Mono<FilaPedidosPreparacao> findByPedidoPorId(Long id) {
        return Mono.fromCallable(() ->
                filaPedidosPreparacaoGateway
                        .findByPedidocodigoId(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        FilaPedidosPreparacao.class, id
                                )
                        )
        ).subscribeOn(Schedulers.boundedElastic());
    }


    @Override
    public Mono<Void> removerPedidoDaFila(Long idPedido) {
        return findByPedidoPorId(idPedido)
                .flatMap(filaPedidosPreparacaoGateway::removerPedidoDaFila);
    }

}
