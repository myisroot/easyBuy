<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<!--[if IE 6]>
<![endif]-->
<%
    request.setAttribute("ctx", request.getContextPath());
%>
<div class="i_bg">
    <div class="content mar_20">
        <img src="${ctx}/shop/images/img2.jpg"/>
    </div>
    <!--Begin 第二步：确认订单信息 Begin -->
    <div class="content mar_20">
        <div class="two_bg">
            <div class="two_t">
                <span class="fr"></span>选择收货地址
            </div>
            <div class="address_box">
                <c:forEach items="${allAddress}" var="Address" varStatus="index">
                    <ul value="${Address.a_id}"
                        <c:if test="${Address.a_isdefault==1}">flag="true" style="border:3px solid red" </c:if>
                        <c:if test="${Address.a_isdefault!=1}">flag="false"</c:if>
                    >
                        <li>${Address.a_area}&nbsp;&nbsp;(${Address.a_username}&nbsp;收)</li>
                        <li>${Address.a_address}</li>
                        <li>${Address.a_phone}</li>
                        <c:if test="${Address.a_isdefault==1}">
                            <li>默认地址</li>
                        </c:if>
                    </ul>
                </c:forEach>
            </div>
            <div class="two_t">
                <span class="fr"><a href="#">修改</a></span>商品列表
            </div>
            <table border="0" class="car_tab" style="width:1110px;" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="car_th" width="550">商品名称</td>
                    <td class="car_th" width="140">单价(￥)</td>
                    <td class="car_th" width="150">购买数量</td>
                    <td class="car_th" width="130">小计</td>
                </tr>
                <c:forEach items="${sessionScope.shopCat.shopItems}" var="item" varStatus="i">
                    <tr <c:if test="${i.index%2!=0}">
                        class="car_tr"
                    </c:if>>
                        <td>
                            <div class="c_s_img"><img src="${ctx}/shop/images/c_1.jpg" width="73" height="73"/></div>
                                ${item.easyProduct.p_name}
                        </td>
                        <td align="center">￥${item.easyProduct.p_price}</td>
                        <td align="center">${item.quantity}</td>
                        <td align="center" style="color:#ff4e00;">￥${item.cost}</td>
                    </tr>
                </c:forEach>
            </table>

            <table border="0" style="width:900px; margin-top:20px;" cellspacing="0" cellpadding="0">
                <tr height="70">
                    <td align="right">
                        <b style="font-size:14px;">应付款金额：<span
                                style="font-size:22px; color:#ff4e00;">￥${shopCat.sum}</span></b>
                    </td>
                </tr>
                <tr height="70">
                    <td align="right"><a href="javascript:void(0);" onclick="clearCart()"><img
                            src="${ctx}/shop/images/btn_sure.gif"/></a>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <!--End 第二步：确认订单信息 End-->
    <!--Begin Footer Begin -->
    <%@include file="common/footer.jsp" %>
    <script src="${pageContext.request.contextPath}/shop/alert/js/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/shop/alert/js/jquery.hDialog.min.js"></script>
    <script type="text/javascript">
        $(".i_car").hide();
    </script>
    <script type="text/javascript">
        function clearCart() {
            var address;
            $(".address_box ul").each(function () {
                var $this = $(this);
                var flag = $this.attr("flag");
                if (flag == "true") {
                    address = $this.attr("value").toString().trim();
                    return;
                }
            });
            if (address == null || address.toString().trim() == '') {
                alert("请选择一个收货地址");
                return;
            }
            $.ajax({
                "url": path + "/ShoppingCat",
                "type": "POST",
                "data": {
                    "action": "toSettlement3",
                    "address": address,
                },
                "success": function (data) {
                    if (data.status == 1) {
                        $('#cart').html(data);
                    } else {
                        data = JSON.parse(data);
                        console.log(data)
                        $.tooltip(data.message);
                    }
                }
            });
        }
    </script>
    <script type="text/javascript">
        // address_add
        $(".address_box ul").click(function () {
            $(".address_box ul").css("border", "");
            $(".address_box ul").attr("flag", "false");
            $(this).css("border", "3px solid red");
            $(this).attr("flag", "true");
        });
    </script>
    <!--End Footer End -->
</div>
<!--[if IE 6]>

<![endif]-->
</html>
