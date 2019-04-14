<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <!--[if IE 6]>

    <![endif]-->
    <title>商品分类</title>
</head>
<style>
    #loginShow {
        width: 300px;
        height: 200px;
        background-color: green;
    }
</style>
<body>
<!--Begin Header Begin-->
<%@include file="common/header.jsp" %>
<div id="searchBar">
    <%@include file="common/search.jsp" %>
</div>

<!--End Header End-->
<!--Begin Menu Begin-->
<%@include file="common/menu.jsp" %>
<!--End Menu End-->
<div class="i_bg">
    <div class="postion">
        <span class="fl">全部 > 美妆个护 > 香水 > 香奈儿</span>
    </div>

    <div class="content mar_20">
        <div class="l_history">
            <div class="his_t">
                <span class="fl">浏览历史</span>
                <span class="fr"><a href="#">清空</a></span>
            </div>
            <ul>
                <li>
                    <div class="img"><a href="#"><img src="${ctx}/shop/images/his_1.jpg" width="185" height="162"/></a>
                    </div>
                    <div class="name"><a href="#">Dior/迪奥香水2件套装</a></div>
                    <div class="price">
                        <font>￥<span>368.00</span></font> &nbsp; 18R
                    </div>
                </li>
                <li>
                    <div class="img"><a href="#"><img src="${ctx}/shop/images/his_2.jpg" width="185" height="162"/></a>
                    </div>
                    <div class="name"><a href="#">Dior/迪奥香水2件套装</a></div>
                    <div class="price">
                        <font>￥<span>768.00</span></font> &nbsp; 18R
                    </div>
                </li>
            </ul>
        </div>
        <div class="l_list">
            <table border="0" style="width:100%; margin-bottom:30px; border:1px solid #eaeaea;" cellspacing="0"
                   cellpadding="0">
                <tr valign="top">
                    <td width="248">
                        <div class="brand_img"><img src="${ctx}/shop/images/brand5.jpg" width="226" height="108"/></div>
                    </td>
                    <td class="td_b" style="padding:15px 40px;">
                        所有分类<br/>
                        <a href="#" class="now">香水（10）</a><a href="#">彩妆套装（2）</a><a href="#">洁面（1）</a><a
                            href="#">精华（1）</a><a href="#">化妆水（2）</a><a href="#">嫩肤（3）</a>
                    </td>
                </tr>
            </table>
            <div class="list_t">
            	<span class="fl list_or">
                	<a href="#" class="now">默认</a>
                    <a href="#">
                    	<span class="fl">销量</span>
                        <span class="i_up">销量从低到高显示</span>
                        <span class="i_down">销量从高到低显示</span>
                    </a>
                    <a href="#">
                    	<span class="fl">价格</span>
                        <span class="i_up">价格从低到高显示</span>
                        <span class="i_down">价格从高到低显示</span>
                    </a>
                    <a href="#">新品</a>
                </span>
                <span class="fr">共发现${pageBean.allCounts}件</span>
            </div>
            <div class="list_c">
                <ul class="cate_list">
                    <c:if test="${empty allRroDuct}">
                        <h3>未查询到商品</h3>
                    </c:if>
                    <c:forEach items="${allRroDuct}" var="RroDuct">
                        <li>
                            <div class="img"><a href="${ctx}/product?action=productInfo&p_id=${RroDuct.p_id}"><img
                                    src="${ctx}/shop/images/${RroDuct.p_filename}" width="210"
                                    height="185"/></a>
                            </div>
                            <div class="price">
                                <font>￥<span>${RroDuct.p_price}</span></font> &nbsp; 26R
                            </div>
                            <div class="name"><a
                                    href="${ctx}/product?action=productInfo&p_id=${RroDuct.p_id}">${RroDuct.p_name}</a>
                            </div>
                            <div class="carbg">
                                <a href="#" class="ss">收藏</a>
                                <a href="javascript:void(0);" class="j_car"
                                   id="add" onclick="addItem('${RroDuct.p_id}',1)">加入购物车</a>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    <div class="log_box">
        <form action="${ctx}/login" method="post">
            <input type="hidden" name="action" value="login"/>
            <table border="0" style="width:370px; font-size:14px; margin-top:30px;" cellspacing="0" cellpadding="0">
                <tr height="70">
                    <td>用户名</td>
                    <td><input type="text" value="admin" class="l_user" name="u_loginname" id="username"/></td>
                </tr>
                <tr height="15">
                    <td></td>
                    <td id="name_info" style="color: red"></td>
                </tr>
                <tr height="70">
                    <td>密&nbsp; &nbsp; 码</td>
                    <td><input type="password" value="1234" class="l_pwd" name="u_pwd" id="u_pwd"/></td>
                </tr>
                <tr height="10">
                    <td></td>
                    <td id="err_info" style="color: red"></td>
                </tr>
                <tr height="60">
                    <td>&nbsp;</td>
                    <td><input type="button" value="登录" class="log_btn" onclick="userLogin()"/></td>
                </tr>
            </table>
        </form>
    </div>
    <div id="page" class="page_div">1</div>
    <!--Begin Footer Begin -->
    <%@include file="common/footer.jsp" %>
    <script type="text/javascript" src="${ctx}/shop/js/n_nav.js"></script>
    <script type="text/javascript" src="${ctx}/shop/js/milk_ban.js"></script>
    <script type="text/javascript" src="${ctx}/shop/js/paper_ban.js"></script>
    <script type="text/javascript" src="${ctx}/shop/js/baby_ban.js"></script>
    <script type="text/javascript" src="${ctx}/shop/js/jquery-1.11.1.min_044d0927.js"></script>
    <script type="text/javascript" src="${ctx}/shop/js/paging.js"></script>
    <script type="text/javascript" src="${ctx}/shop/js/iepng.js"></script>
    <script src="${pageContext.request.contextPath}/shop/alert/js/jquery.hDialog.min.js"></script>
    <script type="text/javascript">
        $("#left_Nav").hide();
        $(document).click(function () {
            $(".log_box").fadeOut();
        });
        $(".log_box").click(function () {
            return false;
        });


        function userLogin() {
            var $name = $("#username").val().trim();
            var $pwd = $("#u_pwd").val().trim();
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
                        $(".log_box").fadeOut();
                        window.location.reload();
                    }
                });
        }

        function addItem(p_id, number) {
            $.ajax({
                "url": "${ctx}/ShoppingCat",
                "type": "POST",
                "data": {
                    "action": "add",
                    "p_id": p_id,
                    "number": number
                },
                "dataType": "JSON",
                "success": function (data) {
                    if (data.status == '1') {
                        $.tooltip('成功加入购物车', 1200, true, function () {
                            $('#searchBar').load('${ctx}/ShoppingCat?action=reCart');
                        });
                    } else {
                        $.tooltip(data.message);
                        if (data.message === '请先登入') {
                            $(".log_box").fadeIn();
                        }
                    }
                }
            });
        }

    </script>
    <script type="text/javascript">
        $(function () {
            $("#page").paging({
                pageNo: ${pageBean.currPaNo},
                totalPage: ${pageBean.pageCounts},
                totalSize: ${pageBean.allCounts},
                callback: function (num) {
                    var text = $("#search_val").val();
                    $(window).attr('location', '${ctx}/product?action=getProduct&c_name=' + text + '&currPaNo=' + num + '&c_id=${c_id}');
                }
            });
        });
    </script>
    <!--End Footer End -->
</div>

</body>
</html>
