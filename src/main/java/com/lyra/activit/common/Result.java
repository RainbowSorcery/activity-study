package com.lyra.activit.common;

public class Result<T> {
    private Integer statusCode;
    private String message;
    private Boolean success;
    private T data;

    public static <T> Result<T> ok(T data) {
        return new Result<>(200, "成功", true, data);
    }


    public static <T> Result<T> ok() {
        return new Result<>(200, "成功", true);
    }


    public static <T> Result<T> error(T data) {
        return new Result<>(500, "失败", false, data);
    }

    public static <T> Result<T> error() {
        return new Result<>(500, "失败", false);
    }

    public Result(Integer statusCode, String message, Boolean success) {
        this.statusCode = statusCode;
        this.message = message;
        this.success = success;
    }

    public Result(Integer statusCode, String message, Boolean success, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.success = success;
        this.data = data;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
