<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/resources/0.setting.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- http://aminshoe.co.kr/shopinfo/guide.html -->
<div id="contain">
	<jsp:include page="ad.jsp"/>
	<div class="c" id="header" >
	
		<!--메인banner  -->
		<a href="main.cus"><img src="/springStore/resources/storePic/banner.jpg" width="700" height="150"></a>
		
		<!-- login bar -->
		<div id="loginBar">
			<jsp:include page="mainBar.jsp"/>
		</div>	
		
		<!--메인 menu  -->
		<div class="c" id="mainmenu" >
			<jsp:include page="mainMenu.jsp"/>
		</div>
		
		<!--서브바  -->
		<div class="c" id="submenu" >
			<jsp:include page="subMenu.jsp"/>
		</div>
		
	</div>
	<div class="join" id="contents" style="margin-top: 10">
		<form action="/springStore/loginPro" method="post" name="login" style="margin-left: 150px;">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			<fieldset style="width: 250px" align="center">
				<legend>로그인</legend>
					<table style="border-collapse: collapse;" align="center">
						<tr>
							<td style="text-align: right;">아이디</td>
							<td><input type="text" name="memId" required="required" size="15"></td>
						</tr>
						<tr>
							<td style="text-align: right;">비밀번호</td>
							<td><input type="password" name="memPwd" required="required" size="15"></td>
						</tr>
						<tr>
							<td colspan="2" style="text-align: right;">
								<input type="submit" value="로그인">
								<input type="reset" value="취소">
							</td>							
						</tr>
					</table>
			</fieldset>
		</form>
	</div>
	
	<div id="footer">
		<jsp:include page="footer.jsp"/>
	</div>
</div>




		
</body>
</html>