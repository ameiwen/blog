package com.blog.controller;

import com.blog.controller.base.BaseController;
import com.blog.service.IndexService;
import com.blog.vo.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController extends BaseController {

    @Autowired
    private IndexService indexService;

    @ResponseBody
    @RequestMapping(value = "/indexInit")
    public ResponseWrapper indexInit(HttpServletRequest request){
        return indexService.IndexInit();
    }
}
