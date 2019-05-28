package com.comercial.iruber.usuario.persistencia;

public class ContratoUsuario {
    public static final String NOME_TABELA = "usuarios";
    public static final String USUARIO_ID = "usuarioId";
    public static final String USUARIO_EMAIL = "email";
    public static final String USUARIO_SENHA = "senha";
    public static final String USUARIO_TIPO = "tipo";

    public static final String SQL_CREATE_TABLE_USUARIO = "CREATE TABLE "+NOME_TABELA+"(" +
            USUARIO_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
            USUARIO_SENHA+" TEXT NOT NULL, " +
            USUARIO_EMAIL+" TEXT NOT NULL," +
            USUARIO_TIPO+" TEXT ); ";

    public static final String SQL_DELETE_USUARIOS = "DROP TABLE IF EXISTS " + ContratoUsuario.NOME_TABELA;
}
