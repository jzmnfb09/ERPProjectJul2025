package com.example.nerp.service.kdoffsite;

import java.util.List;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.nerp.model.kdoffsite.PFOASNDCP;

@Service
public class PFOASNDCPService {
    private final JdbcTemplate jdbc;

    public PFOASNDCPService(@Qualifier("db2JdbcTemplate") JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @SuppressWarnings("deprecation")
    public List<PFOASNDCP> buscarPorPart(String filtro){
        String sql = "SELECT ASSRL, ASPRT, ASPRC, ASQTY, ASDECD, ASDECT, ASDECU "+
                        "FROM RLMPRDBASD.PFOASNDCP pfo "+
                        "JOIN RLMPRDBASD.PFYSTICHP p ON pfo.ASCAS = p.TCCAS "+
                        "WHERE p.TCCAS = ?";

        return jdbc.query(sql, new Object[]{filtro}, (rs, rowNum) -> {
            PFOASNDCP pfoasndcp = new PFOASNDCP();
            pfoasndcp.setAssrl(rs.getString("ASSRL"));
            pfoasndcp.setAsprt(rs.getString("ASPRT"));
            pfoasndcp.setAsprc(rs.getString("ASPRC"));
            pfoasndcp.setAsqty(rs.getString("ASQTY"));
            pfoasndcp.setAsdecd(rs.getString("ASDECD"));
            pfoasndcp.setAsdect(rs.getString("ASDECT"));
            pfoasndcp.setAsdecu(rs.getString("ASDECU"));
            return pfoasndcp;
        });
    }
}
