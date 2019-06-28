package com.comercial.iruber.restaurante.dominio;

import java.math.BigDecimal;

public class Nota {
    private long id;
    private BigDecimal valor;
    private long idCliente;
    private long idRestaurante;

    public BigDecimal getValor() {
        return valor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public long getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(long idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public void setValor(BigDecimal nota) {
        this.valor = nota;
    }
}

