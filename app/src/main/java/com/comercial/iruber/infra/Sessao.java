package com.comercial.iruber.infra;
import java.util.HashMap;
import java.util.Map;

import com.comercial.iruber.cliente.dominio.Cliente;


public class Sessao {
        public static final Sessao instance = new Sessao();
        private Cliente cliente;
        private static int ID_LOGADO;

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public int getIt(){
        return ID_LOGADO;
    }
    public void logado(int id){
        ID_LOGADO=id;
    }
    public void deslogar(){
        ID_LOGADO=0;
    }
}


