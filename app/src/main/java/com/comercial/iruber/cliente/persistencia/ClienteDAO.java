package com.comercial.iruber.cliente.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.comercial.iruber.cliente.dominio.Cliente;
import com.comercial.iruber.infra.EnumTipo;
import com.comercial.iruber.infra.persistencia.DbHelper;
import com.comercial.iruber.usuario.persistencia.EnderecoDAO;
import com.comercial.iruber.usuario.persistencia.UsuarioDAO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClienteDAO {

    private DbHelper bancoDados;
    private UsuarioDAO usuarioDAO;
    private EnderecoDAO enderecoDAO;

    public ClienteDAO(Context context) {
        bancoDados = new DbHelper(context);
        usuarioDAO = new UsuarioDAO(context);
        enderecoDAO = new EnderecoDAO(context);
    }

    public long inserirCliente(Cliente cliente) {
        SQLiteDatabase bancoEscreve = bancoDados.getWritableDatabase();
        ContentValues values = new ContentValues();
        cliente.getUsuario().setTipo(EnumTipo.CLIENTE);
        cliente.getUsuario().setTipo(EnumTipo.RESTAURANTE);
        long idUser = this.usuarioDAO.inserirUsuario(cliente.getUsuario());
        long idEndereco = this.enderecoDAO.inserirEndereco(cliente.getEndereco());
        String nome = cliente.getNome();
        Date nascimento = cliente.getNascimento();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String snascimento = dateFormat.format(nascimento);
        String cpf = cliente.getCpf();
        cliente.getUsuario().setId(idUser);
        values.put(ContratoCliente.CLIENTE_ID, idUser);
        values.put(ContratoCliente.PESSOA_CPF, cpf);
        values.put(ContratoCliente.PESSOA_NASCIMENTO, snascimento);
        values.put(ContratoCliente.PESSOA_ENDERECO_ID, idEndereco);
        values.put(ContratoCliente.PESSOA_NOME, nome);
        long id = bancoEscreve.insert(ContratoCliente.NOME_TABELA, null, values);
        bancoEscreve.close();
        return id;
    }

    public Cliente criarCliente(Cursor cursor) {
        String colunaId = ContratoCliente.CLIENTE_ID;
        int indexColunaId = cursor.getColumnIndex(colunaId);
        long id = cursor.getLong(indexColunaId);
        Cliente cliente = new Cliente();
        cliente.setIdCliente(id);
        return cliente;
    }

    private Cliente load(String query, String[] args) {
        SQLiteDatabase leitorBanco = bancoDados.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        Cliente cliente = null;
        if (cursor.moveToNext()) {
            cliente = criarCliente(cursor);
        }
        cursor.close();
        leitorBanco.close();
        return cliente;
    }

    public Cliente getClienteByIdUsuario(long id) {
        String query = "SELECT * FROM cliente " +
                "WHERE idUsuario = ?";
        String[] args = {String.valueOf(id)};
        return this.load(query, args);
    }
    public Cliente getClienteById(long id) {
        String query = "SELECT * FROM cliente " +
                "WHERE id = ?";
        String[] args = {String.valueOf(id)};
        return this.load(query, args);
    }
}

