<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%><%@ 
	taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />

<script src="js/index.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css" />


<title>DOSN - Login</title>
</head>
<body>

				
<div class="wrapper">
	<div class="container">
		<h1>Welcome</h1>

	<h2>TU-Berin DOSN</h2>

					<c:if test="${not empty param.login_error}">
						<font color="red"> Your login attempt was not successful,
							try again.<br />Reason: <c:out
								value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />.
						</font>
					</c:if>

					
					<form name="form" class="form" action="<c:url value="/j_spring_security_check"></c:url>" method="post">
								<input name="j_username" placeholder="username" type="text" />
								<input name="j_password" placeholder="password" type="password" />
								<input type="submit" value="login" name="submit" id="submit" />
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					</form>

	
</div>
<ul class="bg-bubbles">
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
</div>

			
			
			
</body>
</html>