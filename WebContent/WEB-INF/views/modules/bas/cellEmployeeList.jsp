<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="decorator" content="default">
	<title>工位与员工关系管理</title>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
		}
	</script>
</head>
<body>
	<!-- 1.tab -->
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bas/cellEmployee/">工位与员工关系列表</a></li>
		<li><a href="${ctx}/bas/cellEmployee/form">工位与员工关系添加</a></li>
	</ul>
	<!-- 2.查询 -->
	<form:form id="searchForm" method="post" action="${ctx}/bas/cellEmployee" modelAttribute="cellEmployee" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value=${page.pageNo}/>
		<input id="pageSize" name="pageSize" type="hidden" value=${page.pageSize}/>
		<div class="controls">
			<label>员工姓名：</label>
			<form:input path="employee.employeeName" maxlength="50" class="input-medium" htmlEscape="false"/>
			<label>工位名称：</label>
			<form:input path="workCell.cellName" maxlength="50" class="input-medium" htmlEscape="false"/>
			<input id="btnSubmit" type="submit" value="查询" class="btn btn-primary"/>
		</div>
	</form:form>
	<sys:message content="${message}"></sys:message>
	<!-- 3.列表 -->
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<th>员工姓名</th>
			<th>所属工位</th>
			<th>操作</th>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="cellEmployee">
				<tr>
					<td>${cellEmployee.employee.employeeName}</td>
					<td>${cellEmployee.workCell.cellName }</td>
					<td>
						<a href="${ctx}/bas/cellEmployee/form?id=${cellEmployee.id}">修改</a>
						<a href="${ctx}/bas/cellEmployee/delete?id=${cellEmployee.id}" onclick="return confirmx('确认要删除该关系吗？',this.href)">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 4.分页 -->
	<div class="pagination">${page}</div>
</body>
</html>