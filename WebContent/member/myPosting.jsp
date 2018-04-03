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
</div>