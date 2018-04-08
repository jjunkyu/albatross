<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<main class="container">
<div class="row justify-content-md-center">
	<form action="dispatcher" method="post">
		<input type="hidden" name="command" value="postWrite">
		<table class="table">
			<tr>
				<td>
					<label for="title">제목</label>
					<input type="text" name="title" class="form-control" placeholder="게시글 제목을 입력하세요" required="required">
				</td>
			</tr>
			<tr>
				<td>
					<label for="content">내용</label>
					<textarea cols="90" rows="15" name="content" class="form-control" required="required" placeholder="본문내용을 입력하세요"></textarea>
				</td>
			</tr>
			<tr class="controls">
				<td colspan="5">
					<button type="submit" class="btn btn-primary" >확인</button>
					<button type="reset" class="btn btn-primary">취소</button>
				</td>
			</tr>
		</table>
	</form>
</div>
</main>