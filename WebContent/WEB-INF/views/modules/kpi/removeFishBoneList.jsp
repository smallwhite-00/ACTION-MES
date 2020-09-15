<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="decorator" content="default">
	<title>挑刺工站绩效</title>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
		}
	</script>
</head>
<body>
	<!-- 1.tab头部 -->
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/kpi/removeFishBone">挑刺工站绩效</a></li>
	</ul>
	<!-- 2.查询部分 -->
	<form:form id="searchForm" method="post" action="${ctx}/kpi/removeFishBone" modelAttribute="removeFishBone" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value=${page.pageNo}/>
		<input id="pageSize" name="pageSize" type="hidden" value=${page.pageSize}/>
		<div class="controls">
			<label>开始时间：</label>
			<form:input path="startTime" maxlength="50" class="input-medium wdate" htmlEscape="false" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
			&nbsp;&nbsp;
			<label>结束时间：</label>
			<form:input path="endTime" maxlength="50" class="input-medium wdate" htmlEscape="false" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>			
			&nbsp;&nbsp;
			<label>人员名称：</label>
			<form:input path="employeeName" maxlength="50" class="input-medium" htmlEscape="false"/>
			<input id="btnSubmit" type="submit" value="查询" class="btn btn-primary"/>
		</div>
	</form:form>
	<!-- 3.列表 -->
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<th>日期</th>
			<th>人员</th>
			<th>总重</th>
			<th>薪资(元)</th>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="removeFishBone">
				<tr>
					<td>${removeFishBone.time}</td>
					<td>${removeFishBone.employeeName}</td>
					<td>${removeFishBone.weight}</td>
					<td>${removeFishBone.money}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 4.分页 -->
	<div class="pagination">${page}</div>
</body>
</html>