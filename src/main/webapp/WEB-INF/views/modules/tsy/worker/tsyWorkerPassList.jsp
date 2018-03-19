<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工程部待制作管理</title>
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
		<li><a href="${ctx}/tsy/tsyWorker">工程部待制作</a></li>
		<shiro:hasPermission name="tsy:tsyCreater:edit"><li><a href="${ctx}/tsy/tsyWorker/form">工程部录入</a></li></shiro:hasPermission>
		<li  class="active"><a href="${ctx}/tsy/tsyWorker/pass">工程部已制作</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tsyCreater" action="${ctx}/tsy/tsyCreater/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>客户代码：<input type="text" name="cusName" value="${vplOrderImport.cusName}" style="width: 8em">&nbsp;&nbsp;&nbsp;</li>
			<li>客户代码：<input type="text" name="cusName" value="${vplOrderImport.cusName}" style="width: 8em">&nbsp;&nbsp;&nbsp;</li>
			<li>客户代码：<input type="text" name="cusName" value="${vplOrderImport.cusName}" style="width: 8em">&nbsp;&nbsp;&nbsp;</li>
			<li>客户代码：<input type="text" name="cusName" value="${vplOrderImport.cusName}" style="width: 8em">&nbsp;&nbsp;&nbsp;</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center">序号</th>
				<th style="text-align: center">客户代码</th>
				<th style="text-align: center">客户型号</th>
				<th style="text-align: center">下单日期</th>
				<th style="text-align: center">生产型号</th>
				<th style="text-align: center">批次</th>
				<th style="text-align: center">订单类型</th>
				<th style="text-align: center">交货日期</th>
				<th style="text-align: center">包模要求</th>
				<th style="text-align: center">外型要求</th>
				<th style="text-align: center">测试架要求</th>
				<%--<th>特殊要求</th>--%>
				<%--<th>备注</th>--%>
				<shiro:hasPermission name="tsy:tsyCreater:edit"><th style="text-align: center">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:if test="${page.list==null || page.list.size()<=0}">
			<tr>
				<td colspan="12" style="text-align: center">对不起，没有数据……</td>
			</tr>
		</c:if>
		<c:forEach items="${page.list}" var="tsyCreater" varStatus="num">
			<tr>
				<td style="text-align: center">${num.count}</td>
				<td style="text-align: center">${tsyCreater.cusNo}</td>
				<td style="text-align: center">${tsyCreater.cusModel}</td>
				<td style="text-align: center">
					<fmt:formatDate value="${tsyCreater.orderDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td style="text-align: center">${tsyCreater.createModel}</td>
				<td style="text-align: center">${tsyCreater.lotNum}</td>
				<td style="text-align: center">${tsyCreater.type==0?"样品":"批量"}</td>
				<td style="text-align: center">
					<fmt:formatDate value="${tsyCreater.deliveDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td style="text-align: center">${tsyCreater.mouldeFlag==0?"是":"否"}</td>
				<td style="text-align: center">${tsyCreater.lookerFlag==0?"是":"否"}</td>
				<td style="text-align: center">${tsyCreater.testerFlag==0?"是":"否"}</td>
				<%--<td>${tsyCreater.cusRequire}</td>--%>
				<%--<td>
					${tsyCreater.remarks}
				</td>--%>
				<shiro:hasPermission name="tsy:tsyCreater:edit"><td style="text-align: center">
    				<a href="${ctx}/tsy/tsyWorker/form?createModel=${tsyCreater.createModel}&id=${tsyCreater.id}">录入</a>
					<a href="${ctx}/tsy/tsyCreater/delete?id=${tsyCreater.id}" onclick="return confirmx('确认要删除该市场部订单明细表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>