package com.example.nerp.model;

public class TrainInformationModel {

    // Campos de GCCTRNMS00
    private String ftrntrn;
    private String ftrntps;
    private String ftrntms;
    private String ftrncstn;
    private String ftrnnstn;
    private String ftrnclu;

    // Campos de T03OG3P
    private String ogplcd;
    private String ogsctg;
    private String kdrrnf;
    private String kdrrnt;
    private String trnsts;
    private String retrbf;
    private String dvlctn;
    private String stgstn;
    private String tggrdr;
    private String sdspdt;
    private String sdssptm;

    // Campos de T02OG2P
    private String train;
    private String t2Ogsctg;
    private String crtseq;
    private String crtid;
    private String t2Dvlctn;
    private String crttyp;
    private String t2Sdspdt;
    private String t2Sdspmt;
    private String crtlpt;
    private String cntflg;
    private String crtsts;

    // Campos de T01OG1P
    private String t1Crtseq;
    private String cntpsq;
    private String mbpn;
    private String ptclr;
    private String year;
    private String model;
    private String supcde;
    private String splctn;
    private String t1Crtlpt;
    private String t1Crtid;

    // Campos de GCCCASHD00
    private String fcaschs;
    private String fcasplsp;
    private String fcascsts;
    private String fcaspcku;
    private String fcasepst;
    private String chdCrtlpt;
    private String shdcrtlpt;
    
    // Campos de GCCCASDT00
    private String cdtFcaschs;
    private String fcascasq;
    private String fcasprt;
    private String fcaspclr;
    private String fcasoqty;
    private String fcascsq;
    private String fcascid;
    private String cdtFcasplsp;
    private String fcaspsts;
    private String fcasshpq;
    private String fcasshrq;
    private String fcaslpn;
    private String fcaspusr;
    private String fcaspadt;
    private String fcasmcid;
    private String fcasmcst;

    // Campos de T07SPRP
    private String t7Ogplcd;
    private String t7Ogsctg;
    private String ogcrmm;
    private String ogrrn;
    private String t7Train;
    private String t7Crtid;
    private String t7Cntpsq;
    private String cntptn;
    private String t7Crtseq;
    private String t7Mbpn;
    private String t7Ptclr;
    private String t7Year;
    private String t7Model;
    private String mcid;
    private String shpqty;
    private String t7Crtlpt;
    private String lblsno;
    private String t7Sdspdt;
    private String t7Sdssptm;
    private String deldat;
    private String deltim;
    private String adnflg;
    private String adndat;
    private String adntim;
    private String adspdt;
    private String adsptm;

    // Campos de GCCSRTMS00
    private String fsrttrn;
    private String fsrtchs;
    private String fsrtprt;
    private String fsrtpclr;
    private String fsrtoqty;
    private String fsrtcids;
    private String fsrtcsts;
    private String fsrtshpq;
    private String fsrtshrq;

    // Campos de GCMBTHDT00
    private String fbtdbth;
    private String fbtdstsc;
    private String fbtdtrn;
    private String fbtdseq;
    private String fbtdtrfl;
    private String fbtdctms;
    private String fbtcdusr;
    private String sdtSdspdt;
    private String sdtSdsptm;
    private String sdtDvlctn;


public String getShdcrtlpt() {
    return shdcrtlpt;
}

public void setShdcrtlpt(String shdcrtlpt) {
    this.shdcrtlpt = shdcrtlpt;
}


    
    public String getFcaspadt() {
        return fcaspadt;
    }
    public void setFcaspadt(String fcaspadt) {
        this.fcaspadt = fcaspadt;
    }
    public String getFtrntrn() {
        return ftrntrn;
    }
    public void setFtrntrn(String ftrntrn) {
        this.ftrntrn = ftrntrn;
    } 
    
    public String getFtrntms() {
        return ftrntms;
    }
    public void setFtrntms(String ftrntms) {
        this.ftrntms = ftrntms;
    }
    public String getFtrncstn() {
        return ftrncstn;
    }
    public void setFtrncstn(String ftrncstn) {
        this.ftrncstn = ftrncstn;
    }
    public String getFtrnnstn() {
        return ftrnnstn;
    }
    public void setFtrnnstn(String ftrnnstn) {
        this.ftrnnstn = ftrnnstn;
    }
    public String getFtrnclu() {
        return ftrnclu;
    }
    public void setFtrnclu(String ftrnclu) {
        this.ftrnclu = ftrnclu;
    }
    public String getOgplcd() {
        return ogplcd;
    }
    public void setOgplcd(String ogplcd) {
        this.ogplcd = ogplcd;
    }
    public String getOgsctg() {
        return ogsctg;
    }
    public void setOgsctg(String ogsctg) {
        this.ogsctg = ogsctg;
    }
    public String getKdrrnf() {
        return kdrrnf;
    }
    public void setKdrrnf(String kdrrnf) {
        this.kdrrnf = kdrrnf;
    }
    public String getKdrrnt() {
        return kdrrnt;
    }
    public void setKdrrnt(String kdrrnt) {
        this.kdrrnt = kdrrnt;
    }
    public String getTrnsts() {
        return trnsts;
    }
    public void setTrnsts(String trnsts) {
        this.trnsts = trnsts;
    }
    public String getRetrbf() {
        return retrbf;
    }
    public void setRetrbf(String retrbf) {
        this.retrbf = retrbf;
    }
    public String getDvlctn() {
        return dvlctn;
    }
    public void setDvlctn(String dvlctn) {
        this.dvlctn = dvlctn;
    }
    public String getStgstn() {
        return stgstn;
    }
    public void setStgstn(String stgstn) {
        this.stgstn = stgstn;
    }
    public String getTggrdr() {
        return tggrdr;
    }
    public void setTggrdr(String tggrdr) {
        this.tggrdr = tggrdr;
    }
    public String getSdspdt() {
        return sdspdt;
    }
    public void setSdspdt(String sdspdt) {
        this.sdspdt = sdspdt;
    }
    public String getSdssptm() {
        return sdssptm;
    }
    public void setSdssptm(String sdssptm) {
        this.sdssptm = sdssptm;
    }
    public String getTrain() {
        return train;
    }
    public void setTrain(String train) {
        this.train = train;
    }
    public String getT2Ogsctg() {
        return t2Ogsctg;
    }
    public void setT2Ogsctg(String t2Ogsctg) {
        this.t2Ogsctg = t2Ogsctg;
    }
    public String getCrtseq() {
        return crtseq;
    }
    public void setCrtseq(String crtseq) {
        this.crtseq = crtseq;
    }
    public String getCrtid() {
        return crtid;
    }
    public void setCrtid(String crtid) {
        this.crtid = crtid;
    }
    public String getT2Dvlctn() {
        return t2Dvlctn;
    }
    public void setT2Dvlctn(String t2Dvlctn) {
        this.t2Dvlctn = t2Dvlctn;
    }
    public String getCrttyp() {
        return crttyp;
    }
    public void setCrttyp(String crttyp) {
        this.crttyp = crttyp;
    }
    public String getT2Sdspdt() {
        return t2Sdspdt;
    }
    public void setT2Sdspdt(String t2Sdspdt) {
        this.t2Sdspdt = t2Sdspdt;
    }
    public String getT2Sdspmt() {
        return t2Sdspmt;
    }
    public void setT2Sdspmt(String t2Sdspmt) {
        this.t2Sdspmt = t2Sdspmt;
    }
    public String getCrtlpt() {
        return crtlpt;
    }
    public void setCrtlpt(String crtlpt) {
        this.crtlpt = crtlpt;
    }
    public String getCntflg() {
        return cntflg;
    }
    public void setCntflg(String cntflg) {
        this.cntflg = cntflg;
    }
    public String getCrtsts() {
        return crtsts;
    }
    public void setCrtsts(String crtsts) {
        this.crtsts = crtsts;
    }
    public String getT1Crtseq() {
        return t1Crtseq;
    }
    public void setT1Crtseq(String t1Crtseq) {
        this.t1Crtseq = t1Crtseq;
    }
    public String getCntpsq() {
        return cntpsq;
    }
    public void setCntpsq(String cntpsq) {
        this.cntpsq = cntpsq;
    }
    public String getMbpn() {
        return mbpn;
    }
    public void setMbpn(String mbpn) {
        this.mbpn = mbpn;
    }
    public String getPtclr() {
        return ptclr;
    }
    public void setPtclr(String ptclr) {
        this.ptclr = ptclr;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getSupcde() {
        return supcde;
    }
    public void setSupcde(String supcde) {
        this.supcde = supcde;
    }
    public String getSplctn() {
        return splctn;
    }
    public void setSplctn(String splctn) {
        this.splctn = splctn;
    }
    public String getT1Crtlpt() {
        return t1Crtlpt;
    }
    public void setT1Crtlpt(String t1Crtlpt) {
        this.t1Crtlpt = t1Crtlpt;
    }
    public String getT1Crtid() {
        return t1Crtid;
    }
    public void setT1Crtid(String t1Crtid) {
        this.t1Crtid = t1Crtid;
    }
    public String getFcaschs() {
        return fcaschs;
    }
    public void setFcaschs(String fcaschs) {
        this.fcaschs = fcaschs;
    }
    public String getFcasplsp() {
        return fcasplsp;
    }
    public void setFcasplsp(String fcasplsp) {
        this.fcasplsp = fcasplsp;
    }
    public String getFcascsts() {
        return fcascsts;
    }
    public void setFcascsts(String fcascsts) {
        this.fcascsts = fcascsts;
    }
    public String getFcaspcku() {
        return fcaspcku;
    }
    public void setFcaspcku(String fcaspcku) {
        this.fcaspcku = fcaspcku;
    }
    public String getFcasepst() {
        return fcasepst;
    }
    public void setFcasepst(String fcasepst) {
        this.fcasepst = fcasepst;
    }
    public String getChdCrtlpt() {
        return chdCrtlpt;
    }
    public void setChdCrtlpt(String chdCrtlpt) {
        this.chdCrtlpt = chdCrtlpt;
    }
    public String getCdtFcaschs() {
        return cdtFcaschs;
    }
    public void setCdtFcaschs(String cdtFcaschs) {
        this.cdtFcaschs = cdtFcaschs;
    }
    public String getFcascasq() {
        return fcascasq;
    }
    public void setFcascasq(String fcascasq) {
        this.fcascasq = fcascasq;
    }
    public String getFcasprt() {
        return fcasprt;
    }
    public void setFcasprt(String fcasprt) {
        this.fcasprt = fcasprt;
    }
    public String getFcaspclr() {
        return fcaspclr;
    }
    public void setFcaspclr(String fcaspclr) {
        this.fcaspclr = fcaspclr;
    }
    public String getFcasoqty() {
        return fcasoqty;
    }
    public void setFcasoqty(String fcasoqty) {
        this.fcasoqty = fcasoqty;
    }
    public String getFcascsq() {
        return fcascsq;
    }
    public void setFcascsq(String fcascsq) {
        this.fcascsq = fcascsq;
    }
    public String getFcascid() {
        return fcascid;
    }
    public void setFcascid(String fcascid) {
        this.fcascid = fcascid;
    }
    public String getCdtFcasplsp() {
        return cdtFcasplsp;
    }
    public void setCdtFcasplsp(String cdtFcasplsp) {
        this.cdtFcasplsp = cdtFcasplsp;
    }
    public String getFcaspsts() {
        return fcaspsts;
    }
    public void setFcaspsts(String fcaspsts) {
        this.fcaspsts = fcaspsts;
    }
    public String getFcasshpq() {
        return fcasshpq;
    }
    public void setFcasshpq(String fcasshpq) {
        this.fcasshpq = fcasshpq;
    }
    public String getFcasshrq() {
        return fcasshrq;
    }
    public void setFcasshrq(String fcasshrq) {
        this.fcasshrq = fcasshrq;
    }
    public String getFcaslpn() {
        return fcaslpn;
    }
    public void setFcaslpn(String fcaslpn) {
        this.fcaslpn = fcaslpn;
    }
    public String getFcaspusr() {
        return fcaspusr;
    }
    public void setFcaspusr(String fcaspusr) {
        this.fcaspusr = fcaspusr;
    }
    public String getFcasmcid() {
        return fcasmcid;
    }
    public void setFcasmcid(String fcasmcid) {
        this.fcasmcid = fcasmcid;
    }
    public String getFcasmcst() {
        return fcasmcst;
    }
    public void setFcasmcst(String fcasmcst) {
        this.fcasmcst = fcasmcst;
    }
    public String getT7Ogplcd() {
        return t7Ogplcd;
    }
    public void setT7Ogplcd(String t7Ogplcd) {
        this.t7Ogplcd = t7Ogplcd;
    }
    public String getT7Ogsctg() {
        return t7Ogsctg;
    }
    public void setT7Ogsctg(String t7Ogsctg) {
        this.t7Ogsctg = t7Ogsctg;
    }
    public String getOgcrmm() {
        return ogcrmm;
    }
    public void setOgcrmm(String ogcrmm) {
        this.ogcrmm = ogcrmm;
    }
    public String getOgrrn() {
        return ogrrn;
    }
    public void setOgrrn(String ogrrn) {
        this.ogrrn = ogrrn;
    }
    public String getT7Train() {
        return t7Train;
    }
    public void setT7Train(String t7Train) {
        this.t7Train = t7Train;
    }
    public String getT7Crtid() {
        return t7Crtid;
    }
    public void setT7Crtid(String t7Crtid) {
        this.t7Crtid = t7Crtid;
    }
    public String getT7Cntpsq() {
        return t7Cntpsq;
    }
    public void setT7Cntpsq(String t7Cntpsq) {
        this.t7Cntpsq = t7Cntpsq;
    }
    public String getCntptn() {
        return cntptn;
    }
    public void setCntptn(String cntptn) {
        this.cntptn = cntptn;
    }
    public String getT7Crtseq() {
        return t7Crtseq;
    }
    public void setT7Crtseq(String t7Crtseq) {
        this.t7Crtseq = t7Crtseq;
    }
    public String getT7Mbpn() {
        return t7Mbpn;
    }
    public void setT7Mbpn(String t7Mbpn) {
        this.t7Mbpn = t7Mbpn;
    }
    public String getT7Ptclr() {
        return t7Ptclr;
    }
    public void setT7Ptclr(String t7Ptclr) {
        this.t7Ptclr = t7Ptclr;
    }
    public String getT7Year() {
        return t7Year;
    }
    public void setT7Year(String t7Year) {
        this.t7Year = t7Year;
    }
    public String getT7Model() {
        return t7Model;
    }
    public void setT7Model(String t7Model) {
        this.t7Model = t7Model;
    }
    public String getMcid() {
        return mcid;
    }
    public void setMcid(String mcid) {
        this.mcid = mcid;
    }
    public String getShpqty() {
        return shpqty;
    }
    public void setShpqty(String shpqty) {
        this.shpqty = shpqty;
    }
    public String getT7Crtlpt() {
        return t7Crtlpt;
    }
    public void setT7Crtlpt(String t7Crtlpt) {
        this.t7Crtlpt = t7Crtlpt;
    }
    public String getLblsno() {
        return lblsno;
    }
    public void setLblsno(String lblsno) {
        this.lblsno = lblsno;
    }
    public String getT7Sdspdt() {
        return t7Sdspdt;
    }
    public void setT7Sdspdt(String t7Sdspdt) {
        this.t7Sdspdt = t7Sdspdt;
    }
    public String getT7Sdssptm() {
        return t7Sdssptm;
    }
    public void setT7Sdssptm(String t7Sdssptm) {
        this.t7Sdssptm = t7Sdssptm;
    }
    public String getDeldat() {
        return deldat;
    }
    public void setDeldat(String deldat) {
        this.deldat = deldat;
    }
    public String getDeltim() {
        return deltim;
    }
    public void setDeltim(String deltim) {
        this.deltim = deltim;
    }
    public String getAdnflg() {
        return adnflg;
    }
    public void setAdnflg(String adnflg) {
        this.adnflg = adnflg;
    }
    public String getAdndat() {
        return adndat;
    }
    public void setAdndat(String adndat) {
        this.adndat = adndat;
    }
    public String getAdntim() {
        return adntim;
    }
    public void setAdntim(String adntim) {
        this.adntim = adntim;
    }
    public String getAdspdt() {
        return adspdt;
    }
    public void setAdspdt(String adspdt) {
        this.adspdt = adspdt;
    }
    public String getAdsptm() {
        return adsptm;
    }
    public void setAdsptm(String adsptm) {
        this.adsptm = adsptm;
    }
    public String getFsrttrn() {
        return fsrttrn;
    }
    public void setFsrttrn(String fsrttrn) {
        this.fsrttrn = fsrttrn;
    }
    public String getFsrtchs() {
        return fsrtchs;
    }
    public void setFsrtchs(String fsrtchs) {
        this.fsrtchs = fsrtchs;
    }
    public String getFsrtprt() {
        return fsrtprt;
    }
    public void setFsrtprt(String fsrtprt) {
        this.fsrtprt = fsrtprt;
    }
    public String getFsrtpclr() {
        return fsrtpclr;
    }
    public void setFsrtpclr(String fsrtpclr) {
        this.fsrtpclr = fsrtpclr;
    }
    public String getFsrtoqty() {
        return fsrtoqty;
    }
    public void setFsrtoqty(String fsrtoqty) {
        this.fsrtoqty = fsrtoqty;
    }
    public String getFsrtcids() {
        return fsrtcids;
    }
    public void setFsrtcids(String fsrtcids) {
        this.fsrtcids = fsrtcids;
    }
    public String getFsrtcsts() {
        return fsrtcsts;
    }
    public void setFsrtcsts(String fsrtcsts) {
        this.fsrtcsts = fsrtcsts;
    }
    public String getFsrtshpq() {
        return fsrtshpq;
    }
    public void setFsrtshpq(String fsrtshpq) {
        this.fsrtshpq = fsrtshpq;
    }
    public String getFsrtshrq() {
        return fsrtshrq;
    }
    public void setFsrtshrq(String fsrtshrq) {
        this.fsrtshrq = fsrtshrq;
    }
    public String getFbtdbth() {
        return fbtdbth;
    }
    public void setFbtdbth(String fbtdbth) {
        this.fbtdbth = fbtdbth;
    }
    public String getFbtdstsc() {
        return fbtdstsc;
    }
    public void setFbtdstsc(String fbtdstsc) {
        this.fbtdstsc = fbtdstsc;
    }
    public String getFbtdtrn() {
        return fbtdtrn;
    }
    public void setFbtdtrn(String fbtdtrn) {
        this.fbtdtrn = fbtdtrn;
    }
    public String getFbtdseq() {
        return fbtdseq;
    }
    public void setFbtdseq(String fbtdseq) {
        this.fbtdseq = fbtdseq;
    }
    public String getFbtdtrfl() {
        return fbtdtrfl;
    }
    public void setFbtdtrfl(String fbtdtrfl) {
        this.fbtdtrfl = fbtdtrfl;
    }
    public String getFbtdctms() {
        return fbtdctms;
    }
    public void setFbtdctms(String fbtdctms) {
        this.fbtdctms = fbtdctms;
    }
    public String getFbtcdusr() {
        return fbtcdusr;
    }
    public void setFbtcdusr(String fbtcdusr) {
        this.fbtcdusr = fbtcdusr;
    }
    public String getSdtSdspdt() {
        return sdtSdspdt;
    }
    public void setSdtSdspdt(String sdtSdspdt) {
        this.sdtSdspdt = sdtSdspdt;
    }
    public String getSdtSdsptm() {
        return sdtSdsptm;
    }
    public void setSdtSdsptm(String sdtSdsptm) {
        this.sdtSdsptm = sdtSdsptm;
    }
    public String getSdtDvlctn() {
        return sdtDvlctn;
    }
    public void setSdtDvlctn(String sdtDvlctn) {
        this.sdtDvlctn = sdtDvlctn;
    }
    public String getFtrntps() {
        return ftrntps;
    }
    public void setFtrntps(String ftrntps) {
        this.ftrntps = ftrntps;
    }

    

 }


