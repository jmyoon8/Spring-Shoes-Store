<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/resources/0.setting.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${path}/member/deletePro" method="post" name="passwdfocus" >
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	<table>
		<tr>
			<th>비밀번호를 입력하세요</th>		
		</tr>
		<tr>
			<td>
				<input class="input" type="password" name="memPwd" maxlength="20" required>
			</td>
		</tr>
		<tr>
			<td align="center">
				<input class="button" type="submit" value="비밀번호체크">
				<input class="button" type="reset" value="취소" onclick="self.close()">
			</td>
		</tr>
	</table>
</form>

</body>
</html>