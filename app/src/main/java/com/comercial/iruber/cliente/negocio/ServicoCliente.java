package com.comercial.iruber.cliente.negocio;

import android.content.Context;

import com.comercial.iruber.cliente.dominio.Cliente;
import com.comercial.iruber.cliente.persistencia.ClienteDAO;
import com.comercial.iruber.infra.IruberException;
import com.comercial.iruber.restaurante.dominio.Restaurante;
import com.comercial.iruber.restaurante.persistencia.RestauranteDAO;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ServicoCliente {
    private ClienteDAO clienteDAO;
    private RestauranteDAO restauranteDAO;

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
