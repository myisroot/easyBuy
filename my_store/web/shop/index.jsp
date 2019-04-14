<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="">
        var ctx = '${ctx}';
    </script>
    <!--[if IE 6]>

    <script type="text/javascript" src=ctx+'/shop/js/iepng.js'></script>
    <script type="text/javascript">
        EvPNG.fix('div, ul, img, li, input, a');
    </script>
    <![endif]-->
    <title>易卖网</title>
</head>
<body>
<!--Begin Header Begin-->
<%@include file="common/header.jsp" %>

<%@include file="common/search.jsp" %>
<!--End Header End-->
<!--Begin Menu Begin-->
<%@include file="common/menu.jsp" %>
<%
    Object object = request.getParameter("action");
    if (object == null) {
        response.sendRedirect(request.getContextPath() + "/Home?action=index");
    }
%>
<!--End Menu End-->
<div class="i_bg bg_color">
    <div class="i_ban_bg">
        <!--Begin Banner Begin-->
        <div class="banner">
            <div class="top_slide_wrap">
                <ul class="slide_box bxslider">
                    <li><img src="${ctx}/shop/images/ban1.jpg" width="740" height="401"/></li>
                    <li><img src="${ctx}/shop/images/ban1.jpg" width="740" height="401"/></li>
                    <li><img src="${ctx}/shop/images/ban1.jpg" width="740" height="401"/></li>
                    <li><img src="${ctx}/shop/images/ban1.jpg" width="740" height="401"/></li>
                </ul>
                <div class="op_btns clearfix">
                    <a href="#" class="op_btn op_prev"><span></span></a>
                    <a href="#" class="op_btn op_next"><span></span></a>
                </div>
            </div>
        </div>
        <!--End Banner End-->
        <div class="inews">
            <div class="news_t">
                <span class="fr"><a href="#">更多 ></a></span>新闻资讯
            </div>
            <ul>
                <c:forEach items="${allNews}" var="News" begin="0" end="4">
                    <li><span>[ ${News.n_title} ]</span><a href="#">${News.n_content}</a></li>
                </c:forEach>
            </ul>
            <div class="charge_t">
                话费充值
                <div class="ch_t_icon"></div>
            </div>
            <form>
                <table border="0" style="width:205px; margin-top:10px;" cellspacing="0" cellpadding="0">
                    <tr height="35">
                        <td width="33">号码</td>
                        <td><input type="text" value="" class="c_ipt"/></td>
                    </tr>
                    <tr height="35">
                        <td>面值</td>
                        <td>
                            <select class="jj" name="city">
                                <option value="0" selected="selected">100元</option>
                                <option value="1">50元</option>
                                <option value="2">30元</option>
                                <option value="3">20元</option>
                                <option value="4">10元</option>
                            </select>
                            <span style="color:#ff4e00; font-size:14px;">￥99.5</span>
                        </td>
                    </tr>
                    <tr height="35">
                        <td colspan="2"><input type="button" value="立即充值" class="c_btn"/></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <div class="content mar_20">
        <img src="${ctx}/shop/images/mban_1.jpg" width="1200" height="110"/>
    </div>
    <c:forEach items="${allVO}" var="pc1" varStatus="count" begin="0" end="3">
        <div class="i_t mar_10">
            <span class="floor_num">${count.index+1}F</span>
            <span class="fl">${pc1.easy_product_category.c_name}</span>
            <span class="i_mores fr">
                <c:forEach items="${pc1.list}" var="pc2" begin="0" end="4">
                    <a href="${ctx}/product?action=getProduct&c_id=${pc2.easy_product_category.c_id}">${pc2.easy_product_category.c_name}</a>&nbsp; &nbsp; &nbsp; &nbsp;
                </c:forEach>
            </span>
        </div>
        <div class="content">
            <div class="food_left">
                <div class="food_ban">
                    <div id="imgPlay2">
                        <ul class="imgs" id="actor${count.index}">
                            <li><a href="#"><img src="${ctx}/shop/images/food_r.jpg" width="211" height="286"/></a></li>
                            <li><a href="#"><img src="${ctx}/shop/images/food_r.jpg" width="211" height="286"/></a></li>
                            <li><a href="#"><img src="${ctx}/shop/images/food_r.jpg" width="211" height="286"/></a></li>
                        </ul>
                        <div class="prev_f">上一张</div>
                        <div class="next_f">下一张</div>
                    </div>
                </div>
                <div class="fresh_txt">
                    <div class="fresh_txt_c">
                        <c:forEach items="${pc1.list}" var="pc2" begin="0" end="12">
                            <a href="${ctx}/product?action=getProduct&c_id=${pc2.easy_product_category.c_id}">${pc2.easy_product_category.c_name}</a>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="fresh_mid">
                <ul>
                        <%--<h1>${products0}</h1>--%>
                    <c:forEach items="${pc1.products}" var="product" begin="0" end="5">
                        <li>
                            <div class="name"><a
                                    href="${ctx}/product?action=productInfo&p_id=${product.p_id}">${product.p_name}</a>
                            </div>
                            <div class="price">
                                <font>￥<span>${product.p_price}</span></font>
                            </div>
                            <div class="img"><a href="${ctx}/product?action=productInfo&p_id=${product.p_id}"><img
                                    src="${ctx}/shop/images/${product.p_filename}" width="185"
                                    height="155"/></a>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="fresh_right">
                <ul>
                    <li><a href="#"><img src="${ctx}/shop/images/food_b1.jpg" width="260" height="220"/></a></li>
                    <li><a href="#"><img src="${ctx}/shop/images/food_b2.jpg" width="260" height="220"/></a></li>
                </ul>
            </div>
        </div>
    </c:forEach>
    <%@include file="common/footer.jsp" %>
    <!--End Footer End -->
</div>
</body>
<script type="text/javascript">
    //var jq = jQuery.noConflict();
    (function () {
        $(".bxslider").bxSlider({
            auto: true,
            prevSelector: jq(".top_slide_wrap .op_prev")[0], nextSelector: jq(".top_slide_wrap .op_next")[0]
        });
    })();
</script>

<!--[if IE 6]>
<![endif]-->
</html>


