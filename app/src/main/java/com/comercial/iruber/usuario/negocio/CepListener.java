package com.comercial.iruber.usuario.negocio;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;

import com.comercial.iruber.infra.servicos.CEPRequest;
import com.comercial.iruber.usuario.gui.CadastroUsuarioActivity;

public class CepListener  implements TextWatcher {
    private Context context;

    public CepListener(Context context ){
        this.context = context;
    }

    @Override
    public void afterTextChanged(Editable editable) {
        String zipCode = editable.toString();

        if( zipCode.length() == 8 ){
            new CEPRequest( (CadastroUsuarioActivity) context ).execute();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
}
