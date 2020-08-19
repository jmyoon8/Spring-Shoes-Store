<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/resources/0.setting.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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
				alert("글이 등록되었습니다.");
				location.href="${path}/notice?nowPage=${nowPage}"
			</script>
		</c:if>
		<c:if test="${result==0}">
			<script type="text/javascript">
				error("글작성 실패하였습니다 잠시후 시도해주세요!");
			</script>
		</c:if>
			<form action="${path}/member/writePro" method="post" name="writeform">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				<c:if test="${not empty param.num}">	
					<input type="hidden" name="num" value="${param.num}">
					<input type="hidden" name="ref" value="${param.ref}">
					<input type="hidden" name="ref_step" value="${param.ref_step}">
					<input type="hidden" name="ref_level" value="${param.ref_level}">
				</c:if>
				<c:if test="${empty param.num}">	
					<input type="hidden" name="num" value="0">
					<input type="hidden" name="ref" value="0">
					<input type="hidden" name="ref_step" value="0">
					<input type="hidden" name="ref_level" value="0">
				</c:if>
				
				<input type="hidden" name="nowPage" value="${param.nowPage}">
				<table align="center">
					<tr>
						<th>작성자</th>
						<td>
							<input class="input" type="text" name="writer" maxlength="20" value="${param.writer}" readonly="readonly">
							<c:if test="${sessionScope.hostId=='host'}">
								<input type="hidden" value="post" name="select" >
								<select name="type" onchange="changeType();">
									<optgroup label="게시글 종류를 선택해주세요">
										<option value="news">공지사항</option>
										<option value="post">게시글</option>
									</optgroup>
								</select>
							</c:if>
						</td>
						<c:if test="${not empty sessionScope.sessId}">
							<input type="hidden" value="post" name="select" >
						</c:if>
					</tr>
					<tr>
						<th>제목</th>
						<td>
							<input class="input" type="text" name="subject" maxlength="50" placeholder="내용을 입력하세요" required autofocus>
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<textarea class=input rows="10" cols="40" name="content" style="width:400px; height: 300px;"  placeholder="글내용을 입력하세요!" word-break:break-all></textarea>
						</td>
					</tr>
					<tr>
						<th colspan="2">
							<input class="button" type="submit" value="작성">
							<input class="button" type="reset" value="초기화">
							<input class="button" type="button" value="목록으로" onclick="window.location='${path}/notice?nowPage=${param.nowPage}'">
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


