package com.example.cristopher.proyectomoviles.Domain;


import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import static android.util.Base64.encodeToString;

/**
 * Created by steff on 15/05/2018.
 */

public class Servicio



{

    private int idservicio;
    private String nombreservicio;
    private  String  descripcionservicio;



    private String ubicacionservicio;
    private boolean estadoservicio;
    private Double precioservicio;
    private String tiposervicio;
    private String cedulausuario;
    private Bitmap imagen1;
    private Bitmap imagen2;
    private Bitmap imagen3;

    public Servicio(int idservicio, String nombreservicio, String descripcionservicio, String ubicacionservicio, boolean estadoservicio, Double precioservicio, String tiposervicio, String cedulausuario, Bitmap imagen1, Bitmap imagen2, Bitmap imagen3) {
        this.idservicio = idservicio;
        this.nombreservicio = nombreservicio;
        this.descripcionservicio = descripcionservicio;
        this.ubicacionservicio = ubicacionservicio;
        this.estadoservicio = estadoservicio;
        this.precioservicio = precioservicio;
        this.tiposervicio = tiposervicio;
        this.cedulausuario = cedulausuario;
        this.imagen1 = imagen1;
        this.imagen2 = imagen2;
        this.imagen3 = imagen3;
    }

    public Servicio(){

    }

    @Override
    public String toString() {
        return idservicio +
                ","+nombreservicio+ "," + descripcionservicio+","+ ubicacionservicio + ","+
                estadoservicio +","+precioservicio +","+ tiposervicio +","+cedulausuario +","+ getStringImagen(imagen1) +","+getStringImagen(imagen2) +","+
                 getStringImagen(imagen3)+",";
    }

    public Bitmap getImagen1() {
        return imagen1;
    }

    public void setImagen1(Bitmap imagen1) {
        this.imagen1 = imagen1;
    }

    public Bitmap getImagen2() {
        return imagen2;
    }

    public void setImagen2(Bitmap imagen2) {
        this.imagen2 = imagen2;
    }

    public Bitmap getImagen3() {
        return imagen3;
    }

    public void setImagen3(Bitmap imagen3) {
        this.imagen3 = imagen3;
    }

    public int getIdservicio() {
        return idservicio;
    }

    public void setIdservicio(int idservicio) {
        this.idservicio = idservicio;
    }

    public String getNombreservicio() {
        return nombreservicio;
    }

    public void setNombreservicio(String nombreservicio) {
        this.nombreservicio = nombreservicio;
    }

    public String getDescripcionservicio() {
        return descripcionservicio;
    }

    public void setDescripcionservicio(String descripcionservicio) {
        this.descripcionservicio = descripcionservicio;
    }

    public String getUbicacionservicio() {
        return ubicacionservicio;
    }

    public void setUbicacionservicio(String ubicacionservicio) {
        this.ubicacionservicio = ubicacionservicio;
    }

    public boolean isEstadoservicio() {
        return estadoservicio;
    }

    public void setEstadoservicio(boolean estadoservicio) {
        this.estadoservicio = estadoservicio;
    }

    public Double getPrecioservicio() {
        return precioservicio;
    }

    public void setPrecioservicio(Double precioservicio) {
        this.precioservicio = precioservicio;
    }

    public String getTiposervicio() {
        return tiposervicio;
    }

    public void setTiposervicio(String tiposervicio) {
        this.tiposervicio = tiposervicio;
    }

    public String getCedulausuario() {
        return cedulausuario;
    }

    public void setCedulausuario(String cedulausuario) {
        this.cedulausuario = cedulausuario;
    }
    public String getStringImagen(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
}