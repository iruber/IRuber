package com.comercial.iruber.infra.servicos;

import android.location.Address;
import android.os.AsyncTask;

import com.comercial.iruber.usuario.dominio.Cep;
import com.comercial.iruber.usuario.dominio.Endereco;
import com.comercial.iruber.usuario.gui.CadastroUsuarioActivity;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;

public class CEPRequest extends AsyncTask<Void, Void, Cep> {
    private WeakReference<CadastroUsuarioActivity> activity;

    public CEPRequest(CadastroUsuarioActivity context) {
    }

    public void AddressRequest( CadastroUsuarioActivity activity ){
        this.activity = new WeakReference<>( activity );
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activity.get().lockFields( true );
    }
    @Override
    protected Cep doInBackground(Void... voids) {
        try{
            String jsonString = JsonRequest.request( activity.get().getUriRequest() );
            Gson gson = new Gson();

            /* GERANDO INSTÂNCIA */
            return gson.fromJson(jsonString, Cep.class);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Cep address) {
        super.onPostExecute(address);

        if( activity.get() != null ){
            activity.get().lockFields( false );

            if( address != null ){
                /* ENVIANDO INSTÂNCIA */
                activity.get().setAddressFields( address );
            }
        }
    }
}
