package br.com.ms_entregas.config;

import br.com.ms_entregas.controller.EntregaController;
import br.com.ms_entregas.controller.mapper.EntregaMapper;
import br.com.ms_entregas.gateway.IEntregaGateway;
import br.com.ms_entregas.gateway.IPedidoGateway;
import br.com.ms_entregas.gateway.impl.EntregaGateway;
import br.com.ms_entregas.gateway.persistence.IEntregaRepository;
import br.com.ms_entregas.usecase.IEntregaUseCase;
import br.com.ms_entregas.usecase.IFilaPedidosPreparacaoUseCase;
import br.com.ms_entregas.usecase.impl.EntregaUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EntregaConfig {

    @Bean
    EntregaController entregaController(IEntregaUseCase entregaUseCase) {
        return new EntregaController(entregaUseCase);
    }

    @Bean
    EntregaMapper entregaMapper() {
        return new EntregaMapper();
    }

    @Bean
    EntregaUseCase entregaUseCase(IPedidoGateway pedidoGateway, IEntregaGateway entregaGateway, IFilaPedidosPreparacaoUseCase filaPedidosPreparacaoUseCase) {
        return new EntregaUseCase(pedidoGateway, entregaGateway, filaPedidosPreparacaoUseCase);
    }

    @Bean
    EntregaGateway entregaGateway(IEntregaRepository IEntregaRepository) {
        return new EntregaGateway(IEntregaRepository);
    }
}