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
		error("수정성공!!");
	</script>
</c:if>
<c:if test="${result==0}">
	<script type="text/javascript">
		error("수정실패!!");
	</script>
</c:if>


<form action="${path}/manager/modifystock?${_csrf.parameterName}=${_csrf.token}" name="modifyStock" method="post" enctype="multipart/form-data" onsubmit="return confirmModifyStock(); ">
	<fieldset style="width: 400px;">
		<legend>목록수정</legend>
			<table>
				<tr>
					<td align="right">신발번호</td>
					<td>
						<input type="text" name="shoesNumber" value="${vo.shoesNumber}" required>
						<input type="button" value="조회" onclick="modifyRef();">
					</td>				
				</tr>
				<tr>
					<td>등록된사진</td>
					<c:if test="${vo.pic!=null}">
						<td><img src="/springStore/resources/shoesPic/${vo.pic}" name="OriPic" width="250" height="200"></td>
						<input  type="hidden" name="pic2" value="${vo.pic}">
					</c:if>
					
				</tr>
				<tr id="addpic">
					<td align="right">사진변경</td>
					<td><input type="file" id="file" name="pic" accept="image/*"></td>
				</tr>
				<tr>
					<td colspan="1" align="right">미리보기</td>
					<td><img id="image" width="210" height="200" alt="Image Preview" style="display:none;" align="right"></td>
				</tr>
				
				<tr>
					<td align="right">브랜드</td><td><input type="text" name="brand" value="${vo.brand}" required></td>
				</tr>
				<tr>
					<td align="right">모델명</td><td><input type="text" name="modelName" value="${vo.modelName}" required></td>
				</tr>
				<tr>
					<td align="right">가격</td><td><input type="text" name="price" value="${vo.price}" required></td>
				</tr>				
				<tr>
					<td align="right">수량</td><td><input type="number" name="stockCount" min="0" size="10" value="${vo.stockCount}" required></td>
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