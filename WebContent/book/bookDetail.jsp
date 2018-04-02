<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
function rentBook(){
	if(${sessionScope.loginVO==null}){
		alert("로그인 이용자만 가능합니다");
		location.href="dispatcher?command=home";
	}else{
		if(confirm("책을 대여하겠습니까?")){
			if(${bookVO.rented}){
				alert("이미 대여중인 책입니다");
			}else{
				document.rentForm.submit();		
			}
		}
	}
}
</script>
<div class="row">
	<div class="col-sm-12">
		<table class="table">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>저자</th>
					<th>출판사</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${bookVO.bNo}</td>
					<td>${bookVO.title}</td>
					<td>${bookVO.author}</td>
					<td>${bookVO.publisher}</td>
				</tr>
				<tr>
					<td colspan="5" class="content">
						<pre>${bookVO.content}</pre>
					</td>
				</tr>
				<tr>
					<td colspan="5" class="btnArea">
						<form name="rentForm" action="dispatcher">
						<input type="hidden" name="command" value="rentBook">
						<input type="hidden" name="bNo" value="${bookVO.bNo}">
						<input type="hidden" name="isRented" value="${bookVO.rented}">
						</form>
						<button type="button" class="btn" onclick="rentBook()">대여</button>
					</td>
				</tr>
				
			</tbody>
		</table>
	</div>
</div>