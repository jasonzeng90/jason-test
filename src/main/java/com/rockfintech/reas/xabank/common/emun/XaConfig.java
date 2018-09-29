package com.rockfintech.reas.xabank.common.emun;

import com.rockfintech.reas.xabank.util.config.SpringContextUtil;

public enum XaConfig {
    ACCT_NO("转出账户",SpringContextUtil.getProperty("XA.ACCT.NO"));

    private String code;
    private String msg;

    XaConfig(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
