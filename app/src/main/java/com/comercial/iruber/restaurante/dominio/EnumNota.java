package com.comercial.iruber.restaurante.dominio;

public enum EnumNota {
    UM("1"), DOIS("2"),
    TRES("3"), QUATRO("4"),CINCO("5");

    private String descricao;

    EnumNota(String descricao) { this.descricao = descricao; }

    public String getDescricao() { return this.descricao; }


    @Override
    public String toString() { return this.descricao; }
}
