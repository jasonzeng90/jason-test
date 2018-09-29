package com.rockfintech.reas.xabank.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class XaLog implements Serializable {
    @Id
    @Column(name = "XALOG_ID")
    private String xalogId;

    @Column(name = "CHID")
    private String chid;

    @Column(name = "ACCT_NO")
    private String acctNo;

    @Column(name = "REQ_MSG")
    private String reqMsg;

    @Column(name = "REQ_TIME")
    private Date reqTime;

    @Column(name = "RES_MSG")
    private String resMsg;

    @Column(name = "RES_TIME")
    private Date resTime;

    @Column(name = "RE_CODE")
    private String reCode;

    @Column(name = "RE_MSG")
    private String reMsg;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "REMARK")
    private String remark;

    private static final long serialVersionUID = 1L;

    /**
     * @return XALOG_ID
     */
    public String getXalogId() {
        return xalogId;
    }

    /**
     * @param xalogId
     */
    public void setXalogId(String xalogId) {
        this.xalogId = xalogId;
    }

    /**
     * @return CHID
     */
    public String getChid() {
        return chid;
    }

    /**
     * @param chid
     */
    public void setChid(String chid) {
        this.chid = chid;
    }

    /**
     * @return ACCT_NO
     */
    public String getAcctNo() {
        return acctNo;
    }

    /**
     * @param acctNo
     */
    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    /**
     * @return REQ_MSG
     */
    public String getReqMsg() {
        return reqMsg;
    }

    /**
     * @param reqMsg
     */
    public void setReqMsg(String reqMsg) {
        this.reqMsg = reqMsg;
    }

    /**
     * @return REQ_TIME
     */
    public Date getReqTime() {
        return reqTime;
    }

    /**
     * @param reqTime
     */
    public void setReqTime(Date reqTime) {
        this.reqTime = reqTime;
    }

    /**
     * @return RES_MSG
     */
    public String getResMsg() {
        return resMsg;
    }

    /**
     * @param resMsg
     */
    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    /**
     * @return RES_TIME
     */
    public Date getResTime() {
        return resTime;
    }

    /**
     * @param resTime
     */
    public void setResTime(Date resTime) {
        this.resTime = resTime;
    }

    /**
     * @return RE_CODE
     */
    public String getReCode() {
        return reCode;
    }

    /**
     * @param reCode
     */
    public void setReCode(String reCode) {
        this.reCode = reCode;
    }

    /**
     * @return RE_MSG
     */
    public String getReMsg() {
        return reMsg;
    }

    /**
     * @param reMsg
     */
    public void setReMsg(String reMsg) {
        this.reMsg = reMsg;
    }

    /**
     * @return STATUS
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return REMARK
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", xalogId=").append(xalogId);
        sb.append(", chid=").append(chid);
        sb.append(", acctNo=").append(acctNo);
        sb.append(", reqMsg=").append(reqMsg);
        sb.append(", reqTime=").append(reqTime);
        sb.append(", resMsg=").append(resMsg);
        sb.append(", resTime=").append(resTime);
        sb.append(", reCode=").append(reCode);
        sb.append(", reMsg=").append(reMsg);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}