package com.rockfintech.reas.xabank.vo.req;

public class XaReqSysHeadVo {

    private String CHANNEL_CODE = "1099";
    private String SOURCE_TYPE = "014";
    private String BRANCH_ID = "101";
    private String USER_ID = "9994";
    private String TRAN_DATE ;
    private String TRAN_TIMESTAMP ;
    private String SEQ_NO ;
    private String SERVICE_CODE = "0110010001";
    private String TRAN_CODE = "01";
    private String TRAN_MODE = "ONLINE";
    private String COMPANY = "XA";
    private String ZONE_CODE = "65";
    private String TERMINAL_CODE = "11111111";
    private String BUSINESS_NO ;

    public String getCHANNEL_CODE() {
        return CHANNEL_CODE;
    }

    public void setCHANNEL_CODE(String CHANNEL_CODE) {
        this.CHANNEL_CODE = CHANNEL_CODE;
    }

    public String getSOURCE_TYPE() {
        return SOURCE_TYPE;
    }

    public void setSOURCE_TYPE(String SOURCE_TYPE) {
        this.SOURCE_TYPE = SOURCE_TYPE;
    }

    public String getBRANCH_ID() {
        return BRANCH_ID;
    }

    public void setBRANCH_ID(String BRANCH_ID) {
        this.BRANCH_ID = BRANCH_ID;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getTRAN_DATE() {
        return TRAN_DATE;
    }

    public void setTRAN_DATE(String TRAN_DATE) {
        this.TRAN_DATE = TRAN_DATE;
    }

    public String getTRAN_TIMESTAMP() {
        return TRAN_TIMESTAMP;
    }

    public void setTRAN_TIMESTAMP(String TRAN_TIMESTAMP) {
        this.TRAN_TIMESTAMP = TRAN_TIMESTAMP;
    }


    public String getSEQ_NO() {
        return SEQ_NO;
    }

    public void setSEQ_NO(String SEQ_NO) {
        this.SEQ_NO = SEQ_NO;
    }

    public String getSERVICE_CODE() {
        return SERVICE_CODE;
    }

    public void setSERVICE_CODE(String SERVICE_CODE) {
        this.SERVICE_CODE = SERVICE_CODE;
    }

    public String getTRAN_CODE() {
        return TRAN_CODE;
    }

    public void setTRAN_CODE(String TRAN_CODE) {
        this.TRAN_CODE = TRAN_CODE;
    }

    public String getTRAN_MODE() {
        return TRAN_MODE;
    }

    public void setTRAN_MODE(String TRAN_MODE) {
        this.TRAN_MODE = TRAN_MODE;
    }

    public String getCOMPANY() {
        return COMPANY;
    }

    public void setCOMPANY(String COMPANY) {
        this.COMPANY = COMPANY;
    }

    public String getZONE_CODE() {
        return ZONE_CODE;
    }

    public void setZONE_CODE(String ZONE_CODE) {
        this.ZONE_CODE = ZONE_CODE;
    }

    public String getTERMINAL_CODE() {
        return TERMINAL_CODE;
    }

    public void setTERMINAL_CODE(String TERMINAL_CODE) {
        this.TERMINAL_CODE = TERMINAL_CODE;
    }

    public String getBUSINESS_NO() {
        return BUSINESS_NO;
    }

    public void setBUSINESS_NO(String BUSINESS_NO) {
        this.BUSINESS_NO = BUSINESS_NO;
    }
}
