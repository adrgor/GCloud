<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	<s:url var="url_js" value="/static/js/app.js"/>
	<s:url var="url_jq" value="/static/js/jquery-3.4.1.min.js"/>
	<script src="${url_jq}" type="text/javascript"></script>
	<script src="${url_js}" type="text/javascript"></script>
</head>
<body>
	<c:forEach var="doc" items="${documentList}" varStatus="iteration">
		<div id="file_id_${doc.id}">
			${iteration.index+1}
			${doc.fileName} <button onclick="deleteDoc(${doc.id})">Delete</button> 
			<form action="download_doc" method="get">
				<input type = "hidden" name = "id" value = "${doc.id}" />
				<button>Download</button></br>
			</form>
		</div>
	</c:forEach>
	Here are my files
</body>
</html>