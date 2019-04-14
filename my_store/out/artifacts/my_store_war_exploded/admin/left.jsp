<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/common.css">
    <style>
        body {
            border-top: 1px solid;
        }

        ul {
            width: 250px;
            padding-top: 50px;
        }

        ul li {
            color: #B5B5B5;
            width: 250px;
            height: 50px;
            line-height: 50px;
            border-bottom: 1px solid rgba(107, 108, 109, 0.19);
            position: relative;
        }

        ul li a {
            display: block;
            width: 250px;
            height: 50px;
            color: #B5B5B5;
            background: transparent;
            text-shadow: none;
            font-size: 15px;
            text-decoration: none;
            float: left;
            padding-left: 50px;
        }

        ul li a:hover {
            background: #1cc09f;
            color: white;
            text-decoration: none;
        }

        ul li a:hover {

        }

        ul li a i {
            display: inline-block;
            width: 30px;
            height: 30px;
            float: left;
            position: absolute;
            left: 10px;
            top: 8px;
        }

        ul li:nth-child(1) a i {
            background: url("images/category_icon.png") no-repeat;
        }

        ul li:nth-child(2) a i {
            background: url("images/goods_icon.png") no-repeat;
        }

        ul li:nth-child(3) a i {
            background: url("images/admin_icon.png") no-repeat;
        }

        ul li:nth-child(4) a i {
            background: url("images/goods_icon.png") no-repeat;
        }

        ul li:nth-child(5) a i {
            background: url("images/category_icon.png") no-repeat;
        }

        ul li:nth-child(6) a i {
            background: url("images/category_icon.png") no-repeat;
        }

        ul li:nth-child(7) a i {
            background: url("images/admin_icon.png") no-repeat;
        }

        ul li:nth-child(1) a:hover i {
            background: url("images/category_hover.png") no-repeat;
        }

        ul li:nth-child(2) a:hover i {
            background: url("images/goods_hover.png") no-repeat;
        }

        ul li:nth-child(3) a:hover i {
            background: url("images/admin_hover.png") no-repeat;
        }

        ul li:nth-child(4) a:hover i {
            background: url("images/goods_icon.png") no-repeat;
        }

        ul li:nth-child(5) a:hover i {
            background: url("images/category_icon.png") no-repeat;
        }

        ul li:nth-child(6) a:hover i {
            background: url("images/category_icon.png") no-repeat;
        }

        ul li:nth-child(7) a:hover i {
            background: url("images/admin_hover.png") no-repeat;
        }

    </style
    >
</head>

<body style="background:#283643;">

<ul>
    <li><a href="${pageContext.request.contextPath}/admin_product?action=allCategory" target="mainFrame"><i></i>分类管理</a>
    </li>
    <li><a href="${pageContext.request.contextPath}/admin_product?action=allProduct"
           target="mainFrame"><i></i>商品管理</a></li>
    <c:if test="${admin_user.u_isadmin==ROOT}">
        <li><a href="${pageContext.request.contextPath}/admin_user?action=getAdminUsers&flag=true"
               target="mainFrame"><i></i>用户管理</a>
        </li>
    </c:if>
    <li><a href="${pageContext.request.contextPath}/admin_order?action=index&flag=true"
           target="mainFrame"><i></i>订单管理</a></li>
    <li><a href="${pageContext.request.contextPath}/admin_news?action=getNews" target="mainFrame"><i></i>新闻管理</a></li>
    <c:if test="${admin_user.u_isadmin==ROOT}">
        <li><a href="${pageContext.request.contextPath}/admin_user?action=getAdminUsers&flag=false"
               target="mainFrame"><i></i>权限管理</a>
        </li>
    </c:if>
</ul>

</body>
</html>