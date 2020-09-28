<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
    	<title>home</title>
</head>
<body>
	<c:if test="${user == null}">
	<h1>
		로그인
	</h1>
	<form action="<%=request.getContextPath()%>/" method="post">
		<input type="text" name="id" placeholder="아이디">
		<input type="password" name="password" placeholder="비밀번호 ">
		<button>로그인</button>
	</form>
	<input type="hidden" value="${isLogin}" id="isLogin">
	<input type="hidden" value="${id}" id="id">
	<script type="text/javascript">
		$(function(){
			var id = $('#id').val();
			var isLogin = $('#isLogin').val();
			if(isLogin == 'false' && id !=''){
				alert(id+'가 없거나 비밀번호가 잘못 되었습니다.');
			}
		})
	</script>
	</c:if>
	<c:if test="${user != null && user.auth == 'USER'}">
		<br>
		<h1>
		환영합니다 ${user.id}님
		</h1>
	</c:if>
	<c:if test="${user.auth == 'ADMIN'}">
		<br>
		<h1>
		관리자모드 작동중
		</h1>
	</c:if>
</body>

</html>