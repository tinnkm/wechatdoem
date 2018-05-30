package com.tinnkm.application.util.wechat.model;

import com.tinnkm.application.util.wechat.emus.WeChatMenuType;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * @author tinnkm
 */
public class MiniProgramButton extends AbstractButton {
    @Max(1024)
    @NotNull
    private String url;
    @NotNull
    private String appid;
    @NotNull
    private String pagepath;

    public MiniProgramButton() {
        super.setType(WeChatMenuType.MINIPROGRAM.getValue());
    }


    public MiniProgramButton(@Max(1024) @NotNull String url, @NotNull String appid, @NotNull String pagepath) {
        this();
        this.url = url;
        this.appid = appid;
        this.pagepath = pagepath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

}
