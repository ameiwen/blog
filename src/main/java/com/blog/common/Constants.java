package com.blog.common;


import com.blog.utils.PropertyReader;

public class Constants {

    public static final String TOKEN_KEY = "51bi~!@#cn";

    public  static final String USERID="userid";
    public static final String SESSION_UPWD = "BitUserpwd";
    public static final String SESSION_USER = "BitUserSession";

    public static final String INVITER_URL = "https://www.bitpoolex.com/customer/invite?source=inviteAward&ref=";//邀请URL
    public static final String HEADIMG = "https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=1402472998,1033961727&fm=58";//用户默认头像
    public static final String IMGPATH = "http://ui.51bi.com/";//图片路径

    public static final String SMSUSERNAME = "sunway27";//发送短信账号
    public static final String SMSPWD = "86ico@123";//发送短信密码

    public static final String BTC = "BTC ";//发送短信密码
    public static final String ETH = "ETH ";//发送短信密码

    public static final String BIT_REG_MOBILE_KEY="bit_eregistersmscode";//手机注册key
    public static final String BIT_LOGIN_MOBILE_KEY="bit_loginsmscode";//手机注册key
    public static final String BIT_UPDATE_MOBILE_KEY="bit_updatemobilesmscode";//修改绑定手机key\(原手机)
    public static final String BIT_APPLY_MOBILE_KEY="bit_applymobile_smscode";//修改绑定手机key(新手机)
    public static final String BIT_SET_GOOLE_KEY="bit_setgoogle_smscode";//设置谷歌验证器
    public static final String BIT_FINDPWD_MOBILE_KEY="bit_findpwd_smscode";//找回密码

    public static final String BIT_SETMONEY_MOBILE_KEY="bit_setmoneysmscode";//设置资金密码key\
    public static final String BIT_UPDATEMONEY_MOBILE_KEY="bit_updatemoneysmscode";//修改资金密码key\
    public static final String BIT_UPDATEPWD_MOBILE_KEY="bit_updatepwdsafesmscode";//修改密码key\

    public static final String UUID_AES_KEY = "1d2h3e4o5t6y7u8i"; // session UUID enAes key

    public static final String UUID_DES_KEY = "1cf4a27e2b56a63d0h6y7u8i";//userid 3desc key
    public static final String COOKIE_DOMAIN = PropertyReader.get("front.cookie.domain", "front.properties");

    public static final String WEBSOCKET_ALLOW_ORIGINS = PropertyReader.get("front.webSocket.allowOrigins","front.properties");
}
