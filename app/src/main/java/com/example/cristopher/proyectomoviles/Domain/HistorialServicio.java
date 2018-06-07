package com.example.cristopher.proyectomoviles.Domain;

public class HistorialServicio {

    private int idhistorialservicio;
    private int idservicio;
    private String nombreservicio;
    private String descripcionservicio;
    private String ubicacionservicio;
    private Double precioservicio;
    private int tiposervicio;
    private String fechavisita;

    public HistorialServicio(int idhistorialservicio, int idservicio, String nombreservicio, String descripcionservicio, String ubicacionservicio, Double precioservicio, int tiposervicio, String fechavisita) {
        this.idhistorialservicio = idhistorialservicio;
        this.idservicio = idservicio;
        this.nombreservicio = nombreservicio;
        this.descripcionservicio = descripcionservicio;
        this.ubicacionservicio = ubicacionservicio;
        this.precioservicio = precioservicio;
        this.tiposervicio = tiposervicio;
        this.fechavisita = fechavisita;
    }

    public int getIdhistorialservicio() {
        return idhistorialservicio;
    }

    public void setIdhistorialservicio(int idhistorialservicio) {
        this.idhistorialservicio = idhistorialservicio;
    }

    public int getIdservicio() {
        return idservicio;
    }

    public void setIdservicio(int idservicio) {
        this.idservicio = idservicio;
    }

    public String getNombreservicio() {
        return nombreservicio;
    }

    public void setNombreservicio(String nombreservicio) {
        this.nombreservicio = nombreservicio;
    }

    public String getDescripcionservicio() {
        return descripcionservicio;
    }

    public void setDescripcionservicio(String descripcionservicio) {
        this.descripcionservicio = descripcionservicio;
    }

    public String getUbicacionservicio() {
        return ubicacionservicio;
    }

    public void setUbicacionservicio(String ubicacionservicio) {
        this.ubicacionservicio = ubicacionservicio;
    }

    public Double getPrecioservicio() {
        return precioservicio;
    }

    public void setPrecioservicio(Double precioservicio) {
        this.precioservicio = precioservicio;
    }

    public int getTiposervicio() {
        return tiposervicio;
    }

    public void setTiposervicio(int tiposervicio) {
        this.tiposervicio = tiposervicio;
    }

    public String getFechavisita() {
        return fechavisita;
    }

    public void setFechavisita(String fechavisita) {
        this.fechavisita = fechavisita;
    }
}
