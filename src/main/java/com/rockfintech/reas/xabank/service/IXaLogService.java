package com.rockfintech.reas.xabank.service;

import com.rockfintech.reas.xabank.entity.XaLog;
import com.rockfintech.reas.xabank.exception.BizException;

import java.util.LinkedHashMap;
import java.util.Map;

public interface IXaLogService extends IBaseService<XaLog> {

    void notice(Map<String, Object> map) throws Exception;
}
