<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html lang="true">
<head>
	<html:base />

	<title>商户信息统计-商户信息增加页面</title>
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/style_a.css" />
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/cssem.css" />
	<script type="text/javascript" src="<%=path%>/js/checkform.js"></script>
	<script type="text/javascript" src="<%=path%>/js/textselect.js"></script>
	<script type="text/javascript" src="<%=path%>/js/dwr.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
	<script src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
</head>
<script type="text/javascript">	
	function CurentDay() {
		var now = new Date();
		var year = now.getFullYear(); //年
		var month = now.getMonth() + 1; //月
		var day = now.getDate(); //日
		var clock = year + "";
		if (month < 10)
			clock += "0";
		clock += month + "";
		if (day < 10)
			clock += "0";
		clock += day + "";
		return (clock);
	}
	
	function loadMy() {
		document.getElementsByName("installdate")[0].value = CurentDay();
		document.getElementsByName("disabledate")[0].value = CurentDay();
		document.getElementsByName("updatedate")[0].value = CurentDay();
		
		getMerchantInfoListByIdOrName();
	}
   	
	function is1A7Num(formname,element,msg,len)
	{
	    var input = document.forms[formname].elements[element].value;
	    
	    if (input.length != len) {
	        alert (msg+"必须为" + len + "位，并且必须以大写字母A或数字开头！");
	        return false;
	    }
    	
	    var charset1 = "A1234567890"  //首位
    	var charset2to8 = "1234567890";  //其它位
		
    	var char1 = input.substr(0,1);
    	var char2to8 = input.substr(1);
    	
    	if (!checkChar(charset1, char1, true) || !checkChar(charset2to8, char2to8, true)) {
	        alert (msg+"必须为" + len + "位，并且必须以大写字母A或数字开头！");
	        document.forms[formname].elements[element].focus();
	        document.forms[formname].elements[element].value = "";
	        return false;
		}
	    	
	    return true;
	}
	
	//检查字符串中是否有规定字符以内/外的字符
	function checkChar(charset, val, should_in)
	{
	    var num = val.length;
	    for (i=0; i < num; i++) {
	       var char = val.charAt(i);
	       if ((charset.indexOf(char) > -1) && (!should_in))
	          return false;
	       else if ((charset.indexOf(char) == -1) && (should_in))
	          return false;
	    }
	    return true;
	}
	
   	function commit() {
		if (!is1A7Num("terminalInfoForm", "id", "终端编号", 8)) {
			return false;
		}   
		if (!checkelement("terminalInfoForm", "merchantid", "所属商户编号")) {
			return false;
		}
		
		if (!checkelement("terminalInfoForm", "address", "商户装机地址")) {
			return false;
		}  
		if (!checkelement("terminalInfoForm", "detailaddress", "店铺及款台地址")) {
			return false;
		}  
		if (!checkelement("terminalInfoForm", "model", "POS型号")) {
			return false;
		}  
		if (!checkelement("terminalInfoForm", "type", "POS类型")) {
			return false;
		}  		
		var input = document.forms["terminalInfoForm"].elements["mobilenumber"].value;
		if (input != "") {
			if (!isnumberonly3("terminalInfoForm", "mobilenumber", "无线POS手机号")) {
				return false;
			}
		}
		if (!checkelement("terminalInfoForm", "snnumber", "POS机S/N号")) {
			return false;
		}  
		if (!checkelement("terminalInfoForm", "installdate", "安装日期")) {
			return false;
		}  
		if (!checkelement("terminalInfoForm", "disabledate", "停用日期")) {
			return false;
		}  
		if (!checkelement("terminalInfoForm", "updatedate", "升级日期")) {
			return false;
		}  
		if (!checkelement("terminalInfoForm", "name", "联系人")) {
			return false;
		}  
		if (!isnumberonly3("terminalInfoForm", "phonenumber", "门店电话")) {
			return false;
		}  
		if (!checkelement("terminalInfoForm", "status", "POS机状态")) {
			return false;
		}  
		if (!isnumberonly3("terminalInfoForm", "deposite", "POS押金（元）")) {
			return false;
		}  
		
		$("#btTJ").attr("disabled","true");
		document.terminalInfoForm.submit();   	    
	}
	
	//根据aqmerchantid获取merchantid的可选项
	function getMerchantInfoListByIdOrName(){
		var qmerchantidname = document.getElementsByName("qmerchantidname")[0].value;
		if(trim(qmerchantidname)!=''){
			merchantid = document.getElementsByName("merchantid")[0];
			document.getElementsByName("merchantid")[0].options.length = 0;
			merchantid.innerHTML = "";
			merchantid.add(new Option("－请选择－", ""));
			
			merchantInfoManage.getMerchantInfoListByIdOrName(trim(qmerchantidname), function(data) {
				for (i = 0; i < data.length; i++) {
					merchantid.add(new Option(data[i].name, data[i].id));
				}
			});			
		}

	}
   	
	//删除左右两端的空格
    function trim(str){
		return str.replace(/(^\s*)|(\s*$)/g, "");
	}
	
</script>

<body  onload="loadMy();">
	<html:form styleId="terminalInfoForm" action="/terminalInfo?method=addTerminalInfo" method="post">
		<table border="0" cellpadding="0" cellspacing="0" style="width: 100%; height: 99%;">
			<tr>
				<td align="center" valign="top">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td style="width: 28px; height: 10px;"></td>
						</tr>
						<tr>
							<td align="left"
								style="width:28px; height:28px;  background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">
							</td>
							<td
								style="height:28px;  background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;">
								&nbsp;&nbsp;当前位置： 商户信息统计 &gt; 增加终端信息
							</td>
							<td
								style="width:7px; height:28px;  background:url(<%=path%>/image1/Navigation_bar/right1.gif) ">
							</td>
						</tr>
						<tr>
							<td style="width: 28px; height: 5px" colspan="3"></td>
						</tr>
					</table>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td coslpan="2"><font color="red">${info}</font></td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								终端编号
							</td>
							<td align="left" class="box2">
								<html:text property="id" maxlength="8" onblur="checkTerminalInfoPK();" onkeyup="this.value=this.value.replace(/\W/g,'').toUpperCase()"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								所属商户
							</td>
							<td align="left" class="box2">
								<html:text property="qmerchantidname" maxlength="15" onchange="getMerchantInfoListByIdOrName();"></html:text>
								<html:select property="merchantid">
									<html:option value=" ">－请选择－</html:option>
									<logic:present name="mrchList">
										<html:optionsCollection name="mrchList" label="name" value="mrchno" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								商户装机地址
							</td>
							<td align="left" class="box2">
								<html:text property="address" maxlength="66"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								店铺及款台地址
							</td>
							<td align="left" class="box2">
								<html:text property="detailaddress" maxlength="66"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								POS型号
							</td>
							<td align="left" class="box2">
								<html:select property="model">
									<html:option value="">－请选择－</html:option>								
									<logic:present name="pos_modelList">
										<html:optionsCollection name="pos_modelList"
											label="param_name" value="param_value" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								POS类型
							</td>
							<td align="left" class="box2">
								<html:select property="type">
									<html:option value="">－请选择－</html:option>								
									<logic:present name="pos_typeList">
										<html:optionsCollection name="pos_typeList"
											label="param_name" value="param_value" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								无线POS手机号
							</td>
							<td align="left" class="box2">
								<html:text property="mobilenumber" maxlength="16" onkeyup="this.value=this.value.replace(/\D/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								POS机S/N号
							</td>
							<td align="left" class="box2">
								<html:text property="snnumber" maxlength="16"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								安装日期
							</td>
							<td align="left" class="box2">
								<html:text property="installdate" onclick="WdatePicker({dateFmt:'yyyyMMdd'});" maxlength="8"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								停用日期
							</td>
							<td align="left" class="box2">
								<html:text property="disabledate" onclick="WdatePicker({dateFmt:'yyyyMMdd'});" maxlength="8"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								升级日期
							</td>
							<td align="left" class="box2">
								<html:text property="updatedate" onclick="WdatePicker({dateFmt:'yyyyMMdd'});" maxlength="8"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								联系人
							</td>
							<td align="left" class="box2">
								<html:text property="name" maxlength="16"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								门店电话
							</td>
							<td align="left" class="box2">
								<html:text property="phonenumber" maxlength="16" onkeyup="this.value=this.value.replace(/\D/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								POS机状态
							</td>
							<td align="left" class="box2">
								<html:select property="status">
									<html:option value="">－请选择－</html:option>
									<html:option value="0">可用</html:option>
									<html:option value="1">不可用</html:option>
									<html:option value="2">黑名单</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								POS押金（元）
							</td>
							<td align="left" class="box2">
								<html:text property="deposite" maxlength="50" onkeyup="this.value=this.value.replace(/\D/g,'')"></html:text>
							</td>
						</tr>
					</table>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td style="height: 23px;" align="center" class="box1">
								<input id="btTJ" class="button" type="button" onclick="commit()"
									value="保存" />
								&nbsp;&nbsp;&nbsp;
								<input class="button" type="button" onClick="javascript:window.close()" 
									value="关闭" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>

</html:html>