<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<main class="container">
	<div class="row justify-content-md-center">
		<div class="col-sm-3">
			<span id="checkResult">ID : ${requestScope.mvo.id }<br>	PassWord: ${requestScope.mvo.password}</span>
		</div>
	</div>
</main>
<script type="text/javascript">
var flag = ${requestScope.mvo.id==null}
$(document).ready(function(){
	if(flag){
		$.ajax({
			type : "post" ,
			url : "memberId_pwCheckServlet",			
			data : "command=idpwcheck&flag="+flag,
			success : function(data) {//data 는 json 객체가 전달
				if(data=="ok")
				$("#checkResult").text("존재하는 아이디가 없습니다").css("color","red");
		}
	})//ajax
	}//if
 })//ready
</script>