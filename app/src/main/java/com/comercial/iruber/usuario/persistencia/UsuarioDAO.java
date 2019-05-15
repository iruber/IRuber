package com.comercial.iruber.usuario.persistencia;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.comercial.iruber.usuario.dominio.Usuario;
import com.comercial.iruber.infra.persistencia.DbHelper;


public class UsuarioDAO {

    private DbHelper bancoDados;



    String tabela = DbHelper.TABELA_USUARIO;
    String colunaEmail = DbHelper.USUARIO_EMAIL;
    String colunaSenha = DbHelper.USUARIO_SENHA;
    String colunaTipo = DbHelper.USUARIO_TIPO;


    public UsuarioDAO() {
        bancoDados = new DbHelper();

    }

    public long inserirUsuario(Usuario usuario) {

        SQLiteDatabase bancoEscreve = bancoDados.getWritableDatabase();
        ContentValues values = new ContentValues();


        String email = usuario.getEmail();
        values.put(colunaEmail, email);

        String senha = usuario.getSenha();
        values.put(colunaSenha, senha);

        long id =bancoEscreve.insert(tabela, null, values);
        bancoEscreve.close();
        return id;

    }




    public Usuario criarUsuario(Cursor cursor){
        String colunaId = DbHelper.USUARIO_ID;
        int indexColunaId= cursor.getColumnIndex(colunaId);
        long id = cursor.getInt(indexColunaId);

        String colunaEmail = DbHelper.USUARIO_EMAIL;
        int indexColunaEmail = cursor.getColumnIndex(colunaEmail);
        String email = cursor.getString(indexColunaEmail);

        String colunaSenha = DbHelper.USUARIO_SENHA;
        int indexColunaSenha = cursor.getColumnIndex(colunaSenha);
        String senha = cursor.getString(indexColunaSenha);

        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        return usuario;
    }




    public Usuario getByEmailSenha(String email, String senha) {
        String query =  "SELECT * FROM usuario " +
                "WHERE email = ? AND senha = ?";
        String[] args = {email, senha};
        return this.load(query, args);
    }
    private Usuario load(String query, String[] args) {
        SQLiteDatabase leitorBanco = bancoDados.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        Usuario usuario = null;
        if (cursor.moveToNext()) {
            usuario = criarUsuario(cursor);
        }
        cursor.close();
        leitorBanco.close();
        return usuario;
    }
    public Usuario getByID(String id) {
        String query =  "SELECT * FROM usuario " +
                "WHERE id = ?";
        String[] args = {id};
        return this.load(query, args);
    }


}