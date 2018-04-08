<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header class="container">
	<h3 class="logo">
	    <a href="dispatcher?command=home"><strong>Kosta 도서관</strong></a>
	</h3>
	<div class="header-panel row justify-content-end">
		<ul class="nav login-panel">
			<c:choose>
				<c:when test="${sessionScope.loginVO==null}">
					<li class="nav-item"><a class="nav-link"
						href="dispatcher?command=login">로그인</a></li>
					<li class="nav-item"><a class="nav-link"
						href="dispatcher?command=registerView">회원가입</a></li>
				</c:when>
				<c:when test="${sessionScope.loginVO.cId=='1'}">
					<li class="nav-item"><a class="nav-link">${sessionScope.loginVO.name}님</a></li>
					<li class="nav-item"><a class="nav-link"
						href="dispatcher?command=logout">로그아웃</a></li>
				</c:when>
				<c:otherwise>
					<li class="nav-item"><a class="nav-link">${sessionScope.loginVO.name}님</a></li>
					<li class="nav-item"><a class="nav-link"
						href="dispatcher?command=logout">로그아웃</a></li>
					<li class="nav-item"><a class="nav-link"
						href="dispatcher?command=myAccount">내 계정</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
	<div class="header-links row justify-content-end">
		<nav class="nav-menu">
			<ul class="nav">
				<li class="nav-item"><a class="nav-link" href="dispatcher?command=bookList">도서관</a></li>
				<li class="nav-item"><a class="nav-link"
					href="dispatcher?command=postList">자유게시판</a></li>
			</ul>
		</nav>
	</div>
</header>