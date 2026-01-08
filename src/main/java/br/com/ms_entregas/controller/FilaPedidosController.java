package br.com.ms_entregas.controller;

import br.com.ms_entregas.entity.FilaPedidosPreparacao;
import br.com.ms_entregas.usecase.IFilaPedidosPreparacaoUseCase;
import reactor.core.publisher.Mono;

public class FilaPedidosController {

    private final IFilaPedidosPreparacaoUseCase filaPedidosPreparacaoUseCase;

    public FilaPedidosController(IFilaPedidosPreparacaoUseCase filaPedidosPreparacaoUseCase) {
        this.filaPedidosPreparacaoUseCase = filaPedidosPreparacaoUseCase;
    }

    public void removerPedidoDaFila(Long codigoPedido) {
        filaPedidosPreparacaoUseCase.removerPedidoDaFila(codigoPedido);
    }

    public void salvarPedidoNaFila(Long codigoPedido) {
        filaPedidosPreparacaoUseCase.salvar(codigoPedido);
    }

}
