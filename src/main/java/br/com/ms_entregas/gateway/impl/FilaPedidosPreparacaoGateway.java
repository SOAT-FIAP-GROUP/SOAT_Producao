package br.com.ms_entregas.gateway.impl;


import br.com.ms_entregas.controller.mapper.FilaPedidosPreparacaoMapper;
import br.com.ms_entregas.entity.FilaPedidosPreparacao;
import br.com.ms_entregas.gateway.IFilaPedidosPreparacaoGateway;
import br.com.ms_entregas.gateway.entity.FilaPedidosPreparacaoEntity;
import br.com.ms_entregas.gateway.persistence.IFilaPedidosPreparacaoRepository;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Optional;

public class FilaPedidosPreparacaoGateway implements IFilaPedidosPreparacaoGateway {

    private final IFilaPedidosPreparacaoRepository IFilaPedidosPreparacaoRepository;

    public FilaPedidosPreparacaoGateway(IFilaPedidosPreparacaoRepository IFilaPedidosPreparacaoRepository) {
        this.IFilaPedidosPreparacaoRepository = IFilaPedidosPreparacaoRepository;
    }

    @Override
    public FilaPedidosPreparacao save(FilaPedidosPreparacao entity) {
        return IFilaPedidosPreparacaoRepository.save(FilaPedidosPreparacaoMapper.toEntityPersistence(entity)).toModel();
    }

    @Override
    public Optional<FilaPedidosPreparacao> findById(Long id) {
        return IFilaPedidosPreparacaoRepository.findById(String.valueOf(id));
    }

    @Override
    public Optional<FilaPedidosPreparacao> findByPedidocodigoId(Long id) {
        return IFilaPedidosPreparacaoRepository.findByPedidocodigo(String.valueOf(id)).map(FilaPedidosPreparacaoEntity::toModel);
    }

    @Override
    public Mono<Void> removerPedidoDaFila(FilaPedidosPreparacao entity) {
        return Mono.fromRunnable(() ->
                IFilaPedidosPreparacaoRepository.delete(
                        FilaPedidosPreparacaoMapper.toEntityPersistence(entity)
                )
        ).subscribeOn(Schedulers.boundedElastic()).then();
    }


}
