package com.tinnkm.application.util.result;

/**
 * @author tinnkm
 */

public enum  ResultCode {
    /**
     * 成功
     */
    Success(1),
    /**
     * 失败
     */
    Failed(0),
    /**
     * 错误
     */
    Error(-1);

    private int value;

    public int getValue() {
        return value;
    }

    ResultCode(int i) {
        this.value = i;
    }
}
