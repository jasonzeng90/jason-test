package com.rockfintech.reas.springboot.service;

import com.rockfintech.reas.springboot.entity.XaLog;

import java.util.Map;

public interface IXaLogService extends IBaseService<XaLog> {

    void notice(Map<String, Object> map) throws Exception;
}
