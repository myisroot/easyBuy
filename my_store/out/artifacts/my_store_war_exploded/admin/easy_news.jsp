<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/amazeui.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/pageStyle.css">
    <c:if test="${empty admin_user}">
        <script>
            window.parent.location.reload();
        </script>
    </c:if>
</head>
<body>


<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">新闻资讯管理</strong>
            <small></small>
        </div>
    </div>
    <hr>
</div>

<div class="goods_list" id="account_List">
    <ul class="title_ul">
        <li>序号</li>
        <li>新闻内容</li>
        <li>标题</li>
        <li>发布时间</li>
        <li>修改</li>
        <li>删除</li>
    </ul>

    <c:forEach items="${news}" var="n" varStatus="i">
        <ul class="list_goods_ul">
            <li>${i.index+1}</li>
            <li>${n.n_content}</li>
            <li>${n.n_title}</li>
            <li>${n.n_creattime}</li>
            <li><a href="javascript:void (0);">
                <img class="img_icon" src="${pageContext.request.contextPath}/admin/images/edit_icon.png" alt=""></a>
            </li>
            <li><a href="javascript:void (0);" onclick="delCategory('${n.n_id}')">
                <img class="img_icon" src="${pageContext.request.contextPath}/admin/images/delete_icon.png" alt=""></a>
            </li>
        </ul>
    </c:forEach>
    <div id="page" class="page_div">1</div>
</div>
<div id="modal_view">
</div>
<div id="modal_content">
    <div id="close"><img src="${pageContext.request.contextPath}/admin/images/delete_icon.png" alt=""></div>
    <div class="edit_content">
        <div class="item1">
            <div>
                <span>添加分类：</span>
            </div>
        </div>
        <div class="item1">
            <div>
                <span>商品名称：</span>
                <input type="text" class="am-form-field">&nbsp;&nbsp;
            </div>
        </div>
        <div class="item1">
            <button class="am-btn am-btn-default" type="button">添加</button>
        </div>
    </div>
</div>
<div id="showInfo" class="modal_content">
</div>
<div id="showBox" class="showBox">
    <h5>是否确认删除?</h5>
    <button class="am-btn am-btn-default" type="button" id="delete">确认</button>
    <button class="am-btn am-btn-default" type="button" style="margin-left:20px;" id="exitShowBow">取消</button>
</div>
<script src="${pageContext.request.contextPath}/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/paging.js"></script>
<script>
    var nid;
    $(function () {
        $("#exitShowBow").click(function () {
            $("#showBox").fadeOut();
        });

        $("#delete").click(function () {
            $("#showBox").fadeOut();
            $.ajax({
                "url": "${pageContext.request.contextPath}/admin_news",
                "type": "POST",
                "data": {
                    "action": "delNews",
                    "nid": nid,
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

        $("#page").paging({
            pageNo: ${pageBean.currPaNo},
            totalPage: ${pageBean.pageCounts},
            totalSize: ${pageBean.allCounts},
            callback: function (num) {
                $(window).attr('location', '${pageContext.request.contextPath}/admin_news?action=getNews&currPaNo=' + num);
            }
        });


    });

    function close() {
        setTimeout(function () {
            $('#showInfo').fadeOut();
            $(window).attr('location', '${pageContext.request.contextPath}/admin_news?action=getNews&currPaNo=${curr}');
        }, 1000)
    }

    function delCategory(n_id) {
        nid = n_id;
        $("#showBox").fadeIn();
    }

    $(function () {
        $('#add').click(function () {
            $("#modal_view").fadeIn();
            $("#modal_content").fadeIn();
        });

        $("#close").click(function () {
            $("#modal_view").fadeOut();
            $("#modal_content").fadeOut();
        });
    });
</script>
</body>
</html>