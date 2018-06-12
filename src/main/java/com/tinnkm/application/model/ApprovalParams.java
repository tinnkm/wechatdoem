package com.tinnkm.application.model;

import com.tinnkm.application.util.jpa.ext.DynamicConditionAbstract;

import java.util.Date;

/**
 * @author tinnkm
 * @version 1.0
 * @classname ApprovalParams
 * @description 申请查询参数
 * @date 2018/6/12 20:01
 **/
public class ApprovalParams extends DynamicConditionAbstract {
    /**
     * 审核状态
     */
    private String status;
    /**
     * 创建时间开始
     */
    private Date createTimeBegin;
    /**
     * 创建时间结束
     */
    private Date createTimeEnd;
    /**
     * 业务id
     */
    private String bizId;
    /**
     * 页面
     */
    private int page;
    /**
     * 行数
     */
    private int row;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTimeBegin() {
        return createTimeBegin;
    }

    public void setCreateTimeBegin(Date createTimeBegin) {
        this.createTimeBegin = createTimeBegin;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
