package com.tinnkm.application.util.wechat.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * @author tinnkm
 */
public class ClickButton extends AbstractButton {
    @Max(128)
    @NotNull
    private String key;


    public ClickButton(@Max(128) @NotNull String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
