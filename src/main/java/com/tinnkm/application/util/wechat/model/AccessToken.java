package com.tinnkm.application.util.wechat.model;

/**
 * @author tinnkm
 */
public class AccessToken {
    private String token;
    /**
     * 过期时间默认是7200s，单位为s
     */
    private int expiresIn;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
