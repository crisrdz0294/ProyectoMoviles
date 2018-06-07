package com.example.cristopher.proyectomoviles.Business;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cristopher.proyectomoviles.Data.VolleySingleton;
import com.example.cristopher.proyectomoviles.Domain.Constantes;
import com.example.cristopher.proyectomoviles.Domain.Usuario;
import com.example.cristopher.proyectomoviles.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoLogin extends FragmentoAbsPrincipal implements  View.OnClickListener {
    private Button Ingresar;
    private EditText correo;
    private EditText clave;
    private Button RegistrarUsuario;




    public FragmentoLogin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = (View)  inflater.inflate(R.layout.fragment_fragmento_login, container, false);
        correo = (EditText) vista.findViewById(R.id.E1);
        clave = (EditText) vista.findViewById(R.id.P1);
        Ingresar = (Button) vista.findViewById(R.id.btnIngresar);
        RegistrarUsuario = (Button) vista.findViewById(R.id.btnRegistrarUsuario);

        Ingresar.setOnClickListener(this);
        RegistrarUsuario.setOnClickListener(this);
        return vista;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnIngresar) {
            Boolean pasa = true;
            String correo1 = correo.getText().toString();
            String clave1 = clave.getText().toString();

            if(TextUtils.isEmpty(correo1) || TextUtils.isEmpty(clave1)){
                pasa=false;
                Toast mensaje = Toast.makeText(getContext(), "Datos Vacios", Toast.LENGTH_LONG);
                mensaje.setGravity(Gravity.CENTER, 0, 0);
                mensaje.show();
            }


            if (pasa) {
                final Usuario[] usuario = {null};

                String nuevaUrl=Constantes.IP_USUARIOS+"?accion=validarUsuario&correo="+correo1+"&clave="+clave1;

                VolleySingleton.getInstance(
                        getActivity()).addToRequestQueue(
                        new JsonObjectRequest(Request.Method.GET, nuevaUrl, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                Gson gson = new Gson();
                                try{

                                    String estadoRespuesta=response.getString("estado");//recibe la respuesta del script

                                    switch (estadoRespuesta){

                                        case "1":
                                            JSONObject object = response.getJSONObject("usuario");
                                            usuario[0] = gson.fromJson(object.toString(), Usuario.class);

                                            boolean result= false;

                                            if(TextUtils.equals(usuario[0].getCorreo(),correo.getText()) && TextUtils.equals(usuario[0].getClave(),clave.getText())){
                                                result = true;

                                            }

                                            if(result){
                                                //LLAMA AL FRAGMENTO
                                                correo.setText("");
                                                clave.setText("");

                                                SharedPreferences pref = getContext().getSharedPreferences("Sesion", MODE_PRIVATE);
                                                SharedPreferences.Editor editor = pref.edit();

                                                editor.putInt("tipoUsuario",usuario[0].getTipoUsuario() );
                                                editor.putString("nombre", usuario[0].getNombre());
                                                editor.putString("apellidos",usuario[0].getApellidos());
                                                editor.putString("cedula",usuario[0].getCedula());
                                                editor.putString("correo",usuario[0].getCorreo());
                                                editor.putInt("telefono",usuario[0].getTelefono());
                                                editor.putString("clave",usuario[0].getClave());
                                                editor.commit();

                                                Intent intent = new Intent(getActivity(), VistaPrincipal.class);
                                                getActivity().startActivity(intent);
                                            }else {
                                                Toast mensaje = Toast.makeText(getContext(), "Datos Incorrectos", Toast.LENGTH_LONG);
                                                mensaje.setGravity(Gravity.CENTER, 0, 0);
                                                mensaje.show();
                                            }

                                            break;
                                        case "2":

                                            Toast mensaje=Toast.makeText(getContext(),"Fallo al obtener los usuarios",Toast.LENGTH_LONG);//CREA UN TOAST(NOTIFICACION) QUE HAY CAMPOS VACIOS
                                            mensaje.setGravity(Gravity.CENTER,0,0);//LE ASIGNA LA POSICION A LA NOTIFICACION
                                            mensaje.show();//MUESTRA LA NOTIFICACION
                                            break;
                                    }


                                }catch (Exception e){

                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast mensaje=Toast.makeText(getContext(),"Error:"+error.getMessage(),Toast.LENGTH_LONG);//CREA UN TOAST(NOTIFICACION) QUE HAY CAMPOS VACIOS
                                mensaje.setGravity(Gravity.CENTER,0,0);//LE ASIGNA LA POSICION A LA NOTIFICACION
                                mensaje.show();//MUESTRA LA NOTIFICACION

                            }
                        }

                        ));

            }

        }

        if(v.getId() == R.id.btnRegistrarUsuario){

            correo.setText("");
            clave.setText("");
            ((FragmentoAbsActividadPrincipal) getActivity()).agregueFragmentoAPila(new FragmentoRegistro());
        }

    }






}
