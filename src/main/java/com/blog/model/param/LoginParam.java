package com.blog.model.param;

import com.blade.validator.annotation.NotEmpty;
import lombok.Data;

/**
 * 登录参数
 */
@Data
public class LoginParam {

    @NotEmpty(message = "用户名不能为空")
    private String username;
    @NotEmpty(message = "密码不能为空")
    private String password;
    private String remeberMe;

}
