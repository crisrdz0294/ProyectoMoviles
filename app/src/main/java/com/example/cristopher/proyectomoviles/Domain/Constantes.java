package com.example.cristopher.proyectomoviles.Domain;

public interface Constantes {
    // CREAR LA ESTRUCTURA DE LA BASE DE DATOS
    public static final int DATABASE_VERSION = 1;
    public static final String BD_NOMBRE = "BDprueba.db";
    public static final String TABLA_USUARIO = "tabla_usuario";
    public static final String USUARIO_COLUMN_CEDULA = "Cedula";
    public static final String USUARIO_COLUMN_NOMBRE = "Nombre";
    public static final String USUARIO_COLUMN_APELLIDO = "Apellido";
    public static final String USUARIO_COLUMN_CLAVE = "Clave";
    public static final String USUARIO_COLUMN_CORREOE = "Correo";
    public static final String USUARIO_COLUMN_TELEFONO = "Telefono";
    public static final String USUARIO_COLUMN_TIPOUSUARIO = "TipoUsuario";

   /* public static final String CONSULTA_CREA_TABLA_USUARIOS = "CREATE TABLE" +
            Constantes.TABLA_USUARIO + "(" +
            Constantes.USUARIO_COLUMN_CEDULA  +" text primary key, " +
            Constantes.USUARIO_COLUMN_NOMBRE + " text, " +
            Constantes.USUARIO_COLUMN_APELLIDO  + " text, " +
            Constantes.USUARIO_COLUMN_CORREOE + " text, " +
            Constantes.USUARIO_COLUMN_CLAVE + " text, " +
            Constantes.USUARIO_COLUMN_TELEFONO + " integer, "+
            Constantes.USUARIO_COLUMN_TIPOUSUARIO+ " integer " + ")" ;*/


 public static final String CONSULTA_CREA_TABLA_USUARIOS = "CREATE TABLE " + Constantes.TABLA_USUARIO + "("
            + Constantes.USUARIO_COLUMN_CEDULA + " TEXT PRIMARY KEY,"
            + Constantes.USUARIO_COLUMN_NOMBRE + " TEXT,"
            + Constantes.USUARIO_COLUMN_APELLIDO + " TEXT,"
            + Constantes.USUARIO_COLUMN_CORREOE + " TEXT,"
            + Constantes.USUARIO_COLUMN_CLAVE + " TEXT,"
            +Constantes.USUARIO_COLUMN_TELEFONO+ " INTEGER,"
            + Constantes.USUARIO_COLUMN_TIPOUSUARIO + " INTEGER" + ");";

    public static final String CONSULTA_BORRA_TABLA_USUARIOS = "DROP TABLE IF EXISTS " + Constantes.TABLA_USUARIO;

    public static final String CONSULTA_CAPTURA_LISTA_USUARIOS = "SELECT * FROM " + Constantes.TABLA_USUARIO;


    public static final int CODIGO_DETALLE = 100;

    public static final int CODIGO_ACTUALIZACION = 101;

    public static final String PUERTO_HOST = "80";

    public static final String IP = "http://10.0.3.2:80/appsas/scriptUsuarios.php";

    public static final String GET = IP ;

    public static final String EXTRA_CEDULA = "cedula";
}
