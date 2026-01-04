package br.com.ms_entregas.entity;

import br.com.ms_entregas.gateway.entity.enums.StatusPedidoEnum;

import java.time.LocalDateTime;

public record Entrega(Long id, Long pedido, StatusPedidoEnum statusPedidoEnum, LocalDateTime dataHoraEntrega) {
}
