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
<main class="container">
<div class="row">
	<div class="col-sm-12">
		<h2 align="center">${sessionScope.loginVO.id}님의 빌린책 목록</h2>
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
				<c:forEach items="${requestScope.listVO.rentList}" var="rentVO">
					<tr>
						<td>${rentVO.bookVO.bNo}</td>
						<td><a href="dispatcher?command=bookDetail&bNo=${rentVO.bookVO.bNo}">${rentVO.bookVO.title}</a></td>
						<td>${rentVO.bookVO.author}</td>
						<td>${rentVO.bookVO.publisher}</td>
						<td>${rentVO.rentDate}</td>
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
								<td>${rentVO.returnDate}</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<div class="row pagingInfo justify-content-center">
	<c:set var="pb" value="${requestScope.listVO.pagingBean}"></c:set>
	<ul class="pagination">
		<c:if test="${pb.previousPageGroup}">
			<li class="page-item"><a class="page-link"
				href="dispatcher?command=rentList&pNo=${pb.startPageOfPageGroup-1}">&laquo;</a></li>
		</c:if>
		<c:forEach begin="${pb.startPageOfPageGroup}"
			end="${pb.endPageOfPageGroup}" var="pop">
			<c:choose>
				<c:when test="${pb.nowPage != pop}">
					<li class="page-item"><a class="page-link" href="dispatcher?command=rentList&pNo=${pop}">${pop}</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item active"><a class="page-link" href="javascript:;">${pop}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${pb.nextPageGroup}">
			<li class="page-item"><a class="page-link"
				href="dispatcher?command=rentList&pNo=${pb.endPageOfPageGroup+1}">&raquo;</a></li>
		</c:if>
	</ul>
</div>
</main>