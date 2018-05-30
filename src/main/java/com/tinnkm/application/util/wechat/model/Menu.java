package com.tinnkm.application.util.wechat.model;

import java.util.Arrays;
import java.util.List;

/**
 * @author tinnkm
 */
public class Menu {
    private List<AbstractButton> button;

    public Menu(List<AbstractButton> button) {
        this.button = button;
    }
    public Menu(AbstractButton ...button) {
        this.button = Arrays.asList(button);
    }

    public Menu() {
    }

    public List<AbstractButton> getButton() {
        return button;
    }

    public void setButton(List<AbstractButton> button) {
        this.button = button;
    }
}
