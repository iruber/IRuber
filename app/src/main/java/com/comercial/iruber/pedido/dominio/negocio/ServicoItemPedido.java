package com.comercial.iruber.pedido.dominio.negocio;

import android.content.Context;

import com.comercial.iruber.pedido.dominio.ItemPedido;
import com.comercial.iruber.pedido.dominio.persistencia.ItemPedidoDAO;
import com.comercial.iruber.pedido.dominio.persistencia.PedidoDAO;

public class ServicoItemPedido {

    private ItemPedidoDAO itemPedidoDAO;

    public ServicoItemPedido(Context context){
        this.itemPedidoDAO = new ItemPedidoDAO(context);
    }



    public ItemPedido getItemPedido(long id){
        return itemPedidoDAO.getItemPedidoPorIdPedido(id);
    }
}
