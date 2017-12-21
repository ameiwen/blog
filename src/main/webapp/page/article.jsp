<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../commons/taglibs.jsp" %>
<html>
<head>
    <title>Blog</title>
    <%@ include file="../commons/meta.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/css/pace.css">
    <link rel="stylesheet" href="${ctx}/css/custom.css">
    <link rel="stylesheet" href="${ctx}/css/qu.css">
    <link rel="stylesheet" href="${ctx}/css/kkpager_orange.css" />
    <link rel="stylesheet" href="https://cdn.bootcss.com/ionicons/2.0.1/css/ionicons.min.css">
</head>
<body>
<jsp:include page="../core/top.jsp"/>
<div class="content-body">
    <div class="container">
        <div class="row">
            <main class="col-md-9">
                <article class="post post-1">
                    <div class="view">
                        <div class="page-header">
                            <h3 contenteditable="false">
                                文章列表
                                <small>全部的文章哟</small>
                            </h3>
                        </div>
                    </div>
                    <div id="header-search-box">
                        <a id="search-menu" href="#">
                            <span id="search-icon" class="ion-ios-search-strong"></span>
                        </a>
                        <div id="search-form" class="search-form">
                            <form role="search" method="get" id="searchform" action="#">
                                <input type="search" placeholder="Search" required>
                                <button type="submit">
                                    <span class="ion-ios-search-strong"></span>.</button>
                            </form>
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
                                           <span class="cy_cmt_count">评论（1 )</span>
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
                                           <span class="cy_cmt_count">评论（1 )</span>
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
                                           <span class="cy_cmt_count">评论（1 )</span>
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
                                           <span class="cy_cmt_count">评论（1 )</span>
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
                                           <span class="cy_cmt_count">评论（1 )</span>
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
                                           <span class="cy_cmt_count">评论（1 )</span>
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
                    <div style="width:800px;margin:0 auto;">
                        <div id="kkpager"></div>
                    </div>
                </article>
            </main>
            <aside class="col-md-3">
                <div class="widget widget-recent-posts">
                    <h5 class="widget-title">点击排行</h5>
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
                <div class="widget widget-recent-posts">
                    <h5 class="widget-title">最新文章</h5>
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
                <div class="widget widget-tag">
                    <h3 class="widget-title">标签云</h3>
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
            </aside>
        </div>
    </div>
</div>
<jsp:include page="../core/foot.jsp"/>
<!-- Mobile Menu -->
<jsp:include page="../core/mobile.jsp"/>
</body>
<!-- js -->
<script src="${ctx}/js/jquery-1.10.2.min.js"></script>
<script src="${ctx}/js/bootstrap.min.js"></script>
<script src="${ctx}/js/pace.min.js"></script>
<script src="${ctx}/js/modernizr.custom.js"></script>
<script src="${ctx}/js/kkpager.min.js"></script>
<script src="${ctx}/js/script.js"></script>
<script>
    $(function(){
        //生成分页
        //有些参数是可选的，比如lang，若不传有默认值
        kkpager.generPageHtml({
            pno : 1,
            //总页码
            total : 20,
            //总数据条数
            totalRecords : 20,
            //链接前部
            hrefFormer : 'dsads',
            //链接尾部
            hrefLatter : '.html',
            getLink : function(n){
                return this.hrefFormer + this.hrefLatter + "?pno="+n;
            }
            /*
            ,lang				: {
                firstPageText			: '首页',
                firstPageTipText		: '首页',
                lastPageText			: '尾页',
                lastPageTipText			: '尾页',
                prePageText				: '上一页',
                prePageTipText			: '上一页',
                nextPageText			: '下一页',
                nextPageTipText			: '下一页',
                totalPageBeforeText		: '共',
                totalPageAfterText		: '页',
                currPageBeforeText		: '当前第',
                currPageAfterText		: '页',
                totalInfoSplitStr		: '/',
                totalRecordsBeforeText	: '共',
                totalRecordsAfterText	: '条数据',
                gopageBeforeText		: ' 转到',
                gopageButtonOkText		: '确定',
                gopageAfterText			: '页',
                buttonTipBeforeText		: '第',
                buttonTipAfterText		: '页'
            }*/

            //,
            //mode : 'click',//默认值是link，可选link或者click
            //click : function(n){
            //	this.selectPage(n);
            //  return false;
            //}
        });
    });
</script>
</html>
