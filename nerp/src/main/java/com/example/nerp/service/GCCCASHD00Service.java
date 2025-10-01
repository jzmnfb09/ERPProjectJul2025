package com.example.nerp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.nerp.model.GCCCASHD00;

@Service
public class GCCCASHD00Service {
    private final JdbcTemplate jdbc;

    public GCCCASHD00Service(@Qualifier("db2JdbcTemplate") JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @SuppressWarnings("deprecation")
    public List<GCCCASHD00> buscarPorClave(String filtro) {
        String sql = "SELECT CHD.FCASTRNÑ, " +
                "CHD.FCASCHSÑ, CHD.FCASPLSP, CHD.FCASCSTS, " +
                "CHD.FCASPCKU, CHD.FCASEPST, " +
                "CHD.FCASCRTÑ " +
                "FROM gcmprdbasd.GCCCASHD00 CHD " +
                "JOIN gcmprdbasd.GCCTRNMS00 TM ON TM.FTRNTRNÑ = CHD.FCASTRNÑ " +
                "WHERE SUBSTR(TM.FTRNTRNÑ, 4, 8) = ? ";

        return jdbc.query(sql, new Object[] { filtro }, (rs, rowNum) -> {
            GCCCASHD00 cashd = new GCCCASHD00();
            cashd.setFcastrnñ(rs.getString("FCASTRNÑ"));
            cashd.setFcaschs(rs.getString("FCASCHSÑ"));
            cashd.setFcasplsp(rs.getString("FCASPLSP"));
            cashd.setFcascsts(rs.getString("FCASCSTS"));
            cashd.setFcaspcku(rs.getString("FCASPCKU"));
            cashd.setFcascrtñ(rs.getString("FCASCRTÑ"));
            cashd.setFcasepst(rs.getString("FCASEPST"));
            return cashd;
        });
    }

    
}
