package com.tinnkm.application.util.wechat.model;

import java.util.Arrays;
import java.util.List;

public class Menu {
    private List<Button> button;

    public Menu(List<Button> button) {
        this.button = button;
    }
    public Menu(Button ...button) {
        this.button = Arrays.asList(button);
    }

    public Menu() {
    }

    public List<Button> getButton() {
        return button;
    }

    public void setButton(List<Button> button) {
        this.button = button;
    }
}
