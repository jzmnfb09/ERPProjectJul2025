package com.example.nerp.model;

import java.sql.Timestamp;

public class BitacoraMovimiento {
private int id;
    private String usuario;
    private String accion;
    private String detalle;
    private Timestamp fecha;
    private String tren;

public String getTren() {
    return tren;
}

public void setTren(String tren) {
    this.tren = tren;
}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

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

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

}
