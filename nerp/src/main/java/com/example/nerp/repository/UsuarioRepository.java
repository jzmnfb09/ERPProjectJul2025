package com.example.nerp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.nerp.model.Usuario;
import java.util.List;
import java.util.Optional;
@Repository
public class UsuarioRepository {
 private final JdbcTemplate jdbcTemplate;
 private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // ✅
 
    @Autowired
    public UsuarioRepository(@Qualifier("psqlJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

public Usuario buscarPorUsernameYPassword(String username, String password) {
    String sql = "SELECT * FROM usuarios WHERE username = ?";
    return jdbcTemplate.query(sql, rs -> {
        if (rs.next()) {
            String hashed = rs.getString("password");
            if (new BCryptPasswordEncoder().matches(password, hashed)) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(hashed); // ya encriptada
                u.setRol(rs.getString("rol"));
                return u;
            }
        }
        return null;
    }, username);
}

public Usuario buscarPorUsername(String username) {
    String sql = "SELECT id, username, password, rol FROM usuarios WHERE username = ?";
    try {
        return jdbcTemplate.queryForObject(sql, new Object[]{username}, (rs, rowNum) -> {
            Usuario u = new Usuario();
            u.setId(rs.getInt("id"));
            u.setUsername(rs.getString("username"));
            u.setPassword(rs.getString("password"));
            u.setRol(rs.getString("rol"));
            return u;
        });
    } catch (EmptyResultDataAccessException e) {
        return null; // ✅ Así evitas el crash
    }
}



public void guardarUsuario(Usuario usuario) {
    String sql = "INSERT INTO usuarios (username, password) VALUES (?, ?)";
    String hashedPassword = passwordEncoder.encode(usuario.getPassword());
    jdbcTemplate.update(sql, usuario.getUsername(), hashedPassword);
}





public List<Usuario> findAll() {
    String sql = "SELECT id, username, password, rol FROM usuarios WHERE id IS NOT NULL";
    return jdbcTemplate.query(sql, (rs, rowNum) -> {
        Usuario u = new Usuario();
        u.setId(rs.getInt("id"));
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password"));
        u.setRol(rs.getString("rol"));
        return u;
    });
}

    
public void actualizar(Usuario usuario) {
    String sql = "UPDATE usuarios SET password = ?, rol = ? WHERE id = ?";
    jdbcTemplate.update(sql, usuario.getPassword(), usuario.getRol(), usuario.getId());
}
 
  
    public void save(Usuario usuario) {
        String sql = "INSERT INTO usuarios (username, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, usuario.getUsername(), usuario.getPassword());
    }
public void eliminarPorId(int id) {
    String sql = "DELETE FROM usuarios WHERE id = ?";
    jdbcTemplate.update(sql, id);
}
    
public Optional<Usuario> findById(Long id) {
    String sql = "SELECT id, username, password, rol FROM usuarios WHERE id = ?";
    try {
        Usuario usuario = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            Usuario u = new Usuario();
            u.setId(rs.getInt("id"));
            u.setUsername(rs.getString("username"));
            u.setPassword(rs.getString("password"));
            u.setRol(rs.getString("rol"));
            return u;
        });
        return Optional.of(usuario);
    } catch (Exception e) {
        return Optional.empty();
    }
}
public void insertarUsuarioNuevo(Usuario usuario) {
    String password = usuario.getPassword();
    
    
    boolean yaCodificada = password != null && password.startsWith("$2a$");

    String passwordFinal = yaCodificada ? password : passwordEncoder.encode(password);

    System.out.println("Contraseña original: " + password);
    System.out.println("Contraseña final usada: " + passwordFinal);

    String sql = "INSERT INTO usuarios (username, password, rol) VALUES (?, ?, ?)";
    jdbcTemplate.update(sql, usuario.getUsername(), passwordFinal, "usuario");
}




public Usuario buscarPorUsernameregistro(String username) {
    String sql = "SELECT * FROM usuarios WHERE username = ?";
    List<Usuario> usuarios = jdbcTemplate.query(sql, new UsuarioRowMapper(), username);
    return usuarios.isEmpty() ? null : usuarios.get(0);
}
public Usuario buscarPorUsername2(String username) {
    String sql = "SELECT * FROM usuarios WHERE username = ?";
    try {
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Usuario.class), username);
    } catch (EmptyResultDataAccessException e) {
        return null;
    }
}
public void actualizarPassword(String username, String nuevaPasswordCodificada) {
    String sql = "UPDATE usuarios SET password = ? WHERE username = ?";
    jdbcTemplate.update(sql, nuevaPasswordCodificada, username);
}


    
}
