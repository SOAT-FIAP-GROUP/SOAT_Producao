package br.com.ms_entregas.gateway.impl;

import br.com.ms_entregas.gateway.entity.EntregaEntity;
import br.com.ms_entregas.gateway.persistence.IEntregaRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class EntregaRepository implements IEntregaRepository {
    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;
    private DynamoDbTable<EntregaEntity> entregaTable;

    public EntregaRepository(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        this.dynamoDbEnhancedClient = dynamoDbEnhancedClient;
    }

    @PostConstruct
    public void init() {
        entregaTable = dynamoDbEnhancedClient.table("Entrega", TableSchema.fromBean(EntregaEntity.class));
    }


    @Override
    public EntregaEntity save(EntregaEntity entity) {
        entregaTable.putItem(entity);
        return entity;
    }

    @Override
    public EntregaEntity findById(String id) {
        var key = software.amazon.awssdk.enhanced.dynamodb.Key.builder()
                .partitionValue(id)
                .build();
        return entregaTable.getItem(key);
    }
}
