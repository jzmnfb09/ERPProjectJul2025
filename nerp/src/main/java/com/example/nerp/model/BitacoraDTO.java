package com.example.nerp.model;

public class BitacoraDTO {
private String accion;
    private String detalle;
 private String tren;

public String getTren() {
    return tren;
}

public void setTren(String tren) {
    this.tren = tren;
}
    // Getters y Setters
    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
