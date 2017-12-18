<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../commons/taglibs.jsp" %>
<!-- Mobile Menu -->
<div class="overlay overlay-hugeinc">
    <button type="button" class="overlay-close">
        <span class="ion-ios-close-empty"></span>
    </button>
    <nav>
        <ul>
            <li><a href="${ctx}/index.jsp">Home</a></li>
            <li><a href="${ctx}/page/article.jsp">Blog</a></li>
            <li><a href="${ctx}/page/about.jsp">About</a></li>
            <li><a href="${ctx}/page/contact.jsp">Contact</a></li>
        </ul>
    </nav>
</div>