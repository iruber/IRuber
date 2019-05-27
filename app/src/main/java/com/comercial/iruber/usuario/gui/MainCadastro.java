package com.comercial.iruber.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.comercial.iruber.R;
import com.comercial.iruber.infra.EnumTipo;

public class MainCadastro extends AppCompatActivity implements View.OnClickListener {

    Button btnCadastraCliente, btnCadastrarRestaurante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cadastro);
        btnCadastraCliente =  findViewById(R.id.btnCliente);
        btnCadastraCliente.setOnClickListener(this);
        btnCadastrarRestaurante = findViewById(R.id.btnRestaurante);
        btnCadastrarRestaurante.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            if(v.getId() == R.id.btnCliente){
                Intent intentA = new Intent(this, CadastroUsuarioActivity.class);
                CadastroUsuarioActivity.tipo = EnumTipo.CLIENTE;
                startActivity(intentA);
            }

            if (v.getId() == R.id.btnRestaurante) {
                Intent intentB = new Intent(this, CadastroUsuarioActivity.class);
                CadastroUsuarioActivity.tipo = EnumTipo.RESTAURANTE;
                startActivity(intentB);
            }
    }
}