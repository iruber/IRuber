package com.comercial.iruber.pedido.dominio;

import com.comercial.iruber.restaurante.dominio.Prato;

import java.math.BigDecimal;

public class ItemPedido {
    private long idItemPedido;
    private BigDecimal valor;
    private int quantidade;
    private long IdPedido;


    public long getIdItemPedido() {
        return idItemPedido;
    }

    public void setIdItemPedido(long idItemPedido) {
        this.idItemPedido = idItemPedido;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public long getIdPedido() {
        return IdPedido;
    }

    public void setIdPedido(long idPedido) {
        IdPedido = idPedido;
    }

}