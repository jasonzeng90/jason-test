package com.rockfintech.reas.springboot.vo.req;

import com.rockfintech.reas.springboot.checker.NotNull;


public class BaseReqVo  {

    @NotNull
    private String channelId;
    @NotNull
    private String firstChannelId;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getFirstChannelId() {
        return firstChannelId;
    }

    public void setFirstChannelId(String firstChannelId) {
        this.firstChannelId = firstChannelId;
    }
}
