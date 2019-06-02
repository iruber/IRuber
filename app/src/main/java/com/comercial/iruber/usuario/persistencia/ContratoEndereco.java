package com.comercial.iruber.usuario.persistencia;

public class ContratoEndereco {
    public static final String NOME_TABELA = "endereco";
    public static final String ID_ENDERECO = "idEndereco";
    public static final String ENDERECO_NUMERO = "numero";
    public static final String ENDERECO_CEP = "cep";
    public static final String ENDERECO_ESTADO = "estado";
    public static final String ENDERECO_BAIRRO = "bairro";
    public static final String ENDERECO_CIDADE = "cidade";
    public static final String ENDERECO_RUA = "rua";
    public static final String SQL_CREATE_TABLE_ENDERECO =
            "CREATE TABLE " + NOME_TABELA + " (" +
                    ID_ENDERECO + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    ENDERECO_RUA + " TEXT NOT NULL, " +
                    ENDERECO_NUMERO + " TEXT NOT NULL," +
                    ENDERECO_CEP + " TEXT NOT NULL," +
                    ENDERECO_BAIRRO + " TEXT NOT NULL," +
                    ENDERECO_CIDADE + " TEXT NOT NULL," +
                    ENDERECO_ESTADO + " TEXT NOT NULL); ";
    public static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + ContratoEndereco.NOME_TABELA;
}
