package com.blog.controller;

import com.blog.controller.base.BaseController;
import com.blog.service.RightService;
import com.blog.vo.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RightController extends BaseController {

    @Autowired
    private RightService rightService;

    @ResponseBody
    @RequestMapping(value = "/mainRight")
    public ResponseWrapper mainRight(){
        return rightService.mainRightInit();
    }

}
