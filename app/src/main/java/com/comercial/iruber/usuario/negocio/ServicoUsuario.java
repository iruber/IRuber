package com.comercial.iruber.usuario.negocio;

import android.content.Context;

import com.comercial.iruber.restaurante.persistencia.RestauranteDAO;
import com.comercial.iruber.usuario.dominio.Cidade;
import com.comercial.iruber.usuario.dominio.Usuario;
import com.comercial.iruber.usuario.persistencia.UsuarioDAO;

public class ServicoUsuario {
    private UsuarioDAO usuarioDAO;
    private RestauranteDAO restauranteDAO;

    public ServicoUsuario(Context context){
        this.usuarioDAO = new UsuarioDAO(context);
        this.restauranteDAO = new RestauranteDAO(context);
    }

    public void updateUsuario (Usuario usuario){
        usuarioDAO.updateSenhaUsuario(usuario);
    }
 }
