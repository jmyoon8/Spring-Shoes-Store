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
		<table align="center">
		<tr>
			<th style="width:150px">글번호</th>
			<td style="width:150px">${number}</td>
			
			<th style="width:150px">조회수</th>
			<td style="width:150px">${vo.cnt}</td>
		</tr>
		<tr>
			<th style="width: 150px">작성자 </th>
			<td style="width: 150px" align="center">${vo.writer}</td>
			
			<th style="width: 150px">작성일</th>
			<td style="width: 150px"><fmt:formatDate type="both" pattern="yyyy-MM-dd hh:mm" value="${vo.reg_date}"/></td>
		</tr>
		<tr>
			<th>글제목</th>
			<td colspan="2" align="center">${vo.subject}</td>
		</tr>
		<tr>
			<th>글내용</th>
			<td colspan="3" style="height:200px" word-break:break-all>
			<!-- 
			word-break:break-all : 글자단위 자동 줄바꿈 - 권장
			word-break:keep-all : 단어기준 자동 줄바꿈
			  -->
			${vo.contents}</td>
		</tr>
		<tr>
			<th colspan="4">
			
				<!--글수정/글삭제 작성자일때만 등장 (삭제는 관리자도 포함)-->
				<c:if test="${sessionScope.sessId==vo.writer}">
					<!--글수정  -->
					<input class="inputButton" type="button" value="글수정 " onclick="window.location='${path}/member/modifyContent?num=${vo.num}&nowPage=${nowPage}'">
					<!--글삭제 -->
					<input class="inputButton" type="button" value="글삭제" id="delete" onclick="deleteConfirm();">
					<input type="hidden" name="num" value="${vo.num}" >
					<input type="hidden" name="nowPage" value="${nowPage}">
					<!--자바스크립트로 값보내기-->
				</c:if>
					
				<c:if test="${vo.writer=='host'&& not empty sessionScope.hostId }">
					<c:if test="${number!='공지사항'}">
						<input class="inputButton"type="button" value="답글쓰기"
						 onclick="window.location='${path}/member/writeNotice?num=${vo.num}&nowPage=${nowPage}&ref=${vo.ref}&ref_step=${vo.ref_step}&ref_level=${vo.ref_level}&writer=${sessionScope.hostId}'">
						<input class="inputButton" type="button" value="글삭제" id="delete" onclick="deleteConfirm();">
						<input type="hidden" name="num" value="${vo.num}" >
						<input type="hidden" name="nowPage" value="${nowPage}">
						<!--글수정  -->
						<input class="inputButton" type="button" value="글수정" onclick="window.location='${path}/member/modifyContent?num=${vo.num}&nowPage=${nowPage}'">
						<input type="hidden" name="num" value="${vo.num}" >
					<input type="hidden" name="nowPage" value="${nowPage}">
					</c:if>
				</c:if>
				
				 <input class="inputButton" type="button" value="목록보기" onclick="window.location='${path}/notice?nowPage=${nowPage}&word=${word}'">
	 		</th>
		</tr>
	</table>
		
	</div>	
	
	<div id="footer">
	
		<jsp:include page="footer.jsp"/>
			
	</div>
</div>
</script>
</body>
</html>