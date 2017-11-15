package com.blog.interceptor;

import com.blog.cache.RedisCache;
import com.blog.common.Constants;
import com.blog.model.BitUser;
import com.blog.utils.AlgorithmUtil;
import com.blog.utils.Misc;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


public class CookieInfo {
    private static Logger logger = LogManager.getLogger(CookieInfo.class);

    // 登陆后的用户唯一标识；用户id,会用aes加密
    public static String LOGIN_UUID_COOKIE_NAME = "_userid";

    public static final String EMPTY = "";
    public static final String DOT = ".";
    public static final String COMMA = ",";
    public static final String COLON = ":";
    public static final String LT = "<";
    public static final String GT = ">";
    public static final String ULT = "\\u003c";
    public static final String UGT = "\\u003e";
    public static final String NULL = "null";
    public static final String PERCENT = "%";
    public static String LOGIN_NICK_COOKIE_NAME = "_un";
    public static String LOGIN_EMAIL_COOKIE_NAME = "_ue";
    public static String LOGIN_MOBILE_COOKIE_NAME = "_um";

    public static String LOGIN_COOKIE_USER_NAME = "_uname";

    public static String LOGIN_SHOW_NICK_COOKIE_NAME = "_usn";

    public static String LOGIN_USER_HEAD_PORTRAIT_COOKIE_NAME = "_uhp";

    public static String REDIS_COOKIE_CUSTOMER_PREFIX = "login:customer:";

    public static String COOKIE_RECENT_BROWSE = "_rb";

    public static int LOGIN_EXPIRE_TIME = 3600 * 2; //  // 登陆失效时间 2小时

    public static void addLoginUserNameCookie(String userName, HttpServletRequest request, HttpServletResponse response) {
        // 删除相关cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (LOGIN_COOKIE_USER_NAME.equals(cookie.getName())) {
                    if (!Misc.isStringEmpty(Constants.COOKIE_DOMAIN)
                            && !Constants.COOKIE_DOMAIN.contains("$")) {
                        cookie.setDomain(Constants.COOKIE_DOMAIN);
                    }
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }

        try {
            if (!Misc.isStringEmpty(userName)) {
                Cookie userNameCookie = new Cookie(LOGIN_COOKIE_USER_NAME, URLEncoder.encode(userName, "utf-8"));
                userNameCookie.setMaxAge(LOGIN_EXPIRE_TIME);
                userNameCookie.setPath("/");
                if (!Misc.isStringEmpty(Constants.COOKIE_DOMAIN)
                        && !Constants.COOKIE_DOMAIN.contains("$")) {
                    userNameCookie.setDomain(Constants.COOKIE_DOMAIN);
                }
                response.addCookie(userNameCookie);
            }
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }

    /**
     * 添加登陆时的 cookie
     */
    public static void addLoginCookie(BitUser customer, HttpServletRequest request, HttpServletResponse response) {

        // 删除相关cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (LOGIN_UUID_COOKIE_NAME.equals(cookie.getName())
                        || LOGIN_NICK_COOKIE_NAME.equals(cookie.getName())
                        || LOGIN_SHOW_NICK_COOKIE_NAME.equals(cookie.getName())
                        || LOGIN_USER_HEAD_PORTRAIT_COOKIE_NAME.equals(cookie.getName())) {
                    if (!Misc.isStringEmpty(Constants.COOKIE_DOMAIN)
                            && !Constants.COOKIE_DOMAIN.contains("$")) {
                        cookie.setDomain(Constants.COOKIE_DOMAIN);
                    }
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }

        // 设置登陆 cookie 标识
        String cacheKey = REDIS_COOKIE_CUSTOMER_PREFIX + customer.getId();
        String toEnc = REDIS_COOKIE_CUSTOMER_PREFIX + System.currentTimeMillis() + COLON + customer.getId();

        String enc = AlgorithmUtil.encryptAESToString(toEnc, Constants.UUID_AES_KEY);

        // cookie 的名称为 _uuid
        Cookie uuidCookie = new Cookie(LOGIN_UUID_COOKIE_NAME, enc);
        if (!Misc.isStringEmpty(Constants.COOKIE_DOMAIN)
                && !Constants.COOKIE_DOMAIN.contains("$")) {
            uuidCookie.setDomain(Constants.COOKIE_DOMAIN);
        }
        uuidCookie.setMaxAge(LOGIN_EXPIRE_TIME);
        uuidCookie.setHttpOnly(true);//设置为true后，JavaScript无法获取
        uuidCookie.setPath("/");
        response.addCookie(uuidCookie);

        // 把customerId 放进redis 缓存
        RedisCache redisCache = RedisCache.getInstance();
        redisCache.delCache(cacheKey);

        Map<String, Object> map = new HashMap<>();
        map.put("customerId", customer.getId());
        redisCache.addCache(cacheKey, map);
        redisCache.expireKey(cacheKey, LOGIN_EXPIRE_TIME);
    }

    /**
     * 移除登陆 cookie
     *
     * @param request
     * @param response
     */
    public static void removeLoginCookie(HttpServletRequest request, HttpServletResponse response) {

        // 删除相关cookie
        Cookie[] cookies = request.getCookies();
        String customerId = EMPTY;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (LOGIN_UUID_COOKIE_NAME.equals(cookie.getName())
                        || LOGIN_COOKIE_USER_NAME.equals(cookie.getName())
                        || LOGIN_NICK_COOKIE_NAME.equals(cookie.getName())
                        || LOGIN_SHOW_NICK_COOKIE_NAME.equals(cookie.getName())
                        || LOGIN_USER_HEAD_PORTRAIT_COOKIE_NAME.equals(cookie.getName())) {

                    if (LOGIN_UUID_COOKIE_NAME.equals(cookie.getName())) {
                        customerId = cookie.getValue();
                    }
                    if (!Misc.isStringEmpty(Constants.COOKIE_DOMAIN)
                            && !Constants.COOKIE_DOMAIN.contains("$")) {
                        cookie.setDomain(Constants.COOKIE_DOMAIN);
                    }
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
            if (!Misc.isStringEmpty(customerId)) {
                customerId = AlgorithmUtil.decryptAESToString(customerId, Constants.UUID_AES_KEY);
            }
            String cacheKey = REDIS_COOKIE_CUSTOMER_PREFIX + customerId;
            RedisCache redisCache = RedisCache.getInstance();
            if (redisCache.existsCache(cacheKey)) {
                redisCache.delCache(cacheKey);
            }
        }
    }

    /**
     * 获取最近浏览项目
     *
     * @param request
     */
    public static String getBrowseCookie(HttpServletRequest request) {
        Cookie[] cookieList = null;
        String rbString = null;
        try {
            if (request.getCookies() != null) {
                cookieList = request.getCookies();
                for (Cookie cookie : cookieList) {
                    if (COOKIE_RECENT_BROWSE.equals(cookie.getName())) {
                        rbString = cookie.getValue();
                    }
                }
            }
        } catch (Exception ex) {
            logger.error(ExceptionUtils.getStackTrace(ex));
        }
        return rbString;
    }


    /**
     * 获取登陆用户userId
     *
     * @param request
     * @return
     */
    public static String getCustomerIdByCookie(HttpServletRequest request) {
        Cookie[] cookieList = null;
        String customerId = null;
        try {
            if (request.getCookies() != null) {
                cookieList = request.getCookies();
                for (Cookie cookie : cookieList) {
                    if (LOGIN_UUID_COOKIE_NAME.equals(cookie.getName())) {
                        customerId = cookie.getValue();
                    }
                }
            }else {
                //特殊处理，如果从cookie里拿不到，则从header里拿userUuid
                String userUuid = null;
                Enumeration headerNames = request.getHeaderNames();
                while(headerNames.hasMoreElements()){
                    String key = String.valueOf(headerNames.nextElement());
                    if("_uuid".equals(key)){
                        userUuid = request.getHeader(key);
                        break;
                    }
                }
                return userUuid;
            }
        } catch (Exception ex) {
            logger.error(ExceptionUtils.getStackTrace(ex));
        }
        if (!Misc.isStringEmpty(customerId)) {
            String tmp = AlgorithmUtil.decryptAESToString(customerId, Constants.UUID_AES_KEY);
            customerId = tmp.substring(tmp.lastIndexOf(COLON) + 1);
        }
        return customerId;
    }

    /**
     * 获取cookie中信息，封装到map中
     * @param request
     * @return
     */
    public static Map<String,String> getCookiesInfo(HttpServletRequest request){
        Cookie[] cookieList = null;
        String customerId = null;
        Map<String,String> map = new HashMap<>();
        try {
            if (request.getCookies() != null) {
                cookieList = request.getCookies();
                for (Cookie cookie : cookieList) {
                    if (LOGIN_UUID_COOKIE_NAME.equals(cookie.getName())) {
                        customerId = cookie.getValue();
                    }else if("locale".equals(cookie.getName())){
                        map.put("locale",cookie.getValue());
                    }
                }
            }
        } catch (Exception ex) {
            logger.error(ExceptionUtils.getStackTrace(ex));
        }
        if (!Misc.isStringEmpty(customerId)) {
            String tmp = AlgorithmUtil.decryptAESToString(customerId, Constants.UUID_AES_KEY);
            customerId = tmp.substring(tmp.lastIndexOf(COLON) + 1);
        }
        map.put("userid",customerId);
        return map;
    }

    /**
     * 更改头像cookie
     *
     * @param portrait
     * @param request
     * @param response
     */

    public static void addPortrait(String portrait, HttpServletRequest request, HttpServletResponse response) {

        // 删除相关cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (LOGIN_USER_HEAD_PORTRAIT_COOKIE_NAME.equals(cookie.getName())) {
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
    }

    /**
     * 从request header中获取安全token
     * @param request
     * @return
     */
    public static String getTokenByHeader(HttpServletRequest request){
        String _token = null;
        Enumeration headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String key = String.valueOf(headerNames.nextElement());
            if("_token".equals(key)){
                _token = request.getHeader(key);
                break;
            }
        }
        return _token;
    }
}
