package br.com.ms_entregas.api.controller;

import br.com.ms_entregas.controller.EntregaController;
import br.com.ms_entregas.controller.mapper.dto.request.EntregaRequest;
import br.com.ms_entregas.controller.mapper.dto.response.EntregaResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/entregar")
public class EntregaAPIController {

    private final EntregaController entregaController;

    public EntregaAPIController(EntregaController entregaController) {this.entregaController = entregaController;}


    @Operation(
            summary = "Finalizar Pedido",
            description = "Rota responsável por finalizar o pedido e retirar da fila"
    )
    @PostMapping
    public Mono<ResponseEntity<EntregaResponse>> finalizarPedido(
            @RequestBody @Valid EntregaRequest entregaRequest) {

        return entregaController
                .entregarPedido(entregaRequest)
                .map(ResponseEntity::ok);
    }

}