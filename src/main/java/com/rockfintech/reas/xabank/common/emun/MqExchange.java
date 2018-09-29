package com.rockfintech.reas.xabank.common.emun;

public enum MqExchange {
    XANOTICE("ps.ex.xinanbank");

    private final String exchange;

    MqExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getExchange() {
        return exchange;
    }
}
