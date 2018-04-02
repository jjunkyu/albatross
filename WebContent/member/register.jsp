<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	function checkForm() {
		var f = document.registerForm;
		if (f.password.value != f.repeatPassword.value) {
			alert("패스워드와 패스워드 확인이 일치하지 않습니다");
			return false;
		}
		if (f.flag.value != f.id.value) {
			alert("인증받은 아이디가 아닙니다\n아이디 중복확인을 하세요");
			return false;
		}
	}
	function checkId() {
		var str = document.registerForm.id.value;
		if (str == "") {
			alert("아이디를 입력하세요!");
		} else {
			window.open("dispatcher?command=idcheck&id=" + str, "popup",
					"width=200,height=200,top=150,left=400");
		}
	}
</script>
<main class="container-fluid">
	<div class="row justify-content-md-center">
		<div class="col-sm-9">

		<form class="form-horizontal" id="registerForm" role="form" method="post"
			name="registerForm" action="dispatcher" onsubmit="return checkForm()">
			<input type="hidden" name="command" value="register">
			<h3>회원가입</h3>
			<hr>
			<div class="form-group">
				<label class="col-sm-6 control-label">아이디 :</label>
				<div class="col-sm-9">
					<input type="text" name="id" required="required"> <input
						type="button" value="중복확인" onclick="checkId()">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-6">패스워드 :</label>
				<div class="col-sm-9">
					<input type="password" name="password" required="required">
					</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-6">패스워드 확인 : </label>
				<div class="col-sm-9">
				<input type="password" name="repeatPassword" required="required">
				</div>
			</div> 
			<div class="form-group">
				<label class="control-label col-sm-6">이름 : </label>
				<div class="col-sm-9">
				<input type="text" name="name" required="required"> 
				</div>
			</div> 
			<div class="form-group">
				<label class="control-label col-sm-6">주소 : </label>
				<div class="col-sm-9">
				<input type="text" name="address" required="required"> 
				<input type="hidden" name="flag" value="">
				</div>
			</div> 
			<div class="form-group">
                    <div class="col-sm-9 col-sm-offset-3">
                        <button type="submit" class="btn btn-primary btn-block">Register</button>
                    </div>
                </div>
		</form>
		</div>
	</div>
</main>