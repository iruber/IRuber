package com.comercial.iruber.cliente.persistencia;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.comercial.iruber.cliente.dominio.Cartao;
import com.comercial.iruber.cliente.dominio.Cliente;
import com.comercial.iruber.infra.persistencia.DbHelper;
import com.comercial.iruber.usuario.persistencia.EnderecoDAO;
import com.comercial.iruber.usuario.persistencia.UsuarioDAO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ClienteDAO {
    private DbHelper bancoDados;
    private UsuarioDAO usuarioDAO;
    private EnderecoDAO enderecoDAO;
    private CartaoDAO cartaoDAO;
    private  ClienteCartaoDAO clienteCartaoDAO;

    public ClienteDAO(Context context) {
        bancoDados = new DbHelper(context);
        usuarioDAO = new UsuarioDAO(context);
        enderecoDAO = new EnderecoDAO(context);
        cartaoDAO = new CartaoDAO(context);
        clienteCartaoDAO=new ClienteCartaoDAO(context);
    }

    public long inserirCliente(Cliente cliente) {
        SQLiteDatabase bancoEscreve = bancoDados.getWritableDatabase();
        ContentValues values = new ContentValues();
        long idUser = this.usuarioDAO.inserirUsuario(cliente.getUsuario());
        long idEndereco = this.enderecoDAO.inserirEndereco(cliente.getEndereco());
        String nome = cliente.getNome();
        Date nascimento = cliente.getNascimento();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String snascimento = dateFormat.format(nascimento);
        String telefone =  cliente.getTelefone();
        String cpf = cliente.getCpf();
        cliente.getUsuario().setId(idUser);
        values.put(ContratoCliente.PESSOA_TELEFONE,telefone);
        values.put(ContratoCliente.PESSOA_USER_ID, idUser);
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
        String colunaCPf= ContratoCliente.PESSOA_CPF;
        int indexColunaCpf=cursor.getColumnIndex(colunaCPf);
        String cpf = cursor.getString(indexColunaCpf);
        String colunaTelefone=ContratoCliente.PESSOA_TELEFONE;
        int colunaIndexTelefone=cursor.getColumnIndex(colunaTelefone);
        String telefone = cursor.getString(colunaIndexTelefone);
        String colunaEndereco=  ContratoCliente.PESSOA_ENDERECO_ID;
        int colunaIndexEndereco= cursor.getColumnIndex(colunaEndereco);
        long idEndereco = cursor.getLong(colunaIndexEndereco);
        String colunaNome = ContratoCliente.PESSOA_NOME;
        int colunaIndexNome=  cursor.getColumnIndex(colunaNome);
        String nome = cursor.getString(colunaIndexNome);
        String colunaNascimento=ContratoCliente.PESSOA_NASCIMENTO;
        int colunaIndexNascimento = cursor.getColumnIndex(colunaNascimento);
        String nascimento = cursor.getString(colunaIndexNascimento);
        Date date = new Date();
        try {
            date = new SimpleDateFormat("dd-MM-yyyy", Locale.CANADA).parse(nascimento);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Cliente cliente = new Cliente();
        cliente.setIdCliente(id);
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setEndereco(enderecoDAO.getEnderecoById(idEndereco));
        cliente.setNascimento(date);
        cliente.setTelefone(telefone);
        cliente.setCartoes(cartaoDAO.listadeCartoes(id));
        return cliente;
    }

    private Cliente criar(String query, String[] args) {
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

    public Cliente getClienteByIdUsuario(long idUser) {
        String query = "SELECT * FROM cliente " +
                "WHERE idUser = ?";
        String[] args = {String.valueOf(idUser)};
        return this.criar(query, args);
    }
    public Cliente getClienteById(long id) {
        String query = "SELECT * FROM cliente " +
                "WHERE id = ?";
        String[] args = {String.valueOf(id)};
        return this.criar(query, args);
    }

    public void updateCliente(Cliente cliente) {
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        String query = "id = ?";
        ContentValues values = new ContentValues();
        values.put(ContratoCliente.PESSOA_TELEFONE,cliente.getTelefone());
        values.put(ContratoCliente.PESSOA_CPF, cliente.getCpf());
        values.put(ContratoCliente.PESSOA_NOME,cliente.getNome());
        String[] args = {String.valueOf(cliente.getIdCliente())};
        escritorBanco.update(ContratoCliente.NOME_TABELA, values, query, args);
        escritorBanco.close();
    }

    private  void adicionarCartoes(Cartao cartao,long idCliente){


            long id = cartaoDAO.inserirCartao(cartao);
              clienteCartaoDAO.inserirCartao(idCliente,cartao.getIdCartao());
        }

    }



