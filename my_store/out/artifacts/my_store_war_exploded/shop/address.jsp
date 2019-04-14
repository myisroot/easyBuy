<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>我的地址</title>
</head>
<style>
    .show_address {
        border: 1px solid #CCCCCC;
        text-align: center;
    }


    .show_address tr td {
        border: 1px solid #CCCCCC;
        width: 120px;
        height: 55px;
    }
</style>
<body>
<%
    Object user = session.getAttribute("user");
    if (null == user) {
        response.sendRedirect(request.getContextPath() + "/shop/Login.jsp");
    }
%>
<%@include file="common/header.jsp" %>
<%@include file="common/search.jsp" %>
<div class="i_bg bg_color">
    <!--Begin 用户中心 Begin -->
    <div class="m_content">
        <%@include file="common/left.jsp" %>
        <div class="m_right">
            <p></p>
            <div class="mem_tit">收货地址</div>
            <table border="0" class="add_tab" style="width:930px;" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="135" align="right">配送地区</td>
                    <td colspan="3" style="font-family:'宋体';">
                        <select name="province" id="province">
                            <option value="请选择">请选择</option>
                        </select>
                        <select name="city" id="city">
                            <option value="请选择">请选择</option>
                        </select>
                        <select name="town" id="town">
                            <option value="请选择">请选择</option>
                        </select>
                        <span style="color: red;"></span>
                    </td>
                </tr>
                <tr>
                    <td align="right">详细地址</td>
                    <td style="font-family:'宋体';"><input type="text" value="" class="add_ipt"
                                                         name="address"/><span style="color: red;">（必填）</span></td>
                </tr>
                <tr>
                    <td align="right">收货人姓名</td>
                    <td style="font-family:'宋体';"><input type="text" value="" class="add_ipt"
                                                         name="aName"/><span style="color: red;">（必填）</span></td>
                </tr>
                <tr>
                    <td align="right">联系电话</td>
                    <td style="font-family:'宋体';"><input type="text" value="" class="add_ipt"
                                                         name="aPhone"/><span style="color: red;">（必填）</span></td>
                </tr>
                <tr>
                    <td align="right">地址备注</td>
                    <td style="font-family:'宋体';"><input type="text" value="" class="add_ipt"
                                                         name="aRemark"/><span style="color: red;">（必填）</span></td>
                </tr>
            </table>
            <div class="mem_tit">
                <a href="javascript:void (0);" onclick="addAddress()"><img
                        src="${pageContext.request.contextPath}/shop/images/add_ad.gif"/></a>
            </div>

            <table border="0" class="show_address" style="width:98%; margin:10px auto;" cellspacing="0" cellpadding="0">
                <tr style="background-color: #CCCCCC;">
                    <td>收货人</td>
                    <td>收货地区</td>
                    <td>详细地址</td>
                    <td>联系电话</td>
                    <td>备注</td>
                    <td>操作</td>
                    <td></td>
                </tr>
                <c:forEach items="${allAddress}" var="address">
                    <tr>
                        <input type="hidden" value="${address.a_id}">
                        <td>${address.a_username}</td>
                        <td>${address.a_area}</td>
                        <td>${address.a_address}</td>
                        <td>${address.a_phone}</td>
                        <td>${address.a_remark}</td>
                        <td><a href="javascript:void (0)" onclick="delAddress('${address.a_id}',this)">删除</a>
                            &nbsp;|&nbsp;
                            <a href="${pageContext.request.contextPath}/address?action=toEditAddress&uid=${user.u_id}&aid=${address.a_id}">编辑</a>
                        </td>
                        <c:if test="${address.a_isdefault==1}">
                            <td class="setDefault"><span
                                    style="background-color:#CCCCCC;padding: 5px;border-radius:2px;">默认地址</span></td>
                        </c:if>
                        <c:if test="${address.a_isdefault!=1}">
                            <td class="setDefault">
                                <a href="javascript:void (0);">设为默认</a>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>

        </div>
    </div>
    <!--End 用户中心 End-->
    <!--Begin Footer Begin -->
    <%@include file="common/footer.jsp" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/shop/js/address/area.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/shop/js/address/select.js"></script>
    <script src="${pageContext.request.contextPath}/shop/alert/js/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/shop/alert/js/jquery.hDialog.min.js"></script>
    <script type="text/javascript">
        $(".i_car").hide();
        /**
         * 设置默认值
         */
        $(".setDefault").click(function () {
            var $this = $(this);
            if ($(this).text().trim() == '默认地址') {
                return;
            }
            var aid = $this.parent().find("input[type='hidden']").val().toString().trim();
            $.ajax({
                "url": "${pageContext.request.contextPath}/address",
                "type": "POST",
                "data": {
                    "action": "editDefault",
                    "aid": aid,
                    "uid": '${user.u_id}'
                },
                "dataType": "JSON",
                "success": function (data) {
                    if (data.status == 1) {
                        $(".setDefault").each(function () {
                            if ($(this).text().toString().trim() == '默认地址') {
                                $(this).html("<a href='javascript:void(0);'>设为默认</a>");
                            }
                        });
                        $this.html("<span style='background-color:#CCCCCC;padding: 5px;border-radius:2px;'>默认地址</span>");
                    } else {
                        $.dialog('alert', '提示', '正在处理中...', 500); //2s自动关闭
                    }
                }
            });
        });

        /**
         *添加地址
         */
        function addAddress() {
            var province = $("#province").select().val().toString().trim();
            var city = $("#city").select().val().toString().trim();
            var town = $("#town").select().val().toString().trim();
            var address = $("input[name='address']").val().toString().trim();
            var aName = $("input[name='aName']").val().toString().trim();
            var aPhone = $("input[name='aPhone']").val().toString().trim();
            var aRemark = $("input[name='aRemark']").val().toString().trim();
            if (province == '请选择') {
                $("#town").next().text("请选择province");
                return;
            }
            if (city == '请选择') {
                $("#town").next().text("请选择city");
                return;
            }
            if (town == '请选择') {
                $("#town").next().text("请选择town");
                return;
            } else {
                $("#town").next().text("");
            }
            if (address == '') {
                $("input[name='address']").next().text("请输入详细地址");
                return;
            } else {
                $("input[name='address']").next().text("（必填）");
            }
            if (aName == '') {
                $("input[name='aName']").next().text("请输入收货人姓名");
                return;
            } else {
                $("input[name='aName']").next().text("（必填）");
            }
            if (aPhone == '') {
                $("input[name='aPhone']").next().text("请输入联系电话");
                return;
            } else {
                $("input[name='aPhone']").next().text("（必填）");
            }
            $.ajax({
                "url": "${pageContext.request.contextPath}/address",
                "type": "POST",
                "data": {
                    "action": "addAddress",
                    "area": province + "-" + city + "-" + town,
                    "address": address,
                    "aName": aName,
                    "aPhone": aPhone,
                    "uid": '${user.u_id}',
                    "aRemark": aRemark,
                    "flag": "true"
                },
                "dataType": "JSON",
                "success": function (data) {
                    if (data.status == 1) {
                        $.tooltip('增加成功', 1200, true, function () {
                            window.location = '${pageContext.request.contextPath}/address?action=toAddress&uid=${user.u_id}';
                        });
                    } else {
                    }
                }
            });
        }

        /**
         * 删除地址
         * @param aid
         * @param that
         */
        function delAddress(aid, that) {
            $.dialog('confirm', '提示', '您确认要删除么？', 0, function () {
                $.tooltip('删除成功～', 2000, true, function () {
                    $.ajax({
                        "url": "${pageContext.request.contextPath}/address",
                        "type": "POST",
                        "data": {
                            "action": "delAddress",
                            "aid": aid,
                            "uid": '${user.u_id}'
                        },
                        "dataType": "JSON",
                        "success": function (data) {
                            if (data.status == 1) {
                                $(that).parent().parent().remove();
                            } else {
                                alert(data.message);
                            }
                        }
                    });
                    $.closeDialog();
                });
            });
            return;
        }
    </script>
</div>
</body>
</html>
