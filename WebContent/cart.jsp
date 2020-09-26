<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>購物車</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<style>
	.price {
		color: #ffaa80;
	}
	</style>
</head>
<body>
	<%@ include file="resources/header.jsp" %>

	<div class="container">
		<h1>購物車</h1>
		
		<c:if test="${empty sessionScope.cart.products}">
			<div>
				<div>購物車內沒有東西!!</div>
			</div>
		</c:if>
		
		<c:if test="${!empty sessionScope.cart.products}">
			<table class="table table-hover">
				<c:forEach items="${sessionScope.cart.products}" var="product">
					<tr>
						<td width="10%"><img src="resources/productsImg/${product.img}" class="img-responsive" style="width: 100%;" alt="Image"></td>
						<td style="font-size: 24px;"><div>${product.name}</div><div class="price">\$${product.price}</div></td>
						<td style="font-size: 24px;">${sessionScope.cart.productQuantities[product.name]}</td>
						<td><a class="btn btn-danger btn-lg glyphicon glyphicon-trash" href="CustomerServlet?action=removeFromCart&pName=${product.name}&pPrice=${product.price}"></a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		
		<div><a href="index.jsp" class="btn btn-warning">去看看其他商品</a></div>
	</div>
</body>
</html>