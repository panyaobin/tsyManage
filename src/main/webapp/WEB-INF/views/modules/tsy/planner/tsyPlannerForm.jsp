<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>计划录入产品信息表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<shiro:hasPermission name="tsy:tsyPlanner:edit"><li class="active"><a href="${ctx}/tsy/tsyPlanner/form">计划部录入
			</a></li
		></shiro:hasPermission>
		<li><a href="${ctx}/tsy/tsyPlanner/noFeed">待投料</a></li>
		<li><a href="${ctx}/tsy/tsyPlanner/feed">投料信息</a></li>
		<li><a href="${ctx}/tsy/tsyPlanner/hasFeed">已投料</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tsyProductInfo" action="${ctx}/tsy/tsyPlanner/save?tag='planner'"
			   method="post"
			   class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<p style="font-size: 25px;text-align: center;width: 900px">计划部产品信息录入</p>
		<br>
		<table  height="500px"; width="900px"; border="1px" style="text-align: center;">
			<tr>
				<td colspan="10" style="text-align: left">基本信息</td>
			</tr>
			<tr>
				<td>生产型号</td>
				<td style="width: 100px;height: 25px;"><input name="createModel" style="width: 100px;font-weight: bolder;font-size: 16px;"
															  value="${tsyPlanner.createModel}" type="text"></td>
				<td>宽</td>
				<td><input name="wide" style="width: 100px;font-weight: bolder;font-size: 16px;" type="text" value="250"
						   tabindex="-1"></td>
				<td>长</td>
				<td style="width: 100px;height: 25px;"><input name="leng"
															  style="width: 100px;font-weight: bolder;font-size:
				16px;" type="text"></td>
				<td>PNL-SET</td>
				<td style="width: 75px;"><input name="pnlSet" style="width: 75px;font-weight: bolder;font-size: 16px;" type="text"></td>
				<td>SET-PCS</td>
				<td style="width: 75px;"><input name="setPcs" style="width: 75px;font-weight: bolder;font-size: 16px;" type="text"></td>
			</tr>
			<tr>
				<td colspan="10" style="text-align: left">用料信息</td>
			</tr>
			<tr>
				<td>基材类型</td>
				<td colspan="2" style="height: 25px">
					<form:select path="baseType" class="input-medium">
						<form:options items="${fns:getDictList('tsy_base_type')}" itemLabel="label" itemValue="value"
									  htmlEscape="false"/>
					</form:select>
					<%--<select name="" style="width: 150px;height: 26px;">
						<option value="0">12/12.5无胶电解是</option>
						<option value="1">基材2</option>
						<option value="2">基材3</option>
						<option value="3">基材4</option>
					</select>--%>
				</td>

				<td>包封类型</td>
				<td colspan="2">
					<form:select path="packType" class="input-medium">
						<form:options items="${fns:getDictList('tsy_pack_type')}" itemLabel="label" itemValue="value"
									  htmlEscape="false"/>
					</form:select>

					<%--<select name="" style="width: 140px;height: 26px;">
						<option value="0">13/15生生世世是</option>
						<option value="1">基材2</option>
						<option value="2">基材3</option>
						<option value="3">基材4</option>
					</select>--%>
				</td>
				<td>电磁模类型</td>
				<td colspan="2">
					<form:select path="filmType" class="input-medium">
						<form:options items="${fns:getDictList('tsy_film_type')}" itemLabel="label" itemValue="value"
									  htmlEscape="false"/>
					</form:select>
					<%--<select name="" style="width: 108px;height: 26px;">
						<option value="0">杀杀杀</option>
						<option value="1">基材2</option>
						<option value="2">基材3</option>
						<option value="3">基材4</option>
					</select>--%>
				</td>
				<td></td>
			</tr>
			<tr>
				<td colspan="10" style="text-align: left">工艺信息</td>
			</tr>
			<tr>
				<td>阻焊</td>
				<td><input type="checkbox" value="0" name="isMask" style="width: 20px;height: 20px"></td>
				<td>热固绿油</td>
				<td><input type="checkbox" value="0" name="isHot" style="width: 20px;height: 20px"></td>
				<td>正面电磁模</td>
				<td><input type="checkbox" value="0" name="ctFilm" style="width: 20px;height: 20px"></td>
				<td>反面电磁模</td>
				<td colspan="2"><input value="0" name="cbFilm" type="checkbox" style="width: 20px;height: 20px"></td>
				<td></td>
			</tr>
			<tr>
				<td colspan="10" style="text-align: left">冲切信息</td>
			</tr>
			<tr>
				<td>正包</td>
				<td style="height: 25px"><input name="ctCount" style="width: 100px;font-weight: bolder;font-size: 16px;"
												type="text"></td>
				<td>外形一</td>
				<td style="width: 100px"><input name="loCount" style="width: 103px;font-weight: bolder;font-size: 16px;"
												type="text"></td>
				<td>正面电磁模</td>
				<td><input name="ftCount" style="width: 100px;font-weight: bolder;font-size: 16px;" type="text"></td>
				<td colspan="4"></td>
			</tr>
			<tr>
				<td>反包</td>
				<td style="height: 25px"><input name="cbCount" style="width: 100px;font-weight: bolder;font-size: 16px;"
												type="text"></td>
				<td>外形二</td>
				<td style="height: 25px"><input name="ltCount" style="width: 100px;font-weight: bolder;font-size: 16px;"
												type="text"></td>
				<td>反面电磁模</td>
				<td style="height: 25px"><input name="fbCount" style="width: 100px;font-weight: bolder;font-size: 16px;"
												type="text"></td>
				<td colspan="4"></td>
			</tr>
			<tr>
				<td colspan="10">
					<textarea style="width: 55em;font-weight: bolder;font-size: 16px;" placeholder="备注：" name="remarks"
							  id=""
							  cols="30"
							  rows="8"></textarea>
				</td>
			</tr>
		</table>
		<div style="width: 67em;float: right;margin-top: 5px">
			<input id="btnSubmit" style="" class="btn btn-primary" type="submit" value="录入"/>
		</div>
	</form:form>
</body>
</html>