<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<main class="container-fluid">
<div class="row">
	<div class="col-sm-12">
		<table class="table">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>저자</th>
					<th>출판사</th>
					<th>대여상태<th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.listVO.bookList}" var="book">
					<tr>
						<td>${book.bNo}</td>
						<td><a href="dispatcher?command=bookDetail&bNo=${book.bNo}">${book.title}</a></td>
						<td>${book.author}</td>
						<td>${book.publisher}</td>
						<td>
						<c:choose>
							<c:when test="${book.rented==false}">
							대여가능
							</c:when>
							<c:otherwise>
							대여중
							</c:otherwise>
						</c:choose>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<div class="pagingInfo">
	<c:set var="pb" value="${requestScope.listVO.pagingBean}"></c:set>
	<ul class="pagination">
		<c:if test="${pb.previousPageGroup}">
			<li><a
				href="dispatcher?command=bookList&bNo=${pb.startPageOfPageGroup-1}">&laquo;</a></li>
		</c:if>
		<c:forEach begin="${pb.startPageOfPageGroup}"
			end="${pb.endPageOfPageGroup}" var="pop">
			<c:choose>
				<c:when test="${pb.nowPage != pop}">
					<li><a href="dispatcher?command=bookList&bNo=${pop}">${pop}</a></li>
				</c:when>
				<c:otherwise>
					<li class="active"><a href="#">${pop}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${pb.nextPageGroup}">
			<li><a
				href="dispatcher?command=bookList&bNo=${pb.endPageOfPageGroup+1}">&raquo;</a></li>
		</c:if>
	</ul>
</div>
</main>