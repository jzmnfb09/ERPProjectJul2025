package com.example.nerp.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.example.nerp.model.Usuario;

public class UsuarioRowMapper implements RowMapper<Usuario> {

    @Override
    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setUsername(rs.getString("username"));
        usuario.setPassword(rs.getString("password"));
        return usuario;
    }
}
