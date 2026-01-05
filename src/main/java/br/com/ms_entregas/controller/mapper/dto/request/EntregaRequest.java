package br.com.ms_entregas.controller.mapper.dto.request;

import java.time.LocalDateTime;

public record EntregaRequest(Long pedidoId, LocalDateTime dataHoraSolicitacao) {
}
