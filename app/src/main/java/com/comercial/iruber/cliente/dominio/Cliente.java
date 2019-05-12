package com.comercial.iruber.cliente.dominio;

import java.util.ArrayList;

import com.comercial.iruber.cliente.dominio.Cartao;
import com.comercial.iruber.cliente.dominio.Pessoa;

public class Cliente {
    private long idCliente;
    private Pessoa pessoa;
    private ArrayList<Cartao> cartoes;

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {

        this.idCliente = idCliente;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }


    public ArrayList<Cartao> getCartoes() {
        return cartoes;
    }

    public void setCartoes(ArrayList<Cartao> cartoes) {
        this.cartoes = cartoes;
    }
}
