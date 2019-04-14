<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="menu_bg">
    <div class="menu">
        <!--Begin 商品分类详情 Begin-->
        <div class="nav">
            <div class="nav_t">全部商品分类</div>
            <div class="leftNav" id="left_Nav">
                <ul>
                    <c:forEach items="${allVO}" var="pc1">
                        <li>
                            <div class="fj">
                                <span class="n_img"><span></span><img src="${ctx}/shop/images/nav1.png"/></span>
                                    <%--获取一级分类的name--%>
                                <span class="fl"><a
                                        href="${ctx}/product?action=getProduct&c_id=${pc1.easy_product_category.c_id}">${pc1.easy_product_category.c_name}</a></span>
                            </div>
                            <div class="zj">
                                <div class="zj_l">
                                        <%--获取一级分类里面的list--%>
                                    <c:forEach items="${pc1.list}" var="pc2">
                                        <div class="zj_l_c">
                                            <h2>
                                                <a href="${ctx}/product?action=getProduct&c_id=${pc2.easy_product_category.c_id}">${pc2.easy_product_category.c_name}</a>
                                            </h2>
                                                <%--获取二级分类里面的list--%>
                                            <c:forEach items="${pc2.list}" var="pc3">
                                                <a href="${ctx}/product?action=getProduct&c_id=${pc3.easy_product_category.c_id}">${pc3.easy_product_category.c_name}</a>
                                            </c:forEach>
                                        </div>
                                    </c:forEach>
                                </div>
                                <div class="zj_r">
                                    <a href="#"><img src="${ctx}/shop/images/n_img1.jpg" width="236" height="200"/></a>
                                    <a href="#"><img src="${ctx}/shop/images/n_img2.jpg" width="236" height="200"/></a>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <!--End 商品分类详情 End-->
        <ul class="menu_r">
            <li><a href="${ctx}/Home?action=index">首页</a></li>
            <c:forEach items="${allProduct}" var="Product">
                <li>
                    <a href="${ctx}/product?action=getProduct&c_id=${Product.c_id}&c_name=${search}">${Product.c_name}</a>
                </li>
            </c:forEach>
        </ul>
        <div class="m_ad">中秋送好礼！</div>
    </div>
</div>
</body>
</html>
