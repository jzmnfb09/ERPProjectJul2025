package com.example.nerp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.nerp.model.GCMBTHDT00;

@Service
public class GCMBTHDT00Service {
    private final JdbcTemplate jdbc;

    public GCMBTHDT00Service(@Qualifier("db2JdbcTemplate") JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @SuppressWarnings("deprecation")
    public List<GCMBTHDT00> buscarPorClave(String filtro) {
        String sql = "SELECT " +
                "BTH2.FBTDBTHÑ, BTH2.FBTDSTSC, BTH2.FBTDTRNÑ, BTH2.FBTDSEQÑ, " +
                "BTH2.FBTDTRFL, BTH2.FBTDCTMS, BTH2.FBTDCUSR, T3.SDSPDT, T3.SDSPTM, T3.DVLCTN " +
                "FROM GCMPRDBASD.GCMBTHDT00 BTH2 " +
                "JOIN GCMPRDBASD.T03OG3P T3 ON BTH2.FBTDTRNÑ = T3.SPARE3 " +
                "WHERE BTH2.FBTDBTHÑ =  " +
                "(SELECT BTH.FBTHBTHÑ " +
                "FROM GCMPRDBASD.GCMBTHHD00 BTH " +
                "LEFT JOIN GCMPRDBASD.GCMBTHDT00 BTH2 ON BTH.FBTHBTHÑ = BTH2.FBTDBTHÑ " +
                "LEFT JOIN GCMPRDBASD.GCCTRNMS00 TMS ON BTH2.FBTDTRNÑ = TMS.FTRNTRNÑ " +
                "WHERE SUBSTR(TMS.FTRNTRNÑ,4,8) = ? ) ";

        return jdbc.query(sql, new Object[] { filtro }, (rs, rowNum) -> {
            GCMBTHDT00 bth = new GCMBTHDT00();
            bth.setFbtdbth(rs.getString("FBTDBTHÑ"));
            bth.setFbtdstsc(rs.getString("FBTDSTSC"));
            bth.setFbtdtrn(rs.getString("FBTDTRNÑ"));
            bth.setFbtdseq(rs.getString("FBTDSEQÑ"));
            bth.setFbtdtrfl(rs.getString("FBTDTRFL"));
            bth.setFbtdctms(rs.getString("FBTDCTMS"));
            bth.setFbtdcusr(rs.getString("FBTDCUSR"));
            bth.setSdspdt(rs.getString("SDSPDT"));
            bth.setSdspTm(rs.getString("SDSPTM"));
            bth.setDvlctn(rs.getString("DVLCTN"));
            return bth;
        });
    }

    @SuppressWarnings("deprecation")
    public List<GCMBTHDT00> buscarPorbatch(String filtro) {
        String sql = "SELECT " +
                "BTH2.FBTDBTHÑ, BTH2.FBTDSTSC, BTH2.FBTDTRNÑ, BTH2.FBTDSEQÑ, " +
                "BTH2.FBTDTRFL, BTH2.FBTDCTMS, BTH2.FBTDCUSR, " +
                "T3.SDSPDT, T3.SDSPTM, T3.DVLCTN " +
                "FROM GCMPRDBASD.GCMBTHDT00 BTH2 " +
                "JOIN GCMPRDBASD.T03OG3P T3 ON BTH2.FBTDTRNÑ = T3.SPARE3 " +
                "WHERE BTH2.FBTDBTHÑ = ( " +
                "    SELECT BTH.FBTHBTHÑ " +
                "    FROM GCMPRDBASD.GCMBTHHD00 BTH " +
                "    LEFT JOIN GCMPRDBASD.GCMBTHDT00 BTH2_ ON BTH.FBTHBTHÑ = BTH2_.FBTDBTHÑ " +
                "    LEFT JOIN GCMPRDBASD.GCCTRNMS00 TMS ON BTH2_.FBTDTRNÑ = TMS.FTRNTRNÑ " +
                "    WHERE BTH2_.FBTDBTHÑ = CAST(? AS DECIMAL(11,0)) FETCH FIRST 1 ROWS ONLY) ";

        return jdbc.query(sql, new Object[] { filtro }, (rs, rowNum) -> {
            GCMBTHDT00 bach = new GCMBTHDT00();
            bach.setFbtdbth(rs.getString("FBTDBTHÑ"));
            bach.setFbtdstsc(rs.getString("FBTDSTSC"));
            bach.setFbtdtrn(rs.getString("FBTDTRNÑ"));
            bach.setFbtdseq(rs.getString("FBTDSEQÑ"));
            bach.setFbtdtrfl(rs.getString("FBTDTRFL"));
            bach.setFbtdctms(rs.getString("FBTDCTMS"));
            bach.setFbtdcusr(rs.getString("FBTDCUSR"));
            bach.setSdspdt(rs.getString("SDSPDT"));
            bach.setSdspTm(rs.getString("SDSPTM"));
            bach.setDvlctn(rs.getString("DVLCTN"));
            return bach;
        });
    }
}
