package com.tinnkm.application.util.result;

/**
 * @author tinnkm
 */

public enum  ResultCode {
    /**
     * 成功
     */
    SUCCESS(1),
    /**
     * 失败
     */
    FAILED(0),
    /**
     * 错误
     */
    ERROR(-1);

    private int value;

    public int getValue() {
        return value;
    }

    ResultCode(int i) {
        this.value = i;
    }
}
