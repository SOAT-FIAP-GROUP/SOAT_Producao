package br.com.ms_entregas.bdd;

import br.com.ms_entregas.controller.FilaPedidosController;
import br.com.ms_entregas.usecase.IFilaPedidosPreparacaoUseCase;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

public class FilaPedidoControllerBDDTest {

    private IFilaPedidosPreparacaoUseCase filaPedidosPreparacaoUseCase;
    private FilaPedidosController filaPedidosController;
    private Mono<Void> resultado;
    private Long codigoPedido;

    @Before
    public void setUp() {
        filaPedidosPreparacaoUseCase = mock(IFilaPedidosPreparacaoUseCase.class);
        filaPedidosController = new FilaPedidosController(filaPedidosPreparacaoUseCase);
    }

    @Given("que há um pedido na fila de preparo")
    public void que_ha_um_pedido_na_fila_de_preparo() {
        this.codigoPedido = 1L;
    }

    @When("o servico invocar a funcao de remover o pedido da fila de preparo")
    public void o_servico_invocar_a_funcao_de_remover_o_pedido_da_fila_de_preparo() {
        when(filaPedidosPreparacaoUseCase.removerPedidoDaFila(codigoPedido))
                .thenReturn(Mono.empty());
        when(filaPedidosPreparacaoUseCase.removerPedidoDaFila(codigoPedido))
                .thenReturn(Mono.empty());
        this.resultado = filaPedidosController.removerPedidoDaFila(codigoPedido);
    }

    @Then("a funcao deve ser executada com sucesso")
    public void a_funcao_deve_ser_executada_com_sucesso() {
        StepVerifier.create(resultado)
                .verifyComplete();

        verify(filaPedidosPreparacaoUseCase).removerPedidoDaFila(codigoPedido);
    }
}