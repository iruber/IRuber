package com.comercial.iruber.usuario.persistencia;
import android.content.ContentValues;
import android.database.Cursor;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import com.comercial.iruber.usuario.dominio.Usuario;
import com.comercial.iruber.infra.persistencia.DbHelper;


public class UsuarioDAO {

    private DbHelper bancoDados;


    String tabela = DbHelper.TABELA_USUARIO;
    String colunaEmail = DbHelper.USUARIO_EMAIL;
    String colunaSenha = DbHelper.USUARIO_SENHA;



    public UsuarioDAO(Context context) {
        bancoDados = new DbHelper(context);

    }

    public long inserirUsuario(Usuario usuario) {

        SQLiteDatabase db = bancoDados.getWritableDatabase();
        ContentValues values = new ContentValues();


        String email = usuario.getEmail();
        values.put(colunaEmail, email);

        String senha = usuario.getSenha();
        values.put(colunaSenha, senha);

        long id = db.insert(tabela, null, values);
        db.close();
        return id;

    }


    public Usuario criarUsuario(Cursor cursor) {
        String colunaId = DbHelper.USUARIO_ID;
        int indexColunaId = cursor.getColumnIndex(colunaId);
        long id = cursor.getLong(indexColunaId);

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
        String query = "SELECT * FROM usuario " +
                "WHERE email = ? AND senha = ?";
        String[] args = {email, senha};
        return this.load(query, args);
    }

    private Usuario load(String query, String[] args) {

        SQLiteDatabase db = bancoDados.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, args);
        Usuario usuario = null;
        if (cursor.moveToNext()) {
            usuario = this.criarUsuario(cursor);
        }
        cursor.close();
        db.close();
        return usuario;
    }

    public Usuario getByID(long id) {
        String query = "SELECT * FROM usuario " +
                "WHERE idUsuario = ?";
        String[] args = {String.valueOf(id)};
        return this.load(query, args);
    }

    public Usuario getByEmail(String email) {
        String query = "SELECT * FROM usuario " +
                "WHERE email = ?";
        String[] args = {email};
        return this.load(query,args);


    }
}
