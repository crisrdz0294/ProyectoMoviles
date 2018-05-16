package com.example.cristopher.proyectomoviles.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.cristopher.proyectomoviles.Domain.Constantes;
import com.example.cristopher.proyectomoviles.Domain.Usuario;

public class BaseDatos extends SQLiteOpenHelper {
    public Context context;
    public SQLiteDatabase.CursorFactory factory;


    public BaseDatos(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, Constantes.BD_NOMBRE, factory, Constantes.DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Constantes.CONSULTA_CREA_TABLA_USUARIOS);
    }

    @Override
    /**
     * pERMITE ACTUALIZAR UNA TABLA EXISTENTE
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Constantes.CONSULTA_CAPTURA_LISTA_USUARIOS);
        onCreate(db);
    }



    public boolean insertarUsuario(Usuario usuario) {
   
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constantes.USUARIO_COLUMN_CEDULA, usuario.getCedula());
        contentValues.put(Constantes.USUARIO_COLUMN_NOMBRE, usuario.getNombre());
        contentValues.put(Constantes.USUARIO_COLUMN_APELLIDO, usuario.getApellidos());
        contentValues.put(Constantes.USUARIO_COLUMN_CORREOE, usuario.getCorreo());
        contentValues.put(Constantes.USUARIO_COLUMN_TELEFONO, usuario.getTelefono());
        contentValues.put(Constantes.USUARIO_COLUMN_CLAVE, usuario.getClave());
        contentValues.put(Constantes.USUARIO_COLUMN_TIPOUSUARIO, usuario.getTipoUsuario());
        long result = db.insert(Constantes.TABLA_USUARIO, null, contentValues);

        return (result != -1) ? true : false;
    }

    ///////////////////////////////Metodo de Login
    public Usuario validarUsuario(String correo, String clave) {
        Usuario usuario=new Usuario();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from tabla_usuario where Correo='" + correo + "'and Clave='"+clave+"'", null);

        if (res.getCount()>0) {

            while(res.moveToNext()) {

                usuario.setCorreo(res.getString(res.getColumnIndex(Constantes.USUARIO_COLUMN_CORREOE)));

                usuario.setClave(res.getString(res.getColumnIndex(Constantes.USUARIO_COLUMN_CLAVE)));

                usuario.setTipoUsuario(res.getInt(res.getColumnIndex(Constantes.USUARIO_COLUMN_TIPOUSUARIO)));


            }
        }


        res.close();



        return usuario;
    }



    public Cursor getUsuarios(){

        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tabla_usuario", null);
        return cursor;
    }

    public boolean actualizarUsuario(Usuario usuario) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constantes.USUARIO_COLUMN_CEDULA, usuario.getCedula());
        contentValues.put(Constantes.USUARIO_COLUMN_NOMBRE, usuario.getNombre());
        contentValues.put(Constantes.USUARIO_COLUMN_APELLIDO, usuario.getApellidos());
        contentValues.put(Constantes.USUARIO_COLUMN_TELEFONO, usuario.getTelefono());
        contentValues.put(Constantes.USUARIO_COLUMN_CORREOE, usuario.getCorreo());
        contentValues.put(Constantes.USUARIO_COLUMN_CLAVE, usuario.getClave());
        contentValues.put(Constantes.USUARIO_COLUMN_CLAVE, usuario.getTipoUsuario());


        long result =   db.update(Constantes.TABLA_USUARIO, contentValues, Constantes.USUARIO_COLUMN_CEDULA + " = ? ", new String[]{usuario.getCedula()});
        return (result != -1) ? true : false;
    }

    public boolean eliminarUsuario(String cedula){

        SQLiteDatabase db = getWritableDatabase();
        long result =  db.delete(Constantes.TABLA_USUARIO, Constantes.USUARIO_COLUMN_CEDULA + " = ? ", new String[]{cedula});
        return (result != -1) ? true : false;
    }



}
