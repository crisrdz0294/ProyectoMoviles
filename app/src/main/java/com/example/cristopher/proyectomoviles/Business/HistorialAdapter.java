package com.example.cristopher.proyectomoviles.Business;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cristopher.proyectomoviles.Data.VolleySingleton;
import com.example.cristopher.proyectomoviles.Domain.Constantes;
import com.example.cristopher.proyectomoviles.Domain.HistorialServicio;
import com.example.cristopher.proyectomoviles.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistorialAdapter extends ArrayAdapter<HistorialServicio> {

    Context contexto;
    int layoutVistaHistorial;
    List<HistorialServicio> listaHistorial;

    public HistorialAdapter(Context contexto,int layout,List<HistorialServicio> historiales){
        super(contexto,layout,historiales);

        this.contexto=contexto;
        this.layoutVistaHistorial=layout;
        this.listaHistorial=historiales;
    }


    public View getView(int posicion, View vistaConvertida, ViewGroup vistaPadre){

        LayoutInflater inflater = LayoutInflater.from(contexto);

        final View vista = inflater.inflate(layoutVistaHistorial, null);

        final HistorialServicio historial=listaHistorial.get(posicion);

        TextView textViewNombre= vista.findViewById(R.id.nombreServicioHistorial);
        TextView textVieFecha = vista.findViewById(R.id.fechaVisita);

        textViewNombre.setText(historial.getNombreservicio());
        textVieFecha.setText(historial.getFechavisita());

        final FloatingActionButton btnEliminar=vista.findViewById(R.id.floatingEliminarHistorial);

        String nuevaUrl2= Constantes.IP_HISTORIAL+"?accion=obtenerImagenesServicio&&idServicio="+historial.getIdservicio();

        VolleySingleton.getInstance(this.getContext()).addToRequestQueue(new JsonObjectRequest(Request.Method.GET, nuevaUrl2, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Gson gson = new Gson();

                try{

                    String estadoRespuesta=response.getString("estado");//recibe la respuesta del script
                    switch (estadoRespuesta){

                        case "1"://obtuvo los usuarios
                            try{
                                JSONArray imagenesJson=response.getJSONArray("imagenes");

                                for(int i=0;i<imagenesJson.length();i++){

                                    ViewFlipper historialFlipper=vista.findViewById(R.id.imagenesHistorial);

                                    String pureBase64Encoded = imagenesJson.getJSONObject(i).getString("imagen").substring(imagenesJson.getJSONObject(i).getString("imagen").indexOf(",")  + 1);

                                        ImageView imagen = new ImageView(getContext());
                                        byte[] encodeByte = Base64.decode(pureBase64Encoded, Base64.DEFAULT);
                                        Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);

                                        int width = bitmap.getWidth();

                                        int height = bitmap.getHeight();
                                        int newWidth = 400;
                                        int newHeight = 200;


                                        float scaleWidth = ((float) newWidth) / width;
                                        float scaleHeight = ((float) newHeight) / height;

                                        Matrix matrix = new Matrix();

                                        matrix.postScale(scaleWidth, scaleHeight);


                                        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,width, height, matrix, true);

                                        imagen.setImageBitmap(resizedBitmap);

                                        historialFlipper.addView(imagen);

                                        historialFlipper.setFlipInterval(1000);
                                        historialFlipper.setAutoStart(true);
                                        historialFlipper.setInAnimation(contexto,android.R.anim.slide_in_left);
                                        historialFlipper.setOutAnimation(contexto,android.R.anim.slide_out_right);

                                    resizedBitmap=null;
                                    imagen=null;
                                    pureBase64Encoded="";

                                }

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

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
                builder.setTitle("Esta seguro de realizar esta acción");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {

                        String nuevaUrl= Constantes.IP_HISTORIAL+"?accion=eliminarHistorialServicio";

                        HashMap<String,String> mapaHistorial=new HashMap<String, String>();

                        mapaHistorial.put("idhistorial",String.valueOf(historial.getIdhistorialservicio()));

                        JSONObject jsonObjeto=new JSONObject(mapaHistorial);

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

                                            ((VistaPrincipal)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor2, new FragmentoHistorialServicios(),"FragmentoHistorialServicios").addToBackStack(null).commit();

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
                            }}, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast mensaje1=Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG);
                                        mensaje1.setGravity(Gravity.CENTER,0,0);
                                        mensaje1.show();
                                    }
                                }){
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


