<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../commons/taglibs.jsp" %>
<div class="container">
    <header id="site-header">
        <div class="row">
            <div class="col-md-4 col-sm-5 col-xs-8">
                <div class="logo">
                    <h1><a href="${ctx}/index.jsp"><b>Yang</b>xs</a></h1>
                </div>
            </div><!-- col-md-4 -->
            <div class="col-md-8 col-sm-7 col-xs-4">
                <nav class="main-nav" role="navigation">
                    <div class="navbar-header">
                        <button type="button" id="trigger-overlay" class="navbar-toggle">
                            <span class="ion-navicon"></span>
                        </button>
                    </div>
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav navbar-right">
                            <li class="cl-effect-11"><a href="${ctx}/index.jsp" data-hover="______">首页</a></li>
                            <li class="cl-effect-11"><a href="${ctx}/page/article.jsp" data-hover="______">文章</a></li>
                            <li class="cl-effect-11"><a href="${ctx}/page/about.jsp" data-hover="______">关于</a></li>
                            <li class="cl-effect-11"><a href="${ctx}/page/contact.jsp" data-hover="______">留言</a></li>
                        </ul>
                    </div>
                </nav>
            </div><!-- col-md-8 -->
        </div>
    </header>
</div>