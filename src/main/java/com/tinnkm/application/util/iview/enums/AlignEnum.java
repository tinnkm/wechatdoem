package com.tinnkm.application.util.iview.enums;

/**
 * @author quantdo_wh02
 * @version 1.0
 * @classname AlignEnum
 * @description 对齐方式
 * @date 2018/6/12 10:17
 **/
public enum  AlignEnum {
    /**
     * 左对齐
     */
    LEFT("left"),

    /**
     * 右对齐
     */
    RIGHT("right"),

    /**
     * 居中对齐
     */
    CENTER("center");

    private String value;
    AlignEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
