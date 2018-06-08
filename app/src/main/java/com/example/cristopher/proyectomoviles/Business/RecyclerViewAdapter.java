package com.example.cristopher.proyectomoviles.Business;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cristopher.proyectomoviles.Domain.Servicio;
import com.example.cristopher.proyectomoviles.R;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context myContext;
    private List<Servicio> ListaServicios;



    public RecyclerViewAdapter(Context myContext, List<Servicio> listaServicios) {
        this.myContext = myContext;
        ListaServicios = listaServicios;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater minflater= LayoutInflater.from(myContext);

        view =minflater.inflate(R.layout.elementos_servicio_item,parent,false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        Bitmap bitmap1=ListaServicios.get(position).getImagen1();
        Matrix matrix = new Matrix();

        int width = bitmap1.getWidth();

        int height = bitmap1.getHeight();
        int newWidth = 100;
        int newHeight = 75;
        // resize the Bitmap
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        matrix.postScale(scaleWidth, scaleHeight);

        // volvemos a crear la imagen con los nuevos valores
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap1, 0, 0,width, height, matrix, true);
        holder.image.setImageBitmap(resizedBitmap);
        holder.Tipo.setText(ListaServicios.get(0).getTiposervicio().toString());
        holder.title.setText(ListaServicios.get(0).getUbicacionservicio().toString());



    }

    @Override
    public int getItemCount() {
        return ListaServicios.size();

    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView Tipo;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            image=(ImageView)itemView.findViewById(R.id.id_imagen_servicio);
            title=(TextView) itemView.findViewById(R.id.id_nombre_servicio);
            Tipo=(TextView) itemView.findViewById(R.id.id_tipo_servicio);
        }
    }
}
