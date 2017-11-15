package com.blog.utils;

import javax.servlet.http.HttpServletRequest;

public class SecurityUtil {

    public SecurityUtil() {
    }
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String accept = request.getHeader("Accept");
        String xmlHttpRequest = request.getHeader("X-Requested-With");
        return !Misc.isStringEmpty(accept) && accept.indexOf("application/json") != -1 || !Misc.isStringEmpty(xmlHttpRequest) && xmlHttpRequest.indexOf("XMLHttpRequest") != -1;
    }
}