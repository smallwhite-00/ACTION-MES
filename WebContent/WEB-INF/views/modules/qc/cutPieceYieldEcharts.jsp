<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="decorator" content="default">
	<title>切片质量检测</title>
</head>
<body>
	<!-- 1.tab头部 -->
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/qc/cutPieceYield">切片质量检测</a></li>
	</ul>
	<!-- 2.查询 -->
	<form:form id="searchForm" method="post" action="${ctx}/qc/cutPieceYield" modelAttribute="cutPieceYield" class="breadcrumb form-search">
		<div class="controls">
			<label>开始时间：</label>
			<form:input path="startTime" maxlength="50" class="input-medium wdate" htmlEscape="false" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
			&nbsp;&nbsp;
			<label>结束时间：</label>
			<form:input path="endTime" maxlength="50" class="input-medium wdate" htmlEscape="false" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>			
			<input id="btnSubmit" type="submit" value="查询" class="btn btn-primary"/>
		</div>
	</form:form>
	<!-- 3.图表 -->
	<div id="chartmain" style="width:100%;height:500px;"></div>
	<script type="text/javascript">
		$(function(){
			var xData=[];
			var yData=[];
			<c:forEach items="${yieldList}" var="cutPieceYield">
				xData.push("${cutPieceYield.cardId}${cutPieceYield.employeeName}");
				yData.push((${cutPieceYield.yield}*100).toFixed(2));
			</c:forEach>
	        // 基于准备好的dom，初始化echarts实例
	        var myChart = echarts.init(document.getElementById('chartmain'));

	        // 指定图表的配置项和数据
	        var option = {
	            title: {
	                text: '切片工位成品率',
	                top:'bottom',
	                left:'center'
	            },
	            tooltip: {           	
	            	trigger:'axis'
	            },
	            legend: {
	            	
	            },
	            xAxis: {
	                name:'员工工号',
	                type:'category',
	                data:xData
	            },
	            yAxis: {
	            	name:'成品率%'
	            },
	            series: [{
	                name: '成品率',
	                type: 'bar',
	                data: yData
	            }]
	        };

	        // 使用刚指定的配置项和数据显示图表。
	        myChart.setOption(option);
		})
		
    </script>
</body>
</html>