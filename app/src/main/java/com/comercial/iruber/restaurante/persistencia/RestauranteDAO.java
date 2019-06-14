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
    public static final String SELECT_FROM_RESTAURANTE = "SELECT * FROM restaurante ";
    private DbHelper bancoDados;
    private UsuarioDAO usuarioDAO;
    private EnderecoDAO enderecoDAO;

    public RestauranteDAO(Context context) {
        bancoDados = new DbHelper(context);
        usuarioDAO = new UsuarioDAO(context);
        enderecoDAO = new EnderecoDAO(context);
    }

    public long inserirRestaurante(Restaurante restaurante) {
        SQLiteDatabase bancoEscreve = bancoDados.getWritableDatabase();
        ContentValues values = new ContentValues();
        long idUser = this.usuarioDAO.inserirUsuario(restaurante.getUsuario());
        long idEndereco = this.enderecoDAO.inserirEndereco(restaurante.getEndereco());
        String telefone = restaurante.getTelefone();
        String nome = restaurante.getNome();
        String cnpj = restaurante.getCnpj();
        values.put(ContratoRestaurante.RESTAURANTE_CNPJ, cnpj);
        values.put(ContratoRestaurante.RESTAURANTE_ID_ENDERECO, idEndereco);
        values.put(ContratoRestaurante.RESTAURANTE_NOME, nome);
        values.put(ContratoRestaurante.RESTAURANTE_USER_ID, idUser);
        values.put(ContratoRestaurante.RESTAURANTE_TELEFONE, telefone);
        long id = bancoEscreve.insert(ContratoRestaurante.NOME_TABELA, null, values);
        bancoEscreve.close();
        return id;
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

    public Restaurante getRestauranteById(long idRestaurante) {
        String query = SELECT_FROM_RESTAURANTE +
                "WHERE idRestaurante = ?";
        String[] args = {String.valueOf(idRestaurante)};
        return this.criar(query, args);
    }

    public Restaurante getRestaurantePorNome(String nome) {
        String query = SELECT_FROM_RESTAURANTE +
                "WHERE nome = ?";
        String[] args = {nome};
        return this.criar(query, args);
    }
    public Restaurante getRestauranteByIdUsuario(long idUsuario) {
        String query = SELECT_FROM_RESTAURANTE +
                "WHERE idUsuario = ?";
        String[] args = {String.valueOf(idUsuario)};
        return this.criar(query, args);
    }

    public void updateRestaurante(Restaurante restaurante) {
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        String query = "idRestaurante = ?";
        ContentValues values = new ContentValues();
        values.put(ContratoRestaurante.RESTAURANTE_NOME, restaurante.getNome());
        values.put(ContratoRestaurante.RESTAURANTE_CNPJ, restaurante.getCnpj());
        String[] args = {String.valueOf(restaurante.getIdRestaurante())};
        escritorBanco.update(ContratoRestaurante.NOME_TABELA, values, query, args);
        escritorBanco.close();
    }
}
