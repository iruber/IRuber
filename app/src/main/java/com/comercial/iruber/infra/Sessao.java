package com.comercial.iruber.infra;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.comercial.iruber.cliente.dominio.Cliente;
import com.comercial.iruber.pedido.dominio.Pedido;
import com.comercial.iruber.restaurante.dominio.Entregador;
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
    public void editSessaoPedido(Pedido pedido, Context context) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        Gson gson = new Gson();
        String json = gson.toJson(pedido);
        editor.putString("pedido", json);
        editor.commit();
    }
    public void editSessaoRestaurante(Restaurante user, Context context) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString("Restaurante", json);
        editor.commit();
    }

    public void editSessaoEntregador(Entregador user, Context context) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString("Entregador", json);
        editor.commit();
    }

    public static Usuario getSessao(Context context) {
        Gson gson = new Gson();
        String json = getSharedPreferences(context).getString("usuario", "");
        return gson.fromJson(json, Usuario.class);
    }

    public static Cliente getSessaoCliente(Context context) {
        Gson gson = new Gson();
        String json = getSharedPreferences(context).getString("Cliente", "");
        return gson.fromJson(json, Cliente.class);
    }

    public static Restaurante getSessaoRestaurante(Context context) {
        Gson gson = new Gson();
        String json = getSharedPreferences(context).getString("Restaurante", "");
        return gson.fromJson(json, Restaurante.class);
    }

    public static Pedido getSessaoPedido(Context context) {
        Gson gson = new Gson();
        String json = getSharedPreferences(context).getString("pedido", "");
        return gson.fromJson(json, Pedido.class);
    }

    public static Entregador getSessaoEntregador(Context context) {
        Gson gson = new Gson();
        String json = getSharedPreferences(context).getString("Entregador", "");
        return gson.fromJson(json, Entregador.class);
    }

}
