package com.comercial.iruber.restaurante.dominio;

import android.os.Parcel;
import android.os.Parcelable;

import com.comercial.iruber.pedido.dominio.StatusDisponibilidade;

public class Ingrediente implements Parcelable {
    private long id;
    private long idPrato;
    private String nome;
    private StatusDisponibilidade disponivel;
    private boolean isChecked = false;


    protected Ingrediente(Parcel in) {
        id = in.readLong();
        idPrato = in.readLong();
        nome = in.readString();
        disponivel = (StatusDisponibilidade) in.readSerializable();
        isChecked = in.readByte() != 0;
    }

    public Ingrediente(){

    }

    public static final Creator<Ingrediente> CREATOR = new Creator<Ingrediente>() {
        @Override
        public Ingrediente createFromParcel(Parcel in) {
            return new Ingrediente(in);
        }

        @Override
        public Ingrediente[] newArray(int size) {
            return new Ingrediente[size];
        }
    };

    public long getIdIngrediente() {
        return id;
    }

    public void setIdIngrediente(long idIngrediente) {
        this.id = idIngrediente;
    }

    public String getNome() {
        return nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdPrato() {
        return idPrato;
    }

    public void setIdPrato(long idPrato) {
        this.idPrato = idPrato;
    }

    public void setDisponivel(StatusDisponibilidade disponivel) {
        this.disponivel = disponivel;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public StatusDisponibilidade getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(String StatusDisponibilidade) {
        this.disponivel = disponivel;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(idPrato);
        dest.writeString(nome);
        dest.writeSerializable(disponivel);
        dest.writeByte((byte) (isChecked ? 1 : 0));
    }
}
