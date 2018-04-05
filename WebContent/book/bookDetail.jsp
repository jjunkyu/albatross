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
		document.updateForm.submit();
}
</script>
<div class="container">
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
				<tbody class="book-info">
					<tr>
						<td>${bookVO.bNo}</td>
						<td>${bookVO.title}</td>
						<td>${bookVO.author}</td>
						<td>${bookVO.publisher}</td>
					</tr>
					<tr>
						<td colspan="5" class="content">
							<img src="${bookVO.imagePath}" />
							<pre>${bookVO.content}</pre>
						</td>
					</tr>
					<tr>
						<c:choose>
							<%-- admin : 책 삭제 버튼만 보여주기 --%>
							<c:when test="${sessionScope.loginVO.cId == '1'}">
								<td class="btnArea">
									<form name="deleteForm"
										action="${pageContext.request.contextPath}/dispatcher">
										<input type="hidden" name="command" value="deleteBook">
										<input type="hidden" name="bNo" value="${bookVO.bNo}">
									</form>
									<button type="button" class="btn btn-primary" onclick="deleteBook()">삭제</button>		
								</td>
								<td class="btnArea">
									<form name="updateForm"
										action="${pageContext.request.contextPath}/dispatcher">
										<input type="hidden" name="command" value="bookUpdateView">
										<input type="hidden" name="bNo" value="${bookVO.bNo}">
									</form>
									<button type="button" class="btn btn-primary" onclick="updateBook()">수정</button>
								</td>
							</c:when>
							<c:otherwise>
	
								<%-- 일반 회원 : 대여 버튼만 보여주기 --%>
	
								<td colspan="5" class="btnArea">
									<form name="rentForm"
										action="${pageContext.request.contextPath}/dispatcher">
										<input type="hidden" name="command" value="rentBook"> <input
											type="hidden" name="bNo" value="${bookVO.bNo}"> <input
											type="hidden" name="isRented" value="${bookVO.rented}">
									</form>
									<button type="button" class="btn btn-primary" onclick="rentBook()">대여</button>
								</td>
	
							</c:otherwise>
						</c:choose>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>