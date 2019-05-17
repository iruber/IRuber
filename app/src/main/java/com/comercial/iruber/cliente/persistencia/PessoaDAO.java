package com.comercial.iruber.cliente.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.comercial.iruber.usuario.persistencia.UsuarioDAO;
import com.comercial.iruber.cliente.dominio.Pessoa;
import com.comercial.iruber.infra.persistencia.DbHelper;

public class PessoaDAO {

    private DbHelper bancoDados;
    private  UsuarioDAO usuarioDAO;

    String tabela = DbHelper.TABELA_PESSOA;
    String colunaIdUsuario = DbHelper.PESSOA_USER_ID;
    String colunaNome = DbHelper.PESSOA_NOME;
    String colunaIdade = DbHelper.PESSOA_IDADE;
    String colunaCpf = DbHelper.PESSOA_CPF;
    String colunaEndereco = DbHelper.PESSOA_ENDERECO_ID;


    public PessoaDAO(Context context) {
        bancoDados = new DbHelper(context);
        usuarioDAO = new UsuarioDAO(context);
    }
    public long inserirPessoa(Pessoa pessoa) {

        SQLiteDatabase bancoEscreve = bancoDados.getWritableDatabase();
        ContentValues values = new ContentValues();


        long idUsuario = pessoa.getUsuario().getId();
        values.put(colunaIdUsuario, idUsuario);

        String nome = pessoa.getNome();
        values.put(colunaNome,nome);

        String idade= pessoa.getIdade();
        values.put(colunaIdade,idade);

        String cpf=pessoa.getCpf();
        values.put(colunaCpf,cpf);

        long endereco=pessoa.getEndereco().getIdEndereco();
        values.put(colunaEndereco,endereco);

        long id = bancoEscreve.insert(tabela, null, values);
        bancoEscreve.close();
        return id;

    }


    public Pessoa criarPessoa(Cursor cursor){
        String colunaId = DbHelper.PESSOA_ID;
        int indexColunaId= cursor.getColumnIndex(colunaId);
        long id = cursor.getLong(indexColunaId);

        String colunaUserId = DbHelper.PESSOA_USER_ID;
        int indexColunaUserId= cursor.getColumnIndex(colunaUserId);
        long pessoaUserId = cursor.getLong(indexColunaUserId);

        String colunaCpf = DbHelper.PESSOA_CPF;
        int indexColunaCpf= cursor.getColumnIndex(colunaCpf);
        String cpf = cursor.getString(indexColunaCpf);


        String colunaNome = DbHelper.PESSOA_NOME;
        int indexColunaNome= cursor.getColumnIndex(colunaNome);
        String nome = cursor.getString(indexColunaNome);


        String colunaIdade = DbHelper.PESSOA_IDADE;
        int indexColunaIdade= cursor.getColumnIndex(colunaIdade);
        String idade = cursor.getString(indexColunaIdade);


       Pessoa pessoa= new Pessoa();
       pessoa.setIdPessoa(id);
       pessoa.setCpf(cpf);
       pessoa.setNome(nome);
       pessoa.setIdade(idade);
       pessoa.setUsuario(usuarioDAO.getByID(pessoaUserId));


        return pessoa;
    }
    private Pessoa load(String query, String[] args) {
        SQLiteDatabase leitorBanco = bancoDados.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        Pessoa pessoa = null;
        if (cursor.moveToNext()) {
            pessoa = criarPessoa(cursor);
        }
        cursor.close();
        leitorBanco.close();
        return pessoa;
    }
    public Pessoa getByID(long id) {
        String query =  "SELECT * FROM pessoa " +
                "WHERE idPessoa = ?";
        String[] args = {String.valueOf(id)};
        return this.load(query, args);
    }


    public Pessoa getPessoaByIdUser(long id) {
        String query =  "SELECT * FROM pessoa " +
                "WHERE idUsuario = ?";
        String[] args = {String.valueOf(id)};
        return this.load(query, args);
    }

}
