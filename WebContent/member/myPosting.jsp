<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-sm-12">
	<h2 align="center">${sessionScope.loginVO.name}&nbsp;님의 작성 글 목록</h2>
		<table class="table">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.list}" var="myPosting">
				<tr>
					<td>${myPosting.pNo}</td>
					<td><a href="${pageContext.request.contextPath}/dispatcher?command=postDetail&pNo=${myPosting.pNo }">
					       ${myPosting.title}</a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="pagingInfo row pagingInfo justify-content-center">
				<c:set var="pb" value="${requestScope.listVO.pagingBean}"></c:set>
		
				<ul class="pagination">
					<c:if test="${pb.previousPageGroup}">
						<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/dispatcher?command=postList&pageNo=${pb.startPageOfPageGroup-1}">&laquo;</a></li>
					</c:if>
					<c:forEach var="i" begin="${pb.startPageOfPageGroup}"
						end="${pb.endPageOfPageGroup}">
						<c:choose>
							<c:when test="${pb.nowPage!=i}">
								<li class="page-item"><a class="page-link" href="dispatcher?command=postList&pageNo=${i}">${i}</a></li>
							</c:when>
							<c:otherwise>
								<li class="active page-item"><a class="page-link" href="#">${i}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${pb.nextPageGroup}">
						<li class="page-item"><a class="page-link" href="dispatcher?command=postList&pageNo=${pb.endPageOfPageGroup+1}">&raquo;</a></li>
					</c:if>
				</ul>
			</div>
</div>