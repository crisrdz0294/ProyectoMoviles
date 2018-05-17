package com.example.cristopher.proyectomoviles;


import android.content.Intent;
import android.os.Bundle;

import com.example.cristopher.proyectomoviles.Business.FragmentoAbsActividadPrincipal;
import com.example.cristopher.proyectomoviles.Business.FragmentoLogin;
import com.example.cristopher.proyectomoviles.Business.FragmentoRegistro;
import com.example.cristopher.proyectomoviles.Business.VistaPrincipal;

public class MainActivity extends FragmentoAbsActividadPrincipal {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState==null) {

            NuevoFragmento(new FragmentoLogin());
        }
    }
}
