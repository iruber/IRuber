package com.comercial.iruber.restaurante.persistencia;

public class ContratoPrato {
    private static final String TEXT_NOT_NULL = " TEXT NOT NULL,";
    public static final String NOME_TABELA = "prato";
    public static final String PRATO_ID = "id";
    public static final String PRATO_ID_ITEM_PEDIDO = "idItemPedido";
    public static final String PRATO_RESTAURANTE_ID = "idRestaurante";
    public static final String PRATO_NOME = "nome";
    public static final String PRATO_DESCRICAO = "pratoDescricao";
    public static final String PRATO_DISPONIVEL = "pratoDisponivel";
    public static final String PRATO_VALOR = "valor";
    public static final String SQL_CREATE_TABLE_PRATO =
            "CREATE TABLE " + NOME_TABELA + "(" +
                    PRATO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PRATO_NOME + TEXT_NOT_NULL +
                    PRATO_DESCRICAO + TEXT_NOT_NULL +
                    PRATO_DISPONIVEL + TEXT_NOT_NULL +
                    PRATO_ID_ITEM_PEDIDO +"INTEGER, "+
                    PRATO_VALOR + TEXT_NOT_NULL +
                    PRATO_RESTAURANTE_ID + " TEXT NOT NULL" + ")";
    public static final String SQL_DELETE_PRATO =
            "DROP TABLE IF EXISTS " + ContratoPrato.NOME_TABELA;

    private ContratoPrato(){}

}
