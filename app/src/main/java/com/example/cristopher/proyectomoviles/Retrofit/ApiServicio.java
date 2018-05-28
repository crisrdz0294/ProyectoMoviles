package com.example.cristopher.proyectomoviles.Retrofit;

import com.example.cristopher.proyectomoviles.Domain.Usuario;

import org.json.JSONObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


public interface ApiServicio {

    @Headers("Content-Type: application/json")
    @POST("controladoraUsuarios.php")

    Call<Usuario> validarUsuario(@Body JSONObject cuerpo);
}
