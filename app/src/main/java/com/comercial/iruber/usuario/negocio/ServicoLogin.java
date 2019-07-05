package com.comercial.iruber.usuario.negocio;

import android.content.Context;
import android.util.Log;

import com.comercial.iruber.cliente.dominio.Cliente;
import com.comercial.iruber.infra.EnumTipo;
import com.comercial.iruber.infra.IruberException;
import com.comercial.iruber.infra.Sessao;
import com.comercial.iruber.restaurante.dominio.Entregador;
import com.comercial.iruber.restaurante.dominio.Restaurante;
import com.comercial.iruber.restaurante.persistencia.EntregadorDAO;
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
        Usuario usuarioLogado = this.usuarioDAO.logarUsuario(usuario.getEmail(), usuario.getSenha(), usuario.getTipo().getDescricao());
        Sessao sessao = new Sessao();
        sessao.editSessao(usuarioLogado, contexto);
//        Log.d("usuario", usuario.getTipo().getDescricao());
//        Log.d("usuario", usuarioLogado.getTipo().getDescricao());
        if (usuarioLogado.getTipo() == null) {
            throw new IruberException("Usuário ou senha inválidos");
        }else{
            if (usuarioLogado.getTipo().getDescricao().equals(EnumTipo.RESTAURANTE.getDescricao())) {
                Restaurante restaurante = restauranteDAO.getRestauranteByIdUsuario(usuarioLogado.getId());
                sessao.editSessaoRestaurante(restaurante, contexto);
            } else if (usuarioLogado.getTipo().getDescricao().equals(EnumTipo.CLIENTE.getDescricao())) {
                Cliente cliente = clienteDAO.getClienteByIdUsuario(usuarioLogado.getId());
                sessao.editSessaoCliente(cliente, contexto);
            } else if (usuarioLogado.getTipo().getDescricao().equals(EnumTipo.ENTREGADOR.getDescricao())) {
                Entregador entregador = new EntregadorDAO(contexto).getEntregadorPorIdUsuario(usuarioLogado.getId());
                Log.d("entregador", entregador.getNome());
                sessao.editSessaoEntregador(entregador, contexto);
            }
        }

    }


}