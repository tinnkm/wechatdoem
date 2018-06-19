package com.tinnkm.application.service;

import com.tinnkm.application.model.ApprovalParams;
import com.tinnkm.application.util.result.Result;
import org.springframework.data.domain.Pageable;

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
     * @param pageable 分页信息
     * @return table数据
     */
    Result getApprovalList(ApprovalParams approvalParams,Pageable pageable);
}
