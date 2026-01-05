package br.com.ms_entregas.gateway.impl.http.dto.response;

import br.com.ms_entregas.gateway.entity.enums.StatusPedidoEnum;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponse(Long id, Long idUsuario, StatusPedidoEnum status, BigDecimal valorTotal, LocalDateTime dataHoraSolicitacao, Time tempoTotalPreparo, List<PedidoItemResponse> itens) {
}
