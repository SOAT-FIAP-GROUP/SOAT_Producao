package br.com.ms_entregas.gateway.entity;

import br.com.ms_entregas.entity.FilaPedidosPreparacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "filapedidospreparacao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "filapedidospreparacao")
public class FilaPedidosPreparacaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private Long pedidocodigo;

    public FilaPedidosPreparacao toModel() {
        return new FilaPedidosPreparacao(this.codigo, this.pedidocodigo);
    }
}
