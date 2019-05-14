package com.comercial.iruber.cliente.persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.comercial.iruber.usuario.dominio.Usuario;
import com.comercial.iruber.cliente.dominio.Pessoa;
import com.comercial.iruber.infra.persistencia.DbHelper;

public class PessoaDAO {

    private DbHelper bancoDados;
    private SQLiteDatabase db;

    String tabela = DbHelper.TABELA_PESSOA;
    String colunaIdUsuario = DbHelper.PESSOA_USER_ID;
    String colunaNome = DbHelper.PESSOA_NOME;
    String colunaIdade = DbHelper.PESSOA_IDADE;
    String colunaCpf = DbHelper.PESSOA_CPF;
    String colunaEndereco = DbHelper.PESSOA_ENDERECO_ID;


    public PessoaDAO() {
        bancoDados = new DbHelper();
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

        long id = db.insert(tabela, null, values);
        db.close();
        return id;

    }

    /*

    FIZ O BACKUP dessa função. Depois implementaremos ela direitinho, por hora ela é inutil para nossa apresentação  por isso deve ser apagada.

    public Pessoa criarPessoa(Cursor cursor){
        String colunaId = DbHelper.PESSOA_ID;              // O tipo é String porque estar definido assim no DbHelper
        int indexColunaId= cursor.getColumnIndex(colunaId);
        int id = cursor.getInt(indexColunaId);

        String colunaUserId = DbHelper.PESSOA_USER_ID;
        int indexColunaUserId= cursor.getColumnIndex(colunaId);
        int pessoaUserId = cursor.getInt(indexColunaId);

        String colunaCpf = DbHelper.PESSOA_CPF;
        int indexColunaCpf= cursor.getColumnIndex(colunaId);
        String cpf = cursor.getString(indexColunaId);


        String colunaNome = DbHelper.PESSOA_NOME;
        int indexColunaNome= cursor.getColumnIndex(colunaId);
        String nome = cursor.getString(indexColunaId);


        String colunaIdade = DbHelper.PESSOA_IDADE;
        int indexColunaIdade= cursor.getColumnIndex(colunaId);
        String idade = cursor.getString(indexColunaId);


        String colunaEndereco = DbHelper.PESSOA_ENDERECO_ID;
        int indexColunaEndereco= cursor.getColumnIndex(colunaId);
        long endereco = cursor.getInt(indexColunaId);



       Pessoa pessoa= new Pessoa();
       pessoa.setIdPessoa(id);
       pessoa.setCpf(cpf);


        return pessoa;
    }
*/


}
