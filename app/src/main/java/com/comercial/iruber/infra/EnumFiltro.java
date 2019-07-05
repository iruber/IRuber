package com.comercial.iruber.infra;

public enum EnumFiltro {
    SEM_FILTRO("sem_filtro"), NOME("nome"), PRECO("preco"),
    AVALIACAO("avaliacao"), ATIVADO("ativado"), DESATIVADO("desativado"), EM_FALTA("em_falta");
    private final String tipo;
    EnumFiltro(String tipo){
        this.tipo = tipo;
    }
    public String getTipo(){
        return this.tipo;
    }
}
