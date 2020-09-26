<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>登入</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<%@ include file="resources/header.jsp" %>
	
	<div class="container">
	  <h2>登入</h2>
	  <form action="UserServlet?action=login" method="post">
	    <div class="form-group">
	      <label for="username">Username:</label>
	      <input type="text" class="form-control" id="username" placeholder="Enter username" name="username" required>
	    </div>
	    <div class="form-group">
	      <label for="password">Password:</label>
	      <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" required>
	    </div>
	    <input type="submit" class="btn btn-primary" value="登入">
	  </form>
	  
	  <c:if test="${!empty sessionScope.errMsg}">
		<div style="color:red;">${sessionScope.errMsg}</div>
	  </c:if>
	</div>
</body>
</html>