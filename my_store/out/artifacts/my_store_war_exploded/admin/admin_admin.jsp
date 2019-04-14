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
</head>
<body style="background:#f3f3f3;">

<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">管理员列表</strong>
            <small></small>
        </div>
    </div>
    <hr>
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6">
            <div class="am-btn-toolbar">
                <div class="am-btn-group am-btn-group-xs">
                    <button id="add" class="am-btn am-btn-default">
                        <span class="am-icon-plus"></span> 添加管理员
                    </button>
                </div>
            </div>
        </div>
    </div>
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-3">
        </div>
        <div class="am-u-sm-12 am-u-md-3">
            <div class="am-input-group am-input-group-sm">
                <input type="text" class="am-form-field" id="input_search" value="${uName}">
                <span class="am-input-group-btn">
                    <button class="am-btn am-btn-default" type="button" id="input_search_btn">搜索</button>
                </span>
            </div>
        </div>
    </div>
</div>

<div class="goods_list">
    <ul class="title_ul">
        <li>管理员</li>
        <li>删除权限</li>
        <li>增加权限</li>
        <li>修改权限</li>
        <li>是否启用</li>
        <li>修改密码</li>
        <li>删除</li>
    </ul>
    <div id="info_box">
        <c:forEach items="${allUser}" var="Admin" varStatus="i">
            <c:if test="${Admin.u_isadmin!=ROOT}">
                <ul class="list_goods_ul">
                    <li>${Admin.u_loginname}</li>
                    <li><span
                            <c:if test="${Admin.u_isdelete==NOREMOVE}">class="switch-off"</c:if>
                            <c:if test="${Admin.u_isdelete==ISREMOVE}">class="switch-on"</c:if>
                            onclick="checkDelete(this,'${Admin.u_id}')"></span></li>
                    </li>
                    <li><span
                            <c:if test="${Admin.u_isadd==NOADD}">class="switch-off"</c:if>
                            <c:if test="${Admin.u_isadd==ISADD}">class="switch-on"</c:if>
                            onclick="checkAdd(this,'${Admin.u_id}')"></span></li>
                    </li>
                    <li><span
                            <c:if test="${Admin.u_isupdate==NOUPDATE}">class="switch-off"</c:if>
                            <c:if test="${Admin.u_isupdate==ISUPDATE}">class="switch-on"</c:if>
                            onclick="checkUpdate(this,'${Admin.u_id}')"></span></li>
                    </li>
                    <li><span
                            <c:if test="${Admin.u_islogin==NOLOGIN}">class="switch-off"</c:if>
                            <c:if test="${Admin.u_islogin==ISLOGIN}">class="switch-on"</c:if>
                            onclick="checkLogin(this,'${Admin.u_id}')"></span></li>
                    </li>
                    <li>
                        <a href="javascript:void (0)" onclick="updateAdminPwd('${Admin.u_id}')"><img
                                class="img_icon" src="${pageContext.request.contextPath}/admin/images/edit_icon.png"
                                alt="">
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void (0)" onclick="delAdmin('${Admin.u_id}')"><img
                                class="img_icon" src="${pageContext.request.contextPath}/admin/images/delete_icon.png"
                                alt="删除">
                        </a>
                    </li>
                </ul>
            </c:if>
        </c:forEach>
        <c:if test="${empty allUser}">
            <ul class="list_goods_ul">
                <li>没有查询到记录</li>
            </ul>
        </c:if>
    </div>
    <!--分页-->
    <div id="page" class="page_div">1</div>
    <div id="showBox" class="showBox">
        <h5>是否确认删除?</h5>
        <button class="am-btn am-btn-default" type="button" id="delete">确认</button>
        <button class="am-btn am-btn-default" type="button" style="margin-left:20px;" id="exitShowBow">取消</button>
    </div>
</div>
<div id="showInfo" class="modal_content">
</div>
<div id="modal_content_account">
    <div id="close1"><img src="${pageContext.request.contextPath}/admin/images/delete_icon.png" alt=""></div>
    <div class="edit_content">
        <div class="item1">
            <div>
                <span>添加管理员：</span>
            </div>
        </div>
        <div class="item1">
            <div>
                <span>用户名:</span>
                <input type="text" class="am-form-field" name="adminName">&nbsp;&nbsp;
                <span style="color: red;" id="name_err"></span>
            </div>
        </div>
        <div class="item1">
            <div>
                <span>密&nbsp;&nbsp;&nbsp;码:</span>
                <input type="text" class="am-form-field" name="adminPwd">&nbsp;&nbsp;
                <span style="color: red;" id="pwd_err"></span>
            </div>
        </div>
        <div class="item1">
            <button class="am-btn am-btn-default" type="button" id="add_admin">添加</button>
        </div>
    </div>
</div>

<div class="modal_content_account" id="updatePwd">
    <div id="close"><img src="${pageContext.request.contextPath}/admin/images/delete_icon.png" alt=""></div>
    <div class="edit_content">
        <div class="item1">
            <div>
                <span>修改密码：</span>
            </div>
        </div>
        <div class="item1">
            <div>
                <span>新密码:</span>
                <input type="password" class="am-form-field" name="oldPwd">&nbsp;&nbsp;
                <span style="color: red;font-size: 12px;" id="oldPwd_error"></span>
            </div>
        </div>
        <div class="item1">
            <div>
                <span>再次输入:</span>
                <input type="password" class="am-form-field" name="newPwd">&nbsp;&nbsp;
                <span style="color: red;font-size: 12px;" id="newPwd_error"></span>
            </div>
        </div>
        <div class="item1">
            <button class="am-btn am-btn-default" type="button" id="update_admin">修改</button>
        </div>
    </div>
</div>

<div id="modal_view">
</div>
<script src="${pageContext.request.contextPath}/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/paging.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/honeySwitch.js"></script>
<script>
    //分页
    var uid;
    $(function () {
        $("#page").paging({
            pageNo: ${pageBean.currPaNo},
            totalPage: ${pageBean.pageCounts},
            totalSize: ${pageBean.allCounts},
            callback: function (num) {
                $(window).attr('location', '${pageContext.request.contextPath}/admin_user?action=getAdminUsers&currPaNo='
                    + num + '&uName=' + $("#input_search").val().trim());
            }
        });


        $("#exitShowBow").click(function () {
            $("#showBox").fadeOut();
        });

        //删除管理员
        $("#delete").click(function () {
            $("#showBox").fadeOut();
            $.ajax({
                "url": "${pageContext.request.contextPath}/admin_user",
                "type": "POST",
                "data": {
                    "action": "delAdmin",
                    "uid": uid,
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


        //查询
        $("#input_search_btn").click(function () {
            var search = $("#input_search").val().trim();
            if (search != null) {
                $(window).attr('location', '${pageContext.request.contextPath}/admin_user?action=getAdminUsers&currPaNo=1&uName=' + search);
            }
        });

        /**
         * 添加管理员
         */
        $("#add_admin").click(function () {
            $("#name_err").text('');
            $("#pwd_err").text('');
            var adminName = $("input[name='adminName']").val().toString().trim();
            var adminPwd = $("input[name='adminPwd']").val().toString().trim();
            if (adminName == null || adminName === '') {
                $("#name_err").text('请输入用户名');
                return;
            }
            if (adminPwd == null || adminPwd === '') {
                $("#pwd_err").text('请输入密码');
                return;
            }
            $.ajax({
                "url": "${pageContext.request.contextPath}/admin_user",
                "type": "POST",
                "data": {
                    "action": "addAdmin",
                    "adminName": adminName,
                    "adminPwd": adminPwd
                },
                "dataType": "JSON",
                "success": function (data) {
                    if (data.status == 1) {
                        $('#showInfo').text("添加成功");
                        $('#showInfo').fadeIn();
                        $("#modal_view").fadeOut();
                        $("#modal_content_account").fadeOut();
                        close();
                    } else {
                        $("#name_err").text(data.message);
                    }
                }
            });
        });

        //显示添加管理员模块
        $('#add').click(function () {
            $("#modal_view").fadeIn();
            $("#modal_content_account").fadeIn();
        });

        //关闭添加管理员模块
        $("#close1").click(function () {
            $("#modal_view").fadeOut();
            $("#modal_content_account").fadeOut();
        });

        //关闭修改管理员密码模块
        $("#close").click(function () {
            $("#updatePwd").fadeOut();
            $("#modal_view").fadeOut();
        });
    });


    //修改密码
    function updateAdminPwd(uid) {
        $("#modal_view").fadeIn();
        $("#updatePwd").fadeIn();

        $('#update_admin').click(function () {
            $('#oldPwd_error').text('');
            $('#newPwd_error').text('');
            var oldPwd = $("input[name='oldPwd']").val().toString().trim();
            var newPwd = $("input[name='newPwd']").val().toString().trim();
            if (oldPwd == null || oldPwd === '') {
                $('#oldPwd_error').text('请输入新密码');
                return;
            }
            if (newPwd == null || newPwd === '') {
                $('#newPwd_error').text('请再次输入新密码');
                return;
            }
            if (newPwd !== oldPwd) {
                $('#newPwd_error').text('密码请保持相同');
                return;
            }
            $.ajax({
                "url": "${pageContext.request.contextPath}/admin_user",
                "type": "POST",
                "data": {
                    "action": "editAdminPwd",
                    "uid": uid,
                    "newPwd": newPwd
                },
                "dataType": "JSON",
                "success": function (data) {
                    if (data.status == 1) {
                        closeAndAlert('修改成功');
                        $("#updatePwd").fadeOut();
                        $("#modal_view").fadeOut();
                    } else {
                        $('#newPwd_error').text(data.message);
                    }
                }
            });
        });
    }

    function close() {
        setTimeout(function () {
            $('#showInfo').fadeOut();
            $(window).attr('location', '${pageContext.request.contextPath}/admin_user?action=getAdminUsers&flag=false&currPaNo=${curr}&uName' + $("#input_search").val().trim());
        }, 1000)
    }

    //修改删除权限
    function checkDelete(that, uid) {
        var str;
        var $class = $(that).attr("class").trim();
        if ($class == "switch-off") {
            str = update(uid, ${ISREMOVE}, "给予删除权限", "del");
            if (str === false) {
                $(that).attr("class", "switch-on")
            }
        }
        if ($class == "switch-on") {
            str = update(uid, ${NOREMOVE}, "去除删除权限", "del");
            if (str === false) {
                $(that).attr("class", "switch-off")
            }
        }
    }

    //修改添加权限
    function checkAdd(that, uid) {
        var str;
        var $class = $(that).attr("class").trim();
        if ($class == "switch-off") {
            str = update(uid, ${ISADD}, "给予增加权限", "add");
            if (str === false) {
                $(that).attr("class", "switch-on")
            }
        }
        if ($class == "switch-on") {
            str = update(uid, ${NOADD}, "去除增加权限", "add");
            if (str === false) {
                $(that).attr("class", "switch-off")
            }
        }
    }

    //修改权限
    function checkUpdate(that, uid) {
        var flag;
        var $class = $(that).attr("class").trim();
        if ($class == "switch-off") {
            flag = update(uid, ${ISUPDATE}, "给予修改权限", "update");
            if (flag === false) {
                $(that).attr("class", "switch-on")
            }
        }
        if ($class == "switch-on") {
            flag = update(uid, ${NOUPDATE}, "去除修改权限", "update");
            if (flag === false) {
                $(that).attr("class", "switch-off")
            }
        }
    }

    //是否启用
    function checkLogin(that, uid) {
        var flag;
        var $class = $(that).attr("class").trim();
        if ($class == "switch-off") {
            flag = update(uid, ${ISLOGIN}, "操作成功", "setLogin");
            if (flag === false) {
                $(that).attr("class", "switch-on")
            }
        }
        if ($class == "switch-on") {
            flag = update(uid, ${NOLOGIN}, "操作成功", "setLogin");
            if (flag === false) {
                $(that).attr("class", "switch-off")
            }
        }
    }

    function closeShowInfo() {
        setTimeout(function () {
            $('#showInfo').fadeOut();
        }, 1000)
    }

    function closeAndAlert(message) {
        $('#showInfo').text(message);
        $('#showInfo').fadeIn();
        closeShowInfo();
    }

    function update(uid, $class, message, checktype) {
        var flag = true;
        $.ajax({
            "url": "${pageContext.request.contextPath}/admin_user",
            "type": "POST",
            "async": false,
            "data": {
                "action": "checkLogin",
                "uid": uid,
                "class": $class,
                "checkType": checktype
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
                    flag = false;
                    return flag;
                }
            }
        });
        return flag;
    }

    function delAdmin(u_id) {
        uid = u_id;
        $("#showBox").fadeIn();
    }
</script>

</body>
</html>