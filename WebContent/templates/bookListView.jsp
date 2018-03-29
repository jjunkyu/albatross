<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<main class="container-fluid">
	<div class="row">
		<div class="col-sm-12">
			<table class="table">
				<thead>
					<tr></tr>
				</thead>
				<tbody>
					<c:forEach items="${requestScope.list}" var="book">
						<tr>
							<td>${book.getbNo()}</td>
							<td>${book.getTitle()}</td>
							<td>${book.getAuthor()}</td>
							<td>${book.getPublisher()}</td>
						</tr>
					</c:forEach>
					<tr></tr>
				</tbody>
			</table>
		</div>
	</div>
</main>