package com.comercial.iruber.restaurante.dominio;

import android.os.Parcel;
import android.os.Parcelable;

import com.comercial.iruber.usuario.dominio.Endereco;
import com.comercial.iruber.usuario.dominio.Usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Restaurante implements Parcelable {
    private long idRestaurante;
    private double nota;
    private Usuario usuario;
    private List<Ingrediente> ingredientes;
    private List<Prato> pratos;
    private List<Entregador> entregadores;
    private Endereco endereco;
    private String nome;
    private String cnpj;
    private String telefone;

    public static final Creator<Restaurante> CREATOR = new Creator<Restaurante>() {
        @Override
        public Restaurante createFromParcel(Parcel in) {
            return new Restaurante(in);
        }

        @Override
        public Restaurante[] newArray(int size) {
            return new Restaurante[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags){
        out.writeLong(idRestaurante);
        out.writeDouble(nota);
        out.writeValue(usuario);
        out.writeList(ingredientes);
        out.writeList(pratos);
        out.writeList(entregadores);
        out.writeValue(endereco);
        out.writeString(nome);
        out.writeString(cnpj);
        out.writeString(telefone);
    }

    private Restaurante(Parcel in){
        idRestaurante = in.readLong();
        nota = in.readDouble();
        usuario = (Usuario) in.readValue(Usuario.class.getClassLoader());
        ingredientes = new ArrayList<Ingrediente>();
        in.readList(ingredientes, Ingrediente.class.getClassLoader());
        pratos = new ArrayList<Prato>();
        in.readList(pratos, Prato.class.getClassLoader());
        entregadores = new ArrayList<Entregador>();
        in.readList(entregadores, Entregador.class.getClassLoader());
        endereco = (Endereco) in.readValue(Endereco.class.getClassLoader());
        nome = in.readString();
        cnpj = in.readString();
        telefone = in.readString();
    }

    public Restaurante(){

    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public long getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(long idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public List<Prato> getPratos() {
        return pratos;
    }

    public void setPratos(List<Prato> pratos) {
        this.pratos = pratos;
    }

    public List<Entregador> getEntregadores() {
        return entregadores;
    }

    public void setEntregadores(List<Entregador> entregadores) {
        this.entregadores = entregadores;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
