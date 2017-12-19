<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../commons/meta.jsp" %>
<%@ include file="../commons/taglibs.jsp" %>
<html>
<head>
    <title>Demo</title>
</head>
<body>
<h1>UEditor简单功能</h1>

<!--style给定宽度可以影响编辑器的最终宽度-->
<script type="text/plain" id="container">
        <p>这里我可以写一些输入提示</p>
</script>
</body>
<script src="${ctx}/js/jquery-3.2.1.js"></script>
<script src="${ctx}/ueditor/ueditor.config.js"></script>
<script src="${ctx}/ueditor/ueditor.all.js"></script>
<script>
    var ue = UE.getEditor('container',{
        toolbars:[['FullScreen', 'Source', 'Undo', 'Redo','Bold','simpleupload','test']],
        //focus时自动清空初始化时的内容
        autoClearinitialContent:true,
        //关闭字数统计
        wordCount:false,
        //关闭elementPath
        elementPathEnabled:false,
        //更多其他参数，请参考ueditor.config.js中的配置项
        initialFrameWidth :720,//设置编辑器宽度
        initialFrameHeight:280,//设置编辑器高度
        scaleEnabled:true//设置不自动调整高度
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
</script>
</html>
