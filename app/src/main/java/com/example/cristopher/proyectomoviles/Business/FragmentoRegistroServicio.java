package com.example.cristopher.proyectomoviles.Business;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cristopher.proyectomoviles.Data.VolleySingleton;
import com.example.cristopher.proyectomoviles.Domain.Constantes;
import com.example.cristopher.proyectomoviles.Domain.Servicio;
import com.example.cristopher.proyectomoviles.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;
import static android.util.Base64.encodeToString;

/**
 * A simple {@link Fragment} subclass.
 */

public class FragmentoRegistroServicio extends FragmentoAbsPrincipal implements View.OnClickListener {



    private EditText descripcionServicio;
    private EditText ubicacionServicio;
    private EditText nombreServicio;

    private Button Registro;
    private Button cargar1;
    private Button cargar2;
    private Button cargar3;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private EditText precio;
    private Spinner dropdown ;
    private Bitmap bitmap1;
    private Bitmap bitmap2;
    private Bitmap bitmap3;
    private int PICK_IMAGE_REQUEST = 1;
    private int PICK_OPTION_IMAGE = 0;

    private String UPLOAD_URL ="http://servermorefast.webcindario.com/upload.php";

    private String KEY_IMAGEN = "foto";
    private String KEY_NOMBRE = "nombre";


    public FragmentoRegistroServicio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista=inflater.inflate(R.layout.fragment_fragmento_registro_servicio, container, false);



        dropdown= vista.findViewById(R.id.spinnerTipoServicio);
        String[] items = new String[]{"PENSION", "APARTAMENTO", "CUARTO COMPARTIDO","CUARTO INDIVIDUAL","CASA"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
       image1=vista.findViewById(R.id.imagen1);
        image2=vista.findViewById(R.id.imagen2);
        image3=vista.findViewById(R.id.imagen3);
cargar1=vista.findViewById(R.id.btExaminar);
        cargar2=vista.findViewById(R.id.btExaminar3);
        cargar3=vista.findViewById(R.id.btExaminar2);
        descripcionServicio=vista.findViewById(R.id.tfDescripcionServicio);
        ubicacionServicio=vista.findViewById(R.id.tfUbicacion);
        nombreServicio=vista.findViewById(R.id.tfNombre);
        precio=vista.findViewById(R.id.tfPrecio);
        Registro=vista.findViewById(R.id.btRegistrarServicio);

        Registro.setOnClickListener(this);
        cargar1.setOnClickListener(this);
        cargar2.setOnClickListener(this);
        cargar3.setOnClickListener(this);
        return vista;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("esto es el mensaje:"+PICK_IMAGE_REQUEST);
        Uri filePath = data.getData();
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null&&PICK_OPTION_IMAGE==1) {

            try {
                //Cómo obtener el mapa de bits de la Galería

                bitmap1 = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                //Configuración del mapa de bits en ImageView


                int width = bitmap1.getWidth();

                int height = bitmap1.getHeight();
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
                Bitmap resizedBitmap = Bitmap.createBitmap(bitmap1, 0, 0,width, height, matrix, true);

                // si queremos poder mostrar nuestra imagen tenemos que crear un
                // objeto drawable y así asignarlo a un botón, imageview...

                image1.setImageBitmap(resizedBitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }}
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null&&PICK_OPTION_IMAGE==2) {

                try {
                    //Cómo obtener el mapa de bits de la Galería

                    bitmap2 = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                    //Configuración del mapa de bits en ImageView


                    int width = bitmap2.getWidth();

                    int height = bitmap2.getHeight();
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
                    Bitmap resizedBitmap = Bitmap.createBitmap(bitmap2, 0, 0,width, height, matrix, true);

                    // si queremos poder mostrar nuestra imagen tenemos que crear un
                    // objeto drawable y así asignarlo a un botón, imageview...

                    image2.setImageBitmap(resizedBitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null&&PICK_OPTION_IMAGE==3) {

                try {
                    //Cómo obtener el mapa de bits de la Galería

                    bitmap3 = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                    //Configuración del mapa de bits en ImageView


                    int width = bitmap3.getWidth();

                    int height = bitmap3.getHeight();
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
                    Bitmap resizedBitmap = Bitmap.createBitmap(bitmap3, 0, 0,width, height, matrix, true);

                    // si queremos poder mostrar nuestra imagen tenemos que crear un
                    // objeto drawable y así asignarlo a un botón, imageview...

                    image3.setImageBitmap(resizedBitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }
    }}

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.btRegistrarServicio){

          String Descripcion= descripcionServicio.getText().toString();
          String tipo=  String.valueOf(dropdown.getSelectedItemPosition());
          String ubica=ubicacionServicio.getText().toString();
          String Precio=precio.getText().toString();
          String name=nombreServicio.getText().toString();
             Boolean Pasa=false;
            if(!TextUtils.isEmpty(Descripcion)){

                if(!TextUtils.isEmpty(ubica)){

                    if(!TextUtils.isEmpty(Precio)){
                        if(!TextUtils.isEmpty(name)){
                            Pasa=true;
                        }else{
                            Toast mensaje=Toast.makeText(getContext(),"Nombre Vacio",Toast.LENGTH_LONG);//CREA UN TOAST(NOTIFICACION) QUE HAY CAMPOS VACIOS
                            mensaje.setGravity(Gravity.CENTER,0,0);//LE ASIGNA LA POSICION A LA NOTIFICACION
                            mensaje.show();//MUESTRA LA NOTIFICACION
                        }


                    }else{
                        Toast mensaje=Toast.makeText(getContext(),"Precio Vacio",Toast.LENGTH_LONG);//CREA UN TOAST(NOTIFICACION) QUE HAY CAMPOS VACIOS
                        mensaje.setGravity(Gravity.CENTER,0,0);//LE ASIGNA LA POSICION A LA NOTIFICACION
                        mensaje.show();//MUESTRA LA NOTIFICACION
                    }
                }else{
                    Toast mensaje=Toast.makeText(getContext(),"Ubicacion Vacia",Toast.LENGTH_LONG);//CREA UN TOAST(NOTIFICACION) QUE HAY CAMPOS VACIOS
                mensaje.setGravity(Gravity.CENTER,0,0);//LE ASIGNA LA POSICION A LA NOTIFICACION
                mensaje.show();//MUESTRA LA NOTIFICACIO
                            }
            }else{
                    Toast mensaje=Toast.makeText(getContext(),"Descripcion Vacia",Toast.LENGTH_LONG);//CREA UN TOAST(NOTIFICACION) QUE HAY CAMPOS VACIOS
                    mensaje.setGravity(Gravity.CENTER,0,0);//LE ASIGNA LA POSICION A LA NOTIFICACION
                    mensaje.show();//MUESTRA LA NOTIFICACION

                }


                if(Pasa){
                    String nuevaUrl= Constantes.IP_SERVICIOS;
                    SharedPreferences sesion= this.getActivity().getSharedPreferences("Sesion", MODE_PRIVATE);
                    HashMap<String,String> mapaServicio=new HashMap<String, String>();

                    System.out.println("---------------------------------");




                    mapaServicio.put("nombreservicio",name);
                    mapaServicio.put("accion","insertarServicios");
                    mapaServicio.put("descripcionservicio",Descripcion);
                    mapaServicio.put("ubicacionservicio",ubica);
                    mapaServicio.put("precioservicio",Precio);
                    mapaServicio.put("tiposervicio",tipo);
                    mapaServicio.put("cedulausuario",sesion.getString("cedula",""));
                    mapaServicio.put("imagen1",getStringImagen(bitmap1));
                    mapaServicio.put("imagen2",getStringImagen(bitmap2));
                    mapaServicio.put("imagen3",getStringImagen(bitmap3));

                    JSONObject jsonObjeto=new JSONObject(mapaServicio);


                    VolleySingleton.getInstance(getActivity()).addToRequestQueue(new JsonObjectRequest(
                                                                                         Request.Method.POST, "http://10.0.3.2:80/appsas/controladoras/controladoraServicio.php", jsonObjeto, new Response.Listener<JSONObject>() {
                                                                                     @Override
                                                                                     public void onResponse(JSONObject response) {


                                                                                         try{
                                                                                             System.out.println(response.toString());

                                                                                             String estado=response.getString("estado");
                                                                                             String mensaje=response.getString("mensaje");

                                                                                             switch (estado) {
                                                                                                 case "1":
                                                                                                     Toast mensaje1=Toast.makeText(getContext(),mensaje,Toast.LENGTH_LONG);
                                                                                                     mensaje1.setGravity(Gravity.CENTER,0,0);
                                                                                                     mensaje1.show();

                                                                                                     nombreServicio.setText("");


                                                                                                      descripcionServicio.setText("");
                                                                                                      ubicacionServicio.setText("");
                                                                                                  image1.setImageDrawable(getResources().getDrawable(R.drawable.ic_ver_ayuda));
                                                                                                  image2.setImageDrawable(getResources().getDrawable(R.drawable.ic_ver_ayuda));
                                                                                                     image3.setImageDrawable(getResources().getDrawable(R.drawable.ic_ver_ayuda));
                                                                                                  precio.setText("");

                                                                                                      bitmap1=null;
                                                                                                    bitmap2=null;
                                                                                                     bitmap3=null;

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



        }if(v.getId()==R.id.btExaminar){

            PICK_OPTION_IMAGE=1;
            showFileChooser();




        } if(v.getId()==R.id.btExaminar2){
            PICK_OPTION_IMAGE=3;
        showFileChooser();

    }if(v.getId()==R.id.btExaminar3){

            PICK_OPTION_IMAGE=2;
        showFileChooser();



    }


    }

    public String getStringImagen(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }


}
