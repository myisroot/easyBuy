<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
    String ctx = request.getContextPath();
    request.setAttribute("ctx", ctx);
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link type="text/css" rel="stylesheet" href="${ctx}/shop/css/style.css"/>
    <!--[if IE 6]>
    <script src="js/iepng.js" type="text/javascript"></script>
    <script type="text/javascript">
        EvPNG.fix('div, ul, img, li, input, a');
    </script>
    <![endif]-->
    <title>注册</title>
</head>
<style>

    .info {
        color: red;
    }
</style>
<body>
<!--Begin Header Begin-->
<%@include file="common/notLogin/header.jsp" %>
<!--End Header End-->
<!--Begin Login Begin-->
<div class="log_bg">
    <div class="top">
        <div class="logo"><a href="${ctx}/Home?action=index"><img src="${ctx}/shop/images/logo.png"/></a></div>
    </div>
    <div class="regist">
        <div class="log_img"><img src="${ctx}/shop/images/l_img.png" width="611" height="425"/></div>
        <div class="reg_c">
            <form action="${ctx}/login" method="post">
                <input type="hidden" name="action" value="addUser"/>
                <table border="0" style="width:420px; font-size:14px; margin-top:20px;" cellspacing="0" cellpadding="0">
                    <tr height="25" valign="top">
                        <td width="95">&nbsp;</td>
                        <td>
                            <span class="fl" style="font-size:24px;">注册</span>
                            <span class="fr">已有商城账号，<a href="Login.jsp" style="color:#ff4e00;">我要登录</a></span>
                        </td>
                    </tr>
                    <tr height="40">
                        <td align="right"><font color="#ff4e00">*</font>&nbsp;用户名 &nbsp;</td>
                        <td>
                            <input type="text" value="" class="l_user" name="u_loginname"/>
                        </td>
                    </tr>
                    <tr height="18">
                        <td align="right"></td>
                        <td class="info">
                            <%--用户名--%>
                        </td>
                    </tr>
                    <tr height="25">
                        <td align="right"><font color="#ff4e00">*</font>&nbsp;密码 &nbsp;</td>
                        <td><input type="password" value="" class="l_pwd" name="u_pwd"/></td>
                    </tr>
                    <tr height="18">
                        <td align="right"></td>
                        <td class="info">
                            <%--用户名--%>
                        </td>
                    </tr>
                    <tr height="25">
                        <td align="right"><font color="#ff4e00">*</font>&nbsp;确认密码 &nbsp;</td>
                        <td><input type="password" value="" class="l_pwd" id="r_pwd"/></td>
                    </tr>
                    <tr height="18">
                        <td align="right"></td>
                        <td class="info">
                            <%--用户名--%>
                        </td>
                    </tr>
                    <tr height="25">
                        <td align="right"><font color="#ff4e00">*</font>&nbsp;邮箱 &nbsp;</td>
                        <td><input type="text" value="" class="l_email" name="u_email"/></td>
                    </tr>
                    <tr height="18">
                        <td align="right"></td>
                        <td class="info">
                            <%--用户名--%>
                        </td>
                    </tr>
                    <tr height="20">
                        <td align="right"><font color="#ff4e00">*</font>&nbsp;手机 &nbsp;</td>
                        <td><input type="text" value="" class="l_tel" name="u_phone"/></td>
                    </tr>
                    <tr height="18">
                        <td align="right"></td>
                        <td class="info">
                            <%--用户名--%>
                        </td>
                    </tr>
                    <tr height="20">
                        <td align="right"><font color="#ff4e00">*</font>&nbsp;性别 &nbsp;</td>
                        <td>男<input type="radio" name="u_sex" value="0" checked/>女<input type="radio" name="u_sex"
                                                                                         value="1"/></td>
                    </tr>
                    <tr height="15">
                        <td align="right"></td>
                        <td class="info">
                            <%--用户名--%>
                        </td>
                    </tr>
                    <tr height="20">
                        <td align="right"><font color="#ff4e00">*</font>&nbsp;真实姓名 &nbsp;</td>
                        <td><input type="text" value="" class="l_user" name="u_name"/></td>
                    </tr>
                    <tr height="15">
                        <td align="right"></td>
                        <td class="info">
                            <%--用户名--%>
                        </td>
                    </tr>
                    <tr height="20">
                        <td align="right"><font color="#ff4e00">*</font>&nbsp;身份证号码 &nbsp;</td>
                        <td><input type="text" value="" class="l_user" name="u_identitycods"/></td>
                    </tr>
                    <tr height="15">
                        <td align="right"></td>
                        <td class="info">
                            <%--用户名--%>
                        </td>
                    </tr>
                    <tr height="20">
                        <td align="right"><font color="#ff4e00">*</font>&nbsp;验证码 &nbsp;
                        </td>
                        <td>
                            <input type="text" value="" class="l_ipt" name="code"/>
                            <img src="${ctx}/GetCode" style="margin-top:5px;vertical-align:bottom" id="getCode">
                            <a href="#" style="font-size:12px; font-family:'宋体';" id="change_img">换一张</a>
                            <span style="color: red;font-size: 14px;" id="code_info"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td style="font-size:12px; padding-top:20px;">
                	<span style="font-family:'宋体';" class="fl">
                    	<label class="r_rad"><input type="checkbox"/></label><label class="r_txt">我已阅读并接受《用户协议》</label>
                    </span>
                        </td>
                    </tr>
                    <tr height="20">
                        <td></td>
                        <td class="info">
                            <%--同意--%>
                        </td>
                    </tr>
                    <tr height="25">
                        <td>&nbsp;</td>
                        <td><input type="button" value="立即注册" class="log_btn"/></td>
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
<script>
    $(function () {
        $("#change_img").click(function () {
            $("#getCode").prop("src", "${ctx}/GetCode?time=" + new Date().getTime());
        });

        $("input[value='立即注册']").click(function () {
            $(".info").text("");
            $("#code_info").text("");
            var $code = $("input[name='code']").val();
            if ($("input[name='u_loginname']").val().trim() == "") {
                $(".info:eq(0)").text("请输入用户名")
                return false;
            }
            if ($("input[name='u_pwd']").val().trim() == "") {
                $(".info:eq(1)").text("请输入密码")
                return false;
            }
            if ($("#r_pwd").val().trim() != $("input[name='u_pwd']").val().trim()) {
                $(".info:eq(2)").text("请确保两次密码输入相同")
                return false;
            }

            if ($("input[name='u_email']").val().trim() == "") {
                $(".info:eq(3)").text("请输入电子邮箱")
                return false;
            }

            if (!IsEmail($("input[name='u_email']"))) {
                $(".info:eq(3)").text("请输入正确的电子邮箱")
                return;
            }
            if ($("input[name='u_phone']").val().trim() == "") {
                $(".info:eq(4)").text("请输入手机号")
                return false;
            }
            if (!isPoneAvailable($("input[name='u_phone']"))) {
                $(".info:eq(4)").text("请输入正确的手机号")
                return false;
            }
            if ($("input[name='u_name']").val().trim() == "") {
                $(".info:eq(6)").text("请输入真实姓名")
                return false;
            }
            if ($("input[name='u_identitycods']").val().trim() == "") {
                $(".info:eq(7)").text("请输入身份证号码")
                return false;
            }
            if (!isCards($("input[name='u_identitycods']"))) {
                $(".info:eq(7)").text("请输入正确的身份证号码")
                return false;
            }

            if (!$("input[type='checkbox']").is(":checked")) {
                $(".info:eq(8)").text("请先同意用户协议")
                return false;
            }
            if ($code.trim() == "") {
                $("#code_info").text("请输入验证码");
                return false;
            }
            $.get("${ctx}/login?action=testName", "u_loginname=" + $("input[name='u_name']").val().trim(), function (data) {
                if (data == 'false') {
                    $(".info:eq(0)").text("用户名已存在")
                    return false;
                }
                $.get("${ctx}/login?action=testReGist", "code=" + $code, function (data) {
                    if (data == 'true') {
                        $("form").submit();
                    } else {
                        $("#code_info").text("验证码错误");
                    }
                });
            });

        });
    });
</script>

</body>
</html>
