package com.blog.error;

public class ErrorCodeEn implements ErrorCodeHandler {
    private static ErrorCodeZh instance = null;

    public static synchronized ErrorCodeZh getInstance() {
        if (instance == null)
            instance = new ErrorCodeZh();
        return instance;
    }


    private final String code1 = "Invalid account";//账户信息失效
    private final String code2 = "Illegal request";//非法请求

    @Override
    public String invalidAccount() {
        return code1;
    }

    @Override
    public String illegalRequst() {
        return code2;
    }

}
