package com.example.cristopher.proyectomoviles.Business;


import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cristopher.proyectomoviles.Data.VolleySingleton;
import com.example.cristopher.proyectomoviles.Domain.Constantes;
import com.example.cristopher.proyectomoviles.Domain.HistorialServicio;
import com.example.cristopher.proyectomoviles.Domain.Imagen;
import com.example.cristopher.proyectomoviles.Domain.Usuario;
import com.example.cristopher.proyectomoviles.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoHistorialServicios extends FragmentoAbsPrincipal {

    private ImageView imagen1;
    private ImageView imagen2;
    private ImageView imagen3;

    public FragmentoHistorialServicios() {
        // Required empty public constructor
    }

    public Bitmap StringToBitMap(String encodedString) {

        String pureBase64Encoded = encodedString.substring(encodedString.indexOf(",")  + 1);
        try {
            byte[] encodeByte = Base64.decode(pureBase64Encoded, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista= inflater.inflate(R.layout.fragment_fragmento_historial_servicios, container, false);
        imagen1=vista.findViewById(R.id.imagenServicioHistorial1);
        imagen2=vista.findViewById(R.id.imagenServicioHistorial2);
        imagen3=vista.findViewById(R.id.imagenServicioHistorial3);

        SharedPreferences sesion= this.getActivity().getSharedPreferences("Sesion", MODE_PRIVATE);

        String nuevaUrl= Constantes.IP_HISTORIAL+"?accion=obtenerHistorialServiciosUsuario&&cedulausuario="+sesion.getString("cedula","");

        VolleySingleton.getInstance(getActivity()).addToRequestQueue(
                new JsonObjectRequest(Request.Method.GET, nuevaUrl, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        try{

                            String estadoRespuesta=response.getString("estado");//recibe la respuesta del script


                            switch (estadoRespuesta){

                                case "1"://obtuvo los usuarios
                                    try{
                                        JSONArray historialJson=response.getJSONArray("historial");
                                        Type listType = new TypeToken<ArrayList<HistorialServicio>>(){}.getType();
                                        List<HistorialServicio> listaUsuario=new Gson().fromJson(String.valueOf(historialJson),listType);

                                        HistorialServicio[] historiales=gson.fromJson(historialJson.toString(),HistorialServicio[].class);


                                        int i=0;
                                        for (i=0;i<historiales.length;i++) {
                                            String nuevaUrl2= Constantes.IP_HISTORIAL+"?accion=obtenerImagenesServicio&&idServicio="+historiales[i].getIdservicio();


                                            int finalI = i;
                                            VolleySingleton.getInstance(getActivity()).addToRequestQueue(
                                                    new JsonObjectRequest(Request.Method.GET, nuevaUrl2, null, new                                                       Response.Listener<JSONObject>() {
                                                        @Override
                                                        public void onResponse(JSONObject response) {
                                                            Gson gson = new Gson();
                                                            try{

                                                                String estadoRespuesta=response.getString("estado");//recibe la respuesta del script


                                                                switch (estadoRespuesta){

                                                                    case "1"://obtuvo los usuarios
                                                                        try{
                                                                            JSONArray imagenesJson=response.getJSONArray("imagenes");
                                                                            Type listType = new TypeToken<ArrayList<Imagen>>(){}.getType();
                                                                            List<Imagen> listaImagen=new Gson().fromJson(String.valueOf(imagenesJson),listType);

                                                                            Imagen[] imagenes=gson.fromJson(imagenesJson.toString(),Imagen[].class);

                                                                            Bitmap imagen1Bitmap = StringToBitMap(imagenes[0].getImagen());
                                                                            imagen1.setImageBitmap(imagen1Bitmap);

                                                                            Bitmap imagen2Bitmap = StringToBitMap(imagenes[1].getImagen());
                                                                            imagen2.setImageBitmap(imagen2Bitmap);

                                                                            Bitmap imagen3Bitmap = StringToBitMap(imagenes[2].getImagen());
                                                                            imagen3.setImageBitmap(imagen3Bitmap);


                                                                        }catch (Exception e){

                                                                            Toast mensaje=Toast.makeText(getContext(),"Excepcion imagenes"+e.getMessage(),Toast.LENGTH_LONG);
                                                                            mensaje.setGravity(Gravity.CENTER,0,0);
                                                                            mensaje.show();

                                                                        }

                                                                        break;
                                                                    case "2":

                                                                        Toast mensaje=Toast.makeText(getContext(),"Fallo al obtener las imagenes",Toast.LENGTH_LONG);
                                                                        mensaje.setGravity(Gravity.CENTER,0,0);
                                                                        mensaje.show();
                                                                        break;
                                                                }


                                                            }catch (Exception e){

                                                                e.printStackTrace();
                                                            }
                                                        }
                                                    }, new Response.ErrorListener() {
                                                        @Override
                                                        public void onErrorResponse(VolleyError error) {
                                                            Toast mensaje=Toast.makeText(getContext(),"ErrorListenerImagenes:"+error.getMessage(),Toast.LENGTH_LONG);//CREA UN
                                                            mensaje.setGravity(Gravity.CENTER,0,0);
                                                            mensaje.show();

                                                        }
                                                    }

                                                    ));

                                        }




                                        //UsuariosAdapter usuariosAdapter=new UsuariosAdapter(getContext(),R.layout.list_view_usuarios, Arrays.asList(usuarios));
                                        //listViewUsuarios.setAdapter(usuariosAdapter);

                                    }catch (Exception e){

                                        Toast mensaje=Toast.makeText(getContext(),"Excepcion Historiales"+e.getMessage(),Toast.LENGTH_LONG);
                                        mensaje.setGravity(Gravity.CENTER,0,0);
                                        mensaje.show();

                                    }

                                    break;
                                case "2":

                                    Toast mensaje=Toast.makeText(getContext(),"Fallo al obtener los historiales",Toast.LENGTH_LONG);
                                    mensaje.setGravity(Gravity.CENTER,0,0);
                                    mensaje.show();
                                    break;
                            }


                        }catch (Exception e){

                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast mensaje=Toast.makeText(getContext(),"ErrorListenerHistoriales:"+error.getMessage(),Toast.LENGTH_LONG);//CREA UN
                        mensaje.setGravity(Gravity.CENTER,0,0);
                        mensaje.show();

                    }
                }

                ));



        return vista;
    }

}
