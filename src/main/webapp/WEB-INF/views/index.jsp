<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<%
//response.setHeader("Cache-Control","no-cache"); 
//response.setHeader("Pragma","no-cache"); 
//response.setDateHeader ("Expires", -1); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
     "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring MVC - Hibernate File Upload to Database Demo</title>

		<s:url var="url_css" value="/static/css/style_index.css"/>
		<s:url var="url_fontello" value="/static/css/fontello-4c30d2f1/css/fontello.css"/>
		<s:url var="url_js" value="/static/js/app.js"/>
		<s:url var="url_jq" value="/static/js/jquery-3.4.1.min.js"/>
        <link href="${url_css}" rel="stylesheet" type="text/css"/>
        <link href="${url_fontello}" rel="stylesheet" type="text/css"/>
        <script src="${url_jq}" type="text/javascript"></script>
        <script src="${url_js}" type="text/javascript"></script>
</head>
<body>

	<div id="container">
		<div id="top-bar">
			<div id="username"><p>Username</p></div>
			<div id="search-bar">
				<input type="text" placeholder="Find file">
			</div>
		</div>
		
		<div id="content">
			<div id="navigation">
				<ul>
					
					<form method="post" action="doUpload" enctype="multipart/form-data">
					<input type="file" onchange="form.submit()" id="defaultSelect" name="fileUpload" size="50" style="display: none"/>
						<li id="testLi">
                    		<i class="demo-icon icon-doc-new"></i>
							<p>Add new</p>
						</li>
					</form>
				
					<form action="logout" method="post"><button type="submit"><li>
						<i class="demo-icon icon-heart-empty"></i>
						<p>Favourite</p>
					</li></button></form>
					<form action="logout" method="post"><button type="submit"><li>
						<i class="demo-icon icon-trash"></i>	
						<p>Trash</p>
					</li></button></form>
					<form action="logout" method="post"><button type="submit"><li>
						<i class="demo-icon icon-cog"></i>
						<p>Settings</p>
					</li></button></form>
					<form action="logout" method="post"><button type="submit"><li>
						<i class="demo-icon icon-logout"></i>
						<p>Logout</p>
					</li></button></form>
				</ul>
			</div>
			<div id="files">
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
			</div>
		</div>
	</div>
</body>
</html>