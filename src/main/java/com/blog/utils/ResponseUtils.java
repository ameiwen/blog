package com.blog.utils;


import com.blog.vo.ResponseWrapper;

public class ResponseUtils {

    public static ResponseWrapper errorResponse(String msg) {
        ResponseWrapper response = new ResponseWrapper();
        response.setMsg(msg);
        response.setSuccess(false);
        response.setStatus(-1);
        return response;
    }

    public static ResponseWrapper errorResponse(String msg, int status) {
        ResponseWrapper response = new ResponseWrapper();
        response.setMsg(msg);
        response.setSuccess(false);
        response.setStatus(status);
        return response;
    }

    public static ResponseWrapper successResponse(String msg) {
        ResponseWrapper response = new ResponseWrapper();
        response.setMsg(msg);
        response.setStatus(0);
        response.setSuccess(true);
        return response;
    }

    public static ResponseWrapper successResponse(String msg, int status) {
        ResponseWrapper response = new ResponseWrapper();
        response.setMsg(msg);
        response.setStatus(status);
        response.setSuccess(true);
        return response;
    }
    public static ResponseWrapper successResponse(String key, Object object, String msg) {
        ResponseWrapper response = new ResponseWrapper();
        response.setMsg(msg);
        response.setSuccess(true);
        response.setStatus(0);
        response.addAttribute(key, object);
        return response;
    }

    public static ResponseWrapper successResponse(String msg, int status, String key, Object object) {
        ResponseWrapper response = new ResponseWrapper();
        response.setMsg(msg);
        response.setStatus(status);
        response.setSuccess(true);
        response.addAttribute(key, object);
        return response;
    }


    public static ResponseWrapper errorResponse(String key, Object object, String msg) {
        ResponseWrapper response = new ResponseWrapper();
        response.setMsg(msg);
        response.setSuccess(false);
        response.setStatus(-1);
        response.addAttribute(key, object);
        return response;
    }

}
