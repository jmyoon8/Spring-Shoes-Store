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
		error("등록성공");
	</script>
</c:if>
<c:if test="${result==0}">
	<script type="text/javascript">
		error("등록실패했습니다 잠시후 이용해주세요");
	</script>
</c:if>
</body>
</html>