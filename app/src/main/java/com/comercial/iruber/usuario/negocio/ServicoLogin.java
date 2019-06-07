package com.comercial.iruber.usuario.negocio;

import android.content.Context;
import android.util.Log;

import com.comercial.iruber.cliente.dominio.Cliente;
import com.comercial.iruber.infra.EnumTipo;
import com.comercial.iruber.infra.IruberException;
import com.comercial.iruber.infra.Sessao;
import com.comercial.iruber.restaurante.dominio.Entregador;
import com.comercial.iruber.restaurante.dominio.Restaurante;
import com.comercial.iruber.restaurante.persistencia.RestauranteDAO;
import com.comercial.iruber.usuario.persistencia.UsuarioDAO;
import com.comercial.iruber.cliente.persistencia.ClienteDAO;
import com.comercial.iruber.usuario.dominio.Usuario;

public class ServicoLogin {
    private UsuarioDAO usuarioDAO;
    private ClienteDAO clienteDAO;
    private RestauranteDAO restauranteDAO;
    private Context contexto;

    public ServicoLogin(Context context) {
        usuarioDAO = new UsuarioDAO(context);
        clienteDAO = new ClienteDAO(context);
        restauranteDAO = new RestauranteDAO(context);
        contexto = context;
    }

    public void loginCliente(Usuario usuario) throws IruberException {
        Usuario usuarioLogado = this.usuarioDAO.logarUsuario(usuario.getEmail(), usuario.getSenha());
        if (usuarioLogado == null) {
            throw new IruberException("Usuário ou senha inválidos");
        }
        Log.d("sessao", usuarioLogado.getTipo().toString());
        if (usuarioLogado.getTipo() == EnumTipo.RESTAURANTE){
            Restaurante restaurante = restauranteDAO.getRestauranteByIdUsuario(usuarioLogado.getId());
            Sessao sessao = new Sessao();
            sessao.editSessaoRestaurante(restaurante, contexto);
        }else if(usuarioLogado.getTipo() == EnumTipo.CLIENTE){
            Cliente cliente = clienteDAO.getClienteByIdUsuario(usuarioLogado.getId());
            Sessao sessao = new Sessao();
            sessao.editSessaoCliente(cliente, contexto);
        }else if(usuarioLogado.getTipo() == EnumTipo.ENTREGADOR){
            Entregador entregador = new Entregador();
            Sessao sessao = new Sessao();
            sessao.editSessaoEntregador(entregador, contexto);
        }

    }


}