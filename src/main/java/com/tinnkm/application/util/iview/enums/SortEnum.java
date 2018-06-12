package com.tinnkm.application.util.iview.enums;

/**
 * @author quantdo_wh02
 * @version 1.0
 * @classname SortEnum
 * @description 排序方式
 * @date 2018/6/12 10:22
 **/
public enum SortEnum {
    ASC("asc"),
    DESC("DESC");

    private String value;
    SortEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
