package br.com.ms_entregas.gateway.persistence;

import br.com.ms_entregas.gateway.entity.EntregaEntity;

public interface IEntregaRepository {
    EntregaEntity save(EntregaEntity entity);
    EntregaEntity findById(String id);
}
