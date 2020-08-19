<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/resources/0.setting.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:if test="${result==1}" >
	<script type="text/javascript">
		error("삭제성공");
	</script>
</c:if>
<c:if test="${result==0}" >
	<script type="text/javascript">
		error("삭제실패");
	</script>
</c:if>

<form action="${path}/manager/deletePro" method="post" name="shoesNumber" >
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }">
	<table>
		<tr>
			<th>삭제할 신발번호를입력해주세요</th>		
		</tr>
		<tr>
			<td>
				<input class="input" type="text" name="shoesNumber" required>
			</td>
		</tr>
		<tr>
			<td align="center">
				<input class="button" type="submit" value="삭제진행">
				<input class="button" type="reset" value="취소" onclick="self.close()">
			</td>
		</tr>
	</table>
</form>
</body>
</html>