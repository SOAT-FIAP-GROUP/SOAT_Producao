package br.com.ms_entregas.gateway.entity;

import br.com.ms_entregas.entity.FilaPedidosPreparacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamoDbBean
public class FilaPedidosPreparacaoEntity {

    private String codigo;

    private String pedidoCodigo;

    @DynamoDbPartitionKey
    public String getCodigo() {
        return codigo;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = "pedidoCodigo")
    public String getPedidoCodigo() {
        return pedidoCodigo;
    }

    public FilaPedidosPreparacao toModel() {
        return new FilaPedidosPreparacao(Long.parseLong(this.codigo), Long.parseLong(this.pedidoCodigo));
    }
}
