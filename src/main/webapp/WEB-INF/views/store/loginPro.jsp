<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/resources/0.setting.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${result==1 }">
	<script type="text/javascript">
		alert("로그인 성공!");
		location.href="/springStore/main";
	</script>	
</c:if>
<c:if test="${result==0}">
	<script type="text/javascript">
		error("아이디 또는 비밀번호가 틀렸습니다.");
	</script>
</c:if>


</body>
</html>