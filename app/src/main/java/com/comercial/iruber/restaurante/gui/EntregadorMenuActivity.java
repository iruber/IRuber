package com.comercial.iruber.restaurante.gui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.comercial.iruber.R;
import com.comercial.iruber.restaurante.gui.fragments.ListaPedidoFragment;
import com.comercial.iruber.restaurante.gui.fragments.PerfilEntregadorFragment;

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
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.placeHolder, new PerfilEntregadorFragment());
        ft.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.pratos) {
            abrirPerfil();
        } else if (id == R.id.ingredientes) {
            abrirListaPedidos();
        }
        drawerLayout.closeDrawers();
        return true;
    }

    public void abrirListaPedidos() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.placeHolder, new ListaPedidoFragment());
        ft.commit();
    }

    public void abrirPerfil() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.placeHolder, new PerfilEntregadorFragment());
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if (navigationView.isShown()) {
            drawerLayout.closeDrawers();
        }
    }

}
