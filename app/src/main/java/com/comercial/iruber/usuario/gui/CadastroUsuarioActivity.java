package com.comercial.iruber.usuario.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.comercial.iruber.R;
import com.comercial.iruber.infra.EnumTipo;

public class CadastroUsuarioActivity extends AppCompatActivity {
    public static EnumTipo tipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        TextView documento = findViewById(R.id.inputDocumento);
        if (tipo == EnumTipo.RESTAURANTE){
            documento.setHint("CNPJ");
        }
    }
}
