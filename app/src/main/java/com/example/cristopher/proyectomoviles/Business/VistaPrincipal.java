package com.example.cristopher.proyectomoviles.Business;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cristopher.proyectomoviles.R;

public class VistaPrincipal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


  public void agregarFragmentoPrincipal(FragmentoAbsPrincipal fragment){
        (getSupportFragmentManager().beginTransaction()
                .replace(R.id.Contenedor2 , fragment))
                .commit();
    }

    public void cerrarSesion(FragmentoAbsPrincipal fragment) {//METODO QUE AGREGA EL FRAGMENTO ACTUAL A LA PILA PARA PROCESAR EL OTRO
        (getSupportFragmentManager().beginTransaction()
                .replace(R.id.Contenedor , fragment))
                .commit();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

       SharedPreferences sesion= getSharedPreferences("Sesion", MODE_PRIVATE);


        String nombre=sesion.getString("nombre","");
        String apellidos=sesion.getString("apellidos","");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        View hView = navigationView.getHeaderView(0);

        TextView nombreHeader = (TextView) hView.findViewById(R.id.nombreUsuario);
        TextView apellidosHeader=(TextView) hView.findViewById(R.id.apellidoUsuario);

        nombreHeader.setText(nombre);
        apellidosHeader.setText(apellidos);

        navigationView.setNavigationItemSelectedListener(this);
        validarTipoUsuario();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.vista_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void validarTipoUsuario(){

        SharedPreferences sesion= getSharedPreferences("Sesion", MODE_PRIVATE);
        int tipoUsuario=sesion.getInt("tipoUsuario",0);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();

        switch (tipoUsuario){

            case 1:

                nav_Menu.findItem(R.id.nav_registrar_servicio).setVisible(false);
                nav_Menu.findItem(R.id.nav_ver_servicios).setVisible(false);
                nav_Menu.findItem(R.id.nav_historial_servicios).setVisible(false);
                break;
            case 2:

                nav_Menu.findItem(R.id.nav_ver_usuarios).setVisible(false);
                nav_Menu.findItem(R.id.nav_historial_servicios).setVisible(false);

                break;

            case 3:
                nav_Menu.findItem(R.id.nav_ver_usuarios).setVisible(false);
                nav_Menu.findItem(R.id.nav_registrar_servicio).setVisible(false);
                nav_Menu.findItem(R.id.nav_ver_servicios).setVisible(false);
                break;
        }


    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_registrar_servicio) {
           agregarFragmentoPrincipal(new FragmentoRegistroServicio());
        } else if (id == R.id.nav_ver_servicios) {
            agregarFragmentoPrincipal(new FragmentoServicio());
        } else if (id == R.id.nav_ver_perfil) {
            agregarFragmentoPrincipal(new FragmentoPerfil());
        } else if (id == R.id.nav_ver_usuarios) {
            agregarFragmentoPrincipal(new FragmentoListaUsuarios());
        } else if (id == R.id.nav_historial_servicios) {
            agregarFragmentoPrincipal(new FragmentoHistorialServicios());
        } else if (id == R.id.nav_cerrar_sesion) {

            AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
            dialogo.setMessage("Esta seguro que desea salir de la aplicacion?");
            dialogo.setCancelable(true);

            dialogo.setPositiveButton(
                    "Si",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            SharedPreferences sesionUsuario= getSharedPreferences("Sesion", MODE_PRIVATE);
                            SharedPreferences.Editor editorUsuario = sesionUsuario.edit();
                            editorUsuario.remove("tipoUsuario");
                            editorUsuario.remove("nombre");
                            editorUsuario.remove("apellidos");
                            editorUsuario.remove("cedula");
                            editorUsuario.clear();
                            editorUsuario.commit();
                            onBackPressed();
                            dialog.cancel();
                        }
                    });

            dialogo.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            dialog.cancel();
                        }
                    });

            AlertDialog alerta = dialogo.create();
            alerta.show();

        }else if (id == R.id.nav_configuracion) {

        }else if (id == R.id.nav_ayuda) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
