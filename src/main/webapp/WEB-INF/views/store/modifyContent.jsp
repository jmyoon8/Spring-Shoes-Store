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
	
	<div class="in" id="contents" >
	
		<c:if test="${result==1}">
		<script type="text/javascript">
			alert("글수정 성공!");
			location.href="${path}/notice?nowPage=${nowPage}";
		</script>
		</c:if>
		<c:if test="${result==0}">
			<script type="text/javascript">
				error("글수정 실패! 잠시후 다시 시도해 주세요!");
			</script>
		</c:if>
		
		<form action="${path}/member/modifyContentPro">
			<input type="hidden" name="nowPage" value="${nowPage}">
			<input type="hidden" name="num" value="${num}">
			
			<table align="center">
				<tr>
					<th colspan="2">수정할 정보를 입력하세요!! </th>
				</tr>
				<tr>
					<th style="width: 150px">작성자 </th>
					<td style="width: 150px" align="center">${vo.writer}</td>
				</tr>
				<tr>
					<th>글제목</th>
					<td><input class="input" type="text" name="subject" maxlength="50" value="${vo.subject}" style="width: 270px"></td>
				</tr>
				<tr>
					<th>글내용</th>
					<td><textarea class="input" rows="35" cols="100" name="content" word-break:break-all>${vo.contents}</textarea></td>
				</tr>
				<tr>
					<th colspan="2">
						<!--글수정  -->
						<input class="input" type="submit" value="글수정" >
						
						<input class="input" type="reset" value="취소">
						<input class="input" type="button" value="목록보기" onclick="window.location='${path}/notice?pageNum=${pageNum}'">
			 		</th>
				</tr>
			</table>
		</form>
	</div>	
	<div id="footer">
		<jsp:include page="footer.jsp"/>
			
	</div>
</div>
</body>
</html>

