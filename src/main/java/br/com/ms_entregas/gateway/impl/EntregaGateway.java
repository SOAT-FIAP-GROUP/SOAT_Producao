package br.com.ms_entregas.gateway.impl;

import br.com.ms_entregas.controller.mapper.EntregaMapper;
import br.com.ms_entregas.entity.Entrega;
import br.com.ms_entregas.gateway.IEntregaGateway;
import br.com.ms_entregas.gateway.persistence.IEntregaRepository;

import java.util.Optional;

public class EntregaGateway implements IEntregaGateway {

    private final IEntregaRepository IEntregaRepository;

    public EntregaGateway(IEntregaRepository IEntregaRepository) {
        this.IEntregaRepository = IEntregaRepository;
    }

    @Override
    public Optional<Entrega> findById(Long id) {
        return Optional.ofNullable(this.IEntregaRepository.findById(id.toString()).toModel());
    }

    @Override
    public Entrega save(Entrega entity) {
        return this.IEntregaRepository.save(EntregaMapper.toEntityPersistence(entity)).toModel();
    }
}
