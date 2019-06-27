package com.comercial.iruber.restaurante.dominio;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Prato implements Parcelable {
    private long id;
    private long idRestaurante;
    private long idItemPedido;
    private String nome;
    private String descricao;
    private String  disponivel;
    private BigDecimal valor;
    private List<Ingrediente> ingredientes;

    protected Prato(Parcel in) {
        id = in.readLong();
        idRestaurante = in.readLong();
        idItemPedido = in.readLong();
        nome = in.readString();
        descricao = in.readString();
        disponivel = in.readString();
        valor = (BigDecimal) in.readSerializable();
        ingredientes = in.createTypedArrayList(Ingrediente.CREATOR);
    }

    public Prato(){

    }

    public static final Creator<Prato> CREATOR = new Creator<Prato>() {
        @Override
        public Prato createFromParcel(Parcel in) {
            return new Prato(in);
        }

        @Override
        public Prato[] newArray(int size) {
            return new Prato[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdItemPedido() {
        return idItemPedido;
    }

    public void setIdItemPedido(long idItemPedido) {
        this.idItemPedido = idItemPedido;
    }

    public String getDisponivel() {
        return disponivel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String isDisponivel() {
        return disponivel;
    }

    public long getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(long idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public void setDisponivel(String disponivel) {
        this.disponivel = disponivel;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(idRestaurante);
        dest.writeLong(idItemPedido);
        dest.writeString(nome);
        dest.writeString(descricao);
        dest.writeString(disponivel);
        dest.writeSerializable(valor);
        dest.writeTypedList(ingredientes);
    }
}
