<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>GCloud</title>
		<s:url var="url_css" value="/static/css/style_log_reg.css"/>
		<s:url var="url_js" value="/static/js/app.js"/>
		<s:url var="url_jq" value="/static/js/jquery-3.4.1.min.js"/>
		<link href="https://fonts.googleapis.com/css?family=Lato:400,700,900&display=swap" rel="stylesheet">
        <link href="${url_css}" rel="stylesheet" type="text/css"/>
        <script src="${url_jq}" type="text/javascript"></script>
        <script src="${url_js}" type="text/javascript"></script>
	</head>

	<body>
	 <c:if test="${param.msg eq 'bad_login'}">
		<style>
			input{
				border: 1px solid red;
			}
			
			input:focus{
				outline: none;
				border: 2px solid red;
			}
			#login-button{
				border: 0px;
			}
		</style>
	</c:if>
	
		<div class="form">
			<p id="gcloud">GCLOUD</p>
			<!-- <p class="msg_err">Bad login or password</p> -->
			<form action="login" method="post">	
				<c:if test="${param.msg eq 'bad_login'}">
					<p class="msg-err">Bad username or password</p>
				</c:if>
				<input type="text" name="loginName" placeholder="Username"></input>
				<input type="password" name="password" placeholder="Password">
				<p id="forgot-pass"> <a href="#">Forgot password?</a> </p>
				<input id="login-button" type="submit" value="LOGIN">
				<p id="no-acc">Don't have an account?</p>
				<p id="create"> <a href="./register">CREATE</a> </p>
			</form>
		</div>
	</body>
</html>
