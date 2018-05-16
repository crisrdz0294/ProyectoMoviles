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
public class FragmentoLogin extends FragmentoAbsPrincipal implements  View.OnClickListener {
    private Button Ingresar;
    private EditText correo;
    private EditText clave;
    private Button RegistrarUsuario;



    public FragmentoLogin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = (View)  inflater.inflate(R.layout.fragment_fragmento_login, container, false);
        correo = (EditText) vista.findViewById(R.id.E1);
        clave = (EditText) vista.findViewById(R.id.P1);
        Ingresar = (Button) vista.findViewById(R.id.btnIngresar);
        RegistrarUsuario = (Button) vista.findViewById(R.id.btnRegistrarUsuario);

        Ingresar.setOnClickListener(this);
        RegistrarUsuario.setOnClickListener(this);
        return vista;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnIngresar) {
            Boolean pasa = true;
            String correo1 = correo.getText().toString();
            String clave1 = clave.getText().toString();





            if(TextUtils.isEmpty(correo1) || TextUtils.isEmpty(clave1)){
                pasa=false;
                Toast mensaje = Toast.makeText(getContext(), "Datos Vacios", Toast.LENGTH_LONG);
                mensaje.setGravity(Gravity.CENTER, 0, 0);
                mensaje.show();
            }


            if (pasa) {
                Usuario resultado = BD.validarUsuario(correo1,clave1);
                boolean result= false;
                if(resultado != null){
                    if(TextUtils.equals(resultado.getCorreo(),correo1) && TextUtils.equals(resultado.getClave(),clave1)){
                        result = true;
                    }
                }
                if(result){
                    //LLAMA AL FRAGMENTO
                    correo.setText("");
                    clave.setText("");
                    Intent intent = new Intent(getActivity(), VistaPrincipal.class);
                    getActivity().startActivity(intent);
                }else {
                    Toast mensaje = Toast.makeText(getContext(), "Datos Incorrectos", Toast.LENGTH_LONG);
                    mensaje.setGravity(Gravity.CENTER, 0, 0);
                    mensaje.show();
                }


            }

        }

        if(v.getId() == R.id.btnRegistrarUsuario){

            correo.setText("");
            clave.setText("");
            ((FragmentoAbsActividadPrincipal) getActivity()).agregueFragmentoAPila(new FragmentoRegistro());
        }

    }
}
