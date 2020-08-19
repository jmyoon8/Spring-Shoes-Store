<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/resources/0.setting.jsp" %>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${sessionScope.hostId=='host'}">
		<ul>
			<hr>
			<li><a href="${path}/board">재고관리</a></li>
			<li><a href="${path}/manager/decide">주문확정</a></li>
			<li><a href="${path}/manager/total">결산</a></li>
			<li><a href="#header">맨위로가기</a></li>
			<li><a href="#footer">맨아래로가기</a></li>
			<hr>
		</ul>
		</c:when>
		<c:when test="${not empty sessionScope.sessId }">
			<ul>
				<hr>
				
				<li><a href="${path}/member/cart">장바구니</a></li>
				<li><a href="${path}/member/buyList">주문목록</a></li>
				<li><a href="${path}/member/refund">환불</a></li>
				<li><a href="#header">맨위로가기</a></li>
				<li><a href="#footer">맨아래로가기</a></li>
				<hr>
			</ul>
		</c:when>
		<c:otherwise>
			<ul>
				<hr>
				<li><a href="#header">맨위로가기</a></li>
				<li><a href="#footer">맨아래로가기</a></li>
				<hr>
			</ul>
		</c:otherwise>
	</c:choose>
</body>
</html>