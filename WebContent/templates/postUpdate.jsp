<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table class="table-condensed">
<tr>
<form action="">
<td>제목 <input type="text" id="post_title"  placeholder="${requestScope.PostVO.title }"></td>
<td>내용<input type="text" id="post_content" placeholder="${requestScope.PostVO.content}"></td>

</form>
</tr>
</tr>
</table>

</body>
</html>