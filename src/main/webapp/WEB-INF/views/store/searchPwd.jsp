<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/resources/0.setting.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:choose>

	<c:when test="${memPwd=='notF'}">
		<script type="text/javascript">
			error("이메일 인증 실패하였습니다 아이디가 존재하지 않습니다.");
		</script>
	</c:when>
	
	<c:when test="${not empty memPwd}">
		<script type="text/javascript">
			alert("이메일 인증 성공!");
		</script>
		회원님의 임시 비밀번호는 ${memPwd}입니다.! 로그인후 비밀번호를 바꿔주세요!
	</c:when>
	<c:otherwise>
		<form action="searchPwdPro" method="post" name="searchPwd" onsubmit="return joinMem();">
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
			<table>
				<tr>
					<td>아이디를 입력하세요</td>
					<td><input type="text" name="memId"></td>
				</tr>
				<tr>
					<td>이메일을 입력하세요</td>
					<td>
						<input type="text" name="email1" required="required" size="8">
						@
						<input type="text" name="email2" required="required" size="12">
						<select name="email3" onchange="emaill();">
							<option value="">주소를입력해주세요</option>
							<option value="">직접입력</option>
							<option value="naver.com">네이버</option>
							<option value="daum.net">다음</option>
							<option value="nate.com">네이트</option>
						</select>
						<input type="button" value="인증키 발송" onclick="sendKeyPwd();">
				</tr>
				<tr>
					<td>인증키 입력</td>
					<td>
						<input type="text" name="insertKey">
						<input type="button" value="인증하기" onclick="key();">
					</td>
				</tr>	
				<tr>
					<td>
						<input type="submit" value="비밀번호 찾기">
						<input class="button" type="reset" value="취소" onclick="self.close()">
					</td>								
				</tr>
			</table>
			<input type="hidden" name="randomKey" value="${key}">
			<input type="hidden" name="hiddenEmail" value="0">
		</form>	
	</c:otherwise>
</c:choose>
</body>
</html>