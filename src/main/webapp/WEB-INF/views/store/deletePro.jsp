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
		alert("회원탈퇴 성공!");
		self.close();
	</script>
</c:if>
<c:if test="${result==0}">
	<script type="text/javascript">
		error("탈퇴 실패헀습니다 잠시 후 시도해 주세요!");
	</script>
</c:if>
</body>
</html>