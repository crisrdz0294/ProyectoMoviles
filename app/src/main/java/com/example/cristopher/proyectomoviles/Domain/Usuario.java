package com.example.cristopher.proyectomoviles.Domain;

public class Usuario {

    private String nombre;
    private String cedula;
    private String apellidos;
    private String clave;
    private String correo;
    private int telefono;
    private int tipo;

    public Usuario(int telefono, String nombre, String clave, String apellidos, String correo, String cedula,  int tipo ) {
        this.telefono = telefono;
        this.nombre = nombre;
        this.clave = clave;
        this.apellidos = apellidos;
        this.correo = correo;
        this.cedula = cedula;
        this.tipo = tipo;




    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getTipoUsuario() {
        return tipo;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipo = tipoUsuario;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", cedula='" + cedula + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", clave='" + clave + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono=" + telefono +'\'' +
                ", tipoUsuario=" + tipo +
                '}';
    }
}
