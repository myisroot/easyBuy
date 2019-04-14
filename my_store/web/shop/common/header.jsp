<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String ctx = request.getContextPath();
    request.setAttribute("ctx", ctx);
%>
<link type="text/css" rel="stylesheet" href="${ctx}/shop/css/style.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/shop/css/pageStyle.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/shop/alert/css/animate.min.css"/> <!-- 动画效果 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/shop/alert/css/common.css"/><!-- 页面基本样式 -->
<script type="text/javascript">
    var path = '${ctx}';
</script>
<div class="soubg">
    <div class="sou">
        <!--Begin 所在收货地区 Begin-->
        <span class="s_city_b">
        	<span class="fl">送货至：</span>
            <span class="s_city">
            	<span>四川</span>
                <div class="s_city_bg">
                	<div class="s_city_t"></div>
                    <div class="s_city_c">
                    	<h2>请选择所在的收货地区</h2>
                        <table border="0" class="c_tab" style="width:235px; margin-top:10px;" cellspacing="0"
                               cellpadding="0">
                          <tr>
                            <th>A</th>
                            <td class="c_h"><span>安徽</span><span>澳门</span></td>
                          </tr>
                          <tr>
                            <td class="c_h"><span>北京</span></td>
                          </tr>
                          <tr>
                            <th>C</th>
                            <td class="c_h"><span>重庆</span></td>
                          </tr>
                          <tr>
                            <th>F</th>
                            <td class="c_h"><span>福建</span></td>
                          </tr>
                          <tr>
                            <th>G</th>
                            <td class="c_h"><span>广东</span><span>广西</span><span>贵州</span><span>甘肃</span></td>
                          </tr>
                          <tr>
                            <th>H</th>
                            <td class="c_h"><span>河北</span><span>河南</span><span>黑龙江</span><span>海南</span><span>湖北</span><span>湖南</span></td>
                          </tr>
                          <tr>
                            <th>J</th>
                            <td class="c_h"><span>江苏</span><span>吉林</span><span>江西</span></td>
                          </tr>
                          <tr>
                            <th>L</th>
                            <td class="c_h"><span>辽宁</span></td>
                          </tr>
                          <tr>
                            <th>N</th>
                            <td class="c_h"><span>内蒙古</span><span>宁夏</span></td>
                          </tr>
                          <tr>
                            <th>Q</th>
                            <td class="c_h"><span>青海</span></td>
                          </tr>
                          <tr>
                            <th>S</th>
                            <td class="c_h"><span>上海</span><span>山东</span><span>山西</span><span class="c_check">四川</span><span>陕西</span></td>
                          </tr>
                          <tr>
                            <th>T</th>
                            <td class="c_h"><span>台湾</span><span>天津</span></td>
                          </tr>
                          <tr>
                            <th>X</th>
                            <td class="c_h"><span>西藏</span><span>香港</span><span>新疆</span></td>
                          </tr>
                          <tr>
                            <th>Y</th>
                            <td class="c_h"><span>云南</span></td>
                          </tr>
                          <tr>
                            <th>Z</th>
                            <td class="c_h"><span>浙江</span></td>
                          </tr>
                        </table>
                    </div>
                </div>
            </span>
        </span>
        <!--End 所在收货地区 End-->
        <span class="fr">
        	  <c:if test="${empty user}">
                  <span class="fl">你好,
                  请<a href="${ctx}/shop/Login.jsp">登录</a>&nbsp;|&nbsp;

                <a href="${ctx}/shop/Regist.jsp" style="color:#ff4e00;">免费注册</a>&nbsp;&nbsp;
              </span>
              </c:if>
            <c:if test="${!empty user}">
                  <span class="fl">你好 ,
                  <a href="#">${user.u_loginname}</a>&nbsp;|&nbsp;
                  <a href="${ctx}/login?action=exit" style="color:#ff4e00;" id="exit">退出</a>&nbsp;|&nbsp;
                  <a href="${ctx}/product?action=getOrderVo&uid=${user.u_id}">我的订单</a>&nbsp;|&nbsp;
                  <a href="javascript:void (0);" onclick="openChat()">在线客服</a>&nbsp;|&nbsp;
                  <a href="${ctx}/admin/admin_login.jsp">后台管理</a>
              </span>
            </c:if>
            <%--</span>--%>
        </span>
    </div>
</div>
<script>
    function openChat() {
        window.open("${ctx}/chat/userChat.jsp", 'newwindow', 'height=900, width=600, top=0,left=1000, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no')
    }
</script>
</body>
</html>
