package com.example.nerp.service.kdoffsite;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.nerp.model.kdoffsite.PFCCASNK;

@Service
public class PFCCASNKService {
    private final JdbcTemplate jdbc;

    public PFCCASNKService(@Qualifier("db2JdbcTemplate") JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @SuppressWarnings("deprecation")
    public List<PFCCASNK> buscarPorASN(String filtro){
        String sql = "SELECT SUPPLIER_NO, ASN_NUMBER, BOX_SERIAL_NO, PART_NUMBER, PART_COLOR, CONFIRM_DEL_QTY, PC_NUMBER, INVENTORY_CATEGORY " +
                "FROM RLMPRDBASD.PFCCASNK pk "+
                "JOIN RLMPRDBASD.PFYSTICHP p1 ON pK.ASN_NUMBER  = p1.TCASN " +
                "WHERE p1.TCCAS = ?";

        return jdbc.query(sql, new Object[]{filtro}, (rs, rowNum) -> {
            PFCCASNK pfccasnk = new PFCCASNK();
            pfccasnk.setSupplier_no(rs.getString("SUPPLIER_NO"));
            pfccasnk.setAsn_number(rs.getString("ASN_NUMBER"));
            pfccasnk.setBox_serial_no(rs.getString("BOX_SERIAL_NO"));
            pfccasnk.setPart_number(rs.getString("PART_NUMBER"));
            pfccasnk.setPart_color(rs.getString("PART_COLOR"));
            pfccasnk.setConfirm_del_qty(rs.getString("CONFIRM_DEL_QTY"));
            pfccasnk.setPc_number(rs.getString("PC_NUMBER"));
            pfccasnk.setInventory_category(rs.getString("INVENTORY_CATEGORY"));
            return pfccasnk;
        });
    }
}
