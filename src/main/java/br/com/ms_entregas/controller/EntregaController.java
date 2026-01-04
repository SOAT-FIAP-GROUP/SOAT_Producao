package br.com.ms_entregas.controller;

import br.com.ms_entregas.controller.mapper.EntregaMapper;
import br.com.ms_entregas.controller.mapper.dto.request.EntregaRequest;
import br.com.ms_entregas.controller.mapper.dto.response.EntregaResponse;
import br.com.ms_entregas.gateway.entity.enums.StatusPedidoEnum;
import br.com.ms_entregas.usecase.IEntregaUseCase;
import reactor.core.publisher.Mono;

public class EntregaController {

    private final IEntregaUseCase entregaUseCase;

    public EntregaController(IEntregaUseCase entregaUseCase) {
        this.entregaUseCase = entregaUseCase;
    }

    public Mono<EntregaResponse> entregarPedido(EntregaRequest entregaRequest) {
        return entregaUseCase
                .entregarPedido(EntregaMapper.toEntity(entregaRequest))
                .map(entrega ->
                        EntregaMapper.toResponse(entrega, StatusPedidoEnum.FINALIZADO)
                );
    }

}