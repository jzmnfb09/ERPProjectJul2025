package com.example.nerp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.nerp.model.GCCCASDT00;

@Service
public class GCCCASDT00Service {
    private final JdbcTemplate jdbc;

    public GCCCASDT00Service(@Qualifier("db2JdbcTemplate") JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @SuppressWarnings("deprecation")
    public List<GCCCASDT00> buscarPorClave(String filtro) {
        String sql = "SELECT CDT.FCASTRNÑ, " +
                     "CDT.FCASCHSÑ, CDT.FCASCASQ, CDT.FCASPRTÑ, "+
                     "CDT.FCASPCLR, CDT.FCASOQTY, CDT.FCASCSQÑ, CDT.FCASCIDÑ, "+
                     "CDT.FCASPLSP, CDT.FCASPSTS, CDT.FCASSHPQ, "+
                     "CDT.FCASSHRQ, CDT.FCASLPNÑ, CDT.FCASPUSR, CDT.FCASPADT, CDT.FCASMCID "+
                     "FROM gcmprdbasd.GCCCASDT00 CDT " +
                     "JOIN gcmprdbasd.GCCTRNMS00 TM ON TM.FTRNTRNÑ = CDT.FCASTRNÑ " +
                     "wHERE SUBSTR(TM.FTRNTRNÑ, 4, 8) = ? ";

        return jdbc.query(sql, new Object[]{filtro}, (rs, rowNum) -> {
            GCCCASDT00 casdt = new GCCCASDT00();
            casdt.setFcastrnñ(rs.getString("FCASTRNÑ"));
            casdt.setFcaschs(rs.getString("FCASCHSÑ"));
            casdt.setFcascasq(rs.getString("FCASCASQ"));
            casdt.setFcasprt(rs.getString("FCASPRTÑ"));
            casdt.setFcaspclr(rs.getString("FCASPCLR"));
            casdt.setFcasoqty(rs.getString("FCASOQTY"));
            casdt.setFcascsq(rs.getString("FCASCSQÑ"));
            casdt.setFcascid(rs.getString("FCASCIDÑ"));
            casdt.setFcasplsp(rs.getString("FCASPLSP"));
            casdt.setFcaspsts(rs.getString("FCASPSTS"));
            casdt.setFcasshpq(rs.getString("FCASSHPQ"));
            casdt.setFcasshrq(rs.getString("FCASSHRQ"));
            casdt.setFcaslpn(rs.getString("FCASLPNÑ"));
            casdt.setFcaspusr(rs.getString("FCASPUSR"));
            casdt.setFcaspadt(rs.getString("FCASPADT"));
            casdt.setFcasmcid(rs.getString("FCASMCID"));
            //
            return casdt;
        });
    }
}
