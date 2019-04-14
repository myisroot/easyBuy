<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/common.css">
</head>
<style>
    .admin_top {
        height: 70px;
        background: #1cc09f;
        text-align: right;
        padding-right: 70px;
        color: white;
        position: relative;
    }

    .admin_top span {
        position: absolute;
        right: 10px;
        top: 30px;

    }

    .admin_top img {
        margin-top: 20px;
    }

    .admin_top .top_left {
        width: 250px;
        height: 70px;
        background: #283643;
        float: left;
        color: white;
        font-weight: bold;
        text-align: center;
        padding-top: 20px;
        font-size: 20px;
    }

    .admin_top .top_left:hover {
        background: #000;
    }
</style>
<body style="background:blue">

<div class="admin_top">
    <div class="top_left">
        易买网管理系统
    </div>

    <div class="" id="user" style="margin-right: 80px;">
        <img src="${pageContext.request.contextPath}/admin/images/user_icon.png">
        <span style="font-size: 20px;font-weight: bold;margin-top: -7px;margin-right: 70px;">${admin_user.u_loginname}</span>
    </div>
    <p style="margin-right: -40px;margin-top: -28px; cursor: pointer;font-size: 15px; "><a
            href="javascript:void (0)" onclick="exit()" style="color: black;">[退出]</a></p>
</div>

<script src="${pageContext.request.contextPath}/admin/js/jquery.min.js"></script>
<script>
    function exit() {
        $.ajax({
            "url": "${pageContext.request.contextPath}/admin_user",
            "type": "POST",
            "data": {
                "action": "exit",
            },
            "dataType": "JSON",
            "success": function (data) {
                if (data.status == 1) {
                    window.parent.location.reload();
                }
            }
        });
    }
</script>
</body>
</html>