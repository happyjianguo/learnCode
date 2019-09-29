<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>终端资料设定</title>
	<fmt:setBundle basename="MyResource"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<fmt:message key='StylePath' />style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />common.js"></script>
	<script language="javascript" src="<fmt:message key='JavaScriptPath' />meizzDate.js"></script>
	<script language="JavaScript" src="<fmt:message key='JavaScriptPath' />GetDate.js"></script>
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />DatePicker/WdatePicker.js" ></script>
	<script language="javascript">
	
	function saveClick()
	{
	    var flag1=/^[0-9]{8}$/; 
	    var flag2=/^A[0-9]{7}$/;
		var terminalId=document.forms[0].terminalId.value;
		var edcType=document.forms[0].edcType.value;
		var softVer=document.forms[0].softVer.value;
		var busRoleId=document.forms[0].busRoleId.value;
			if(terminalId.length!=8){
				alert("终端号必须满足8位！！！");
				document.forms[0].terminalId.focus(); 
				return false;
			}
			if(0==edcType.indexOf("STD")){
				if(flag1.test(terminalId)==false){
					alert("终端设备型号为STD的终端号必须8位全部为数字！！！");
					document.forms[0].terminalId.focus(); 
					return false;
				}
			}
			if(0==edcType.indexOf("APP")){
				if(flag2.test(terminalId)==false){
					alert("终端设备型号为APP的终端号必须第1位为A其余7位为数字！！！");
					document.forms[0].terminalId.focus(); 
					return false;
				}
			}
			
			if(softVer=="mul"&&(busRoleId==""||busRoleId==null)){
	  			alert("请选择业务角色！！！");
				return false;
	  		}
			
		document.forms[0].method.value="createEdcTerminal";
		return validateEdcTerminalForm(document.forms[0]);		
	}
	
	function backClick()
	{
		document.forms[0].method.value="queryEdcTerminal";
	}
	/*****************************************/
	
	var req;
	function init() {
		if(window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	//删除后面空格
	String.prototype.RTrim   =   function(){   
		return   this.replace(/(\s*$)/g,"");   
	}
	/***************商户编号验证*****************/
	function checkMerchantId(merchantId) {
	    if(merchantId==null||merchantId==""){
	       alert("商户编号不能为空！！！");
	    }else{
			init();
			var url="edcCheck.do?method=checkMerchantId&merchantId="+merchantId;
			req.open("POST", url, true);
			req.onreadystatechange = merchantIdCallback;
			req.send(null);
		}
    }
    
	function merchantIdCallback() {
	 if(4 == req.readyState) {
		if(200 == req.status) {
		    if(req.responseText=='false'){
		       alert("商户编号不存在，请重新输入！！！");
		       document.forms[0].merchantId.value='';
		       document.forms[0].merchanName.value='';
		    }else{
		       document.forms[0].merchanName.value=req.responseText;
		    }
		}
	  }
    }
    
    /*****************终端编号验证***********************/
    function checkTerminalId(terminalId) {
        var merchantId=document.forms[0].merchantId.value ;
	    if(terminalId==null||terminalId==""||merchantId==null||merchantId==""){
	       alert("商户编号|终端编号不能为空！！！");
	    }else{
			init();
			//var url="edcCheck.do?method=checkTerminalId&terminalId="+terminalId;
			var url="edcCheck.do?method=checkMerchantTerminalId&merchantId="+merchantId+"&terminalId="+terminalId;
			req.open("POST", url, true);
			req.onreadystatechange = terminalIdCallback;
			req.send(null);
			
		}
    }
    
	function terminalIdCallback() {
	 if(4 == req.readyState) {
		if(200 == req.status) {
		    if(req.responseText!='false'){
		       alert("该商户下终端编号已存在，请重新输入！！！");
		       document.forms[0].terminalId.value='';
		    }
		}
	  }
    }
    
	
	function findBusRoleList(obj)
	{
	  if(obj.value=="mul"){
	  	document.getElementsByName("busRoleId")[0].style.display = "block";
	  	init();
 		var url="edcTerminal.do?method=findBusRoleList";
 			req.open("POST", url, true);
 		req.onreadystatechange = findBusRoleListCallback;
 		req.send(null);
	  }else{
	  	document.getElementsByName("busRoleId")[0].style.display = "none";
	  }
	}
	
	function findBusRoleListCallback() {
		if(4 == req.readyState) {
			if(200 == req.status) {
				if(req.responseText=='false'){
					alert("请确认业务角色是否存在！");
				}else{
					document.forms[0].busRoleId.options.length = 0;
					var str = req.responseText;
					var areaList = str.split("|");
					if (areaList.length > 1) {
						document.forms[0].busRoleId.options.add(new Option("--请选择业务角色--", ""));
						for (var i = 0; i < areaList.length-1;i=i+2) {
							document.forms[0].busRoleId.options.add(new Option(areaList[i].RTrim()+"--"+areaList[i+1].RTrim(),areaList[i+1].RTrim()));
						}
					}					
				}			
			}
		}
	}
	</script>
</head>
<shiro:lacksPermission name="posp:terminal:add">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:terminal:add">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				新增终端资料
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="edcTerminalForm" />
				<html:errors />
				<html:form action="/edcTerminal">

					<html:hidden property="method" value="createEdcTerminal" />

					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">

						<tr>
							<td class="table2_td_title">
								商户编号:
							</td>
							<td class="table2_td">
								<html:text styleId="merchantId" property="id.merchantId" size="25" maxlength="15" onblur="checkMerchantId(this.value)" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								商户名称:
							</td>
							<td class="table2_td">
								<html:text styleId="merchanName" property="id.merchanName" size="25" readonly="true" maxlength="15"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								 终端号：
							</td>
							<td class="table2_td">
								<html:text styleId="terminalId" property="id.terminalId" size="25"  maxlength="8" onblur="checkTerminalId(this.value)" onkeyup="this.value=this.value.replace(/\W/g,'').toUpperCase()"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								终端状态：
							</td>
							<td class="table2_td">
								<html:select property="terminalStat">
								 <option value="Y">正常</option>
								 <option value="N">冻结</option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								终端设备型号：
							</td>
							<td class="table2_td">
								<html:select property="edcType">
								  <html:option value=""></html:option>
								  <html:option value="STD">STD</html:option>
							      <html:option value="APP">APP</html:option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								设备规格说明：
							</td>
							<td class="table2_td">
								<html:text property="edcDoc" size="25"  maxlength="30"/>
								
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								打印机类型：
							</td>
							<td class="table2_td">
								<html:text property="printerType" size="25"  maxlength="4"/>
								
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								PIN PAD类型：
							</td>
							<td class="table2_td">
								<html:text property="pinpadType" size="25"  maxlength="4"/>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								终端类型：
							</td>
							<td class="table2_td">
								<html:select property="softVer" onchange="findBusRoleList(this)">
								  <html:option value="">请选择</html:option>
								  <html:option value="mul">多场景终端</html:option>
							      <html:option value="common">普通终端</html:option>
								</html:select>
								<font color="red">*</font>
								
						
								<html:select property="busRoleId" style="width:220px; display: none;">
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								参数下载标志：
							</td>
							<td class="table2_td">
								<html:select property="downloadFlag">
								 <option value="0">需要安装</option>
								 <option value="1">无需安装</option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								参数下载模式：
							</td>
							<td class="table2_td">0-无
								<html:hidden property="downloadMode" value="0"/>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								安装日期：
							</td>
							<td class="table2_td">
								<html:text property="setDate" size="25" onclick="WdatePicker({dateFmt:'yyyyMMdd'})" styleClass="Wdate" />
								<font color="red">*</font>							
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								安装地点：
							</td>
							<td class="table2_td">
								<html:text property="setAddr" size="25"  maxlength="30"/>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2" class="table2_btn">
								<input type="image" src="<fmt:message key='CommonImagePath' />btnSave.gif" onclick="return saveClick()">
								&nbsp;
								<input type="image" src="<fmt:message key='CommonImagePath' />btnBack.gif" onclick="return backClick()">
							</td>
						</tr>
					</table>
					<!-- 维护视图状态的隐藏域 -->
					<%@include file="../common/getDisplayParams1.jsp"%>
					<input type="hidden" name="search_sort" value="<c:out value="${param.search_sort}"/>" />
					<input type="hidden" name="search_name" value="<c:out value="${param.search_name}"/>" />
					<!-- 维护视图状态的隐藏域 -->
					</html:form>
			</td>
		</tr>
	</table>
</body>
</shiro:hasPermission>
</html:html>



