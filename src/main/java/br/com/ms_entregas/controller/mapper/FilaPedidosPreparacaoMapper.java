package br.com.ms_entregas.controller.mapper;

import br.com.ms_entregas.controller.mapper.dto.response.FilaResponse;
import br.com.ms_entregas.entity.FilaPedidosPreparacao;
import br.com.ms_entregas.gateway.entity.FilaPedidosPreparacaoEntity;

public class FilaPedidosPreparacaoMapper {

    public static FilaPedidosPreparacaoEntity toEntityPersistence(FilaPedidosPreparacao filaPedidosPreparacao) {
        return new FilaPedidosPreparacaoEntity(filaPedidosPreparacao.id(), filaPedidosPreparacao.pedido());
    }

    public static FilaResponse toResponse(FilaPedidosPreparacao filaPedidosPreparacao) {
        return new FilaResponse(filaPedidosPreparacao.id(), filaPedidosPreparacao.pedido());
    }
}
