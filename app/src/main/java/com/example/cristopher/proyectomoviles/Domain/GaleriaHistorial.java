package com.example.cristopher.proyectomoviles.Domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GaleriaHistorial {

    private List<Imagen> listaImagen;

    public GaleriaHistorial(){
        listaImagen=new ArrayList<Imagen>();
    }

    public void setGaleriaHistorial(Imagen imagen){
        listaImagen.add(imagen);
    }

    public List<Imagen> getGaleriaHistorial(){
        return this.listaImagen;
    }
}
