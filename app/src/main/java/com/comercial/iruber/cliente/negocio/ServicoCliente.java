package com.comercial.iruber.cliente.negocio;

import android.content.Context;

import com.comercial.iruber.cliente.dominio.Cliente;
import com.comercial.iruber.cliente.persistencia.ClienteDAO;
import com.comercial.iruber.infra.IruberException;

public class ServicoCliente {
    private ClienteDAO clienteDAO;

    public ServicoCliente(Context context){
        this.clienteDAO = new ClienteDAO(context);
    }

    public void registrarCliente(Cliente cliente) throws IruberException{
        clienteDAO.inserirCliente(cliente);
    }

    public void updateCliente(Cliente cliente) throws IruberException{
        clienteDAO.updateCliente(cliente);
    }
}
