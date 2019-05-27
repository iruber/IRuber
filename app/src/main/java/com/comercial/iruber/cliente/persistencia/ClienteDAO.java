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

public class ClienteDAO {

    private DbHelper bancoDados;
    private UsuarioDAO usuarioDAO;
    private EnderecoDAO enderecoDAO;


    String tabela = DbHelper.TABELA_CLIENTE;
    String colunanome = DbHelper.PESSOA_NOME;
    String colunacpf = DbHelper.PESSOA_CPF;
    String colunaidade =DbHelper.PESSOA_IDADE;
    String colunaidUser =DbHelper.PESSOA_USER_ID;
    String colunaidEndereco= DbHelper.PESSOA_ENDERECO_ID;



    public ClienteDAO(Context context){
        bancoDados = new DbHelper(context);
        usuarioDAO= new UsuarioDAO(context);
        enderecoDAO=new EnderecoDAO(context);
    }



    public long inserirCliente(Cliente cliente) {

        SQLiteDatabase bancoEscreve = bancoDados.getWritableDatabase();
        ContentValues values = new ContentValues();

        cliente.getUsuario().setTipo(EnumTipo.CLIENTE);

        long idUser= this.usuarioDAO.inserirUsuario(cliente.getUsuario());
        long idEndereco=this.enderecoDAO.inserirEndereco(cliente.getEndereco());

        String nome = cliente.getNome();
        String idade = cliente.getNascimento();
        String cpf =  cliente.getCpf();

        cliente.getUsuario().setId(idUser);
        values.put(colunaidUser,idUser);
        values.put(colunacpf,cpf);
        values.put(colunaidade,idade);
        values.put(colunaidEndereco,idEndereco);
        values.put(colunanome,nome);




        long id = bancoEscreve.insert(tabela, null, values);
        bancoEscreve.close();
        return id;

    }

    public Cliente criarCliente(Cursor cursor){

        String colunaId = DbHelper.CLIENTE_ID;
        int indexColunaId= cursor.getColumnIndex(colunaId);
        long id = cursor.getLong(indexColunaId);



        Cliente  cliente =  new Cliente();

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
    public Cliente getClienteByidPessoa(long id) {
        String query =  "SELECT * FROM cliente " +
                "WHERE idPessoa = ?";
        String[] args = {String.valueOf(id)};
        return this.load(query, args);
    }






}
