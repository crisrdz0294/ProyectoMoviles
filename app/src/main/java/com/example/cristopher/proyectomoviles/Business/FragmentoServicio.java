package com.example.cristopher.proyectomoviles.Business;


import android.content.SharedPreferences;
import android.media.Image;
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
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cristopher.proyectomoviles.Data.VolleySingleton;
import com.example.cristopher.proyectomoviles.Domain.Constantes;
import com.example.cristopher.proyectomoviles.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoServicio extends FragmentoAbsPrincipal implements View.OnClickListener {

    private RatingBar calificacion;
    private EditText comentario;
    private Button Enviar;


    public FragmentoServicio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View vista=inflater.inflate(R.layout.fragment_fragmento_servicio, container, false);

        calificacion=vista.findViewById(R.id.R1);
        comentario=vista.findViewById(R.id.comentario);
        Enviar=vista.findViewById(R.id.btnEnviar);
        Enviar.setOnClickListener(this);


        return vista;
    }

    @Override
    public void onClick(View v) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        boolean pasa = true;
        if(v.getId() == R.id.btnEnviar){
            float calificacion1 = calificacion.getRating();
            SharedPreferences sesion2= this.getActivity().getSharedPreferences("Sesion", MODE_PRIVATE);
            final String comentario1= comentario.getText().toString();
            String calificacionFinal = Float.toString(calificacion1);
            String fecha1 = dateFormat.format(date);
            int idServicio=2;
            String idFinal = Integer.toString(idServicio);
            String cedula1=sesion2.getString("cedula","");

            if(TextUtils.isEmpty(comentario1)){

                pasa=false;

                Toast mensaje=Toast.makeText(getContext(),"Hay campos vacios",Toast.LENGTH_LONG);//CREA UN TOAST(NOTIFICACION) QUE HAY CAMPOS VACIOS
                mensaje.setGravity(Gravity.CENTER,0,0);//LE ASIGNA LA POSICION A LA NOTIFICACION
                mensaje.show();//MUESTRA LA NOTIFICACION
            }

            if(pasa){

                String nuevaUrl= Constantes.IP_CALIFICACION+"?accion=insertarCalificacion";

                HashMap<String,String> mapaCalificacion=new HashMap<String, String>();

                mapaCalificacion.put("comentario",comentario1);
                mapaCalificacion.put("puntuacion",calificacionFinal);
                mapaCalificacion.put("fecha",fecha1);
                mapaCalificacion.put("servicio",idFinal);
                mapaCalificacion.put("cedula",cedula1);

                JSONObject jsonObjeto=new JSONObject(mapaCalificacion);
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

                                            comentario.setText("");

                                            break;
                                            case "2":
                                                Toast mensaje2=Toast.makeText(getContext(),mensaje,Toast.LENGTH_LONG);
                                                mensaje2.setGravity(Gravity.CENTER,0,0);
                                                mensaje2.show();
                                                break;
                                                }
                                }
                                catch(JSONException e){

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


        }

    }
}
