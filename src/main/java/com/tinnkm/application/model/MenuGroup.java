package com.tinnkm.application.model;

import java.util.List;

public class MenuGroup {
    private List<Menu> button;

    public MenuGroup(List<Menu> button) {
        this.button = button;
    }

    public MenuGroup() {
    }

    public List<Menu> getButton() {
        return button;
    }

    public void setButton(List<Menu> button) {
        this.button = button;
    }
}
