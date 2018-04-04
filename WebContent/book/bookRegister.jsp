<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<main class="container">
<div class="row justify-content-md-center">
	<form action="dispatcher" method="post" enctype="multipart/form-data">
		<input type="hidden" name="command" value="bookRegister">
		<table class="table">
			<tr>
				<td colspan="2">제목 &nbsp; <input type="text" name="title" required="required"></td>
			</tr>
			<tr>
				<td>저자 &nbsp; <input type="text" name="author" required="required"></td>
				<td>출판사 &nbsp; <input type="text" name="publisher" required="required"></td>
			</tr>
			<tr>
				<td>
					<label for="imagePath">책표지 업로드</label>
					<input type="file" name="imagePath" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div><span>내용</span></div>
					<textarea cols="90" rows="15" name="content"required="required" > </textarea>
				</td>
			</tr>
		</table>
		<div class="btnArea">
			<span style="float: right"><button type="reset" class="btn" >취소</button></span>
			<span style="float: right"><button type="submit" class="btn">확인</button></span>
		</div>
	</form>
</div>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<main class="container">
<div class="row justify-content-md-center">
	<form action="dispatcher" method="post">
		<input type="hidden" name="command" value="bookRegister">
		<table class="table">
			<tr>
				<td colspan="2">제목 &nbsp; <input type="text" name="title" required="required"></td>
			</tr>
			<tr>
				<td>저자 &nbsp; <input type="text" name="author" required="required"></td>
				<td>출판사 &nbsp; <input type="text" name="publisher" required="required"></td>
			</tr>
			<tr>
				<td colspan="2"><textarea cols="90" rows="15" name="content"required="required" > </textarea></td>
			</tr>
		</table>
		<div class="btnArea">
			<span style="float: right"><button type="reset" class="btn" >취소</button></span>
			<span style="float: right"><button type="submit" class="btn">확인</button></span>
		</div>
	</form>
</div>
>>>>>>> branch 'master' of https://github.com/Jaysok/albatross.git
</main>