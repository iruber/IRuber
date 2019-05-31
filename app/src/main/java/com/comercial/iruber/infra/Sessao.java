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
    public void setSessao(Usuario user, Context context) {
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
    public static Usuario getSessao(Context context) {
        Gson gson = new Gson();
        String json = getSharedPreferences(context).getString("usuario", "");
        return gson.fromJson(json, Usuario.class);
    }

}
