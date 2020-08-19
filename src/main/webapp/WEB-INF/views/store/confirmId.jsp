<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/resources/0.setting.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h2>중복확인 페이지 </h2>
  
  <form action="confirmId" method="post" name="idCheck" onsubmit="idCheck;">
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"> 
	<c:if test="${result==1}">
			<table>
				<tr>
					<th colspan="2">
						${id}는 사용할 수없습니다
					</th>
				</tr>
				<tr>
					<th>
						<input class="input" type="text" name="memId" maxlength="20" style="width:150px"autofocus required>
					</th>
				</tr>
				<tr align="center">
					<td>
						<input class="inputButton" type="submit" value="확인">
						<input class="inputButton" type="reset" value="취소" onclick="self.close();">
					</td>
				</tr>
			</table>
		</c:if>
		<c:if test="${result==0}">
			<table>
				<tr>
					<td align="center">
						${id}는 사용 할 수 있습니다<!--중복이 아닌경우 -->
					</td>
				</tr>
				<tr>
					<th>
						<input class="inputButton" type="button" value="확인"	onclick="setId('${id}');">
					</th>
				</tr>
			</table>
		</c:if>
  </form>
</body>
</html>