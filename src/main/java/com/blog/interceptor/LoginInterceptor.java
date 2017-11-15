package com.blog.interceptor;

import com.blog.cache.RedisCache;
import com.blog.common.Constants;
import com.blog.utils.AlgorithmUtil;
import com.blog.utils.Misc;
import com.blog.utils.SecurityUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class LoginInterceptor implements HandlerInterceptor {
    private static int STATUS_NOT_LOGIN = 401;
    private static Logger logger = LogManager.getLogger(LoginInterceptor.class);

    /**
     * 请求controller 之前 验证用户是否登陆
     * @param request
     * @param response
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        boolean isLogin = false;
        Cookie[] cookies = request.getCookies();
        String cookieValue = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (CookieInfo.LOGIN_UUID_COOKIE_NAME.equals(cookie.getName())) {
                    cookieValue = cookie.getValue();

                    cookieValue = AlgorithmUtil.decryptAESToString(cookieValue, Constants.UUID_AES_KEY);

                    if (!Misc.isStringEmpty(cookieValue)) {
                        isLogin = true;
                    }
                }
            }
        }else{
            if(checkLoginFromHeader(request)){
                isLogin = true;
            }
        }
        if (!isLogin) {
            try {
                response.setContentType("application/json;charset=utf-8");
                response.setStatus(STATUS_NOT_LOGIN);
            } catch (Exception e) {
                logger.error(ExceptionUtils.getStackTrace(e));
            }

            if (SecurityUtil.isAjaxRequest(request)) {
                try {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(STATUS_NOT_LOGIN);
                } catch (Exception e) {
                    logger.error(ExceptionUtils.getStackTrace(e));
                }
            } else {
                String path = request.getContextPath();
                String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
//                String targetUrl = basePath + request.getRequestURI();
                response.sendRedirect(basePath);
            }

            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    /**
     *  如果从cookie中获取不到_uuid,则从head中获取，如果有则表明已登录
     */
    private boolean checkLoginFromHeader(HttpServletRequest request){
        String userUuid = null;
        Enumeration headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String key = String.valueOf(headerNames.nextElement());
            if("_uuid".equals(key)){
                userUuid = request.getHeader(key);
                break;
            }
        }
        System.out.println("cookie is null .... start get useruuid from header..." + request.getRequestURI() + userUuid);
        if(!Misc.isStringEmpty(userUuid)){
            String cacheKey = CookieInfo.REDIS_COOKIE_CUSTOMER_PREFIX + userUuid;
            RedisCache redisCache = RedisCache.getInstance();
            if (redisCache.existsCache(cacheKey)) {
                // 重新延长两个小时
                redisCache.expireKey(cacheKey, CookieInfo.LOGIN_EXPIRE_TIME);
                return true;
            }
        }
        return false;
    }
}
