package com.comercial.iruber.usuario.negocio;
import android.content.Context;
import com.comercial.iruber.infra.IruberException;
import com.comercial.iruber.restaurante.persistencia.RestauranteDAO;
import com.comercial.iruber.usuario.persistencia.UsuarioDAO;
import com.comercial.iruber.cliente.persistencia.ClienteDAO;
import com.comercial.iruber.usuario.dominio.Usuario;

public class ServicoLogin {
    private UsuarioDAO usuarioDAO;
    private ClienteDAO clienteDAO;
    private RestauranteDAO restauranteDAO;
    public ServicoLogin(Context context) {
        usuarioDAO = new UsuarioDAO(context);
        clienteDAO = new ClienteDAO(context);
        restauranteDAO= new RestauranteDAO(context);
    }
    public void loginCliente(Usuario usuario) throws IruberException {
        Usuario usuarioLogado = this.usuarioDAO.getByEmailSenha(usuario.getEmail(), usuario.getSenha());
        if (usuarioLogado == null) {
            throw new IruberException("Usuário ou senha inválidos");
        }

    }







}