<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>市场部订单明细表管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#cusNo").focus();
            //页面加载的时候,先查出今天的年月日,然后根据年月日模糊查询出当天日期所有订单,倒序查出第一个,+1
            var year=new Date().getFullYear().toString();
            var month=new Date().getMonth()+1;
            var day=new Date().getDate().toString();
            if (month >= 1 && month <= 9) {
                month = "0" + month;
            }
            if (day >= 0 && day <= 9) {
                day = "0" + day;
            }
            var today=year+month+day;//算出当天日期  yyyy-MM-dd
            var oid=$("#oId").val();
            var toid=$("#toId").val();
            var time;
            //匹配数据库
            $.post("${ctx}/tsy/tsyCreater/selId",{"orderId":today},function(msg){
                debugger
              if(msg==""){
                time=today+"001";
              }else{
                time=parseInt(msg)+1;
              }
              if(oid!=""){
                time=toid;
              }
                $("#orderId").val(time);
            });




            $("#inputForm").validate({
                submitHandler: function (form) {
                    //loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
            //新增默认显示系统当前时间
            var d = new Date();
            var str = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
            $("#orderDate").val(str);
        });
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/tsy/tsyCreater/form?id=${tsyCreater.id}">市场部下单<shiro:hasPermission
            name="tsy:tsyCreater:edit">${not empty tsyCreater.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission
            name="tsy:tsyCreater:edit">查看</shiro:lacksPermission></a></li>
    <li><a href="${ctx}/tsy/tsyCreater/">下单登记表</a></li>
   <%-- <li><a href="${ctx}/tsy/tsyCreater/test">测试</a></li>--%>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="tsyCreater" action="${ctx}/tsy/tsyCreater/save" method="post"
           class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <input type="hidden" name="" id="oId" value="${tsyCreater.id}">
    <input type="hidden" name="" id="toId" value="${tsyCreater.orderId}">
    <table style="height: 70%;width:40em;font-size: 1.5em;border-spacing:5px;" border="1">
        <tr style="font-size: 1.5em;text-align: center;font-family: GB2312 KaiTi_GB2312;height: 2em">
            <td colspan="6">
                <lable>生产通知单</lable>
            </td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;订单类型&nbsp;&nbsp;</td>
            <td style="text-align: center">
                <c:choose>
                    <c:when test="${tsyCreater.type==0}">
                        <input type="radio" name="type" value="0" title="样品" checked=""><span style="font-size:0.8em">样品</span>

                        <input type="radio" name="type" value="1" title="订单"><span style="font-size:0.8em">批量</span>
                    </c:when>
                    <c:when test="${tsyCreater.type==1}">
                        <input type="radio" name="type" value="0" title="订单"><span style="font-size:0.8em">样品</span>
                        <input type="radio" name="type" value="1" title="订单"checked=""><span style="font-size:0.8em">批量</span>
                    </c:when>
                    <c:otherwise>
                        <input type="radio" name="type" value="0" title="样品" checked=""><span style="font-size:0.8em">样品</span>
                        <input type="radio" name="type" value="1" title="订单"><span style="font-size:0.8em">批量</span>
                    </c:otherwise>
                </c:choose>

            </td>
            <td>&nbsp;&nbsp;订单数量&nbsp;&nbsp;</td>
            <td><input type="text" name="orderSum"  value="${tsyCreater.orderSum}"
                       style="border: 0;font-size:1em;width: 120px;"
                       required="required"></td>
            <td>&nbsp;&nbsp;订单编号&nbsp;&nbsp;</td>
            <td><input type="text" name="orderId" id="orderId" value="${tsyCreater.orderId}" style="border: 0;font-size:1em;width:
             8em;" required></td>
        </tr>
        <tr style="height: 1.5em">
            <td>&nbsp;&nbsp;客户代码&nbsp;&nbsp;</td>
            <td style="width: 11em"><input type="text" name="cusNo" id="cusNo" value="${tsyCreater.cusNo}"
                       style="border: 0;font-size:1em;width: 122px;" required></td>
            <td>&nbsp;&nbsp;客户型号&nbsp;&nbsp;</td>
            <td style="width: 11em"><input type="text" name="cusModel" value="${tsyCreater.cusModel}"
                       style="border: 0;font-size:1em;width: 120px" required></td>
            <td>&nbsp;&nbsp;下单日期&nbsp;&nbsp;</td>
            <td>
                <c:choose>
                    <c:when test="${not empty tsyCreater.id}">
                        <input name="orderDate"  style="width: 8em;font-size:1em" type="text" readonly="readonly"
                               maxlength="20"
                               class="input-medium Wdate"
                               value="<fmt:formatDate value="${tsyCreater.orderDate}" pattern="yyyy-MM-dd"/>"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
                    </c:when>
                    <c:otherwise>
                        <input name="orderDate"  style="width: 8em;font-size:1em"  id="orderDate" type="text"
                               readonly="readonly"
                               maxlength="20"
                               class="input-medium Wdate "
                               value="<fmt:formatDate value="${tsyCreater.orderDate}" pattern="yyyy-MM-dd"/>"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>

        <tr style="height: 1.5em">
            <td>&nbsp;&nbsp;生产型号&nbsp;&nbsp;</td>
            <td><input type="text" name="createModel" value="${tsyCreater.createModel}"
                       style="border: 0;font-size:1em;width: 120px;" required></td>
            <td>&nbsp;&nbsp;批&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;次&nbsp;&nbsp;</td>
            <td><input type="text" name="lotNum" value="${tsyCreater.lotNum}"
                       style="border: 0;font-size:1em;width: 120px" required></td>
            <td>&nbsp;&nbsp;交货日期&nbsp;&nbsp;</td>
            <td style="width: 13em">
                <input name="deliveDate"  style="width: 8em;font-size:1em;" type="text" readonly="readonly"
                       maxlength="20" required
                       class="input-medium Wdate "
                       value="<fmt:formatDate value="${tsyCreater.deliveDate}" pattern="yyyy-MM-dd"/>"
                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
            </td>

        </tr>

        <tr style="font-family: GB2312 KaiTi_GB2312;height: 1.5em">
            <td colspan="6">
                <lable>&nbsp;&nbsp;开模要求</lable>
            </td>
        </tr>

        <tr style="height: 1.5em">
            <td>&nbsp;&nbsp;包模模具&nbsp;&nbsp;</td>
            <td style="text-align: center">
                <c:choose>
                    <c:when test="${tsyCreater.mouldeFlag==0}">
                        <input type="radio" name="mouldeFlag" value="0" title="是" checked=""><span style="font-size:0.8em">是</span>
                        <input type="radio" name="mouldeFlag" value="1" title="否"><span style="font-size:0.8em">否</span>
                    </c:when>
                    <c:when test="${tsyCreater.mouldeFlag==1}">
                        <input type="radio" name="mouldeFlag" value="0" title="是"><span style="font-size:0.8em">是</span>
                        <input type="radio" name="mouldeFlag" value="1" title="否" checked=""><span style="font-size:0.8em">否</span>
                    </c:when>
                    <c:otherwise>
                        <input type="radio" name="mouldeFlag" value="0" title="是" checked=""><span
                            style="font-size:0.8em">是</span>
                        <input type="radio" name="mouldeFlag" value="1" title="否"><span style="font-size:0.8em">否</span>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>&nbsp;&nbsp;外型模具&nbsp;&nbsp;</td>
            <td style="text-align: center">
                <c:choose>
                    <c:when test="${tsyCreater.lookerFlag==0}">
                        <input type="radio" name="lookerFlag" value="0" title="是" checked=""><span style="font-size:0.8em">是</span>
                        <input type="radio" name="lookerFlag" value="1" title="否"><span style="font-size:0.8em">否</span>
                    </c:when>
                    <c:when test="${tsyCreater.lookerFlag==1}">
                        <input type="radio" name="lookerFlag" value="0" title="是"><span style="font-size:0.8em">是</span>
                        <input type="radio" name="lookerFlag" value="1" title="否" checked=""><span style="font-size:0.8em">否</span>
                    </c:when>
                    <c:otherwise>
                        <input type="radio" name="lookerFlag" value="0" title="是" checked=""><span style="font-size:0.8em">是</span>
                        <input type="radio" name="lookerFlag" value="1" title="否"><span style="font-size:0.8em">否</span>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>&nbsp;&nbsp;测&nbsp;&nbsp;试&nbsp;&nbsp;架&nbsp;&nbsp;</td>
            <td style="text-align: center">
                <c:choose>
                    <c:when test="${tsyCreater.testerFlag==0}">
                        <input type="radio" name="testerFlag" value="0" title="是" checked=""><span style="font-size:0.8em">是</span>
                        <input type="radio" name="testerFlag" value="1" title="否"><span style="font-size:0.8em">否</span>
                    </c:when>
                    <c:when test="${tsyCreater.testerFlag==1}">
                        <input type="radio" name="testerFlag" value="0" title="是"><span style="font-size:0.8em">是</span>
                        <input type="radio" name="testerFlag" value="1" title="否" checked=""><span style="font-size:0.8em">否</span>
                    </c:when>
                    <c:otherwise>
                        <input type="radio" name="testerFlag" value="0" title="是" checked=""><span style="font-size:0.8em">是</span>
                        <input type="radio" name="testerFlag" value="1" title="否"><span style="font-size:0.8em">否</span>
                    </c:otherwise>
                </c:choose>

            </td>
        </tr>

        <tr>
            <td colspan="6">
                <textarea placeholder="客户特殊要求：" style="width: 57.7em;height: 7em;font-size: 18px;" name="cusRequire"
                          cols="30"
                          rows="10">${tsyCreater.cusRequire}</textarea></td>
        </tr>

        <tr>
            <td colspan="6">
                <textarea placeholder="备注：" style="width: 57.7em;height: 7em;font-size: 18px;" name="remarks" cols="30"
                          rows="10">${tsyCreater.remarks}</textarea></td>
        </tr>

    </table>
    <div style="width: 55em;float: right;margin-top: 5px">
        <input id="btnSubmit" style="" class="btn btn-primary" type="submit"
               value="${not empty tsyCreater.id?'修改':'下单'}"/>
    </div>
</form:form>
</body>
</html>