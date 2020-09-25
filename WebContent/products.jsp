<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
	table, tr, th, td {
		border: 1px solid black;
	}
	</style>
</head>
<body>
<!-- 	<h1>商品</h1> -->
<!-- 	<a href="cart.jsp">購物車</a> -->
	<table>
		<tr>
			<th>名稱</th>
			<th>價格</th>
		</tr>
		<c:forEach items="${applicationScope.products}" var="product">
			<tr>
				<td>${product.name}</td>
				<td>${product.price}</td>
				<c:if test="${!empty sessionScope.username}">
					<td><a href="CustomerServlet?action=addToCart&pName=${product.name}&pPrice=${product.price}">加到購物車</a></td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
</body>
</html>