<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//code.jquery.com/jquery.min.js"></script>

<main class="container">
	<div class="row justify-content-md-center">
		<div class="col-12 col-md-7">

		<form class="form-horizontal" id="memberUpdateForm" role="form" method="post"
			name="memberUpdateForm" action="dispatcher?command=memberUpdate">
			
			<h3>회원정보 수정</h3>
			<hr>
			<div class="form-group">
				<label for="id">아이디 :</label>
				<input type="text" id="id" name="id" class="form-control" readonly="readonly" value="${sessionScope.loginVO.id }">
			</div>
			<div class="form-group">
				<label class="control-label" for="password">패스워드 :</label>
				<input type="password" id="password" class="form-control" name="password" required="required" value="${sessionScope.loginVO.password}">
			</div>
			
			<div class="form-group">
				<label class="control-label" for="name">이름 : </label>
				<input type="text" name="name" class="form-control" required="required" value="${sessionScope.loginVO.name}"> 
			</div> 
			<div class="form-group">
				<label class="control-label" for="email">email : </label>
				<input type="email" id="email" name="email" class="form-control" readonly="readonly" value="${sessionScope.loginVO.email}">
			</div> 
			<div class="form-group">
				<label class="control-label" for="address">주소 : </label>
				<input type="text" name="address" required="required" class="form-control" value="${sessionScope.loginVO.address}"> 
			</div> 
			<div class="form-group">
				<label class="control-label" for="answer">${requestScope.pidQuery}:</label>
				<input type="text" name="answer" class="form-control" required="required" value="${sessionScope.loginVO.answer}"> 
			</div> 
			<div class="form-group">
           		<button id="updateBtn" type="submit" class="btn btn-primary btn-block">수정 완료</button>
            </div>
		</form>
		</div>
	</div>
</main>