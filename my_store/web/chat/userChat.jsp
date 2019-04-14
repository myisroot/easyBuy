<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
        <title>在线客服</title>
</head>
<style>
    * {
        margin: 0;
        padding: 0;
    }
</style>
<body>
 
<hr/>
 
<div id="message" style="width: 60%;height: 700px;border: 1px solid #CCCCCC;overflow: auto;margin-left: 20px;"></div>
    <!--userno:发送消息人的编号-->
<textarea id="text" type="text" style="width: 300px;height: 33px;"></textarea>
<button onclick="send()" type="button" style="vertical-align: top; width: 60px;height: 33px">发送
</button>
   
</body>
<script src="${pageContext.request.contextPath}/admin/js/jquery.min.js"></script>
<script type="text/javascript">
    var websocket = null;
    var userno = '${user.u_id}';
    var userName = '${user.u_loginname}';
    var toSendUserNo = 'admin'; //接收人编号：4567
    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8080/my_store_war_exploded/websocket/" + userno);
    } else {
        alert('当前浏览器 Not support websocket')
    }


    //连接发生错误的回调方法
    websocket.onerror = function () {
        setMessageInnerHTML("WebSocket连接发生错误");
    };


    //连接成功建立的回调方法
    websocket.onopen = function () {
        setMessageInnerHTML("连接成功");
    }


    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        setMessageInnerHTML(event.data);
    }


    //连接关闭的回调方法
    websocket.onclose = function () {
        setMessageInnerHTML("WebSocket连接关闭");
    }


    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        closeWebSocket();
    }


    //将消息显示在网页上
    function setMessageInnerHTML(sendMessage) {
        document.getElementById('message').innerHTML += '<br/>' + "<span style='color:blue;'> " + getNowFormatDate() + "</span>" + '<br/>' + sendMessage + '<br/>';
        $('#message').scrollTop($('#message').get(0).scrollHeight);
    }


    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
    }


    //发送消息
    function send() {
        var message = document.getElementById('text').value;//要发送的消息内容
        if (message.trim() == "" || message == null) {
            alert("请输入内容");
            return false;
        }
        document.getElementById('text').value = '';
        var now = getNowFormatDate();//获取当前时间
        document.getElementById('message').innerHTML += "<div style='text-align:right;width: 100%;'>" + "<span style='color: blue;text-align:left'>" + now + "</span>" + "<br>" + userName + ":" + message + "</div> ";
        message = message + "|" + toSendUserNo//将要发送的信息和内容拼起来，以便于服务端知道消息要发给谁
        $('#message').scrollTop($('#message').get(0).scrollHeight);
        websocket.send(message);
    }

    //获取当前时间
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
</html>