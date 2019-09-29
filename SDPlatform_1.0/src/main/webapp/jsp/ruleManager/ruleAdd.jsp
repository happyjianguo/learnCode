<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.*,cn.yufu.posp.terminalmanager.domain.model.*"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>风险规则</title>
	<fmt:setBundle basename="MyResource"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<fmt:message key='StylePath' />style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />common.js"></script>
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />check.js"></script>
	<script language="javascript" src="<fmt:message key='JavaScriptPath' />meizzDate.js"></script>
	<script language="JavaScript" src="<fmt:message key='JavaScriptPath' />GetDate.js"></script>
    <script type="text/javascript" src="<fmt:message key='JavaScriptPath' />jquery-1.4.min.js" ></script>
	<script language="javascript">
	
	function saveClick()
	{
		document.forms[0].method.value="createItem";
		var status = $("#status").val();
		var desc = $("#desc").val();
		var configCycle = $("#configCycle").val();
		var param = $("#param").val().split(";");
		var list = '';
		$.each( param, function(i, n){
			if(n!=null&&n!="")
				list+=n+"|"+$("#"+n).val()+";";
			});
		if(desc==null||trim(desc)==""||status==null||trim(status)==""||configCycle==null||trim(configCycle)==""){
			alert("必填不可为空！");
			return false;
		}
		$("#param").val(list);
		return document.forms[0].submit();
	
	}
	function backClick()
	{
		document.forms[0].method.value="queryTempAll";
		document.forms[0].submit();
	}
	</script>
	<script type="text/javascript" language="javascript">

var req;
		
function init() {
	if(window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
}
		
function leavetext() {
	
	if ("" == document.merchantSwitchForm.merchantId.value)
    {
	   // alert("商户编号不能为空，请重新输入！");
	    //form1.merchantId.focus()
	    return false;
	}
	
	if (document.merchantSwitchForm.merchantId.value.length !=15)
	{
	    alert("商户编号必须为十五位，请重新输入！");
	    //form1.merchantId.focus()
	    return false;
	}
	
	
	init();
	var url = "merchantSwitch.do?method=findMerchantName&merchantId=" + escape(document.merchantSwitchForm.merchantId.value);
	req.open("POST", url, true);
	req.onreadystatechange = callback;
	req.send(null);
	
}
function callback() {
	if(4 == req.readyState) {

		if(200 == req.status) {
			//alert(req.responseText);
			//eval(req.responseText);
			document.merchantSwitchForm.merchantName.value = req.responseText;
			
		}
	}
	
}
//验证是否为数字
function checknumber(String) 
{ 
	var Letters = "1234567890"; 
	var i; 
	var c; 
	for( i = 0; i < String.length; i ++ ) 
	{ 
	c = String.charAt( i ); 
	if (Letters.indexOf( c ) ==-1) 
	{ 
	return true; 
	} 
	} 
	return false; 
} 
function selChange(id){
	if($("#"+id.id).val()=='OTHER')
		$("#"+id.id+"txtDiv").html('<input id="'+id.id+'txt" name="'+id.id+'txt"/>');
	else
		$("#"+id.id+"txtDiv").html('');
}
</script>
<script type="text/javascript" language="javascript">
$(document).ready(function() {
	var tbody = "";
	var param = "";
	<c:forEach var="model" items="${ruleTemp.ruleTempParam}">
	tbody += '<tr><td class="table2_td_title" >规则参数<c:out value="${model.ruleTempReg}"/> </td>'+
	'<td class="table2_td">';
	param += '<c:out value="${model.ruleTempReg}"/>;';
	<c:if test='${model.ruleTempRegList eq "Y" and model.ruleTempRegType eq "SELECT"}'>
	tbody += '<select id="<c:out value="${model.ruleTempReg}"/>" name="<c:out value="${model.ruleTempReg}"/>" onchange="selChange(this);">'+
		 	 '<option value="">--请选择--</option>';
	<c:forEach var="paramList" items="${model.ruleTempParamList}">
	tbody += '<option value="<c:out value="${paramList.key}"/>"><c:out value="${paramList.value}"/></option>'
	</c:forEach>
	tbody += '</select><span><font color="red">*</font></span><span id="<c:out value="${model.ruleTempReg}"/>txtDiv"></span>';
	</c:if>
	<c:if test='${model.ruleTempRegList eq "N" and model.ruleTempRegType eq "INPUT"}'>
	tbody += '<input id="<c:out value="${model.ruleTempReg}"/>" value="<c:out value="${model.ruleTempRegDefault}"/>"><span><font color="red">*</font></span>';
	</c:if>
	tbody  += '</td></tr>';
	</c:forEach>
	//处理后面返回数据开始
	$("#param").val(param);
	$("#main").append(tbody);
	//处理后面返回数据结束
});
</script>
</head>
<shiro:lacksPermission name="posp:ruleManager:addRule">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:ruleManager:addRule">
<body>
	<!--------------以下Table为路径-------->
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30" class="place">
				<img src="<fmt:message key='CommonImagePath' />place_btn.gif" width="12" height="11">   
				当前位置：收单系统管理平台 >> 规则管理 >> 风险规则
			</td>
		</tr>
		<tr>
			<td height="10">
			</td>
		</tr>
	</table>
	<!--------------Table为路径结束-------->
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">  
				增加风险规则 </td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:errors />
				<html:form action="/ruleManager" method="post" onsubmit="return false;">
					<input id="param" name="param" type="hidden"/>
					<input id="ruleTempNo" name="ruleTempNo" type="hidden" value="<c:out value="${ruleTemp.ruleTempNo}"/>"/>
					<html:hidden property="method" value="createItem" />
					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
					<thead>
						<tr>
							<td class="table2_td_title">模板编号</td>
							<td class="table2_td"><c:out value="${ruleTemp.ruleTempNo}"/></td>
						</tr>
						<tr>
							<td class="table2_td_title">模板规则</td>
							<td class="table2_td"><c:out value="${ruleTemp.ruleTempDesc}"/></td>
						</tr>
						<tr>
							<td class="table2_td_title">规则ID</td>
							<td class="table2_td" id="tempReg"><input id="configId" name="configId" value="<c:out value="${ruleTemp.ruleTempNo}"/>_100XX"/></td>
						</tr>
						<tr>
							<td class="table2_td_title">规则</td>
							<td class="table2_td" ><c:out value="${ruleTemp.ruleTempReg}"/></td>
						</tr>
						<tr>
							<td class="table2_td_title">规则标题</td>
							<td class="table2_td"><input id="configTitle" name="configTitle" size="50" value=""/></td>
						</tr>
					</thead>
					<tbody id="main">
					</tbody>
					<tfoot>
						<tr>
							<td class="table2_td_title">
								处理动作:
							</td>
							<td class="table2_td">
								<select id="actionStatus" name="actionStatus">
									<option value = "0">无动作</option>
									<option value = "1">提醒</option>
									<option value = "2">移交</option>
									<option value = "3">托收</option>
									<option value = "4">拒绝</option>
									<option value = "5">管控</option>
								</select><span><font color="red">*</font></span>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								规则时点:
							</td>
							<td class="table2_td">
								<select id="agingMode" name="agingMode">
									<option value = "0">实时</option>
									<option value = "1">准实时</option>
								</select><span><font color="red">*</font></span>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								周期:
							</td>
							<td class="table2_td">
							<input id="configCycle" name="configCycle"/>
								<select id="cycleType" name="cycleType">
									<option value = "">--请选择--</option>
									<option value = "SS">秒</option>
									<option value = "MM" selected>分</option>
									<option value = "HH">时</option>
									<option value = "DD">天</option>
								</select><span><font color="red">*</font></span>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								规则状态:
							</td>
							<td class="table2_td">
								<select id="status" name="status">
									<option value = "">--请选择--</option>
									<option value = "S" selected>启用</option>
									<option value = "T">停用</option>
								</select><span><font color="red">*</font></span>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								描述:
							</td>
							<td class="table2_td">
								<textarea id="desc" name="desc" cols="40" rows="5"></textarea><span><font color="red">*</font></span>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4" class="table2_btn" >
								<input type="image" src="<fmt:message key='CommonImagePath' />btnSave.gif" onclick="return saveClick()">
								&nbsp;
								<input type="image" src="<fmt:message key='CommonImagePath' />btnBack.gif" onclick="return backClick()">
							</td>
						</tr>
					</tfoot>
					</table>
				</html:form>
			</td>
		</tr>
	</table>
</body>
</shiro:hasPermission>
</html:html>



