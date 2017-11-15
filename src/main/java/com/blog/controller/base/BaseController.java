package com.blog.controller.base;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


@Controller
public class BaseController {

    protected Map<String, Object> convertParamToMap(HttpServletRequest request) {
        Enumeration<String> enumeration = request.getParameterNames();
        HashMap map = new HashMap();

        while(enumeration.hasMoreElements()) {
            String paramName = (String)enumeration.nextElement();
            map.put(paramName, request.getParameter(paramName));
        }

        return map;
    }
}
