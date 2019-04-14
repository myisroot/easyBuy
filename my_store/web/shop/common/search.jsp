<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="top">
    <%
        String path = request.getContextPath();
        request.setAttribute("path", path);
    %>
    <div class="logo"><a href="${path}/Home?action=index"><img
            src="${path}/shop/images/logo.png"/></a></div>
    <div class="search">
        <%--<form action="" method="get">--%>
        <input type="text" value="${search}" class="s_ipt" id="search_val"/>
        <input type="submit" value="搜索" class="s_btn" onclick="search()"/>
        <%--</form>--%>
        <span class="fl"><a href="#">咖啡</a><a href="#">iphone 6S</a><a href="#">新鲜美食</a><a href="#">蛋糕</a><a
                href="#">日用品</a><a
                href="#">连衣裙</a></span>
    </div>
    <div class="i_car">
        <div class="car_t">购物车 [
            <c:if test="${sessionScope.shopCat.shopItems!=null&& sessionScope.shopCat.shopItems.size()>0&&user!=null}">
                <span>${shopCat.shopItems.size()}</span>
            </c:if>
            <c:if test="${sessionScope.shopCat.shopItems==null||sessionScope.shopCat.shopItems.size()==0||user==null}">
                <span>0</span>
            </c:if>
            ]
        </div>
        <div class="car_bg">
            <!--Begin 购物车未登录 Begin-->
            <c:if test="${empty sessionScope.user}">
                <div class="un_login">还未登录！<a href="${path}/shop/Login.jsp" style="color:#ff4e00;">马上登录</a> 查看购物车！</div>
            </c:if>
            <!--End 购物车未登录 End-->
            <!--Begin 购物车已登录 Begin-->
            <ul class="cars">
                <c:if test="${!empty sessionScope.shopCat.shopItems&&user!=null}">
                    <c:forEach items="${sessionScope.shopCat.shopItems}" var="item">
                        <li>
                            <div class="img"><a href="#"><img src="${path}/shop/images/car1.jpg" width="58"
                                                              height="58"/></a>
                            </div>
                            <div class="name"><a href="#">${item.easyProduct.p_name}</a></div>
                            <div class="price"><font color="#ff4e00">￥${item.easyProduct.p_price}</font>
                                X ${item.quantity}
                            </div>
                        </li>
                    </c:forEach>
                </c:if>
                <c:if test="${empty sessionScope.shopCat.shopItems}">
                    <div style="text-align: center;"><strong>购物车还没有商品</strong></div>
                </c:if>
            </ul>
            <div class="price_sum">共计&nbsp; <font color="#ff4e00">￥</font><span>
                <c:if test="${empty shopCat.sum}">0</c:if>
                <c:if test="${!empty shopCat.sum&&user!=null}"> ${shopCat.sum}</c:if>
            </span>
            </div>
            <c:if test="${!empty sessionScope.user}">
                <div class="price_a"><a href="${path}/ShoppingCat?action=toSettlement">去购物车结算</a></div>
            </c:if>
            <c:if test="${empty sessionScope.user}">
                <div class="price_a"><a href="${path}/shop/Login.jsp">马上登入</a></div>
            </c:if>
            <!--End 购物车已登录 End-->
        </div>
    </div>
</div>
<script>
    function search() {
        var text = $("#search_val").val().trim();
        if (text != '' && text != null) {
            $(window).attr('location', '${pageContext.request.contextPath}/product?action=getProduct&c_name=' + text);
        }
    }
</script>

