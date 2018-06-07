package com.example.cristopher.proyectomoviles.Domain;

import java.util.Date;

public class Calificacion {
    private int idCalificacion;
    private String comentario;
    private float puntuacion;
    private Date fecha;
    private int idServicio;
    private String cedulaUsario;

    public Calificacion(int idCalificacion, String comentario, float puntuacion, Date fecha, int idServicio, String cedulaUsario) {
        this.idCalificacion = idCalificacion;
        this.comentario = comentario;
        this.puntuacion = puntuacion;
        this.fecha = fecha;
    }

    public int getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(int idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public float getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(float calificacion) {
        this.puntuacion = puntuacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getCedulaUsario() {
        return cedulaUsario;
    }

    public void setCedulaUsario(String cedulaUsario) {
        this.cedulaUsario = cedulaUsario;
    }

    @Override
    public String toString() {
        return "Calificacion{" +
                "idCalificacion=" + idCalificacion +
                ", comentario='" + comentario + '\'' +
                ", calificacion=" + puntuacion +
                ", fecha=" + fecha +
                ", idServicio=" + idServicio +
                ", cedulaUsario='" + cedulaUsario + '\'' +
                '}';
    }
}
