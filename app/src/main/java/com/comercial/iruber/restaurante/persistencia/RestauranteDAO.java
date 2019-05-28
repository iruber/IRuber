package com.comercial.iruber.restaurante.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.comercial.iruber.infra.persistencia.DbHelper;
import com.comercial.iruber.restaurante.dominio.Restaurante;
import com.comercial.iruber.usuario.persistencia.EnderecoDAO;
import com.comercial.iruber.usuario.persistencia.UsuarioDAO;

public class RestauranteDAO {
    private DbHelper bancoDados;
    private UsuarioDAO usuarioDAO;
    private EnderecoDAO enderecoDAO;
    private String tabela = DbHelper.TABELA_RESTAURANTE;

    public RestauranteDAO(Context context) {
        bancoDados = new DbHelper(context);
        usuarioDAO=  new UsuarioDAO(context);
        enderecoDAO =new EnderecoDAO(context);
    }
    public void inserirRestaurante(Restaurante restaurante){
        SQLiteDatabase bancoEscreve = bancoDados.getWritableDatabase();
        ContentValues values = new ContentValues();
        long idUser= this.usuarioDAO.inserirUsuario(restaurante.getUsuario());
        long idEndereco=this.enderecoDAO.inserirEndereco(restaurante.getEndereco());
        String nome = restaurante.getNome();
        String cnpj =  restaurante.getCNPJ();
        values.put(DbHelper.RESTAURANTE_USER_ID,idUser);
        values.put(DbHelper.RESTAURANTE_CNPJ,cnpj);
        values.put(DbHelper.RESTAURANTE_ID_ENDERECO,idEndereco);
        values.put(DbHelper.RESTAURANTE_NOME,nome);
        bancoEscreve.insert(tabela, null, values);
        bancoEscreve.close();
    }
    public Restaurante criarRestaurante(Cursor cursor){
        String colunaId = DbHelper.RESTAURANTE_ID;
        int indexColunaId= cursor.getColumnIndex(colunaId);
        long id = cursor.getLong(indexColunaId);
        Restaurante restaurante = new Restaurante();
        restaurante.setIdRestaurante(id);
        return restaurante;
    }
    private Restaurante criar(String query, String[] args) {
        SQLiteDatabase leitorBanco = bancoDados.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        Restaurante restaurante = null;
        if (cursor.moveToNext()) {
            restaurante = criarRestaurante(cursor);
        }
        cursor.close();
        leitorBanco.close();
        return restaurante;
    }
}
