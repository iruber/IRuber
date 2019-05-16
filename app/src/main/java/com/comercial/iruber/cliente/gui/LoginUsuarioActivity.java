package com.comercial.iruber.cliente.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.comercial.iruber.R;

public class LoginUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtLogin = (TextView) findViewById(R.id.textLogin);
                TextView  txtSenha= (TextView) findViewById(R.id.textSenha);

                String login = txtLogin.getText().toString();
                String senha = txtSenha.getText().toString();
                if(login.equals("martaTeste")&&senha.equals("123")){
                    alert("login realizado com sucesso!");
                }else{
                    alert("login e/ou senha inválido!");
                };

            }

        });

    }
    private void alert(String s){
        Toast.makeText(this,s, Toast.LENGTH_LONG).show();
    }
}
