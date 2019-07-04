package com.comercial.iruber.cliente.persistencia;

public class ContratoClienteCartao {
    private static final String TEXT_NOT_NULL = " TEXT NOT NULL,";
    public static final String NOME_TABELA = "clienteCartao";
    public static final String CLIENTE_CARTAO_ID="id";
    public static final String CLIENTE_CARTAO_CLIENTE_ID="idCliente";
    public static final String CLIENTE_CARTAO_ID_CARTAO="idCartao";

    public static final String SQL_CREATE_TABLE_CLIENTE_CARTAO =
            "CREATE TABLE " + NOME_TABELA + " (" +
                    CLIENTE_CARTAO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CLIENTE_CARTAO_CLIENTE_ID + TEXT_NOT_NULL +
                    CLIENTE_CARTAO_ID_CARTAO + " INTEGER NOT NULL" + ")";
    public static final String SQL_DELETE_CLIENTE_CARTAO =
            "DROP TABLE IF EXISTS " + ContratoClienteCartao.NOME_TABELA;
    private ContratoClienteCartao() {}

}
