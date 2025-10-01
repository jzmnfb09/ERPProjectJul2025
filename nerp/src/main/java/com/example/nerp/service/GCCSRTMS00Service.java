package com.example.nerp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.nerp.model.GCCSRTMS00;


@Service
public class GCCSRTMS00Service {
    private final JdbcTemplate jdbc;

    public GCCSRTMS00Service(@Qualifier("db2JdbcTemplate") JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @SuppressWarnings("deprecation")
    public List<GCCSRTMS00> buscarPorClave(String filtro) {
        String sql = "SELECT "+
                    "SM.FSRTTRNÑ, SM.FSRTCHSÑ, SM.FSRTPRTÑ, "+
                    "SM.FSRTPCLR, SM.FSRTOQTY, SM.FSRTCIDÑ, "+
                    "SM.FSRTCSTS, SM.FSRTSHPQ, SM.FSRTSHRQ "+
                     "FROM GCMPRDBASD.GCCSRTMS00 SM " +
                     "LEFT JOIN gcmprdbasd.GCCTRNMS00 TM ON TM.FTRNTRNÑ = SM.FSRTTRNÑ " +
                     "WHERE SUBSTR(FTRNTRNÑ, 4, 8) = ? ";

        return jdbc.query(sql, new Object[]{filtro}, (rs, rowNum) -> {
            GCCSRTMS00 shms = new GCCSRTMS00();
            shms.setFsrttrn(rs.getString("FSRTTRNÑ"));
            shms.setFsrtchs(rs.getString("FSRTCHSÑ"));
            shms.setFsrtprt(rs.getString("FSRTPRTÑ"));
            shms.setFsrtpclr(rs.getString("FSRTPCLR"));
            shms.setFsrtoqty(rs.getString("FSRTOQTY"));
            shms.setFsrtcids(rs.getString("FSRTCIDÑ"));
            shms.setFsrtcsts(rs.getString("FSRTCSTS"));
            shms.setFsrtshpq(rs.getString("FSRTSHPQ"));
            shms.setFsrtshrq(rs.getString("FSRTSHRQ"));
            return shms;
        });
    }  
}
