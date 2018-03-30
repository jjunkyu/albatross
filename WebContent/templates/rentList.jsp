<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
function returnBook(){
	if(confirm("책 반납함??")){
		document.getElementById("returnForm").submit();
	}
}
</script>
<div class="row">
	<div class="col-sm-12">
	<h2 align="center">${sessionScope.loginVO.id}님의 빌린 책 목록</h2>
		<table class="table">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>저자</th>
					<th>출판사</th>
					<th>반납</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.rentList}" var="bookVO">
				<tr>
					<td>${bookVO.bNo}</td>
					<td>${bookVO.title}</td>
					<td>${bookVO.author}</td>
					<td>${bookVO.publisher}</td>
					<td class="btnArea">
						<form id="returnForm" action="${pageContext.request.contextPath}/dispatcher">
						<input type="hidden" name="command" value="returnBook">
						<input type="hidden" name="bNo" value="${bookVO.bNo}">
						<input type="hidden" name="isRented" value="${bookVO.rented}">
						</form>
						<button type="button" class="btn" onclick="returnBook()">반납</button>
					</td>
					<td>${bookVO.rented}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>