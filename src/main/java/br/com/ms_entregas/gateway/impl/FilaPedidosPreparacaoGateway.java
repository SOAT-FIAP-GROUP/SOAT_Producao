package br.com.ms_entregas.gateway.impl;


import br.com.ms_entregas.controller.mapper.FilaPedidosPreparacaoMapper;
import br.com.ms_entregas.entity.FilaPedidosPreparacao;
import br.com.ms_entregas.gateway.IFilaPedidosPreparacaoGateway;
import br.com.ms_entregas.gateway.entity.FilaPedidosPreparacaoEntity;
import br.com.ms_entregas.gateway.persistence.jpa.FilaPedidosPreparacaoRepository;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Optional;

public class FilaPedidosPreparacaoGateway implements IFilaPedidosPreparacaoGateway {

    private final FilaPedidosPreparacaoRepository filaPedidosPreparacaoRepository;

    public FilaPedidosPreparacaoGateway(FilaPedidosPreparacaoRepository filaPedidosPreparacaoRepository) {
        this.filaPedidosPreparacaoRepository = filaPedidosPreparacaoRepository;
    }

    @Override
    public FilaPedidosPreparacao save(FilaPedidosPreparacao entity) {
        return filaPedidosPreparacaoRepository.save(FilaPedidosPreparacaoMapper.toEntityPersistence(entity)).toModel();
    }

    @Override
    public Optional<FilaPedidosPreparacao> findById(Long id) {
        return filaPedidosPreparacaoRepository.findById(id).map(FilaPedidosPreparacaoEntity::toModel);
    }

    @Override
    public Optional<FilaPedidosPreparacao> findByPedidocodigoId(Long id) {
        return filaPedidosPreparacaoRepository.findByPedidocodigo(id).map(FilaPedidosPreparacaoEntity::toModel);
    }

    @Override
    public Mono<Void> removerPedidoDaFila(FilaPedidosPreparacao entity) {
        return Mono.fromRunnable(() ->
                filaPedidosPreparacaoRepository.delete(
                        FilaPedidosPreparacaoMapper.toEntityPersistence(entity)
                )
        ).subscribeOn(Schedulers.boundedElastic()).then();
    }


}
