package com.example.cristopher.proyectomoviles.Business;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cristopher.proyectomoviles.Data.VolleySingleton;
import com.example.cristopher.proyectomoviles.Domain.Constantes;
import com.example.cristopher.proyectomoviles.Domain.Usuario;
import com.example.cristopher.proyectomoviles.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoRegistro extends FragmentoAbsPrincipal implements View.OnClickListener{


    private EditText nombre;
    private EditText cedula;
    private EditText apellidos;
    private EditText email;
    private EditText telefono;
    private EditText clave;
    private EditText confirmarClave;
    private Button btnRegistrar;
    private Spinner spinner;


    public FragmentoRegistro() {
        // Required empty public constructor
    }



    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {

            //additional code
        } else {
            getFragmentManager().popBackStack();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View vista=inflater.inflate(R.layout.fragment_fragmento_registro, container, false);
       cedula=vista.findViewById(R.id.cedula);
        nombre=vista.findViewById(R.id.nombre);
        apellidos=vista.findViewById(R.id.apellidos);
        clave=vista.findViewById(R.id.clave);
        confirmarClave=vista.findViewById(R.id.confirmarClave);
        email=vista.findViewById(R.id.email);
        telefono=vista.findViewById(R.id.telefono);
        spinner=(Spinner) vista.findViewById(R.id.spinnerUsuarios);

        String[] usuarios={"Seleccione un tipo de usuario","Arrendador","Arrendatario"};

        final ArrayAdapter<String> adaptadorUsuarios = new ArrayAdapter<String>(
                getContext() ,android.R.layout.simple_spinner_item, usuarios) {

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

        spinner.setAdapter(adaptadorUsuarios);

        btnRegistrar=vista.findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(this);

       return vista;
    }

    private void procesarRespuesta(JSONObject respuesta){

        try{

            String estado=respuesta.getString("estado");
            String mensaje=respuesta.getString("mensaje");

            switch (estado) {
                case "1":
                    Toast mensaje1=Toast.makeText(getContext(),mensaje,Toast.LENGTH_LONG);
                    mensaje1.setGravity(Gravity.CENTER,0,0);
                    mensaje1.show();
                break;
                case "2":
                    Toast mensaje2=Toast.makeText(getContext(),mensaje,Toast.LENGTH_LONG);
                    mensaje2.setGravity(Gravity.CENTER,0,0);
                    mensaje2.show();
                    break;
            }


        }
        catch(JSONException e){


        }
    }

    @Override
    public void onClick(View v) {

        boolean pasa=true;

        if(v.getId()==R.id.btnRegistrar){

            String nombre1=nombre.getText().toString();
            String cedula1=cedula.getText().toString();
            String apellidos1=apellidos.getText().toString();
            String clave1=clave.getText().toString();
            String confirmarClave1=confirmarClave.getText().toString();
            String email1=email.getText().toString();
            String telefono1=telefono.getText().toString();
            String tipoUsuario=spinner.getSelectedItem().toString();

            if(TextUtils.isEmpty(nombre1)||TextUtils.isEmpty(cedula1)||TextUtils.isEmpty(apellidos1)||TextUtils.isEmpty(clave1)||
                    TextUtils.isEmpty(confirmarClave1)||TextUtils.isEmpty(email1)||TextUtils.isEmpty(telefono1)){

                pasa=false;

                Toast mensaje=Toast.makeText(getContext(),"Hay campos vacios",Toast.LENGTH_LONG);//CREA UN TOAST(NOTIFICACION) QUE HAY CAMPOS VACIOS
                mensaje.setGravity(Gravity.CENTER,0,0);//LE ASIGNA LA POSICION A LA NOTIFICACION
                mensaje.show();//MUESTRA LA NOTIFICACION
            }else{

                if(!clave1.equals(confirmarClave1)){//COMPARA SI AMBAS CLAVES NO COINCICEN
                    Toast mensaje=Toast.makeText(getContext(),"Las claves no coinciden",Toast.LENGTH_LONG);
                    mensaje.setGravity(Gravity.CENTER,0,0);
                    mensaje.show();
                    pasa=false;
                }
            }

            if(pasa){

                String nuevaUrl=Constantes.IP_USUARIOS+"?accion=insertarUsuario";

                HashMap<String,String> mapaUsuario=new HashMap<String, String>();

                mapaUsuario.put("cedula",cedula1);
                mapaUsuario.put("nombre",nombre1);
                mapaUsuario.put("apellidos",apellidos1);
                mapaUsuario.put("correo",email1);
                mapaUsuario.put("telefono",telefono1);
                mapaUsuario.put("clave",clave1);
                mapaUsuario.put("tipo",tipoUsuario);

                JSONObject jsonObjeto=new JSONObject(mapaUsuario);

                VolleySingleton.getInstance(getActivity()).addToRequestQueue(new JsonObjectRequest(
                        Request.Method.POST, nuevaUrl, jsonObjeto, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        try{

                            String estado=response.getString("estado");
                            String mensaje=response.getString("mensaje");

                            switch (estado) {
                                case "1":
                                    Toast mensaje1=Toast.makeText(getContext(),mensaje,Toast.LENGTH_LONG);
                                    mensaje1.setGravity(Gravity.CENTER,0,0);
                                    mensaje1.show();

                                    nombre.setText("");
                                    cedula.setText("");
                                    apellidos.setText("");
                                    clave.setText("");
                                    confirmarClave.setText("");
                                    email.setText("");
                                    telefono.setText("");
                                    onBackPressed();

                                    break;
                                case "2":
                                    Toast mensaje2=Toast.makeText(getContext(),mensaje,Toast.LENGTH_LONG);
                                    mensaje2.setGravity(Gravity.CENTER,0,0);
                                    mensaje2.show();
                                    break;
                            }


                        }
                        catch(JSONException e){


                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast mensaje1=Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG);
                        mensaje1.setGravity(Gravity.CENTER,0,0);
                        mensaje1.show();
                    }
                }

                ){

                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json; charset=utf-8");
                        headers.put("Accept", "application/json");
                        return headers;
                    }

                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8" + getParamsEncoding();
                    }
                                                                             }

                );




            }
        }

    }
}
