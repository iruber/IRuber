package com.comercial.iruber.restaurante.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.comercial.iruber.infra.persistencia.DbHelper;
import com.comercial.iruber.restaurante.dominio.Empresa;
import com.comercial.iruber.usuario.dominio.Usuario;
import com.comercial.iruber.usuario.persistencia.UsuarioDAO;

public class EmpresaDAO {

    private DbHelper bancoDados;
    private UsuarioDAO usuarioDAO;

    String tabela = DbHelper.TABELA_EMPRESA;
    String colunaEnderecoId = DbHelper.EMPRESA_ID_ENDERECO;
    String colunaCNPJ = DbHelper.EMPRESA_CNPJ;
    String colunaNome = DbHelper.EMPRESA_NOME;
    String colunaIdUsuario=DbHelper.EMPRESA_ID_USUARIO;



    public EmpresaDAO(Context context) {
        bancoDados = new DbHelper(context);
        usuarioDAO = new UsuarioDAO(context);
    }

    public long inserirEmpresa(Empresa empresa) {

        SQLiteDatabase bancoEscreve = bancoDados.getWritableDatabase();
        ContentValues values = new ContentValues();


        long idEndereco = empresa.getEndereco().getIdEndereco();
        values.put(colunaEnderecoId, idEndereco);

        String cnpj = empresa.getCnpj();
        values.put(colunaCNPJ, cnpj);

        String nome = empresa.getNome();
        values.put(colunaNome,nome);

        long idUsuario=empresa.getUsuario().getId();
        values.put(colunaIdUsuario,idUsuario);


        long id = bancoEscreve.insert(tabela, null, values);
        bancoEscreve.close();
        return id;

    }




    public Empresa criarEmpresa(Cursor cursor){
        String colunaId = DbHelper.EMPRESA_ID;
        int indexColunaId= cursor.getColumnIndex(colunaId);
        long id = cursor.getLong(indexColunaId);

        String colunaNome = DbHelper.EMPRESA_NOME;
        int indexColunaNome = cursor.getColumnIndex(colunaNome);
        String nome = cursor.getString(indexColunaNome);

        String colunaCNPJ = DbHelper.EMPRESA_CNPJ;
        int indexColunaCNPJ = cursor.getColumnIndex(colunaCNPJ);
        String cnpj = cursor.getString(indexColunaCNPJ);

        String colunaEndereco=DbHelper.EMPRESA_ID_ENDERECO;
        int indexColunaEndereco=cursor.getColumnIndex(colunaEndereco);
        long idEndereco= cursor.getLong(indexColunaEndereco);

        String colunaidUsuario=DbHelper.EMPRESA_ID_USUARIO;
        int indexColunaIdUsuario=cursor.getColumnIndex(colunaidUsuario);
        long idUsuario= cursor.getLong(indexColunaIdUsuario);



       Empresa empresa = new Empresa();
       empresa.setId(id);
       empresa.setCnpj(cnpj);
       empresa.setEndereco(null);
       empresa.setNome(nome);
       empresa.setUsuario(usuarioDAO.getByID(idUsuario));

       return empresa;
    }

    private Empresa load(String query, String[] args) {
        SQLiteDatabase leitorBanco = bancoDados.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        Empresa empresa = null;
        if (cursor.moveToNext()) {
            empresa = criarEmpresa(cursor);
        }
        cursor.close();
        leitorBanco.close();
        return empresa;
    }
    public Empresa getByID(long id) {
        String query =  "SELECT * FROM empresa " +
                "WHERE empresaId = ?";
        String[] args = {String.valueOf(id)};
        return this.load(query, args);
    }

    public Empresa getEmpresabyIdUser(long id) {
        String query =  "SELECT * FROM empresa " +
                "WHERE idUsuario = ?";
        String[] args = {String.valueOf(id)};
        return this.load(query, args);
    }





}
