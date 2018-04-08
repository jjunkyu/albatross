<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">
function memberfind(){
	location.href='dispatcher?command=memberFindView';
}
$(document).ready(function() {
	$("#loginBts").click(function() {
		$.ajax({
			type:"post",
			url:"LoginServlet",
			data:$("#loginForm").serialize(),
			success:function(data){
				if(data=="ok"){
					location.href="index.jsp";
				}else{
					$("#checkPW").html('아이디 또는 비밀번호를 다시 확인하세요.');
					$("#userPassword").val('');
				}
			}
		});
	});
	$('#userPassword').on('keypress', function(e) {
		if(e.which == 13) {
			$('#loginBts').trigger('click');
		}
	});
});
</script>


<main class="container">
	<div class="row justify-content-md-center">
		<div class="col-sm-3">
			<form  id="loginForm" >
			<input type="hidden" name="checkAjaxlogin" value="logincheck">
				<div class="form-group">
					<label for="userID">UserID</label> 
					<input type="text"
						class="form-control" name="userID" placeholder="ID를 입력해주세요" required="required">
				</div>
				<div class="form-group">
					<label for="userPassword">Password</label> 
					<input type="password"
						class="form-control" name="userPassword" id="userPassword" aria-describedby="checkPW"
						placeholder="비밀번호를 입력해주세요" required="required">
					<span id="checkPW" style="font-weight:bold; font-size:smaller; color:red"></span>
				</div>
				<div class="login-btn-wrapper">
					<button type="button" class="btn btn-primary" id="loginBts">login</button>
				</div>
				<div class="find-password">
					<small>
						<a href="dispatcher?command=memberFindView">아이디/비번찾기 </a>
					</small>
				</div>
			</form>
		</div>
	</div>
</main>