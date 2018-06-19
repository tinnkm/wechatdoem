package com.tinnkm.application.service.impl;

import com.tinnkm.application.service.SignatureService;
import com.tinnkm.application.util.wechat.WeChatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tinnkm
 */
@Service
public class SignatureServiceImpl implements SignatureService {
    @Autowired
    private WeChatUtils weChatUtils;
    @Override
    public boolean checkSignature(String signature, String timestamp, String nonce) {
        return weChatUtils.checkSignature(signature, timestamp, nonce);
    }
}
