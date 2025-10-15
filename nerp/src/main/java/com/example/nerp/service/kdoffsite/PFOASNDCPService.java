package com.example.nerp.service.kdoffsite;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.nerp.model.kdoffsite.PFOASNDCP;

@Service
public class PFOASNDCPService {
    private final JdbcTemplate jdbc;

    public PFOASNDCPService(@Qualifier("db2JdbcTemplate") JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @SuppressWarnings("deprecation")
    public List<PFOASNDCP> buscarPorBoxYPart(String boxSerialNo, String partNumber) {
        String sql = "SELECT ASSRL, ASPRT, ASPRC, ASQTY, ASDECD, ASDECT, ASDECU " +
                "FROM RLMPRDBASD.PFCCASNK p " +
                "JOIN RLMPRDBASD.PFOASNDCP p1 ON p.BOX_SERIAL_NO = p1.ASCAS " +
                "WHERE p.BOX_SERIAL_NO = ? AND p1.ASPRT = ?";

        return jdbc.query(sql, new Object[] { boxSerialNo, partNumber }, (rs, rowNum) -> {
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
