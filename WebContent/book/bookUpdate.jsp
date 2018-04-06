<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<main class="container">
	<div class="row book-summary">
		<div class="col-sm-3 book-thumb">
			<img src="${bookVO.imagePath}" />
		</div>
		<div class="col-sm-9 book-info">
			<form action="dispatcher" method="post">
				<input type="hidden" name="bNo" value="${requestScope.bookVO.bNo}">
				<input type="hidden" name="command" value="bookUpdate">
				<div class="title-wrapper">
					<div class="caption title"><span>제목 : </span></div>
					<div class="text title">
						<input class="form-control" type="text" name="title" value="${bookVO.title}" />
					</div>
				</div>
				<div class="author-wrapper">
					<div class="caption author"><span>저자 : </span></div>
					<div class="text author">
						<input class="form-control" type="text" name="author" value="${bookVO.author}" />
					</div>
				</div>
				<div class="publisher-wrapper">
					<div class="caption publisher"><span>출판사 : </span></div>
					<div class="text publisher">
						<input class="form-control" type="text" name="publisher" value="${bookVO.publisher}" />
					</div>
				</div>
				<div class="content-wrapper">
					<div class="caption content"><span>내용 : </span></div>
					<div class="text content">
						<textarea class="form-control" cols="90" rows="15" name="content" required="required">${bookVO.content}</textarea>
					</div>
				</div>
				<div class="button-wrapper">
					<button class="form-control" type="submit">수정</button>
				</div>
			</form>
		</div>
	</div>
</main>

