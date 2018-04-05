<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		
		$("#id").keyup(function() {
			var idValue = $(this).val();
			if(idValue.length<4||idValue.length>10){
				$("#checkID")
					.html('ID는 4자 이상 10자 이하 만 가능합니다')
					.removeClass('valid')
					.addClass('invalid')
					.addClass('active');
				$("#registerBtn").prop("disabled", true);
				
			}else{
				$.ajax({
					type:"post",
					url:"CheckIdServlet",
					data:$("#registerForm").serialize(),
					success:function(data){
						if(data=="ok"){
							$("#checkID")
								.html('사용가능')
								.removeClass('invalid')
								.addClass('valid')
								.addClass('active');
							$("#registerBtn").prop("disabled", false);
						}
						else{
							$("#checkID")
								.html('중복인 아이디입니다. 다시 입력해주세요')
								.removeClass('valid')
								.addClass('invalid')
								.addClass('active');
							$("#registerBtn").prop("disabled", true);
							
						}
					}
				});
			}
		});
		$("#email").keyup(function() {
			var emailValue = $(this).val();
			
			if(emailValue.indexOf("@")==-1){
				$("#checkEmail")
					.html('이메일형식을 맞춰주세요')
					.removeClass('valid')
					.addClass('invalid')
					.addClass('active');
				$("#registerBtn").prop("disabled", true);
				
			}else{
				$.ajax({
					type:"post",
					url:"CheckEmailServlet",
					data:$("#registerForm").serialize(),
					success:function(data){
						if(data=="ok"){
							$("#checkEmail")
								.html('사용가능')
								.removeClass('invalid')
								.addClass('valid')
								.addClass('active');
							$("#registerBtn").prop("disabled", false);
						}
						else{
							$("#checkEmail")
								.html('중복인 이메일입니다. 다시 입력해주세요')
								.removeClass('valid')
								.addClass('invalid')
								.addClass('active');
							$("#registerBtn").prop("disabled", true);
							
						}
					}
				});
			}
		});
		$("#repeatPassword,#password").keyup(function() {
			var password=$("#password").val();
			var repeatPassword=$("#repeatPassword").val();
			if(password!=repeatPassword){
				$("#checkPass")
					.html('비밀번호가 일치하지 않습니다')
					.removeClass('valid')
					.addClass('invalid')
					.addClass('active');
				$("#registerBtn").prop("disabled", true);
				
			}else{
				$("#checkPass")
					.removeClass('invalid')
					.addClass('valid')
					.removeClass('active');
				$("#registerBtn").prop("disabled", false);
				
			}
		});
		
	});
</script>
<main class="container">
	<div class="row justify-content-center">
		<div class="col-12 col-md-7">

		<form class="form-horizontal" id="registerForm" role="form" method="post"
			name="registerForm" action="dispatcher">
			<input type="hidden" name="command" value="register">
			
			<div class="form-group">
				<input type="text" id="id" name="id" placeholder="아이디" required="required" class="form-control">
				<input type="hidden" name="checkAjax" value="idcheck">
				<span class="hint" id="checkID" style="font-weight:bold; font-size:smaller" >
				</span>
			</div>
			
			<div class="form-group">
				<input type="password" id="password" placeholder="비밀번호" name="password" required="required" class="form-control">
			</div>
			<div class="form-group">
				<input type="password" id="repeatPassword" placeholder="비밀번호확인" name="repeatPassword" class="form-control" required="required">
				<span class="hint" id="checkPass" style="color:red;font-weight:bold;font-size:smaller" >
				</span>
			</div> 
			<div class="form-group">
				<input type="text" name="name" class="form-control" placeholder="성명" required="required"> 
			</div> 
			<div class="form-group">
				<input type="email" id="email" name="email" placeholder="이메일" class="form-control" required="required">
				<input type="hidden" name="checkAjax2" value="emailcheck">
				<span class="hint" id="checkEmail" style="font-weight:bold;font-size:smaller" >
				</span>
			</div> 
			<div class="form-group">
				<input type="text" name="address" placeholder="주소" class="form-control" required="required"> 
			</div> 
			<div class="form-group">
				<select name="qid" id="qid" class="form-control">
				<c:forEach items="${requestScope.questionList}" var="list" varStatus="status">
				<option value=${status.index}>${list}</option>
				</c:forEach>
				</select>
				<input type="text" name="answer" class="form-control" placeholder="계정찾기 질문에 대한 답을 입력해주세요" required="required"> 
			</div> 
			<div class="form-group">
                    <input id="registerBtn" type="submit" class="btn btn-primary btn-block" value="Register" disabled="disabled">
            </div>
		</form>
		</div>
	</div>
</main>