package com.tinnkm.application.model;

import com.tinnkm.application.enums.WeChatMune;

public class Mune {
    private WeChatMune type;
    private String name;
    private String key;
    private String url;
    private String mediaId;
    // 小程序的appid要使用时type必须是miniprogram
    private String appid;
    // 小程序的页面路径要使用时type必须是miniprogram
    private String pagepath;
}
