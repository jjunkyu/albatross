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
				if(data=='ok'){
					alert(1);
				}else{
					$("#checkPW").text("아이디 또는 비밀번호를 다시 확인하세요.").css("color","red","font-weight","bold","font-size","smaller");
					$("#userPassword").val("");
				}
			}
		});
	});
});
</script>
<main class="container-fluid">
	<div class="row justify-content-md-center">
		<div class="col-sm-3">
			<form action="dispatcher?command=loginCheck" method="post" id="loginForm" >
			<input type="hidden" name="checkAjaxlogin" value="logincheck">
				<div class="form-group">
					<label for="userID">UserID</label> 
					<input type="text"
						class="form-control" name="userID" placeholder="Enter ID" required="required">
				</div>
				<div class="form-group">
					<label for="userPassword">Password</label> 
					<input type="password"
						class="form-control" name="userPassword" id="userPassword" aria-describedby="checkPW"
						placeholder="Enter Password" required="required">
					<c:choose>
						<c:when test="${sessionScope.failLogin==false}">
						<span id="checkPW" >
						
						</span>
						</c:when>
					</c:choose>
				</div>
				<button type="submit" class="btn btn-primary" id="loginBts">login</button>
			<button type="button" class="btn btn-primary" onclick="memberfind()">아이디/비번찾기 </button>
				
			</form>
		
		</div>
	</div>
</main>