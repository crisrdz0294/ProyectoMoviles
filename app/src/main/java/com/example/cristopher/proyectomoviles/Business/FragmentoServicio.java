package com.example.cristopher.proyectomoviles.Business;


import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.example.cristopher.proyectomoviles.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoServicio extends FragmentoAbsPrincipal implements View.OnClickListener {

    private RatingBar calificacion;
    private EditText comentario;
    private Button Enviar;
    private  Button Finalizar;


    public FragmentoServicio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista=inflater.inflate(R.layout.fragment_fragmento_servicio, container, false);

        calificacion=vista.findViewById(R.id.R1);
        comentario=vista.findViewById(R.id.comentario);




        Enviar=vista.findViewById(R.id.btnEnviar);
        Enviar.setOnClickListener(this);
        Finalizar=vista.findViewById(R.id.btnComentar);
        Finalizar.setOnClickListener(this);

        return vista;
    }

    @Override
    public void onClick(View v) {

    }
}
