package com.comercial.iruber.pedido.dominio.persistencia;

public class ContratoItemPedido {


    public static final String NOME_TABELA = "itemPedido";
    public static final String ITEM_PEDIDO_ID = "id";
    public static final String ITEM_PEDIDO_VALOR = "valor";
    public static final String ITEM_PEDIDO_QUANTIDADE = "quantidade";
    public static final String ITEM_PEDIDO_ID_PEDIDO="idPedido";
    public static final String ITEM_PEDIDO_ID_PRATO="idPrato";


    public static final String SQL_CREATE_TABLE_ITEM_PEDIDO =
            "CREATE TABLE " + NOME_TABELA + "(" +
                    ITEM_PEDIDO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ITEM_PEDIDO_QUANTIDADE + " TEXT NOT NULL, " +
                    ITEM_PEDIDO_VALOR + " TEXT NOT NULL, " +
                    ITEM_PEDIDO_ID_PRATO+" TEXT, " +
                    ITEM_PEDIDO_ID_PEDIDO + " TEXT NOT NULL);";
    public static final String SQL_DELETE_ITEM_PEDIDO =
            "DROP TABLE IF EXISTS " + ContratoItemPedido.NOME_TABELA;



}
