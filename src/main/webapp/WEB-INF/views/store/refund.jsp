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
		alert("환불실패헀습니다. 잠시후 다시 시도해주세요!");
	</script>
</c:if>
<c:if test="${result==1}">
	<script type="text/javascript">
		alert("환불완료");
		location.href="/springStore/member/refund"
	</script>
</c:if>
<c:if test="${dResult==0}">
	<script type="text/javascript">
		alert("환불리스트 삭제 실패했습니다.");
	</script>
</c:if>
<c:if test="${dResult==1}">
	<script type="text/javascript">
		alert("환불 삭제 완료!");
		location.href="/springStore/member/refund"
	</script>
</c:if>  

<!-- http://aminshoe.co.kr/shopinfo/guide.html -->
<div id="contain">
	<div class="adt" id="aside" style="border: 1px solid black;">광고문의(01044851230)</div>
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
	<!-- confirmRefund.cus -->
	<div class="in" id="contents" >
		<form action="${path}/member/confirmRefund" method="get" name="refundForm" >
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<input type="hidden" name="memId" value="${sessionScope.sessId}">
			<table id="cart">
			<colgroup>
			<col width="100px">
			<col width="100px">
			<col width="200px">
			<col width="100px">
			<col width="100px">
			<col width="100px">
			<col width="100px">
			</colgroup>
			<caption>==환불목록==</caption>
				<thead>
					<tr style="text-align: center;">
						<td>구매체크</td>
						<td>신발번호</td>
						<td>이미지</td>
						<td>브랜드</td>
						<td>모델명</td>
						<td>판매가</td>
						<td>환불수량</td>
						<td>최대수량</td>
					</tr>
				</thead>
				<tbody>
				  <c:if test="${total>0}">
					<c:forEach var="vo" items="${vo}">
						<tr style="text-align: center;">
							<td><input type="checkbox" name="box" class="re" value="${vo.pNum}"></td>
							<td>
								${vo.shoesNumber}
								<input type="hidden" name="shoesNumber" value="${vo.shoesNumber}" >
							</td>
							<td><img src="/springStore/resources/shoesPic/${vo.pic}" width="100"></td>
							<td>${vo.brand}</td>
							<td>${vo.modelName}</td>
							<td>${vo.price}</td>
							<td><input type="number" min="0" name="count" value="1" max="${vo.pCount}"></td>
							<td>${vo.pCount}</td>
						</tr>
					</c:forEach>
				 </c:if>
				 <c:if test="${total<=0}">
					<tr>
						<td colspan="7" align="center">
							환불 목록이 없습니다!
						</td>
					</tr> 
				</c:if> 
				</tbody>
				<tfoot>
					<tr>
						<td align="center"><label for="all">전체선택</label><input type="checkbox" id="all" name="allselect"></td>
						<td colspan="6" align="right">
						<c:if test="${total>0 }">
							<input type="submit" value="환불확정" onclick="return check()">
							<input type="button" value="환불삭제" onclick="deleteRefund();">
						</c:if>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>	
	
	<div id="footer">
	
		<jsp:include page="footer.jsp"/>
			
	</div>
</div>
</body>
</html>
