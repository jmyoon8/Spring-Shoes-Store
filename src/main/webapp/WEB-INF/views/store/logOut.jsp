<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/resources/0.setting.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
request.getSession().invalidate();
%>
<script type="text/javascript">
	alert("로그아웃 되었습니다.");
	location.href="${path}/main";
</script>
</body>
</html>