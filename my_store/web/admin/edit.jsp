<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String ctx = request.getContextPath();
    pageContext.setAttribute("ctx", ctx);
%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${ctx }/admin/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="${ctx }/admin/css/amazeui.min.css"/>
</head>
<body>

<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">更新商品</strong>
            <small></small>
        </div>
    </div>
    <hr>
    <div class="edit_content">
        <form action="${ctx}/admin_product?action=updateProduct&&currPaNo=${param.currPaNo}"
              enctype="multipart/form-data"
              method="post"
              id="add_form" style="background: none; width: 700px;">
            <div class="item1">
                <%--enctype="multipart/form-data"--%>
                <div>
                    <input type="hidden" value="${product.p_id}" name="p_id">
                    <span>商品名称:</span>
                    <input type="text" class="am-form-field" name="p_name" required id="name" value="${product.p_name}">&nbsp;&nbsp;
                </div>
                <div>
                    <span>商品价格:</span>
                    <input type="text" class="am-form-field" name="p_price" required id="price"
                           value="${product.p_price}"
                           onkeyup="this.value=this.value.replace(/[^\d]/g,'');">
                </div>

            </div>

            <div class="item1">
                <div>
                    <span>商品库存:&nbsp;&nbsp;</span>
                    <span><button class="am-btn am-btn-default" type="button"
                                  style="margin-right: -10px;height: 31px;margin-top: -5px;line-height: 15px;border-radius: 3px;"
                                  onclick="addUpdate1(this)">
                        +</button></span>
                    <input type="text" value="${product.p_stock}" class="am-form-field" name="p_stock" id=""
                           style="width: 120px;text-align: center"
                           onkeyup="this.value=this.value.replace(/[^\d]/g,'');">
                    <button class="am-btn am-btn-default" type="button"
                            style="height: 31px;margin-top: -5px;line-height: 15px; border-radius: 3px;"
                            onclick="jianUpdate1(this)">-
                    </button>
                </div>
                <div>
                    <span>一级分类:</span>
                    <select id="category_select" name="p_categorylevel1" onchange="getTwoType(this)">
                        <option value="0">请选择</option>
                        <c:forEach items="${categorys1}" var="category">
                            <option value="${category.c_id}">${category.c_name}</option>
                        </c:forEach>
                    </select>
                    &nbsp;
                </div>
            </div>
            <div class="item1">
                <div>
                    <span>二级分类:</span>
                    <select name="p_categorylevel2" class="category_select" id="p_categorylevel2"
                            onchange="getShType(this)">
                        <option value="0">请选择</option>
                        <c:forEach items="${categorys2}" var="category">
                            <option value="${category.c_id}">${category.c_name}</option>
                        </c:forEach>
                    </select>
                    &nbsp;
                </div>
                <div>
                    <span>三级分类:</span>
                    <select name="p_categorylevel3" class="category_select" id="p_categorylevel3">
                        <option value="0">请选择</option>
                        <c:forEach items="${categorys3}" var="category">
                            <option value="${category.c_id}">${category.c_name}</option>
                        </c:forEach>
                    </select>
                    &nbsp;
                </div>
            </div>
            <div class="item1">
                <span>商品图片：</span>
                <input type="file" name="p_filename" id="image"/>
                <input type="hidden" name="fileName" value="${product.p_filename}">
            </div>
            <div>
                图片预览:&nbsp;&nbsp;&nbsp;<img src="" style="width: 80px;height: 80px;border:  1px" id="img">
            </div>
            <div class="item1 item_desc">
                <span>商品描述：</span>
                <textarea id="desc" name="p_description" rows="4" cols="50" value="${product.p_description}"></textarea>
            </div>
            <button class="am-btn am-btn-default" type="button" id="add">修改</button>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <button class="am-btn am-btn-default" type="button" id="reset">取消</button>
        </form>
    </div>

</div>

<script src="${ctx }/admin/js/jquery.min.js"></script>

<script>

    $(function () {
        $("#edit").click(function () {
            //获取表单  让其提交
            $("#edit_form").submit();
        });

        //让value的值等于商品的option成为选中状态
        $("#category_select option[value=${product.p_categorylevel1}]").prop("selected", true);
        $("#p_categorylevel2 option[value=${product.p_categorylevel2}]").prop("selected", true);
        $("#p_categorylevel3 option[value=${product.p_categorylevel3}]").prop("selected", true);
    });

    $("#image").change(function () {
        var objUrl = getObjectURL(this.files[0]);//获取文件信息
        if (objUrl) {
            $("#img").attr("src", objUrl);
        }
    });
    $("#reset").click(function () {
        window.location = "${pageContext.request.contextPath}/admin_product?action=allProduct&currPaNo=${param.currPaNo}";
    });

    function getObjectURL(file) {
        var url = null;
        if (window.createObjectURL != undefined) {
            url = window.createObjectURL(file);
        } else if (window.URL != undefined) { // mozilla(firefox)
            url = window.URL.createObjectURL(file);
        } else if (window.webkitURL != undefined) { // webkit or chrome
            url = window.webkitURL.createObjectURL(file);
        }
        return url;
    }

    function getTwoType(that) {
        var $val = $(that).select().val();
        getType($val, $("#p_categorylevel2"));
        getType($("#category_select").select().val(), $("#p_categorylevel3"));
    }

    function getShType(that) {
        var $val = $(that).select().val();
        getType($val, $("#p_categorylevel3"));
    }

    function getType(val, elem) {
        $.ajax({
            "url": "${pageContext.request.contextPath}/admin_product",
            "type": "GET",
            "data": {
                "action": "getCategoryByParentId",
                "parentid": val,
            },
            "dataType": "JSON",
            "success": function (data) {
                if (data.status == 1) {
                    var html = "<option value='0'>请选择</option>";
                    for (var i = 0; i < data.data.length; i++) {
                        var $option = "<option value='" + data.data[i].c_id + "'>" + data.data[i].c_name + "</option>";
                        html += $option;
                    }
                    elem.html(html);
                }
            }
        });
    }

    $("#add").click(function () {
        if ($("#name").val().trim() == '' || $("#name").val() == null) {
            $('#showInfo').text("请输入商品名称");
            $('#showInfo').fadeIn();
            closeShowInfo();
            return;
        } else if ($("#price").val().trim() == '' || $("#price").val() == null) {
            $('#showInfo').text("请输入价格");
            $('#showInfo').fadeIn();
            closeShowInfo();
            return;
        }
        $("#hidden").val($("#image").val());
        //让表单提交 GoodsAddServlet
        //获取表单  让其提交
        $("#add_form").submit();
    });

    function addUpdate1(jia) {
        var c = $(jia).parent().next().val();
        if (c.trim() == "") {
            c = 0;
        }
        c = parseInt(c) + 1;
        $(jia).parent().next().val(c);
    }

    function jianUpdate1(jian) {
        var c = $(jian).prev().val();
        if (c.trim() == "") {
            c = 1;
        }
        if (c == 1) {
            c = 1;
        } else {
            c = parseInt(c) - 1;
            $(jian).prev().val(c);
        }
    }
</script>
</body>
</html>