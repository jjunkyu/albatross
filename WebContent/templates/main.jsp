<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main class="container">
    <section class="search-jumbotron">
		<img class="main-image" src="static/media/jumbotron.jpg" />
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
	<section class="best-books-section">
		<h2><span>이달의 책</span></h2>
		<ul class="main-carousel best-books">
			<c:forEach items="${books}" var="book">
			<li class="best-book-item carousel-cell">
				<img src="${book.imagePath}" />
				<div class="book-info">
					<div class="book-title"><span>${book.title}</span></div>
					<div class="book-author"><span>${book.author}</span></div>
					<div class="book-publisher"><span>${book.publisher}</span></div>
				</div>
			</li>
			</c:forEach>
		</ul>
	</section>
</main>
<script type="text/javascript" src="static/js/jquery.js"></script>
<script type="text/javascript" src="static/js/slick.js"></script>
<script>
$(document).ready(function() {
	var slider = $('.main-carousel').slick({
		infinite: true,
		slidesToShow: 3
	});
});
</script>