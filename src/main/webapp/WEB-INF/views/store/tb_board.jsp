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
				<td width="70">번호</td>
				<td width="70">신발번호</td>
				<td width="200">사진</td>
				<td width="100">브랜드</td>
				<td width="90">모델명</td>
				<td width="60">수량</td>
				<td width="60">가격</td>
				<td width="60">조회수</td>
				<td width="60">등록일</td>
				
			</tr>
			
				
		<c:if test="${total>0}">
		<c:forEach var="vo" items="${vo}">
			<tr align="center">
				<!--번호 -->
				<td>
					${number}
					<c:set var="number" value="${number-1}"/>
				</td>
				
				<td>${vo.shoesNumber}</td>
				<td ><a href="${path}/member/goodsInfo?nowPage=${nowPage}&shoesNumber=${vo.shoesNumber}&number=${number+1}"><img src="/springStore/resources/shoesPic/${vo.pic}" width="200" height="150"></a></td>
				<td>${vo.brand}</td>
				<td>${vo.modelName}</td>
				<td>${vo.stockCount}</td>
				<td>${vo.price}</td>
				<td>${vo.cnt}</td>
				<td><fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm" value="${vo.reg_date}"/></td>
			</tr>
		</c:forEach>
		</c:if>
		<c:if test="${total<=0}">
			글을 작성해주세요!
		</c:if>
		</table>
		
		<!-- 페이지 조정 블럭 -->
		
		<table align="center">
			<tr align="right" id="bot">
				<td>
					<!--페이지 맨앞/1칸 앞으로이동  -->
						<a href="board.tb?nowPage=${nowPage}">◀◀  </a>
							<c:choose>
								<c:when test="${nowPage==1}">
									<a href="board.tb?nowPage=${nowPage}">◀  </a>
									<!--페이지가 1보다 더이상 안작아지도록  -->
								</c:when>
								<c:otherwise>
									<a href="board.tb?nowPage=${nowPage-1}">◀  </a>
								</c:otherwise>
							</c:choose>
							
					<%-- 	<!-- max블록씩 페이지 앞으로 이동 -->							
						<c:if test="${startBlock>maxBlock}" >
							<a href="board.tb?nowPage=${startBlock-maxBlock}"> 이전줄</a>
						</c:if> --%>
						
					<!--페이지 블록 -->
					<c:if test="${total>0}" >
						<c:forEach var="i" begin="${startBlock}" end="${endBlock}">
							<c:if test="${i==nowPage}">
								<a href="board.tb?nowPage=${i}" style="font-size:12px;">[${i}]</a>
							</c:if>
							<c:if test="${i!=nowPage}">
								<a href="board.tb?nowPage=${i}">[${i}]</a>
							</c:if>
						</c:forEach>
					</c:if>
					
						<%-- <!-- max블록씩 페이지 뒤로 이동 -->
						<c:if test="${totalBlock>endBlock}" >
							<a href="board.tb?nowPage=${startBlock+maxBlock}">다음줄</a>
						</c:if> --%>
						
					<!--뒤로1칸이동 / 페이지 맨뒤-->
						 	<c:choose>
						 		<c:when test="${nowPage==totalBlock}">
						 		<!--최종 페이지보다 더 뒤로 못가게  --> 
						 			<a href="board.tb?nowPage=${nowPage}"> ▶</a>
						 		</c:when>
						 		<c:otherwise>
						 			<a href="board.tb?nowPage=${nowPage+1}">  ▶</a>
						 		</c:otherwise>
						 	</c:choose>
						<a href="board.tb?nowPage=${totalBlock}"> ▶▶</a>
				</td>
					
					<c:choose>
						<c:when test="${sessionScope.hostId=='host'}">
						<td>
							<table>
								<tr>
									<td><a href="" onclick="writee();">재고등록</a></td>
									<td><a href="" onclick="deleteStock();">재고삭제</a></td>
									<td><a href="" onclick="modifyStock();">재고수정</a></td>
								</tr>
							</table>
						</td>
						</c:when>
					</c:choose>
				
			</tr>
		</table>
	</div>
	<div id="footer">
		<jsp:include page="footer.jsp"/>
			
	</div>
</div>
</body>
</html>