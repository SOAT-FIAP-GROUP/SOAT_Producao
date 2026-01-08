package br.com.ms_entregas.controller.mapper;

import br.com.ms_entregas.entity.FilaPedidosPreparacao;
import br.com.ms_entregas.gateway.entity.FilaPedidosPreparacaoEntity;

public class FilaPedidosPreparacaoMapper {

    public static FilaPedidosPreparacaoEntity toEntityPersistence(FilaPedidosPreparacao filaPedidosPreparacao){
        return new FilaPedidosPreparacaoEntity(filaPedidosPreparacao.id().toString(), filaPedidosPreparacao.pedido().toString());
    }
}
