package com.example.nerp.service;

import java.time.LocalDate;
import java.util.List;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.nerp.model.BitacoraMovimiento;


@Service
public class BitacoraService {
private final JdbcTemplate jdbcTemplate;

    public BitacoraService(@Qualifier("psqlJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // ✅ Registrar con tren
    public void registrar(String usuario, String accion, String detalle, String tren) {
        try {
            String sql = "INSERT INTO bitacora_movimientos (usuario, accion, detalle, tren) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, usuario, accion, detalle, tren);
            System.out.println("Bitácora registrada: " + usuario + " - " + accion + " - " + detalle + " - Tren: " + tren);
        } catch (Exception e) {
            System.err.println("Error al guardar en bitácora: " + e.getMessage());
        }
    }

    // ✅ Registrar sin tren (por compatibilidad)
    public void registrar(String usuario, String accion, String detalle) {
        registrar(usuario, accion, detalle, null);
    }

    // ✅ Obtener todos
    public List<BitacoraMovimiento> obtenerTodos() {
        String sql = "SELECT id, usuario, accion, detalle, fecha, tren FROM bitacora_movimientos ORDER BY fecha ASC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            BitacoraMovimiento m = new BitacoraMovimiento();
            m.setId(rs.getInt("id"));
            m.setUsuario(rs.getString("usuario"));
            m.setAccion(rs.getString("accion"));
            m.setDetalle(rs.getString("detalle"));
            m.setFecha(rs.getTimestamp("fecha"));
            m.setTren(rs.getString("tren"));
            return m;
        });
    }

    // ✅ Buscar por fecha y usuario
    public List<BitacoraMovimiento> buscarPorFechaYUsuario(LocalDate fecha, String usuario) {
        String sql = "SELECT id, usuario, accion, detalle, fecha, tren FROM bitacora_movimientos " +
                     "WHERE DATE(fecha) = ? AND usuario ILIKE ? ORDER BY fecha ASC";
        return jdbcTemplate.query(sql, new Object[]{fecha, "%" + usuario + "%"}, (rs, rowNum) -> {
            BitacoraMovimiento m = new BitacoraMovimiento();
            m.setId(rs.getInt("id"));
            m.setUsuario(rs.getString("usuario"));
            m.setAccion(rs.getString("accion"));
            m.setDetalle(rs.getString("detalle"));
            m.setFecha(rs.getTimestamp("fecha"));
            m.setTren(rs.getString("tren"));
            return m;
        });
    }

    // ✅ Buscar por usuario
    public List<BitacoraMovimiento> buscarPorUsuario(String usuario) {
        String sql = "SELECT id, usuario, accion, detalle, fecha, tren FROM bitacora_movimientos " +
                     "WHERE usuario ILIKE ? ORDER BY fecha ASC";
        return jdbcTemplate.query(sql, new Object[]{"%" + usuario + "%"}, (rs, rowNum) -> {
            BitacoraMovimiento m = new BitacoraMovimiento();
            m.setId(rs.getInt("id"));
            m.setUsuario(rs.getString("usuario"));
            m.setAccion(rs.getString("accion"));
            m.setDetalle(rs.getString("detalle"));
            m.setFecha(rs.getTimestamp("fecha"));
            m.setTren(rs.getString("tren"));
            return m;
        });
    }

    // ✅ Buscar por fecha
    public List<BitacoraMovimiento> buscarPorFecha(LocalDate fecha) {
        String sql = "SELECT id, usuario, accion, detalle, fecha, tren FROM bitacora_movimientos " +
                     "WHERE DATE(fecha) = ? ORDER BY fecha ASC";
        return jdbcTemplate.query(sql, new Object[]{fecha}, (rs, rowNum) -> {
            BitacoraMovimiento m = new BitacoraMovimiento();
            m.setId(rs.getInt("id"));
            m.setUsuario(rs.getString("usuario"));
            m.setAccion(rs.getString("accion"));
            m.setDetalle(rs.getString("detalle"));
            m.setFecha(rs.getTimestamp("fecha"));
            m.setTren(rs.getString("tren"));
            return m;
        });
    }
   public List<BitacoraMovimiento> buscarPorTren(String tren) {
    String sql = "SELECT id, usuario, accion, detalle, fecha, tren FROM bitacora_movimientos WHERE tren ILIKE ? ORDER BY fecha ASC";
    return jdbcTemplate.query(sql, new Object[]{"%" + tren + "%"}, (rs, rowNum) -> {
        BitacoraMovimiento m = new BitacoraMovimiento();
        m.setId(rs.getInt("id"));
        m.setUsuario(rs.getString("usuario"));
        m.setAccion(rs.getString("accion"));
        m.setDetalle(rs.getString("detalle"));
        m.setFecha(rs.getTimestamp("fecha"));
        m.setTren(rs.getString("tren"));
        return m;
    });
}
public List<BitacoraMovimiento> buscarPorFechaUsuarioYTren(LocalDate fecha, String usuario, String tren) {
    String sql = "SELECT id, usuario, accion, detalle, fecha, tren FROM bitacora_movimientos " +
                 "WHERE DATE(fecha) = ? AND usuario ILIKE ? AND tren ILIKE ? ORDER BY fecha ASC";
    return jdbcTemplate.query(sql, new Object[]{fecha, "%" + usuario + "%", "%" + tren + "%"}, (rs, rowNum) -> {
        BitacoraMovimiento m = new BitacoraMovimiento();
        m.setId(rs.getInt("id"));
        m.setUsuario(rs.getString("usuario"));
        m.setAccion(rs.getString("accion"));
        m.setDetalle(rs.getString("detalle"));
        m.setFecha(rs.getTimestamp("fecha"));
        m.setTren(rs.getString("tren"));
        return m;
    });
}
public List<BitacoraMovimiento> buscarPorFechaYTren(LocalDate fecha, String tren) {
    String sql = "SELECT id, usuario, accion, detalle, fecha, tren FROM bitacora_movimientos WHERE DATE(fecha) = ? AND tren ILIKE ? ORDER BY fecha ASC";
    return jdbcTemplate.query(sql, new Object[]{fecha, "%" + tren + "%"}, (rs, rowNum) -> {
        BitacoraMovimiento m = new BitacoraMovimiento();
        m.setId(rs.getInt("id"));
        m.setUsuario(rs.getString("usuario"));
        m.setAccion(rs.getString("accion"));
        m.setDetalle(rs.getString("detalle"));
        m.setFecha(rs.getTimestamp("fecha"));
        m.setTren(rs.getString("tren"));
        return m;
    });
}

public List<BitacoraMovimiento> buscarPorUsuarioYTren(String usuario, String tren) {
    String sql = "SELECT id, usuario, accion, detalle, fecha, tren FROM bitacora_movimientos WHERE usuario ILIKE ? AND tren ILIKE ? ORDER BY fecha ASC";
    return jdbcTemplate.query(sql, new Object[]{"%" + usuario + "%", "%" + tren + "%"}, (rs, rowNum) -> {
        BitacoraMovimiento m = new BitacoraMovimiento();
        m.setId(rs.getInt("id"));
        m.setUsuario(rs.getString("usuario"));
        m.setAccion(rs.getString("accion"));
        m.setDetalle(rs.getString("detalle"));
        m.setFecha(rs.getTimestamp("fecha"));
        m.setTren(rs.getString("tren"));
        return m;
    });
}
}
