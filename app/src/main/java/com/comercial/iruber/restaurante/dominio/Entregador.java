package com.comercial.iruber.restaurante.dominio;

import android.os.Parcel;
import android.os.Parcelable;

import com.comercial.iruber.usuario.dominio.Usuario;

public class Entregador implements Parcelable {
    private long idEntregador;
    private long idRestaurante;
    private String nome;
    private String telefone;
    private Usuario usuario;
    private EnumEntregador estado;

    protected Entregador(Parcel in) {
        idEntregador = in.readLong();
        idRestaurante = in.readLong();
        nome = in.readString();
        telefone = in.readString();
        usuario = in.readParcelable(Usuario.class.getClassLoader());
        estado = (EnumEntregador) in.readSerializable();
    }

    public Entregador(){

    }

    public static final Creator<Entregador> CREATOR = new Creator<Entregador>() {
        @Override
        public Entregador createFromParcel(Parcel in) {
            return new Entregador(in);
        }

        @Override
        public Entregador[] newArray(int size) {
            return new Entregador[size];
        }
    };

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public long getIdEntregador() {
        return idEntregador;
    }

    public void setIdEntregador(long idEntregador) {
        this.idEntregador = idEntregador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(long idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public Usuario getUsuario() { return usuario; }

    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public EnumEntregador getEstado() { return estado; }

    public void setEstado(EnumEntregador estado) { this.estado = estado; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(idEntregador);
        dest.writeLong(idRestaurante);
        dest.writeString(nome);
        dest.writeString(telefone);
        dest.writeParcelable(usuario, flags);
        dest.writeSerializable(estado);
    }
}
