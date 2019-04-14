<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<style>
    * {
        margin: 0;
        padding: 0;
        list-style: none;
    }

    body {
        width: 100%;
        height: 100%;

    }

    .model {
        overflow: hidden;
        float: left;
        width: 100%;
        height: 870px;
        background: rgb(221, 221, 221);
    }

    .person_info {
        float: left;
        height: 100%;
        width: 15%;
        border: 1px green solid;
    }

    .person_info li {
        height: 35px;
        width: 100%;
        line-height: 35px;
        text-align: center;
        overflow: hidden;
        cursor: pointer;
        border-bottom: 1px solid #cccccc;
    }

    .text_box {
        height: 100%;
        width: 84.5%;
        float: left;
        border-top: 1px solid green;
        border-right: 1px solid green;
        border-bottom: 1px solid green;
        position: relative;
    }

    .text_body {
        width: 60%;
        border: 1px solid #CCCCCC;
        height: 820px;
        overflow: auto;
    }

    #showInfo {
        position: absolute;
        right: 20px;
        height: 40px;
        line-height: 40px;
        text-align: center;
        background-color: green;
        border-radius: 5px;
        display: none;
        font-size: 14px;
        color: white;
        z-index: 9999;
    }

    textarea {
        width: 400px;
    }

    button[type='button'] {
        vertical-align: top;
        width: 60px;
        height: 33px;
    }

    .text_bottom {
        position: absolute;
        bottom: 10px;
    }

    strong {
        margin-right: 8px;
        color: red;
        display: none;
    }
</style>

<body>
<div class="model">
    <ul class="person_info">
        <c:forEach items="${allUser}" var="User">
            <li user_id="${User.u_id}"><strong>新消息</strong><span>${User.u_loginname}</span></li>
        </c:forEach>
    </ul>
    <c:forEach items="${allUser}" var="User">
        <div class="text_box">
            <div>
                <div class="text_body">
                </div>
                <div class="text_bottom">
                    <textarea> </textarea>
                    <button type="button">
                        发送
                    </button>
                </div>
            </div>
        </div>
    </c:forEach>
    <div id="showInfo"></div>
</div>
<script src="${pageContext.request.contextPath}/admin/js/jquery.min.js"></script>
<script>
    var userno = "admin";
    var liIndxe = 0;
    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8080/my_store_war_exploded/websocket/" + userno);
    } else {
        alert('当前浏览器 Not support websocket')
    }
    websocket.onerror = function () {
        console.log("WebSocket连接发生错误");
    };


    //连接成功建立的回调方法
    websocket.onopen = function () {
        console.log("WebSocket连接成功");
    }

    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        //获取当前索引
        var liIndex = 0;
        //获取的信息1内容
        var str = event.data.toString();
        var index = str.indexOf(":");
        //截取用户名
        var username = str.substring(0, index);
        $("#showInfo").text("收到一条新信息");
        $("#showInfo").fadeIn();
        setTimeout(function () {
            $("#showInfo").fadeOut();
        }, 5000);
        $(".person_info li").each(function () {
            var name = $(this).attr('user_id').trim();
            if (username == name) {
                liIndex = $(this).index();
                //内容框
                var $text_box = $(".text_body").eq(liIndex);
                var sendName = $(this).find("span").text();
                //拼接一条信息
                var info = "<span style='color:blue;font-size: 14px;'>" + getNowFormatDate() + "</span><br>" + sendName + ":" + str.substring(index + 1, str.length) + "<br/><br/>";
                $text_box.append(info);
                $('.text_body').scrollTop($('.text_body').get(liIndex).scrollHeight);
                return false;
            }
        });

        if (liIndex != liIndxe) {
            $("li[user_id='" + username + "']").find("strong").show();
        }
    }


    //连接关闭的回调方法
    websocket.onclose = function () {
        alert("WebSocket连接关闭");
    }
    window.onbeforeunload = function () {
        websocket.close();
    }

    $(function () {
        $(".person_info li").first().css("background", "green");
    });

    $(".person_info li").click(function () {
        var $this = $(this);
        liIndxe = $this.index();
        $this.find('strong').hide();
        $(".person_info li").css("background", "");
        $(".text_box").hide();
        $(".text_box").eq(liIndxe).show();
        $this.css("background", "green");
    });

    $("button[type='button']").click(function () {
        var message = $(this).prev().val();
        if (message == null || message.trim() == "") {
            alert("请输入发送信息!");
            return;
        }
        //获取要发送的人的id
        var toSendUserNo = $(".person_info li").eq(liIndxe).attr('user_id');
        //获取当前内容框
        var $text_box = $(".text_body").eq(liIndxe);
        var info = "<div style='width: 100%;text-align: right;'> <span style='color:blue;font-size: 14px;'>" + getNowFormatDate() + "</span><br>" + userno + ":" + message + "</div>";
        $text_box.append(info);
        $('.text_body').scrollTop($('.text_body').get(liIndxe).scrollHeight);
        $(this).prev().val("");
        //发送信息
        message = message + "|" + toSendUserNo//将要发送的信息和内容拼起来，以便于服务端知道消息要发给谁
        websocket.send(message);
    });

    function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
        return currentdate;
    }
</script>
</body>

</html>