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
		<a href="${path}/main"><img src="/springStore/resources/storePic/banner.jpg" width="700" height="150"></a>
		
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
		<table id="board" align="center">
			<tr align="center">
				<th width="70">번호</th>
				<th width="250">제목</th>
				<th width="100">글쓴이</th>
				<th width="90">조회수</th>
				<th width="100">작성일</th>
			</tr>
		<c:if test="${total>0}">
		<c:forEach var="news" items="${news}">
			<tr align="center">
				<td width="100"><b>공지사항</b></td>
				<td><a href="${path}/member/content?nowPage=${nowPage}&num=${news.num}&number=공지사항">${news.subject}</a></td>
				<td>${news.writer}</td>
				<td>${news.cnt }</td>
				<td><fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm" value="${news.reg_date}"/></td>
			</tr>
		</c:forEach>
		
		<c:forEach var="vo" items="${vo}">
			<tr align="center">
				<!--번호 -->
				<td>
					${number}
					<c:set var="number" value="${number-1}"/>
				</td>
				<td>
				
				<c:if test="${vo.ref_level>0}">
					<c:forEach begin="0" end="${vo.ref_level}">
							&nbsp;					
					</c:forEach>
				</c:if>
				<c:if test="${vo.ref_step>0}">
					→RE : 		
				</c:if>
				<a href="${path}/member/content?nowPage=${nowPage}&num=${vo.num}&number=${number+1}&word=${word}">${vo.subject}</a></td>
				
				<td>${vo.writer}</td>
				<td>${vo.cnt}</td>
				<td><fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm" value="${vo.reg_date}"/></td>
			</tr>
		</c:forEach>
		</c:if>
		<c:if test="${total<=0}">
			<tr>
				<td>글을 작성해주세요!</td>
			</tr>
			
		</c:if>
		</table>
		
		<!-- 페이지 조정 블럭 -->
		
		<table align="center">
			<tr align="right" id="bot">
				<td>
					<!--페이지 맨앞/1칸 앞으로이동  -->
						<a href="notice">◀◀  </a>
							<c:choose>
								<c:when test="${nowPage==1}">
									<a href="notice?nowPage=${nowPage}&word=${word}">◀  </a>
									<!--페이지가 1보다 더이상 안작아지도록  -->
								</c:when>
								<c:otherwise>
									<a href="notice?nowPage=${nowPage-1}&word=${word}">◀  </a>
								</c:otherwise>
							</c:choose>
							
					<%-- 	<!-- max블록씩 페이지 앞으로 이동 -->							
						<c:if test="${startBlock>maxBlock}" >
							<a href="notice?nowPage=${startBlock-maxBlock}"> 이전줄</a>
						</c:if> --%>
						
					<!--페이지 블록 -->
					<c:if test="${total>0}" >
						<c:forEach var="i" begin="${startBlock}" end="${endBlock}">
							<c:if test="${i==nowPage}">
								<a href="notice?nowPage=${i}&word=${word}" style="font-size:12px;">[${i}]</a>
							</c:if>
							<c:if test="${i!=nowPage}">
								<a href="notice?nowPage=${i}&word=${word}">[${i}]</a>
							</c:if>
						</c:forEach>
					</c:if>
					
						<%-- <!-- max블록씩 페이지 뒤로 이동 -->
						<c:if test="${totalBlock>endBlock}" >
							<a href="notice?nowPage=${startBlock+maxBlock}">다음줄</a>
						</c:if> --%>
						
					<!--뒤로1칸이동 / 페이지 맨뒤-->
						 	<c:choose>
						 		<c:when test="${nowPage==totalBlock}">
						 		<!--최종 페이지보다 더 뒤로 못가게  --> 
						 			<a href="notice?nowPage=${nowPage}&word=${word}"> ▶</a>
						 		</c:when>
						 		<c:otherwise>
						 			<a href="notice?nowPage=${nowPage+1}&word=${word}">  ▶</a>
						 		</c:otherwise>
						 	</c:choose>
						<a href="notice?nowPage=${totalBlock}"> ▶▶</a>
				</td>
				<td>	
					<c:choose>
						<c:when test="${sessionScope.hostId=='host'}">
							<a href="${path}/member/writeNotice?nowPage=${nowPage}&writer=host">글쓰기</a>
						</c:when>
						<c:when test="${not empty sessionScope.sessId}">
							<a href="${path}/member/writeNotice?nowPage=${nowPage}&writer=${sessionScope.sessId}">글쓰기</a>
						</c:when>
					</c:choose>
				</td>
				<td align="right"><input type="text" id="word" value="${word}"><input type="button" value="검색" onclick="search();"> </td>
			</tr>
		</table>
						
	</div>
	<div id="footer">
		<jsp:include page="footer.jsp"/>
			
	</div>
</div>
</body>
</html>