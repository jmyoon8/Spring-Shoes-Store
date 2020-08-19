<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/resources/0.setting.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:choose>
	<c:when test="${sessionScope.hostId=='host'}">
		<ul>
			<li><a href="${path}/manager/managerlogOut">|로그아웃|</a></li>
		</ul>
	</c:when>
	<c:when test="${not empty sessionScope.sessId}">
		<ul style="font-size: 13px" class="inlineBlock">
			<li>안녕하세요!<a style="color:fuchsia; ; font-size: 12px;">${sessionScope.sessId}</a>님 오늘은 신발사기 좋은 날입니다.!!</li>
			<br>
			<li><a href="${path}/member/logOut">|로그아웃|</a></li>
			<li><a href="" onclick="pwdCheck_modify();">|회원정보 변경|</a></li>
			<li><a href="" onclick="pwdCheck_delete();">|회원탈퇴| </a></li>
		</ul>
	</c:when>
	<c:otherwise>
		<ul style="font-size: 13px" class="inlineBlock">
			<li><a href="login">|로그인|</a></li>
			<li><a href="" onclick="searchId()">|아이디 찾기|</a></li><!--이메일로 찾기  -->
			<li><a href="" onclick="searchPwd()">|비밀번호 찾기|</a></li><!--아이디 & 이메일 체크로찾기 -->
			<li><a href="join">|회원가입|</a></li>
			<li><a href="login">|관리자로그인|</a></li>
		</ul>
	</c:otherwise>
</c:choose>

</body>
</html>