<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="container">
  <h2>게시판</h2>
  <p>당신의 뻘글을 여기에 쓰시오.</p>            
  <table class="table table-hover">
    <thead>
      <tr>
        <th>#</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일자</th>
        <th>조회수</th>
        <th>댓글수</th>
      </tr>
    </thead>
    <tbody>
    <c:if test="${list.size()!=0}">
	    <c:forEach var="board" items="${list}">
	      <tr>
	        <td>${board.num}</td>
	        <td>
	       	 <a href="<%=request.getContextPath()%>/board/content?num=${board.num}">${board.title}</a>
	       	 </td>
	        <td>${board.writer}</td>
	        <td>${board.date}</td>
	        <td>${board.watchNum}</td>
	      	<td>${board.commentNum}</td>
	      </tr>
	      </c:forEach>
      </c:if>
      <c:if test="${list.size()==0}">
      	<tr>
      		<td colespan=5>게시글이 없습니다.</td>
      	</tr>
      </c:if>
    </tbody>
  </table>

  	<a href="<%=request.getContextPath()%>/board/write"><button>등록</button></a>

 
  	<!--  페이지네이션 start  -->
<ul class="pagination justify-content-center">
  <li class="page-item <c:if test="${!pm.prev}">disabled</c:if>"><a class="page-link" href="<%=request.getContextPath()%>/board?page=${pm.startPage-1}&search=${pm.cri.search}&type=${pm.cri.type}">Previous</a></li>
  <c:forEach var="index" begin="${pm.startPage}" end="${pm.endPage}">
  	<li class="page-item <c:if test="${pm.cri.page == index}">active</c:if>"><a class="page-link" href="<%=request.getContextPath()%>/board?page=${index}&search=${pm.cri.search}&type=${pm.cri.type}">${index}</a></li>
  </c:forEach>
  <li class="page-item <c:if test="${!pm.next}">disabled</c:if>"><a class="page-link" href="<%=request.getContextPath()%>/board?page=${pm.endPage+1}&search=${pm.cri.search}&type=${pm.cri.type}">Next</a></li>
</ul>
	<!--  페이지네이션 end  -->
	<!-- 검색박스 start -->
<form action="<%=request.getContextPath()%>/board">
	<div class="input-group mb-3">
    <select class="form-control">
    	<option value="0" <c:if test="${pm.cri.type == 0}">selected</c:if>>전체</option>
    	<option value="1" <c:if test="${pm.cri.type == 1}">selected</c:if>>작성자</option>
    	<option value="2" <c:if test="${pm.cri.type == 2}">selected</c:if>>제목</option>
    	<option value="3" <c:if test="${pm.cri.type == 3}">selected</c:if>>내용</option>
    </select>
    <input type="text" class="form-control" placeholder="search" name="search" value="${pm.cri.search}">
    <div class="input-group-append">
    	<button class="btn btn-success" type="submit">검색</button>
  	</div>
  </div>
</form>
	<!-- 검색박스 end -->
</div>
