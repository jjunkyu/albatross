<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table class="table">
	<thead>
		<tr>
			<th>글번호 ${requestScope.pvo.pNo }</th>
			<th>제목:${requestScope.pvo.title }</th>
			<th>작성자:${requestScope.pvo.memberVO.name }</th>
			<th>조회수:${requestScope.pvo.hits }</th>
			<th>${requestScope.pvo.timePosted }</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td colspan="5" class="content">
			<pre>${requestScope.pvo.content }</pre>
			</td>
		</tr>
	</tbody>
		<tr>
			<td colspan="5" class="btnArea">
			 <c:if test="${requestScope.pvo.memberVO.id==sessionScope.loginVO.id}">
			 <form name="deleteForm" action="${pageContext.request.contextPath}/dispatcher" method="post">
			 	<input type="hidden" name="command" value="deletePosting">
			 	<input type="hidden" name="pNo" value="${requestScope.pvo.pNo}">
			 </form>
			 <button type="button" class="btn" onclick="deleteBoard()">삭제</button>
			 <button type="button" class="btn" onclick="updateBoard()">수정</button>
			 </c:if>
			 </td>
		</tr>
</table>