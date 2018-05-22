package com.tinnkm.application.util.result;

/**
 * 返回结果封装
 * @param <T>
 */
public class Result<T> {
    private ResultCode code;
    private String message;
    private T data;

    private Result(){

    }
    public static final Result getInstance(){
        return Singleton.RESULT;
    }
    private static class Singleton{
        public static final Result RESULT = new Result();
    }
    public static Result success(String msg){
        Result instance = getInstance();
        instance.setCode(ResultCode.Success);
        instance.setMessage(msg);
        return instance;
    }
    public static Result success(){
        Result instance = getInstance();
        instance.setCode(ResultCode.Success);
        instance.setMessage("success");
        return instance;
    }
    public static <T> Result success(String msg,T data){
        Result instance = getInstance();
        instance.setCode(ResultCode.Success);
        instance.setMessage(msg);
        instance.setData(data);
        return instance;
    }
    public static <T> Result success(T data){
        Result instance = getInstance();
        instance.setCode(ResultCode.Success);
        instance.setMessage("success");
        instance.setData(data);
        return instance;
    }

    public static Result failed(String msg){
        Result instance = getInstance();
        instance.setCode(ResultCode.Failed);
        instance.setMessage(msg);
        return instance;
    }
    public static Result failed(){
        Result instance = getInstance();
        instance.setCode(ResultCode.Failed);
        instance.setMessage("failed");
        return instance;
    }
    public static <T> Result failed(String msg,T data){
        Result instance = getInstance();
        instance.setCode(ResultCode.Failed);
        instance.setMessage(msg);
        instance.setData(data);
        return instance;
    }
    public static <T> Result failed(T data){
        Result instance = getInstance();
        instance.setCode(ResultCode.Failed);
        instance.setMessage("failed");
        instance.setData(data);
        return instance;
    }
    public static Result error(String msg){
        Result instance = getInstance();
        instance.setCode(ResultCode.Error);
        instance.setMessage(msg);
        return instance;
    }
    public static Result error(){
        Result instance = getInstance();
        instance.setCode(ResultCode.Error);
        instance.setMessage("error");
        return instance;
    }
    public static <T> Result error(String msg,T data){
        Result instance = getInstance();
        instance.setCode(ResultCode.Error);
        instance.setMessage(msg);
        instance.setData(data);
        return instance;
    }
    public static <T> Result error(T data){
        Result instance = getInstance();
        instance.setCode(ResultCode.Error);
        instance.setMessage("error");
        instance.setData(data);
        return instance;
    }
    public static Result error(Throwable throwable){
        Result instance = getInstance();
        instance.setCode(ResultCode.Error);
        instance.setMessage(throwable.getLocalizedMessage());
        return instance;
    }
    public ResultCode getCode() {
        return code;
    }

    public void setCode(ResultCode code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
