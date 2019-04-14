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
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">订单管理</strong>
            <small></small>
        </div>
    </div>
    <hr>
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-3">
        </div>
        <div class="am-u-sm-12 am-u-md-3">
            <div class="am-input-group am-input-group-sm">
                <input type="text" class="am-form-field" id="input_search" value="${oName}">
                <span class="am-input-group-btn">
                    <button class="am-btn am-btn-default" type="button" id="input_search_btn">搜索</button>
                </span>
            </div>
        </div>
    </div>


</div>

<div class="goods_list">
    <ul class="title_ul">
        <li>订单用户</li>
        <li>订单总金额</li>
        <li>订单号</li>
        <li>下单时间</li>
        <li>配送地址</li>
        <li>联系电话</li>
        <li>操作</li>
    </ul>
    <div id="info_box">
        <c:forEach items="${orderByUid}" var="order" varStatus="i">
            <ul class="list_goods_ul">
                <li>${order.a_username}</li>
                <li style="color: red;">￥${order.o_cost}</li>
                <li class="order_info" onclick="orderInfo('${order.o_id}')">${order.o_serialnumber}</li>
                <li>${order.o_createtime}</li>
                <li style="font-size:12px;">${order.a_area}${order.a_address}</li>
                <li>${order.a_phone}</li>
                <li>
                    <img class="img_icon" src="${pageContext.request.contextPath}/admin/images/delete_icon.png"
                         alt="删除" onclick="delOrder('${order.o_id}',this)">
                </li>
            </ul>
        </c:forEach>
        <c:if test="${empty orderByUid}">
            <ul class="list_goods_ul">
                <li>没有查询到记录</li>
            </ul>
        </c:if>
    </div>
    <!--分页-->
    <div id="page" class="page_div"></div>
    <div id="showInfo" class="modal_content">
    </div>
    <div id="showBox" class="showBox">
        <h5>是否确认删除?</h5>
        <button class="am-btn am-btn-default" type="button" id="delete">确认</button>
        <button class="am-btn am-btn-default" type="button" style="margin-left:20px;" id="exitShowBow">取消</button>
    </div>
</div>

<script src="${pageContext.request.contextPath}/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/paging.js"></script>
<script>
    var oid;
    //分页
    $(function () {
        $("#page").paging({
            pageNo: ${page.currPaNo},
            totalPage: ${page.pageCounts},
            totalSize: ${page.allCounts},
            callback: function (num) {
                $(window).attr('location', '${pageContext.request.contextPath}/admin_order?action=index&currPaNo=' + num);
            }
        });
        $("#exitShowBow").click(function () {
            $("#showBox").fadeOut();
        });
        $("#input_search_btn").click(function () {
            $(window).attr('location', '${pageContext.request.contextPath}/admin_order?action=index&currPaNo=${curr}&oName=' + $("#input_search").val().trim());
        });

        $("#delete").click(function () {
            $("#showBox").fadeOut();
            $.ajax({
                "url": "${pageContext.request.contextPath}/admin_order",
                "type": "POST",
                "data": {
                    "action": "delOrder",
                    "oid": oid,
                },
                "dataType": "JSON",
                "success": function (data) {
                    if (data.status == 1) {
                        $('#showInfo').text("删除成功!");
                        $('#showInfo').fadeIn();
                        close();
                    } else {
                        $('#showInfo').text(data.message);
                        $('#showInfo').fadeIn();
                        setTimeout(function () {
                            $('#showInfo').fadeOut();
                        }, 1000)
                    }
                }
            });
        });


        $("#add").click(function () {
            $(window).attr('location', '${pageContext.request.contextPath}/order_info.jsp');
        });


    });

    function close() {
        setTimeout(function () {
            $('#showInfo').fadeOut();
            $(window).attr('location', '${pageContext.request.contextPath}/admin_order?action=index&flag=true&currPaNo=${curr}');
        }, 1000)
    }

    function delOrder(o_id) {
        oid = o_id;
        $("#showBox").fadeIn();
    }

    function orderInfo(oid) {
        $(window).attr('location', '${pageContext.request.contextPath}/admin_order?action=orderInfo&oid=' + oid);
    }
</script>

</body>
</html>