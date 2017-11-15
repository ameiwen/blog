package com.blog.error;


import com.blog.utils.Misc;

public class ErrorCode {
    public static ErrorCodeHandler getInstance(String locale) {
        if (Misc.isStringEmpty(locale)) {
            locale = "zh";//default simple chinese
        }
        ErrorCodeHandler handler = null;
        if ("zh".equals(locale))
            handler = ErrorCodeZh.getInstance();
        else if ("en".equals(locale))
            handler = ErrorCodeEn.getInstance();
        return handler;
    }
}
