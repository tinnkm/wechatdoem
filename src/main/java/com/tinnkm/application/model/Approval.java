package com.tinnkm.application.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/**
 * 同一bizid与openid只有一条信息
 */
@Entity
public class Approval {
    // 业务id
    @Id
    private UUID bizId;
    // 用户的openId
    private String openId;
    // 审批状态
    private int status;
    // 备注
    private String comment;

    //region getter/setter
    public UUID getBizId() {
        return bizId;
    }

    public void setBizId(UUID bizId) {
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
    //endregion
}
