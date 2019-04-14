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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/honeySwitch.css">
</head>
<body style="background:#f3f3f3;">

<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">用户管理</strong>
            <small></small>
        </div>
    </div>
    <hr>
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-3">
        </div>
        <div class="am-u-sm-12 am-u-md-3">
            <div class="am-input-group am-input-group-sm">
                <input type="text" class="am-form-field" id="input_search" value="${uName}">
                <span class="am-input-group-btn">
                    <button class="am-btn am-btn-default" type="button" id="input_search_btn">搜索</button>
                </span>
            </div>
        </div>
    </div>


</div>

<div class="goods_list">
    <ul class="title_ul">
        <li>用户名</li>
        <li>真实姓名</li>
        <li>身份证号</li>
        <li>电话</li>
        <li>邮箱</li>
        <li>注册时间</li>
        <li>是否冻结</li>
    </ul>
    <div id="info_box">
        <c:forEach items="${allUser}" var="Admin" varStatus="i">
            <ul class="list_goods_ul">
                <input type="hidden" name="uid" value="${Admin.u_id}">
                <li>${Admin.u_loginname}</li>
                <li>${Admin.u_name}</li>
                <li>${Admin.u_identitycods}</li>
                <li>${Admin.u_phone}</li>
                <li>${Admin.u_email}</li>
                <li>${Admin.u_createtime}</li>
                <li><span
                        <c:if test="${Admin.u_islogin==ISLOGIN}">class="switch-off"</c:if>
                        <c:if test="${Admin.u_islogin==NOLOGIN}">class="switch-on"</c:if>
                        onclick="checkDelete(this,'${Admin.u_id}')"></span></li>
                </li>
            </ul>
        </c:forEach>
        <c:if test="${empty allUser}">
            <ul class="list_goods_ul">
                <li>没有查询到记录</li>
            </ul>
        </c:if>
    </div>
    <!--分页-->
    <div id="page" class="page_div">1</div>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/honeySwitch.js"></script>
<script>
    //分页
    $(function () {
        $("#page").paging({
            pageNo: ${pageBean.currPaNo},
            totalPage: ${pageBean.pageCounts},
            totalSize: ${pageBean.allCounts},
            callback: function (num) {
                $(window).attr('location', '${pageContext.request.contextPath}/admin_user?action=getAdminUsers&flag=true&currPaNo='
                    + num + '&uName=' + $("#input_search").val().trim());
            }
        });

        /**
         * 关闭提示信息
         */
        $("#exitShowBow").click(function () {
            $("#showBox").fadeOut();
        });


        //删除

        //查询
        $("#input_search_btn").click(function () {
            var search = $("#input_search").val().trim();
            if (search != null) {
                $(window).attr('location', '${pageContext.request.contextPath}/admin_user?action=getAdminUsers&flag=true&currPaNo=1&uName=' + search);
            }
        });

    });


    function close() {
        setTimeout(function () {
            $('#showInfo').fadeOut();
            $(window).attr('location', '${pageContext.request.contextPath}/admin_product?action=allProduct&currPaNo=${curr}&uName' + $("#input_search").val().trim());
        }, 1000)
    }

    function checkDelete(that, pid) {
        var $class = $(that).attr("class").trim();
        if ($class == "switch-off") {
            update(pid, ${NOLOGIN}, "冻结成功");
        }
        if ($class == "switch-on") {
            update(pid, ${ISLOGIN}, "解冻成功");
        }
    }

    function closeShowInfo() {
        setTimeout(function () {
            $('#showInfo').fadeOut();
        }, 1000)
    }

    function update(uid, $class, message) {
        $.ajax({
            "url": "${pageContext.request.contextPath}/admin_user",
            "type": "POST",
            "data": {
                "action": "checkLogin",
                "flag": "true",
                "uid": uid,
                "checkType": "user",
                "class": $class,
            },
            "dataType": "JSON",
            "success": function (data) {
                if (data.status == 1) {
                    $('#showInfo').text(message);
                    $('#showInfo').fadeIn();
                    closeShowInfo();
                } else {
                    $('#showInfo').text(data.message);
                    $('#showInfo').fadeIn();
                    closeShowInfo();
                }
            }
        });
    }

</script>

</body>
</html>