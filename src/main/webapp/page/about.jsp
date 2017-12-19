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
<html>
<body>
<jsp:include page="../core/top.jsp"/>
<div class="content-body">
    <div class="container">
        <div class="row">
            <main class="col-md-12">
                <h1 class="page-title">About Me</h1>
                <article class="post">
                    <div class="entry-content clearfix">
                        <figure class="img-responsive-center">
                            <img class="img-responsive" src="${ctx}/images/me.jpg" alt="Developer Image">
                        </figure>
                        <p>Responsive web design offers us a way forward, finally allowing us to design for the ebb and flow of things. There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don’t look even slightly.</p>
                        <p>Responsive web design offers us a way forward, finally allowing us to design for the ebb and flow of things. There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don’t look even slightly.</p>
                        <div class="height-40px"></div>
                        <h2 class="title text-center">Social</h2>
                        <ul class="social">
                            <li class="facebook"><a href="#"><span class="ion-social-facebook"></span></a></li>
                            <li class="twitter"><a href="#"><span class="ion-social-twitter"></span></a></li>
                            <li class="google-plus"><a href="#"><span class="ion-social-googleplus"></span></a></li>
                            <li class="tumblr"><a href="#"><span class="ion-social-tumblr"></span></a></li>
                        </ul>
                    </div>
                </article>
            </main>
        </div>
    </div>
</div>
<jsp:include page="../core/foot.jsp"/>
<jsp:include page="../core/mobile.jsp"/>
</body>
<!-- js -->
<script src="${ctx}/js/jquery-3.2.1.js"></script>
<script src="${ctx}/js/bootstrap.min.js"></script>
<script src="${ctx}/js/pace.min.js"></script>
<script src="${ctx}/js/modernizr.custom.js"></script>
<script src="${ctx}/js/script.js"></script>
</html>