package com.comercial.iruber.usuario.negocio;

import android.content.Context;

import com.comercial.iruber.infra.IruberException;

import com.comercial.iruber.restaurante.dominio.Restaurante;
import com.comercial.iruber.restaurante.persistencia.EmpresaDAO;
import com.comercial.iruber.restaurante.persistencia.RestauranteDAO;
import com.comercial.iruber.usuario.persistencia.UsuarioDAO;
import com.comercial.iruber.cliente.persistencia.ClienteDAO;
import com.comercial.iruber.cliente.persistencia.PessoaDAO;
import com.comercial.iruber.restaurante.dominio.Empresa;
import com.comercial.iruber.restaurante.dominio.Restaurante;
import com.comercial.iruber.infra.Sessao;
import com.comercial.iruber.cliente.dominio.Pessoa;
import com.comercial.iruber.cliente.dominio.Cliente;
import com.comercial.iruber.usuario.dominio.Usuario;


public class ServicoLogin {

    private PessoaDAO pessoaDAO;
    private UsuarioDAO usuarioDAO;
    private ClienteDAO clienteDAO;

    private RestauranteDAO restauranteDAO;
    private EmpresaDAO empresaDAO;

    public ServicoLogin(Context context) {
        usuarioDAO = new UsuarioDAO(context);
        clienteDAO = new ClienteDAO(context);
        pessoaDAO = new PessoaDAO(context);
        empresaDAO= new EmpresaDAO(context);
        restauranteDAO= new RestauranteDAO(context);
    }

    public void loginCliente(Usuario usuario) throws IruberException {

        Usuario usuarioLogado = this.usuarioDAO.getByEmailSenha(usuario.getEmail(), usuario.getSenha());
        if (usuarioLogado != null) {
            Pessoa pessoa = this.pessoaDAO.getPessoaByIdUser(usuarioLogado.getId());
            Cliente cliente = this.clienteDAO.getClienteByidPessoa(pessoa.getIdPessoa());
            Sessao.instance.setCliente(cliente);
        } else {
            throw new IruberException("Usuário ou senha inválidos");
        }
    }


    public void loginRestaurante(Usuario usuario) throws IruberException {
        Usuario usuarioLogado = this.usuarioDAO.getByEmailSenha(usuario.getEmail(), usuario.getSenha());
        if (usuarioLogado != null) {
            Empresa empresa = this.empresaDAO.getEmpresabyIdUser(usuarioLogado.getId());
            Restaurante restaurante = this.restauranteDAO.getRestauranteByIdEmpresa(empresa.getId());
            Sessao.instance.setRestaurante(restaurante);
        } else {
            throw new IruberException("Usuário ou senha inválidos");
        }
    }





}