package com.comercial.iruber.usuario.persistencia;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.comercial.iruber.usuario.dominio.Usuario;
import com.comercial.iruber.infra.persistencia.DbHelper;


public class UsuarioDAO {

    private DbHelper bancoDados;


    public UsuarioDAO() {
        bancoDados = new DbHelper();

    }


    private Usuario criarUsuario(Cursor cursor){

        

    Usuario usuario = new Usuario();

    return usuario;
    }
}
