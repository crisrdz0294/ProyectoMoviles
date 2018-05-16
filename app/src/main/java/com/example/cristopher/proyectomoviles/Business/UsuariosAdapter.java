package com.example.cristopher.proyectomoviles.Business;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.cristopher.proyectomoviles.Domain.Usuario;
import com.example.cristopher.proyectomoviles.R;

import java.util.List;

public class UsuariosAdapter extends ArrayAdapter<Usuario> {
    Context contexto;
    int layoutVistaUsuarios;
    List<Usuario> listaUsuarios;

    public UsuariosAdapter(Context contexto,int layoutUsuarios,List<Usuario> listaUsuarios){
        super(contexto, layoutUsuarios, listaUsuarios);

        this.contexto = contexto;
        this.layoutVistaUsuarios = layoutUsuarios;
        this.listaUsuarios = listaUsuarios;
    }

    public View getView(int posicion, @Nullable View convertView, @NonNull ViewGroup parent){

        LayoutInflater inflater = LayoutInflater.from(contexto);
        View vista = inflater.inflate(layoutVistaUsuarios, null);

        Usuario usuario=listaUsuarios.get(posicion);

        TextView textViewNombre= vista.findViewById(R.id.textViewNombre);
        TextView textViewApellidos = vista.findViewById(R.id.textViewApellidos);
        TextView textViewTelefono = vista.findViewById(R.id.textViewTelefono);
        TextView textViewCorreo = vista.findViewById(R.id.textViewCorreo);


        textViewNombre.setText(usuario.getNombre());
        textViewApellidos.setText(usuario.getApellidos());
        textViewTelefono.setText(String.valueOf(usuario.getTelefono()));
        textViewCorreo.setText(usuario.getCorreo());

        Button btnEditar = vista.findViewById(R.id.btnEditar);
        Button btnEliminar =vista.findViewById(R.id.btnEliminar);

        return vista;
    }
}
