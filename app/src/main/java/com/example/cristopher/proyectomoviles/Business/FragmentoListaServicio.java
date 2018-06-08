package com.example.cristopher.proyectomoviles.Business;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cristopher.proyectomoviles.Data.VolleySingleton;
import com.example.cristopher.proyectomoviles.Domain.Constantes;
import com.example.cristopher.proyectomoviles.Domain.Servicio;
import com.example.cristopher.proyectomoviles.Domain.Usuario;
import com.example.cristopher.proyectomoviles.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoListaServicio extends FragmentoAbsPrincipal {

    private ListView listViewServicios;
    private List<Usuario> listaServicios;

    private View vista;
    private ArrayList<Servicio> servicios = new ArrayList<>();


    public FragmentoListaServicio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vista =inflater.inflate(R.layout.fragment_fragmento_lista_servicio, container, false);
        listViewServicios=vista.findViewById(R.id.listListaServicios);
        listaServicios=new ArrayList<>();
        cargarDatosAdaptador();
        return vista;
    }

    public void cargarDatosAdaptador(){

        String nuevaUrl= Constantes.IP_SERVICIOS+"?accion=verTodosServicios";




        VolleySingleton.getInstance(
                getActivity()).addToRequestQueue(
                new JsonObjectRequest(Request.Method.GET, nuevaUrl, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        procesarRespuestaServicio(response);
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

    private void procesarRespuestaServicio(JSONObject respuesta){
        Gson gson = new Gson();
        try{

            String estadoRespuesta=respuesta.getString("estado");//recibe la respuesta del script

            switch (estadoRespuesta){

                case "1"://obtuvo los usuarios


                    try{
                        JSONArray usuariosJson=respuesta.getJSONArray("servicios");
                        Servicio[] servicios = new Servicio[usuariosJson.length()];

                            for(int i=0;i<usuariosJson.length();i++){

                                Servicio servi= new Servicio();
                                servicios[i]= servi;
                                servicios[i].setIdservicio(Integer.parseInt(usuariosJson.getJSONObject(i).getString("idservicio")));
                                servicios[i].setNombreservicio(usuariosJson.getJSONObject(i).getString("nombreservicio"));
                                servicios[i].setDescripcionservicio(usuariosJson.getJSONObject(i).getString("descripcionservicio"));
                                servicios[i].setUbicacionservicio(usuariosJson.getJSONObject(i).getString("ubicacionservicio"));
                                servicios[i].setEstadoservicio(Boolean.parseBoolean(usuariosJson.getJSONObject(i).getString("estadoservicio")));
                                servicios[i].setPrecioservicio(Double.parseDouble(usuariosJson.getJSONObject(i).getString("precioservicio")));
                                servicios[i].setTiposervicio(usuariosJson.getJSONObject(i).getString("tiposervicio"));
                                servicios[i].setCedulausuario(usuariosJson.getJSONObject(i).getString("cedulausuario"));



                                String temp1= usuariosJson.getJSONObject(i).getString("imagen1");
                                String temp2= usuariosJson.getJSONObject(i).getString("imagen2");
                                String temp3= usuariosJson.getJSONObject(i).getString("imagen3");
                                Bitmap im1=StringToBitMap(temp1);
                                Bitmap im2=StringToBitMap(temp2);
                                Bitmap im3=StringToBitMap(temp3);

                                servicios[i].setImagen1(im1);
                                servicios[i].setImagen2(im2);
                                servicios[i].setImagen3(im3);








                            }






                       ServiciosAdapter serAdapter=new ServiciosAdapter(getContext(),R.layout.list_view_servicios, Arrays.asList(servicios));
                        listViewServicios.setAdapter(serAdapter);

                    }catch (Exception e){

                        Toast mensaje=Toast.makeText(getContext(),"Excepcion aqui"+e.getMessage(),Toast.LENGTH_LONG);//CREA UN TOAST(NOTIFICACION) QUE HAY CAMPOS VACIOS
                        mensaje.setGravity(Gravity.CENTER,0,0);//LE ASIGNA LA POSICION A LA NOTIFICACION
                        mensaje.show();//MUESTRA LA NOTIFICACION

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

    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }




}
