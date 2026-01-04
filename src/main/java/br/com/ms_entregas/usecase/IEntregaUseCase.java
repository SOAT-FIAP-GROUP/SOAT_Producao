package br.com.ms_entregas.usecase;

import br.com.ms_entregas.entity.Entrega;
import reactor.core.publisher.Mono;

public interface IEntregaUseCase {

    Mono<Entrega> entregarPedido(Entrega entrega);
}
