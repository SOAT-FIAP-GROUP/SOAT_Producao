package br.com.ms_entregas.gateway.entity;

import br.com.ms_entregas.entity.Entrega;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "entregas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "codigo")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EntregaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(name = "pedidocodigo")
    private Long pedidoCodigo;

    @Column(name = "datahoraentrega")
    private LocalDateTime dataHoraEntrega;

    public Entrega toModel() {
        return new Entrega(this.getCodigo(), this.getPedidoCodigo(), null, this.getDataHoraEntrega());
    }

}