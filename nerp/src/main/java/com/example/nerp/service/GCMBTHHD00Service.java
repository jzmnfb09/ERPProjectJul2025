package com.example.nerp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.nerp.model.GCMBTHHD00;
@Service
public class GCMBTHHD00Service {
    private final JdbcTemplate jdbc;
 
    public GCMBTHHD00Service(@Qualifier("db2JdbcTemplate") JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
@SuppressWarnings("deprecation")
    public List<GCMBTHHD00> buscarPorClave(String filtro) {
        String sql = "SELECT "+
                     "BTH.FBTHBTHÑ, BTH.FBTHPLCD, BTH.FBTHSTSC, BTH.FBTHSTGL, BTH.FBTHDZON, BTH.FBTHSCTG, BTH.FBTHCUSR, BTH.FBTHPLST "+
                     "FROM GCMPRDBASD.GCMBTHHD00 BTH "+
                     "LEFT JOIN GCMPRDBASD.GCMBTHDT00 BTH2 ON BTH.FBTHBTHÑ = BTH2.FBTDBTHÑ "+
                     "LEFT JOIN GCMPRDBASD.GCCTRNMS00 TMS ON BTH2.FBTDTRNÑ = TMS.FTRNTRNÑ " +
                     "WHERE SUBSTR(TMS.FTRNTRNÑ,4,8) = ? ";
                     return jdbc.query(sql, new Object[]{filtro}, (rs, rowNum) -> {
                        GCMBTHHD00 bthh = new GCMBTHHD00();
                        bthh.setFbthbthñ(rs.getString("FBTHBTHÑ"));
                        bthh.setFbthplcd(rs.getString("FBTHPLCD"));
                        bthh.setFbthstsc(rs.getString("FBTHSTSC"));
                        bthh.setFbthstgl(rs.getString("FBTHSTGL"));
                        bthh.setFbthdzon(rs.getString("FBTHDZON"));
                        bthh.setFbthsctg(rs.getString("FBTHSCTG"));
                        bthh.setFbthcusr(rs.getString("FBTHCUSR"));
                        bthh.setFbthplst(rs.getString("FBTHPLST"));
                        return bthh;
                    });
                }
    @SuppressWarnings("deprecation")
    public List<GCMBTHHD00> buscarPorbatchh(String filtro) {
        String sql = "SELECT "+
                     "BTH.FBTHBTHÑ, BTH.FBTHPLCD, BTH.FBTHSTSC, BTH.FBTHSTGL, BTH.FBTHDZON, BTH.FBTHSCTG, BTH.FBTHCUSR, BTH.FBTHPLST "+
                     "FROM GCMPRDBASD.GCMBTHHD00 BTH "+
                     "LEFT JOIN GCMPRDBASD.GCMBTHDT00 BTH2 ON BTH.FBTHBTHÑ = BTH2.FBTDBTHÑ "+
                     "LEFT JOIN GCMPRDBASD.GCCTRNMS00 TMS ON BTH2.FBTDTRNÑ = TMS.FTRNTRNÑ " +
                     "WHERE FBTHBTHÑ = ? FETCH FIRST 1 ROWS ONLY ";
                     return jdbc.query(sql, new Object[]{filtro}, (rs, rowNum) -> {
                        GCMBTHHD00 bachh = new GCMBTHHD00();
                        bachh.setFbthbthñ(rs.getString("FBTHBTHÑ"));
                        bachh.setFbthplcd(rs.getString("FBTHPLCD"));
                        bachh.setFbthstsc(rs.getString("FBTHSTSC"));
                        bachh.setFbthstgl(rs.getString("FBTHSTGL"));
                        bachh.setFbthdzon(rs.getString("FBTHDZON"));
                        bachh.setFbthsctg(rs.getString("FBTHSCTG"));
                        bachh.setFbthcusr(rs.getString("FBTHCUSR"));
                        bachh.setFbthplst(rs.getString("FBTHPLST"));
                        return bachh;
                    });
                }          
}
