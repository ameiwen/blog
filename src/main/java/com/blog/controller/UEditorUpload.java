package com.blog.controller;

import com.alibaba.fastjson.JSON;
import com.blog.controller.base.BaseController;
import com.blog.service.FileUploadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping(value = "/ueditor")
public class UEditorUpload extends BaseController {

    @Resource
    private FileUploadService fileUploadService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST,value = "/uploadImg")
    public String headUpload(MultipartFile file,HttpServletRequest request){
        Map<String, Object> param = convertParamToMap(request);
        Map<String,Object> map= fileUploadService.uploadFile(file,param);
        String result = JSON.toJSONString(map);
        return result;
    }
}
