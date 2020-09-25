<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>會員中心</h1>
	
	<c:if test="${sessionScope.username == null}">
		<a href="register.jsp">註冊</a> | <a href="login.jsp">登入</a>
	</c:if>
	
	<c:if test="${sessionScope.username != null}">
		<a href="CustomerServlet?action=viewProducts">商品清單</a> | 
		<a href="cart.jsp">購物車</a> | 
		<a href="UserServlet?action=logout">登出</a>
	</c:if>
</body>
</html>