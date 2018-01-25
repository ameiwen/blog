package com.blog.controller.admin;

import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.page.Page;
import com.blade.kit.DateKit;
import com.blade.mvc.annotation.JSON;
import com.blade.mvc.annotation.Param;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.Route;
import com.blade.mvc.http.HttpMethod;
import com.blade.mvc.http.Request;
import com.blade.mvc.multipart.FileItem;
import com.blade.mvc.ui.RestResponse;
import com.blog.comment.Constants;
import com.blog.controller.BaseController;
import com.blog.exception.TipException;
import com.blog.extension.Commons;
import com.blog.init.TaleConst;
import com.blog.model.dto.LogActions;
import com.blog.model.dto.Types;
import com.blog.model.entity.Attach;
import com.blog.model.entity.Logs;
import com.blog.model.entity.Users;
import com.blog.service.SiteService;
import com.blog.utils.BlogUtils;
import com.blog.utils.UploadUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 附件管理
 * <p>
 */
@Slf4j
@Path("admin/attach")
public class AttachController extends BaseController {

    public static final String CLASSPATH = new File(AttachController.class.getResource("/").getPath()).getPath() + File.separatorChar;

    @Inject
    private SiteService siteService;

    /**
     * 附件页面
     *
     * @param request
     * @param page
     * @param limit
     * @return
     */
    @Route(value = "", method = HttpMethod.GET)
    public String index(Request request, @Param(defaultValue = "1") int page,
                        @Param(defaultValue = "12") int limit) {

        Attach attach  = new Attach();
        Page<Attach> attachPage = attach.page(page, limit);
        request.attribute("attachs", attachPage);
        request.attribute(Types.ATTACH_URL, Constants.URL);
        request.attribute("max_file_size", TaleConst.MAX_FILE_SIZE / 1024);
        return "admin/attach";
    }

    /**
     * 上传文件接口
     * <p>
     * 返回格式
     *
     * @param request
     * @return
     */
    @Route(value = "upload", method = HttpMethod.POST)
    @JSON
    public RestResponse upload(Request request) {

        log.info("UPLOAD DIR = {}", BlogUtils.UP_DIR);

        Users users = this.user();
        Integer uid = users.getUid();
        Map<String, FileItem> fileItemMap = request.fileItems();
        Collection<FileItem> fileItems = fileItemMap.values();
        List<Attach> errorFiles = new ArrayList<>();
        List<Attach> urls = new ArrayList<>();
        try {
            fileItems.forEach((FileItem f) -> {
                String fname = f.getFileName();

                if ((f.getLength() / 1024) <= TaleConst.MAX_FILE_SIZE) {
                    String fkey = BlogUtils.getFileKey(fname);

                    String ftype = f.getContentType().contains("image") ? Types.IMAGE : Types.FILE;

                    try {
                        UploadUtils.uploadAliyun(fkey,f.getData());
                    } catch (Exception e) {
                        log.error("", e);
                    }

                    Attach attach = new Attach();
                    attach.setFname(fname);
                    attach.setAuthor_id(uid);
                    attach.setFkey(fkey);
                    attach.setFtype(ftype);
                    attach.setCreated(DateKit.nowUnix());
                    attach.save();

                    urls.add(attach);
                    siteService.cleanCache(Types.C_STATISTICS);
                } else {
                    Attach attach = new Attach();
                    attach.setFname(fname);
                    errorFiles.add(attach);
                }
            });
            if (errorFiles.size() > 0) {
                return RestResponse.builder().success(false).payload(errorFiles).build();
            }
            return RestResponse.ok(urls);
        } catch (Exception e) {
            String msg = "文件上传失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                log.error(msg, e);
            }
            return RestResponse.fail(msg);
        }
    }

    @Route(value = "delete")
    @JSON
    public RestResponse delete(@Param Integer id, Request request) {
        try {
            Attach attach = new Attach().find(id);
            if (null == attach) {
                return RestResponse.fail("不存在该附件");
            }
            String fkey = attach.getFkey();
            siteService.cleanCache(Types.C_STATISTICS);
            UploadUtils.delete(fkey);
            log.info("Delete attach: [{}]", fkey);
            attach.delete(id);
            new Logs(LogActions.DEL_ATTACH, fkey, request.address(), this.getUid()).save();
        } catch (Exception e) {
            String msg = "附件删除失败";
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
