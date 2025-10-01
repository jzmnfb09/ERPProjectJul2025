package com.example.nerp.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.nerp.model.T03OG3P;



@Service
public class T03OG3PService {
    private final JdbcTemplate jdbc;

    public T03OG3PService(@Qualifier("db2JdbcTemplate") JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @SuppressWarnings("deprecation")
    public List<T03OG3P> buscarPorClave(String filtro) {
        String sql = "SELECT T3.SPARE3, "+
                     "T3.OGPLCD, T3.OGSCTG, T3.KDYM_F, "+ 
                     "T3.KDRRNT, T3.TRNSTS, T3.TRNBLD, "+
                     "T3.DVLCTN, T3.STGSTN, T3.TGGRDR, "+
                     "T3.SDSPDT, T3.SDSPTM "+
                     "FROM gcmprdbasd.T03OG3P T3 " +
                     "JOIN gcmprdbasd.GCCTRNMS00 TM ON TM.FTRNTRNÑ = T3.SPARE3 " +
                     "WHERE SUBSTR(TM.FTRNTRNÑ, 4, 8) = ? ";

        return jdbc.query(sql, new Object[]{filtro}, (rs, rowNum) -> {
            T03OG3P t03 = new T03OG3P();
            t03.setSpare3(rs.getString("SPARE3"));
            t03.setOgplcd(rs.getString("OGPLCD"));
            t03.setOgsctg(rs.getString("OGSCTG"));
            t03.setKdrrnf(rs.getString("KDYM_F"));
            t03.setKdrrnt(rs.getString("KDRRNT"));
            t03.setTrnsts(rs.getString("TRNSTS"));
            t03.setTrnbld(rs.getString("TRNBLD"));
            t03.setDvlctn(rs.getString("DVLCTN"));
            t03.setStgstn(rs.getString("STGSTN"));
            t03.setTggrdr(rs.getString("TGGRDR"));
            t03.setSdspdt(rs.getString("SDSPDT"));
            t03.setSdsptm(rs.getString("SDSPTM")); 
            return t03;
        });
    }
    
}
