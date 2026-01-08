package br.com.ms_entregas.gateway;

import br.com.ms_entregas.entity.Entrega;

import java.util.Optional;

public interface IEntregaGateway {

    Optional<Entrega> findById(Long id);

    Entrega save(Entrega entity);
}