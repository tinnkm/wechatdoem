package com.tinnkm.application.util.iview.enums;

/**
 * @author quantdo_wh02
 * @version 1.0
 * @classname ColumnTypeEnum
 * @description iview的table组件中的column对应的类型
 * @date 2018/6/12 10:14
 **/
public enum  ColumnTypeEnum {
    INDEX("index"),
    SELECTION("selection"),
    EXPAND("expand"),
    HTML("html");

    private String value;
    ColumnTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
