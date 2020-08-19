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
	<!--상품 게시물-->
	<div><img src="/springStore/resources/storePic/베스트.PNG" id="best" ></div>
	<hr>
		<c:forEach var="best" items="${best}">
		<span style="display: inline-block;">
			<a href="${path}/member/goodsInfo?shoesNumber=${best.shoesNumber}&number=best"><img src="/springStore/resources/shoesPic/${best.pic}" width="450" height="400" id="pic"></a>
			<p id="shoesname">모델명 : ${best.modelName}</p>
			<p id="brand">브랜드 명 : ${best.brand }</p>
			<p id="price">가격 ${best.price}</p>
			<p id="price">조회수 :${best.cnt}</p>
		</span>
		</c:forEach>
	</div>
	
	
	<!--BEST 게시물-->
	<div class="in" id="contentss" style="border: 1px solid white;">
	<div><img src="/springStore/resources/storePic/신상.PNG" id="new" ></div>
	
	<hr>
		<c:forEach var="recently" items="${recently}">
		<span style="display: inline-block;">
			<a href="${path}/member/goodsInfo?shoesNumber=${recently.shoesNumber}&number=recently"><img src="/springStore/resources/shoesPic/${recently.pic}" width="450" height="400" id="pic"></a>
			<p id="shoesname">모델명 : ${recently.modelName}</p>
			<p id="brand">브랜드 명 : ${recently.brand }</p>
			<p id="price">가격 ${recently.price}</p>
			<p id="price">입고일 <fmt:formatDate type="both" value="${recently.reg_date}" pattern="yyyy-MM-dd HH:mm"/></p>
			</span>
		</c:forEach>
	</div>	
	
	<div id="footer">
	
		<jsp:include page="footer.jsp"/>
			
	</div>
</div>
<script type="text/javascript"src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
var num=1;
var pic=document.getElementById("nextd");
	function nextpic(){
		pic.setAttribute("src","../HTML_picture/"+num+".jpg");
		if(num<=12){
			num++;
		}else{
			num=1;
		}
		console.log(num);
	};
$(function(){
	
	var inter;
	
	$("#nextd").mouseover(function(){
		if(inter==null){
			inter =setInterval(nextpic,500);
		}else{
		}
	});
	$("#nextd").mouseleave(function() {
		clearInterval(inter);	
		inter=null;
		
	});
	
	/*마우스 올리면 시작 치우면 멈춤*/
});
</script>
</body>
</html>