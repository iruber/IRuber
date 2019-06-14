package com.comercial.iruber.restaurante.persistencia;

public class ContratoRestaurante {
    private static final String TEXT_NOT_NULL = " TEXT NOT NULL,";
    public static final String NOME_TABELA = "restaurante";
    public static final String RESTAURANTE_ID = "idRestaurante";
    public static final String RESTAURANTE_NOME = "nome";
    public static final String RESTAURANTE_USER_ID = "idUsuario";
    public static final String RESTAURANTE_CNPJ = "cnpj";
    public static final String RESTAURANTE_ID_ENDERECO = "idEndereco";
    public static final String RESTAURANTE_TELEFONE = "telefone";
    public static final String SQL_CREATE_TABLE_RESTAURANTE =
            "CREATE TABLE " + NOME_TABELA + " (" +
                    RESTAURANTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    RESTAURANTE_USER_ID + " INTEGER NOT NULL, " +
                    RESTAURANTE_NOME + TEXT_NOT_NULL +
                    RESTAURANTE_CNPJ + TEXT_NOT_NULL +
                    RESTAURANTE_ID_ENDERECO + " INTEGER NOT NULL" +
                    RESTAURANTE_TELEFONE + TEXT_NOT_NULL +")";
    public static final String SQL_DELETE_RESTAURANTE =
            "DROP TABLE IF EXISTS " + ContratoRestaurante.NOME_TABELA;

    private ContratoRestaurante(){}
}
