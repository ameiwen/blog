package com.blog.controller;

import com.blog.controller.base.BaseController;
import com.blog.model.BitUser;
import com.blog.service.BitUserService;
import com.blog.utils.ResponseUtils;
import com.blog.vo.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController{

    @Autowired
    private BitUserService userService;

    @ResponseBody
    @RequestMapping(value = "/getUser")
    public ResponseWrapper getUser(){
        ResponseWrapper responseWrapper = ResponseUtils.successResponse("");
        BitUser user = userService.getUser(341L);
        responseWrapper.addAttribute("user",user);
        return responseWrapper;
    }

}
