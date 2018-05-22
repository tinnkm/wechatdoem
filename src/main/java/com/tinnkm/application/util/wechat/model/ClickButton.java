package com.tinnkm.application.util.wechat.model;

import com.tinnkm.application.util.wechat.emus.WeChatMenuType;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ClickButton extends Button {
    @Max(128)
    @NotNull
    private String key;

    public ClickButton() {
        super.setType(WeChatMenuType.CLICK.getValue());
    }

    public ClickButton(@Max(128) @NotNull String key) {
        this();
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
