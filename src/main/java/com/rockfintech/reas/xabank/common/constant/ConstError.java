package com.rockfintech.reas.xabank.common.constant;

public class ConstError {
    /** 0001 参数校验错误 */
    public static final String ER000101_CODE = "010101";
    public static final String ER000101_MESSAGE = "请求参数[%s]格式错误！";
    public static final String BZFLAG_EB9999 = "EB9999";
    public static final String BZFLAG_EB9999_MSG = "未知异常！";
    public static final String BZFLAG_EB9998 = "EB9998";
    public static final String BZFLAG_EB9998_MSG = "报文JSON格式错误！";
    public static final String BZFLAG_EB9997 = "EB9997";
    public static final String BZFLAG_EB9997_MSG = "报文参数类型转换错误！";
    /** 0301合同相关错误  */
    public static final String ER030101_CODE = "030101";
    public static final String ER030101_MESSAGE = "商户号不存在";
    public static final String ER030102_CODE = "030102";
    public static final String ER030102_MESSAGE = "签名信息转换错误";
    /** 0302印章相关错误  */
    public static final String ER030201_CODE = "030201";
    public static final String ER030201_MESSAGE = "用户不存在";
    public static final String ER030202_CODE = "030202";
    public static final String ER030202_MESSAGE = "印章信息服务未记录";
    public static final String ER030203_CODE = "030203";
    public static final String ER030203_MESSAGE = "用户ID不能为空";
    public static final String ER030204_CODE = "030204";
    public static final String ER030204_MESSAGE = "印章信息不存在";
    public static final String ER030205_CODE = "030205";
    public static final String ER030205_MESSAGE = "印章文件信息不存在";
    /** 0303开户相关错误  */
    public static final String ER030301_CODE = "030301";
    public static final String ER030301_MESSAGE = "企业开户固定电话不能为空";
    public static final String ER030302_CODE = "030302";
    public static final String ER030302_MESSAGE = "企业开户经办人不能为空";
    public static final String ER030303_CODE = "030303";
    public static final String ER030303_MESSAGE = "用户信息已存在";
    public static final String ER030304_CODE = "030304";
    public static final String ER030304_MESSAGE = "渠道所属账户已存在";
    /** 0304模板相关错误  */
    public static final String ER030401_CODE = "030401";
    public static final String ER030401_MESSAGE = "该渠道尚未在安兴签创建用户";
    public static final String ER030402_CODE = "030402";
    public static final String ER030402_MESSAGE = "该模板不存在";
    /** 0305模板渠道默认签名域信息错误  */
    public static final String ER030501_CODE = "030501";
    public static final String ER030501_MESSAGE = "模板不存在";
    public static final String ER030502_CODE = "030502";
    public static final String ER030502_MESSAGE = "渠道对应该模板未发现默认签名信息";


    /** 安兴签返回 错误码识别 */
    public static final String ER_ANXIN_FLAG = "errorCode";
}
