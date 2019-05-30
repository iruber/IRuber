package com.comercial.iruber.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.comercial.iruber.R;
import com.comercial.iruber.infra.EnumTipo;
public class MainLogin extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login_v);
        Button btnloginrestaurante = findViewById(R.id.restauranteButton);
        btnloginrestaurante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirLoginRestaurante();
            }
        });
        Button btnlogincliente = findViewById(R.id.clienteButton);
        btnlogincliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirLoginCliente();
            }
        });
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
    public void abrirLoginRestaurante() {
        Intent menuIntent;
        menuIntent = new Intent(getApplicationContext(), LoginUsuarioActivity.class);
        LoginUsuarioActivity.tipoUsuario = EnumTipo.RESTAURANTE;
        startActivity(menuIntent);
    }
    public void abrirLoginCliente() {
        Intent menuIntent;
        menuIntent = new Intent(getApplicationContext(), LoginUsuarioActivity.class);
        LoginUsuarioActivity.tipoUsuario = EnumTipo.CLIENTE;
        startActivity(menuIntent);
    }
    public void abrirMenuCadastro() {
        Intent menuIntent;
        menuIntent = new Intent(getApplicationContext(), MainCadastro.class);
        startActivity(menuIntent);
    }
}
