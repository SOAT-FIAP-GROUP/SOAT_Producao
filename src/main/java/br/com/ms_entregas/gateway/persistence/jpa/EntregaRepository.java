package br.com.ms_entregas.gateway.persistence.jpa;

import br.com.ms_entregas.gateway.entity.EntregaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends JpaRepository<EntregaEntity, Long> {
}
