<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <!--[if IE 6]>
    <![endif]-->
    <title>商品详情</title>
</head>
<body>
<!--Begin Header Begin-->
<%@include file="common/header.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx}/shop/css/ShopShow.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/shop/css/MagicZoom.css"/>
<div id="searchBar">
    <%@include file="common/search.jsp" %>
</div>
<!--End Header End-->
<!--Begin Menu Begin-->
<%@include file="common/menu.jsp" %>
<div class="i_bg">
    <div class="postion">
        <span class="fl">全部 > 美妆个护 > 香水 > 迪奥 > 迪奥真我香水</span>
    </div>
    <div class="content">

        <div id="tsShopContainer">
            <div id="tsImgS"><a href="${ctx}/shop/images/${easyProduct.p_filename}" title="${ctx}/shop/images"
                                class="MagicZoom"
                                id="MagicZoom"><img
                    src="${ctx}/shop/images/${easyProduct.p_filename}" width="390" height="390"/></a></div>
            <div id="tsPicContainer">
                <div id="tsImgSArrL" onclick="tsScrollArrLeft()"></div>
                <div id="tsImgSCon">
                    <ul>
                        <li onclick="showPic(0)" rel="MagicZoom" class="tsSelectImg"><img
                                src="${ctx}/shop/images/${easyProduct.p_filename}"
                                tsImgS="${ctx}/shop/images/${easyProduct.p_filename}"
                                width="79" height="79"/></li>
                    </ul>
                </div>
                <div id="tsImgSArrR" onclick="tsScrollArrRight()"></div>
            </div>
        </div>

        <div class="pro_des">
            <div class="des_name">
                <p>${easyProduct.p_name}</p>
            </div>
            <div class="des_price">
                本店价格：<b>￥${easyProduct.p_price}</b><br/>
                库存：<span><strong>${easyProduct.p_stock}</strong></span>
            </div>
            <div class="des_choice">
                <span class="fl">型号选择：</span>
                <ul>
                    <li class="checked">30ml
                        <div class="ch_img"></div>
                    </li>
                    <li>50ml
                        <div class="ch_img"></div>
                    </li>
                    <li>100ml
                        <div class="ch_img"></div>
                    </li>
                </ul>
            </div>
            <div class="des_choice">
                <span class="fl">颜色选择：</span>
                <ul>
                    <li>红色
                        <div class="ch_img"></div>
                    </li>
                    <li class="checked">白色
                        <div class="ch_img"></div>
                    </li>
                    <li>黑色
                        <div class="ch_img"></div>
                    </li>
                </ul>
            </div>
            <div class="des_share">
                <div class="d_sh">
                    分享
                    <div class="d_sh_bg">
                        <a href="#"><img src="${ctx}/shop/images/sh_1.gif"/></a>
                        <a href="#"><img src="${ctx}/shop/images/sh_2.gif"/></a>
                        <a href="#"><img src="${ctx}/shop/images/sh_3.gif"/></a>
                        <a href="#"><img src="${ctx}/shop/images/sh_4.gif"/></a>
                        <a href="#"><img src="${ctx}/shop/images/sh_5.gif"/></a>
                    </div>
                </div>
                <div class="d_care"><a onclick="ShowDiv('MyDiv','fade')">关注商品</a></div>
            </div>
            <div class="des_join">
                <div class="j_nums">
                    <input type="text" value="1" name="" class="n_ipt" id="price"/>
                    <input type="button" value="" onclick="addUpdate(jq(this));" class="n_btn_1"/>
                    <input type="button" value="" onclick="jianUpdate(jq(this));" class="n_btn_2"/>
                </div>
                <span class="fl"><a onclick="addItem('${easyProduct.p_id}')"><img
                        src="${ctx}/shop/images/j_car.png"/></a></span>
            </div>
        </div>

        <div class="s_brand">
            <div class="s_brand_img"><img src="${ctx}/shop/images/sbrand.jpg" width="188" height="132"/></div>
            <div class="s_brand_c"><a href="#">进入品牌专区</a></div>
        </div>
    </div>

    <!--Begin 弹出层-收藏成功 Begin-->
    <div id="fade" class="black_overlay"></div>
    <div id="MyDiv" class="white_content">
        <div class="white_d">
            <div class="notice_t">
                <span class="fr" style="margin-top:10px; cursor:pointer;" onclick="CloseDiv('MyDiv','fade')"><img
                        src="${ctx}/shop/images/close.gif"/></span>
            </div>
            <div class="notice_c">

                <table border="0" align="center" style="margin-top:5px;" cellspacing="0" cellpadding="0">
                    <tr valign="top">
                        <td width="40"><img src="${ctx}/shop/images/suc.png"/></td>
                        <td>
                            <span style="color:#3e3e3e; font-size:18px; font-weight:bold;">您已成功收藏该商品</span><br/>
                            <a href="#">查看我的关注 >></a>
                        </td>
                    </tr>
                    <tr height="50" valign="bottom">
                        <td>&nbsp;</td>
                        <td><a href="#" class="b_sure">确定</a></td>
                    </tr>
                </table>

            </div>
        </div>
    </div>
    <!--End 弹出层-收藏成功 End-->


    <!--Begin 弹出层-加入购物车 Begin-->
    <div id="fade1" class="black_overlay"></div>
    <div id="MyDiv1" class="white_content">
        <div class="white_d">
            <div class="notice_t">
                <span class="fr" style="margin-top:10px; cursor:pointer;" onclick="CloseDiv_1('MyDiv1','fade1')"><img
                        src="${ctx}/shop/images/close.gif"/></span>
            </div>
            <div class="notice_c">
                <table border="0" align="center" style="margin-top:5px;" cellspacing="0" cellpadding="0">
                    <tr valign="top">
                        <td width="40"><img src="${ctx}/shop/images/suc.png"/></td>
                        <td>
                            <span style="color:#3e3e3e; font-size:18px; font-weight:bold;">宝贝已成功添加到购物车</span><br/>
                            购物车共有<span id="cart_len"></span>件宝贝 &nbsp; &nbsp; 合计：<span id="cart_sum"></span></span>元
                        </td>
                    </tr>
                    <tr height="50" valign="bottom">
                        <td>&nbsp;</td>
                        <td><a href="${ctx}/ShoppingCat?action=toSettlement" class="b_sure">去购物车结算</a><a
                                href="javascript:void(0);" class="b_buy"
                                onclick="CloseDiv_1('MyDiv1','fade1')">继续购物</a>
                        </td>
                    </tr>
                </table>

            </div>
        </div>
    </div>
    <!--End 弹出层-加入购物车 End-->
    <!--Begin Footer Begin -->
    <%@include file="common/footer.jsp" %>
    <script type="text/javascript" src="${ctx}/shop/js/MagicZoom.js"></script>
    <script type="text/javascript">$("#left_Nav").hide();</script>
    <script type="text/javascript" src="${ctx}/shop/js/num.js">
        var jq = jQuery.noConflict();
    </script>
    <script type="text/javascript" src="${ctx}/shop/js/p_tab.js"></script>
    <script type="text/javascript" src="${ctx}/shop/js/shade.js"></script>
    <script type="text/javascript">
        function addItem(p_id) {
            $.ajax({
                "url": "${ctx}/ShoppingCat",
                "type": "POST",
                "data": {
                    "action": "add",
                    "p_id": p_id,
                    "number": $("#price").val(),
                },
                "dataType": "JSON",
                "success": function (data) {
                    if (data.status == '1') {
                        $('#cart_len').text(data.data.shopItems.length);
                        $('#cart_sum').text(data.data.sum);
                        ShowDiv_1('MyDiv1', 'fade1');
                        $('#searchBar').load('${ctx}/ShoppingCat?action=reCart');
                    } else {
                        alert(data.message);
                    }
                }
            });
        }
    </script>
    <script type="text/javascript" src="${ctx}/shop/js/n_nav.js"></script>
    <script src="${ctx}/shop/js/ShopShow.js"></script>
    <!--End Footer End -->
</div>

</body>


<!--[if IE 6]>
<![endif]-->
</html>
