<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%
        String ctx = request.getContextPath();
        request.setAttribute("ctx", ctx);
    %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link type="text/css" rel="stylesheet" href="${ctx}/shop/css/style.css"/>
    <!--[if IE 6]>
    <script src="${ctx}/shop/js/iepng.js" type="text/javascript"></script>
    <script type="text/javascript">
        EvPNG.fix('div, ul, img, li, input, a');
    </script>
    <![endif]-->
    <title>找回密码</title>
</head>
<body>
<!--Begin Header Begin-->
<%@include file="common/notLogin/header.jsp" %>
<!--End Header End-->
<!--Begin Login Begin-->
<div class="log_bg">
    <div class="top">
        <div class="logo"><a href="index.jsp"><img src="${ctx}/shop/images/logo.png"/></a></div>
    </div>
    <div class="login">
        <div class="log_img"><img src="${ctx}/shop/images/l_img.png" width="611" height="425"/></div>
        <div class="log_c">
            <form action="${ctx}/login" method="post">
                <input type="hidden" name="action" value="login"/>
                <table border="0" style="width:400px; font-size:14px; margin-top:30px;"
                       cellspacing="0" cellpadding="0">
                    <tr height="50" valign="top">
                        <td width="60">&nbsp;</td>
                        <td>
                            <span class="fl" style="font-size:24px;">修改密码</span>
                            <span class="fr">还没有商城账号，<a href="${ctx}/shop/Regist.jsp"
                                                        style="color:#ff4e00;">立即注册</a></span>
                        </td>
                    </tr>
                    <tr height="50">
                        <td width="55">新密码</td>
                        <td><input type="password" class="l_pwd" name="u_pwd"/></td>
                    </tr>
                    <tr height="10">
                        <td></td>
                        <td id="pwd_err" style="color: red"></td>
                    </tr>
                    <tr height="50">
                        <td width="55">确认密码</td>
                        <td><input type="password" class="l_pwd" name="u_rpwd"/></td>
                    </tr>
                    <tr height="10">
                        <td></td>
                        <td id="err_info" style="color: red"></td>
                    </tr>
                    <tr height="50">
                        <td width="55">手机号</td>
                        <td><input type="text" class="l_user" name="email"/>
                            <button type="button" onclick="sendEmail(this)">发送验证码</button>
                        </td>
                    </tr>
                    <tr height="15">
                        <td></td>
                        <td id="name_info" style="color: red;"></td>
                    </tr>
                    <tr height="50">
                        <td width="55">验证码</td>
                        <td><input type="password" class="l_pwd" name="code"/></td>
                    </tr>
                    <tr height="10">
                        <td></td>
                        <td id="code_info" style="color: red"></td>
                    </tr>
                    <tr height="60">
                        <td></td>
                        <td><input type="button" value="修改密码" class="log_btn" onclick="updatePwd()"/></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
<!--End Login End-->
<!--Begin Footer Begin-->
<%@include file="common/notLogin/footer.jsp" %>
<!--End Footer End -->
<script type="text/javascript" src="${ctx}/shop/js/tools.js"></script>
</body>
<script type="text/javascript">
    var time = 120;
    var flag = true;

    function updatePwd() {
        var $pwd = $("input[name='u_pwd']").val().trim();
        var $rpwd = $("input[name='u_rpwd']").val().trim();
        var $email = $("input[name='email']").val().trim();
        var $code = $("input[name='code']").val().trim();
        if ($pwd == "" || $pwd == null) {
            $("#pwd_err").text("请输入新密码");
            return false;
        } else {
            $("#pwd_err").text("");
        }
        if ($rpwd == "" || $rpwd == null) {
            $("#err_info").text("请再次输入新密码");
            return false;
        } else {
            $("#err_info").text("");
        }
        if ($pwd != $rpwd) {
            $("#err_info").text("请确认输入两次密码是否相同");
            return false;
        }
        if ($code == "" || $code == null) {
            $("#code_info").text("请输入验证码");
            return false;
        } else {
            $("#code_info").text("");
        }
        $.ajax({
            "url": "${pageContext.request.contextPath}/login",
            "type": "POST",
            "data": {
                "action": "updatePwd",
                "email": $email,
                "pwd": $rpwd,
                "code": $code
            },
            "dataType": "JSON",
            "success": function (data) {
                if (data.status == 1) {
                    $("#code_info").text("修改成功");
                    setTimeout(function () {
                        window.location = '${pageContext.request.contextPath}/shop/Login.jsp';
                    }, 400);
                } else {
                    $("#code_info").text(data.message);
                }
            }
        });
    }

    function sendEmail(that) {
        if (flag) {
            flag = false;
            var $email = $("input[name='email']").val().trim();
            if ($email == "" || $email == null) {
                $("#name_info").text("请输入手机号");
                flag = true;
                return false;
            }
            if (!isPoneAvailable($("input[name='email']"))) {
                $("#name_info").text("请输入正确的手机号")
                flag = true;
                return false;
            }
            $.ajax({
                "url": "${pageContext.request.contextPath}/login",
                "type": "POST",
                "data": {
                    "action": "getEmail",
                    "email": $email,
                },
                "dataType": "JSON",
                "success": function (data) {
                    if (data.status == 1) {
                        $("#name_info").text("验证码已发送到你的手机,请注意查收!");
                    } else {
                    }
                }
            });
            var timer = setInterval(function () {
                $(that).text(time + "s后重新发送");
                if (time == -1) {
                    clearInterval(timer);
                    $(that).text("发送验证码");
                    time = 120;
                    flag = true;
                    return false;
                }
                time--;
            }, 1000);
        }
    }

</script>


<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
</html>
