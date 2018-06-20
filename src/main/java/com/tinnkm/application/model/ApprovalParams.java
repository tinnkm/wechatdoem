package com.tinnkm.application.model;

import com.tinnkm.application.util.jpa.ext.DynamicConditionInterface;

import java.util.Date;

/**
 * @author tinnkm
 * @version 1.0
 * @classname ApprovalParams
 * @description 申请查询参数
 * @date 2018/6/12 20:01
 **/
public class ApprovalParams implements DynamicConditionInterface {
    /**
     * 审核状态
     */
    private String status;
    /**
     * 创建时间开始
     */
    private Date createTimeAfter;
    /**
     * 创建时间结束
     */
    private Date createTimeBefore;
    /**
     * 业务id
     */
    private String bizIdLike;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTimeAfter() {
        return createTimeAfter;
    }

    public void setCreateTimeAfter(Date createTimeAfter) {
        this.createTimeAfter = createTimeAfter;
    }

    public Date getCreateTimeBefore() {
        return createTimeBefore;
    }

    public void setCreateTimeBefore(Date createTimeBefore) {
        this.createTimeBefore = createTimeBefore;
    }

    public String getBizIdLike() {
        return bizIdLike;
    }

    public void setBizIdLike(String bizIdLike) {
        this.bizIdLike = bizIdLike;
    }

}
