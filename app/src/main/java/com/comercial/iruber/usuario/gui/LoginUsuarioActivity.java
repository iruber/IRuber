package com.comercial.iruber.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.comercial.iruber.R;
import com.comercial.iruber.cliente.gui.ClienteMenuActivity;
import com.comercial.iruber.infra.EnumTipo;
import com.comercial.iruber.restaurante.gui.RestauranteMenuActivity;
import com.comercial.iruber.usuario.dominio.Usuario;
import com.comercial.iruber.usuario.negocio.ServicoLogin;

public class LoginUsuarioActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText senhaEd, emailEd;
    private Button buttonLogin, cadastrarUsuario;
    public static EnumTipo tipoUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario);
        cadastrarUsuario = findViewById(R.id.buttonCadastrarUsuario);
        cadastrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telaCadastroCliente();
            }
        });
        encontrarView();
    }


    private void encontrarView() {
        this.emailEd = (EditText) findViewById(R.id.textLogin);
        this.senhaEd = (EditText) findViewById(R.id.textSenha);
        this.buttonLogin = (Button) findViewById(R.id.btnLogin);
        this.cadastrarUsuario = (Button) findViewById(R.id.buttonCadastrarUsuario);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonCadastrarUsuario) {
            Intent intent = new Intent(this, MainCadastro.class);
            startActivity(intent);
            finish();
        }
    }

    private void login() {
        ServicoLogin servicoLogin = new ServicoLogin(getApplicationContext());
        String resultado;
        String SUCESSO = "Logado com Sucesso";
        try {
            if (tipoUsuario == EnumTipo.RESTAURANTE) {
                servicoLogin.loginCliente(this.criarUsuario());
                Intent intent = new Intent(this, RestauranteMenuActivity.class);
                startActivity(intent);
            } else {
                servicoLogin.loginCliente(this.criarUsuario());
                Intent intent = new Intent(this, ClienteMenuActivity.class);
                startActivity(intent);
            }

            finish();
            Toast.makeText(getApplicationContext(), SUCESSO, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            resultado = e.getMessage();
            Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
        }
    }

    private Usuario criarUsuario() {
        Usuario usuario = new Usuario();
        String email = emailEd.getText().toString();
        String senha = senhaEd.getText().toString();
        usuario.setEmail(email);
        usuario.setSenha(senha);
        return usuario;
    }

    private void telaCadastroCliente() {
        startActivity(new Intent(LoginUsuarioActivity.this, MainCadastro.class));
    }
}


