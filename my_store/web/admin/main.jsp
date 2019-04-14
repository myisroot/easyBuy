<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/amazeui.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/pageStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/honeySwitch.css">
    <c:if test="${empty admin_user}">
        <script>
            window.parent.location.reload();
        </script>
    </c:if>
</head>
<body style="background:#f3f3f3;">

<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">商品管理</strong>
            <small></small>
        </div>
    </div>
    <hr>
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6">
            <div class="am-btn-toolbar">
                <div class="am-btn-group am-btn-group-xs">
                    <button id="add" class="am-btn am-btn-default">
                        <span class="am-icon-plus"></span>新增
                    </button>
                </div>
            </div>
        </div>
        <div class="am-u-sm-12 am-u-md-3">

        </div>
        <div class="am-u-sm-12 am-u-md-3">
            <div class="am-input-group am-input-group-sm">
                <input type="text" class="am-form-field" id="input_search" value="${pName}">
                <span class="am-input-group-btn">
                    <button class="am-btn am-btn-default" type="button" id="input_search_btn">搜索</button>
                </span>
            </div>
        </div>
    </div>


</div>

<div class="goods_list">
    <ul class="title_ul">
        <li>序号</li>
        <li>商品图片</li>
        <li>商品名称</li>
        <li>商品价格</li>
        <li>商品库存</li>
        <li>是否下架</li>
        <li>编辑</li>
        <li>删除</li>
    </ul>
    <div id="info_box">
        <c:forEach items="${products}" var="product" varStatus="i">
            <ul class="list_goods_ul">
                <li>${i.index+1}</li>
                <li><img src="${pageContext.request.contextPath}/shop/images/${product.p_filename}"></li>
                <li>${product.p_name}</li>
                <li style="color: red;">￥${product.p_price}</li>
                <li>${product.p_stock}</li>
                <li><span
                        <c:if test="${product.p_isdelete==0}">class="switch-off"</c:if>
                        <c:if test="${product.p_isdelete==1}">class="switch-on"</c:if>
                        onclick="isDelete(this,'${product.p_id}')"></span></li>
                <li>
                    <a href="javascript:void (0)" onclick="updateProduct('${product.p_id}')"><img
                            class="img_icon" src="${pageContext.request.contextPath}/admin/images/edit_icon.png" alt="">
                    </a>
                </li>
                <li>
                    <a href="javascript:void (0)" onclick="delProduct('${product.p_id}')"><img
                            class="img_icon" src="${pageContext.request.contextPath}/admin/images/delete_icon.png"
                            alt="删除">
                    </a>
                </li>
            </ul>
        </c:forEach>
        <c:if test="${empty products}">
            <ul class="list_goods_ul">
                <li>没有查询到记录</li>
            </ul>
        </c:if>
    </div>
    <!--分页-->
    <div id="showInfo" class="modal_content">
    </div>

    <div id="showBox" class="showBox">
        <h5>是否确认删除?</h5>
        <button class="am-btn am-btn-default" type="button" id="delete">确认</button>
        <button class="am-btn am-btn-default" type="button" style="margin-left:20px;" id="exitShowBow">取消</button>
    </div>
    <div id="page" class="page_div">1</div>
</div>
<script src="${pageContext.request.contextPath}/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/paging.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/honeySwitch.js"></script>
<script>
    function updateProduct(pid) {
        if ('${admin_user.u_isupdate}'==='${NOUPDATE}') {
            $('#showInfo').text("无修改权限");
            $('#showInfo').fadeIn();
            closeShowInfo();
            return false;
        } else {
            $.ajax({
                "url": "${pageContext.request.contextPath}/admin_product",
                "type": "POST",
                "data": {
                    "action": "testUpdate",
                },
                "dataType": "JSON",
                "success": function (data) {
                    if (data.status == 1) {
                        $(window).attr('location', '${pageContext.request.contextPath}/admin_product?action=setProduct&pid=' + pid + '&currPaNo=${curr}');
                    } else {
                        $('#showInfo').text(data.message);
                        $('#showInfo').fadeIn();
                        closeShowInfo();
                    }
                }
            });
        }
    }

    <c:if test="${row>0}">
    $('#showInfo').text("添加成功");
    $('#showInfo').fadeIn();
    closeShowInfo();
    </c:if>

    <c:if test="${size>0}">
    $('#showInfo').text("修改成功");
    $('#showInfo').fadeIn();
    closeShowInfo();
    </c:if>
    //分页
    var pid;
    $(function () {
        $("#page").paging({
            pageNo: ${pageBean.currPaNo},
            totalPage: ${pageBean.pageCounts},
            totalSize: ${pageBean.allCounts},
            callback: function (num) {
                $(window).attr('location', '${pageContext.request.contextPath}/admin_product?action=allProduct&currPaNo='
                    + num + '&pName=' + $("#input_search").val().trim());
            }
        });


        $("#exitShowBow").click(function () {
            $("#showBox").fadeOut();
        });

        $("#delete").click(function () {
            $("#showBox").fadeOut();
            $.ajax({
                "url": "${pageContext.request.contextPath}/admin_product",
                "type": "POST",
                "data": {
                    "action": "delProduct",
                    "pid": pid,
                },
                "dataType": "JSON",
                "success": function (data) {
                    if (data.status == 1) {
                        $('#showInfo').text("删除成功!");
                        $('#showInfo').fadeIn();
                        close();
                    } else {
                        $('#showInfo').text(data.message);
                        $('#showInfo').fadeIn();
                        setTimeout(function () {
                            $('#showInfo').fadeOut();
                        }, 1000)
                    }
                }
            });
        });
        $("#input_search_btn").click(function () {
            var search = $("#input_search").val().trim();
            if ('${curr}' != 1 && search == "") {
                return false;
            }
            if (search != null) {
                $(window).attr('location', '${pageContext.request.contextPath}/admin_product?action=allProduct&currPaNo=1&pName=' + $("#input_search").val().trim());
            }
        });
        $("#add").click(function () {
            $.ajax({
                "url": "${pageContext.request.contextPath}/admin_product",
                "type": "POST",
                "data": {
                    "action": "testAdd",
                },
                "dataType": "JSON",
                "success": function (data) {
                    if (data.status == 1) {
                        $(window).attr('location', '${pageContext.request.contextPath}/admin_product?action=checkCategory&currPaNo=${curr}');
                    } else {
                        $('#showInfo').text(data.message);
                        $('#showInfo').fadeIn();
                        closeShowInfo();
                    }
                }
            });
        });


    });

    function close() {
        setTimeout(function () {
            $('#showInfo').fadeOut();
            $(window).attr('location', '${pageContext.request.contextPath}/admin_product?action=allProduct&currPaNo=${curr}&pName' + $("#input_search").val().trim());
        }, 1000)
    }

    function closeShowInfo() {
        setTimeout(function () {
            $('#showInfo').fadeOut();
        }, 1000)
    }

    function isDelete(that, pid) {
        var $class = $(that).attr("class").trim();
        if ($class == "switch-off") {
            update(pid, 1, "上架成功!");
        }
        if ($class == "switch-on") {
            update(pid, 0, "下架成功!");
        }
    }

    function update(pid, $class, message) {
        $.ajax({
            "url": "${pageContext.request.contextPath}/admin_product",
            "type": "POST",
            "data": {
                "action": "isDelete",
                "pid": pid,
                "class": $class,
            },
            "dataType": "JSON",
            "success": function (data) {
                if (data.status == 1) {
                    $('#showInfo').text(message);
                    $('#showInfo').fadeIn();
                    closeShowInfo();
                } else {
                    $('#showInfo').text(data.message);
                    $('#showInfo').fadeIn();
                    closeShowInfo();
                }
            }
        });
    }


    function delProduct(p_id) {
        pid = p_id;
        $("#showBox").fadeIn();
    }
</script>

</body>
</html>