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

    <title>登入</title>
</head>
<body>
<%@include file="common/notLogin/header.jsp" %>
<div class="log_bg">
    <div class="top">
        <div class="logo"><a href="index.jsp"><img src="${ctx}/shop/images/logo.png"/></a></div>
    </div>
    <div class="login">
        <div class="log_img"><img src="${ctx}/shop/images/l_img.png" width="611" height="425"/></div>
        <div class="log_c">
            <form action="${ctx}/login" method="post">
                <input type="hidden" name="action" value="login"/>
                <table border="0" style="width:370px; font-size:14px; margin-top:30px;" cellspacing="0" cellpadding="0">
                    <tr height="50" valign="top">
                        <td width="55">&nbsp;</td>
                        <td>
                            <span class="fl" style="font-size:24px;">登录</span>
                            <span class="fr">还没有商城账号，<a href="${ctx}/shop/Regist.jsp"
                                                        style="color:#ff4e00;">立即注册</a></span>
                        </td>
                    </tr>
                    <tr height="70">
                        <td>用户名</td>
                        <td><input type="text" value="admin" class="l_user" name="u_loginname"/></td>
                    </tr>
                    <tr height="15">
                        <td></td>
                        <td id="name_info"></td>
                    </tr>
                    <tr height="70">
                        <td>密&nbsp; &nbsp; 码</td>
                        <td><input type="password" value="1234" class="l_pwd" name="u_pwd"/></td>
                    </tr>
                    <tr height="10">
                        <td></td>
                        <td id="err_info" style="color: red"></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td style="font-size:12px; padding-top:20px;">
                	<span style="font-family:'宋体';" class="fl">
                    	<label class="r_rad"><input type="checkbox"/></label><label class="r_txt">请保存我这次的登录信息</label>
                    </span>
                            <span class="fr"><a href="${ctx}/shop/setpwd.jsp" style="color:#ff4e00;">忘记密码</a></span>
                        </td>
                    </tr>
                    <tr height="60">
                        <td>&nbsp;</td>
                        <td><input type="button" value="登录" class="log_btn"/></td>
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
</body>
<script type="text/javascript">
    $(function () {
        $("input[value='登录']").click(
            function () {
                var $name = $("input[name='u_loginname']").val().trim();
                var $pwd = $("input[name='u_pwd']").val().trim();
                if ($name == "") {
                    $("#name_info").text("请输入用户名");
                    return false;
                }
                if ($pwd == "") {
                    $("#err_info").text("请输入密码");
                    return false;
                }
                $.post("${ctx}/login",
                    {
                        "action": "login",
                        "u_loginname": $name,
                        "u_pwd": $pwd,
                        "flag": "false"
                    }
                    , function (data) {
                        if (data == "false") {
                            $("#err_info").text("用户名或密码错误");
                            return false;
                        } else {
                            $("form").submit();
                        }
                    });
            }
        );
    });
</script>


<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
</html>
