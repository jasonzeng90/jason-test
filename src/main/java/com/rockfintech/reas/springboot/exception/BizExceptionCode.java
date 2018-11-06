package com.rockfintech.reas.springboot.exception;

/**
 * 异常code类
 * 2018年2月8日下午2:05:18
 * @author simon.sheng
 */
public enum BizExceptionCode {
	SUCCESS("SUCCESS","操作成功"),
	PARA_NULL("PARA0000","参数为空"),
	ORDER_NOT_FOUND("PARA0001","订单不存在"),
	ORDER_STATUS_ILLEGAL("CHECK0001","订单状态不合法"),
	ORDER_DUMPLICAT("CHECK.0002","订单重复"),
	BINDING_NOT_EXIST("CHECK.0003","客户绑定信息不存在"),
	CHANNEL_ILLEGAL("CHECK.0004","渠道信息不合法"),
	ACCOUNT_ILLEGAL("CHECK.0005","账号不存在或者已经注销"),
	ORG_NOTEXIST("CHECK.0006","机构不存在或者已经注销"),
	AUTH_FAILURE("CHECK.0007","越权操作"),
	AMT_NOT_MATCH("CHECK.0008","前后金额不一致"),
	SYSTEM_ERROR("SYS.0001","运行时异常"),
	SIGN_ILLEGAL("SIGN.0001","签名不合法"),
	PARSE_XML_EXCPETION("PARSE.0001","解析xml失败"),
	SOCKET_EXCPETION("SOCKET.0001","socket异常")
	;
	public String code;
	public String desc;
	private BizExceptionCode(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
}
