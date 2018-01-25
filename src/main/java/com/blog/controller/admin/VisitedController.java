package com.blog.controller.admin;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.*;
import com.blade.mvc.ui.RestResponse;
import com.blog.controller.BaseController;
import com.blog.exception.TipException;
import com.blog.model.dto.Types;
import com.blog.model.entity.Visited;
import com.blog.service.SiteService;
import com.blog.service.VisitedService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("admin/visited")
public class VisitedController extends BaseController{

    @Inject
    private VisitedService visitedService;

    @Inject
    private SiteService siteService;

    /**
     * 删除一条访问记录
     *
     * @param id
     * @return
     */
    @PostRoute(value = "delete")
    @JSON
    public RestResponse delete(@Param Integer id) {
        try {
            Visited visited = new Visited().find(id);
            if(null == visited){
                return RestResponse.fail("不存在该记录");
            }
            visitedService.removeVisited(id);
            siteService.cleanCache(Types.C_STATISTICS);
        } catch (Exception e) {
            String msg = "访问记录删除失败";
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
