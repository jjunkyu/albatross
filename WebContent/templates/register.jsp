<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
var idCheck = 0;
var pwdCheck = 0;
/*
 * 패스워드 체크
 */
function checkPwd() {
	var pw=$("#pw").val();
	var rpw=$("#rpw").val();
	if(rpw=="" && (pw != rpw || pw == rpw)){
        $("#btn").prop("disabled", true);
        $("#btn").css("background-color", "#aaaaaa");
    }else if (pw == rpw) {
        pwdCheck = 1;
        if(idCheck==1 && pwdCheck == 1) {
            $("#btn").prop("disabled", false);
            $("#btn").css("background-color", "#4CAF50");
            signupCheck();
        }
    } else if (pw != rpw) {
        pwdCheck = 0;
        $("#btn").prop("disabled", true);
        $("#btn").css("background-color", "#aaaaaa");
    }
}
function checkId() {
	
}
/*
 * 이름과 주소 입력안했을 때 가입버튼 비활성화
 */
function signupCheck() {
    var name = $("#name").val();
    var address = $("#address").val();
    if(name=="" || address=="") {
    	$("#btn").prop("disabled", true);
        $("#btn").css("background-color", "#aaaaaa");
    } else {
    }
}

</script>

<div class="container">
	<form class="form-horizontal" role="form" action="dispatcher"
		method="post" name="joinForm" onsubmit="return checkAll()">
		<input type="hidden" name="command" value="registerSubmit">

		<h2>Registration</h2>
		<%--아이디--%>
		<div class="form-group">
			<label for="inputID" class="col-sm-3 control-label">ID</label>
			<div class="col-sm-9">
				<input type="text" name="inputID" placeholder="ID"
					class="form-control" oninput="checkId()" autofocus>
			</div>
		</div>
		<%--이름--%>
		<div class="form-group">
			<label for="inputID" class="col-sm-3 control-label">Full Name</label>
			<div class="col-sm-9">
				<input type="text" name="inputName" id="name" placeholder="Full Name"
					class="form-control">
			</div>
		</div>
		<%--패스워드--%>
		<div class="form-group">
			<label for="password" class="col-sm-3 control-label">Password</label>
			<div class="col-sm-9">
				<input type="password" name="inputPW" id="pw" placeholder="Password"
					class="form-control" oninput="checkPwd()">
			</div>
		</div>
		<%--패스워드확인--%>
		<div class="form-group">
			<label for="passwordCheck" class="col-sm-3 control-label">Repeat
				Password</label>
			<div class="col-sm-9">
				<input type="password" name="inputPWC" id="rpw" placeholder="Repeat Password" 
				class="form-control" oninput="checkPwd()">
			</div>
			<span id="passwordCheck"></span>
		</div>
		<%--주소--%>
		<div class="form-group">
			<label for="passwordCheck" class="col-sm-3 control-label">Address</label>
			<div class="col-sm-9">
				<input type="text" name="inputAdd" id="address" placeholder="Apartment, studio, or floor"
					class="form-control">
			</div>
		</div>
		<%--가입버튼--%>
		<div class="form-group">
			<div class="col-sm-9 col-sm-offset-3">
				<button type="submit" class="btn btn-primary btn-block" id="btn" disabled="disabled">Register</button>
			</div>
		</div>
	</form>
	<!-- /form -->
</div>
<!-- ./container -->