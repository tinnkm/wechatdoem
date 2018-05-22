package com.tinnkm.application.service.impl;

import com.tinnkm.application.dao.ApprovalDao;
import com.tinnkm.application.model.Approval;
import com.tinnkm.application.service.AuthService;
import com.tinnkm.application.util.wechat.WeChatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private WeChatUtils weChatUtils;
    @Autowired
    private ApprovalDao approvalDao;
    @Override
    public boolean getAuth(String redirectUri, String state) throws IOException, URISyntaxException {
        Map auth = weChatUtils.getAuth(false, redirectUri, state);
        String openId = auth.get("openid").toString();
        Approval byOpenId = approvalDao.findByOpenId(openId);
        if (null == byOpenId){
            Approval approval = new Approval();
            approval.setBizId(UUID.randomUUID());
            approval.setOpenId(openId);
            // 表示初始创建
            approval.setStatus(0);
            approvalDao.saveAndFlush(approval);
        }
        return true;
    }
}
