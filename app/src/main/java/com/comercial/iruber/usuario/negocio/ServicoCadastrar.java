package com.comercial.iruber.usuario.negocio;

import android.content.Context;

import com.comercial.iruber.infra.IruberException;
import com.comercial.iruber.restaurante.dominio.Entregador;
import com.comercial.iruber.restaurante.persistencia.EntregadorDAO;
import com.comercial.iruber.usuario.persistencia.UsuarioDAO;
import com.comercial.iruber.cliente.persistencia.ClienteDAO;
import com.comercial.iruber.restaurante.persistencia.RestauranteDAO;
import com.comercial.iruber.restaurante.dominio.Restaurante;
import com.comercial.iruber.cliente.dominio.Cliente;
import com.comercial.iruber.usuario.dominio.Usuario;

public class ServicoCadastrar {
    private UsuarioDAO usuarioDAO;
    private ClienteDAO clienteDAO;
    private RestauranteDAO restauranteDAO;
    private EntregadorDAO entregadorDAO;

    public ServicoCadastrar(Context context) {
        usuarioDAO = new UsuarioDAO(context);
        clienteDAO = new ClienteDAO(context);
        restauranteDAO = new RestauranteDAO(context);
        entregadorDAO = new EntregadorDAO(context);
    }

    public void cadastrarCliente(Cliente cliente) throws IruberException {
        if (verificarEmailExistente(cliente.getUsuario().getEmail())) {
            throw new IruberException("Usuário já cadastrado");
        } else {
            this.clienteDAO.inserirCliente(cliente);
        }
    }

    public void cadastrarRestaurante(Restaurante restaurante) throws IruberException {
        if (verificarEmailExistente(restaurante.getUsuario().getEmail())) {
            throw new IruberException("Usuário já cadastrado");
        } else {
            this.restauranteDAO.inserirRestaurante(restaurante);
        }
    }

    public void cadastrarEntregador(Entregador entregador) throws IruberException{
        if (verificarEmailExistente(entregador.getUsuario().getEmail())){
            throw new IruberException("Usuário já cadastrado");
        } else{
            this.entregadorDAO.inserirEntregador(entregador);
        }
    }

    private boolean verificarEmailExistente(String email) {
        Usuario usuario = this.usuarioDAO.getByEmail(email);
        return usuario != null;
    }
}
