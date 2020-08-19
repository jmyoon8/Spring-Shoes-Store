<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/resources/0.setting.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${result==0 }">
	<script type="text/javascript">
		error("회원정보 수정에 실패 했습니다 잠시후 다시 요청해주세요!");
	</script>
</c:if>
<c:if test="${result==1 }">
	<script type="text/javascript">
		alert("회원정보 수정에 성공했습니다.");
		self.close();
	</script>
</c:if>
</body>
</html>