package com.blog.controller;

import com.blade.mvc.http.Request;
import com.blog.model.entity.Users;
import com.blog.utils.MapCache;
import com.blog.utils.BlogUtils;

/**
 * Created by biezhi on 2017/2/21.
 */
public abstract class BaseController {

    public static String THEME = "themes/default";

    protected MapCache cache = MapCache.single();

    public String render(String viewName) {
        return THEME + "/" + viewName;
    }

    public BaseController title(Request request, String title) {
        request.attribute("title", title);
        return this;
    }

    public BaseController keywords(Request request, String keywords) {
        request.attribute("keywords", keywords);
        return this;
    }

    public Users user() {
        return BlogUtils.getLoginUser();
    }

    public Integer getUid(){
        return this.user().getUid();
    }

    public String render_404() {
        return "/comm/error_404";
    }

}
