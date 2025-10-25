package com.example.nerp.model.kdoffsite;

import java.time.ZonedDateTime;

public class BitacoraKD {
    
    private Integer id;
    private String casem;
    private String usuario;
    private String accion;
    private String detalle;
    private ZonedDateTime fechaHora;


    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCasem() {
        return casem;
    }
    public void setCasem(String casem) {
        this.casem = casem;
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
    public ZonedDateTime getFechaHora() {
        return fechaHora;
    }
    public void setFechaHora(ZonedDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

}
