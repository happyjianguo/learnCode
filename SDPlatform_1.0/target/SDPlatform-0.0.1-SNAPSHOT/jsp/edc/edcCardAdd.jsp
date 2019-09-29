<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>终端受理卡类</title>
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
	
	<script language="javascript">
	
	function saveClick()
	{
		document.forms[0].method.value="createEdcCard";
		return validateEdcCardForm(document.forms[0]);
	}
	function backClick()
	{
		document.forms[0].method.value="queryEdcCard";
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
	
	/***************商户编号验证*****************/
	function checkMerchantId(merchantId) {
	    if(merchantId==null||merchantId==""){
	       alert("商户编号不能为空！！！");
	       //document.forms[0].merchantId.focus();
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
		       document.forms[0].merchantName.value='';
		    }else{
		       document.forms[0].merchantName.value=req.responseText;
		    }
		}
	  }
    }
    
    /*****************终端编号验证***********************/
    
    function checkTerminalId() {
       var merchantId=document.forms[0].merchantId.value ;
       var terminalId=document.forms[0].terminalId.value ;
	    if(merchantId==null||merchantId==""){
	       alert("商户编号不能为空！！！");
	    }else if(terminalId==null||terminalId==""){
	       alert("终端编号不能为空！！！");
	    }else{
			init();
			var url="edcCheck.do?method=checkMerchantTerminalId&merchantId="+merchantId+"&terminalId="+terminalId;
			req.open("POST", url, true);
			req.onreadystatechange = terminalIdCallback;
			req.send(null);
			
		}
    }
    
	function terminalIdCallback() {
	 if(4 == req.readyState) {
		if(200 == req.status) {
		    if(req.responseText=='false'){
		       alert("终端编号不存在，请重新输入！！！");
		       document.forms[0].terminalId.value='';
		    }else{
		          //新增受理卡类型和银行卡类型
		          
			      document.forms[0].bankType.options.length=0;
	              document.forms[0].cardType.options.length=0;
	              
	              var str=req.responseText;
	              var tt=str.split("#");
	              var bankType=tt[0].split("|");
	              var cardType=tt[1].split("|");
	              if(bankType.length>1){
	                 document.forms[0].bankType.options.add(new Option("",""));
	                 for(var i=0;i<bankType.length-1;i=i+2){
		               document.forms[0].bankType.options.add(new Option(bankType[i],bankType[i+1])); 
	                 }
	              }
	              if(cardType.length>1){
	                 document.forms[0].cardType.options.add(new Option("",""));
	                 for(var i=0;i<cardType.length-1;i=i+2){
		               document.forms[0].cardType.options.add(new Option(cardType[i],cardType[i+1]));
	                 }
	              }
	              if(bankType.length<=1||cardType.length<=1){
	                 alert("此商户不能新增受理卡类，应为缺少受理卡类型或者银行类型");
	              }
		    }
		    
		}
	  }
    }
    
    /******************************************/
    function edcCardCheck(){
       var merchantId=document.forms[0].merchantId.value ;
       var terminalId=document.forms[0].terminalId.value ;
       var cardType=document.forms[0].cardType.value ;
       
       if(merchantId==null||merchantId==""){
	       alert("商户编号不能为空！！！");
	    }else if(terminalId==null||terminalId==""){
	       alert("终端编号不能为空！！！");
	    }else if(cardType==null||cardType==""){
	       alert("请选择受理卡类型！！！");
	    }else{
			init();
			var url="edcCheck.do?method=checkEdcCard&merchantId="+merchantId+"&terminalId="+terminalId+"&cardType="+cardType;
			req.open("POST", url, true);
			req.onreadystatechange = edcCardCallback;
			req.send(null);
		}
    }
    function edcCardCallback() {
	 if(4 == req.readyState) {
		if(200 == req.status) {
		    if(req.responseText=='false'){
		       alert("商户编号,终端号和受理卡类的一组数据已存在, 请重新选择！！！");
		       //document.forms[0].merchantId.value='';
		       //document.forms[0].terminalId.value='';
		       document.forms[0].bankType.options.length=0;
	           document.forms[0].cardType.options.length=0;
		    }
		}
	  }
    }
	</script>
</head>

<body>
	<!--------------以下Table为路径-------->
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30" class="place">
				<img src="<fmt:message key='CommonImagePath' />place_btn.gif" width="12" height="11"> 
				当前位置：收单系统管理平台 >> 终端机管理 >> 终端受理卡类
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
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle"/>
				新增终端受理卡类
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="edcCardForm" />
				<html:errors />
				<html:form action="/edcCard">
					<html:hidden property="method" value="createEdcCard" />
					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">

						<tr>
							<td class="table2_td_title">
								商户编号:
							</td>
							<td class="table2_td">
								<html:text styleId="merchantId" property="id.merchantId" size="19" maxlength="15" onblur="checkMerchantId(this.value)" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								商户名称:
							</td>
							<td class="table2_td">
								<html:text styleId="merchantName" property="id.merchantName" size="19" readonly="true" maxlength="10"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								终端号：
							</td>
							<td class="table2_td">
								<html:text styleId="terminalId" property="id.terminalId" size="19" maxlength="8" onblur="checkTerminalId()" onkeyup="this.value=this.value.replace(/\W/g,'')"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								受理卡类型：
							</td>
							<td class="table2_td">
								<html:select styleId="cardType" property="id.cardType" onchange="edcCardCheck()">
								 <option >&nbsp;&nbsp;&nbsp;&nbsp;</option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								受理卡状态：
							</td>
							<td class="table2_td">
								<html:select property="cardStat">
								 <option value="Y">正常受理</option>
								 <option value="N">暂停受理</option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								银行类型：
							</td>
							<td class="table2_td">								
								<html:select styleId="bankType" property="id.bankType">
								<option >&nbsp;&nbsp;&nbsp;&nbsp;</option>
								</html:select>
								<font color="red">*</font>
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

</html:html>



