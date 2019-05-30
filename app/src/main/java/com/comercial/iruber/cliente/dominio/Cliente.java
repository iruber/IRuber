package com.comercial.iruber.cliente.dominio;
import com.comercial.iruber.usuario.dominio.Endereco;
import com.comercial.iruber.usuario.dominio.Usuario;
import java.util.ArrayList;

public class Cliente {
    private long idCliente;
    private ArrayList<Cartao> cartoes;
    private String nome;
    private String cpf;
    private String nascimento;
    private Endereco endereco;
    private Usuario usuario;

    public long getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(long idCliente) {

        this.idCliente = idCliente;
    }
    public ArrayList<Cartao> getCartoes() {
        return cartoes;
    }
    public void setCartoes(ArrayList<Cartao> cartoes) {
        this.cartoes = cartoes;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getNascimento() {
        return nascimento;
    }
    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
