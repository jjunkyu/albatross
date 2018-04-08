<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<main class="container">
<div class="row">
	<div class="col-sm-12">
<table class="table">
			<thead>
				<tr>
					<th class="book-number">번호</th>
					<th class="book-thumb">표지</th>
					<th class="book-title">제목</th>
					<th class="book-author">저자</th>
					<th class="book-publisher">출판사</th>
					<th class="book-is-rented">대여상태<th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.listVO.bookList}" var="book">
					<tr class="book-item" data-bNo="${book.bNo}">
						<td class="book-number">${book.bNo}</td>
						<td class="book-thumb"><img src="${book.imagePath}"/></td>
						<td class="book-title"><a href="dispatcher?command=bookDetail&bNo=${book.bNo}">${book.title}</a></td>
						<td class="book-author">${book.author}</td>
						<td class="book-publisher">${book.publisher}</td>
						<td class="book-is-rented">
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
		<c:if test="${sessionScope.loginVO.cId=='1'}">
		<form action="dispatcher" method="post">
			<span style="float: right"><input type="submit" class="btn btn-primary btn-xs" value="책등록"> </span>
				 <input type="hidden" name="command" value="bookRegisterView">
		</form>
		</c:if>
	</div>
</div>
<div class="row pagingInfo justify-content-center">
	<c:set var="pb" value="${requestScope.listVO.pagingBean}"></c:set>
	<ul class="pagination">
		<c:if test="${pb.previousPageGroup}">
			<li class="page-item"><a class="page-link"
				href="dispatcher?command=${requsetScope.command}&pNo=${pb.startPageOfPageGroup-1}&value=${requestScope.command}">&laquo;</a></li>
		</c:if>
		<c:forEach begin="${pb.startPageOfPageGroup}"
			end="${pb.endPageOfPageGroup}" var="pop">
			<c:choose>
				<c:when test="${pb.nowPage != pop}">
					<li class="page-item"><a class="page-link" href="dispatcher?command=${requestScope.command}&pNo=${pop}&value=${requestScope.value}">${pop}</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item active"><a class="page-link" href="javascript:;">${pop}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${pb.nextPageGroup}">
			<li class="page-item"><a class="page-link"
				href="dispatcher?command=${requestScope.command}&pNo=${pb.endPageOfPageGroup+1}&value=${requestScope.value}">&raquo;</a></li>
		</c:if>
	</ul>
</div>
</main>
<script>
	$(document).ready(function(){
		$('.book-item').click(function(){
			location.href='dispatcher?command=bookDetail&bNo=' + $(this).data('bno');
		});
	});
</script>