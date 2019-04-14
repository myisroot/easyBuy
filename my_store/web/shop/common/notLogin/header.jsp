<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<span class="fr">
        	  <c:if test="${empty user}">
                  <span class="fl">你好,
                  请<a href="${ctx}/shop/Login.jsp">登录</a>
                  &nbsp;&nbsp;
                <a href="${ctx}/shop/Regist.jsp" style="color:#ff4e00;">免费注册</a>&nbsp;&nbsp;
              </span>
              </c:if>
            <c:if test="${!empty user}">
                  <span class="fl">你好,
                  <a href="#">${user.u_loginname}</a>&nbsp;
                <a href="${ctx}/regist?action=exit" style="color:#ff4e00;" id="exit">退出</a>&nbsp;|&nbsp;
                <a href="#">我的订单</a>&nbsp;|
              </span>
            </c:if>

            <%--</span>--%>
        <span class="fl">|&nbsp;关注我们：</span>
        <span class="s_sh"><a href="#" class="sh1">新浪</a><a href="#" class="sh2">微信</a></span>
        <span class="fr">|&nbsp;<a href="#">手机版&nbsp;<img src="${ctx}/shop/images/s_tel.png"
                                                          align="absmiddle"/></a></span>
        </span>
</body>
</html>
