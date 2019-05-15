package com.comercial.iruber.usuario.negocio;


import com.comercial.iruber.infra.IruberException;

import com.comercial.iruber.usuario.persistencia.UsuarioDAO;
import com.comercial.iruber.cliente.persistencia.ClienteDAO;
import com.comercial.iruber.cliente.persistencia.PessoaDAO;

import com.comercial.iruber.cliente.dominio.Pessoa;
import com.comercial.iruber.cliente.dominio.Cliente;
import com.comercial.iruber.usuario.dominio.Usuario;

public class ServicoCadastrar {
    private UsuarioDAO usuarioDAO;
    private ClienteDAO clienteDAO;
    private PessoaDAO pessoaDAO;

    public  ServicoCadastrar(){
        usuarioDAO=new UsuarioDAO();
        clienteDAO=new ClienteDAO();
        pessoaDAO= new PessoaDAO();
    }

    public void cadastrar(Cliente cliente) throws IruberException{
        if(verificarEmailExistente(cliente.getUserEmail())){
            throw  new IruberException("Usuario já cadastrado");

        }else{

            long idUser= this.usuarioDAO.inserirUsuario(cliente.getUser());
            long idPessoa= this.pessoaDAO.inserirPessoa(cliente.getPessoa());
            cliente.getPessoa().setIdPessoa(idPessoa);
            cliente.getUser().setId(idUser);
            this.clienteDAO.inserirCliente(cliente);
        }


    }
    private boolean verificarEmailExistente(String email) {
        Usuario usuario = this.usuarioDAO.getByEmail(email);
        return usuario != null;

    }





}
