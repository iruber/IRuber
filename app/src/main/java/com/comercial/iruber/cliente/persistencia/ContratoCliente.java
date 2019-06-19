package com.comercial.iruber.cliente.persistencia;

public class ContratoCliente {
    private static final String TEXT_NOT_NULL = " TEXT NOT NULL,";
    public static final String NOME_TABELA = "cliente";
    public static final String CLIENTE_ID = "id";
    public static final String PESSOA_NOME = "nome";
    public static final String PESSOA_NASCIMENTO = "nascimento";
    public static final String PESSOA_CPF = "cpf";
    public static final String PESSOA_ENDERECO_ID = "idEndereco";
    public static final String PESSOA_USER_ID = "idUser";
    public static final String PESSOA_TELEFONE="telefone";
    public static final String PESSOA_IMG="imagem";
    public static final String SQL_CREATE_TABLE_CLIENTE =
            "CREATE TABLE " + NOME_TABELA + " (" +
                    CLIENTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    PESSOA_NOME + TEXT_NOT_NULL +
                    PESSOA_NASCIMENTO + TEXT_NOT_NULL +
                    PESSOA_CPF + TEXT_NOT_NULL +
                    PESSOA_IMG + "blob, " +
                    PESSOA_USER_ID + TEXT_NOT_NULL +
                    PESSOA_TELEFONE + TEXT_NOT_NULL +
                    PESSOA_ENDERECO_ID + " INTEGER NOT NULL);";
    public static final String SQL_DELETE_ADDRESS =
            "DROP TABLE IF EXISTS " + ContratoCliente.NOME_TABELA;
    private ContratoCliente() {}
}
