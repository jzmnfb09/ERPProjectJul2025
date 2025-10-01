package com.example.nerp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.nerp.model.T01OG1P;


@Service
public class T01OG1PService {
    private final JdbcTemplate jdbc;

    public T01OG1PService(@Qualifier("db2JdbcTemplate") JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @SuppressWarnings("deprecation")
    public List<T01OG1P> buscarPorClave(String filtro) {
        String sql = "SELECT T1.SPARE3, "+
                     "T1.CRTSEQ, T1.CNTPSQ, T1.MBPN, "+ 
                     "T1.PTCLR, T1.YEAR, T1.MODEL, "+
                     "T1.SUPCDE, T1.SPLCTN, T1.CRTLPT, "+ 
                     "T1.CRTID, CS.FCASPLSP "+
                     "FROM gcmprdbasd.T01OG1P T1 " +
                     "JOIN gcmprdbasd.GCCTRNMS00 TM ON TM.FTRNTRNÑ = T1.SPARE3 "+
                     "JOIN GCMPRDBASD.GCCCASHD00 CS ON CS.FCASTRNÑ = T1.SPARE3 " +
                     "WHERE SUBSTR(TM.FTRNTRNÑ, 4, 8) = ? ";

        return jdbc.query(sql, new Object[]{filtro}, (rs, rowNum) -> {
            T01OG1P t01 = new T01OG1P();
            t01.setSpare3(rs.getString("SPARE3"));
            t01.setCrtseq(rs.getString("CRTSEQ"));
            t01.setCntpsq(rs.getString("CNTPSQ"));
            t01.setMbpn(rs.getString("MBPN"));
            t01.setPtclr(rs.getString("PTCLR"));
            t01.setYear(rs.getString("YEAR"));
            t01.setModel(rs.getString("MODEL"));
            t01.setSupcde(rs.getString("SUPCDE"));
            t01.setSplctn(rs.getString("SPLCTN"));
            t01.setCrtlpt(rs.getString("CRTLPT"));
            t01.setCrtid(rs.getString("CRTID"));
            t01.setFcasplsp(rs.getString("FCASPLSP"));
            return t01;
        });
    }
}
