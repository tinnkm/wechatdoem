package com.tinnkm.application.service;

import com.tinnkm.application.util.result.Result;

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
}
