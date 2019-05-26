package com.comercial.iruber.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.comercial.iruber.R;

public class MainLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login_v);
        Button btnloginrestaurante = findViewById(R.id.restauranteButton);
        Button btnlogincliente = findViewById(R.id.clienteButton);
        Button btnloginentregador = findViewById(R.id.entregadorButton);
        Button btnCadastrar;
        btnCadastrar = findViewById(R.id.cadastrarButton);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirMenuCadastro();
            }
        });
    }

    public void abrirMenuCadastro() {
        Intent menuIntent;
        menuIntent = new Intent(getApplicationContext(), MainCadastro.class);
        startActivity(menuIntent);
    }
}
