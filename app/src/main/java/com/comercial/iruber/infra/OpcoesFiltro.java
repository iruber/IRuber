package com.comercial.iruber.infra;

public class OpcoesFiltro {
    private boolean isSelected;
    private String opcao;
    private EnumFiltro enumFiltro;

    public OpcoesFiltro(boolean isSelected, String opcao, EnumFiltro enumFiltro) {
        this.isSelected = isSelected;
        this.opcao = opcao;
        this.enumFiltro = enumFiltro;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getOpcao() {
        return opcao;
    }

    public void setOpcao(String opcao) {
        this.opcao = opcao;
    }

    public EnumFiltro getEnumFiltro() {
        return enumFiltro;
    }

    public void setEnumFiltro(EnumFiltro enumFiltro) {
        this.enumFiltro = enumFiltro;
    }
}
