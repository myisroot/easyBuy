<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    request.setAttribute("ctx", request.getContextPath());
%>
<div class="i_bg">
    <div class="content mar_20">
        <img src="${ctx}/shop/images/img1.jpg"/>
    </div>

    <!--Begin 第一步：查看购物车 Begin -->
    <div class="content mar_20">
        <table border="0" class="car_tab" style="width:1200px; margin-bottom:50px;" cellspacing="0" cellpadding="0">
            <tr>
                <td class="car_th" width="490">商品名称</td>
                <td class="car_th" width="140">单价(￥)</td>
                <td class="car_th" width="150">购买数量</td>
                <td class="car_th" width="130">小计</td>
                <td class="car_th" width="150">操作</td>
            </tr>
            <c:forEach items="${sessionScope.shopCat.shopItems}" var="item">
                <tr>
                    <td>
                        <div class="c_s_img"><img src="${ctx}/shop/images/${item.easyProduct.p_filename}" width="73"
                                                  height="73"/></div>
                            ${item.easyProduct.p_name}
                    </td>
                    <td align="center" style="color:#ff4e00;">￥${item.easyProduct.p_price}</td>
                    <td align="center">
                        <div class="c_num">
                            <input type="button" value="" onclick="jianUpdate1(jq(this),'${item.easyProduct.p_id}');"
                                   class="car_btn_1"/>
                            <input type="text" value="${item.quantity}" name="" class="car_ipt"/>
                            <input type="button" value=""
                                   onclick="addUpdate1(jq(this),'${item.easyProduct.p_id}','${item.easyProduct.p_stock}');"
                                   class="car_btn_2"/>
                        </div>
                    </td>
                    <td align="center" style="color:#ff4e00;">￥${item.cost}</td>
                    <td align="center"><a href="javascript:void(0)"
                                          onclick="ShowDiv('MyDiv','fade','${item.easyProduct.p_id}')">删除</a>&nbsp;
                        &nbsp;
                    </td>
                </tr>
            </c:forEach>
            <tr height="70">
                <td colspan="6" style="font-family:'Microsoft YaHei'; border-bottom:0;">
                    <%--<label class="r_rad"><input type="checkbox" name="clear" checked="checked"/></label><label--%>
                    <%--class="r_txt">清空购物车</label>--%>
                    <span class="fr">商品总价：<b style="font-size:22px; color:#ff4e00;">￥${shopCat.sum}</b></span>
                </td>
            </tr>
            <tr valign="top" height="150">
                <td colspan="6" align="right">
                    <a href="${ctx}/Home?action=index"><img src="${ctx}/shop/images/buy1.gif"/></a>&nbsp; &nbsp; <a
                        href="javascript:void(0)" onclick="toCat()"><img
                        src="${ctx}/shop/images/buy2.gif"/></a>
                </td>
            </tr>
        </table>

    </div>
    <!--End 第一步：查看购物车 End-->


    <!--Begin 弹出层-删除商品 Begin-->
    <div id="fade" class="black_overlay"></div>
    <div id="MyDiv" class="white_content">
        <div class="white_d">
            <div class="notice_t">
                <span class="fr" style="margin-top:10px; cursor:pointer;" onclick="CloseDiv('MyDiv','fade')"><img
                        src="${ctx}/shop/images/close.gif"/></span>
            </div>
            <div class="notice_c">
                <table border="0" align="center" style="font-size:16px;" cellspacing="0" cellpadding="0">
                    <tr valign="top">
                        <td>您确定要把该商品移除购物车吗？</td>
                    </tr>
                    <tr height="50" valign="bottom">
                        <td><a href="javascript:void(0)" class="b_sure" onclick="DelItem()">确定</a>
                            <a href="javascript:void(0)" class="b_buy" onclick="CloseDiv('MyDiv','fade')">取消</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <!--End 弹出层-删除商品 End-->
    <!--Begin Footer Begin -->
    <%@include file="common/footer.jsp" %>
    <script type="text/javascript">
        $("#left_Nav").hide();
        $(".i_car").hide();

        function toCat() {
            <c:if test="${empty sessionScope.shopCat.shopItems}">
            alert("购物车还没有商品继续购物吧")
            return;
            </c:if>
            $('#cart').load(path + '/ShoppingCat?action=toSettlement2');
        }
    </script>
    <script type="text/javascript" src="${ctx}/shop/js/shade.js"></script>
    <script type="text/javascript" src="${ctx}/shop/js/num.js">
        var jq = jQuery.noConflict();
    </script>
    <script type="text/javascript" src="${ctx}/shop/js/n_nav.js"></script>
    <!--End Footer End -->
</div>
