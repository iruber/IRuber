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
        String tipoUsuario = endereco.getTipoUsuario().getDescricao();
        String idProprietario = String.valueOf(endereco.getIdProprietario());
        values.put(ContratoEndereco.ENDERECO_BAIRRO, bairro);
        values.put(ContratoEndereco.ENDERECO_CEP, cep);
        values.put(ContratoEndereco.ENDERECO_CIDADE, cidade);
        values.put(ContratoEndereco.ENDERECO_ESTADO, estado);
        values.put(ContratoEndereco.ENDERECO_NUMERO, numero);
        values.put(ContratoEndereco.ENDERECO_RUA, rua);
        values.put(ContratoEndereco.ENDERECO_TIPO_USUARIO, tipoUsuario);
        values.put(ContratoEndereco.ENDEREÇO_PROPRIETARIO, idProprietario);
        long id = bancoEscreve.insert(ContratoEndereco.NOME_TABELA, null, values);
        bancoEscreve.close();
        return id;
    }

    public Endereco criarEndereco(Cursor cursor) {
        String colunaId = ContratoEndereco.ID_ENDERECO;
        int indexColunaId = cursor.getColumnIndex(colunaId);
        long id = cursor.getLong(indexColunaId);
        Endereco endereco = new Endereco();
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
