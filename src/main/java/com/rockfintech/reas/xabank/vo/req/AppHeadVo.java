package com.rockfintech.reas.xabank.vo.req;

public class AppHeadVo {

    private String PGUP_OR_PGDN = "1";
    private String TOTAL_NUM = "999";
    private String CURRENT_NUM = "1";
    private String PAGE_START;
    private String PAGE_END ;
    private String TOTAL_ROWS ;
    private String TOTAL_PAGES ;
    private String TOTAL_FLAG = "N";

    public String getPGUP_OR_PGDN() {
        return PGUP_OR_PGDN;
    }

    public void setPGUP_OR_PGDN(String PGUP_OR_PGDN) {
        this.PGUP_OR_PGDN = PGUP_OR_PGDN;
    }

    public String getTOTAL_NUM() {
        return TOTAL_NUM;
    }

    public void setTOTAL_NUM(String TOTAL_NUM) {
        this.TOTAL_NUM = TOTAL_NUM;
    }

    public String getCURRENT_NUM() {
        return CURRENT_NUM;
    }

    public void setCURRENT_NUM(String CURRENT_NUM) {
        this.CURRENT_NUM = CURRENT_NUM;
    }

    public String getPAGE_START() {
        return PAGE_START;
    }

    public void setPAGE_START(String PAGE_START) {
        this.PAGE_START = PAGE_START;
    }

    public String getPAGE_END() {
        return PAGE_END;
    }

    public void setPAGE_END(String PAGE_END) {
        this.PAGE_END = PAGE_END;
    }

    public String getTOTAL_ROWS() {
        return TOTAL_ROWS;
    }

    public void setTOTAL_ROWS(String TOTAL_ROWS) {
        this.TOTAL_ROWS = TOTAL_ROWS;
    }

    public String getTOTAL_PAGES() {
        return TOTAL_PAGES;
    }

    public void setTOTAL_PAGES(String TOTAL_PAGES) {
        this.TOTAL_PAGES = TOTAL_PAGES;
    }

    public String getTOTAL_FLAG() {
        return TOTAL_FLAG;
    }

    public void setTOTAL_FLAG(String TOTAL_FLAG) {
        this.TOTAL_FLAG = TOTAL_FLAG;
    }
}
