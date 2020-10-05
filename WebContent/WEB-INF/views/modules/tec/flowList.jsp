<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="decorator" content="default">
	<title>工艺流程管理</title>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
		}
	</script>
	<script type="text/javascript">
		function Select() {
			var name = document.getElementsByName("ids");
			var sel = document.getElementById("selall");
			for (var i = 0; i < name.length; i++) {
				name[i].checked=true;
			}
			if(!sel.checked){
				for (var i = 0; i < name.length; i++) {
					name[i].checked=false;
				}
			}
		}

		/*添加删除选中栏*/
		function delmore(){
			//给删除选中按钮添加单击事件
			document.getElementById("delSelected").onclick = function(){
				if(confirm("您确定要删除选中条目吗？")){
					var flag=false;
					//判断是否有选中条目,不选中任何条目删除会报空指针异常错误
					var idAr=new Array();
					var id='';
					var name = document.getElementsByName("ids");
					for (var i = 0; i < name.length; i++) {
						if(name[i].checked){
							//有一个条目选中了
							flag=true;
							break;
						}
					}
					if(flag==false)
						alert("当前未选中任何项目，请检查");
					else
					{
						for(var i=0;i<name.length;i++){
							id=name[i].value;
							if(name[i].checked){

								idAr[i]=id;

							}
						}
						//上面会拼接出一个名为idAr的数组

						window.location.href = "${ctx}/tec/flow/delmore?idAr="+idAr;

					}
				}
			}
		}
	</script>
</head>
<body>
	<!-- 1.tab头部 -->
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/tec/flow">工艺流程列表</a></li>
		<li><a href="${ctx}/tec/flow/form">工艺流程添加</a></li>
	</ul>
	<!-- 2.查询 -->
	<form:form id="searchForm" method="post" action="${ctx}/tec/flow" modelAttribute="flow" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div class="controls">
			<label>流程编码：</label>
			<form:input path="flowCode" maxlength="50" class="input-medium" htmlEscape="false"/>
			<input id="btnSubmit" type="submit" value="查询" class="btn btn-primary"/>
			<button type="button" class="btn btn-primary" onclick="delmore()" id="delSelected">删除选中</button>
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<!-- 3.列表 -->
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<th><input type="checkbox" name="selall" id="selall" onclick="Select()"/>全选</th>
			<th>流程编码</th>
			<th>流程版本</th>
			<th>流程名称</th>
			<th>流程描述</th>
			<th>操作</th>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="flow">
				<tr>
					<td><input type="checkbox" name="ids" id="id" value="${flow.id}"/></td>
					<td>${flow.flowCode}</td>
					<td>${flow.version}</td>
					<td>${flow.flowName}</td>
					<td>${flow.flowDesc}</td>
					<td>
						<a href="${ctx}/tec/flow/form?id=${flow.id}">修改</a>
						<a href="${ctx}/tec/flow/delete?id=${flow.id}" onclick="return confirmx('确认要删除该工艺流程信息吗？',this.href)">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 4.分页 -->
	<div class="pagination">${page}</div>
</body>
</html>