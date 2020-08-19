<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/resources/0.setting.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload = function(){
	//파일을 선택할 때
	document.getElementById('file').onchange = function(){
		readImage();
	};
};
</script>
</head>
<body>
<c:if test="${result==1}">
	<script type="text/javascript">
		alert("등록성공!");
		location.href="${path}/manager/write";
	</script>
</c:if>
<c:if test="${result==0}">
	<script type="text/javascript">
		error("등록실패!");
	</script>
</c:if>
<form action="${path}/manager/insertStock?${_csrf.parameterName}=${_csrf.token}" name="addstock" method="post" enctype="multipart/form-data">
	<fieldset style="width: 400px;">
		<legend>목록추가</legend>
			<table>
				<tr id="addpic"><!-- accept="image/*" -->
					<td align="right">사진추가</td><td><input type="file" id="file" name="pic" accept="image/*"  required></td>
				</tr>
				<tr>
					<td colspan="1" align="right">미리보기</td>
					<td><img id="image" width="210" height="200" alt="Image Preview" style="display:none;" align="right"></td>
				</tr>
				<tr>
					<td align="right">브랜드</td><td><input type="text" name="brand" required></td>
				</tr>
				<tr>
					<td align="right">모델명</td><td><input type="text" name="modelName" required></td>
				</tr>
				<tr>
					<td align="right">가격</td><td><input type="text" name="price" required></td>
				</tr>				
				<tr>
					<td align="right">수량</td><td><input type="number" name="stockCount" min="0" size="10" required></td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<input type="submit" value="등록">
						<input type="reset" value="등록취소">
						<input type="button" value="닫기" onclick="self.close()"> 
					</td>
				</tr>
			</table>	
	</fieldset>	
</form>
</body>
</html>