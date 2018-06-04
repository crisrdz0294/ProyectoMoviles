package com.example.cristopher.proyectomoviles.Domain;


import java.util.ArrayList;

/**
 * Created by steff on 15/05/2018.
 */

public class Servicio



{

    private int idServicio;
    private String nombreServicio;
    private  String  descripcionServicio;
    private String ubicacionServicio;
    private boolean estadoServicio;
    private String PrecioServicio;
    private ArrayList<String> listaFotos;
    private String tipoServicio;
public Servicio( int IdServicio,
        String NombreServicio,
        String  DescripcionServicio,
        String UbicacionServicio,
         boolean EstadoServicio,
         ArrayList<String> ListaFotos,String precio,String tipo){
    this.descripcionServicio=DescripcionServicio;
    this.estadoServicio=EstadoServicio;
    this.idServicio=IdServicio;
    this.listaFotos=ListaFotos;
    this.nombreServicio=NombreServicio;
    this.descripcionServicio=DescripcionServicio;
    this.PrecioServicio=precio;
this.tipoServicio=tipo;
}


    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getDescripcionServicio() {
        return descripcionServicio;
    }

    public void setDescripcionServicio(String descripcionServicio) {
        this.descripcionServicio = descripcionServicio;
    }

    public String getUbicacionServicio() {
        return ubicacionServicio;
    }

    public void setUbicacionServicio(String ubicacionServicio) {
        this.ubicacionServicio = ubicacionServicio;
    }
public String getPrecioServicio(){
    return PrecioServicio;
}
public String getTipoServicio(){
    return tipoServicio;
}

    public boolean isEstadoServicio() {
        return estadoServicio;
    }

    public void setEstadoServicio(boolean estadoServicio) {
        this.estadoServicio = estadoServicio;
    }

    public ArrayList<String> getListaFotos() {
        return listaFotos;
    }

    public void setListaFotos(ArrayList<String> listaFotos) {
        this.listaFotos = listaFotos;
    }
}
