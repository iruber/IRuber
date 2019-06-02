package com.comercial.iruber.restaurante.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.comercial.iruber.infra.persistencia.DbHelper;
import com.comercial.iruber.restaurante.dominio.Restaurante;
import com.comercial.iruber.usuario.persistencia.EnderecoDAO;
import com.comercial.iruber.usuario.persistencia.UsuarioDAO;

public class RestauranteDAO {
    private DbHelper bancoDados;
    private UsuarioDAO usuarioDAO;
    private EnderecoDAO enderecoDAO;

    public RestauranteDAO(Context context) {
        bancoDados = new DbHelper(context);
        usuarioDAO = new UsuarioDAO(context);
        enderecoDAO = new EnderecoDAO(context);
    }

    public void inserirRestaurante(Restaurante restaurante) {
        SQLiteDatabase bancoEscreve = bancoDados.getWritableDatabase();
        ContentValues values = new ContentValues();
        long idUser = this.usuarioDAO.inserirUsuario(restaurante.getUsuario());
        long idEndereco = this.enderecoDAO.inserirEndereco(restaurante.getEndereco());
        String nome = restaurante.getNome();
        String cnpj = restaurante.getCNPJ();
        values.put(ContratoRestaurante.RESTAURANTE_ID, idUser);
        values.put(ContratoRestaurante.RESTAURANTE_CNPJ, cnpj);
        values.put(ContratoRestaurante.RESTAURANTE_ID_ENDERECO, idEndereco);
        values.put(ContratoRestaurante.RESTAURANTE_NOME, nome);
        bancoEscreve.insert(ContratoRestaurante.NOME_TABELA, null, values);
        bancoEscreve.close();
    }

    public Restaurante criarRestaurante(Cursor cursor) {
        String colunaId = ContratoRestaurante.RESTAURANTE_ID;
        int indexColunaId = cursor.getColumnIndex(colunaId);
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

    public Restaurante getRestauranteById(long id) {
        String query = "SELECT * FROM restaurante " +
                "WHERE idRestaurante = ?";
        String[] args = {String.valueOf(id)};
        return this.criar(query, args);
    }
}
