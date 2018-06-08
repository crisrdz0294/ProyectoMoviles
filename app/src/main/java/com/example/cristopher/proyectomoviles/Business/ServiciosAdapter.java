package com.example.cristopher.proyectomoviles.Business;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.example.cristopher.proyectomoviles.Domain.Servicio;
import com.example.cristopher.proyectomoviles.Domain.Usuario;
import com.example.cristopher.proyectomoviles.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.goodiebag.carouselpicker.CarouselPicker;

import static java.lang.String.valueOf;

/**
 * Created by steff on 07/06/2018.
 */

public class ServiciosAdapter extends ArrayAdapter<Servicio> {
    ViewFlipper carouseel;

    Context contexto;
    int layoutVistaServicio;
    List<Servicio> listaServicio;

    public ServiciosAdapter(Context contexto, int layoutServicio, List<Servicio> lisServicio) {

        super(contexto, layoutServicio, lisServicio);

        this.contexto = contexto;
        this.layoutVistaServicio = layoutServicio;
        this.listaServicio = lisServicio;

    }

    public View getView(int posicion, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(contexto);
        View vista = inflater.inflate(layoutVistaServicio, null);

        final Servicio servicio = listaServicio.get(posicion);


        TextView textViewNombre = vista.findViewById(R.id.textNombreServicio);
        TextView textViewDescripcion = vista.findViewById(R.id.tfDescripcionServicio);
        TextView textViewEstado = vista.findViewById(R.id.tfEstado);
        TextView textViewPrecio = vista.findViewById(R.id.tfPrecio);
        TextView textViewUbicacion = vista.findViewById(R.id.tfubicacion);
        TextView textViewtipo = vista.findViewById(R.id.tfTipo);
        carouseel = vista.findViewById(R.id.fotosCarousel);
       // Bitmap images[] = {servicio.getImagen1(), servicio.getImagen2(), servicio.getImagen3()};


        Button edit, elimi;
        edit = vista.findViewById(R.id.btEditar);
        elimi = vista.findViewById(R.id.btnEliminar);

          //System.out.println("evaluado"+servicio.toString());
          try {
              textViewNombre.setText(servicio.getNombreservicio());
              textViewDescripcion.setText(servicio.getDescripcionservicio());

              if(servicio.isEstadoservicio()==true){
                  textViewEstado.setText("ACTIVO");
              }else{
                  textViewEstado.setText("DESACTIVADO");
              }


              textViewPrecio.setText(valueOf(servicio.getPrecioservicio()));
              textViewUbicacion.setText(servicio.getUbicacionservicio());

              if(servicio.getTiposervicio().equals("0")){
                  textViewtipo.setText("PENSION");
              }else if(servicio.getTiposervicio().equals("1")){
                  textViewtipo.setText("APARTAMENTO");
              }else if(servicio.getTiposervicio().equals("2")){
                  textViewtipo.setText("CUAR COMP");
              }else if(servicio.getTiposervicio().equals("3")){
                  textViewtipo.setText("CUAR IND");
              }else if(servicio.getTiposervicio().equals("4")){
                  textViewtipo.setText("CASA");
              }


              ViewFlipperImages(servicio.getImagen1());
              ViewFlipperImages(servicio.getImagen3());
              ViewFlipperImages(servicio .getImagen2());
          }catch (Exception e){
              System.out.println("Error al setear"+e);
          }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String servi = servicio.toString();

                Bundle enviar = new Bundle();
                enviar.putString("servicio", servi);

                ActualizarServicio actualizarServ = new ActualizarServicio();
                actualizarServ.setArguments(enviar);

                ((VistaPrincipal) getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor2, actualizarServ, "actualizarServicio").addToBackStack(null).commit();
            }


        });
        elimi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
                builder.setTitle("Esta seguro de realizar esta acción");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {
                        String nuevaUrl= Constantes.IP_SERVICIOS;

                        HashMap<String,String> mapaUsuario=new HashMap<String, String>();

                        mapaUsuario.put("idservicio",valueOf(servicio.getIdservicio()));
                        mapaUsuario.put("accion","eliminarServicio");
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

                                                                                                    ((VistaPrincipal)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor2, new FragmentoListaServicio(),"fragmentoListaServicios").addToBackStack(null).commit();

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

    void ViewFlipperImages(Bitmap image){

        ImageView imagen = new ImageView(this.getContext());
        int width = image.getWidth();

        int height = image.getHeight();
        int newWidth = 100;
        int newHeight = 75;

        // calculamos el escalado de la imagen destino
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        // para poder manipular la imagen
        // debemos crear una matriz

        Matrix matrix = new Matrix();
        // resize the Bitmap
        matrix.postScale(scaleWidth, scaleHeight);

        // volvemos a crear la imagen con los nuevos valores
        Bitmap resizedBitmap = Bitmap.createBitmap(image, 0, 0,width, height, matrix, true);

        // si queremos poder mostrar nuestra imagen tenemos que crear un
        // objeto drawable y así asignarlo a un botón, imageview...

        imagen.setImageBitmap(resizedBitmap);
        carouseel.addView(imagen);
        carouseel.setFlipInterval(1500);
        carouseel.setAutoStart(true);
         carouseel.setInAnimation(this.getContext(),android.R.anim.slide_in_left);
        carouseel.setOutAnimation(this.getContext(),android.R.anim.slide_out_right
        );
    }
}
