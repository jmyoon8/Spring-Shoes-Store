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
	<div class="join" id="contents" style="margin-top: 1px;">
	<!--회원가입폼-->
			<form action="joinPro" method="post" name="join" onsubmit="return joinMem();">
				<input type="hidden" name="hiddenId" value="0">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"> 
						<table>
						<caption>회원가입</caption>
							<tr>
								<td style="text-align: right;">아이디</td>
								<td colspan="2"><input type="text" name="memId" required="required" size="25" autofocus placeholder="영문 소문자 숫자포함 5~10자리 ">
								<input type="button" value="아이디 중복 확인 확인" onclick="idCheck();" ></td>
							</tr>
							<tr>
								<td style="text-align: right;">비밀번호</td>
								<td><input type="password" name="memPwd" required="required" size="20"></td>
							</tr>
							<tr>
								<td style="text-align: right;">비밀번호 확인</td>
								<td><input type="password" name="rPwd" required="required" size="15"></td>
							</tr>
							<tr>
								<td style="text-align: right;">우편번호 <br>기본주소 <br>상새주소</td>
								<td>
									<input name="address1" type="text" id="sample4_postcode" placeholder="우편번호" required>
									<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"  required><br>
									<span id="guide" style="color:#999;display:none"></span>
									<input name="address2" type="text" id="sample4_roadAddress" placeholder="도로명주소" size="40"  required><br>
									<input name="address3"type="text" id="sample4_detailAddress" placeholder="상세주소" required><input type="text" id="sample4_extraAddress" placeholder="참고항목"  required>
								</td>
							</tr>
								<tr>
								<td style="text-align: right;">이름</td>
								<td><input type="text" name="name" required="required" size="10" ></td>
							</tr>
							<tr>
								<td style="text-align: right;">주민등록번호</td>
								<td>
									<input type="text" name="jumin1" required="required" size="10" maxlength="6" onkeyup="onkeyUp();">
									-
									<input type="text" name="jumin2" required="required" size="10" maxlength="7" onkeyup="onkeyUp();">
								</td>
							</tr>
							<tr>
								<td style="text-align: right;">전화번호</td>
								<td>
									<input type="tel" name="hp1"  required="required" size="1" maxlength="3" onkeyup="onkeyUp();">
									-
									<input type="tel" name="hp2"  required="required" size="3" maxlength="4" onkeyup="onkeyUp();">
									-
									<input type="tel" name="hp3"  required="required" size="3" maxlength="4" onkeyup="onkeyUp();">
								</td>
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
								<input type="button" value="이메일 인증" onclick="sendKey();">								</td>
							</tr>
							<tr>
								<td>이메일 인증키</td>
								<td><input type="text" name="insertKey" size="10"><input type="button" value="이메일 인증확인" onclick="key();"></td>
							</tr>
							<tr>
								<td colspan="3" style="text-align:center;">
									<input type="submit" value="회원가입">
									<input type="reset" value="회원가입 취소">
								</td>							
							</tr>
					</table>
					<input type="hidden" name="randomKey" value="${key}">
					<input type="hidden" name="hiddenEmail"value="0">
			</form>
		</div>
		<div id="footer">
	
		<jsp:include page="footer.jsp"/>
			
	</div>
</div>
</body>
</html>