<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>购物车结算</title>
</head>
<style>
    .address_box {
        float: left;
        margin: 20px 20px 20px 43px;
        width: 1110px;
        /*height: 120px;*/
    }

    .address_box ul {
        list-style: none;
        width: 32%;
        border: 2px solid #CCCCCC;
        border-radius: 3px;
        margin: 3px 3px;
        float: left;
        cursor: pointer;
    }
</style>
<body>
<!--Begin Header Begin-->
<%@include file="common/header.jsp" %>
<!--End Header End-->
<!--Begin Menu Begin-->
<%@include file="common/search.jsp" %>
<%@include file="common/menu.jsp" %>
<!--End Menu End-->
<div id="cart">
    <%@include file="cart.jsp" %>
</div>
</body>
<!--[if IE 6]>
<![endif]-->
</html>
