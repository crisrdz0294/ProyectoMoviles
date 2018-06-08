package com.example.cristopher.proyectomoviles.Business;


import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Selection;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cristopher.proyectomoviles.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_dropdown_item_1line;
import static android.widget.Toast.LENGTH_LONG;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoAyuda extends FragmentoAbsPrincipal implements AdapterView.OnItemSelectedListener {
    private Spinner spinnerPreguntasF ;
    private Spinner spinnerTerminos;
    private Spinner spinnerContatenos;
    private Spinner spinnerInfo;
    private boolean primerAcceso=true;
    String[] spinnerPreguntasFrecuentes ={"Seleccione una Pregunta","Descarga e instalación", "Cuenta y perfil", "Como buscar un lugar","Ver mis servicios"};
    String[] spinnerContactanos ={"Puede ver los Contactos","Contactanos"};
    String[] spinnerPrivacidad ={"Ver Terminos","Terminos y Privacidad"};
    String[] spinnerInformacion ={"Ver Información","Info de la App"};
    public FragmentoAyuda() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_fragmento_ayuda, container, false);
        spinnerPreguntasF = (Spinner) vista.findViewById(R.id.spinnerPreguntas);
        spinnerTerminos = (Spinner) vista.findViewById(R.id.spinnerTerminos);
        spinnerContatenos = (Spinner)  vista.findViewById(R.id.spinnerContactanos);
        spinnerInfo =(Spinner) vista.findViewById(R.id.spinnerinfo);

        final ArrayAdapter<String> adaptadorPreguntas = new ArrayAdapter<String>(getContext() ,android.R.layout.simple_spinner_dropdown_item, spinnerPreguntasFrecuentes) {
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
        final ArrayAdapter<String> adaptadorContactanos = new ArrayAdapter<String>(getContext() ,android.R.layout.simple_spinner_dropdown_item, spinnerContactanos) {
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
        ///Adapatador para cada Spinner Terminos y Privacidad
        final ArrayAdapter<String> adaptadorTerminos = new ArrayAdapter<String>(getContext() ,android.R.layout.simple_spinner_dropdown_item, spinnerPrivacidad) {
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
        ///Adapatador para cada Spinner Terminos y Privacidad
        final ArrayAdapter<String> adaptadorInfo = new ArrayAdapter<String>(getContext() ,android.R.layout.simple_spinner_dropdown_item, spinnerInformacion) {

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

        //////Setea la información al adapatador
        spinnerPreguntasF.setAdapter(adaptadorPreguntas);
        spinnerContatenos.setAdapter(adaptadorContactanos);
        spinnerTerminos.setAdapter(adaptadorTerminos);
        spinnerInfo.setAdapter(adaptadorInfo);
        spinnerPreguntasF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String item="";
                if(primerAcceso){
                    primerAcceso=false;
                }else{
                    item = spinnerPreguntasFrecuentes[position];
                    //Log.v("item", (String) parent.getItemAtPosition(position));

                    if(item=="Descarga e instalación"){
                        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Descarga e Instalacion");
                        builder.setMessage("Desde el celular Acceda a la Play Store y en el buscador escriba el nombre de SAS APP al presionar sobre SAS APP se muestra la información de cuanto espacio requiere para la instalacion.");
                        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(final DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }else if(item=="Cuenta y perfil"){
                        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Cuenta y Perfil");
                        builder.setMessage("Para ver los datos de su perfil: Dirijase a la parte inferior del menu y seleccione Perfil donde puede modificar su datos personales.");
                        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(final DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }else if(item=="Como buscar un lugar"){
                        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Buscar un Lugar");
                        builder.setMessage("Para buscar un Lugar en especifico: Debe dirigigirse al menu y selecciona la opción de Ver Servicios, en la parte superior se muestra la barra de busqueda donde puede escribir el nombre del lugar a buscar.");
                        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(final DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }else if(item=="Ver mis servicios"){
                        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Ver mis servicios");
                        builder.setMessage("En el menu seleccione la opción Ver mis servicios");
                        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(final DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        /////////////SPINNER DE CONTACTATNOS
        spinnerContatenos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String contactanos="";
                if(primerAcceso){
                    primerAcceso=false;
                }else{
                    contactanos = spinnerContactanos[position];
                    //Log.v("item", (String) parent.getItemAtPosition(position));

                    if(contactanos=="Contactanos"){
                        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Contactanos");
                        builder.setMessage("Para mas información escribanos apsas@gmail.com");
                        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(final DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        /////////////SPINNER DE TERMINOS
        spinnerTerminos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String terminos="";
                if(primerAcceso){
                    primerAcceso=false;
                }else{
                    terminos = spinnerPrivacidad[position];
                    //Log.v("item", (String) parent.getItemAtPosition(position));

                    if(terminos=="Terminos y Privacidad"){
                        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Terminos y Privacidad");
                        builder.setMessage("La aplicacion Permite al usuario ARRENDADOR registrar sus servicios y ofrecerlos a los ARRENDATARIOS,por otra parte el ARRENDATARIO puede ingresar y buscar el lugar que mas se ajuste a sus necesidades, a su vez ver la información del lugar.");
                        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(final DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        /////////////SPINNER DE INFO DE LA APP
        spinnerInfo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                String info="";
                if(primerAcceso){
                    primerAcceso=false;
                }else {
                    info = spinnerInformacion[position];
                    //Log.v("item", (String) parent.getItemAtPosition(position));

                    if (info == "Info de la App") {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Info de la App");
                        builder.setMessage("APPSAS" +"\n"+
                                "version 1.0 " +"\n"+
                                "© 2018" +"\n"+
                                " All rights reserved ");
                        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(final DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();

                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        return vista;
    }





    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



}
