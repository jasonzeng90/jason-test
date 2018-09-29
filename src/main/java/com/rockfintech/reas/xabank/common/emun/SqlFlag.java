package com.rockfintech.reas.xabank.common.emun;

public enum SqlFlag {
    Fuzzy("%");

    private final String sqlFlag;

    SqlFlag(String sqlFlag) {
        this.sqlFlag = sqlFlag;
    }

    public String getSqlFlag() {
        return sqlFlag;
    }
}
