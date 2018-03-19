<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>暂未使用</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<shiro:hasPermission name="tsy:tsyPlanner:edit"><li><a href="${ctx}/tsy/tsyPlanner/form">计划部录入</a></li
		></shiro:hasPermission>
		<li><a href="${ctx}/tsy/tsyPlanner/noFeed">待投料</a></li>
		<li><a href="${ctx}/tsy/tsyPlanner/feed">投料信息</a></li>
		<li><a href="${ctx}/tsy/tsyPlanner/hasFeed">已投料</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tsyProductInfo" action="${ctx}/tsy/tsyPlanner/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>查询条件：<input type="text" name="" value="" style="width: 8em">&nbsp;</li>
			<li>查询条件：<input type="text" name="" value="" style="width: 8em">&nbsp;</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center">序号</th>
				<th style="text-align: center">生产型号</th>
				<th style="text-align: center">宽</th>
				<th style="text-align: center">长</th>
				<th style="text-align: center">PNL-SET</th>
				<th style="text-align: center">SET-PCS</th>
				<th style="text-align: center">下单时间</th>
				<th style="text-align: center">备注</th>
				<shiro:hasPermission name="tsy:tsyPlanner:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tsyProductInfo" varStatus="tpi">
			<tr>
				<td style="text-align: center">${tpi.count}</td>
				<td style="text-align: center">${tsyProductInfo.createModel}</td>
				<td style="text-align: center">${tsyProductInfo.wide}</td>
				<td style="text-align: center">${tsyProductInfo.leng}</td>
				<td style="text-align: center">${tsyProductInfo.pnlSet}</td>
				<td style="text-align: center">${tsyProductInfo.setPcs}</td>
				<td style="text-align: center"><a href="${ctx}/tsy/tsyProductInfo/form?id=${tsyProductInfo.id}">
					<fmt:formatDate value="${tsyProductInfo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td style="text-align: center">
						${tsyProductInfo.remarks}
				</td>
				<shiro:hasPermission name="tsy:tsyPlanner:edit"><td>
    				<a href="${ctx}/tsy/tsyPlanner/form?id=${tsyPlanner.id}">修改</a>
					<a href="${ctx}/tsy/tsyPlanner/delete?id=${tsyPlanner.id}" onclick="return confirmx('确认要删除该产品信息表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>