package com.example.cristopher.proyectomoviles.Business;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cristopher.proyectomoviles.R;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class FragmentoAbsActividadPrincipal extends AppCompatActivity {


    public FragmentoAbsActividadPrincipal() {
        // Required empty public constructor
    }

    protected boolean cambia_fragmento = true;
    public static String nombre;//VARIABLE PARA ALMACENAR EL VALOR DEL NOMBRE Y IR MOSTRANDO ENTRE FRAGMENTS
    public boolean siPuedeCambiarFragmento() { return this.cambia_fragmento; }//RETORNA EL VALOR DEL BOOLEANO PARA EL CAMBIO DE FRAGMENTO

    public void agregueFragmentoAPila(FragmentoAbsPrincipal fragment) {//METODO QUE AGREGA EL FRAGMENTO ACTUAL A LA PILA PARA PROCESAR EL OTRO
        // Initialize fragment transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace with fragment content
        //ft.replace(R.id.Contenedor, fragment);
        ft.replace(R.id.Contenedor,fragment);
        // Animation on change
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        // Clear stack (back button memory)
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {//METODO PARA DEVOLVERSE A UN FRAGMENTO ANTERIOR
        // Check if fragment not doing any BE calling
        if (this.siPuedeCambiarFragmento()) {
            super.onBackPressed();
        }

    }


    public void NuevoFragmento(FragmentoAbsPrincipal fragment){
        (getSupportFragmentManager().beginTransaction()
                .replace(R.id.Contenedor , fragment))
                .commit();
    }



}
