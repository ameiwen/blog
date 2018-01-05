package com.blog.controller.admin;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.JSON;
import com.blade.mvc.annotation.Param;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.Route;
import com.blade.mvc.http.HttpMethod;
import com.blade.mvc.http.Request;
import com.blade.mvc.ui.RestResponse;
import com.blog.controller.BaseController;
import com.blog.exception.TipException;
import com.blog.init.TaleConst;
import com.blog.model.dto.Types;
import com.blog.model.entity.Metas;
import com.blog.service.MetasService;
import com.blog.service.SiteService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 分类管理
 *
 * Created by biezhi on 2017/2/21.
 */
@Slf4j
@Path("admin/category")
public class CategoryController extends BaseController {

    @Inject
    private MetasService metasService;

    @Inject
    private SiteService siteService;

    @Route(value = "", method = HttpMethod.GET)
    public String index(Request request) {
        List<Metas> categories = siteService.getMetas(Types.RECENT_META, Types.CATEGORY, TaleConst.MAX_POSTS);
        List<Metas> tags  = siteService.getMetas(Types.RECENT_META, Types.TAG, TaleConst.MAX_POSTS);
        request.attribute("categories", categories);
        request.attribute("tags", tags);
        return "admin/category";
    }

    @Route(value = "save", method = HttpMethod.POST)
    @JSON
    public RestResponse saveCategory(@Param String cname, @Param Integer mid) {
        try {
            metasService.saveMeta(Types.CATEGORY, cname, mid);
            siteService.cleanCache(Types.C_STATISTICS);
        } catch (Exception e) {
            String msg = "分类保存失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                log.error(msg, e);
            }
            return RestResponse.fail(msg);
        }
        return RestResponse.ok();
    }

    @Route(value = "delete")
    @JSON
    public RestResponse delete(@Param int mid) {
        try {
            metasService.delete(mid);
            siteService.cleanCache(Types.C_STATISTICS);
        } catch (Exception e) {
            String msg = "删除失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                log.error(msg, e);
            }
            return RestResponse.fail(msg);
        }
        return RestResponse.ok();
    }

}
