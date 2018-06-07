package com.example.cristopher.proyectomoviles.Business;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cristopher.proyectomoviles.Data.VolleySingleton;
import com.example.cristopher.proyectomoviles.Domain.Constantes;
import com.example.cristopher.proyectomoviles.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoPerfil extends FragmentoAbsPrincipal implements View.OnClickListener{

    private EditText tNombre;
    private EditText tApellidos;
    private EditText tCedula;
    private EditText tCorreo;
    private EditText tTelefono;
    private Button btnActualizar;
    private ImageView imageEditar;
    private EditText tClave;
    private TextView mensaje;


    public FragmentoPerfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista=inflater.inflate(R.layout.fragment_fragmento_perfil, container, false);

        tNombre=vista.findViewById(R.id.nombrePerfil);
        tApellidos=vista.findViewById(R.id.apellidosPerfil);
        tCedula=vista.findViewById(R.id.cedulaPerfil);
        tCorreo=vista.findViewById(R.id.correoPerfil);
        tTelefono=vista.findViewById(R.id.telefonoPerfil);
        imageEditar=vista.findViewById(R.id.editarPerfil);
        btnActualizar=vista.findViewById(R.id.btnActualizarPerfil);
        tClave=vista.findViewById(R.id.clavePerfil);

        SharedPreferences sesion= this.getActivity().getSharedPreferences("Sesion", MODE_PRIVATE);
        tNombre.setText(sesion.getString("nombre",""));
        tApellidos.setText(sesion.getString("apellidos",""));
        tCedula.setText(sesion.getString("cedula",""));
        tCorreo.setText(sesion.getString("correo",""));
        tTelefono.setText(String.valueOf(sesion.getInt("telefono",0)));
        tClave.setText(sesion.getString("clave",""));
        tClave.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

        tNombre.setEnabled(false);
        tApellidos.setEnabled(false);
        tCedula.setEnabled(false);
        tCorreo.setEnabled(false);
        tTelefono.setEnabled(false);
        tClave.setEnabled(false);
        btnActualizar.setEnabled(false);

        btnActualizar.setOnClickListener(this);
        imageEditar.setOnClickListener(this);

        return vista;


    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.editarPerfil){
            tNombre.setEnabled(true);
            tNombre.setFocusable(true);
            tNombre.setCursorVisible(true);
            tNombre.setFocusableInTouchMode(true);
            tNombre.requestFocus();

            tApellidos.setEnabled(true);

            tCorreo.setEnabled(true);
            tClave.setEnabled(true);
            tClave.setTransformationMethod(PasswordTransformationMethod.getInstance());

            tTelefono.setEnabled(true);

            btnActualizar.setEnabled(true);

        }

        if(v.getId()==R.id.btnActualizarPerfil){

            String nombreActualizar=tNombre.getText().toString();
            String cedulaActualizar=tCedula.getText().toString();
            String apellidosActualizar=tApellidos.getText().toString();
            String claveActualizar=tClave.getText().toString();
            String emailActualizar=tCorreo.getText().toString();
            String telefonoActualizar=tTelefono.getText().toString();

            boolean pasa=true;

            if(TextUtils.isEmpty(nombreActualizar)||TextUtils.isEmpty(cedulaActualizar)||TextUtils.isEmpty(apellidosActualizar)||TextUtils.isEmpty(claveActualizar)||
                    TextUtils.isEmpty(emailActualizar)||TextUtils.isEmpty(telefonoActualizar)){

                pasa=false;

                Toast mensaje=Toast.makeText(getContext(),"Hay campos vacios",Toast.LENGTH_LONG);//CREA UN TOAST(NOTIFICACION) QUE HAY CAMPOS VACIOS
                mensaje.setGravity(Gravity.CENTER,0,0);//LE ASIGNA LA POSICION A LA NOTIFICACION
                mensaje.show();//MUESTRA LA NOTIFICACION
            }

            if(pasa){

                String nuevaUrl= Constantes.IP_USUARIOS+"?accion=actualizarUsuario";

                HashMap<String,String> mapaUsuario=new HashMap<String, String>();

                mapaUsuario.put("cedula",cedulaActualizar);
                mapaUsuario.put("nombre",nombreActualizar);
                mapaUsuario.put("apellidos",apellidosActualizar);
                mapaUsuario.put("correo",emailActualizar);
                mapaUsuario.put("telefono",telefonoActualizar);
                mapaUsuario.put("clave",claveActualizar);

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

                                            tNombre.setText("");
                                            tApellidos.setText("");
                                            tCedula.setText("");
                                            tCorreo.setText("");
                                            tTelefono.setText("");
                                            tClave.setText("");

                                         break;

                                         case "2":
                                             Toast mensaje2=Toast.makeText(getContext(),mensaje,Toast.LENGTH_LONG);
                                             mensaje2.setGravity(Gravity.CENTER,0,0);
                                             mensaje2.show();
                                          break;
                                    }

                                }catch (Exception e){

                                }
                            }}, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            }) {

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
                });

            }

        }

    }


}
