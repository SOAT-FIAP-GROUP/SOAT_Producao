package br.com.ms_entregas.gateway.impl;

import br.com.ms_entregas.entity.FilaPedidosPreparacao;
import br.com.ms_entregas.gateway.entity.FilaPedidosPreparacaoEntity;
import br.com.ms_entregas.gateway.persistence.IFilaPedidosPreparacaoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

import java.util.Optional;

@Repository
public class FilaPedidoPreparacaoRepository implements IFilaPedidosPreparacaoRepository {
    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;
    private DynamoDbTable<FilaPedidosPreparacaoEntity> filaTable;

    public FilaPedidoPreparacaoRepository(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        this.dynamoDbEnhancedClient = dynamoDbEnhancedClient;
    }

    @PostConstruct
    public void init() {
        filaTable = dynamoDbEnhancedClient.table("Fila_Pedido", TableSchema.fromBean(FilaPedidosPreparacaoEntity.class));
    }


    @Override
    public FilaPedidosPreparacaoEntity save(FilaPedidosPreparacaoEntity entity) {
        filaTable.putItem(entity);
        return entity;
    }

    @Override
    public Optional<FilaPedidosPreparacaoEntity> findByPedidocodigo(String id) {
        DynamoDbIndex<FilaPedidosPreparacaoEntity> index =
                filaTable.index("pedidoCodigo");

        for (Page<FilaPedidosPreparacaoEntity> page : index.query(r ->
                r.queryConditional(
                        QueryConditional.keyEqualTo(k ->
                                k.partitionValue(id)
                        )
                ))) {

            if (!page.items().isEmpty()) {
                return Optional.of(page.items().get(0));
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<FilaPedidosPreparacao> findById(String id) {
        var key = software.amazon.awssdk.enhanced.dynamodb.Key.builder()
                .partitionValue(id)
                .build();
        return Optional.of(filaTable.getItem(key).toModel());
    }

    @Override
    public void delete(FilaPedidosPreparacaoEntity id) {
        filaTable.deleteItem(
                Key.builder()
                        .partitionValue(String.valueOf(id))
                        .build()
        );
    }
}
