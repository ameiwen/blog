<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../commons/taglibs.jsp" %>
<html>
<head>
    <title>Blog</title>
    <%@ include file="../commons/meta.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/css/pace.css">
    <link rel="stylesheet" href="${ctx}/css/custom.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/ionicons/2.0.1/css/ionicons.min.css">
</head>
<body>
<jsp:include page="../core/top.jsp"/>
<div class="content-body">
    <div class="container">
        <div class="row">
            <main class="col-md-12">
                <article class="post post-1">
                    <header class="entry-header">
                        <h1 class="entry-title">
                            <a href="single.html">Adaptive Vs. Responsive Layouts And Optimal Text Readability</a>
                        </h1>
                        <div class="entry-meta">
                            <span class="post-category"><a href="#">Web Design</a></span>

                            <span class="post-date"><a href="#"><time class="entry-date" datetime="2012-11-09T23:15:57+00:00">February 2, 2013</time></a></span>

                            <span class="post-author"><a href="#">Albert Einstein</a></span>

                            <span class="comments-link"><a href="">4 Comments</a></span>
                        </div>
                    </header>
                    <div class="entry-content clearfix">
                        <p>Responsive web design offers us a way forward, finally allowing us to design for the ebb and flow of things. There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don’t look even slightly.</p>
                        <div class="read-more cl-effect-14">
                            <a href="#" class="more-link">Continue reading <span class="meta-nav">→</span></a>
                        </div>
                    </div>
                </article>

                <article class="post post-2">
                    <header class="entry-header">
                        <h1 class="entry-title">
                            <a href="single.html">Adaptive Vs. Responsive Layouts And Optimal Text Readability</a>
                        </h1>
                        <div class="entry-meta">
                            <span class="post-category"><a href="#">Web Design</a></span>

                            <span class="post-date"><a href="#"><time class="entry-date" datetime="2012-11-09T23:15:57+00:00">February 2, 2013</time></a></span>

                            <span class="post-author"><a href="#">Albert Einstein</a></span>

                            <span class="comments-link"><a href="#">4 Comments</a></span>
                        </div>
                    </header>
                    <div class="entry-content clearfix">
                        <p>Responsive web design offers us a way forward, finally allowing us to design for the ebb and flow of things. There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don’t look even slightly.</p>
                        <div class="read-more cl-effect-14">
                            <a href="#" class="more-link">Continue reading <span class="meta-nav">→</span></a>
                        </div>
                    </div>
                </article>

                <article class="post post-3">
                    <header class="entry-header">
                        <h1 class="entry-title">
                            <a href="single.html">Adaptive Vs. Responsive Layouts And Optimal Text Readability</a>
                        </h1>
                        <div class="entry-meta">
                            <span class="post-category"><a href="#" rel="category tag">Web Design</a></span>

                            <span class="post-date"><a href="#"><time class="entry-date" datetime="2012-11-09T23:15:57+00:00">February 2, 2013</time></a></span>

                            <span class="post-author"><a href="#">Albert Einstein</a></span>

                            <span class="comments-link"><a href="#">4 Comments</a></span>
                        </div>
                    </header>
                    <div class="entry-content clearfix">
                        <p>Responsive web design offers us a way forward, finally allowing us to design for the ebb and flow of things. There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don’t look even slightly.</p>
                        <div class="read-more cl-effect-14">
                            <a href="#" class="more-link">Continue reading <span class="meta-nav">→</span></a>
                        </div>
                    </div>
                </article>

                <article class="post post-4">
                    <header class="entry-header">
                        <h1 class="entry-title">
                                <a href="single.html">Adaptive Vs. Responsive Layouts And Optimal Text Readability</a>
                        </h1>
                        <div class="entry-meta">
                            <span class="post-category"><a href="#" rel="category tag">Web Design</a></span>

                            <span class="post-date"><a href="#"><time class="entry-date" datetime="2012-11-09T23:15:57+00:00">February 2, 2013</time></a></span>

                            <span class="post-author"><a href="#">Albert Einstein</a></span>

                            <span class="comments-link"><a href="#">4 Comments</a></span>
                        </div>
                    </header>
                    <div class="entry-content clearfix">
                        <p>Responsive web design offers us a way forward, finally allowing us to design for the ebb and flow of things. There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don’t look even slightly.</p>
                        <div class="read-more cl-effect-14">
                            <a href="#" class="more-link">Continue reading <span class="meta-nav">→</span></a>
                        </div>
                    </div>
                </article>
            </main>
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
