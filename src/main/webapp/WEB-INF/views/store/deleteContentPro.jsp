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
		alert("삭제성공!");
		location.href="${path}/notice?nowPage=${nowPage}";
	</script>
</c:if>

<c:if test="${result==0}">
	<script type="text/javascript">
		error("삭제실패 잠시후 다시 시도해주세요!");
	</script>
</c:if>

</body>
</html>