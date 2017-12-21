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
    <link rel="stylesheet" href="${ctx}/css/style.css">
    <link rel="stylesheet" href="${ctx}/css/comment.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/ionicons/2.0.1/css/ionicons.min.css">
</head>
<body>
<jsp:include page="../core/top.jsp"/>
<div class="content-body">
    <div class="container">
        <div class="row">
            <main class="col-md-12">
                <article class="col-md-12 post">
                    <h3 style="text-align: center; position: relative;font-size: 26px; margin-bottom: 50px;">留言</h3>
                    <div class="entry-content clearfix">
                        <img class="img-responsive" src="${ctx}/images/banner_top.jpg" alt="banner">
                    </div>
                </article>
                <aside class="col-md-9">
                    <div class="post post-1">
                        <div class="commentAll">
                            <!--评论区域 begin-->
                            <div class="reviewArea clearfix">
                                <script type="text/plain" id="container">
                                    <p>留点什么吧！</p>
                                </script>
                                <a href="javascript:;" class="plBtn">留言</a>
                            </div>
                            <!--评论区域 end-->
                            <!--回复区域 begin-->
                            <div class="comment-show">
                                <div class="comment-show-con clearfix">
                                    <div class="comment-show-con-img pull-left"><img src="${ctx}/images/header-img-comment_03.png" alt=""></div>
                                    <div class="comment-show-con-list pull-left clearfix">
                                        <div class="pl-text clearfix">
                                            <a href="#" class="comment-size-name">张三 : </a>
                                            <span class="my-pl-con">&nbsp;来啊 造作啊!</span>
                                        </div>
                                        <div class="date-dz">
                                            <span class="date-dz-left pull-left comment-time">2017-5-2 11:11:39</span>
                                            <div class="date-dz-right pull-right comment-pl-block">
                                                <a href="javascript:;" class="removeBlock">删除</a>
                                                <a href="javascript:;" class="date-dz-pl pl-hf hf-con-block pull-left">回复</a>
                                                <span class="pull-left date-dz-line">|</span>
                                                <a href="javascript:;" class="date-dz-z pull-left"><i class="date-dz-z-click-red"></i>赞 (<i class="z-num">666</i>)</a>
                                            </div>
                                        </div>
                                        <div class="hf-list-con" style="display: block;">
                                            <div class="all-pl-con">
                                                <div class="pl-text hfpl-text clearfix">
                                                    <a href="#" class="comment-size-name">我的名字 : </a>
                                                    <span class="my-pl-con">
                                                        回复<a href="#" class="atName">@张三 </a> :  adsadsa</span>
                                                </div>
                                                <div class="date-dz">
                                                    <span class="date-dz-left pull-left comment-time">2017-12-21 13:56:02</span>
                                                    <div class="date-dz-right pull-right comment-pl-block">
                                                        <a href="javascript:;" class="removeBlock">删除</a>
                                                        <a href="javascript:;" class="date-dz-pl pl-hf hf-con-block pull-left">回复</a>
                                                        <span class="pull-left date-dz-line">|</span>
                                                        <a href="javascript:;" class="date-dz-z pull-left">
                                                            <i class="date-dz-z-click-red"></i>赞 (<i class="z-num">666</i>)</a>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="all-pl-con">
                                                <div class="pl-text hfpl-text clearfix">
                                                    <a href="#" class="comment-size-name">
                                                        我的名字 :
                                                    </a>
                                                    <span class="my-pl-con">
                                                        回复<a href="#" class="atName">@我的名字 </a> :  dsadsa
                                                    </span>
                                                </div>
                                                <div class="date-dz">
                                                    <span class="date-dz-left pull-left comment-time">2017-12-21 13:55:58</span>
                                                    <div class="date-dz-right pull-right comment-pl-block">
                                                        <a href="javascript:;" class="removeBlock">删除</a>
                                                        <a href="javascript:;" class="date-dz-pl pl-hf hf-con-block pull-left">回复</a>
                                                        <span class="pull-left date-dz-line">|</span>
                                                        <a href="javascript:;" class="date-dz-z pull-left">
                                                            <i class="date-dz-z-click-red"></i>赞 (<i class="z-num">666</i>)</a>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="all-pl-con">
                                                <div class="pl-text hfpl-text clearfix">
                                                    <a href="#" class="comment-size-name">我的名字 : </a>
                                                    <span class="my-pl-con">
                                                        回复<a href="#" class="atName">@张三 </a> :  dsadsa</span>
                                                </div>
                                                <div class="date-dz">
                                                    <span class="date-dz-left pull-left comment-time">2017-12-21 13:55:52</span>
                                                    <div class="date-dz-right pull-right comment-pl-block">
                                                        <a href="javascript:;" class="removeBlock">删除</a>
                                                        <a href="javascript:;" class="date-dz-pl pl-hf pull-left hf-con-block">回复</a>
                                                        <span class="pull-left date-dz-line">|</span>
                                                        <a href="javascript:;" class="date-dz-z pull-left">
                                                            <i class="date-dz-z-click-red"></i>赞 (<i class="z-num">666</i>)</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--回复区域 end-->
                        </div>
                    </div>
                </aside>
                <aside class="col-md-3">
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
<script src="${ctx}/js/jquery.flexText.js"></script>
<script src="${ctx}/js/script.js"></script>
<script src="${ctx}/ueditor/ueditor.config.js"></script>
<script src="${ctx}/ueditor/ueditor.all.js"></script>
<script>
//    textarea高度自适应
    $(function () {
        $('.content').flexText();
        var ue = UE.getEditor('container',{
            toolbars:[['FullScreen', 'Source', 'Undo', 'Redo','Bold','simpleupload','test']],
            //focus时自动清空初始化时的内容
            autoClearinitialContent:true,
            //关闭字数统计
            wordCount:false,
            //关闭elementPath
            elementPathEnabled:false,
            //更多其他参数，请参考ueditor.config.js中的配置项
            initialFrameWidth :'100%',//设置编辑器宽度
            scaleEnabled:false//设置不自动调整高度
        });
        ue.ready(function(){
            UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
            UE.Editor.prototype.getActionUrl = function(action) {
                if (action == 'ueditor/uploadImg') {
                    return 'http://localhost:6060/page/Demo.jsp';
                } else if (action == 'uploadvideo') {
                    return 'http://a.b.com/video.php';
                } else {
                    return this._bkGetActionUrl.call(this, action);
                }
            }
        })
    });
//    textarea限制字数
    function keyUP(t){
        var len = $(t).val().length;
        if(len > 139){
            $(t).val($(t).val().substring(0,140));
        }
    }
//点击评论创建评论条
$('.commentAll').on('click','.plBtn',function(){
    var myDate = new Date();
    //获取当前年
    var year=myDate.getFullYear();
    //获取当前月
    var month=myDate.getMonth()+1;
    //获取当前日
    var date=myDate.getDate();
    var h=myDate.getHours();       //获取当前小时数(0-23)
    var m=myDate.getMinutes();     //获取当前分钟数(0-59)
    if(m<10) m = '0' + m;
    var s=myDate.getSeconds();
    if(s<10) s = '0' + s;
    var now=year+'-'+month+"-"+date+" "+h+':'+m+":"+s;
    //获取输入内容
    var oSize = $(this).siblings('.flex-text-wrap').find('.comment-input').val();
    console.log(oSize);
    //动态创建评论模块
    oHtml = '<div class="comment-show-con clearfix"><div class="comment-show-con-img pull-left"><img src="images/header-img-comment_03.png" alt=""></div> <div class="comment-show-con-list pull-left clearfix"><div class="pl-text clearfix"> <a href="#" class="comment-size-name">David Beckham : </a> <span class="my-pl-con">&nbsp;'+ oSize +'</span> </div> <div class="date-dz"> <span class="date-dz-left pull-left comment-time">'+now+'</span> <div class="date-dz-right pull-right comment-pl-block"><a href="javascript:;" class="removeBlock">删除</a> <a href="javascript:;" class="date-dz-pl pl-hf hf-con-block pull-left">回复</a> <span class="pull-left date-dz-line">|</span> <a href="javascript:;" class="date-dz-z pull-left"><i class="date-dz-z-click-red"></i>赞 (<i class="z-num">666</i>)</a> </div> </div><div class="hf-list-con"></div></div> </div>';
    if(oSize.replace(/(^\s*)|(\s*$)/g, "") != ''){
        $(this).parents('.reviewArea ').siblings('.comment-show').prepend(oHtml);
        $(this).siblings('.flex-text-wrap').find('.comment-input').prop('value','').siblings('pre').find('span').text('');
    }
});
    //点击回复动态创建回复块
$('.comment-show').on('click','.pl-hf',function(){
    //获取回复人的名字
    var fhName = $(this).parents('.date-dz-right').parents('.date-dz').siblings('.pl-text').find('.comment-size-name').html();
    //回复@
    var fhN = '回复@'+fhName;
    //var oInput = $(this).parents('.date-dz-right').parents('.date-dz').siblings('.hf-con');
    var fhHtml = '<div class="hf-con pull-left"> <textarea class="content comment-input hf-input" placeholder="" onkeyup="keyUP(this)"></textarea> <a href="javascript:;" class="hf-pl">回复</a></div>';
    //显示回复
    if($(this).is('.hf-con-block')){
        $(this).parents('.date-dz-right').parents('.date-dz').append(fhHtml);
        $(this).removeClass('hf-con-block');
        $('.content').flexText();
        $(this).parents('.date-dz-right').siblings('.hf-con').find('.pre').css('padding','6px 15px');
        //console.log($(this).parents('.date-dz-right').siblings('.hf-con').find('.pre'))
        //input框自动聚焦
        $(this).parents('.date-dz-right').siblings('.hf-con').find('.hf-input').val('').focus().val(fhN);
    }else {
        $(this).addClass('hf-con-block');
        $(this).parents('.date-dz-right').siblings('.hf-con').remove();
    }
});
//评论回复块创建
$('.comment-show').on('click','.hf-pl',function(){
    var oThis = $(this);
    var myDate = new Date();
    //获取当前年
    var year=myDate.getFullYear();
    //获取当前月
    var month=myDate.getMonth()+1;
    //获取当前日
    var date=myDate.getDate();
    var h=myDate.getHours();       //获取当前小时数(0-23)
    var m=myDate.getMinutes();     //获取当前分钟数(0-59)
    if(m<10) m = '0' + m;
    var s=myDate.getSeconds();
    if(s<10) s = '0' + s;
    var now=year+'-'+month+"-"+date+" "+h+':'+m+":"+s;
    //获取输入内容
    var oHfVal = $(this).siblings('.flex-text-wrap').find('.hf-input').val();
    console.log(oHfVal)
    var oHfName = $(this).parents('.hf-con').parents('.date-dz').siblings('.pl-text').find('.comment-size-name').html();
    var oAllVal = '回复@'+oHfName;
    if(oHfVal.replace(/^ +| +$/g,'') == '' || oHfVal == oAllVal){

    }else {
        $.getJSON(${ctx}+"/json/pl.json",function(data){
            var oAt = '';
            var oHf = '';
            $.each(data,function(n,v){
                delete v.hfContent;
                delete v.atName;
                var arr;
                var ohfNameArr;
                if(oHfVal.indexOf("@") == -1){
                    data['atName'] = '';
                    data['hfContent'] = oHfVal;
                }else {
                    arr = oHfVal.split(':');
                    ohfNameArr = arr[0].split('@');
                    data['hfContent'] = arr[1];
                    data['atName'] = ohfNameArr[1];
                }

                if(data.atName == ''){
                    oAt = data.hfContent;
                }else {
                    oAt = '回复<a href="#" class="atName">@'+data.atName+'</a> : '+data.hfContent;
                }
                oHf = data.hfName;
            });

            var oHtml = '<div class="all-pl-con"><div class="pl-text hfpl-text clearfix"><a href="#" class="comment-size-name">我的名字 : </a><span class="my-pl-con">'+oAt+'</span></div><div class="date-dz"> <span class="date-dz-left pull-left comment-time">'+now+'</span> <div class="date-dz-right pull-right comment-pl-block"> <a href="javascript:;" class="removeBlock">删除</a> <a href="javascript:;" class="date-dz-pl pl-hf hf-con-block pull-left">回复</a> <span class="pull-left date-dz-line">|</span> <a href="javascript:;" class="date-dz-z pull-left"><i class="date-dz-z-click-red"></i>赞 (<i class="z-num">666</i>)</a> </div> </div></div>';
            oThis.parents('.hf-con').parents('.comment-show-con-list').find('.hf-list-con').css('display','block').prepend(oHtml) && oThis.parents('.hf-con').siblings('.date-dz-right').find('.pl-hf').addClass('hf-con-block') && oThis.parents('.hf-con').remove();
        });
    }
});
//删除评论块
$('.commentAll').on('click','.removeBlock',function(){
    var oT = $(this).parents('.date-dz-right').parents('.date-dz').parents('.all-pl-con');
    if(oT.siblings('.all-pl-con').length >= 1){
        oT.remove();
    }else {
        $(this).parents('.date-dz-right').parents('.date-dz').parents('.all-pl-con').parents('.hf-list-con').css('display','none')
        oT.remove();
    }
    $(this).parents('.date-dz-right').parents('.date-dz').parents('.comment-show-con-list').parents('.comment-show-con').remove();

})
//点赞
$('.comment-show').on('click','.date-dz-z',function(){
    var zNum = $(this).find('.z-num').html();
    if($(this).is('.date-dz-z-click')){
        zNum--;
        $(this).removeClass('date-dz-z-click red');
        $(this).find('.z-num').html(zNum);
        $(this).find('.date-dz-z-click-red').removeClass('red');
    }else {
        zNum++;
        $(this).addClass('date-dz-z-click');
        $(this).find('.z-num').html(zNum);
        $(this).find('.date-dz-z-click-red').addClass('red');
    }
})
</script>
</html>

