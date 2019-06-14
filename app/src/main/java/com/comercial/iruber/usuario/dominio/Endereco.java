package com.comercial.iruber.usuario.dominio;

import com.comercial.iruber.infra.EnumTipo;

public class Endereco {
    private long idEndereco;
    private String numero;
    private String cidade;
    private String cep;
    private String bairro;
    private String estado;
    private String rua;
    private EnumTipo tipoUsuario;
    private long idProprietario;

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEnderecoFormatado(){
        return "Rua " + this.getRua()
                + ", numero " + this.getNumero()
                + ", bairro " + this.getBairro()
                +". " + this.getCidade() + "-"
                + this.getEstado();
    }

    public EnumTipo getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(EnumTipo tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public long getIdProprietario() {
        return idProprietario;
    }

    public void setIdProprietario(long idProprietario) {
        this.idProprietario = idProprietario;
    }
}
