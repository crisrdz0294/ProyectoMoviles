package com.example.cristopher.proyectomoviles.Business;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
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
import com.example.cristopher.proyectomoviles.Retrofit.ApiAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoLogin extends FragmentoAbsPrincipal implements  View.OnClickListener {
    private Button Ingresar;
    private EditText correo;
    private EditText clave;
    private Button RegistrarUsuario;
    private Usuario usuarioTemporal;


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


    private void cargarUsuario(String correo, String clave){


        try {
            JSONObject objeto=new JSONObject();

            objeto.put("accion","validarUsuario");
            objeto.put("correo",correo);
            objeto.put("clave",clave);

            Call<Usuario> call= ApiAdapter.getApiServicio().validarUsuario(objeto);

            call.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, retrofit2.Response<Usuario> response) {


                    usuarioTemporal=response.body();
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {

                    Toast mensaje = Toast.makeText(getContext(), "error:"+ t.getMessage(), Toast.LENGTH_LONG);
                    mensaje.setGravity(Gravity.CENTER, 0, 0);
                    mensaje.show();

                }
            });

        }catch (JSONException e){
            Toast mensaje = Toast.makeText(getContext(), "errorJson:"+e.getMessage(), Toast.LENGTH_LONG);
            mensaje.setGravity(Gravity.CENTER, 0, 0);
            mensaje.show();

        }






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
                boolean resultado = false;

                try{
                    cargarUsuario(correo1,clave1);
                    if(TextUtils.equals(usuarioTemporal.getCorreo(),correo1) && TextUtils.equals(usuarioTemporal.getClave(),clave1)){
                        resultado = true;
                    }

                    if(resultado){
                        //LLAMA AL FRAGMENTO
                        correo.setText("");
                        clave.setText("");
                        Intent intent = new Intent(getActivity(), VistaPrincipal.class);
                        getActivity().startActivity(intent);
                    }else {
                        Toast mensaje = Toast.makeText(getContext(), "Datos Incorrectos", Toast.LENGTH_LONG);
                        mensaje.setGravity(Gravity.CENTER, 0, 0);
                        mensaje.show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }






            }

        }

        if(v.getId() == R.id.btnRegistrarUsuario){

            correo.setText("");
            clave.setText("");
            ((FragmentoAbsActividadPrincipal) getActivity()).agregueFragmentoAPila(new FragmentoRegistro());
        }

    }
}
