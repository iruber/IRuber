package com.comercial.iruber.cliente.persistencia;

public class ContratoCartao {

    private static final String TEXT_NOT_NULL = " TEXT NOT NULL,";
    public static final String NOME_TABELA = "cartao";
    public static final String CARTAO_ID="id";
    public static final String CARTAO_NOME="nome";
    public static final String CARTAO_ID_CLIENTE="idCliente";
    public static final String CARTAO_NUMERO="numero";
    public static final String CARTAO_CODIGO="codigo";
    public static final String CARTAO_DATA_VENCIMENTO="dataVencimento";
    public static final String SQL_CREATE_TABLE_CARTAO =
            "CREATE TABLE " + NOME_TABELA + " (" +
                    CARTAO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    CARTAO_NOME + TEXT_NOT_NULL +
                    CARTAO_ID_CLIENTE + TEXT_NOT_NULL+
                    CARTAO_CODIGO + TEXT_NOT_NULL +
                    CARTAO_NUMERO + TEXT_NOT_NULL +
                    CARTAO_DATA_VENCIMENTO + " TEXT NOT NULL" + ")";


    public static final String SQL_DELETE_CARTAO =
            "DROP TABLE IF EXISTS " + ContratoCartao.NOME_TABELA;
    private ContratoCartao() {}
}
