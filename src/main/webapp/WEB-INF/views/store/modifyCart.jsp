<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/resources/0.setting.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${result==0}">
	<script type="text/javascript">
		alert("수정실패! 잠시후 시도해주세요!");
		self.close();
	</script>
</c:if>

<c:if test="${result==1}">
	<script type="text/javascript">
		alert("수정성공!");
		history.back();
	</script>
</c:if>

<form action="${path}/member/cartModifyPro" name="modifyCart" method="post"  onsubmit="">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	<fieldset style="width: 400px;">
		<legend>목록수정</legend>
			<table>
				<tr>
					<td align="right">신발번호</td>
					<td>
						<input type="text" name="shoesNumber" value="${vo.shoesNumber}" required>
						<input type="button" value="조회" onclick="modifyCartRef();">
						<input type="hidden" value="${sessionScope.sessId}" name="memId">
	 				</td>				
				</tr>
				<tr>
					<td align="right">사진</td>
					<c:if test="${vo.pic!=null}">
						<td><img src="/store/store/shoesPic/${vo.pic}"width="250" height="200"></td>
					</c:if>
				</tr>
				<tr>
					<td align="right">브랜드 :</td><td>${vo.brand}</td>
				</tr>
				<tr>
					<td align="right">모델명 :</td><td>${vo.modelName}</td>
				</tr>
				<tr>
					<td align="right">가격 :</td><td>${vo.price}</td>
				</tr>				
				<tr>
					<td align="right">수량 수정</td><td><input type="number" name="cCount" min="0" max="${vo.stockCount}" value="${vo.cCount}" required></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<input type="submit" value="수정완료">
						<input type="button" value="닫기" onclick="self.close()"> 
					</td>
				</tr>
			</table>	
	</fieldset>	
</form>
</body>
</html>