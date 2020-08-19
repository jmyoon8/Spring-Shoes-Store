<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/resources/0.setting.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${result==1}">
	<script type="text/javascript">
		alert("주문성공!");
		history.back();
	</script>	
</c:if>
<c:if test="${result==0}">
	<script type="text/javascript">
		alert("주문실패!!");
		history.back();
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
		<form action=""  method="post" name="buy" onsubmit="cart();">
		<div id="goodsleft"><img src="/springStore/resources/shoesPic/${vo.pic}" width="300" height="250"></div>
			<div id="goodsright">
				<table id="ta" >
						<tr>
							<td>게시물 번호 : ${number}</td>
						
						</tr>
						<tr>
							<td>Name</td>
							<td>${vo.modelName}</td>
						<tr>   
						    <td>Brand</td>
						    <td>${vo.brand}</td>
						</tr>
						<tr>
						    <td>Price</td>
						    <td><fmt:formatNumber value="${vo.price}" type="number" />원</td>
						</tr>
						
						<tr>
							<td>구매수량</td>
							<td><input type="number" name="count" min="0" max="${vo.stockCount}" value="0">EA</td>
							<td>제고수량 : ${vo.stockCount} </td>
						</tr>
						<tr>
							<td>
								<c:if test="${not empty sessionScope.sessId}">
									<input type="hidden" name="memId" value="${sessionScope.sessId}">
									<input type="hidden" name="shoesNumber" value="${vo.shoesNumber}">
									<input type="button" onclick="regCart();" value="장바구니 등록">
									<input type="button" onclick="nowBuy();" value="바로구매">
								</c:if>
								<c:if test="${not empty nowPage}">
								<input type="button" value="뒤로가기" onclick="location.href='${path}/board?nowPage=${nowPage}'">
								</c:if>
								<c:if test="${empty nowPage}">
								<input type="button" value="뒤로가기" onclick="history.back()">
								</c:if>
							</td>
						</tr>
				</table>
			</div>
		</form>
	</div>	
	
	<div id="footer">
		<jsp:include page="footer.jsp"/>
	</div>
</div>
</body>
</html>
