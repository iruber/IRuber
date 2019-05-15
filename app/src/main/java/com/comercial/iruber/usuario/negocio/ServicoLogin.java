package com.comercial.iruber.usuario.negocio;

import com.comercial.iruber.infra.IruberException;

import com.comercial.iruber.usuario.persistencia.UsuarioDAO;
import com.comercial.iruber.cliente.persistencia.ClienteDAO;
import com.comercial.iruber.cliente.persistencia.PessoaDAO;
import com.comercial.iruber.infra.Sessao;
import com.comercial.iruber.cliente.dominio.Pessoa;
import com.comercial.iruber.cliente.dominio.Cliente;
import com.comercial.iruber.usuario.dominio.Usuario;


public class ServicoLogin {

    private PessoaDAO pessoaDAO;
    private UsuarioDAO usuarioDAO;
    private ClienteDAO clienteDAO;


    public ServicoLogin() {
        usuarioDAO = new UsuarioDAO();
        clienteDAO = new ClienteDAO();
        pessoaDAO = new PessoaDAO();

    }

    public void login(Usuario usuario) throws IruberException {
        Usuario usuarioLogado = this.usuarioDAO.getByEmailSenha(usuario.getEmail(), usuario.getSenha());
        if (usuarioLogado != null) {
            Pessoa pessoa = this.pessoaDAO.getPessoaByIdUser(usuarioLogado.getId());
            Cliente cliente = this.clienteDAO.getClienteByidPessoa(pessoa.getIdPessoa());
            this.iniciarSessao(cliente);
        } else {
            throw new IruberException("Usuário ou senha inválidos");
        }
    }


    private void iniciarSessao(Cliente cliente) {
        Sessao.instance.setCliente(cliente);
    }



}