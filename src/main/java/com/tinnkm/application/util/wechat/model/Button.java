package com.tinnkm.application.util.wechat.model;

import javax.validation.constraints.Max;
import java.util.List;

public abstract class Button {
    @Max(16)
    protected String name;
    protected String type;
    private List<Button> subButton;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Button> getSubButton() {
        return subButton;
    }

    public void setSubButton(List<Button> subButton) {
        this.subButton = subButton;
    }
}
