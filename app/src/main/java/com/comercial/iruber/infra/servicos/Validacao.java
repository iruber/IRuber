package com.comercial.iruber.infra.servicos;


import android.util.Log;
import android.util.Patterns;

import java.util.InputMismatchException;

public class Validacao {
    public boolean verificarCampoVazio(String campo) {
        if (campo.length()==0){
            return false;
        }
        return true;
    }
    public boolean verificarCampoEmail(String email) {
        if (!email.matches("^((?!.*?\\.\\.)[A-Za-z0-9\\.\\!\\#\\$\\%\\&\\'*\\+\\-\\/\\=\\?\\^_`\\{\\|\\}\\~]+@[A-Za-z0-9]+[A-Za-z0-9\\-\\.]+\\.[A-Za-z0-9\\-\\.]+[A-Za-z0-9]+)$")) {
            return false;
        }else {
            return true;
        }
    }

    public boolean isCPF(String cpf) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (cpf.equals("00000000000") ||
                cpf.equals("11111111111") ||
                cpf.equals("22222222222") || cpf.equals("33333333333") ||
                cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") ||
                cpf.equals("88888888888") || cpf.equals("99999999999") ||
                (cpf.length() != 11))
            return (false);
        char dig10;
        char dig11;
        int sm;
        int i;
        int r;
        int num;
        int peso;
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char) (r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char) (r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
                return (true);
            else return (false);
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public boolean isCNPJ(String CNPJ) {
        if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") ||
                CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333") ||
                CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555") ||
                CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") ||
                CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999") ||
                (CNPJ.length() != 14))
            return(false);

        char dig13, dig14;
        int sm, i, r, num, peso;
        try {
            sm = 0;
            peso = 2;
            for (i=11; i>=0; i--) {
                num = (int)(CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }
            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig13 = '0';
            else dig13 = (char)((11-r) + 48);
            sm = 0;
            peso = 2;
            for (i=12; i>=0; i--) {
                num = (int)(CNPJ.charAt(i)- 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }
            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig14 = '0';
            else dig14 = (char)((11-r) + 48);
            if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13)))
                return(true);
            else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
    }

    public boolean verificarCampoNome(String toString) {
        if (!toString.matches("^[A-Za-z ]*$")) {
            return false;
        }
        if (!verificarCampoVazio(toString)){
            return false;
        }
        return true;
    }

    public boolean verificarCampoSenha(String toString) {
        if (!verificarCampoVazio(toString)){
            return false;
        }
        if (toString.length() < 7) {
            return false;
        }
        return true;
    }

    public boolean verificarCampoEstado(String toString) {
        if (toString.equals("Estado")){
            return false;
        }
        return true;
    }

    public boolean verificarCampoCidade(String toString) {
        if (toString.equals("Cidade")){
            return false;
        }
        return true;
    }

    public boolean verificarCampoBairro(String toString) {
        if (!verificarCampoVazio(toString)){
            return false;
        }
        return true;
    }

    public boolean verificarCampoCEP(String toString) {
        if (!verificarCampoVazio(toString)){
            return false;
        }if (toString.length() < 9){
            return false;
        }
        return true;
    }

    public boolean verificarCampoNumero(String toString) {
        if (!verificarCampoVazio(toString)){
            return false;
        }
        return true;
    }

    public boolean verificarCampoRua(String toString) {
        if (!verificarCampoVazio(toString)){
            return false;
        }
        return true;
    }

    public boolean verificarCampoNascimento(String toString) {
        if (!verificarCampoVazio(toString)){
            return false;
        }
        if(toString.length() < 10){
            return false;
        }
        return true;
    }

    public boolean verificarCampoCelular(String toString) {
        if (!verificarCampoVazio(toString)){
            return false;
        }
        if(toString.length() < 14){
            return false;
        }
        return true;
    }
}