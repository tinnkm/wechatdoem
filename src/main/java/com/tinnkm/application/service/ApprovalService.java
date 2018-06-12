package com.tinnkm.application.service;

import com.tinnkm.application.model.ApprovalParams;
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
     * 获取审批列表
     * @param approvalParams 审核参数
     * @return table数据
     */
    Result getApprovalList(ApprovalParams approvalParams);
}
