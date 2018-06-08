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
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cristopher.proyectomoviles.Data.VolleySingleton;
import com.example.cristopher.proyectomoviles.Domain.Calificacion;
import com.example.cristopher.proyectomoviles.Domain.Constantes;
import com.example.cristopher.proyectomoviles.Domain.Usuario;
import com.example.cristopher.proyectomoviles.R;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoMostrarServicio extends FragmentoAbsPrincipal implements View.OnClickListener {
    private RatingBar puntuacion;
    private EditText comentario;
    private EditText fecha;


    public FragmentoMostrarServicio()  {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_fragmento_mostrar_servicio, container, false);
        puntuacion=vista.findViewById(R.id.R2);
        fecha = vista.findViewById(R.id.fecha);
        comentario = vista.findViewById(R.id.comentarioMostrar);
        cargarCalificaciones();
        return vista;
    }


    public void cargarCalificaciones(){
        final Calificacion[] calificacion = {null};
        SharedPreferences pref = getContext().getSharedPreferences("Sesion", MODE_PRIVATE);
        String cedula=pref.getString("cedula","");
        String nuevaUrl= Constantes.IP_CALIFICACION+"?accion=obtenerCalificaciones&&cedula="+cedula+"&&id="+2;

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
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault());
                                    JSONObject object = response.getJSONObject("calificaciones");
                                    calificacion[0] = gson.fromJson(object.toString(), Calificacion.class);
                                    String comentarioRecibido = calificacion[0].getComentario();
                                    Date fechaRecibido = calificacion[0].getFecha();
                                    String fechaCalificacion = dateFormat.format(fechaRecibido);
                                    float puntuacionRecibida = calificacion[0].getPuntuacion();

                                    puntuacion.setRating(puntuacionRecibida);
                                    fecha.setText(fechaCalificacion);
                                    comentario.setText(comentarioRecibido);


                                    break;
                                case "2":

                                    Toast mensaje=Toast.makeText(getContext(),"Fallo al obtener las calificaciones",Toast.LENGTH_LONG);//CREA UN TOAST(NOTIFICACION) QUE HAY CAMPOS VACIOS
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
    @Override
    public void onClick(View v) {

    }
}
