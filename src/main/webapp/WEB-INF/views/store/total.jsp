<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/resources/0.setting.jsp" %>
<html>
<head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);
	  /* [['YEAR/MONTH', 'Sales'],
          ['2004', 		 1000],
          ['2005',  	 1170],
          ['2006',  	 660],
          ['2007',  	 1030]] */       
      function drawChart() {
        var data = google.visualization.arrayToDataTable(
        			${totalJson}
       	  
        );

        var options = {
          title: '월별 매출액',
          curveType: 'function',
          legend: { position: 'bottom' }
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

        chart.draw(data, options);
      }
    </script>
  </head>
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
	
	<div class="in" id="contents"  >
	
	<!-- 차트 보여질 곳 -->
		총매출 : <fmt:formatNumber value="${total}" type="number" />원<br>
		평균매출 : <fmt:formatNumber value="${avg}" type="number" />원
		<div id="curve_chart" style="width: 900px; height: 500px"></div>
		
	</div>	
	<div align="center" style="margin-top: 300px;">
		<form action="total.cus"  method="get">
			조회하고자하는 월을 선택해주세요!<br>
			시작 : <input type="month" name="startMonth" ><br> 
			   끝 : <input type="month" name="endMonth" >
			<br>
			<input type="submit">
		</form>
	</div>
	<div id="footer">
		<jsp:include page="footer.jsp"/>
	</div>
	
</div>
</body>
</html>