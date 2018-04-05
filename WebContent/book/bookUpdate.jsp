<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<form action="dispatcher">
	<input type="hidden" name="bNo" value="${requestScope.bookVO.bNo}">
	<input type="hidden" name="command" value="bookUpdate">
	<textarea cols="90" rows="15" name = "content" required="required" placeholder="본문내용">
	</textarea>
	<input type="submit" value="수정">
</form>

