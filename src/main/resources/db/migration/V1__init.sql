CREATE TABLE entregas (
    codigo BIGINT NOT NULL AUTO_INCREMENT,
    pedidocodigo BIGINT NULL,
    datahoraentrega DATETIME NULL,
    PRIMARY KEY (codigo)
);

CREATE TABLE filapedidospreparacao (
    codigo BIGINT NOT NULL AUTO_INCREMENT,
    pedidocodigo BIGINT NULL,
    PRIMARY KEY (codigo)
);

ALTER TABLE entregas
    ADD CONSTRAINT uc_entregas_pedidocodigo UNIQUE (pedidocodigo);

ALTER TABLE filapedidospreparacao
    ADD CONSTRAINT uc_filapedidospreparacao_pedidocodigo UNIQUE (pedidocodigo);
