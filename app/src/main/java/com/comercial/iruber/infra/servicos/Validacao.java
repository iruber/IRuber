package com.comercial.iruber.infra.servicos;


import android.util.Patterns;

public class Validacao {
    public boolean verificarCampoVazio(String campo) {
        if (campo.isEmpty()) {
            return true;
        }
        return false;
    }
    public boolean verificarCampoEmail(String email) {
        if (verificarCampoVazio(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        } else {
            return false;
        }
    }
}