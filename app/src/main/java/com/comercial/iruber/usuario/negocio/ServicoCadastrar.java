package com.comercial.iruber.usuario.negocio;


import android.content.Context;

import com.comercial.iruber.infra.IruberException;

import com.comercial.iruber.usuario.persistencia.UsuarioDAO;
import com.comercial.iruber.cliente.persistencia.ClienteDAO;
import com.comercial.iruber.cliente.persistencia.PessoaDAO;
import com.comercial.iruber.restaurante.persistencia.RestauranteDAO;
import com.comercial.iruber.restaurante.persistencia.EmpresaDAO;
import com.comercial.iruber.restaurante.dominio.Empresa;
import com.comercial.iruber.restaurante.dominio.Restaurante;
import com.comercial.iruber.cliente.dominio.Pessoa;
import com.comercial.iruber.cliente.dominio.Cliente;
import com.comercial.iruber.usuario.dominio.Usuario;

public class ServicoCadastrar {
    private UsuarioDAO usuarioDAO;
    private ClienteDAO clienteDAO;
    private PessoaDAO pessoaDAO;

    private RestauranteDAO restauranteDAO;
    private EmpresaDAO empresaDAO;

    public ServicoCadastrar(Context context){
        usuarioDAO=new UsuarioDAO(context);
        clienteDAO=new ClienteDAO(context);
        pessoaDAO= new PessoaDAO(context);

        empresaDAO= new EmpresaDAO(context);
        restauranteDAO= new RestauranteDAO(context);

    }

    public void cadastrarCliente(Cliente cliente) throws IruberException{
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

    public void cadastrarRestaurante(Restaurante restaurante) throws IruberException{
        if(verificarEmailExistente(restaurante.getUserEmail())){
            throw  new IruberException("Usuario já cadastrado");

        }else{

            long idUser= this.usuarioDAO.inserirUsuario(restaurante.getUsuario());
            long idEmpresa= this.empresaDAO.inserirEmpresa(restaurante.getEmpresa());
            restaurante.getEmpresa().setId(idEmpresa);
            restaurante.getUsuario().setId(idUser);
            this.restauranteDAO.inserirRestaurante(restaurante);
        }
    }
    private boolean verificarEmailExistente(String email) {
        Usuario usuario = this.usuarioDAO.getByEmail(email);
        return usuario != null;

    }
}
