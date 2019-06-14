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
        Usuario usuarioLogado = this.usuarioDAO.logarUsuario(usuario.getEmail(), usuario.getSenha(), usuario.getTipo().getDescricao());
        Sessao sessao = new Sessao();
        sessao.editSessao(usuarioLogado, contexto);
        if (usuarioLogado == null) {
            throw new IruberException("Usuário ou senha inválidos");
        }
        Log.d("logar", usuarioLogado.getTipo().getDescricao());
        Log.d("logarRestaurante", EnumTipo.RESTAURANTE.getDescricao());
        if (usuarioLogado.getTipo().getDescricao().equals(EnumTipo.RESTAURANTE.getDescricao())) {
            Restaurante restaurante = restauranteDAO.getRestauranteByIdUsuario(usuarioLogado.getId());
            Log.d("logarRestaurante cnpgj:", restaurante.getCnpj());
            sessao.editSessaoRestaurante(restaurante, contexto);
        } else if (usuarioLogado.getTipo().getDescricao().equals(EnumTipo.CLIENTE.getDescricao())) {
            Cliente cliente = clienteDAO.getClienteByIdUsuario(usuarioLogado.getId());
            sessao.editSessaoCliente(cliente, contexto);
        } else if (usuarioLogado.getTipo().getDescricao().equals(EnumTipo.ENTREGADOR.getDescricao())) {
            Entregador entregador = new Entregador();
            sessao.editSessaoEntregador(entregador, contexto);
        }

    }


}