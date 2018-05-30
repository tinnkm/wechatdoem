package com.tinnkm.application.service.impl;

import com.tinnkm.application.dao.ApprovalDao;
import com.tinnkm.application.model.Approval;
import com.tinnkm.application.service.ApprovalService;
import com.tinnkm.application.util.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tinnkm
 */
@Service
public class ApprovalServiceImpl implements ApprovalService {
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
            e.printStackTrace();
            return Result.error(e);
        }
    }
}
