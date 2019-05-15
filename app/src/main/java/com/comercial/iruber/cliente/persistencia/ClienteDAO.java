package com.comercial.iruber.cliente.persistencia;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.comercial.iruber.cliente.persistencia.PessoaDAO;
import com.comercial.iruber.cliente.dominio.Cliente;
import com.comercial.iruber.infra.persistencia.DbHelper;
public class ClienteDAO {

    private DbHelper bancoDados;
    private PessoaDAO pessoaDAO;



    String tabela = DbHelper.TABELA_CLIENTE;
    String colunaIdPessoa = DbHelper.CLIENTE_ID_PESSOA;

    public ClienteDAO(){
        bancoDados = new DbHelper();
        pessoaDAO = new PessoaDAO();

    }



    public long inserirCliente(Cliente cliente) {

        SQLiteDatabase bancoEscreve = bancoDados.getWritableDatabase();
        ContentValues values = new ContentValues();


        long idPessoa = cliente.getPessoa().getIdPessoa();
        values.put(colunaIdPessoa, idPessoa);

        long id = bancoEscreve.insert(tabela, null, values);
        bancoEscreve.close();
        return id;

    }

    public Cliente criarCliente(Cursor cursor){

        String colunaId = DbHelper.CLIENTE_ID;
        int indexColunaId= cursor.getColumnIndex(colunaId);
        long id = cursor.getLong(indexColunaId);

        String colunaPessoaId = DbHelper.CLIENTE_ID_PESSOA;
        int indexColunaIdPessoa = cursor.getColumnIndex(colunaPessoaId);
        String idPessoa = cursor.getString(indexColunaIdPessoa);


        Cliente  cliente =  new Cliente();

        cliente.setIdCliente(id);
        cliente.setPessoa(pessoaDAO.getByID(idPessoa));

        return cliente;
    }

}
