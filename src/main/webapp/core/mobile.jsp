<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../commons/taglibs.jsp" %>
<!-- Mobile Menu -->
<div class="overlay overlay-hugeinc">
    <button type="button" class="overlay-close">
        <span class="ion-ios-close-empty"></span>
    </button>
    <nav>
        <ul>
            <li><a href="${ctx}/index.jsp">首页</a></li>
            <li><a href="${ctx}/page/article.jsp">文章</a></li>
            <li><a href="${ctx}/page/contact.jsp">说说</a></li>
            <li><a href="${ctx}/page/about.jsp">关于</a></li>
            <li><a href="${ctx}/page/contact.jsp">留言</a></li>
        </ul>
    </nav>
</div>