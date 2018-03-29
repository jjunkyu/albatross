<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form action="dispatcher?command=loginCheck" method="post">
	<div class="form-group">
		<label for="userID">UerID</label> <input type="text"
			class="form-control" name="userID" placeholder="Enter ID" required="required">
	</div>
	<div class="form-group">
		<label for="userPassword">Password</label> <input type="password"
			class="form-control" name="userPassword" aria-describedby="checkPW"
			placeholder="Enter Password" required="required">
		<c:choose>
			<c:when test="${sessionScope.failLogin==null}">
				<small id="checkPW" class="form-text text-muted">아이디 또는
					비밀번호를 다시 확인하세요.</small>
			</c:when>
		</c:choose>
	</div>
	<button type="submit" class="btn btn-primary">login</button>
</form>