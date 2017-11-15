package com.blog.utils;

import com.blog.cache.RedisCache;
import com.blog.common.Constants;
import com.blog.vo.ResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidateUtil {

    private static Pattern mobilePattern = Pattern.compile("^1\\d{10}$");

    private static Pattern emailPattern = Pattern.compile("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$");

    private static Pattern userNamePattern = Pattern.compile("^([\\u4e00-\\u9fa5]*|[a-zA-Z]*|_*|[0-9])*$");// 4-20个字符，字母、数字或下划线

    private static Pattern numberPattern = Pattern.compile("^\\d+$");

    public static boolean validateMobile(String s) {
        if (Misc.isStringEmpty(s)) {
            return false;
        }

        Matcher matcher = mobilePattern.matcher(s);
        return matcher.matches();
    }

    public static boolean validateEmail(String s) {
        if (Misc.isStringEmpty(s)) {
            return false;
        }

        Matcher matcher = emailPattern.matcher(s);
        return matcher.matches();
    }

    // 验证规则 4-20个字符，支持汉字、字母、数字和_组合
    public static String validateRegUserName(String s) {
        if (Misc.isStringEmpty(s)) {
            return "请输入用户名";
        }
        if (s.length() < 4 || s.length() > 20) {
            return "用户名只能在4-20位字符之间";
        }
        Matcher matcher = numberPattern.matcher(s);
        if (matcher.matches()) {
            return "用户名不能为纯数字，请重新输入";
        }

        matcher = userNamePattern.matcher(s);
        boolean match = matcher.matches();
        if (!match) {
            return "用户名只能由汉字、字母、数字及_组成";
        }
        ResponseWrapper responseWrapper = new ResponseWrapper();
        if (responseWrapper.getStatus() == 1) {
            return "该用户名已被注册，请重新输入，如果您是该用户，请<a href=\"/login.html\" class=\"c_blue\">登录</a>";
        }
        return null;
    }

    /**
     * 安全校验 token
     * @param userid
     * @param token
     * @return
     */
    public static boolean isSafeKey(Long userid,String token){
    	if(userid==null || userid.longValue()==0)
    		return false ;
    	if(Misc.isStringEmpty(token))
    		return false ;
    	String safeKey = TripleDesUtil.encryptTripleDesToString(Constants.TOKEN_KEY+String.valueOf(userid), Constants.UUID_DES_KEY);
    	if(token.equalsIgnoreCase(safeKey)){
    		return true ;
    	}
    	return false ;
    }
    
    /**
     * 安全校验 token
     * @param userid
     * @param token
     * @return
     */
    public static boolean isSafeKey(String userid,String token){
    	if(Misc.isStringEmpty(userid))
    		return false ;
    	if(Misc.isStringEmpty(token))
    		return false ;
    	String safeKey = TripleDesUtil.encryptTripleDesToString(Constants.TOKEN_KEY+userid, Constants.UUID_DES_KEY);
    	if(token.equalsIgnoreCase(safeKey)){
    		return true ;
    	}
    	return false ;
    }

    public static void main(String[] args) {

    }
}
