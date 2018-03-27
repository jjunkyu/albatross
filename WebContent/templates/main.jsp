<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<main class="container-fluid">
    <section class="search-jumbotron">
		<img class="main-image" src="${pageContext.request.contextPath}/static/media/jumbotron.jpg" />
		<div class="search-bar">
			<form id="book-search" class="form-inline" action="">
				<select class="form-control">
					<option>제목</option>
					<option>저자</option>
					<option>제목 or 저자</option>
				</select>
				<input class="form-control" type="text" placeholder="도서검색" />
				<button class="form-control" type="button">검색</button>
			</form>
		</div>
	</section>
</main>