package com.tinnkm.application.util.result;

public enum  ResultCode {
    Success(1),Failed(0),Error(-1);

    private int value;

    public int getValue() {
        return value;
    }

    ResultCode(int i) {
        this.value = i;
    }
}
