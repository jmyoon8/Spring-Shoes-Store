<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/resources/0.setting.jsp" %>s
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
<!--회원 정보 수정 폼  -->
<c:if test="${result==1}">
	<form action="${path }/member/modifyPro" method="get" name="modifyMem" onsubmit="return joinMem();">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"> 
					<table>
						<caption>회원정보수정</caption>
						<tr>
							<td style="text-align: right;">아이디</td>
							<td colspan="2">${mem.memId}</tr>
						<tr>
							<td style="text-align: right;">비밀번호</td>
							<td><input type="password" name="memPwd" required size="15" placeholder="소문자 숫자포함 5~10자리" value="${mem.memPwd}"></td>
						</tr>
						<tr>
							<td style="text-align: right;">비밀번호 확인</td>
							<td><input type="password" name="rPwd" required size="15" value="${mem.memPwd}"></td>
						</tr>
						<tr>
							<td style="text-align: right;">우편번호 <br>기본주소 <br>상새주소</td>
							<td>
								<input name="address1" type="text" id="sample4_postcode" placeholder="우편번호" value="${mem.address1}" required>
								<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"  required><br>
								<span id="guide" style="color:#999;display:none"></span>
								<input name="address2" type="text" id="sample4_roadAddress" placeholder="도로명주소" size="40" value="${mem.address2}"  required><br>
								<input name="address3"type="text" id="sample4_detailAddress" placeholder="상세주소" value="${mem.address3}"><input type="text" id="sample4_extraAddress" placeholder="참고항목">
							</td>
						</tr>
							<tr>
							<td style="text-align: right;">이름</td>
							<td>${mem.name}</td>
						</tr>
						<tr>
							<td style="text-align: right;">주민등록번호</td>
							<td>
								${mem.jumin1}
								-
								${mem.jumin2}
							</td>
						</tr>
						<tr>
						<c:set var="hpArr" value="${fn:split(mem.hp,'-')}"/><!--jstl function 라이브러리를 사용  -->
							<td style="text-align: right;">전화번호</td>
							<td>
								<input type="tel" name="hp1" value="${hpArr[0]}"  required="required" size="1" maxlength="3" onkeyup="onkeyUp();">
								-
								<input type="tel" name="hp2" value="${hpArr[1]}"  required="required" size="3" maxlength="4" onkeyup="onkeyUp();">
								-
								<input type="tel" name="hp3" value="${hpArr[2]}"  required="required" size="3" maxlength="4" onkeyup="onkeyUp();">
							</td>
						</tr>
						<tr>
							<c:set var="emailArr" value="${fn:split(mem.email,'@')}" />
							<td style="text-align: right;"><label for="email">이메일</label></td>
							<td>
								<input type="text" name="email1" value="${emailArr[0]}" required="required" size="8">
								@
								<input type="text" name="email2" value="${emailArr[1]}" required="required" size="12">
								<select name="email3" onchange="email();">
									<option value="">주소를입력해주세요</option>
									<option value="">직접입력</option>
									<option value="naver.com">네이버</option>
									<option value="daum.net">다음</option>
									<option value="nate.com">네이트</option>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="3" style="text-align:center;">
								<input type="submit" value="정보수정">
								<input type="reset" value="취소">
							</td>							
						</tr>
			</table>
		</form>
</c:if>
<c:if test="${result==0}">
	<script type="text/javascript">
		error("비밀번호가 틀렸습니다.");
	</script>
</c:if>
</body>
</html>