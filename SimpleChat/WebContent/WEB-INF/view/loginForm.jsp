<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" href="./css/loginForm.css">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<title>ChatRoom Login</title>
</head>
<body>
	<div class = "container">
	<div class="wrapper">
		<form action="./login" method="post" name="Login_Form" class="form-signin">
		    <h3 class="form-signin-heading"> Please Sign In</h3>
			  <hr class="colorgraph"><br>

			  <input type="text" class="form-control" name="Mail" placeholder="Mail" required="" autofocus="" />
			  <input type="password" class="form-control" name="Password" placeholder="Password" required=""/>

			  <button class="btn btn-lg btn-primary btn-block"  name="Submit" value="Login" type="Submit">Login</button>

		</form>

		<form action="./register" method="get" name="Login_Form" class="form-signin">
			  <button class="btn btn-lg btn-primary btn-block"  name="Submit" value="Register" type="Submit">Register</button>
		</form>

<!--
		<form action="./dbController" method="post" name="Login_Form" class="form-signin">
			  <button class="btn btn-lg btn-primary btn-block"  name="Submit" value="selectAll" type="Submit">selectAll</button>
		</form>
-->
		<form action="./dbController" method="get" name="Login_Form" class="form-signin">
			  <input type="text" class="form-control" name="Mail" placeholder="Mail" required="" autofocus="" />
			  <input type="password" class="form-control" name="Password" placeholder="Password" required=""/>
			  <button class="btn btn-lg btn-primary btn-block"  name="Submit" value="update" type="Submit">update</button>
		</form>
	</div>
</div>

<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</body>
</html>