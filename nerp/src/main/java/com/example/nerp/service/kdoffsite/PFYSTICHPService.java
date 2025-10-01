package com.example.nerp.service.kdoffsite;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.nerp.model.kdoffsite.PFYSTICHP;

@Service
public class PFYSTICHPService {
    private final JdbcTemplate jdbc;

    public PFYSTICHPService(@Qualifier("db2JdbcTemplate") JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @SuppressWarnings("deprecation")
    public List<PFYSTICHP> buscarPorCase(String casem) {

    String sql = "SELECT TCSUP, TCSUL, TCASN, TCLOC, TCSTS, TCCAS " +
                 "FROM RLMPRDBASD.PFYSTICHP " +
                 "WHERE RLMPRDBASD.PFYSTICHP.TCCAS = ?";

    return jdbc.query(sql, new Object[]{casem}, (rs, rowNum) -> {
        PFYSTICHP obj = new PFYSTICHP();
        obj.setTcsup(rs.getString("TCSUP"));
        obj.setTcsul(rs.getString("TCSUL"));
        obj.setTcasn(rs.getString("TCASN"));
        obj.setTcloc(rs.getString("TCLOC"));
        obj.setTcsts(rs.getString("TCSTS"));
        obj.setTccas(rs.getString("TCCAS"));
        return obj;
    });
}


}
