<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>商品</title>
</head>
<body>
	<div class="container">
		<c:forEach items="${applicationScope.products}" var="product">
			<div class="col-lg-4">
				<div class="panel panel-primary">
					<div class="panel-body"><img src="resources/productsImg/${product.img}" class="img-responsive" style="width: 100%;" alt="Image"></div>
					<div class="panel-footer" style="text-align: center;"><div>${product.name}</div><div>\$${product.price}</div></div>
					<a href="CustomerServlet?action=addToCart&pName=${product.name}&pPrice=${product.price}&pImg=${product.img}">
						<c:if test="${empty sessionScope.username}">
							<button class="addToCart btn btn-primary btn-block glyphicon glyphicon-shopping-cart" disabled>加到購物車</button>
						</c:if>
						<c:if test="${!empty sessionScope.username}">
							<button class="addToCart btn btn-primary btn-block glyphicon glyphicon-shopping-cart">加到購物車</button>
						</c:if>
					</a>
				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>