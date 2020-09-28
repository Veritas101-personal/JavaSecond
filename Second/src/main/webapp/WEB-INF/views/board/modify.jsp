<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:if test="${board eq null}">
	<h1>해당 게시물은 없는 게시물입니다.</h1>
</c:if>
<c:if test="${board ne null}">
	<c:if test="${board.isDel == 'Y'.charAt(0)}">
		해당 게시물은 삭제되었습니다.
	</c:if>
	<c:if test="${board.isDel == 'N'.charAt(0)}">
		<form action="<%=request.getContextPath()%>/board/modify" method="post" enctype="multipart/form-data">
			<div class="container">
				<div class="form-group">
				 	<label>제목</label>
				    <input type="text" class="form-control" name="title" value="${board.title}">
				 </div>
			     <div class="form-group">
				 	<label>#</label>
				    <input type="text" class="form-control" name="num" value="${board.num}" readonly>
				 </div>
				  <div class="form-group">
				 	<label>작성자</label>
				    <input type="text" class="form-control" name="writer" value="${board.writer}" readonly>
				 </div>
				  <div class="form-group">
				 	<label>작성일자</label>
				    <input type="text" class="form-control" name="date" value="${board.date}" readonly>
				 </div>
				 <c:if test="${board.file != null}">
					 <div class="form-group board-file">
        				<label>파일: </label>
        				<label>${board.oriFile}</label>
						<button id="fileDelete"><i class="fas fa-times"></i></button>
						<input type="hidden" name="file" value="${board.file}">
    				</div>
				</c:if>
				<div class="form-group">
        			<label>파일</label>
        			<input type="file" class="form-control" name="file2"/>
    			</div>
				  <div class="form-group">
				 	<label>내용</label>
				    <textarea class="form-control" rows="10" name="content">${board.content}</textarea>
				 </div>
			</div>
			<button>제출</button>
			<a href="<%=request.getContextPath()%>/board/content?num=${board.num}"><button>취소</button></a>
		</form>
	</c:if>
</c:if>
<script>
	$(function(){
		$('#fileDelete').click(function(){
			$('.board-file').empty();
			
		})
		$('input[name=file2]').change(function(){
			if($('input[name=file]').val() == '' || $('input[name=file]').val() == null || typeof($('input[name=file]').val()) == 'undefined')
				return ;
			$(this).val('');
			alert('첨부파일을 하나만 추가해야 합니다. 기존 첨부파일을 삭제하세요.');

		})

	})
</script>


