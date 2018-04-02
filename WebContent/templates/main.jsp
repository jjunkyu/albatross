<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<main class="container-fluid">
    <section class="search-jumbotron">
		<img class="main-image" src="${pageContext.request.contextPath}/static/media/jumbotron.jpg" />
		<div class="search-bar">
			<form id="book-search" class="form-inline" action="dispatcher">
				<input type="hidden" name="command" value="BookSearch" />
				<select class="form-control" name="by">
					<option value="title">제목</option>
					<option value="author">저자</option>
					<option value="mixed">제목 or 저자</option>
				</select>
				<input class="form-control" type="text" placeholder="도서검색" name="value"/>
				<button class="form-control" type="submit">검색</button>
			</form>
		</div>
	</section>
</main>