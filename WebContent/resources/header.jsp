<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<style>
    /* Remove the navbar's default rounded borders and increase the bottom margin */ 
    .navbar {
      margin-bottom: 50px;
      border-radius: 0;
    }
    
    /* Remove the jumbotron's default bottom margin */ 
     .jumbotron {
      margin-bottom: 0;
    }
   
    /* Add a gray background color and some padding to the footer */
    footer {
      background-color: #f2f2f2;
      padding: 25px;
    }
  </style>
</head>
<body>
	<div class="jumbotron">
	  <div class="container text-center">
	    <h1>My Shopping Web</h1>      
	  </div>
	</div>
	
	<nav class="navbar navbar-inverse">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>                        
	      </button>
	      <a class="navbar-brand" href="#">Logo</a>
	    </div>
	    <div class="collapse navbar-collapse" id="myNavbar">
	      <ul class="nav navbar-nav">
	        <li class="active"><a href="#">Home</a></li>
	        <li><a href="#">Products</a></li>
	        <li><a href="#">Deals</a></li>
	        <li><a href="#">Stores</a></li>
	        <li><a href="#">Contact</a></li>
	      </ul>
	      <c:if test="${empty sessionScope.username}">
		      <ul class="nav navbar-nav navbar-right">
		        <li><a href="register.jsp"><span class="glyphicon glyphicon-user"></span> 註冊</a></li>
		        <li><a href="login.jsp"><span class="glyphicon glyphicon-user"></span> 登入</a></li>
		      </ul>
	      </c:if>
		  <c:if test="${!empty sessionScope.username}">    
		      <ul class="nav navbar-nav navbar-right">
		        <li><a href="UserServlet?action=logout"><span class="glyphicon glyphicon-user"></span> 登出</a></li>
		        <li><a href="cart.jsp"><span class="glyphicon glyphicon-shopping-cart"></span> ${sessionScope.username} 購物車</a></li>
		      </ul>
	      </c:if>
	    </div>
	  </div>
	</nav>
</body>
</html>