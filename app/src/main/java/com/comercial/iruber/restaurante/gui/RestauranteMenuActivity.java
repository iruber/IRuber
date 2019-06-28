package com.comercial.iruber.restaurante.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.comercial.iruber.R;
import com.comercial.iruber.infra.Sessao;
import com.comercial.iruber.restaurante.gui.fragments.ListaEntregadorFragment;
import com.comercial.iruber.restaurante.gui.fragments.ListaIngredienteFragment;
import com.comercial.iruber.restaurante.gui.fragments.ListaPratoFragment;
import com.comercial.iruber.usuario.gui.MainLogin;
import com.comercial.iruber.usuario.gui.fragments.PerfilUsuarioFragment;

public class RestauranteMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurante_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        setTitle("Perfil");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameRestaurante, new PerfilUsuarioFragment());
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.restaurante_menu, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.perfilRestaurante) {
            abrirPerfil();
        } if (id == R.id.pratos) {
            abrirListaPratos();
        }else if (id == R.id.ingredientes) {
            abrirListaIngredientes();
        }else if (id == R.id.sair) {
            finalizarSessao();
        }else if(id == R.id.entregadores){
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public void finalizarSessao() {
        Sessao sessao = new Sessao();
        sessao.clear(getApplicationContext());
        Intent login = new Intent(RestauranteMenuActivity.this, MainLogin.class);
        startActivity(login);
        finish();
    }

    public void abrirPerfil() {
        setTitle("Perfil");
        Fragment fragment = new PerfilUsuarioFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameRestaurante, fragment);
        ft.commit();
    }

    public void abrirListaIngredientes() {
        setTitle("Ingredientes");
        Fragment fragment = new ListaIngredienteFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameRestaurante, fragment);
        ft.commit();
    }

    public void abrirListaPratos() {
        setTitle("Pratos");
        Fragment fragment = new ListaPratoFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameRestaurante, fragment);
        ft.commit();
    }
}
