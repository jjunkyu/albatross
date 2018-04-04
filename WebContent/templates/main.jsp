<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<main class="container-fluid">
    <section class="search-jumbotron">
		<img class="main-image" src="${pageContext.request.contextPath}/static/media/jumbotron.jpg" />
		<div class="search-bar">
			<form id="book-search" class="form-inline" action="dispatcher">
				<select class="form-control" name="command">
					<option value="bookSearchByTitle">제목</option>
					<option value="bookSearchByAuthor">저자</option>
					<option value="bookSearchByMix">제목 or 저자</option>
				</select>
				<input class="form-control" type="text" placeholder="도서검색" name="value"/>
				<button class="form-control" type="submit">검색</button>
			</form>
		</div>
	</section>
</main>