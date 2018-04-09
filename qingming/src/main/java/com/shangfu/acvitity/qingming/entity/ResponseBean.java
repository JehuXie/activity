package com.shangfu.acvitity.qingming.entity;

import java.util.Date;

/**
 * @author JehuXie
 * @Description: ${todo}
 * @date 2018/3/29 0029下午 5:32
 */
public class ResponseBean<T> {

    private int code;
    private String message;
    private String lang;
    private String accessToken;

    private String sign;
    private int source;
    private Date time;
    private T value;

    private ResponseBean() {

    }

    private static class Inner{
        private static ResponseBean res = new ResponseBean();
    }

    public static ResponseBean getResponse(){
        return Inner.res;
    }

    private ResponseBean(int code, T value) {
        this(code, value, "");
    }

    private ResponseBean(int code, T value, String message) {
        this.time = new Date();
        this.lang = "zh_Cn";
        this.source = 201;
        this.code = code;
        this.value = value;
        this.message = message;

    }

    private ResponseBean(int code, String message) {
        this.time = new Date();
        this.lang = "zh_Cn";
        this.source = 201;
        this.code = code;
        this.message = message;
    }

    public static <T> ResponseBean<T> error(String msg, T data) {
        return new ResponseBean<>(200, data, msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public static <T> ResponseBean<T> setSuccess(T value, String message) {
        return new ResponseBean<>(Common.HTTP_SUCCESS, value, message);
    }

    public static <T> ResponseBean<T> setBind(T value, String message) {
        return new ResponseBean<>(Common.HTTP_BIND, value, message);
    }

    public static <T> ResponseBean<T> setSuccess(String message) {
        return new ResponseBean<>(Common.HTTP_SUCCESS, message);
    }

    public static <T> ResponseBean<T> setError(String message) {
        return new ResponseBean<>(Common.HTTP_ERROR, message);
    }

    public static <T> ResponseBean<T> setTokenError(T value, String message) {
        return new ResponseBean<>(Common.HTTP_TOKEN_ERROR, value, message);
    }

}
