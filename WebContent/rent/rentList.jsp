<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
function returnBook(){
	if(confirm("선택한 책을 반납 하시겠습니까?")){
		document.getElementById("returnForm").submit();
	}
}
</script>
<div class="row">
	<div class="col-sm-12">
		<h2 align="center">${sessionScope.loginVO.id}님의빌린책 목록</h2>
		<table class="table">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>저자</th>
					<th>출판사</th>
					<th>대여일</th>
					<th>반납일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.rentList}" var="rentVO">
					<tr>
						<td>${rentVO.bookVO.bNo}</td>
						<td>${rentVO.bookVO.title}</td>
						<td>${rentVO.bookVO.author}</td>
						<td>${rentVO.bookVO.publisher}</td>
						<td>${rentVO.rentDate}시</td>
						<c:choose>
							<c:when test="${rentVO.returnDate == null}">
								<td class="btnArea">
									<form id="returnForm" action="${pageContext.request.contextPath}/dispatcher">
										<input type="hidden" name="command" value="returnBook">
										<input type="hidden" name="bNo" value="${rentVO.bookVO.bNo}">
										<input type="hidden" name="isRented" value="${rentVO.bookVO.rented}">
										<input type="hidden" name="rId" value="${rentVO.rId}">
									</form>
									<button type="button" class="btn" onclick="returnBook()">반납</button>
								</td>
							</c:when>
							<c:otherwise>
								<td>${rentVO.returnDate}시</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>