package com.tinnkm.application.service;

import java.security.NoSuchAlgorithmException;

/**
 * @author tinnkm
 */
public interface SignatureService {
    /**
     * 验证请求的确来自微信
     * @param signature signature
     * @param timestamp timestamp
     * @param nonce nonce
     * @return 返回结果
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     */
    boolean checkSignature(String signature, String timestamp, String nonce) throws NoSuchAlgorithmException;
}
