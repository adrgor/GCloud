<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>GCloud</title>
		<s:url var="url_css" value="/static/css/style.css"/>
        <link href="${url_css}" rel="stylesheet" type="text/css"/>
	</head>

	<body>
		<form action="login" method="post">
				Username
				<input type="text" name="loginName"/>
				Password
				<input type="password" name="password"/> 
				<button>Login</button><br>
				<a href="./register"> New User </a> 
		</form>
		
	</body>
</html>
