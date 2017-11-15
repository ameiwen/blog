package com.blog.error;

public interface ErrorCodeHandler {
    /**
     * 账户信息失效
     *
     * @return
     */
    String invalidAccount();

    /**
     * 非法请求
     *
     * @return
     */
    String illegalRequst();

}