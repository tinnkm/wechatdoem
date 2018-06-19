package com.tinnkm.application.util.result;

/**
 * 返回结果封装
 * 此类不适合使用单例因为对于每个请求而言都应该是一个新的Result
 * @author tinnkm
 * @param <T>
 */
public class Result<T> {
    private ResultCode code;
    private String message;
    private T data;
    private static final String SUCCESS_MSG = "operation success";
    private static final String FAILED_MSG = "operation failed";
    private static final String ERROR_MSG = "operation error";

    private Result(){

    }

    private static <T> Result<T> getInstance(ResultCode resultCode,String msg,T data){
        Result<T> instance = new Result<>();
        instance.setCode(resultCode);
        instance.setMessage(msg);
        instance.setData(data);
        return instance;
    }


    public static Result success(){
        return getInstance(ResultCode.SUCCESS,SUCCESS_MSG,null);
    }
    public static <T> Result<T> success(String msg,T data){
        return getInstance(ResultCode.SUCCESS,msg,data);
    }
    public static <T> Result<T> success(T data){
        return getInstance(ResultCode.SUCCESS,SUCCESS_MSG,data);
    }

    public static Result failed(String msg){
        return getInstance(ResultCode.FAILED,msg,null);
    }
    public static Result failed(){
        return getInstance(ResultCode.FAILED,FAILED_MSG,null);
    }
    public static <T> Result<T> failed(String msg,T data){
        return getInstance(ResultCode.FAILED,msg,data);
    }
    public static <T> Result<T> failed(T data){
        return getInstance(ResultCode.FAILED,FAILED_MSG,data);
    }
    public static Result error(String msg){
        return getInstance(ResultCode.ERROR,msg,null);
    }
    public static Result error(){
        return getInstance(ResultCode.ERROR,ERROR_MSG,null);
    }
    public static <T> Result<T> error(String msg,T data){
        return getInstance(ResultCode.ERROR,msg,data);
    }
    public static <T> Result<T> error(T data){
        return getInstance(ResultCode.ERROR,ERROR_MSG,data);
    }
    public static Result error(Throwable throwable){
        return getInstance(ResultCode.ERROR,ERROR_MSG,throwable.getLocalizedMessage());
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
