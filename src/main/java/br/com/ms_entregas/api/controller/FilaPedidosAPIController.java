package br.com.ms_entregas.api.controller;

import br.com.ms_entregas.controller.FilaPedidosController;
import br.com.ms_entregas.controller.mapper.dto.response.FilaResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/fila")
public class FilaPedidosAPIController {

    private final FilaPedidosController filaPedidosController;

    public FilaPedidosAPIController(FilaPedidosController filaPedidosController) {
        this.filaPedidosController = filaPedidosController;
    }

    @DeleteMapping("/remover/{codigoPedido}")
    @Operation(summary = "Remove pedido da fila de preparo",description = "Remove pedido da fila de preparo com base no id do pedido")
    public Mono<ResponseEntity<Void>> removerPedidoDaFilaDePreparo(@PathVariable Long codigoPedido) {
        return filaPedidosController.removerPedidoDaFila(codigoPedido)
                .thenReturn(ResponseEntity.noContent().build());
    }

    @PostMapping("/adicionar/{codigoPedido}")
    @Transactional
    @Operation(summary = "Adiciona pedido na fila de preparo", description = "Adiciona pedido da fila de preparo com base no código do pedido")
    public Mono<ResponseEntity<Void>> adicionarPedidoNaFila(@PathVariable Long codigoPedido) {
        return filaPedidosController.salvarPedidoNaFila(codigoPedido)
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED).build());
    }

    @GetMapping("/buscar/{codigoPedido}")
    @Operation(summary = "Busca pedido na fila", description = "Busca pedido na fila de preparo com base no código do pedido")
    public Mono<ResponseEntity<FilaResponse>> buscarPedidoNaFilaPorCodigo(@PathVariable Long codigoPedido) {
        return filaPedidosController.buscarPedidoNaFila(codigoPedido)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/listar")
    @Operation(summary = "Lista os pedidos na fila", description = "lista todos os pedidos na fila")
    public ResponseEntity<List<FilaResponse>> listarPedidosNaFila() {
        var fila = filaPedidosController.listarPedidosNaFila();
        return ResponseEntity.status(HttpStatus.OK).body(fila);
    }
}
