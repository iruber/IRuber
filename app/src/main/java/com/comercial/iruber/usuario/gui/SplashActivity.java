package com.comercial.iruber.usuario.gui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.comercial.iruber.R;
import com.comercial.iruber.infra.Sessao;
import com.comercial.iruber.usuario.dominio.Usuario;


public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIMEOUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Usuario obj = Sessao.getSession(getBaseContext());
        new Handler().postDelayed(new Runnable() {
            /**
             * Exibindo splash com timer.
             */
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainLogin.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIMEOUT);
    }
}
