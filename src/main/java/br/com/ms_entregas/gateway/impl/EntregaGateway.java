package br.com.ms_entregas.gateway.impl;

import br.com.ms_entregas.controller.mapper.EntregaMapper;
import br.com.ms_entregas.entity.Entrega;
import br.com.ms_entregas.gateway.IEntregaGateway;
import br.com.ms_entregas.gateway.entity.EntregaEntity;
import br.com.ms_entregas.gateway.persistence.jpa.EntregaRepository;

import java.util.Optional;

public class EntregaGateway implements IEntregaGateway {

    private final EntregaRepository entregaRepository;

    public EntregaGateway(EntregaRepository entregaRepository) {
        this.entregaRepository = entregaRepository;
    }

    @Override
    public Optional<Entrega> findById(Long id) {
        return this.entregaRepository.findById(id).map(EntregaEntity::toModel);
    }

    @Override
    public Entrega save(Entrega entity) {
        return this.entregaRepository.save(EntregaMapper.toEntityPersistence(entity)).toModel();
    }
}
