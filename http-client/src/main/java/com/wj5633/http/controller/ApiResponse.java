package com.wj5633.http.controller;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/2/28 1:35
 * @description
 */

@Data
public class ApiResponse<T> implements Serializable {
    private int code;
    private String error;
    private T data;

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;

    public static <T> ApiResponse<T> of(int code, String error, T data) {
        ApiResponse<T> response = new ApiResponse<T>();
        response.setCode(code);
        response.setError(error);
        response.setData(data);
        return response;
    }

    public static <T> ApiResponse<T> success(T data) {
        return of(SUCCESS, null, data);
    }

    public static <T> ApiResponse<T> success() {
        return success(null);
    }

    public static <T> ApiResponse<T> error(int code, String error) {
        return of(code, error, null);
    }

    public static <T> ApiResponse<T> error(String error) {
        return error(ERROR, error);
    }

    public boolean isSuccess() {
        return code == SUCCESS;
    }
}
