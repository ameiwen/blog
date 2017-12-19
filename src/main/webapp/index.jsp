<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="commons/taglibs.jsp" %>
<html>
<head>
    <title>Blog</title>
    <%@ include file="commons/meta.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/css/pace.css">
    <link rel="stylesheet" href="${ctx}/css/custom.css">
    <link rel="stylesheet" href="${ctx}/css/qu.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/ionicons/2.0.1/css/ionicons.min.css">
</head>
<body>
<jsp:include page="core/top.jsp"/>
<div class="content-body">
    <div class="container">
        <div class="row">
            <main class="col-md-9">
                <article class="post post-1">
                    <div id="myCarousel" class="carousel slide">
                        <!-- 轮播（Carousel）指标 -->
                        <ol class="carousel-indicators">
                            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                            <li data-target="#myCarousel" data-slide-to="1"></li>
                            <li data-target="#myCarousel" data-slide-to="2"></li>
                        </ol>
                        <!-- 轮播（Carousel）项目 -->
                        <div class="carousel-inner">
                            <div class="item active">
                                <img src="${ctx}/images/banner.jpg" alt="First slide">
                                <div class="carousel-caption">标题 1</div>
                            </div>
                            <div class="item">
                                <img src="${ctx}/images/banner.jpg" alt="Second slide">
                                <div class="carousel-caption">标题 2</div>
                            </div>
                            <div class="item">
                                <img src="${ctx}/images/banner.jpg" alt="Third slide">
                                <div class="carousel-caption">标题 3</div>
                            </div>
                        </div>
                        <!-- 轮播（Carousel）导航 -->
                        <a class="carousel-control left" href="#myCarousel"
                           data-slide="prev">&lsaquo;</a>
                        <a class="carousel-control right" href="#myCarousel"
                           data-slide="next">&rsaquo;</a>
                    </div>
                </article>
                <article class="post post-2">
                    <div class="view">
                        <div class="page-header">
                            <h3 contenteditable="false">
                                最新文章
                                <small>最近发布</small>
                            </h3>
                        </div>
                    </div>
                    <div class="row exchange" style="padding: 20px 0;">
                        <div class="col-lg-3">
                            <figure>
                                <a href="#" title="正儿八经得思考有点怪" target="_blank">
                                    <img src="http://www.bitcoin86.com/uploads/allimg/171215/1-1G215123T9408-lp.jpg" original="http://www.bitcoin86.com/uploads/allimg/171215/1-1G215123T9408-lp.jpg" style="width: 167.5px; height: 137px; display: block;" alt="正儿八经得思考有点怪">
                                </a>
                            </figure>
                        </div>
                        <div class="col-lg-9">
                            <div class="view">
                                <h4><a href="#" title="正儿八经得思考有点怪" target="_blank">正儿八经得思考有点怪</a></h4>
                                <p>
                                    博客更新的断断续续，126邮箱也收到很多网友的来信，很感谢大家对叶子博客的关注。其实，入行五年了，我觉得自己进步不是那么大。偶尔看看书，也是静不下心来。有的人说了，或许年龄越大，功利性太强了，走着走着似乎忘记了自己的...
                                    <a href="${ctx}/page/detail.jsp" target="_blank" style="color: #759b08;padding-left:5px;">[详情]</a>
                                </p>
                            </div>
                            <ul style="margin-left: -39px;">
                                <p>
                                    <span>
                                        <i class="icon ion-pricetag"></i>
                                        <a href="/memory/index?tag=3" style="color: #759b08">韶华追忆</a>
                                    </span>
                                    <span>&nbsp;&nbsp;<i class="icon ion-ios-clock-outline"></i>2017-11-14  21:40:41</span>
                                    <span>&nbsp;&nbsp;<i class="icon ion-ios-eye"></i>浏览（1153）</span>
                                    <span>
                                        &nbsp;&nbsp;
                                        <a href="https://www.yezismile.com/detail/459.html#SOHUCS">
                                            <i class="icon ion-ios-chatboxes-outline"></i>
                                           <span id="sourceId::459" class="cy_cmt_count">评论（1 )</span>
                                        </a>
                                    </span>
                                    <span style="padding-left: 0;">
                                        &nbsp;&nbsp;
                                        <i class="icon ion-thumbsup"></i>
                                        <a href="javascript:void(0)" onclick="return false;" style="">39</a>
                                    </span>
                                </p>
                            </ul>
                        </div>
                    </div>
                    <hr>
                </article>
            </main>
            <aside class="col-md-3">
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
            </aside>
        </div>
    </div>
</div>
<jsp:include page="core/foot.jsp"/>
<!-- Mobile Menu -->
<jsp:include page="/core/mobile.jsp"/>
</body>
<!-- js -->
<script src="${ctx}/js/jquery-3.2.1.js"></script>
<script src="${ctx}/js/bootstrap.min.js"></script>
<script src="${ctx}/js/pace.min.js"></script>
<script src="${ctx}/js/modernizr.custom.js"></script>
<script src="${ctx}/js/script.js"></script>
</html>
