package br.com.ms_entregas.api.controller;

import br.com.ms_entregas.controller.FilaPedidosController;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fila")
public class FilaPedidosAPIController {

    private final FilaPedidosController filaPedidosController;

    public FilaPedidosAPIController(FilaPedidosController filaPedidosController) {
        this.filaPedidosController = filaPedidosController;
    }

    @DeleteMapping("/remover/{codigoPedido}")
    @Transactional
    @Operation(summary = "Remove pedido da fila de preparo", description = "Remove pedido da fila de preparo com base no código do pedido")
    public ResponseEntity<Void> removerPedidoDaFilaDePreparo(@PathVariable Long codigoPedido) {
        filaPedidosController.removerPedidoDaFila(codigoPedido);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PostMapping("/adicionar/{codigoPedido}")
    @Transactional
    @Operation(summary = "Remove pedido da fila de preparo", description = "Adiciona pedido da fila de preparo com base no código do pedido")
    public ResponseEntity<Void> adicionarPedidoNaFila(@PathVariable Long codigoPedido) {
        filaPedidosController.salvarPedidoNaFila(codigoPedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
