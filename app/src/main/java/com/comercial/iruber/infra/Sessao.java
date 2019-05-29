package com.comercial.iruber.infra;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.comercial.iruber.cliente.dominio.Cliente;
import com.comercial.iruber.restaurante.dominio.Restaurante;
import com.comercial.iruber.usuario.dominio.Usuario;
import com.google.gson.Gson;


public class Sessao {
    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public void editSessao(Usuario user, Context context) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString("usuario", json);
        editor.commit();
    }

    public void clear(Context context) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.clear();
        editor.commit();
    }

    public void editSessaoCliente(Cliente user, Context context) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString("Cliente", json);
        editor.commit();
    }

    public void editSessaoFornecedor(Restaurante user, Context context) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString("Fornecedor", json);
        editor.commit();
    }

    public static Usuario getSession(Context context) {
        Gson gson = new Gson();
        String json = getSharedPreferences(context).getString("usuario", "");
        return gson.fromJson(json, Usuario.class);
    }

    public static Cliente getSessionCliente(Context context) {
        Gson gson = new Gson();
        String json = getSharedPreferences(context).getString("Cliente", "");
        return gson.fromJson(json, Cliente.class);
    }

    public static Restaurante getSessionFornecedor(Context context) {
        Gson gson = new Gson();
        String json = getSharedPreferences(context).getString("Fornecedor", "");
        return gson.fromJson(json, Restaurante.class);
    }

}
