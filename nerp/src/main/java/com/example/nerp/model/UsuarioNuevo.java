package com.example.nerp.model;

public class UsuarioNuevo {
private Integer id;
    private String username;
    private String password;
    private String rol;

public String getRol() {
    return rol;
}

public void setRol(String rol) {
    this.rol = rol;
}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
