package com.rockfintech.reas.xabank.common.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 常用常量
 */
public class Constants {

    /** TRUE  /FALSE */
    public static final String TRUE = "TRUE";
    public static final String FALSE = "FALSE";

    /** 字符常量 */
    public static final String DEFAULT_CHARSET = "UTF-8";

    /**  验签产品类型    */
    public static final String PRODUCT_NETPAY = "netpay";

    /** 验签产品类型集合    */
    public static final List<String> PRODUCT_LIST = new ArrayList<>();

    static {
        PRODUCT_LIST.add(PRODUCT_NETPAY);
    }

    /**  个人开户   */
    public static final String TX_3001 = "3001";
    /**  企业开户   */
    public static final String TX_3002 = "3002";
    /**  增印章   */
    public static final String TX_3011 = "3011";
    /**  修改印章   */
    public static final String TX_3012 = "3012";
    /**  删除印章   */
    public static final String TX_3013 = "3013";
    /**  查询印章   */
    public static final String TX_3014 = "3014";
    /**  创建合同   */
    public static final String TX_3201 = "3201";
    /**  签署合同   */
    public static final String TX_3206 = "3206";
    /**  合同查询   */
    public static final String TX_3210 = "3210";

    /**  用户类型   */
    public static final Integer USER_PERSON = 1;
    public static final Integer USER_ENTERPRISE = 2;

    /**   数据流类型 1印章图片PNG*/
    public static final Integer CLOB_TYPE_SEAL = 1;
    /**   数据流类型 2合同文件PDF*/
    public static final Integer CLOB_TYPE_CONTR = 2;

    /**    状态 开关    */
    public static final Integer STATUS_ON = 1;

    public static final Integer STATUS_OFF = 0;

    /** 开户类型 */
    public static final String OPEN_TYEP_PERSON = "1";
    public static final String OPEN_TYEP_COM = "2";

    /**     用户是不是渠道账户标识     */

    public static final Integer USER_CHANNEL_TRUE = 1;

    public static final Integer USER_CHANNEL_FALSE = 0;

    /**     模板是否启用     */

    public static final Integer TEMPLATE_ON = 1;

    public static final Integer TEMPLATE_OFF = 0;

    /**     模板是否关联     */

    public static final Integer TEMPLATE_RELATION_ON = 1;

    public static final Integer TEMPLATE_RELATION_OFF = 0;

    /**     合同是否代签     */

    public static final Integer CONTRACT_SIGN_ON = 1;

    public static final Integer CONTRACT_SIGN_OFF = 0;
    /**     合同项目是否检查     */

    public static final Integer CONTRACT_PRO_CHECK_ON = 1;

    public static final Integer CONTRACT_PRO_CHECK_OFF = 0;
}
