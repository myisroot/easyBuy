<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/amazeui.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/pageStyle.css">
    <c:if test="${empty admin_user}">
        <script>
            window.parent.location.reload();
        </script>
    </c:if>
</head>
<body style="background:#f3f3f3;">

<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">订单详情</strong>
            <small></small>
        </div>
    </div>
    <hr>


</div>

<div class="goods_list">
    <ul class="title_ul">
        <li>序号</li>
        <li>商品图片</li>
        <li>商品名称</li>
        <li>商品数量</li>
        <li>商品小计</li>
    </ul>
    <div id="info_box">
        <c:forEach items="${allByOid}" var="order" varStatus="i">
            <ul class="list_goods_ul">
                <li>${i.index+1}</li>
                <li><img src="${pageContext.request.contextPath}/shop/images/${order.p_filename}"></li>
                <li>${order.p_name}</li>
                <li>${order.d_quantity}</li>
                <li style="color: red;">￥${order.d_cost}</li>
            </ul>
        </c:forEach>
    </div>
    <!--分页-->
</div>

<script src="${pageContext.request.contextPath}/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/paging.js"></script>
<script>
    //分页


    $("#add").click(function () {
        $(window).attr('location', '${pageContext.request.contextPath}/main.jsp');
    });

    $(".list_goods_ul>li img[alt=删除]").click(function () {
        var flag = confirm("是否确认删除？")
        if (!flag) {
            return false;
        }
    });

</script>

</body>
</html>