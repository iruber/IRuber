package Dominio.Pedido;

import java.math.BigDecimal;
import java.util.ArrayList;

import Dominio.Restaurante.Ingrediente;

public class ItemPedido {
    private long idItemPedido;
    private BigDecimal valor;
    private int quantidade;


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
}
