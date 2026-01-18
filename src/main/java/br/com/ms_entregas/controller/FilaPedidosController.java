package br.com.ms_entregas.controller;

import br.com.ms_entregas.controller.mapper.FilaPedidosPreparacaoMapper;
import br.com.ms_entregas.controller.mapper.dto.response.FilaResponse;
import br.com.ms_entregas.entity.FilaPedidosPreparacao;
import br.com.ms_entregas.usecase.IFilaPedidosPreparacaoUseCase;
import reactor.core.publisher.Mono;

import java.util.List;

public class FilaPedidosController {

    private final IFilaPedidosPreparacaoUseCase filaPedidosPreparacaoUseCase;

    public FilaPedidosController(IFilaPedidosPreparacaoUseCase filaPedidosPreparacaoUseCase) {
        this.filaPedidosPreparacaoUseCase = filaPedidosPreparacaoUseCase;
    }

    public Mono<Void> removerPedidoDaFila(Long codigoPedido) {
        return filaPedidosPreparacaoUseCase.removerPedidoDaFila(codigoPedido);
    }

    public Mono<FilaPedidosPreparacao> salvarPedidoNaFila(Long codigoPedido) {
        return filaPedidosPreparacaoUseCase.salvar(codigoPedido);
    }

    public Mono<FilaResponse> buscarPedidoNaFila(Long codigoPedido) {
        return filaPedidosPreparacaoUseCase.findByPedidoPorId(codigoPedido)
                .map(FilaPedidosPreparacaoMapper::toResponse);
    }

    public List<FilaResponse> listarPedidosNaFila() {
        return filaPedidosPreparacaoUseCase.listarPedidosNaFila()
                .stream().map(FilaPedidosPreparacaoMapper::toResponse).toList();
    }
}
