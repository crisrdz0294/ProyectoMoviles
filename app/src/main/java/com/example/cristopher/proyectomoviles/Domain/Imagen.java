package com.example.cristopher.proyectomoviles.Domain;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Imagen {


    private Bitmap imagen;

    public Bitmap convertirStringBitmap(String imagenBase64) {

        String imagenBase64Final = imagenBase64.substring(imagenBase64.indexOf(",")  + 1);
        try {
            byte[] arregloByte = Base64.decode(imagenBase64Final, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(arregloByte, 0, arregloByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public Imagen() {

    }

    public void setImagen(String imagenBase64) {
        this.imagen=convertirStringBitmap(imagenBase64);
    }

    public Bitmap getImagen(){
        return this.imagen;
    }

}
