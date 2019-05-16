package com.comercial.iruber.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.comercial.iruber.R;
import com.comercial.iruber.cliente.gui.LoginUsuarioActivity;


public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIMEOUT = 3000;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            /**
             * Exibindo splash com timer.
             */

            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, LoginUsuarioActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIMEOUT);
    }
}
