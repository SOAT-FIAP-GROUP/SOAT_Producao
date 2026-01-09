Feature: Produção
  Como usuário desejo remover pedidos da fila de preparo

  @Fila
  Scenario: Remover pedido da fila de preparo com sucesso
    Given que há um pedido na fila de preparo
    When o servico invocar a funcao de remover o pedido da fila de preparo
    Then a funcao deve ser executada com sucesso