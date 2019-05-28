package com.comercial.iruber.restaurante.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.comercial.iruber.R;
import com.comercial.iruber.restaurante.dominio.Restaurante;
import com.comercial.iruber.restaurante.dominio.Empresa;
import com.comercial.iruber.usuario.negocio.ServicoCadastrar;
import com.comercial.iruber.usuario.persistencia.UsuarioDAO;


public class CadastroRestauranteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_restaurante);
    }
}
