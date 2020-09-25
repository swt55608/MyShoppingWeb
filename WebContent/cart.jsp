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
	<h1>購物車</h1>
	
	<c:if test="${empty sessionScope.cart.products}">
		購物車內沒有東西!!
	</c:if>
	
	<c:if test="${!empty sessionScope.cart.products}">
		<table>
			<tr>
				<th>名稱</th>
				<th>價格</th>
				<th>數量</th>
			</tr>
			<c:forEach items="${sessionScope.cart.products}" var="product">
				<tr>
					<td>${product.name}</td>
					<td>${product.price}</td>
					<td>${sessionScope.cart.productQuantities[product.name]}</td>
					<td><a href="CustomerServlet?action=removeFromCart&pName=${product.name}&pPrice=${product.price}">移除商品</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>