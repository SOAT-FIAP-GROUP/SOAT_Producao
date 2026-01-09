package br.com.ms_entregas.controller;

import br.com.ms_entregas.controller.mapper.EntregaMapper;
import br.com.ms_entregas.controller.mapper.dto.request.EntregaRequest;
import br.com.ms_entregas.controller.mapper.dto.response.EntregaResponse;
import br.com.ms_entregas.entity.Entrega;
import br.com.ms_entregas.gateway.entity.enums.StatusPedidoEnum;
import br.com.ms_entregas.usecase.IEntregaUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class EntregaControllerTest {

    private IEntregaUseCase entregaUseCase;

    private EntregaController entregaController;

    @BeforeEach
    void setUp() {
        entregaUseCase = mock(IEntregaUseCase.class);

        entregaController = new EntregaController(entregaUseCase);
    }

    @Test
    void deveRetornarEntregaResponseQuandoPedidoForEntregueComSucesso() {

        EntregaRequest entregaRequest = mock(EntregaRequest.class);
        Entrega entrega = mock(Entrega.class);
        Entrega entregaSalva = mock(Entrega.class);
        EntregaResponse entregaResponse = mock(EntregaResponse.class);

        try (MockedStatic<EntregaMapper> mockedMapper = mockStatic(EntregaMapper.class)) {

            mockedMapper.when(() -> EntregaMapper.toEntity(entregaRequest))
                    .thenReturn(entrega);

            mockedMapper.when(() ->
                            EntregaMapper.toResponse(entregaSalva, StatusPedidoEnum.FINALIZADO))
                    .thenReturn(entregaResponse);

            when(entregaUseCase.entregarPedido(entrega))
                    .thenReturn(Mono.just(entregaSalva));

            Mono<EntregaResponse> resultado =
                    entregaController.entregarPedido(entregaRequest);

            StepVerifier.create(resultado)
                    .expectNext(entregaResponse)
                    .verifyComplete();

            verify(entregaUseCase, times(1)).entregarPedido(entrega);
        }
    }
}
