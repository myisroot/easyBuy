<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String ctx = request.getContextPath();
    request.setAttribute("ctx", ctx);
%>
<html>
<title>404</title>
<!-- Bootstrap core CSS -->
<link href="${ctx}/shop/css/bootstrap.css" rel="stylesheet">
<!-- FONT AWESOME CSS -->
<link href="${ctx}/shop/css/font-awesome.min.css" rel="stylesheet"/>
<!--GOOGLE FONT -->
<link href='http://fonts.googleapis.com/css?family=Nova+Flat' rel='stylesheet' type='text/css'>
<!-- custom CSS here -->
<link href="${ctx}/shop/css/404.css" rel="stylesheet"/>
</head>
<body>

<div class="container">

    <div class="row pad-top text-center">
        <div class="col-md-6 col-md-offset-3 text-center">
            <h1> What have you done? </h1>
            <h5> Now Go Back Using Below LInk</h5>
            <span id="error-link"></span>
            <h2>页面飞跑了</h2>
        </div>
    </div>

    <div class="row text-center">
        <div class="col-md-8 col-md-offset-2">

            <h3><i class="fa fa-lightbulb-o fa-5x"></i></h3>
            <a href="#" class="btn btn-primary">GO TO HOME PAGE</a>
        </div>
    </div>

</div>
<!-- /.container -->


<!--Core JavaScript file  -->
<script src="${ctx}/shop/js/jquery-1.10.2.js"></script>
<!--bootstrap JavaScript file  -->
<script src="${ctx}/shop/js/bootstrap.js"></script>
<!--Count Number JavaScript file  -->
<script src="${ctx}/shop/js/countUp.js"></script>
<!--Custom JavaScript file  -->
<script src="${ctx}/shop/js/custom.js"></script>
</body>

</html>
