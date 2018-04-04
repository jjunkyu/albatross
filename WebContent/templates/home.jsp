<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta content="charset=UTF-8">
	<base href="${pageContext.request.contextPath}/">
	<title>Home</title>
	<link rel="stylesheet" href="static/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="static/css/styles.css" />
</head>
<body>
	<div id="wrap" class="page ${requestScope.page}">
		<jsp:include page="header.jsp"></jsp:include>	
		<jsp:include page="${requestScope.url}"></jsp:include>
		<jsp:include page="footer.jsp"></jsp:include>	
	</div>
	
	<script type="text/javascript" src="static/js/jquery.js"></script>
	<script type="text/javascript" src="static/js/bootstrap.bundle.js"></script>
</body>
</html>