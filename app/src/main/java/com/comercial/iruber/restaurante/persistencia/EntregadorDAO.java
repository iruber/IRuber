package com.comercial.iruber.restaurante.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.comercial.iruber.infra.persistencia.DbHelper;
import com.comercial.iruber.restaurante.dominio.Entregador;
import com.comercial.iruber.restaurante.dominio.Ingrediente;


public class EntregadorDAO {
    private DbHelper bancoDados;


    public EntregadorDAO(Context context) {

        bancoDados = new DbHelper(context);
    }


    public long inserirEntregador(Entregador entregador) {
        SQLiteDatabase db = bancoDados.getWritableDatabase();
        ContentValues values = new ContentValues();

        String nome = entregador.getNome();
        values.put(ContratoEntregador.ENTREGADOR_NOME, nome);

        String cpf = entregador.getCpf();
        values.put(ContratoEntregador.ENTREGADOR_CPF, cpf);

        long idRestaurante = entregador.getIdRestaurante();
        values.put(ContratoEntregador.ENTREGADOR_ID_RESTAURANTE, idRestaurante);

        long numeroDeEntregadas = entregador.getNumeroEntregas();
        values.put(ContratoEntregador.ENTREGADOR_NUMERO_ENTREGAS, numeroDeEntregadas);


        long id = db.insert(ContratoEntregador.NOME_TABELA, null, values);

        return id;


    }


    public Entregador criarEntregador(Cursor cursor) {

        String colunaId = ContratoEntregador.ENTREGADOR_ID;
        int indexColunaId = cursor.getColumnIndex(colunaId);
        long id = cursor.getLong(indexColunaId);

        String colunaNome = ContratoEntregador.ENTREGADOR_NOME;
        int indexColunaNome = cursor.getColumnIndex(colunaNome);
        String nome = cursor.getString(indexColunaNome);

        String colunaCpf = ContratoEntregador.ENTREGADOR_CPF;
        int indexColunaCpf = cursor.getColumnIndex(colunaCpf);
        String cpf = cursor.getString(indexColunaNome);

        String colunaIdRestaurante = ContratoEntregador.ENTREGADOR_ID_RESTAURANTE;
        int indexColunaIdRestaurante = cursor.getColumnIndex(colunaIdRestaurante);
        long idRestaurante = cursor.getLong(indexColunaIdRestaurante);

        String colunaNumeroEntregas = ContratoEntregador.ENTREGADOR_NUMERO_ENTREGAS;
        int indexColunaEntregas = cursor.getColumnIndex(colunaNumeroEntregas);
        long entregas = cursor.getLong(indexColunaEntregas);

        Entregador entregador = new Entregador();

        entregador.setIdEntregador(id);
        entregador.setNome(nome);
        entregador.setCpf(cpf);
        entregador.setIdRestaurante(idRestaurante);
        entregador.setNumeroEntregas(entregas);
        return entregador;
    }


    public Entregador criar(String query, String[] args) {
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


    public Entregador getIngredientePorId(long id) {
        String query = "SELECT * FROM entregador " +
                "WHERE  id = ?";
        String[] args = {String.valueOf(id)};
        return this.criar(query, args);
    }

    public Entregador getEntregadorByIdRestaurante(long id) {
        String query = "SELECT * FROM entregador " +
                "WHERE  idRestaurante = ?";
        String[] args = {String.valueOf(id)};
        return this.criar(query, args);
    }

}


