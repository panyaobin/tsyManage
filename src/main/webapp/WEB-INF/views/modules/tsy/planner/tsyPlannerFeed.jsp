<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>计划部投料表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#percent").focus();
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
			$("#percent").blur(function () {
			    //这里是自动运算 数量
                var wide=$("#wide").val();
			    var leng=$("#leng").val();
			    var setPcs=$("#setPcs").val();
			    var pnlSet=$("#pnlSet").val();
			    var orderSum=$("#orderSum").val();
			    var percent=$("#percent").val();
                if(percent!="") {
				 	var sum=(orderSum/setPcs/pnlSet)/(percent/100); //基材数量
					var area=wide*leng*Math.round(sum);	    //基材面积
					$("#count1").text(Math.round(sum));          			//基材数量
                    $("#count2").text((Math.round(area)/1000000).toFixed(2)); 			//基材面积
                    $("#count3").text(Math.round(sum)*2);          			//包封数量
                    $("#feedSum").val(Math.round(sum));											//隐藏域基材数量赋值
                    $("#feedArea").val((Math.round(area)/1000000).toFixed(2));					//隐藏域基材面积赋值
                }else{
                    $("#percent").attr("placeholder","非空");
                    $("#percent").focus();
				}
            });
			$("#spare").blur(function () {
			    //这里是自动运算 数量
                var wide=$("#wide").val();
			    var leng=$("#leng").val();
			    var setPcs=$("#setPcs").val();
			    var pnlSet=$("#pnlSet").val();
			    var orderSum=$("#orderSum").val();
			    var percent=$("#percent").val();
			    var spare=$("#spare").val();
                if(percent!="") {
                    var sum=(orderSum/setPcs/pnlSet)/(percent/100); 							//基材数量
					var area=wide*leng*(Math.round(sum)*2+parseInt(spare));
                    $("#count4").text((Math.round(area)/1000000).toFixed(2));          			//包封数量
                }else{
                    $("#percent").attr("placeholder","非空");
                    $("#percent").focus();
				}
            });
		});

        function checkNull() {
            if($("#percent").val()==""){
                $("#percent").attr("placeholder","非空");
                $("#percent").focus();
                return false;
			}
			return true;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
        <shiro:hasPermission name="tsy:tsyPlanner:edit"><li><a href="${ctx}/tsy/tsyPlanner/form">计划部录入
        </a></li
        ></shiro:hasPermission>
        <li><a href="${ctx}/tsy/tsyPlanner/noFeed">待投料</a></li>
        <li class="active"><a href="${ctx}/tsy/tsyPlanner/feed">投料信息</a></li>
        <li><a href="${ctx}/tsy/tsyPlanner/hasFeed">已投料</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tsyCreater" action="${ctx}/tsy/tsyPlanner/feeding" method="post"
			   class="form-horizontal">
		<%--<form:hidden path="id"/>--%>
		<input type="hidden" name="id" value="${tsyCreater.id}">
		<input type="hidden" name="wide"id="wide" value="${tsyProductInfo.wide}">
		<input type="hidden" name="leng" id="leng" value="${tsyProductInfo.leng}">
		<input type="hidden" name="setPcs" id="setPcs" value="${tsyProductInfo.setPcs}">
		<input type="hidden" name="pnlSet" id="pnlSet" value="${tsyProductInfo.pnlSet}">
		<input type="hidden" name="orderSum" id="orderSum" value="${tsyCreater.orderSum}">
		<input type="hidden" name="orderId" id="orderId" value="${tsyCreater.orderId}">
		<input type="hidden" name="cusNo" id="cusNo" value="${tsyCreater.cusNo}">
		<input type="hidden" name="feedArea" id="feedArea">
		<input type="hidden" name="feedSum" id="feedSum">
		<sys:message content="${message}"/>
		<p style="font-size: 25px;text-align: center;width: 900px">计划部投料信息</p>
		<br>
		<table  height="350px"; width="900px"; border="1px" style="text-align: center;">
			<tr>
				<td colspan="9" style="text-align: left">&nbsp;基本信息</td>
			</tr>
			<tr>
				<td>投料日期</td>
				<td>交货日期</td>
				<td>客户代码</td>
				<td>生产型号</td>
				<td>宽</td>
				<td>长</td>
				<td>拼版数量</td>
				<td>订单量</td>
				<td>良率</td>
			</tr>
			<tr style="font-weight: bolder">
				<td>今天</td>
				<td><fmt:formatDate value="${tsyCreater.deliveDate}" pattern="yyyy-MM-dd"/></td>
				<td>${tsyCreater.cusNo}</td>
				<td>${tsyCreater.createModel}</td>
				<td>${tsyProductInfo.wide}</td>
				<td>${tsyProductInfo.leng}</td>
				<td>${tsyProductInfo.setPcs}</td>
				<td>${tsyCreater.orderSum}</td>
				<td style="width: 6.5em;height: 2em">
					<input type="text" id="percent" style="width: 70px" name="percent" value="90">
				</td>
			</tr>
			<tr>
				<td colspan="9" style="text-align: left">&nbsp;物料清单</td>
			</tr>
			<tr>
				<td>基材</td>
				<td colspan="2">${fns:getDictLabel(tsyProductInfo.baseType, 'tsy_base_type', '')}</td>
				<td>数量</td>
				<td colspan="3"><span id="count1" name="feedSum"></span></td>
				<td>面积</td>
				<td><span id="count2" name="feedArea"></span></td>
			</tr>
			<tr>
				<td>包封</td>
				<td colspan="2">${fns:getDictLabel(tsyProductInfo.packType, 'tsy_pack_type', '')}</td>
				<td>数量</td>
				<td><span id="count3"></span></td>
				<td>备品</td>
				<td style="width: 6.5em;height: 2em">
					<input type="text" id="spare" style="width: 70px" name="spare" value="0">
				</td>
				<td>面积</td>
				<td><span id="count4"></span></td>
			</tr>
		</table>
		<div style="width: 67em;float: right;margin-top: 5px">
				<input id="btnSubmit" style="" class="btn btn-primary" onclick="return checkNull();" type="submit"	value="投料"/></div>
	</form:form>
</body>
</html>