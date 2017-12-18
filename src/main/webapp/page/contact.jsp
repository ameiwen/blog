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
                <h1 class="page-title">Contact</h1>
                <article class="post">
                    <div class="entry-content clearfix">
                        <form action="#" method="post" class="contact-form">
                            <div class="row">
                                <div class="col-md-6 col-md-offset-3">
                                    <input type="text" name="name" placeholder="Name" required>
                                    <input type="email" name="email" placeholder="Email" required>
                                    <input type="text" name="subject" placeholder="Subject" required>
                                    <textarea name="message" rows="7" placeholder="Your Message" required></textarea>
                                    <button class="btn-send btn-5 btn-5b ion-ios-paperplane"><span>Drop Me a Line</span></button>
                                </div>
                            </div>	<!-- row -->
                        </form>
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
<script src="${ctx}/js/jquery-2.1.3.min.js"></script>
<script src="${ctx}/js/bootstrap.min.js"></script>
<script src="${ctx}/js/pace.min.js"></script>
<script src="${ctx}/js/modernizr.custom.js"></script>
<script src="${ctx}/js/script.js"></script>
</html>

