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
				$("#checkID").html('ID는 4자 이상 10자 이하 만 가능합니다').css("color","red");
				$("#registerBtn").prop("disabled", true);
				
			}else{
				$.ajax({
					type:"post",
					url:"CheckIdServlet",
					data:$("#registerForm").serialize(),
					success:function(data){
						if(data=="ok"){
							$("#checkID").html('사용가능').css("color","black");
							$("#registerBtn").prop("disabled", false);
						}
						else{
							$("#checkID").html('중복인 아이디입니다. 다시 입력해주세요').css("color","red");
							$("#registerBtn").prop("disabled", true);
							
						}
					}
				});
			}
		});
		$("#email").keyup(function() {
			var emailValue = $(this).val();
			
			if(emailValue.indexOf("@")==-1){
				$("#checkEmail").html('이메일형식을 맞춰주세요').css("color","red");
				$("#registerBtn").prop("disabled", true);
				
			}else{
				$.ajax({
					type:"post",
					url:"CheckEmailServlet",
					data:$("#registerForm").serialize(),
					success:function(data){
						if(data=="ok"){
							$("#checkEmail").html('사용가능').css("color","black");
							$("#registerBtn").prop("disabled", false);
						}
						else{
							$("#checkEmail").html('중복인 이메일입니다. 다시 입력해주세요').css("color","red");
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

		<form class="form-horizontal" id="registerForm" role="form" method="post"
			name="registerForm" action="dispatcher?command=register">
			
			<h3>회원가입</h3>
			<hr>
			<div class="form-group">
				<label class="col-sm-6 control-label">아이디 :</label>
				<div class="col-sm-9">
				<input type="text" id="id" name="id" required="required">
				<input type="hidden" name="checkAjax" value="idcheck"><br>
				<span id="checkID" style="font-weight:bold; font-size:smaller" >
				</span>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-6">패스워드 :</label>
				<div class="col-sm-9">
					<input type="password" id="password" name="password" required="required">
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
				<input type="text" name="name" required="required"> 
				</div>
			</div> 
			<div class="form-group">
				<label class="control-label col-sm-6">email : </label>
				<div class="col-sm-9">
				<input type="email" id="email" name="email" required="required">
				<input type="hidden" name="checkAjax2" value="emailcheck"><br>
				<span id="checkEmail" style="font-weight:bold;font-size:smaller" >
				</span>
				</div>
			</div> 
			<div class="form-group">
				<label class="control-label col-sm-6">주소 : </label>
				<div class="col-sm-9">
				<input type="text" name="address" required="required"> 
				</div>
			</div> 
			<div class="form-group">
				<label class="control-label col-sm-6">질문 : </label>
				<div class="col-sm-3">
				<select name="qid">
				<c:forEach items="${requestScope.questionList}" var="list" varStatus="status">
				<option value=${status.index}>${list}</option>
				</c:forEach>
				</select>
				</div>
				<div class="col-sm-6">
				<input type="text" name="anwer" required="required"> 
				</div>
			</div> 
			<div class="form-group">
                    <div class="col-sm-9 col-sm-offset-3">
                        <input id="registerBtn" type="submit" class="btn btn-primary btn-block" value="Register" disabled="disabled">
                    </div>
                </div>
		</form>
		</div>
	</div>
</main>