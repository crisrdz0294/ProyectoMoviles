package com.example.cristopher.proyectomoviles.Business;


import android.annotation.SuppressLint;
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
@SuppressLint("ValidFragment")
public class ActualizarUsuario extends FragmentoAbsPrincipal implements View.OnClickListener {


    private EditText nombreActualizar;
    private EditText cedulaActualizar;
    private EditText apellidosActualizar;
    private EditText emailActualizar;
    private EditText telefonoActualizar;
    private EditText claveActualizar;
    private EditText confirmarClaveActualizar;
    private Button btnActualizar;
    private Spinner spinnerActualizar;

    private Usuario usuario;

    public ActualizarUsuario() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista=inflater.inflate(R.layout.fragment_actualizar_usuario, container, false);


        cedulaActualizar=vista.findViewById(R.id.cedulaActualizar);
        nombreActualizar=vista.findViewById(R.id.nombreActualizar);
        apellidosActualizar=vista.findViewById(R.id.apellidosActualizar);
        claveActualizar=vista.findViewById(R.id.claveActualizar);
        confirmarClaveActualizar=vista.findViewById(R.id.confirmarClaveActualizar);
        emailActualizar=vista.findViewById(R.id.emailActualizar);
        telefonoActualizar=vista.findViewById(R.id.telefonoActualizar);
        spinnerActualizar=(Spinner) vista.findViewById(R.id.spinnerUsuariosActualizar);

        btnActualizar=vista.findViewById(R.id.btnActualizarUsuario);
        btnActualizar.setOnClickListener(this);
        cargarDatos();
        return vista;
    }

    private void cargarDatos(){

        try{

            Bundle recibir = this.getArguments();
            String[] usuario = null;
            if(recibir != null){
                usuario = recibir.getString("usuario").split(",");
            }

            cedulaActualizar.setText(usuario[0]);
            nombreActualizar.setText(usuario[1]);
            apellidosActualizar.setText(usuario[2]);
            emailActualizar.setText(usuario[4]);
            claveActualizar.setText(usuario[3]);
            confirmarClaveActualizar.setText(usuario[3]);
            telefonoActualizar.setText(usuario[5]);
            cedulaActualizar.setEnabled(false);


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

            spinnerActualizar.setAdapter(adaptadorUsuarios);
            int tipo = Integer.parseInt(usuario[6]);

            switch (tipo){
                case 2:
                    spinnerActualizar.setSelection(1);
                    break;
                case 3:
                    spinnerActualizar.setSelection(2);
                    break;

            }



        }catch(Exception e){


        }



    }

    @Override
    public void onClick(View v) {

        boolean pasa=true;

        if(v.getId()==R.id.btnActualizarUsuario){

            String nombre1=nombreActualizar.getText().toString();
            String cedula1=cedulaActualizar.getText().toString();
            String apellidos1=apellidosActualizar.getText().toString();
            String clave1=claveActualizar.getText().toString();
            String confirmarClave1=confirmarClaveActualizar.getText().toString();
            String email1=emailActualizar.getText().toString();
            String telefono1=telefonoActualizar.getText().toString();
            String tipoUsuario=spinnerActualizar.getSelectedItem().toString();

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

                String nuevaUrl= Constantes.IP_USUARIOS+"?accion=actualizarUsuario";

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

                                            nombreActualizar.setText("");
                                            cedulaActualizar.setEnabled(true);
                                            cedulaActualizar.setText("");
                                            apellidosActualizar.setText("");
                                            claveActualizar.setText("");
                                            confirmarClaveActualizar.setText("");
                                            emailActualizar.setText("");
                                            telefonoActualizar.setText("");

                                            ((VistaPrincipal)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor2, new FragmentoListaUsuarios(),"fragmentoListaUsuarios").addToBackStack(null).commit();
                                            break;
                                            case "2":
                                                Toast mensaje2=Toast.makeText(getContext(),mensaje,Toast.LENGTH_LONG);
                                                mensaje2.setGravity(Gravity.CENTER,0,0);
                                                mensaje2.show();
                                                break;
                                    }


                                } catch(JSONException e){

                                }

                            }}, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast mensaje1=Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG);
                                    mensaje1.setGravity(Gravity.CENTER,0,0);
                                    mensaje1.show();
                                }
                        }){
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
