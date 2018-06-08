package com.example.cristopher.proyectomoviles.Business;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cristopher.proyectomoviles.Domain.Servicio;
import com.example.cristopher.proyectomoviles.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoMostrarPrincipal extends FragmentoAbsPrincipal {

    List<Servicio> todos;
    public FragmentoMostrarPrincipal() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View vista= inflater.inflate(R.layout.fragment_fragmento_mostrar_principal, container, false);


    ArrayList<Bitmap> image= new ArrayList<>();









        Bitmap bitmap = Bitmap.createBitmap(20, 20, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(1);
        canvas.drawRect(0F, 0F, (float) 20, (float) 20, paint);
        image.add(bitmap);
        for(int i=0;i<=5;i++){
        todos.add(new Servicio(1,"prueba","prueba","prueba",true,null,null,null,null,null,null));
    }
        RecyclerView vist;
    vist= (RecyclerView) vista.findViewById(R.id.recyclerview_id2);
    RecyclerViewAdapter adapter= new RecyclerViewAdapter(this.getContext(),todos);
          vist.setLayoutManager(new GridLayoutManager(this.getContext(),3));
          vist.setAdapter(adapter);
    return vista;
    }

}
