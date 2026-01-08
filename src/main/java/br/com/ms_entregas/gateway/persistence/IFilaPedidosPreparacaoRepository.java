package br.com.ms_entregas.gateway.persistence;

import br.com.ms_entregas.entity.FilaPedidosPreparacao;
import br.com.ms_entregas.gateway.entity.FilaPedidosPreparacaoEntity;

import java.util.Optional;

public interface IFilaPedidosPreparacaoRepository {
    FilaPedidosPreparacaoEntity save(FilaPedidosPreparacaoEntity entity);
    Optional<FilaPedidosPreparacaoEntity> findByPedidocodigo(String id);
    Optional<FilaPedidosPreparacao>findById(String id);
    void delete(FilaPedidosPreparacaoEntity id);
}
