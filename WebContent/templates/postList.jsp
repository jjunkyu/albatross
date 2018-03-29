<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<main class="container-fluid">
	<div class="row">
		<div class="col-sm-12">
			<div class="board-wrapper">
				<table class="table">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>조회수</th>
							<th>작성일</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${requestScope.list}" var="list">
						<tr>
							<td>${book.bNo}</td>
							<td><a href="dispatcher?command=bookDetail&bNo=${book.bNo}">${book.title}</a></td>
							<td>${book.author}</td>
							<td>${book.publisher}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>		
		</div>	
	</div>
</main>