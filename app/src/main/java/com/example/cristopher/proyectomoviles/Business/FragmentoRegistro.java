package com.example.cristopher.proyectomoviles.Business;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cristopher.proyectomoviles.Domain.Usuario;
import com.example.cristopher.proyectomoviles.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoRegistro extends FragmentoAbsPrincipal implements View.OnClickListener{


    private EditText nombre;
    private EditText cedula;
    private EditText apellidos;
    private EditText email;
    private EditText telefono;
    private EditText clave;
    private EditText confirmarClave;
    private Button btnRegistrar;


    public FragmentoRegistro() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View vista=inflater.inflate(R.layout.fragment_fragmento_registro, container, false);
       cedula=vista.findViewById(R.id.cedula);
        nombre=vista.findViewById(R.id.nombre);
        apellidos=vista.findViewById(R.id.apellidos);
        clave=vista.findViewById(R.id.clave);
        confirmarClave=vista.findViewById(R.id.confirmarClave);
        email=vista.findViewById(R.id.email);
        telefono=vista.findViewById(R.id.telefono);

        btnRegistrar=vista.findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(this);

       return vista;
    }

    @Override
    public void onClick(View v) {

        boolean pasa=true;

        if(v.getId()==R.id.btnRegistrar){

            String nombre1=nombre.getText().toString();
            String cedula1=cedula.getText().toString();
            String apellidos1=apellidos.getText().toString();
            String clave1=clave.getText().toString();
            String confirmarClave1=confirmarClave.getText().toString();
            String email1=email.getText().toString();
            String telefono1=telefono.getText().toString();

            if(TextUtils.isEmpty(nombre1)||TextUtils.isEmpty(cedula1)||TextUtils.isEmpty(apellidos1)||TextUtils.isEmpty(clave1)||
                    TextUtils.isEmpty(confirmarClave1)||TextUtils.isEmpty(email1)||TextUtils.isEmpty(telefono1)){

                pasa=false;

                Toast mensaje=Toast.makeText(getContext(),"Hay campos vacios",Toast.LENGTH_LONG);//CREA UN TOAST(NOTIFICACION) QUE HAY CAMPOS VACIOS
                mensaje.setGravity(Gravity.CENTER,0,0);//LE ASIGNA LA POSICION A LA NOTIFICACION
                mensaje.show();//MUESTRA LA NOTIFICACION
            }else{

                if(!clave1.equals(confirmarClave1)){//COMPARA SI AMBAS CLAVES NO COINCICEN
                    Toast mensaje=Toast.makeText(getContext(),"Las claves no coinciden",Toast.LENGTH_LONG);
                    mensaje.setGravity(Gravity.CENTER,0,0);
                    mensaje.show();
                    pasa=false;
                }
            }

            if(pasa){

                Usuario nuevousuario = new Usuario();
                nuevousuario.setNombre(nombre1);
                nuevousuario.setCedula(cedula1);
                nuevousuario.setCorreo(email1);
                nuevousuario.setApellidos(apellidos1);
                nuevousuario.setTelefono(Integer.parseInt(telefono1));
                nuevousuario.setClave(clave1);
                nuevousuario.setTipoUsuario(1);
                Boolean resultado =  BD.insertarUsuario(nuevousuario);

                if (resultado) {

                    nombre.setText("");
                    cedula.setText("");
                    apellidos.setText("");
                    clave.setText("");
                    confirmarClave.setText("");
                    email.setText("");
                    telefono.setText("");
                    Toast mensaje=Toast.makeText(getContext(),"Exito al Registrar el Usuario",Toast.LENGTH_LONG);//CREA UN TOAST(NOTIFICACION) QUE HAY CAMPOS VACIOS
                    mensaje.setGravity(Gravity.CENTER,0,0);//LE ASIGNA LA POSICION A LA NOTIFICACION
                    mensaje.show();//MUESTRA LA NOTIFICACION


                }else{
                    Toast mensaje=Toast.makeText(getContext(),"Fallo al Registrar el Usuario",Toast.LENGTH_LONG);//CREA UN TOAST(NOTIFICACION) QUE HAY CAMPOS VACIOS
                    mensaje.setGravity(Gravity.CENTER,0,0);//LE ASIGNA LA POSICION A LA NOTIFICACION
                    mensaje.show();//MUESTRA LA NOTIFICACION
                }


            }
        }

    }
}
