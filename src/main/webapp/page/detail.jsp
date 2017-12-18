<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Blog</title>
    <%@ include file="../commons/meta.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/css/pace.css">
    <link rel="stylesheet" href="${ctx}/css/custom.css">
    <link rel="stylesheet" href="${ctx}/css/qu.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/ionicons/2.0.1/css/ionicons.min.css">
</head>
<body>
<jsp:include page="../core/top.jsp"/>
<div class="content-body">
    <div class="container">
        <div class="row">
            <main class="col-md-8">
                <article class="post post-1">
                    <header class="entry-header">
                        <h1 class="entry-title">Adaptive Vs. Responsive Layouts And Optimal Text Readability</h1>
                        <div class="entry-meta">
                            <span class="post-category"><a href="#">Web Design</a></span>

                            <span class="post-date"><a href="#"><time class="entry-date" datetime="2012-11-09T23:15:57+00:00">February 2, 2013</time></a></span>

                            <span class="post-author"><a href="#">Albert Einstein</a></span>

                            <span class="comments-link"><a href="#">4 Comments</a></span>
                        </div>
                    </header>
                    <div class="entry-content clearfix">
                        <p>Responsive web design offers us a way forward, finally allowing us to design for the ebb and flow of things. There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don’t look even slightly.</p>
                        <p>Responsive web design offers us a way forward, finally allowing us to design for the ebb and flow of things. There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don’t look even slightly.</p>
                        <p>Responsive web design offers us a way forward, finally allowing us to design for the ebb and flow of things. There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don’t look even slightly.</p>
                        <p>Responsive web design offers us a way forward, finally allowing us to design for the ebb and flow of things. There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don’t look even slightly.</p>
                    </div>
                </article>
            </main>
            <aside class="col-md-4">
                <div class="widget widget-recent-posts">
                    <h3 class="widget-title">Recent Posts</h3>
                    <ul>
                        <li>
                            <a href="#">Adaptive Vs. Responsive Layouts And Optimal Text Readability</a>
                        </li>
                        <li>
                            <a href="#">Web Design is 95% Typography</a>
                        </li>
                        <li>
                            <a href="#">Paper by FiftyThree</a>
                        </li>
                    </ul>
                </div>
                <div class="widget widget-archives">
                    <h3 class="widget-title">Archives</h3>
                    <ul>
                        <li>
                            <a href="#">November 2014</a>
                        </li>
                        <li>
                            <a href="#">September 2014</a>
                        </li>
                        <li>
                            <a href="#">January 2013</a>
                        </li>
                    </ul>
                </div>

                <div class="widget widget-category">
                    <h3 class="widget-title">Category</h3>
                    <ul>
                        <li>
                            <a href="#">Web Design</a>
                        </li>
                        <li>
                            <a href="#">Web Development</a>
                        </li>
                        <li>
                            <a href="#">SEO</a>
                        </li>
                    </ul>
                </div>

                <div class="widget widget-category">
                    <h3 class="widget-title">Tag Cloud</h3>
                    <div class="panel panel-default">
                        <div class="panel-body">
				        <span class="tag-list">
                            <a href="/resource/fortype/1 " class="hot-label " target="_self">JAVA</a>
                            <a href="/resource/fortype/2 " class="hot-label " target="_self">开发工具</a>
                            <a href="/resource/fortype/3 " class="hot-label " target="_self">使用手册</a>
                            <a href="/resource/fortype/4 " class="hot-label " target="_self">算法</a>
                            <a href="/resource/fortype/5 " class="hot-label " target="_self">架构</a>
                            <a href="/resource/fortype/6 " class="hot-label " target="_self">数据库</a>
                            <a href="/resource/fortype/7 " class="hot-label " target="_self">框架技术</a>
                            <a href="/resource/fortype/8 " class="hot-label " target="_self">网页模板</a>
					    </span>
                        </div>
                    </div>
                </div>
            </aside>
        </div>
    </div>
</div>
<jsp:include page="../core/foot.jsp"/>
<!-- Mobile Menu -->
<jsp:include page="../core/mobile.jsp"/>
</body>
<!-- js -->
<script src="${ctx}/js/jquery-3.2.1.js"></script>
<script src="${ctx}/js/bootstrap.min.js"></script>
<script src="${ctx}/js/pace.min.js"></script>
<script src="${ctx}/js/modernizr.custom.js"></script>
<script src="${ctx}/js/script.js"></script>
</html>