package com.tinnkm.application.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 同一bizid与openid只有一条信息
 * @author tinnkm
 */
@Entity
public class Approval {
    /**
     * 业务id
     */
    @Id
    private String bizId;
    /**
     * 用户openid，每个微信用户的openid时固定的
     */
    private String openId;
    /**
     * 审批状态0表示初始创建，1表示审核中，2表示审核通过，3表示审核不通过
     */
    private int status;
    /**
     * 备注
     */
    private String comment;
    /**
     * 审核通过后的凭证
     */
    private String accessCode;
    /**
     * 如果信息审核不通过，用户重新申请时，讲失败信息标记删除
     */
    private int deleted;
    //region getter/setter

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getOpenId() {
        return openId;
    }


    public void setOpenId(String openId) {
        this.openId = openId;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
    //endregion
}
