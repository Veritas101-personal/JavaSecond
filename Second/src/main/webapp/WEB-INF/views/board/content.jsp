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
		<form>
			<div class="container">
				<div class="form-group">
				 	<label>제목</label>
				    <input type="text" class="form-control" name="title" value="${board.title}" readonly>
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
				 <div class="form-group">
				      <label>추천수</label>
				      <input type="text" class="form-control" name="like" value="${board.up}" readonly>
				      <button type="button"id='up'><i class="far fa-thumbs-up"></i></button>
			   	 </div>
			   	 <c:if test="${board.file == null}"></c:if>
				 <c:if test="${board.file != null}">
					 <div class="form-group">
					 	<label>자료: </label>
					 	<a href="<%=request.getContextPath()%>/board/download?fileName=${board.file}">${board.oriFile}</a>
					 </div>
				</c:if>
				 <div class="form-group">
				 	<label>내용</label>
				    <textarea class="form-control" rows="10" name="content" readonly>${board.content}</textarea>
				 </div>
			</div>
		</form>
		<a href="<%=request.getContextPath()%>/board/write"><button>새글</button></a>
		<c:if test="${user.id == board.writer || user.auth == 'ADMIN'}">
			<a href="<%=request.getContextPath()%>/board/modify?num=${board.num}"><button>수정</button></a>
			<a href="<%=request.getContextPath()%>/board/delete?num=${board.num}"><button>삭제</button></a>
		</c:if>
		<c:if test="${user.id != board.writer}"></c:if>
	</c:if>
</c:if>
<script>
	$(function(){
		$('#up').click(function(){
			var num = $('input[name=num]').val();
			  $.ajax({
			        async:true,
			        type:'POST',
			        data:id,
			        url:"test",
			        dataType:"json",
			        contentType:"application/json; charset=UTF-8",
			        success : function(data){
			       			
			        }
			    });
			
		})
	})
</script>

