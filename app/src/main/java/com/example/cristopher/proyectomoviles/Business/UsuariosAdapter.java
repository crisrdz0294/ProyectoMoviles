package com.example.cristopher.proyectomoviles.Business;

import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
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
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuariosAdapter extends ArrayAdapter<Usuario> {
    Context contexto;
    int layoutVistaUsuarios;
    List<Usuario> listaUsuarios;

    public UsuariosAdapter(Context contexto,int layoutUsuarios,List<Usuario> listaUsuarios){
        super(contexto, layoutUsuarios, listaUsuarios);

        this.contexto = contexto;
        this.layoutVistaUsuarios = layoutUsuarios;
        this.listaUsuarios = listaUsuarios;

    }

    public View getView(int posicion, @Nullable View convertView, @NonNull ViewGroup parent){

        LayoutInflater inflater = LayoutInflater.from(contexto);
        View vista = inflater.inflate(layoutVistaUsuarios, null);

        final Usuario usuario=listaUsuarios.get(posicion);

        TextView textViewNombre= vista.findViewById(R.id.textViewNombre);
        TextView textViewApellidos = vista.findViewById(R.id.textViewApellidos);
        TextView textViewTelefono = vista.findViewById(R.id.textViewTelefono);
        TextView textViewCorreo = vista.findViewById(R.id.textViewCorreo);


        textViewNombre.setText(usuario.getNombre());
        textViewApellidos.setText(usuario.getApellidos());
        textViewTelefono.setText(String.valueOf(usuario.getTelefono()));
        textViewCorreo.setText(usuario.getCorreo());

        Button btnEditar = vista.findViewById(R.id.btnEditar);
        Button btnEliminar =vista.findViewById(R.id.btnEliminar);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuarios = usuario.getCedula()+","+usuario.getNombre()+","+usuario.getApellidos()+","+usuario.getClave()+","
                        +usuario.getCorreo()+","+usuario.getTelefono()+","+usuario.getTipoUsuario();

               Bundle enviar = new Bundle();
                enviar.putString("usuario",usuarios);

                ActualizarUsuario actualizarUsuario = new ActualizarUsuario();
                actualizarUsuario.setArguments(enviar);

                ((VistaPrincipal)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor2, actualizarUsuario,"actualizarUsuario").addToBackStack(null).commit();
            }


        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
                builder.setTitle("Esta seguro de realizar esta acción");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {
                        String nuevaUrl= Constantes.IP_USUARIOS+"?accion=eliminarUsuario";

                        HashMap<String,String> mapaUsuario=new HashMap<String, String>();

                        mapaUsuario.put("cedula",usuario.getCedula());

                        JSONObject jsonObjeto=new JSONObject(mapaUsuario);

                        VolleySingleton.getInstance(contexto).addToRequestQueue(new JsonObjectRequest(Request.Method.POST, nuevaUrl, jsonObjeto, new Response.Listener<JSONObject>() {
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
                                            dialog.dismiss();

                                            ((VistaPrincipal)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor2, new FragmentoListaUsuarios(),"fragmentoListaUsuarios").addToBackStack(null).commit();

                                            break;
                                        case "2":
                                            Toast mensaje2=Toast.makeText(getContext(),mensaje,Toast.LENGTH_LONG);
                                            mensaje2.setGravity(Gravity.CENTER,0,0);
                                            mensaje2.show();
                                            dialog.dismiss();

                                            break;
                                    }
                                }catch (Exception e){

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
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });



        return vista;
    }


}
