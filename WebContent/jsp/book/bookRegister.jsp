<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<main class="container">
	<div class="row justify-content-md-center">
	<form action="dispatcher" method="post" enctype="multipart/form-data">
		<input type="hidden" name="command" value="bookRegister">
		<table class="table">
			<tr>
				<td colspan="2">
					<label for="title">제목</label>
					<input class="form-control" type="text" name="title" required="required">
				</td>
			</tr>
			<tr>
				<td>
					<label for="author">저자</label>
					<input class="form-control" type="text" name="author" required="required">
				</td>
			</tr>
			<tr>
				<td>
					<label for="publisher">출판사</label>
					<input class="form-control" type="text" name="publisher" required="required">
				</td>
			</tr>
			<tr>
				<td>
					<label for="imagePath">책표지 업로드</label>
					<input class="form-control" type="file" name="imagePath" required="required" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<label for="content">내용</label>
					<textarea class="form-control" cols="90" rows="15" name="content" required="required"></textarea>
				</td>
			</tr>
			<tr class="controls">
				<td>
					<span><button type="reset" class="btn btn-primary" >취소</button></span>
					<span><button type="submit" class="btn btn-primary">확인</button></span>
				</td>
			</tr>
		</table>
	</form>
	</div>
</main>