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
		alert("주문리스트에서 삭제했습니다.");
	</script>
</c:if>

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
		<form action="" method="post" name="buyListForm" onsubmit="">
			<table id="cart">
				<colgroup>
				<col width="100px">
				<col width="100px">
				<col width="200px">
				<col width="100px">
				<col width="100px">
				<col width="100px">
				</colgroup>
				<caption>===주문목록===</caption>
				<thead>
					<tr style="text-align: center;">
						<td>구매체크</td>
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
						<td colspan="7" align="center">
							주문 목록이 없습니다!
						</td>
					</tr>
				</c:if> 
				</tbody>
				<tfoot>
					<tr>
						<td align="center"><label for="all">전체선택</label><input type="checkbox" id="all" name="allselect"></td>
						<td colspan="6" align="right">
							<input type="button" value="주문취소" onclick="deleteBuyList();">
						</td>
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
