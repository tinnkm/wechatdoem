package com.tinnkm.application.util.wechat.model;

import javax.validation.constraints.Max;
import java.util.List;

/**
 * @author tinnkm
 */
public abstract class AbstractButton {
    @Max(16)
    protected String name;
    protected String type;
    private List<AbstractButton> subButton;
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

    public List<AbstractButton> getSubButton() {
        return subButton;
    }

    public void setSubButton(List<AbstractButton> subButton) {
        this.subButton = subButton;
    }
}
