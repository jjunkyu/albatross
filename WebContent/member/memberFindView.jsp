<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<main class="container">
	<div class="row justify-content-md-center">
		<div class="col-9 col-md-5">
			<form action="dispatcher" method="post">
				<input type="hidden" name="command" value="memberFind" />
				<div class="form-group">
					<label for="email">E-Mail</label>
					<input type="email" name="email" class="form-control" placeholder="회원가입시 등록한 이메일" required="required">
				</div>
				<div class="form-group">
					<label for="userPassword">질문</label> 
					<select name="question" class="form-control">
					<c:forEach items="${requestScope.list }" var="questionList" varStatus="qId">
						<option value=${qId.index }>${questionList }</option>
					</c:forEach>
					</select>
				 	<input type="text" class="form-control" name="question_answer" placeholder="답변" required="required">
				</div>
				<button type="submit" class="btn btn-primary">찾기</button>
			</form>
		</div>
	</div>
</main>