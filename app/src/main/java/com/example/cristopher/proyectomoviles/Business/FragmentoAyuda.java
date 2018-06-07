package com.example.cristopher.proyectomoviles.Business;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cristopher.proyectomoviles.R;

import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_dropdown_item_1line;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoAyuda extends FragmentoAbsPrincipal implements View.OnClickListener {
    private Spinner spinnerPreguntas ;
    private Spinner spinnerTerminos;
    private Spinner spinnerContatenos;
    private Spinner spinnerInfo;
    String[] spinnerPreguntasFrecuentes ={"Descarga e instalación", "Cuenta y perfil", "Como buscar un lugar","Donde encuentro la información"};
    String[] spinnerContactanos ={"Contactanos"};
    String[] spinnerPrivacidad ={"Terminos y Privacidad"};
    String[] spinnerInformacion ={"Info de la App"};
    public FragmentoAyuda() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_fragmento_ayuda, container, false);
        spinnerPreguntas = vista.findViewById(R.id.spinnerPreguntas);
        spinnerTerminos = vista.findViewById(R.id.spinnerTerminos);
        spinnerContatenos = vista.findViewById(R.id.spinnerContactanos);
        spinnerInfo = vista.findViewById(R.id.spinnerinfo);
        ///Adapatador para cada Spinner Preguntas fecuentes
        final ArrayAdapter<String> adaptadorPreguntas = new ArrayAdapter<String>(
                getContext() ,android.R.layout.simple_dropdown_item_1line, spinnerPreguntasFrecuentes) {

            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the second item from Spinner
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;

                tv.setGravity(Gravity.CENTER);

                if(position==0) {
                    // Set the disable item text color
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        ///Adapatador para cada Spinner Contactanos
        final ArrayAdapter<String> adaptadorContactanos = new ArrayAdapter<String>(
                getContext() ,android.R.layout.simple_dropdown_item_1line, spinnerContactanos) {


        };
        ///Adapatador para cada Spinner Terminos y Privacidad
        final ArrayAdapter<String> adaptadorTerminos = new ArrayAdapter<String>(
                getContext() ,android.R.layout.simple_dropdown_item_1line, spinnerPrivacidad) {


        };
        ///Adapatador para cada Spinner Terminos y Privacidad
        final ArrayAdapter<String> adaptadorInfo = new ArrayAdapter<String>(
                getContext() ,android.R.layout.simple_dropdown_item_1line, spinnerInformacion) {


        };
        //////Setea la información al adapatador
        spinnerPreguntas.setAdapter(adaptadorPreguntas);
        spinnerContatenos.setAdapter(adaptadorContactanos);
        spinnerTerminos.setAdapter(adaptadorTerminos);
        spinnerInfo.setAdapter(adaptadorInfo);

        return vista;
    }




    @Override
    public void onClick(View v) {

    }
}
