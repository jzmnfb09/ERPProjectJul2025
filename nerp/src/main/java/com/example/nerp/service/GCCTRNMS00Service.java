package com.example.nerp.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import com.example.nerp.model.GCCTRNMS00;

@Service
public class GCCTRNMS00Service {
    private final JdbcTemplate jdbc;

    public GCCTRNMS00Service(@Qualifier("db2JdbcTemplate") JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @SuppressWarnings("deprecation")
    public List<GCCTRNMS00> buscarPorClave(String filtro) {
        try {
        String sql = "SELECT FTRNTRNÑ, FTRNTPS, FTRNTMS, FTRNCSTN, FTRNNSTN, FTRNCLU " +
                     "FROM GCMPRDBASD.GCCTRNMS00 " +
                     "WHERE SUBSTR(FTRNTRNÑ,4, 8) = ? ";

        return jdbc.query(sql, new Object[]{filtro}, (rs, rowNum) -> {
            GCCTRNMS00 trnms = new GCCTRNMS00();
            trnms.setFtrntrnñ(rs.getString("FTRNTRNÑ"));
            trnms.setFtrntps(rs.getString("FTRNTPS"));
            trnms.setFtrntms(rs.getString("FTRNTMS"));
            trnms.setFtrncstn(rs.getString("FTRNCSTN"));
            trnms.setFtrnnstn(rs.getString("FTRNNSTN"));
            trnms.setFtrnclu(rs.getString("FTRNCLU"));
            return trnms;
        });
    } catch (Exception e) {
        e.printStackTrace(); // 💥 muestra el error en la consola
        throw new RuntimeException("Error al consultar GCCTRNMS00: " + e.getMessage());
      }
    }
    
}
