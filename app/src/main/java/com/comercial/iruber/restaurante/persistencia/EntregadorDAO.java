package com.comercial.iruber.restaurante.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.comercial.iruber.infra.persistencia.Contrato;
import com.comercial.iruber.infra.persistencia.DbHelper;
import com.comercial.iruber.restaurante.dominio.Entregador;
import com.comercial.iruber.usuario.persistencia.UsuarioDAO;

import java.util.ArrayList;
import java.util.List;


public class EntregadorDAO {

    private UsuarioDAO usuarioDAO;
    private DbHelper bancoDados;
    private static final String SELECT_FROM_ENTREGADOR = "SELECT * FROM entregador ";

    public EntregadorDAO(Context context) {

        bancoDados = new DbHelper(context);
        usuarioDAO = new UsuarioDAO(context);
    }

    public long inserirEntregador(Entregador entregador) {
        SQLiteDatabase db = bancoDados.getWritableDatabase();
        ContentValues values = new ContentValues();
        String nome = entregador.getNome();
        values.put(ContratoEntregador.ENTREGADOR_NOME, nome);
        long idUsuario = usuarioDAO.inserirUsuario(entregador.getUsuario());
        values.put(ContratoEntregador.ENTREGADOR_ID_USUARIO, idUsuario);
        String telefone = entregador.getTelefone();
        values.put(ContratoEntregador.ENTREGADOR_TELEFONE, telefone);
        long idRestaurante = entregador.getIdRestaurante();
        values.put(ContratoEntregador.ENTREGADOR_ID_RESTAURANTE, idRestaurante);
        String estado = entregador.getEstado().getDescricao();
        values.put(ContratoEntregador.ENTREGADOR_ESTADO, estado);
        long id = db.insert(ContratoEntregador.NOME_TABELA, null, values);
        db.close();
        return id;
    }

    private Entregador criarEntregador(Cursor cursor) {
        String colunaId = ContratoEntregador.ENTREGADOR_ID;
        int indexColunaId = cursor.getColumnIndex(colunaId);
        long id = cursor.getLong(indexColunaId);
        String colunaNome = ContratoEntregador.ENTREGADOR_NOME;
        int indexColunaNome = cursor.getColumnIndex(colunaNome);
        String nome = cursor.getString(indexColunaNome);
        String colunaTelefone = ContratoEntregador.ENTREGADOR_TELEFONE;
        int indexColunaTelefone = cursor.getColumnIndex(colunaTelefone);
        String telefone = cursor.getString(indexColunaTelefone);
        String colunaIdRestaurante = ContratoEntregador.ENTREGADOR_ID_RESTAURANTE;
        int indexColunaIdRestaurante = cursor.getColumnIndex(colunaIdRestaurante);
        long idRestaurante = cursor.getLong(indexColunaIdRestaurante);
        Entregador entregador = new Entregador();
        entregador.setIdEntregador(id);
        entregador.setNome(nome);
        entregador.setTelefone(telefone);
        entregador.setIdRestaurante(idRestaurante);
        return entregador;
    }

    private Entregador criar(String query, String[] args) {
        SQLiteDatabase leitorBanco = bancoDados.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        Entregador entregador = null;
        if (cursor.moveToNext()) {
            entregador = criarEntregador(cursor);
        }
        cursor.close();
        leitorBanco.close();
        return entregador;
    }

    public Entregador getEntregadorPorId(long id) {
        String query = "SELECT * FROM entregador " +
                "WHERE  id = ?";
        String[] args = {String.valueOf(id)};
        return this.criar(query, args);
    }

    public Entregador getEntregadorPorNome(String nome, long id) {
        String query = "SELECT * FROM entregador " +
                "WHERE idRestaurante = ? " +
                "AND nome = ?";
        String[] args = {String.valueOf(id), nome};
        return this.criar(query, args);
    }

    public void updateEntregador(Entregador entregador) {
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        String query = "id = ?";
        ContentValues values = new ContentValues();
        values.put(ContratoEntregador.ENTREGADOR_NOME, entregador.getNome());
        values.put(ContratoEntregador.ENTREGADOR_TELEFONE, entregador.getTelefone());
        values.put(ContratoEntregador.ENTREGADOR_ESTADO, entregador.getEstado().getDescricao());
        String[] args = {String.valueOf(entregador.getIdEntregador())};
        escritorBanco.update(ContratoEntregador.NOME_TABELA, values, query, args);
        escritorBanco.close();
    }

    public ArrayList<Entregador> getEntregadoresPorIdRestaurante(long idRestaurante){
        String query = SELECT_FROM_ENTREGADOR
                + "WHERE idRestaurante = ?";
        String[] args = {String.valueOf(idRestaurante)};
        return this.criarListaEntregador(query,args);
    }

    private ArrayList<Entregador> criarListaEntregador(String query, String[] args){
        SQLiteDatabase leitorBanco = bancoDados.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query,args);
        ArrayList<Entregador> listaEntregadores = new ArrayList<>();
        Entregador entregador;
        if (cursor.moveToFirst()){
            do {
                entregador = criarEntregador(cursor);
                listaEntregadores.add(entregador);
            } while(cursor.moveToNext());
        }
        cursor.close();
        leitorBanco.close();
        return listaEntregadores;
    }
}



