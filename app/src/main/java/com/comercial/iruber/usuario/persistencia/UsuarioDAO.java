package com.comercial.iruber.usuario.persistencia;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.comercial.iruber.usuario.dominio.Usuario;
import com.comercial.iruber.infra.persistencia.DbHelper;


public class UsuarioDAO {

    private DbHelper bancoDados;
    private SQLiteDatabase db;

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

        long id = db.insert(tabela, null, values);
        db.close();
        return id;

    }



    /*
      FIZ O BACKUP, deve ser apagada para apresentaçao. Depois explico como funciona direitinho. Por hora é inutil para nossa apresentação

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
*/
}