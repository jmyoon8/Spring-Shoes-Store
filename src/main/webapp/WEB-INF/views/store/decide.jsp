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
		alert("삭제실패했습니다. 잠시후 다시 시도해주세요!");
	</script>
</c:if>
<c:if test="${result==1}">
	<script type="text/javascript">
		alert("리스트에서 삭제했습니다.");
		location.href="/springStore/manager/decide"
	</script>
</c:if>

<c:if test="${dResult==0}">
	<script type="text/javascript">
		alert("확정 실패했습니다. 잠시후 다시 시도해주세요!");
	</script>
</c:if>
<c:if test="${dResult==1}">
	<script type="text/javascript">
		alert("확정 완료!");
		location.href="/springStore/manager/decide"
	</script>
</c:if> 

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
		<form action="" method="post" name="decideForm" onsubmit="">
			<table id="cart">
				<colgroup>
				<col width="150px">
				<col width="120px">
				<col width="100px">
				<col width="200px">
				<col width="100px">
				<col width="100px">
				<col width="100px">
				</colgroup>
				<caption>===주문목록===</caption>
				<thead>
					<tr style="text-align: center;">
						<td>주문확정 체크</td>
						<td>주문번호</td>
						<td>주문자</td>
						<td>신발번호</td>
						<td>이미지</td>
						<td>브랜드</td>
						<td>모델명</td>
						<td>판매가</td>
						<td>수량</td>
					</tr>
				</thead>
				<tbody>
				  <c:if test="${total>0}">
					<c:forEach var="vo" items="${vo}">
						<tr style="text-align: center;">
							<td><input type="checkbox" name="box" class="re" value="${vo.oNum}"></td>
							<td>${vo.oNum}</td>
							<td>${vo.memId}</td>
							<td>${vo.shoesNumber}</td>
							<td><img src="/springStore/resources/shoesPic/${vo.pic}" width="100"></td>
							<td>${vo.brand}</td>
							<td>${vo.modelName}</td>
							<td>${vo.price}</td>
							<td>${vo.oCount}</td>
						</tr>
					</c:forEach>
				 </c:if>
				 <c:if test="${total<=0}">
					<tr>
						<td colspan="8" align="center">
							주문 목록이 없습니다!
						</td>
					</tr>
				</c:if> 
				</tbody>
			</table>
			<!-- 페이지 조정 블럭 -->
			<table align="center">
			<tr align="right" id="bot">
				<td>
					<!--페이지 맨앞/1칸 앞으로이동  -->
						<a href="${path}/manager/decide?nowPage=${nowPage}">◀◀  </a>
							<c:choose>
								<c:when test="${nowPage==1}">
									<a href="${path}/manager/decide?nowPage=${nowPage}">◀  </a>
									<!--페이지가 1보다 더이상 안작아지도록  -->
								</c:when>
								<c:otherwise>
									<a href="${path}/manager/decide?nowPage=${nowPage-1}">◀  </a>
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
								<a href="${path}/manager/decide?nowPage=${i}" style="font-size:20px;">[${i}]</a>
							</c:if>
							
							<c:if test="${i!=nowPage}">
								<a href="${path}/manager/decide?nowPage=${i}">[${i}]</a>
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
						 			<a href="${path}/manager/decide?nowPage=${nowPage}"> ▶</a>
						 		</c:when>
						 		<c:otherwise>
						 			<a href="${path}/manager/decide?nowPage=${nowPage+1}">  ▶</a>
						 		</c:otherwise>
						 	</c:choose>
						<a href="${path}/manager/decide?nowPage=${totalBlock}"> ▶▶</a>
				</td>
				<td>	
					<c:choose>
						<c:when test="${sessionScope.hostId=='host'}">
							<table>
								<tr>
									<td align="center"><label for="all">전체선택</label><input type="checkbox" id="all" name="allselect"></td>
									<td colspan="8" align="right">
										<input type="button" value="주문취소" onclick="deleteDecideList();">
										<input type="button" value="주문확정" onclick="confirmDecide();">
									</td>
								</tr>
							</table>
						</c:when>
					</c:choose>
				</td>
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
