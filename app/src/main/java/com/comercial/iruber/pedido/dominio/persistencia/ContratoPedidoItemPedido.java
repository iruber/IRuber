package com.comercial.iruber.pedido.dominio.persistencia;

import android.content.Context;

public class ContratoPedidoItemPedido {
    private static final String TEXT_NOT_NULL = " TEXT NOT NULL,";
    public static final String NOME_TABELA = "pedidoItemPedido";
    public static final String PEDIDO_ITEM_PEDIDO_ID="id";
    public static final String PEDIDO_ITEM_PEDIDO_ID_ITEM_PEDIDO="idItemPedido";
    public static final String PEDIDO_ITEM_PEDIDO_ID_PEDIDO="idPedido";
    public static final String SQL_CREATE_TABLE_PEDIDO_ITEM_PEDIDO =
            "CREATE TABLE " + NOME_TABELA + "(" +
                    PEDIDO_ITEM_PEDIDO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PEDIDO_ITEM_PEDIDO_ID_ITEM_PEDIDO + " TEXT NOT NULL, " +
                    PEDIDO_ITEM_PEDIDO_ID_PEDIDO + " TEXT NOT NULL);";
    public static final String SQL_DELETE_PEDIDO_ITEM_PEDIDO =
            "DROP TABLE IF EXISTS " + ContratoPedidoItemPedido.NOME_TABELA;

    private ContratoPedidoItemPedido(){}


}
