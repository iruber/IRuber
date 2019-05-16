package com.comercial.iruber.cliente.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import com.comercial.iruber.R;
import com.comercial.iruber.usuario.dominio.Usuario;
import com.comercial.iruber.usuario.negocio.ServicoLogin;

public class LoginUsuarioActivity extends AppCompatActivity {
    private  EditText senhaEd,emailEd;
    private Button buttonLogin, cadastrarCliente;
    ServicoLogin servicoLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

    }

    private void encontrarView(){

        this.emailEd= findViewById(R.id.textSenha);
        this.senhaEd=findViewById(R.id.textSenha);
        this.buttonLogin.findViewById(R.id.btnLogin);
        this.cadastrarCliente.findViewById(R.id.buttonCadastrar);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        cadastrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Referenciar objetos usuário  e senha extraidos do negócio
                TextView txtLogin = (TextView) findViewById(R.id.textLogin);
                TextView  txtSenha= (TextView) findViewById(R.id.textSenha);
                telaCadastroCliente();
            }
        });
    }





    private void alert(String s){
        Toast.makeText(this,s, Toast.LENGTH_LONG).show();
    }



    private void login() {
        ServicoLogin servicoLogin = new ServicoLogin(getApplicationContext());
        String resultado;

        try {
            servicoLogin.loginCliente(this.criarUsuario());
            telaCadastroCliente();
            finish();
        } catch (Exception e){
            e.printStackTrace();
            resultado = e.getMessage();
            Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
        }
    }



    private Usuario criarUsuario(){
        Usuario usuario = new Usuario();

        String email=emailEd.getText().toString();
        String senha=senhaEd.getText().toString();
        usuario.setEmail(email);
        usuario.setSenha(senha);
        return usuario;
    }



    private void telaCadastroCliente() {
        startActivity(new Intent(LoginUsuarioActivity.this, CadastroUsuarioActivity.class));
    }

    private void telaMainCliente(){

    }

}


