package com.tinnkm.application.controller;

import com.tinnkm.application.service.SignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

/**
 * @author tinnkm
 */
@RestController
public class SignatureController {
    @Autowired
    private SignatureService signatureService;

    /**
     * 配置接口的时候会认证
     * @param signature
     * @param timestamp 时间戳
     * @param nonce
     * @param echostr 随机字符串
     * @return
     * @throws NoSuchAlgorithmException
     */
    @GetMapping("/checkSignature")
    public String checkSignature(String signature,String timestamp,String nonce,String echostr) throws NoSuchAlgorithmException {
        boolean b = signatureService.checkSignature(signature, timestamp, nonce);
        if (b){
            return echostr;
        }
        return null;
    }
}
