package br.com.ms_entregas.controller.mapper;

import br.com.ms_entregas.controller.mapper.dto.request.EntregaRequest;
import br.com.ms_entregas.controller.mapper.dto.response.EntregaResponse;
import br.com.ms_entregas.entity.Entrega;
import br.com.ms_entregas.gateway.entity.EntregaEntity;
import br.com.ms_entregas.gateway.entity.enums.StatusPedidoEnum;


public class EntregaMapper {

    public static EntregaResponse toResponse(Entrega entity, StatusPedidoEnum statusPedidoEnum) {
        return new EntregaResponse(entity.id(), statusPedidoEnum, entity.dataHoraEntrega());
    }

    public static EntregaEntity toEntityPersistence(Entrega entity) {
        return new EntregaEntity(entity.id().toString(), entity.pedido().toString(), entity.dataHoraEntrega().toString());
    }

    public static Entrega toEntity(EntregaRequest entrega) {
        return new Entrega(null, entrega.pedidoId(), null, entrega.dataHoraSolicitacao());
    }
}
