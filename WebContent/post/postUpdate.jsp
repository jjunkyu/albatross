<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<main class="container">
	<div class="row justify-content-md-center">
		<form action="dispatcher" method="post">
			<input type="hidden" name="command" value="postUpdate">
			<input type="hidden" name="pNo" value="${requestScope.PostVO.pNo}">		
			<table class="table">
				<tr>
					<td>
						<label for="title">
							제목
						</label>
						<input 
							type="text" 
							name="title" 
							placeholder="제목을 입력하세요" 
							value="${requestScope.PostVO.title}" 
							required="required">
					</td>
				</tr>
			<tr>
				<td>
					<textarea cols="90" rows="15" name="content" required="required" placeholder="내용을 입력하세요">${requestScope.PostVO.content}</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="5">
					<button type="submit" class="btn btn-primary">확인</button>
					<button type="reset" class="btn btn-primary">취소</button>				
				</td>
			</tr>
		</table>

	</form>
	</div>
</main>