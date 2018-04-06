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
function deleteBook(){
	if(confirm("책을 삭제하시겠습니까?")){
		document.deleteForm.submit();
	}
}

function updateBook(){
	//document.updateForm.submit();
	location.href = 'dispatcher?command=bookUpdateView&bNo=${bookVO.bNo}';
}
</script>
<main class="container">
	<div class="row book-summary">
		<div class="col-sm-3 book-thumb">
			<img src="${bookVO.imagePath}" />
		</div>
		<div class="col-sm-9 book-info">
			<div class="title-wrapper">
				<div class="caption title"><span>제목 : </span></div>
				<div class="text title"><span>${bookVO.title}</span></div>
			</div>
			<div class="author-wrapper">
				<div class="caption author"><span>저자 : </span></div>
				<div class="text author"><span>${bookVO.author }</span></div>
			</div>
			<div class="publisher-wrapper">
				<div class="caption publisher"><span>출판사 : </span></div>
				<div class="text publisher"><span>${bookVO.publisher }</span></div>
			</div>
			<div class="content-wrapper">
				<div class="caption content"><span>내용 : </span></div>
				<div class="text content">
					<p>${bookVO.content }</p>
				</div>
			</div>
		</div>
	</div>
	<div class="controls">
		<c:choose>
			<%-- admin : 책 삭제 버튼만 보여주기 --%>
			<c:when test="${sessionScope.loginVO.cId == '1'}">
			<form name="deleteForm"	action="dispatcher" method="post">
				<input type="hidden" name="command" value="deleteBook">
				<input type="hidden" name="bNo" value="${bookVO.bNo}">
			</form>
			<span onclick="deleteBook()">삭제</span>		
			<form name="updateForm" action="dispatcher" method="post">
				<input type="hidden" name="command" value="bookUpdateView">
				<input type="hidden" name="bNo" value="${bookVO.bNo}">
			</form>
			<span onclick="updateBook()">수정</span>
			</c:when>
			<c:otherwise>
			<%-- 일반 회원 : 대여 버튼만 보여주기 --%>
			<form name="rentForm" action="dispatcher" method="post">
				<input type="hidden" name="command" value="rentBook">
				<input type="hidden" name="bNo" value="${bookVO.bNo}"> 
				<input type="hidden" name="isRented" value="${bookVO.rented}">
			</form>
			<span onclick="rentBook()">대여</span>		
			</c:otherwise>
		</c:choose>
		</div>
	</div>
</main>