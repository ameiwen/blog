package com.blog.hooks;

import com.blade.ioc.annotation.Bean;
import com.blade.ioc.annotation.Inject;
import com.blade.mvc.hook.Signature;
import com.blade.mvc.hook.WebHook;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;
import com.blog.init.TaleConst;
import com.blog.model.entity.Users;
import com.blog.service.VisitedService;
import com.blog.utils.BlogUtils;
import lombok.extern.slf4j.Slf4j;


@Bean
@Slf4j
public class BaseWebHook implements WebHook {

    @Inject
    private VisitedService visitedService;

    @Override
    public boolean before(Signature signature) {
        Request  request  = signature.request();
        Response response = signature.response();

        String uri = request.uri();
        String ip  = request.host();
        visitedService.saveVisited(BlogUtils.getUserIp(request));
        log.info("来路IP:{}", BlogUtils.getUserIp(request));

        if (uri.startsWith(TaleConst.STATIC_URI)) {
            return true;
        }

        if (!TaleConst.INSTALLED && !uri.startsWith(TaleConst.INSTALL_URI)) {
            response.redirect(TaleConst.INSTALL_URI);
            return false;
        }

        if (TaleConst.INSTALLED) {
            return isRedirect(request, response);
        }
        return true;
    }

    private boolean isRedirect(Request request, Response response) {
        Users user = BlogUtils.getLoginUser();
        String uri  = request.uri();
        if (null == user) {
            Integer uid = BlogUtils.getCookieUid(request);
            if (null != uid) {
                user = new Users().find(uid);
                request.session().attribute(TaleConst.LOGIN_SESSION_KEY, user);
            }
        }
        if (uri.startsWith(TaleConst.ADMIN_URI) && !uri.startsWith(TaleConst.LOGIN_URI)) {
            if (null == user) {
                response.redirect(TaleConst.LOGIN_URI);
                return false;
            }
            request.attribute(TaleConst.PLUGINS_MENU_NAME, TaleConst.PLUGIN_MENUS);
        }
        return true;
    }

}
