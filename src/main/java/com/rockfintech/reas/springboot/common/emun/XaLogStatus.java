package com.rockfintech.reas.springboot.common.emun;

public enum XaLogStatus {

    SEND(1),ERROR(2),TIME_OUT(3),SUCCESS(4);

    private Integer status;

    XaLogStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}
