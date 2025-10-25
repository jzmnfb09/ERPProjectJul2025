package com.example.nerp.service.kdoffsite;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class BitacoraKDService {

    private final JdbcTemplate jdbc;

    public BitacoraKDService(@Qualifier("db2JdbcTemplate") JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * Registra una acción en la bitácora de CASEs.
     * 
     * @param caseModule El CASE que se modifica
     * @param usuario    Usuario que realiza la acción
     * @param accion     Tipo de acción: CREAR, EDITAR, CAMBIO_ESTADO, etc.
     * @param detalle    Descripción de la acción
     */
    public void registrar(String caseModule, String usuario, String accion, String detalle) {
        String sql = "INSERT INTO Bitacora (case_module, usuario, accion, detalle) VALUES (?, ?, ?, ?)";
        jdbc.update(sql, caseModule, usuario, accion, detalle);
    }
}
