package br.com.ms_entregas.controller.mapper.dto.response;

import br.com.ms_entregas.gateway.entity.enums.StatusPedidoEnum;

import java.time.LocalDateTime;

public record EntregaResponse(Long id, StatusPedidoEnum status, LocalDateTime dataHoraEntrega) {
}
