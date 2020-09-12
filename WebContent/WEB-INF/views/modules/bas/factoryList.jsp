<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta name="decorator" content="default">
		<title>工厂管理</title>
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
		<li class="active"><a href="${ctx}/bas/factory/">工厂列表</a></li>
		<li><a href="${ctx}/bas/factory/form">工厂添加</a></li>
	</ul>
	<!-- 2.查询部分 -->
	<form:form id="searchForm" method="post" action="${ctx}/bas/factory/" modelAttribute="factory" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value=${page.pageNo}/>
		<input id="pageSize" name="pageSize" type="hidden" value=${page.pageSize}/>
		<label>工厂名称：</label>
		<form:input path="factoryName" maxlength="50" class="input-medium" htmlEscape="false"/>
		<input id="btnSubmit" type="submit" value="查询" class="btn btn-primary"/>
	</form:form>
	<sys:message content="${message}"/>
	<!-- 3.列表部分 -->
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<th>工厂名称</th>
			<th>所属企业</th>
			<th>建筑日期</th>
			<th>地址</th>
			<th>电话</th>
			<th>邮政编码</th>
			<th>建筑面积</th>
			<th>操作</th>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="factory">
				<tr>
					<td>${factory.factoryName}</td>
					<td>${factory.enterprise.enterName}</td><!-- 注意：外键对象 -->
					<td>${factory.factoryBuildDate}</td>
					<td>${factory.factoryAddress}</td>
					<td>${factory.factoryPhone}</td>
					<td>${factory.factoryECode}</td>
					<td>${factory.factoryBuildM}</td>
					<td>
						<a href="${ctx}/bas/factory/form?id=${factory.id}">修改</a>
						<a href="${ctx}/bas/factory/delete?id=${factory.id}" onclick="return confirm('确认要删除该工厂吗？',this.href)">删除</a>
					</td>
				</tr>
			</c:forEach>		
		</tbody>
	</table>
	<!-- 4.分页 -->
	<div class="pagination">${page}</div>
</body>
</html>