<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<main class="container-fluid">
<div class="row">
	<div class="col-sm-12">
		<div class="board-wrapper">
		<form action="dispatcher">
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
					<c:forEach items="${requestScope.list}" var="list">
						<tr>
							<td>${list.pNo}</td>
							<td>
							<c:choose>
							<c:when test="${sessionScope.loginVO!=null }">
							<a href="${pageContext.request.contextPath}/dispatcher?command=showDetailContent&pNo=${list.pNo }">
							${list.title}</a>
							</c:when>
							<c:otherwise>
							${list.title }
							</c:otherwise>
							</c:choose>
							</td>
							<td>${list.memberVO.id }</td>
							<td>${list.timePosted}</td>
							<td>${list.hits}</td>
						</tr>
					</c:forEach>
					<c:choose>
					<c:when test="${sessionScope.loginVO!=null }">
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td><input type="submit" class="btn btn-primary btn-xs" value="글쓰기">
							<input type="hidden" name="command" value="postWriteView">
							</td>
						</tr>
					</c:when>
					</c:choose>
				</tbody>
			</table>
			</form>
			<div class="pagingInfo">
				<c:set var="pb" value="${requestScope.listVO.pagingBean}"></c:set>
		
				<ul class="pagination">
					<c:if test="${pb.previousPageGroup}">
						<li><a href="dispatcher?command=postList&pageNo=${pb.startPageOfPageGroup-1}">&laquo;</a></li>
					</c:if>
					<c:forEach var="i" begin="${pb.startPageOfPageGroup}"
						end="${pb.endPageOfPageGroup}">
						<c:choose>
							<c:when test="${pb.nowPage!=i}">
								<li><a href="dispatcher?command=postList&pageNo=${i}">${i}</a></li>
							</c:when>
							<c:otherwise>
								<li class="active"><a href="#">${i}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${pb.nextPageGroup}">
						<li><a href="dispatcher?command=postList&pageNo=${pb.endPageOfPageGroup+1}">&raquo;</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
</div>
</main>