package com.rockfintech.reas.springboot.controller;


import javax.servlet.http.HttpServletRequest;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.rockfintech.reas.springboot.biz.BaseLogger;
import com.rockfintech.reas.springboot.common.constant.ConstError;
import com.rockfintech.reas.springboot.common.res.ComResponse;
import com.rockfintech.reas.springboot.exception.BizException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



public class BaseController extends BaseLogger {



    @ExceptionHandler(Exception.class)
    protected @ResponseBody
    Object exp(HttpServletRequest request, Exception ex) {
        String requestURI = request.getRequestURI();
        ComResponse result = new ComResponse();

        if (ex instanceof JSONException) {
            logger.error("{}传输报文格式异常..", new Object[] { requestURI }, ex);
            result.setCode(ConstError.BZFLAG_EB9998);
            result.setMsg(ConstError.BZFLAG_EB9998_MSG);
        } else if (ex instanceof NullPointerException) {
            logger.error("{}传输报文参数空指针异常..", new Object[] { requestURI }, ex);
            result.setCode(ConstError.BZFLAG_EB9997);
            result.setMsg(ConstError.BZFLAG_EB9997_MSG);
        } else if (ex instanceof NumberFormatException) {
            logger.error("{}传输报文参数异常..", new Object[] { requestURI }, ex);
            result.setCode(ConstError.BZFLAG_EB9997);
            result.setMsg(ConstError.BZFLAG_EB9997_MSG);
        } else if (ex instanceof BizException) {
            BizException bizEx = (BizException) ex;
            logger.error("{}业务层异常..", new Object[] { requestURI }, ex);
            result.setCode(bizEx.messageCode);
            result.setMsg(bizEx.getMessage());
        } else {
            logger.error("{}应用层异常..", new Object[] { requestURI }, ex);
            result.setCode(ComResponse.CODE_EXCEPTION);
            result.setMsg(ex.getMessage());
        }
        logger.error(" return value:" + JSON.toJSONString(result));
        return result;
    }

}