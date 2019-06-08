package com.comercial.iruber.infra.servicos;



import java.util.InputMismatchException;

public class Validacao {
    public boolean verificarCampoVazio(String campo) {
        return campo.length() != 0;
    }

    public boolean verificarCampoEmail(String email) {
        String regex = "^((?!.*?\\.\\.)[A-Za-z0-9.\\!#\\$\\%\\&\\'*\\+\\-\\" +
                "/\\=\\?\\^_`\\{\\|\\}\\~]+@[A-Za-z0-9]+[A-Za-z0-9\\" +
                "-\\.]+\\.[A-Za-z0-9\\-\\.]+[A-Za-z0-9]+)$";
        return email.matches(regex);
    }

    public boolean validarCPF(String cpf) {
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
        try {
            dig10 = calcularPrimeiroDigitoCpf(cpf, 10, 9);
            // Calculo do 2o. Digito Verificador
            char dig11 = calcularPrimeiroDigitoCpf(cpf, 11, 10);
            // Verifica se os digitos calculados conferem com os digitos informados.
            return (dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10));
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    private char calcularPrimeiroDigitoCpf(String cpf, int i2, int i3) {
        int sm;
        int peso;
        int i;
        int num;
        int r;
        char dig10;// Calculo do 1o. Digito Verificador
        sm = 0;
        peso = i2;
        for (i = 0; i < i3; i++) {
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
        return dig10;
    }

    public boolean validarCNPJ(String cnpj) {
        if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111") ||
                cnpj.equals("22222222222222") || cnpj.equals("33333333333333") ||
                cnpj.equals("44444444444444") || cnpj.equals("55555555555555") ||
                cnpj.equals("66666666666666") || cnpj.equals("77777777777777") ||
                cnpj.equals("88888888888888") || cnpj.equals("99999999999999") ||
                (cnpj.length() != 14))
            return (false);

        char dig13;
        try {
            dig13 = calculoCnpj(cnpj, 11);
            char dig14 = calculoCnpj(cnpj, 12);
            return (dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13));
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    private char calculoCnpj(String cnpj, int i2) {
        int sm;
        int peso;
        int i;
        int num;
        int r;
        char dig13;
        sm = 0;
        peso = 2;
        for (i = i2; i >= 0; i--) {
            num = cnpj.charAt(i) - 48;
            sm = sm + (num * peso);
            peso = peso + 1;
            if (peso == 10)
                peso = 2;
        }
        r = sm % 11;
        if ((r == 0) || (r == 1))
            dig13 = '0';
        else dig13 = (char) ((11 - r) + 48);
        return dig13;
    }

    public boolean verificarCampoNome(String toString) {
        if (!toString.matches("^[A-Za-z ]*$")) {
            return false;
        }
        return verificarCampoVazio(toString);
    }

    public boolean verificarCampoSenha(String toString) {
        if (!verificarCampoVazio(toString)) {
            return false;
        }
        return toString.length() >= 7;
    }

    public boolean verificarCampoEstado(String toString) {
        return !toString.equals("Estado");
    }

    public boolean verificarCampoCidade(String toString) {
        return !toString.equals("Cidade");
    }

    public boolean verificarCampoBairro(String toString) {
        return verificarCampoVazio(toString);
    }

    public boolean verificarCampoCEP(String toString) {
        if (!verificarCampoVazio(toString)) {
            return false;
        }
        return toString.length() >= 9;
    }

    public boolean verificarCampoNumero(String toString) {
        return verificarCampoVazio(toString);
    }

    public boolean verificarCampoRua(String toString) {
        return verificarCampoVazio(toString);
    }

    public boolean verificarCampoNascimento(String toString) {
        if (!verificarCampoVazio(toString)) {
            return false;
        }
        return toString.length() >= 10;
    }

    public boolean verificarCampoCelular(String toString) {
        if (!verificarCampoVazio(toString)) {
            return false;
        }
        return toString.length() >= 14;
    }
}