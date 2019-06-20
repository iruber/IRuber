package com.comercial.iruber.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.comercial.iruber.infra.persistencia.DbHelper;
import com.comercial.iruber.usuario.dominio.Endereco;

public class EnderecoDAO {
    private DbHelper bancoDados;

    public EnderecoDAO(Context context) {
        bancoDados = new DbHelper(context);
    }

    public long inserirEndereco(Endereco endereco) {
        SQLiteDatabase bancoEscreve = bancoDados.getWritableDatabase();
        ContentValues values = new ContentValues();
        String numero = endereco.getNumero();
        String cidade = endereco.getCidade();
        String cep = endereco.getCep();
        String bairro = endereco.getBairro();
        String estado = endereco.getEstado();
        String rua = endereco.getRua();

        values.put(ContratoEndereco.ENDERECO_BAIRRO, bairro);
        values.put(ContratoEndereco.ENDERECO_CEP, cep);
        values.put(ContratoEndereco.ENDERECO_CIDADE, cidade);
        values.put(ContratoEndereco.ENDERECO_ESTADO, estado);
        values.put(ContratoEndereco.ENDERECO_NUMERO, numero);
        values.put(ContratoEndereco.ENDERECO_RUA, rua);
        long id = bancoEscreve.insert(ContratoEndereco.NOME_TABELA, null, values);
        bancoEscreve.close();
        return id;
    }

    public Endereco criarEndereco(Cursor cursor) {
        String colunaId = ContratoEndereco.ID_ENDERECO;
        int indexColunaId = cursor.getColumnIndex(colunaId);
        long id = cursor.getLong(indexColunaId);
        String colunaBairro= ContratoEndereco.ENDERECO_BAIRRO;
        int indexColunaBairro=cursor.getColumnIndex(colunaBairro);
        String bairro =cursor.getString(indexColunaBairro);
        String colunaCep=ContratoEndereco.ENDERECO_CEP;
        int colunaCepIndex=cursor.getColumnIndex(colunaCep);
        String cep=cursor.getString(colunaCepIndex);
        String cidadeColuna= ContratoEndereco.ENDERECO_CIDADE;
        int colunaIndexCidade=cursor.getColumnIndex(cidadeColuna);
        String cidade= cursor.getString(colunaIndexCidade);
        String colunaEstado=ContratoEndereco.ENDERECO_ESTADO;
        int colunaEstadoIndex=cursor.getColumnIndex(colunaEstado);
        String estado = cursor.getString(colunaEstadoIndex);
        String colunaNumero=ContratoEndereco.ENDERECO_NUMERO;
        int colunaNumeroIndex=cursor.getColumnIndex(colunaNumero);
        String numero = cursor.getString(colunaNumeroIndex);
        String ruaColuna=ContratoEndereco.ENDERECO_RUA;
        int colunaIndexRua=cursor.getColumnIndex(ruaColuna);
        String rua = cursor.getString(colunaIndexRua);


        Endereco endereco = new Endereco();
        endereco.setRua(rua);
        endereco.setEstado(estado);
        endereco.setCep(cep);
        endereco.setNumero(numero);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setIdEndereco(id);
        return endereco;
    }

    private Endereco load(String query, String[] args) {
        SQLiteDatabase leitorBanco = bancoDados.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        Endereco endereco = null;
        if (cursor.moveToNext()) {
            endereco = criarEndereco(cursor);
        }
        cursor.close();
        leitorBanco.close();
        return endereco;
    }

    public Endereco getEnderecoById(long id) {
        String query = "SELECT * FROM endereco " +
                "WHERE idEndereco = ?";
        String[] args = {String.valueOf(id)};
        return this.load(query, args);
    }

    public void updateEndereco(Endereco endereco) {
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        String query = "idEndereco = ?";
        ContentValues values = new ContentValues();
        values.put(ContratoEndereco.ENDERECO_BAIRRO, endereco.getBairro());
        values.put(ContratoEndereco.ENDERECO_CEP, endereco.getCep());
        values.put(ContratoEndereco.ENDERECO_CIDADE, endereco.getCidade());
        values.put(ContratoEndereco.ENDERECO_ESTADO, endereco.getEstado());
        values.put(ContratoEndereco.ENDERECO_NUMERO, endereco.getNumero());
        values.put(ContratoEndereco.ENDERECO_RUA, endereco.getRua());
        String[] args = {String.valueOf(endereco.getIdEndereco())};
        escritorBanco.update(ContratoEndereco.NOME_TABELA, values, query, args);
        escritorBanco.close();
    }
}
