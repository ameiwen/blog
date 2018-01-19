package com.blog.controller.admin;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.*;
import com.blade.mvc.ui.RestResponse;
import com.blog.controller.BaseController;
import com.blog.exception.TipException;
import com.blog.service.VisitedService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("admin/visited")
public class VisitedController extends BaseController{

    @Inject
    private VisitedService visitedService;

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
            visitedService.removeVisited(id);
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
