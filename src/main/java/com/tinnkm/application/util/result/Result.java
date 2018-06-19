package com.tinnkm.application.util.result;

/**
 * 返回结果封装
 * @author tinnkm
 * @param <T>
 */
public class Result<T> {
    private ResultCode code;
    private String message;
    private T data;

    private Result(){

    }
    public static Result getInstance(){
        return Singleton.RESULT;
    }
    private static class Singleton{
        static final Result RESULT = new Result();
    }
    public static Result success(String msg){
        Result instance = getInstance();
        instance.setCode(ResultCode.SUCCESS);
        instance.setMessage(msg);
        instance.setData(null);
        return instance;
    }
    public static Result success(){
        Result instance = getInstance();
        instance.setCode(ResultCode.SUCCESS);
        instance.setMessage("success");
        instance.setData(null);
        return instance;
    }
    public static <T> Result success(String msg,T data){
        Result instance = getInstance();
        instance.setCode(ResultCode.SUCCESS);
        instance.setMessage(msg);
        instance.setData(data);
        return instance;
    }
    public static <T> Result success(T data){
        Result instance = getInstance();
        instance.setCode(ResultCode.SUCCESS);
        instance.setMessage("success");
        instance.setData(data);
        return instance;
    }

    public static Result failed(String msg){
        Result instance = getInstance();
        instance.setCode(ResultCode.FAILED);
        instance.setMessage(msg);
        instance.setData(null);
        return instance;
    }
    public static Result failed(){
        Result instance = getInstance();
        instance.setCode(ResultCode.FAILED);
        instance.setMessage("failed");
        instance.setData(null);
        return instance;
    }
    public static <T> Result failed(String msg,T data){
        Result instance = getInstance();
        instance.setCode(ResultCode.FAILED);
        instance.setMessage(msg);
        instance.setData(data);
        return instance;
    }
    public static <T> Result failed(T data){
        Result instance = getInstance();
        instance.setCode(ResultCode.FAILED);
        instance.setMessage("failed");
        instance.setData(data);
        return instance;
    }
    public static Result error(String msg){
        Result instance = getInstance();
        instance.setCode(ResultCode.ERROR);
        instance.setMessage(msg);
        instance.setData(null);
        return instance;
    }
    public static Result error(){
        Result instance = getInstance();
        instance.setCode(ResultCode.ERROR);
        instance.setMessage("error");
        instance.setData(null);
        return instance;
    }
    public static <T> Result error(String msg,T data){
        Result instance = getInstance();
        instance.setCode(ResultCode.ERROR);
        instance.setMessage(msg);
        instance.setData(data);
        return instance;
    }
    public static <T> Result error(T data){
        Result instance = getInstance();
        instance.setCode(ResultCode.ERROR);
        instance.setMessage("error");
        instance.setData(data);
        return instance;
    }
    public static Result error(Throwable throwable){
        Result instance = getInstance();
        instance.setCode(ResultCode.ERROR);
        instance.setMessage(throwable.getLocalizedMessage());
        instance.setData(null);
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
