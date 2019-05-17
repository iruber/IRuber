package com.comercial.iruber.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.comercial.iruber.R;
import com.comercial.iruber.usuario.dominio.Usuario;
import com.comercial.iruber.usuario.negocio.ServicoLogin;

public class LoginUsuarioActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText senhaEd, emailEd;
    private Button buttonLogin, cadastrarCliente;
    ServicoLogin servicoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario);
        cadastrarCliente =  findViewById(R.id.buttonCadastrarCliente);
        cadastrarCliente.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonCadastrarCliente){
            Intent intent = new Intent(this, MainCadastro.class);
            startActivity(intent);
            finish();
        }

    }

    private void login() {
        ServicoLogin servicoLogin = new ServicoLogin(getApplicationContext());
        String resultado;

        try {
            servicoLogin.loginCliente(this.criarUsuario());
            telaCadastroCliente();
            finish();
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
        startActivity(new Intent(LoginUsuarioActivity.this, CadastroUsuarioActivity.class));
    }

    private void telaMainCliente() {

    }

}


