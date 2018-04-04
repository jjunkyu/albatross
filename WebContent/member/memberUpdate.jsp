<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//code.jquery.com/jquery.min.js"></script>

<main class="container">
	<div class="row justify-content-md-center">
		<div class="col-sm-9">

		<form class="form-horizontal" id="memberUpdateForm" role="form" method="post"
			name="memberUpdateForm" action="dispatcher?command=memberUpdate">
			
			<h3>회원정보 수정</h3>
			<hr>
			<div class="form-group">
				<label class="col-sm-6 control-label">아이디 :</label>
				<div class="col-sm-9">
				<input type="text" id="id" name="id" readonly="readonly" value="${sessionScope.loginVO.id }">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-6">패스워드 :</label>
				<div class="col-sm-9">
					<input type="password" id="password" name="password" required="required" value="${sessionScope.loginVO.password}">
					</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-6">이름 : </label>
				<div class="col-sm-9">
				<input type="text" name="name" required="required" value="${sessionScope.loginVO.name}"> 
				</div>
			</div> 
			<div class="form-group">
				<label class="control-label col-sm-6">email : </label>
				<div class="col-sm-9">
				<input type="email" id="email" name="email" readonly="readonly" value="${sessionScope.loginVO.email}">
				</div>
			</div> 
			<div class="form-group">
				<label class="control-label col-sm-6">주소 : </label>
				<div class="col-sm-9">
				<input type="text" name="address" required="required" value="${sessionScope.loginVO.address}"> 
				</div>
			</div> 
			<div class="form-group">
				<label class="control-label col-sm-6">${requestScope.pidQuery}:</label>
				<div class="col-sm-6">
				<input type="text" name="answer" required="required" value="${sessionScope.loginVO.answer}"> 
				</div>
			</div> 
			<div class="form-group">
                    <div class="col-sm-9 col-sm-offset-3">
                        <button id="updateBtn" type="submit" class="btn btn-primary btn-block">수정 완료</button>
                    </div>
                </div>
		</form>
		</div>
	</div>
</main>