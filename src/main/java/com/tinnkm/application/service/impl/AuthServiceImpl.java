package com.tinnkm.application.service.impl;

import com.tinnkm.application.dao.ApprovalDao;
import com.tinnkm.application.model.Approval;
import com.tinnkm.application.service.AuthService;
import com.tinnkm.application.util.wechat.WeChatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author tinnkm
 */
@Service
public class AuthServiceImpl implements AuthService {
    private static final String APPROVAL_PREFIX = "WX";
    @Autowired
    private WeChatUtils weChatUtils;
    @Autowired
    private ApprovalDao approvalDao;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Approval getAuth(String code, String state) throws IOException, URISyntaxException {
        Map auth = weChatUtils.getAuth(code);
        String openId = auth.get("openid").toString();
        Approval byOpenId = approvalDao.findByOpenIdAndDeleted(openId,0);
        // 当用户审核不通过时可以重新审核
        if (null == byOpenId || byOpenId.getStatus() == 3){
            // 标记删除历史数据
            if (null != byOpenId){
                byOpenId.setDeleted(1);
                approvalDao.updateSelective(byOpenId);
            }
            // 生成新数据
            Approval approval = new Approval();
            approval.setBizId(getBizId());
            approval.setOpenId(openId);
            approval.setDeleted(0);
            // 表示初始创建
            approval.setStatus(0);
            approvalDao.saveAndFlush(approval);
            return approval;
        }
        return byOpenId;
    }

    private synchronized String getBizId(){
        return APPROVAL_PREFIX+Instant.now();
    }
}
