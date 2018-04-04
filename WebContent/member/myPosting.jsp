<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<main class="container">
<div class="row">
	<div class="col-sm-12">
		<h2 align="center">${sessionScope.loginVO.name}&nbsp;님의 작성 글 목록</h2>
		<div class="board-wrapper">
			<table class="table">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${requestScope.listVO.postList}" var="list">
						<tr>
							<td>${list.pNo}</td>
							<td>${list.title }</td>
							<td>${list.memberVO.id }</td>
							<td>${list.timePosted}</td>
							<td>${list.hits}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="pagingInfo row pagingInfo justify-content-center">
				<c:set var="pb" value="${requestScope.listVO.pagingBean}"></c:set>
		
				<ul class="pagination">
					<c:if test="${pb.previousPageGroup}">
						<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/dispatcher?command=myPosting&pNo=${pb.startPageOfPageGroup-1}">&laquo;</a></li>
					</c:if>
					<c:forEach var="i" begin="${pb.startPageOfPageGroup}"
						end="${pb.endPageOfPageGroup}">
						<c:choose>
							<c:when test="${pb.nowPage!=i}">
								<li class="page-item"><a class="page-link" href="dispatcher?command=myPosting&pNo=${i}">${i}</a></li>
							</c:when>
							<c:otherwise>
								<li class="active page-item"><a class="page-link" href="#">${i}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${pb.nextPageGroup}">
						<li class="page-item"><a class="page-link" href="dispatcher?command=myPosting&pNo=${pb.endPageOfPageGroup+1}">&raquo;</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
</div>
</main>