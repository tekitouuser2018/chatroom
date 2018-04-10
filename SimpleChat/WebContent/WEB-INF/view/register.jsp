<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" href="./css/loginForm.css">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script text ="text/javascript">


var today = new Date();
var month = today.getMonth()+1;
var setDay = today.getFullYear()+"/"+month+"/"+today.getDate();


$(function(){
	$("*[name=date]").val(setDay);
});

</script>

<title>ChatRoom Register</title>
</head>
<body onload="setDay();">
	<div class = "container">
	<div class="wrapper">
		<form action="./register" method="post" name="Login_Form" class="form-signin">
		    <h3 class="form-signin-heading"> Please Regist</h3>
			  <hr class="colorgraph"><br>

			  <input type="text" class="form-control" name="Mail" placeholder="Mail" required="" autofocus="" />
			  <input type="password" class="form-control" name="Password" placeholder="Password" required=""/>
			  <input type="text" class="form-control" name="Name" placeholder="Name" />
			  <input  type="hidden" name = "date" value="" >
			  <button class="btn btn-lg btn-primary btn-block"  name="Submit" value="Regist" type="Submit">Regist</button>

		</form>


	</div>
</div>

<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

</body>
</html>