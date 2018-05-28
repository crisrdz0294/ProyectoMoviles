package com.example.cristopher.proyectomoviles.Retrofit;

import android.view.Gravity;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiAdapter {

    private static ApiServicio API_SERVICIO;

    public static ApiServicio getApiServicio(){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if(API_SERVICIO==null){
            API_SERVICIO = new Retrofit.Builder()
                    .baseUrl(ApiConstantes.url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(ApiServicio.class);
        }

        return API_SERVICIO;
    }

}
