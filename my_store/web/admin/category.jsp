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
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">分类管理</strong>
            <small></small>
        </div>
    </div>
    <hr>
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6">
            <div class="am-btn-toolbar">
                <div class="am-btn-group am-btn-group-xs">
                    <button id="add" class="am-btn am-btn-default">
                        <span class="am-icon-plus"></span> 添加分类
                    </button>
                </div>
            </div>
        </div>
        <div class="am-u-sm-12 am-u-md-3">
            <div class="am-input-group am-input-group-sm">
                <input type="text" class="am-form-field" id="input_search" value="${cName}">
                <span class="am-input-group-btn">
                    <button class="am-btn am-btn-default" type="button" id="input_search_btn">搜索</button>
                </span>
            </div>
        </div>
    </div>
</div>

<div class="goods_list" id="account_List">
    <ul class="title_ul">
        <li>序号</li>
        <li>分类名称</li>
        <li>分类级别</li>
        <li>父级分类</li>
        <li>修改分类</li>
        <li>删除分类</li>
    </ul>

    <c:forEach items="${allCategory}" var="Category" varStatus="i">
        <ul class="list_goods_ul">
            <li>${i.index+1}</li>
            <li>${Category.c_name}</li>
            <li>${Category.c_type}</li>
            <li>${Category.c_iconclass}</li>
            <li><a href="javascript:void (0);"
                   onclick="getUpdateType('${Category.c_id}','${Category.c_type}','${Category.c_name}','${Category.c_parentid}')">
                <img class="img_icon" src="${pageContext.request.contextPath}/admin/images/edit_icon.png" alt=""></a>
            </li>
            <li><a href="javascript:void (0);" onclick="delCategory('${Category.c_id}')">
                <img class="img_icon" src="${pageContext.request.contextPath}/admin/images/delete_icon.png" alt=""></a>
            </li>
        </ul>
    </c:forEach>

    <c:if test="${empty allCategory}">
        <ul class="list_goods_ul">
            <li>没有查询到记录</li>
        </ul>
    </c:if>
    <div id="page" class="page_div">1</div>
</div>
<div id="modal_view">
</div>
<%--添加分类 begin--%>
<div id="modal_content">
    <div id="close"><img src="${pageContext.request.contextPath}/admin/images/delete_icon.png" alt=""></div>
    <div class="edit_content">
        <div class="item1">
            <div>
                <span><strong>添加分类:</strong></span>
            </div>
        </div>
        <div class="item1">
            <div>
                <span>分类级别：</span>
                <select class="category_select" onchange="showType(this);" id="ctype">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                </select>
            </div>
        </div>
        <div class="item1" id="oneType" style="display: none;">
            <div>
                <span>一级分类:&nbsp;&nbsp;</span>
                <select class="category_select" id="p_categorylevel1" onchange="getTwo(this)">
                </select>
            </div>
        </div>
        <div class="item1" id="twoType" style="display: none;">
            <div>
                <span>二级分类:&nbsp;&nbsp;</span>
                <select class="category_select" id="p_categorylevel2">
                </select>
            </div>
        </div>
        <div class="item1">
            <div>
                <span>分类名称：</span>
                <input type="text" class="am-form-field" placeholder="必填" name="typename">&nbsp;&nbsp;
            </div>
        </div>
        <div class="item1">
            <button class="am-btn am-btn-default" type="button" onclick="addType()">添加</button>
        </div>
    </div>
</div>
<%--添加分类 end--%>

<%--修改分类 begin--%>
<div id="modal">
    <div id="close1"><img src="${pageContext.request.contextPath}/admin/images/delete_icon.png" alt=""></div>
    <div class="edit_content">
        <div class="item1">
            <div>
                <span><strong>修改分类:</strong></span>
            </div>
        </div>
        <div class="item1">
            <div>
                <span>分类级别：</span>
                <select class="category_select" onchange="setShowType(this);" id="categoryType" disabled>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                </select>
            </div>
        </div>
        <div class="item1" id="setType1">
            <div>
                <span>一级分类:&nbsp;&nbsp;</span>
                <select class="category_select" id="categorylevel1" onchange="getUpdateTwo(this)">
                </select>
            </div>
        </div>
        <div class="item1" id="setType2">
            <div>
                <span>二级分类:&nbsp;&nbsp;</span>
                <select class="category_select" id="categorylevel2">
                </select>
            </div>
        </div>
        <div class="item1">
            <div>
                <span>分类名称：</span>
                <input type="text" class="am-form-field" placeholder="必填" name="tname">&nbsp;&nbsp;
            </div>
        </div>
        <div class="item1">
            <button class="am-btn am-btn-default" type="button" onclick="updateType()">修改</button>
        </div>
    </div>
</div>
<%--修改分类 end--%>

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
    var c_id;

    function closeShowInfo() {
        setTimeout(function () {
            $('#showInfo').fadeOut();
        }, 1000)
    }

    //发送修改请求
    function updateType() {
        var v = $("input[name='tname']").val().trim();
        if (v == null || v == "") {
            $('#showInfo').text("必须输入分类名");
            $('#showInfo').fadeIn();
            setTimeout(function () {
                $('#showInfo').fadeOut();
            }, 1000);
            return;
        }
        $.ajax({
            "url": "${pageContext.request.contextPath}/admin_product",
            "type": "POST",
            "data": {
                "action": "updateCategory",
                "cid": c_id,
                "pc1": $("#categorylevel1").select().val(),
                "pc2": $("#categorylevel2").select().val(),
                "type": $("#categoryType").select().val(),
                "name": v,
            },
            "dataType": "JSON",
            "success": function (data) {
                if (data.status == 1) {
                    $("#modal_view").fadeOut();
                    $("#modal").fadeOut();
                    $('#showInfo').text("修改成功!");
                    $('#showInfo').fadeIn();
                    close();
                } else {
                    $("#modal_view").fadeOut();
                    $("#modal").fadeOut();
                    $('#showInfo').text(data.message);
                    $('#showInfo').fadeIn();
                    closeShowInfo();
                }
            }
        });
    }

    //设置修改前的分类值
    function getUpdateType(cid, type, name, parentid) {
        $("#setType1").show();
        $("#setType2").show();
        c_id = cid;
        $("input[name='tname']").val(name);
        $("#categoryType option[value='" + type + "']").prop("selected", true);
        if (type == 1) {
            $("#setType1").hide();
            $("#setType2").hide();
        } else if (type == 2) {
            getType(0, $("#categorylevel1"));
            $("#categorylevel1 option[value='" + parentid + "']").prop("selected", true);
            $("#setType2").hide();
        } else {
            $("#setType1").hide();
            $.ajax({
                "url": "${pageContext.request.contextPath}/admin_product",
                "type": "GET",
                "async": false,
                "data": {
                    "action": "getCategoryById",
                    "cid": parentid,
                },
                "dataType": "JSON",
                "success": function (data) {
                    if (data.status == 1) {
                        getType(data.data.c_parentid, $("#categorylevel2"));
                        $("#categorylevel2 option[value='" + parentid + "']").prop("selected", true);
                    }
                }
            });
        }
        $("#modal_view").fadeIn();
        $("#modal").fadeIn();
    }

    //分类三级联动
    function showType(that) {
        $("#p_categorylevel1").parent().parent().hide();
        $("#p_categorylevel2").parent().parent().hide();
        var v = $(that).select().val();
        if (v == 2) {
            $("#p_categorylevel1").parent().parent().show();
            getType(0, $("#p_categorylevel1"));
        }
        if (v == 3) {
            $("#p_categorylevel1").parent().parent().show();
            $("#p_categorylevel2").parent().parent().show();
            getType(0, $("#p_categorylevel1"));
        }
    }

    function getUpdateTwo(that) {
        var v = $(that).select().val();
        getType(v, $("#categorylevel2"));
    }

    //二级改变时改变三级的分类
    function getTwo(that) {
        var v = $(that).select().val();
        getType(v, $("#p_categorylevel2"));
    }

    /**
     * 设置分类值
     * @param val
     * @param elem
     */
    function getType(val, elem) {
        $.ajax({
            "url": "${pageContext.request.contextPath}/admin_product",
            "type": "GET",
            "async": false,
            "data": {
                "action": "getCategoryByParentId",
                "parentid": val,
            },
            "dataType": "JSON",
            "success": function (data) {
                if (data.status == 1) {
                    var html = " ";
                    for (var i = 0; i < data.data.length; i++) {
                        var $option = "<option value='" + data.data[i].c_id + "'>" + data.data[i].c_name + "</option>";
                        html += $option;
                    }
                    elem.html(html);
                }
            }
        });
    }

    var cid;
    $(function () {
        //取消删除
        $("#exitShowBow").click(function () {
            $("#showBox").fadeOut();
        });

        //删除分类
        $("#delete").click(function () {
            $("#showBox").fadeOut();
            $.ajax({
                "url": "${pageContext.request.contextPath}/admin_product",
                "type": "POST",
                "data": {
                    "action": "delCategory",
                    "cid": cid,
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

        /**
         * 分页
         */
        $("#page").paging({
            pageNo: ${pageBean.currPaNo},
            totalPage: ${pageBean.pageCounts},
            totalSize: ${pageBean.allCounts},
            callback: function (num) {
                $(window).attr('location', '${pageContext.request.contextPath}/admin_product?action=allCategory&currPaNo=' + num + '&cName=${cName}');
            }
        });
    });

    /**
     * 添加分类
     */
    function addType() {
        var v = $("input[name='typename']").val().trim();
        if (v == null || v == "") {
            $('#showInfo').text("必须输入分类名");
            $('#showInfo').fadeIn();
            setTimeout(function () {
                $('#showInfo').fadeOut();
            }, 1000)
            return;
        } else {
            $.ajax({
                "url": "${pageContext.request.contextPath}/admin_product",
                "type": "POST",
                "data": {
                    "action": "addCategory",
                    "pc1": $("#p_categorylevel1").select().val(),
                    "pc2": $("#p_categorylevel2").select().val(),
                    "ctype": $("#ctype").select().val(),
                    "cname": v
                },
                "dataType": "JSON",
                "success": function (data) {
                    if (data.status == 1) {
                        $("#modal_view").fadeOut();
                        $("#modal_content").fadeOut();
                        $('#showInfo').text("增加成功!");
                        $('#showInfo').fadeIn();
                        close();
                    } else {
                        $("#modal_view").fadeOut();
                        $("#modal_content").fadeOut();
                        $('#showInfo').text(data.message);
                        $('#showInfo').fadeIn();
                        setTimeout(function () {
                            $('#showInfo').fadeOut();
                        }, 1000)
                    }
                }
            });
        }
    }

    /**
     * 提示添加成功并且刷新
     */
    function close() {
        setTimeout(function () {
            $('#showInfo').fadeOut();
            $(window).attr('location', '${pageContext.request.contextPath}/admin_product?action=allCategory&currPaNo=${curr}&cName=' + $("#input_search").val().trim());
        }, 1000)
    }

    /**
     * 点击删除弹出提示框
     * @param c_id
     */
    function delCategory(c_id) {
        cid = c_id;
        $("#showBox").fadeIn();
    }

    /**
     * 点击搜索
     */
    $("#input_search_btn").click(function () {
        var search = $("#input_search").val().trim();
        if (search != null) {
            $(window).attr('location', '${pageContext.request.contextPath}/admin_product?action=allCategory&currPaNo=${curr}&cName=' + search);
        }
    });

    /**
     *弹出添加分类界面
     * 关闭添加分类界面
     */
    $(function () {
        $('#add').click(function () {
            $("#modal_view").fadeIn();
            $("#modal_content").fadeIn();
        });

        $("#close").click(function () {
            $("#modal_view").fadeOut();
            $("#modal_content").fadeOut();
        });

        $("#close1").click(function () {
            $("#modal_view").fadeOut();
            $("#modal").fadeOut();
        });
    });
</script>
</body>
</html>