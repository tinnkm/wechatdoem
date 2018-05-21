package com.tinnkm.application.model;

import javax.validation.constraints.Max;
import java.util.List;

public class Menu {
    // 菜单的相应动作类型
    private String type;
    // 菜单标题(原则上主菜单不超过16，子菜单不超过60，统一规定16)
    @Max(16)
    private String name;
    // 菜单的key值，用于消息接口推送
    @Max(128)
    private String key;
    // 网页链接，用户点击菜单可打开链接，不超过1024字节。 type为miniprogram时，不支持小程序的老版本客户端将打开本url。
    @Max(1024)
    private String url;
    // 调用新增永久素材接口返回的合法media_id
    private String mediaId;
    // 小程序的appid要使用时type必须是miniprogram
    private String appid;
    // 小程序的页面路径要使用时type必须是miniprogram
    private String pagepath;
    // 子菜单项
    private List<Menu> subButton;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }

    public List<Menu> getSubButton() {
        return subButton;
    }

    public void setSubButton(List<Menu> subButton) {
        this.subButton = subButton;
    }
}
