package com.example.cristopher.proyectomoviles.Business;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.cristopher.proyectomoviles.R;

/**
 * A simple {@link Fragment} subclass.
 */

public class FragmentoRegistroServicio extends FragmentoAbsPrincipal implements View.OnClickListener {


    private EditText nombreServicio;
    private EditText descripcionServicio;
    private EditText ubicacionServicio;



    public FragmentoRegistroServicio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista=inflater.inflate(R.layout.fragment_fragmento_registro_servicio, container, false);

        return vista;
    }

    @Override
    public void onClick(View v) {

    }
}
