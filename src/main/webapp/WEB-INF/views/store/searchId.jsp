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
	<c:when test="${memId=='notF'}">
		<script type="text/javascript">
			error("이메일 인증 실패하였습니다 존재하지 않거나 잘못된 이메일입니다");
		</script>
	</c:when>
	<c:when test="${not empty memId}">
	<script type="text/javascript">
			alert("이메일 인증 성공!");
		</script>
		회원님의 아이디는 ${memId}입니다.!
	</c:when>
	<c:otherwise>
		<form action="searchIdPro" method="post" name="searchId" >
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
			<table>
				<tr>
					<th>이메일을 입력하세요</th>		
				</tr>
				<tr>
					<td style="text-align: right;"><label for="email">이메일</label></td>
					<td>
						<input type="text" name="email1" required="required" size="8">
						@
						<input type="text" name="email2" required="required" size="12">
						<select name="email3" onchange="email();">
							<option value="">주소를입력해주세요</option>
							<option value="">직접입력</option>
							<option value="naver.com">네이버</option>
							<option value="daum.net">다음</option>
							<option value="nate.com">네이트</option>
						</select>
					<td>
						<input type="submit" value="아이디찾기">								
						<input class="button" type="reset" value="취소" onclick="self.close()">
					</td>
				</tr>	
			</table>
		</form>	
	</c:otherwise>
</c:choose>
</body>
</html>