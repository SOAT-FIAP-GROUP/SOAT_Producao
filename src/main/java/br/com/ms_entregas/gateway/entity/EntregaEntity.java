package br.com.ms_entregas.gateway.entity;

import br.com.ms_entregas.entity.Entrega;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamoDbBean
public class EntregaEntity {

    private String codigo;

    private String pedidoCodigo;

    private String dataHoraEntrega;

    @DynamoDbPartitionKey
    public String getCodigo() {
        return codigo;
    }


    public Entrega toModel() {
        return new Entrega(Long.parseLong(this.getCodigo()), Long.parseLong(this.getPedidoCodigo()), null, LocalDateTime.parse(this.getDataHoraEntrega()));
    }

}