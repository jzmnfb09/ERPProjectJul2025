package com.example.nerp.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.nerp.model.TrainInformationModel;

@Service
public class ConsultaGeneralService {
     private final JdbcTemplate jdbcTemplate;

    public ConsultaGeneralService(@Qualifier("db2JdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @SuppressWarnings("deprecation")
    public List<TrainInformationModel> buscarPorFiltro(String filtro) {
        String sql = "SELECT " +
                "TM.FTRNTRNÑ, TM.FTRNTMS, TM.FTRNCSTN, "+ 
                "TM.FTRNTPS, TM.FTRNNSTN, TM.FTRNCLU, "+
                "T3.OGPLCD, T3.OGSCTG, T3.KDRRNF, "+ 
                "T3.KDRRNT, T3.TRNSTS, T3.RETRBF, "+
                "T3.DVLCTN, T3.STGSTN, T3.TGGRDR, "+
                "T3.SDSPDT, T3.SDSPTM, "+
                "T2.TRAINÑ, T2.OGSCTG AS T2_OGSCTG, T2.CRTSEQ, "+
                "T2.CRTID, T2.DVLCTN AS T2_DVLCTN, T2.CRTTYP, "+
                "T2.SDSPDT AS T2_SDSPDT, T2.SDSPTM AS T2_SDSPTM, "+ 
                "T2.CRTLPT, T2.CNTFLG, T2.CRTSTS, "+
                "T1.CRTSEQ AS T1_CRTSEQ, T1.CNTPSQ, T1.MBPN, "+ 
                "T1.PTCLR, T1.YEAR, T1.MODEL, "+
                "T1.SUPCDE, T1.SPLCTN, T1.CRTLPT AS T1_CRTLPT, "+ 
                "T1.CRTID AS T1_CRTID, "+
                "CHD.FCASCHSÑ, CHD.FCASPLSP, CHD.FCASCSTS, "+
                "CHD.FCASPCKU, CHD.FCASEPST, "+
                "CDT.FCASCHSÑ AS CDT_FCASCHSÑ, CDT.FCASCASQ, CDT.FCASPRTÑ, "+
                "CDT.FCASPCLR, CDT.FCASOQTY, CDT.FCASCSQÑ, CDT.FCASCIDÑ, "+
                "CDT.FCASPLSP AS CDT_FCASPLSP, CDT.FCASPSTS, CDT.FCASSHPQ, "+
                "CDT.FCASSHRQ, CDT.FCASLPNÑ, CDT.FCASPUSR, CDT.FCASPADT, CDT.FCASMCID, CDT.FCASMCST, "+
                "T7.OGPLCD AS T7_OGPLCD, T7.OGSCTG AS T7_OGSCTG, T7.OGCRMM, "+
                "T7.OGRRN, T7.TRAINÑ AS T7_TRAINÑ, T7.CRTID AS T7_CRTID, "+
                "T7.CNTPSQ AS T7_CNTPSQ, T7.CNTPTN, T7.CRTSEQ AS T7_CRTSEQ, "+ 
                "T7.MBPN AS T7_MBPN, T7.PTCLR AS T7_PTCLR, T7.YEAR AS T7_YEAR, "+ 
                "T7.MODEL AS T7_MODEL, T7.MCIDÑ, T7.SHPQTY, "+
                "T7.CRTLPT AS T7_CRTLPT, T7.LBLSNO, T7.SDSPDT AS T7_SDSPDT, "+ 
                "T7.SDSPTM AS T7_SDSPTM, T7.DELDAT, T7.DELTIM, T7.ADNFLG, "+
                "T7.ADNDAT, T7.ADNTIM, T7.ADSPDT, T7.ADSPTM, "+
                "SM.FSRTTRNÑ, SM.FSRTCHSÑ, SM.FSRTPRTÑ, "+
                "SM.FSRTPCLR, SM.FSRTOQTY, SM.FSRTCIDÑ, "+
                "SM.FSRTCSTS, SM.FSRTSHPQ, SM.FSRTSHRQ, "+
                "SDT.FBTDBTHÑ, SDT.FBTDSTSC, SDT.FBTDTRNÑ, "+
                "SDT.FBTDSEQÑ, SDT.FBTDTRFL, SDT.FBTDCTMS, "+
                "SDT.FBTDCUSR "+
                "FROM GCMPRDBASD.GCCTRNMS00 TM " +
                "JOIN GCMPRDBASD.T03OG3P T3 ON TM.FTRNTRNÑ = T3.SPARE3 " +
                "JOIN GCMPRDBASD.T02OG2P T2 ON TM.FTRNTRNÑ = T2.SPARE3 " +
                "JOIN GCMPRDBASD.T01OG1P T1 ON TM.FTRNTRNÑ = T1.SPARE3 " +
                "JOIN GCMPRDBASD.GCCCASHD00 CHD ON TM.FTRNTRNÑ = CHD.FCASTRNÑ " +
                "JOIN GCMPRDBASD.GCCCASDT00 CDT ON TM.FTRNTRNÑ = CDT.FCASTRNÑ " +
                "JOIN GCMPRDBASD.T07SPRP T7 ON TM.FTRNTRNÑ = T7.SPARE3 " +
                "LEFT JOIN GCMPRDBASD.GCCSRTMS00 SM ON TM.FTRNTRNÑ = SM.FSRTTRNÑ " +
                "LEFT JOIN GCMPRDBASD.GCMBTHDT00 SDT ON TM.FTRNTRNÑ = SDT.FBTDTRNÑ " +
                "WHERE SUBSTR(TM.FTRNTRNÑ,4,8) = ?";

        return jdbcTemplate.query(sql, new Object[]{filtro}, this::mapearFila);
    }

    private TrainInformationModel mapearFila(ResultSet rs, int rowNum) throws SQLException {
        TrainInformationModel dto = new TrainInformationModel();
        //GCCTRNMS00
        dto.setFtrntrn(rs.getString("FTRNTRNÑ"));
        dto.setFtrntps(rs.getString("FTRNTPS"));
        dto.setFtrntms(rs.getString("FTRNTMS"));
        dto.setFtrncstn(rs.getString("FTRNCSTN"));
        dto.setFtrnnstn(rs.getString("FTRNNSTN"));
        dto.setFtrnclu(rs.getString("FTRNCLU"));
        //T03OG3P
        dto.setOgplcd(rs.getString("OGPLCD"));
        dto.setOgsctg(rs.getString("OGSCTG"));
        dto.setKdrrnf(rs.getString("KDRRNF"));
        dto.setKdrrnt(rs.getString("KDRRNT"));
        dto.setTrnsts(rs.getString("TRNSTS"));
        dto.setRetrbf(rs.getString("RETRBF"));
        dto.setDvlctn(rs.getString("DVLCTN"));
        dto.setStgstn(rs.getString("STGSTN"));
        dto.setTggrdr(rs.getString("TGGRDR"));
        dto.setSdspdt(rs.getString("SDSPDT"));
        dto.setSdspdt(rs.getString("SDSPDT"));  
        //T02OG2P
        dto.setTrain(rs.getString("TRAINÑ"));
        dto.setT2Ogsctg(rs.getString("T2_OGSCTG"));
        dto.setCrtseq(rs.getString("CRTSEQ"));
        dto.setCrtid(rs.getString("CRTID"));
        dto.setT2Dvlctn(rs.getString("T2_DVLCTN"));
        dto.setCrttyp(rs.getString("CRTTYP"));
        dto.setT2Sdspdt(rs.getString("T2_SDSPDT"));
        dto.setT2Sdspmt(rs.getString("T2_SDSPTM"));
        dto.setCrtlpt(rs.getString("CRTLPT"));
        dto.setCntflg(rs.getString("CNTFLG"));
        dto.setCrtsts(rs.getString("CRTSTS"));
        //T01OG1P
        dto.setT1Crtseq(rs.getString("T1_CRTSEQ"));
        dto.setCntpsq(rs.getString("CNTPSQ"));
        dto.setMbpn(rs.getString("MBPN"));
        dto.setPtclr(rs.getString("PTCLR"));
        dto.setYear(rs.getString("YEAR"));
        dto.setModel(rs.getString("MODEL"));
        dto.setSupcde(rs.getString("SUPCDE"));
        dto.setSplctn(rs.getString("SPLCTN"));
        dto.setT1Crtlpt(rs.getString("T1_CRTLPT"));
        dto.setT1Crtid(rs.getString("T1_CRTID"));
        //GCCCASHD00
        dto.setFcaschs(rs.getString("FCASCHSÑ"));
        dto.setFcasplsp(rs.getString("FCASPLSP"));
        dto.setFcascsts(rs.getString("FCASCSTS"));
        dto.setFcaspcku(rs.getString("FCASPCKU"));
        dto.setFcasepst(rs.getString("FCASEPST"));
        //GCCCASDT00
        dto.setCdtFcaschs(rs.getString("CDT_FCASCHSÑ"));
        dto.setFcascasq(rs.getString("FCASCASQ"));
        dto.setFcasprt(rs.getString("FCASPRTÑ"));
        dto.setFcaspclr(rs.getString("FCASPCLR"));
        dto.setFcasoqty(rs.getString("FCASOQTY"));
        dto.setFcascsq(rs.getString("FCASCSQÑ"));
        dto.setFcascid(rs.getString("FCASCIDÑ"));
        dto.setCdtFcasplsp(rs.getString("CDT_FCASPLSP"));
        dto.setFcaspsts(rs.getString("FCASPSTS"));
        dto.setFcasshpq(rs.getString("FCASSHPQ"));
        dto.setFcasshrq(rs.getString("FCASSHRQ"));
        dto.setFcaslpn(rs.getString("FCASLPNÑ"));
        dto.setFcaspusr(rs.getString("FCASPUSR"));
        dto.setFcaspadt(rs.getString("FCASPADT"));
        dto.setFcasmcid(rs.getString("FCASMCID"));
        dto.setFcasmcst(rs.getString("FCASMCST"));
        //T07SPRP
        dto.setT7Ogplcd(rs.getString("T7_OGPLCD"));
        dto.setT7Ogsctg(rs.getString("T7_OGSCTG"));
        dto.setOgcrmm(rs.getString("OGCRMM"));
        dto.setOgrrn(rs.getString("OGRRN"));
        dto.setT7Train(rs.getString("T7_TRAINÑ"));
        dto.setT7Crtid(rs.getString("T7_CRTID"));
        dto.setT7Cntpsq(rs.getString("T7_CNTPSQ"));
        dto.setCntptn(rs.getString("CNTPTN"));
        dto.setT7Crtseq(rs.getString("T7_CRTSEQ"));
        dto.setT7Mbpn(rs.getString("T7_MBPN"));
        dto.setT7Ptclr(rs.getString("T7_PTCLR"));
        dto.setT7Year(rs.getString("T7_YEAR"));
        dto.setT7Model(rs.getString("T7_MODEL"));
        dto.setMcid(rs.getString("MCIDÑ"));
        dto.setShpqty(rs.getString("SHPQTY"));
        dto.setT7Crtlpt(rs.getString("T7_CRTLPT"));
        dto.setLblsno(rs.getString("LBLSNO"));
        dto.setT7Sdspdt(rs.getString("T7_SDSPDT"));
        dto.setT7Sdssptm(rs.getString("T7_SDSPTM"));
        dto.setDeldat(rs.getString("DELDAT"));
        dto.setDeltim(rs.getString("DELTIM"));
        dto.setAdnflg(rs.getString("ADNFLG"));
        dto.setAdndat(rs.getString("ADNDAT"));
        dto.setAdntim(rs.getString("ADNTIM"));
        dto.setAdspdt(rs.getString("ADSPDT"));
        dto.setAdsptm(rs.getString("ADSPTM"));
        //GCCSRTMS00
        dto.setFsrttrn(rs.getString("FSRTTRNÑ"));
        dto.setFsrtchs(rs.getString("FSRTCHSÑ"));
        dto.setFsrtprt(rs.getString("FSRTPRTÑ"));
        dto.setFsrtpclr(rs.getString("FSRTPCLR"));
        dto.setFsrtoqty(rs.getString("FSRTOQTY"));
        dto.setFsrtcsts(rs.getString("FSRTCIDÑ"));
        dto.setFsrtcsts(rs.getString("FSRTCSTS"));
        dto.setFsrtshpq(rs.getString("FSRTSHPQ"));
        dto.setFsrtshrq(rs.getString("FSRTSHRQ"));

        //GCMBTHDT00
        dto.setFbtdbth(rs.getString("FBTDBTHÑ"));
        dto.setFbtdstsc(rs.getString("FBTDSTSC"));
        dto.setFbtdtrn(rs.getString("FBTDTRNÑ"));
        dto.setFbtdseq(rs.getString("FBTDSEQÑ"));
        dto.setFbtdtrfl(rs.getString("FBTDTRFL"));
        dto.setFbtdctms(rs.getString("FBTDCTMS"));
        dto.setFbtcdusr(rs.getString("FBTDCUSR"));
        
        return dto;
    }
}
