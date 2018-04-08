<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<main class="container">
	<div class="row">
		<div class="box col-sm-12 col-md-6">
			<div class="box-content">
				<h2><a href="dispatcher?command=rentList">내 대여 목록</a></h2>
			</div>
		</div>
		<div class="box col-sm-12 col-md-6">
			<div class="box-content">
				<h2><a href = "dispatcher?command=myPosting">내가 쓴 글</a></h2>
			</div>
		</div>
		<div class="box col-sm-12 col-md-6">
			<div class="box-content">
				<h2><a href = "dispatcher?command=memberUpdateView">회원 정보 수정</a></h2>
			</div>
		</div>
	</div>
</main>    
<script>
	$(document).ready(function(){
		$('.box').on('click', function(e){
			location.href = $(this).find('a').attr('href');
		});
	});
</script>


