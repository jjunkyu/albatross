<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	if(confirm("대여 성공! 대여 목록으로 이동하시겠습니까?")){
		location.href="${pageContext.request.contextPath}/dispatcher?command=rentList";
	}else{
		location.href="${pageContext.request.contextPath}/dispatcher?command=home";
	}
</script>
