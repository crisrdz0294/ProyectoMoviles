package com.example.cristopher.proyectomoviles.Business;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoListaUsuarios extends FragmentoAbsPrincipal{


    private ListView listViewUsuarios;
    private List<Usuario> listaUsuarios;

    private View vista;


    public FragmentoListaUsuarios() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        vista =inflater.inflate(R.layout.fragment_fragmento_lista_usuarios, container, false);
        listViewUsuarios=vista.findViewById(R.id.listUsuarios);
        listaUsuarios=new ArrayList<>();

        return vista;
    }


    private void procesarRespuestaUsuarios(JSONObject respuesta){
        Gson gson = new Gson();
        try{

            String estadoRespuesta=respuesta.getString("estado");//recibe la respuesta del script

            switch (estadoRespuesta){

                case "1"://obtuvo los usuarios
                    JSONArray usuariosJson=respuesta.getJSONArray("usuarios");
                   Usuario[] usuarios=gson.fromJson(usuariosJson.toString(),Usuario[].class);
                    UsuariosAdapter usuariosAdapter=new UsuariosAdapter(getContext(),R.layout.list_view_usuarios, Arrays.asList(usuarios));
                    listViewUsuarios.setAdapter(usuariosAdapter);
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





}
