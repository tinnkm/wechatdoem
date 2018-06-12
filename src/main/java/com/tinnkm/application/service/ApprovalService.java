package com.tinnkm.application.service;

import com.tinnkm.application.util.result.Result;

import java.util.Date;

/**
 * @author tinnkm
 */
public interface ApprovalService {
    /**
     * 根据业务id更新approval实体
     * @param bizId 业务id
     * @return 更新结果
     */
    Result update(String bizId);

    /**
     * 获取approval记录
     * @param status 审批状态
     * @param createTimeBegin 创建时间开始
     * @param createTimeEnd 创建时间结束
     * @param bizId 业务id
     * @param page 页数
     * @param row 行数
     * @return 分页后的结果
     */
    Result getApprovalList(String status, Date createTimeBegin,Date createTimeEnd,String bizId,int page,int row);
}
