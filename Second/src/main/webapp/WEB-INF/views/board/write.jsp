<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form action="<%=request.getContextPath()%>/board/write" method="post" enctype="multipart/form-data">
	<div class="container">
		<div class="form-group">
		 	<label>제목</label>
		    <input type="text" class="form-control" name="title">
		 </div>
		<div class="form-group">
			<label>작성자</label>
			<input type="text" class="form-control" name="writer" value="${user.id}" readonly>
		</div>
		<div class="form-group">
			<label>내용</label>
			<textarea class="form-control" rows="10" name="content"></textarea>
		</div>
		<div class="form-group">
        	<label>파일</label>
        	<input type="file" class="form-control" name="file2"/>
    	</div>
	</div>
	<button>작성</button>
</form>