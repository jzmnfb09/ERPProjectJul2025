package com.example.nerp.service.kdoffsite;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.nerp.model.kdoffsite.PFYSTIHP;

@Service 
public class PFYSTIHPService {
    private final JdbcTemplate jdbc;

    public PFYSTIHPService(@Qualifier("db2JdbcTemplate") JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @SuppressWarnings("deprecation")
    public List<PFYSTIHP> buscarPorASN(String filtro){
        String sql = "SELECT THSUP, THSUL, THASN, THSCA, THTRL, THDAT, THTIM,  THFRCU, USER_CHG, THSTS "+
                    "FROM RLMPRDBASD.PFYSTIHP p2 "+
	                    "JOIN RLMPRDBASD.PFYSTICHP p1 ON p1.TCASN = p2.THASN "+
                    "WHERE p1.TCCAS = ?";

        return jdbc.query(sql, new Object[]{filtro}, (rs, rowNum) -> {
            PFYSTIHP pfystihp = new PFYSTIHP();
            pfystihp.setThsup(rs.getString("THSUP"));
            pfystihp.setThsul(rs.getString("THSUL"));
            pfystihp.setThasn(rs.getString("THASN"));
            pfystihp.setThsca(rs.getString("THSCA"));
            pfystihp.setThtrl(rs.getString("THTRL"));
            pfystihp.setThdat(rs.getString("THDAT"));
            pfystihp.setThtim(rs.getString("THTIM"));
            pfystihp.setThfrcu(rs.getString("THFRCU"));
            pfystihp.setUser_chg(rs.getString("USER_CHG"));
            pfystihp.setThsts(rs.getString("THSTS"));

            return pfystihp;
        });
    }

    public void editarEstado(String thasn, String newThsts) {
        String sql = "UPDATE RLMPRDBASD.PFYSTIHP p " +
                        "SET p.THSTS = ? " +
                        "WHERE p.THASN = ? ";
        jdbc.update(sql, newThsts, thasn);
    }

    
}
