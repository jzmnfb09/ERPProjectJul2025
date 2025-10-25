package com.example.nerp.service.inventarios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.nerp.model.inventarios.GCCINVDT00;

@Service
public class GCCINVDT00Service {
    private final JdbcTemplate jdbc;

    public GCCINVDT00Service(@Qualifier("db2JdbcTemplate") JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @SuppressWarnings("deprecation")
    public List<GCCINVDT00> buscarPorLPN(String filtro) {
        String sql = "SELECT FINVPRTÑ, FINVPCLR, FINVLBCD, FINVLSTC, FINVAVLQ, FINVSHPQ, FINVINCG, " +
                "FINVSLRC, FINVSUPÑ, FINVCIDÑ, FINVCHSÑ, FINVFCH1 " +
                "FROM gcmprdbasd.GCCINVDT00 WHERE FINVCSRL = ?";

        return jdbc.query(sql, new Object[] { filtro }, (rs, rowNum) -> {
            GCCINVDT00 gccinvdt00 = new GCCINVDT00();
            gccinvdt00.setFinvprtñ(rs.getString("FINVPRTÑ"));
            gccinvdt00.setFinvpclr(rs.getString("FINVPCLR"));
            gccinvdt00.setFinvlbcd(rs.getString("FINVLBCD"));
            gccinvdt00.setFinvlstc(rs.getString("FINVLSTC"));
            gccinvdt00.setFinvavlq(rs.getString("FINVAVLQ"));
            gccinvdt00.setFinvshpq(rs.getString("FINVSHPQ"));
            gccinvdt00.setFinvincg(rs.getString("FINVINCG"));
            gccinvdt00.setFinvslrc(rs.getString("FINVSLRC"));
            gccinvdt00.setFinvsupñ(rs.getString("FINVSUPÑ"));
            gccinvdt00.setFinvcidñ(rs.getString("FINVCIDÑ"));
            gccinvdt00.setFinvchsñ(rs.getString("FINVCHSÑ"));
            gccinvdt00.setFinvfch1(rs.getString("FINVFCH1"));

            return gccinvdt00;
        });
    }

    public void actualizarLPN(String lpn, String finvlbcd, Integer finvlstc, Integer finvavlq, 
                            Integer finvshpq, String finvincg, String finvslrc, String finvcidñ, 
                            String finvchsñ, String finvfch1){
        String sql = "UPDATE GCMPRDBASD.GCCINVDT00 g "+
                    "SET g.FINVLBCD = ?, g.FINVLSTC = ?, g.FINVAVLQ = ?, g.FINVSHPQ = ?, "+ 
                    "g.FINVINCG = ?, g.FINVSLRC = ?, g.FINVCIDÑ = ?, g.FINVCHSÑ = ?, "+
                    "g.FINVFCH1 = ? WHERE g.FINVCSRL = ?";
        jdbc.update(sql, finvlbcd, finvlstc, finvavlq, finvshpq, finvincg, finvslrc, 
                    finvcidñ, finvchsñ, finvfch1, lpn);
    }

}
