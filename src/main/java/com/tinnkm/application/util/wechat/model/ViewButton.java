package com.tinnkm.application.util.wechat.model;

import com.tinnkm.application.util.wechat.emus.WeChatMenuType;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * @author tinnkm
 */
public class ViewButton extends AbstractButton {
    @Max(1024)
    @NotNull
    private String url;

    public ViewButton() {
        super.setType(WeChatMenuType.VIEW.getValue());
    }

    public ViewButton(@Max(1024) @NotNull String url) {
        this();
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
