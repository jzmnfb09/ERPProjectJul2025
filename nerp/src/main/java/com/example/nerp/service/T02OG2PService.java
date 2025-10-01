package com.example.nerp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.nerp.model.T02OG2P;

@Service
public class T02OG2PService {
        private final JdbcTemplate jdbc;

    public T02OG2PService(@Qualifier("db2JdbcTemplate") JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @SuppressWarnings("deprecation")
    public List<T02OG2P> buscarPorClave(String filtro) {
        String sql = "SELECT T2.SPARE3, "+
                     "T2.TRAINÑ, T2.OGSCTG, T2.CRTSEQ, "+
                     "T2.CRTID, T2.DVLCTN, T2.CRTTYP, "+
                     "T2.SDSPDT, T2.SDSPTM, "+ 
                     "T2.CRTLPT, T2.CNTFLG, T2.CRTSTS "+
                     "FROM gcmprdbasd.T02OG2P T2 " +
                     "JOIN gcmprdbasd.GCCTRNMS00 TM ON TM.FTRNTRNÑ = T2.SPARE3 " +
                     "WHERE SUBSTR(TM.FTRNTRNÑ, 4, 8) = ? ";

        return jdbc.query(sql, new Object[]{filtro}, (rs, rowNum) -> {
            T02OG2P t02 = new T02OG2P();
            t02.setSpare3(rs.getString("SPARE3"));
            t02.setTrain(rs.getString("TRAINÑ"));
            t02.setOgsctg(rs.getString("OGSCTG"));
            t02.setCrtseq(rs.getString("CRTSEQ"));
            t02.setCrtid(rs.getString("CRTID"));
            t02.setDvlctn(rs.getString("DVLCTN"));
            t02.setCrttyp(rs.getString("CRTTYP"));
            t02.setSdspdt(rs.getString("SDSPDT"));
            t02.setSdspmt(rs.getString("SDSPTM"));
            t02.setCrtlpt(rs.getString("CRTLPT"));
            t02.setCntflg(rs.getString("CNTFLG"));
            t02.setCrtsts(rs.getString("CRTSTS"));
            return t02;
        });
    }
}
