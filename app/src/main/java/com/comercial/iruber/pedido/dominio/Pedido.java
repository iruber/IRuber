package com.comercial.iruber.pedido.dominio;

import android.os.Parcel;
import android.os.Parcelable;

import com.comercial.iruber.cliente.dominio.Cliente;
import com.comercial.iruber.restaurante.dominio.Entregador;
import com.comercial.iruber.restaurante.dominio.Restaurante;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class Pedido{
    private long idPedido;
    private Cliente cliente;
    private long Identregador;
    private long Idrestaurante;
    private List<ItemPedido> itemPedidos;
    private Date data;
    private BigDecimal valorTotal;
    private StatusPedido statusPedido;

    public long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(long idPedido) {
        this.idPedido = idPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public long getIdentregador() {
        return Identregador;
    }

    public void setIdentregador(long identregador) {
        Identregador = identregador;
    }

    public long getIdrestaurante() {
        return Idrestaurante;
    }

    public void setIdrestaurante(long idrestaurante) {
        Idrestaurante = idrestaurante;
    }

    public List<ItemPedido> getItemPedidos() {
        return itemPedidos;
    }

    public void setItemPedidos(List<ItemPedido> itemPedidos) {
        this.itemPedidos = itemPedidos;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }
}
