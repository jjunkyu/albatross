<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		$("#repeatPassword,#password").keyup(function() {
			var password=$("#password").val();
			var repeatPassword=$("#repeatPassword").val();
			if(password!=repeatPassword){
				$("#checkPass").html('비밀번호가 일치하지 않습니다').css("color","red");
				$("#registerBtn").prop("disabled", true);
				
			}else{
				$("#checkPass").html('비밀번호가 일치합니다').css("color","black");
				$("#registerBtn").prop("disabled", false);
				
			}
		});
		
	});
</script>
<main class="container-fluid">
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
				<label class="control-label col-sm-6">패스워드 확인 : </label>
				<div class="col-sm-9">
				<input type="password" id="repeatPassword" name="repeatPassword" required="required">
				</div>
				<span id="checkPass" style="color:red;font-weight:bold;font-size:smaller" >
				</span>
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
				<label class="control-label col-sm-6">${sessionScope.pidQuery}:</label>
				<div class="col-sm-6">
				<input type="text" name="anwer" required="required" value="${sessionScope.loginVO.answer}"> 
				</div>
			</div> 
			<div class="form-group">
                    <div class="col-sm-9 col-sm-offset-3">
                        <button id="updateBtn" type="submit" class="btn btn-primary btn-block" disabled="disabled">수정 완료</button>
                    </div>
                </div>
		</form>
		</div>
	</div>
</main>