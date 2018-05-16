package com.example.cristopher.proyectomoviles.Domain;

public class Usuario {
    private String nombre;
    private String cedula;
    private String apellidos;
    private String clave;
    private String correo;
    private int telefono;
    private int tipoUsuario;

    public Usuario(String nombre, String cedula, String apellidos, String clave, String correo, int telefono, int tipoUsuario) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.apellidos = apellidos;
        this.clave = clave;
        this.correo = correo;
        this.telefono = telefono;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario() {
        this.nombre = "";
        this.cedula = "";
        this.apellidos = "";
        this.clave = "";
        this.correo = "";
        this.telefono = 0;
        this.tipoUsuario = 0;
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
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", cedula='" + cedula + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", clave='" + clave + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono=" + telefono +
                ", tipoUsuario=" + tipoUsuario +
                '}';
    }
}
