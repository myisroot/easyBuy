<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <title>商城后台管理系统</title>
    <link href="../favicon.ico" rel="shortcut icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/style.css" type="text/css"/>

</head>

<body>
<div id="container">

    <form action="${pageContext.request.contextPath}/admin_login" method="post">
        <div class="login">商城后台管理系统
            <span style="color:red;font-size: 13px" id="err"></span>
        </div>
        <div class="username-text">用户名:</div>
        <div class="password-text">密码:</div>
        <input type="hidden" name="action" value="index">
        <div class="username-field">
            <input type="text" name="username" value="admin" id="username"/>
        </div>
        <div class="password-field">
            <input type="password" name="password" value=""/>
        </div>
        <input type="checkbox" name="remember-me" id="remember-me"/><label for="remember-me">记住用户名</label>
        <div class="forgot-usr-pwd"></div>
        <input type="button" name="submit" value="GO" onclick="adminLogin()"/>
    </form>
</div>
<script src="${pageContext.request.contextPath}/admin/js/jquery.min.js"></script>
<script>
    function adminLogin() {
        var username = $("input[name='username']").val();
        var password = $("input[name='password']").val();
        $.ajax({
            "url": "${pageContext.request.contextPath}/admin_login",
            "type": "POST",
            "data": {
                "action": "login",
                "username": username,
                "password": password,
            },
            "dataType": "JSON",
            "success": function (data) {
                if (data.status == 1) {
                    window.location = '${pageContext.request.contextPath}/admin/admin_index.jsp';
                } else {
                    $('#err').html('用户名或者密码错误');
                }
            }
        });
    }
</script>
</body>
</html>
