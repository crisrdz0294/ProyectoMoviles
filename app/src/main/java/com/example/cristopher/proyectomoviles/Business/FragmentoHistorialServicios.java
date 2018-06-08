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
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

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
 *
 *
 */
public class FragmentoHistorialServicios extends FragmentoAbsPrincipal {

    private ListView listViewHistoriales;
    private List<HistorialServicio> listaHistoriales;
    ViewFlipper simpleViewFlipper;

    private View vista;

    private ArrayList<HistorialServicio> usuarios = new ArrayList<>();

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

    public void cargarDatosAdaptador(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista= inflater.inflate(R.layout.fragment_fragmento_historial_servicios, container, false);
        listViewHistoriales=vista.findViewById(R.id.listaHistorial);

        SharedPreferences sesion= this.getActivity().getSharedPreferences("Sesion", MODE_PRIVATE);

        String nuevaUrl= Constantes.IP_HISTORIAL+"?accion=obtenerHistorialServiciosUsuario&&cedulausuario="+sesion.getString("cedula","");



        VolleySingleton.getInstance(getActivity()).addToRequestQueue(
                new JsonObjectRequest(Request.Method.GET, nuevaUrl, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try{

                            String estadoRespuesta=response.getString("estado");//recibe la respuesta del script

                            switch (estadoRespuesta){

                                case "1"://obtuvo los usuarios
                                    try{
                                        Gson gsonHistorial = new Gson();
                                        JSONArray historialJson=response.getJSONArray("historial");



                                        //Type listType = new TypeToken<ArrayList<HistorialServicio>>(){}.getType();
                                       // List<HistorialServicio> listaUsuario=new Gson().fromJson(String.valueOf(historialJson),listType);

                                        HistorialServicio[] historiales=gsonHistorial.fromJson(historialJson.toString(),HistorialServicio[].class);

                                        HistorialAdapter historialAdapter=new HistorialAdapter(getContext(),R.layout.list_view_historial, Arrays.asList(historiales));
                                        listViewHistoriales.setAdapter(historialAdapter);

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
