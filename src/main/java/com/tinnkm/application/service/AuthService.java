package com.tinnkm.application.service;

import com.tinnkm.application.model.Approval;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author tinnkm
 */
public interface AuthService {
    /**
     * 获取授权
     * @param code 微信端返回的code
     * @param state 传递的参数
     * @return 生成新的approval
     * @throws IOException io
     * @throws URISyntaxException uri异常
     */
    Approval getAuth(String code, String state) throws IOException, URISyntaxException;
}
