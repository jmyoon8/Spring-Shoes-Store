<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/resources/0.setting.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${result==1}">
		<script type="text/javascript">
		alert("회원가입에 성공하셨습니다.");
		location.href="main.cus";
		</script>
	</c:if>
	<c:if test="${result==0 }">
		<script type="text/javascript">
			error("회원가입에 실패하셨습니다!");
		</script>
	</c:if>
</body>
</html>