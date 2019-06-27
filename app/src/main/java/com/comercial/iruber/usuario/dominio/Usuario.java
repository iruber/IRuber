package com.comercial.iruber.usuario.dominio;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import com.comercial.iruber.infra.EnumTipo;

public class Usuario implements Parcelable {
    private long id;
    private String email;
    private String senha;
    private EnumTipo tipo;
    private Bitmap imagem;

    protected Usuario(Parcel in) {
        id = in.readLong();
        email = in.readString();
        senha = in.readString();
        imagem = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    public Usuario(){

    }

    public Bitmap getImagem() {
        return imagem;
    }

    public void setImagem(Bitmap imagem) {
        this.imagem = imagem;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public EnumTipo getTipo() {
        return tipo;
    }

    public void setTipo(EnumTipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(email);
        dest.writeString(senha);
        dest.writeParcelable(imagem, flags);
    }
}

