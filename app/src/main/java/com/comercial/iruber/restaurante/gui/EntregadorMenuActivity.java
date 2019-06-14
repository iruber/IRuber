package com.comercial.iruber.restaurante.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.comercial.iruber.R;
import com.comercial.iruber.infra.Sessao;
import com.comercial.iruber.restaurante.gui.fragments.ListaPedidoFragment;
import com.comercial.iruber.usuario.gui.MainLogin;
import com.comercial.iruber.usuario.gui.fragments.PerfilUsuarioFragment;

public class EntregadorMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entregador_menu);
        navigationView = (NavigationView) findViewById(R.id.navViewEntregador);
        navigationView.setNavigationItemSelectedListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarEntregador);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayoutEntregador);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
//        setTitle("Perfil");
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.placeHolder, new PerfilUsuarioFragment());
//        ft.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.perfil) {
            abrirPerfil();
        } else if (id == R.id.pedidos) {
            abrirListaPedidos();
        }else if (id == R.id.sair) {
            finalizarSessao();
        }
        drawerLayout.closeDrawers();
        return true;
    }

    public void abrirPerfil() {
        setTitle("Perfil");
        Fragment fragment = new PerfilUsuarioFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameRestaurante, fragment);
        ft.commit();
    }

    public void abrirListaPedidos() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.placeHolder, new ListaPedidoFragment());
        ft.commit();
    }

    public void finalizarSessao() {
        Sessao sessao = new Sessao();
        sessao.clear(getApplicationContext());
        Intent login = new Intent(EntregadorMenuActivity.this, MainLogin.class);
        startActivity(login);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (navigationView.isShown()) {
            drawerLayout.closeDrawers();
        }
    }

}
