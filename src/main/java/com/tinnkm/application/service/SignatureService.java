package com.tinnkm.application.service;

import java.security.NoSuchAlgorithmException;

public interface SignatureService {
    boolean checkSignature(String signature, String timestamp, String nonce) throws NoSuchAlgorithmException;
}
