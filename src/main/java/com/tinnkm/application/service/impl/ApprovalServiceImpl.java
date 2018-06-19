package com.tinnkm.application.service.impl;

import com.tinnkm.application.dao.ApprovalDao;
import com.tinnkm.application.model.Approval;
import com.tinnkm.application.model.ApprovalParams;
import com.tinnkm.application.service.ApprovalService;
import com.tinnkm.application.util.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author tinnkm
 */
@Service
public class ApprovalServiceImpl implements ApprovalService {
    private final Logger log = LoggerFactory.getLogger(ApprovalServiceImpl.class);
    @Autowired
    private ApprovalDao approvalDao;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result update(String bizId) {
        Approval approval = approvalDao.findByBizId(bizId);
        if (null == approval){
            return Result.failed("系统中未找到对应数据");
        }
        try {
            // 标记提交
            approval.setStatus(1);
            int i = approvalDao.updateSelective(approval);
            return i > 0 ? Result.success() : Result.failed();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return Result.error(e);
        }
    }

    @Override
    public Result getApprovalList(ApprovalParams approvalParams, Pageable pageable) {
        approvalDao.findSelective(approvalParams,pageable);
        return null;
    }
}
