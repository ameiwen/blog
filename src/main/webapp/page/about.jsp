<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../commons/taglibs.jsp" %>
<html>
<head>
    <title>Blog</title>
    <%@ include file="../commons/meta.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/css/pace.css">
    <link rel="stylesheet" href="${ctx}/css/custom.css">
    <link rel="stylesheet" href="${ctx}/axis/css/index.css" />
    <link rel="stylesheet" href="https://cdn.bootcss.com/ionicons/2.0.1/css/ionicons.min.css">
</head>
<html>
<body>
<jsp:include page="../core/top.jsp"/>
<div class="content-body">
    <div class="container">
        <div class="row">
            <main class="col-md-12">
                <article class="post">
                    <div class="about_content history">
                        <h3 class="page-title">历程</h3>
                    </div>
                    <div class="event_box">
                        <div class="space_div">
                            <p class="left_space"></p>
                            <p class="right_space"></p>
                        </div>
                        <div class="parHd clearfix">
                            <ul>
                                <li>2016.03.01</li>
                                <li>2016.05.02</li>
                                <li>2016.06.03</li>
                                <li>2016.09.04</li>
                                <li>2016.11.05</li>
                                <li>2016.12.06</li>
                                <li>2017.01.07</li>
                                <li>2017.02.08</li>
                            </ul>
                            <a class="sPrev"><img src="${ctx}/axis/images/left_ico.png" alt="" title=""></a>
                            <a class="sNext"><img src="${ctx}/axis/images/right_ico.png" alt="" title=""></a>
                        </div>
                        <div class="parBd clearfix">
                            <div class="slideBox">
                                <h4>【1】所有装饰工程10年维保</h4>
                            </div>
                            <div class="slideBox" style="display: none;">
                                <h4>【2】启动星店计划第一季</h4>
                            </div>
                            <div class="slideBox" style="display: none;">
                                <h4>【3】星店2.0产品发布会</h4>
                            </div>
                            <div class="slideBox" style="display: none;">
                                <h4>【4】创“星”体验2016星店星评启动仪式</h4>
                            </div>
                            <div class="slideBox" style="display: none;">
                                <h4>【5】跨阅 解读从单店到连锁的商业趋势</h4>
                            </div>
                            <div class="slideBox" style="display: none;">
                                <h4>【6】咖啡街区发布会，助力打造精品咖啡馆</h4>
                            </div>
                            <div class="slideBox" style="display: none;">
                                <h4>【7】美团点评好店长生而不凡</h4>
                            </div>
                            <div class="slideBox" style="display: none;">
                                <h4>【8】第一季星店计划授星仪式</h4>
                            </div>
                        </div>
                    </div>
                </article>
            </main>
            <div class="about_content history">
                <h3 class="page-title">关于我</h3>
            </div>
            <article class="col-md-12">
                <div class="entry-content clearfix">
                    <%--<figure class="img-responsive-center">--%>
                    <%--<img class="img-responsive" src="${ctx}/images/me.jpg" alt="Developer Image">--%>
                    <%--</figure>--%>
                    <p>响应式网页设计为我们提供了一条前进的道路，最终使我们能够设计出事物的起伏。Lorem Ipsum有许多不同的变体，但是大多数变体都是以某种形式变化的，或者是注入了幽默，或者是随机的，看起来甚至不是轻微的变化。</p>
                    <p>响应式网页设计为我们提供了一条前进的道路，最终使我们能够设计出事物的起伏。Lorem Ipsum有许多不同的变体，但是大多数变体都是以某种形式变化的，或者是注入了幽默，或者是随机的，看起来甚至不是轻微的变化。</p>
                    <div class="height-40px"></div>
                    <%--<h2 class="title text-center">Social</h2>--%>
                    <%--<ul class="social">--%>
                    <%--<li class="facebook"><a href="#"><span class="ion-social-facebook"></span></a></li>--%>
                    <%--<li class="twitter"><a href="#"><span class="ion-social-twitter"></span></a></li>--%>
                    <%--<li class="google-plus"><a href="#"><span class="ion-social-googleplus"></span></a></li>--%>
                    <%--<li class="tumblr"><a href="#"><span class="ion-social-tumblr"></span></a></li>--%>
                    <%--</ul>--%>
                </div>
            </article>
        </div>
    </div>
</div>
<jsp:include page="../core/foot.jsp"/>
<jsp:include page="../core/mobile.jsp"/>
</body>
<!-- js -->
<script src="${ctx}/js/jquery-1.10.2.min.js"></script>
<script src="${ctx}/js/bootstrap.min.js"></script>
<script src="${ctx}/js/pace.min.js"></script>
<script src="${ctx}/js/modernizr.custom.js"></script>
<script src="${ctx}/js/script.js"></script>
<script type="text/javascript" src="${ctx}/axis/js/jquery.SuperSlide2.1.2.js"></script>
<script>
    $(function() {
        jQuery(".event_box").slide({ titCell: ".parHd li", mainCell: ".parBd", defaultPlay: false, titOnClassName: "act", prevCell: ".sPrev", nextCell: ".sNext",pnLoop:false });
        jQuery(".parHd").slide({ mainCell: " ul", vis: 6, effect: "left", defaultPlay: false, prevCell: ".sPrev", nextCell: ".sNext" ,pnLoop:false,autoPage:true})
    });
</script>
</html>