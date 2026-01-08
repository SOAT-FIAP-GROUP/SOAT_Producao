package br.com.ms_entregas.config;

import br.com.ms_entregas.api.controller.FilaPedidosAPIController;
import br.com.ms_entregas.controller.FilaPedidosController;
import br.com.ms_entregas.gateway.IFilaPedidosPreparacaoGateway;
import br.com.ms_entregas.gateway.IPedidoGateway;
import br.com.ms_entregas.gateway.impl.FilaPedidosPreparacaoGateway;
import br.com.ms_entregas.gateway.persistence.jpa.FilaPedidosPreparacaoRepository;
import br.com.ms_entregas.usecase.IFilaPedidosPreparacaoUseCase;
import br.com.ms_entregas.usecase.impl.FilaPedidosPreparacaoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilaPedidoConfig {

//    @Bean
//    FilaPedidosAPIController FilaPedidosAPIController(FilaPedidosController filaPedidosController) {
//        return new FilaPedidosAPIController(filaPedidosController);
//    }

    @Bean
    FilaPedidosController filaPedidosController(IFilaPedidosPreparacaoUseCase filaPedidosPreparacaoUseCase){
        return new FilaPedidosController(filaPedidosPreparacaoUseCase);
    }


    @Bean
    IFilaPedidosPreparacaoGateway filaPedidoGateway(FilaPedidosPreparacaoRepository filaPedidosPreparacaoRepository) {
        return new FilaPedidosPreparacaoGateway(filaPedidosPreparacaoRepository);
    }

    @Bean
    FilaPedidosPreparacaoUseCase filaPedidoUseCase(IFilaPedidosPreparacaoGateway filaPedidosPreparacaoGateway, IPedidoGateway pedidoGateway){
        return new FilaPedidosPreparacaoUseCase(filaPedidosPreparacaoGateway, pedidoGateway);
    }

}