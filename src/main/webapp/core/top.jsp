<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../commons/taglibs.jsp" %>
<header>
    <div id="logo">
        <a href="/"></a>
    </div>
    <nav class="topnav" id="topnav">
        <a href="${ctx}/index.jsp"><span>首页</span><span class="en">Protal</span></a>
        <a href="${ctx}/page/life.jsp"><span>慢生活</span><span class="en">Life</span></a>
        <a href="${ctx}/page/moonlist.jsp"><span>碎言碎语</span><span class="en">Doing</span></a>
        <a href="${ctx}/page/share.jsp"><span>模板分享</span><span class="en">Share</span></a>
        <a href="${ctx}/page/knowledge.jsp"><span>学无止境</span><span class="en">Learn</span></a>
        <a href="${ctx}/page/book.jsp"><span>留言版</span><span class="en">Gustbook</span></a>
        <a href="${ctx}/page/about.jsp"><span>关于我</span><span class="en">About</span></a>
    </nav>
</header>