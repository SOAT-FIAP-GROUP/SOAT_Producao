package br.com.ms_entregas.api.controller;

import br.com.ms_entregas.controller.FilaPedidosController;
import br.com.ms_entregas.controller.mapper.dto.response.EntregaResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/fila")
public class FilaPedidosAPIController {

    private final FilaPedidosController filaPedidosController;

    public FilaPedidosAPIController(FilaPedidosController filaPedidosController) {
        this.filaPedidosController = filaPedidosController;
    }

    @DeleteMapping("/remover/{codigoPedido}")
    public Mono<ResponseEntity<Void>> removerPedidoDaFilaDePreparo(@PathVariable Long codigoPedido) {
        return filaPedidosController.removerPedidoDaFila(codigoPedido)
                .thenReturn(ResponseEntity.noContent().build());
    }

    @PostMapping("/adicionar/{codigoPedido}")
    @Transactional
    @Operation(summary = "Remove pedido da fila de preparo", description = "Adiciona pedido da fila de preparo com base no código do pedido")
    public Mono<ResponseEntity<Void>> adicionarPedidoNaFila(@PathVariable Long codigoPedido) {
        return filaPedidosController.salvarPedidoNaFila(codigoPedido)
                        .thenReturn(ResponseEntity.status(HttpStatus.CREATED).build());
    }
}
