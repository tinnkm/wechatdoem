package com.tinnkm.application.util.wechat.model;

/**
 * @author tinnkm
 */
public class AccessToken {
    private String accessToken;
    /**
     * 过期时间默认是7200s，单位为s
     */
    private int expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
