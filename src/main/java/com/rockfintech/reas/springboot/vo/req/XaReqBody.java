package com.rockfintech.reas.springboot.vo.req;

import com.rockfintech.reas.springboot.common.emun.XaConfig;

import java.math.BigDecimal;

public class XaReqBody {

    private String czbz = "1";
    private String jdbj = "0";
    private String xzbz = "1";
    private String pzdh = "1";
    private String zhdh = "";
    private BigDecimal jyje;
    private BigDecimal jyje1=BigDecimal.ZERO;
    private BigDecimal jyje2=BigDecimal.ZERO;
    private String xjxmdh = "";
    private String qkfs = "";
    private String mm = "";
    private String tcrq = "";
    private String xtgzh = "";
    private String ywlx = "76";
    private String zydh = "103";
    private String dfzhdh = XaConfig.ACCT_NO.getMsg();

    public String getCzbz() {
        return czbz;
    }

    public void setCzbz(String czbz) {
        this.czbz = czbz;
    }

    public String getJdbj() {
        return jdbj;
    }

    public void setJdbj(String jdbj) {
        this.jdbj = jdbj;
    }

    public String getXzbz() {
        return xzbz;
    }

    public void setXzbz(String xzbz) {
        this.xzbz = xzbz;
    }

    public String getPzdh() {
        return pzdh;
    }

    public void setPzdh(String pzdh) {
        this.pzdh = pzdh;
    }

    public String getZhdh() {
        return zhdh;
    }

    public void setZhdh(String zhdh) {
        this.zhdh = zhdh;
    }

    public BigDecimal getJyje() {
        return jyje;
    }

    public void setJyje(BigDecimal jyje) {
        this.jyje = jyje;
    }

    public BigDecimal getJyje1() {
        return jyje1;
    }

    public void setJyje1(BigDecimal jyje1) {
        this.jyje1 = jyje1;
    }

    public BigDecimal getJyje2() {
        return jyje2;
    }

    public void setJyje2(BigDecimal jyje2) {
        this.jyje2 = jyje2;
    }

    public String getXjxmdh() {
        return xjxmdh;
    }

    public void setXjxmdh(String xjxmdh) {
        this.xjxmdh = xjxmdh;
    }

    public String getQkfs() {
        return qkfs;
    }

    public void setQkfs(String qkfs) {
        this.qkfs = qkfs;
    }

    public String getMm() {
        return mm;
    }

    public void setMm(String mm) {
        this.mm = mm;
    }

    public String getTcrq() {
        return tcrq;
    }

    public void setTcrq(String tcrq) {
        this.tcrq = tcrq;
    }

    public String getXtgzh() {
        return xtgzh;
    }

    public void setXtgzh(String xtgzh) {
        this.xtgzh = xtgzh;
    }

    public String getYwlx() {
        return ywlx;
    }

    public void setYwlx(String ywlx) {
        this.ywlx = ywlx;
    }

    public String getZydh() {
        return zydh;
    }

    public void setZydh(String zydh) {
        this.zydh = zydh;
    }

    public String getDfzhdh() {
        return dfzhdh;
    }

    public void setDfzhdh(String dfzhdh) {
        this.dfzhdh = dfzhdh;
    }
}
