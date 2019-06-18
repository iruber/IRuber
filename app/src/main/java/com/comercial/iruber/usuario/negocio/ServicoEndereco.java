package com.comercial.iruber.usuario.negocio;

import android.content.Context;
import com.comercial.iruber.infra.IruberException;
import com.comercial.iruber.usuario.dominio.Endereco;
import com.comercial.iruber.usuario.persistencia.EnderecoDAO;

public class ServicoEndereco {
    private EnderecoDAO enderecoDAO;

    public ServicoEndereco(Context context){
        this.enderecoDAO = new EnderecoDAO(context);
    }

    public void registrarEndereco(Endereco endereco) throws IruberException {
        enderecoDAO.inserirEndereco(endereco);
    }

    public void updateEndereco(Endereco endereco) throws IruberException{
        enderecoDAO.updateEndereco(endereco);
    }
}
