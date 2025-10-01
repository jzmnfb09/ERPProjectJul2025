package com.example.nerp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import com.example.nerp.model.T07SPRP;

@Service
public class T07SPRPService {
   private final JdbcTemplate jdbc;

    public T07SPRPService(@Qualifier("db2JdbcTemplate") JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @SuppressWarnings("deprecation")
    public List<T07SPRP> buscarPorClave(String filtro) {
        String sql = "SELECT T7.SPARE3, "+
                     "T7.OGPLCD, T7.OGSCTG, T7.OGCRMM, "+
                     "T7.OGRRN, T7.TRAINÑ, T7.CRTID, "+
                     "T7.CNTPSQ, T7.CNTPTN, T7.CRTSEQ, "+ 
                     "T7.MBPN, T7.PTCLR, T7.YEAR, "+ 
                     "T7.MODEL, T7.MCIDÑ, T7.SHPQTY, "+
                     "T7.CRTLPT, T7.LBLSNO, T7.SDSPDT, "+ 
                     "T7.SDSPTM, T7.DELDAT, T7.DELTIM, T7.ADNFLG, "+
                     "T7.ADNDAT, T7.ADNTIM, T7.ADSPDT, T7.ADSPTM "+
                     "FROM gcmprdbasd.T07SPRP T7 " +
                     "LEFT JOIN gcmprdbasd.GCCTRNMS00 TM ON TM.FTRNTRNÑ = T7.SPARE3 " +
                     "WHERE SUBSTR(TM.FTRNTRNÑ, 4, 8) = ? ";

        return jdbc.query(sql, new Object[]{filtro}, (rs, rowNum) -> {
            T07SPRP t07 = new T07SPRP();
            t07.setSpare3(rs.getString("SPARE3"));
            t07.setOgplcd(rs.getString("OGPLCD"));
            t07.setOgsctg(rs.getString("OGSCTG"));
            t07.setOgcrmm(rs.getString("OGCRMM"));
            t07.setOgrrn(rs.getString("OGRRN"));
            t07.setTrain(rs.getString("TRAINÑ"));
            t07.setCrtid(rs.getString("CRTID"));
            t07.setCntpsq(rs.getString("CNTPSQ"));
            t07.setCntptn(rs.getString("CNTPTN"));
            t07.setCrtseq(rs.getString("CRTSEQ"));
            t07.setMbpn(rs.getString("MBPN"));
            t07.setPtclr(rs.getString("PTCLR"));
            t07.setYear(rs.getString("YEAR"));
            t07.setModel(rs.getString("MODEL"));
            t07.setMcid(rs.getString("MCIDÑ"));
            t07.setShpqty(rs.getString("SHPQTY"));
            t07.setCrtlpt(rs.getString("CRTLPT"));
            t07.setLblsno(rs.getString("LBLSNO"));
            t07.setSdspdt(rs.getString("SDSPDT"));
            t07.setSdsptm(rs.getString("SDSPTM"));
            t07.setDeldat(rs.getString("DELDAT"));
            t07.setDeltim(rs.getString("DELTIM"));
            t07.setAdnflg(rs.getString("ADNFLG"));
            t07.setAdndat(rs.getString("ADNDAT"));
            t07.setAdntim(rs.getString("ADNTIM"));
            t07.setAdspdt(rs.getString("ADSPDT"));
            t07.setAdsptm(rs.getString("ADSPTM"));
            return t07;
        });
    }
}
