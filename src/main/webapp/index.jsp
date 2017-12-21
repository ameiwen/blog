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
                                <a href="#" title="JVM内存溢出实例-实战JVM（二）" target="_blank">
                                    <img src="http://www.bitcoin86.com/uploads/allimg/171215/1-1G215123T9408-lp.jpg" original="http://www.bitcoin86.com/uploads/allimg/171215/1-1G215123T9408-lp.jpg" style="width: 167.5px; height: 137px; display: block;" alt="正儿八经得思考有点怪">
                                </a>
                            </figure>
                        </div>
                        <div class="col-lg-9">
                            <div class="view">
                                <h4><a href="#" title="正儿八经得思考有点怪" target="_blank">JVM内存溢出实例-实战JVM（二）</a></h4>
                                <p>
                                    JVM规范规定，除了程序计数器，虚拟机其他内存区域均会发生内存溢出的可能，OutOfMemoryError（OOM）
                                    1、通过代码人为造成OOM，让大家跟了解JVM运行时各区存储的内容。2通过demo让...
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
                <div class="widget widget-tag">
                    <div class="panel-body">
                        <div id="clock"></div>
                    </div>
                </div>
                <div class="widget widget-recent-posts">
                    <h5 class="widget-title">文章推荐</h5>
                    <ul>
                        <li>
                            <a href="#">自定义通信协议之原码，反码，补码</a>
                        </li>
                        <li>
                            <a href="#">maven环境搭建</a>
                        </li>
                        <li>
                            <a href="#">JDK环境变量配置</a>
                        </li>
                        <li>
                            <a href="#">JDK环境变量配置</a>
                        </li>
                        <li>
                            <a href="#">JDK环境变量配置</a>
                        </li>
                        <li>
                            <a href="#">JDK环境变量配置</a>
                        </li>
                        <li>
                            <a href="#">JDK环境变量配置</a>
                        </li>
                        <li>
                            <a href="#">JDK环境变量配置</a>
                        </li>
                        <li>
                            <a href="#">JDK环境变量配置</a>
                        </li>
                        <li>
                            <a href="#">JDK环境变量配置</a>
                        </li>
                    </ul>
                </div>
                <div class="widget widget-archives">
                    <h5 class="widget-title">档案记录</h5>
                    <ul>
                        <li>
                            <a href="#">2017-5-8</a>
                        </li>
                        <li>
                            <a href="#">2017-5-8</a>
                        </li>
                        <li>
                            <a href="#">2017-12-17</a>
                        </li>
                    </ul>
                </div>
                <div class="widget widget-tag">
                    <h5 class="widget-title">友情链接</h5>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <span class="tag-list">
                                <a href="http://www.begincode.net" class="hot-label " target="_blank">beginCode社区</a>
                                <a href="/resource/fortype/5 "  class="hot-label " target="_blank">架构</a>
                                <a href="/resource/fortype/6 " class="hot-label " target="_blank">数据库</a>
                                <a href="/resource/fortype/7 " class="hot-label " target="_blank">框架技术</a>
                                <a href="/resource/fortype/8 " class="hot-label " target="_blank">网页模板</a>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="widget widget-contact">
                    <h5 class="widget-title">联系方式</h5>
                    <p>
                        联系我：
                        <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=310632730&site=qq&menu=yes">
                            <img border="0" src="http://wpa.qq.com/pa?p=2:310632730:41" alt="联系我" title="联系我"/>
                        </a>
                    </p>
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
<script src="${ctx}/clock/js/circle_JT_min.js" ></script>
<script>
    $.circleJt({
        domId:'clock',//必须
        radius:100,//必须
        pbColor:'#00796b',//必需
        pbWidth:10,//非必需
        value:0,//必须
        totalValue:1000,//非必需
        percentage:true,//非必需
        fontSize:30,

        clock:true,//如果clock为真的时候，上述属性除value,totalValue,percentage均全都无效
        digitalWatch:true//以电子表形式显示
    });
</script>
</html>
