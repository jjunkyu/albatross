<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<main class="container-fluid">
	<div class="row justify-content-md-center">
		<div class="col-sm-3">
			<form action="dispatcher?command=loginCheck" method="post" >
				<div class="form-group">
					<label for="userID">UserID</label> 
					<input type="text"
						class="form-control" name="userID" placeholder="Enter ID" required="required">
				</div>
				<div class="form-group">
					<label for="userPassword">Password</label> 
					<input type="password"
						class="form-control" name="userPassword" aria-describedby="checkPW"
						placeholder="Enter Password" required="required">
					<c:choose>
						<c:when test="${sessionScope.failLogin==null}">
						<span id="checkPW" style="color:red;font-weight:bold;font-size:smaller" >
						아이디 또는 비밀번호를 다시 확인하세요.
						</span>
						</c:when>
					</c:choose>
				</div>
				<button type="submit" class="btn btn-primary">login</button>
			<button type="button" class="btn btn-primary" onclick="memberfind()">아이디/비번찾기 </button>
				
			</form>
		
		</div>
	</div>
</main>