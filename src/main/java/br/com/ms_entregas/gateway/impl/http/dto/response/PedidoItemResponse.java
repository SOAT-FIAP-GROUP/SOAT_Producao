package br.com.ms_entregas.gateway.impl.http.dto.response;

import java.math.BigDecimal;

public record PedidoItemResponse(Long id, Long pedidoId, Long produtoId, int quantidade, BigDecimal precoUnitario, BigDecimal precoTotal) {
}
