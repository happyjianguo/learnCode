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

	<title>终端商户增加页面</title>
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
	<script type="text/javascript" src="<%=path%>/js/md5.js"></script>
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
		var clock = year + "-";
		if (month < 10)
			clock += "0";
		clock += month + "-";
		if (day < 10)
			clock += "0";
		clock += day + "";
		return (clock);
	}
	function loadMy() {
		getMerchantBeanListH();
		getMerchantBeanListJS();
		getCity_no();	
	}
	function trim(str){ //删除左右两端的空格
	    return str.replace(/(^\s*)|(\s*$)/g, "");
	}	
   	function commit() {
		if (!checkelement("terminalForm", "merchant_id", "所属商户")) {
			return false;
		}    
		if (!checkelement("terminalForm", "termcode", "终端号")) {
			return false;
		}   
		var termCode=document.getElementsByName("termcode")[0].value;
		if(trim(termCode)!=''){
			if(termCode.length!=8){
			   alert("终端号必须是8位数字");
			   document.forms["terminalForm"].elements["termcode"].focus();
			   return false;		
			}
		}   	
		if (!checkelement("terminalForm", "x_location", "终端所在位置")) {
			return false;
		}  
		if (!checkelement("terminalForm", "term_stat", "终端状态")) {
			return false;
		} 
		if (!checkelement("terminalForm", "actdate", "密钥生效日期")) {
			return false;
		}   	
		if (!checkelement("terminalForm", "acttime", "密钥生效时间")) {
			return false;
		}  
		if (!checkelement("terminalForm", "pos_tel", "终端使用的电话号码")) {
			return false;
		}    	
		if (!checkelement("terminalForm", "add_date", "增加日期")) {
			return false;
		}  
		if (!checkelement("terminalForm", "state", "裕福原有状态")) {
			return false;
		}  
		if (!isnumberonly3("terminalForm", "state", "裕福原有状态")) {
			return false;
		}
		if (!checkelement("terminalForm", "province", "省份")) {
			return false;
		}  
		if (!checkelement("terminalForm", "city_no", "城市")) {
			return false;
		}    	
		if (!checkelement("terminalForm", "zone", "区域")) {
			return false;
		}  
		if (!checkelement("terminalForm", "settle_mrch_acc_id", "划账归账的结算账号")) {
			return false;
		}  
		if (checkDouble("terminalForm", "x_timezone", "费率")) {
			return false;
		}  
		if (!checkelement("terminalForm", "consump_category", "消费场景")) {
			return false;
		}  
		$("#btTJ").attr("disabled","true");
		document.terminalForm.submit();
	}
	function backTo(){
		window.location.href="<%=path%>/terminal.do?method=getTerminalList";
	}
  //根据mrchnoSelQ获取交易商户和正常商户
	function getMerchantBeanListH(){
		var mrchnoSelQ = document.getElementsByName("mrchnoSelQ")[0].value;
		if(mrchnoSelQ!=null&&mrchnoSelQ!=''){
			merchant_id = document.getElementsByName("merchant_id")[0];
			document.getElementsByName("merchant_id")[0].options.length = 0;
			merchant_id.innerHTML = "";
			merchant_id.add(new Option("－请选择－", ""));
			instManage.getMerchantBeanListByMrchNoOrName(null,null,'0',mrchnoSelQ,'yes',null, function(data) {
				for (i = 0; i < data.length; i++) {
					merchant_id.add(new Option(data[i].name, data[i].mrchno));
				}
			});
		}
	}
	//根据mrchnoSelJSQ获取结算商户和正常商户
	function getMerchantBeanListJS(){
//	 	var obj = document.getElementById("merchant_id"); //定位id
// 		var index = obj.selectedIndex; // 选中索引
// 		var merchantId = obj.options[index].value; // 选中值
		var merchantId=document.getElementsByName("merchant_id")[0].value;
		if(merchantId==null||merchantId==""){
			$("#mrchnoSelQ").focus();
			return -1;				
		}
		var mrchnoSelJSQ = document.getElementsByName("mrchnoSelJSQ")[0].value;
		//如果商户设置是按照父商户结算的话只能选择父商户
		if(mrchnoSelJSQ!=null&&mrchnoSelJSQ!=''){
			merchantJS_id = document.getElementsByName("merchantJS_id")[0];
			document.getElementsByName("merchantJS_id")[0].options.length = 0;
			merchantJS_id.innerHTML = "";
			merchantJS_id.add(new Option("－请选择－", ""));
			instManage.getMerchantBeanListByMrchNoOrName(null, null,'1',mrchnoSelJSQ,'yes',merchantId, function(data) {
				for (i = 0; i < data.length; i++) {
					merchantJS_id.add(new Option(data[i].name, data[i].mrchno));
				}
			});
		}
	}
	   function   checkDouble(formname, name, info)   {      
		   var nameArray = document.getElementsByName(name);  
		   for(var i = 0;i< nameArray.length; i++){
		    if(nameArray[i].value != ""){
		       if(!isUnsignedDouble(nameArray[i].value)) {
		          alert(info+"应为数字！");
		          document.forms[formname].elements[name].focus();
		          return true;
		       }
		    }
		   }  
		    return false;    
		    } 
	   //检查是否为double      
	   function   isUnsignedDouble(strDouble)   {      
	   var   newPar=/^\d+(\.\d+)?$/;      
	   return   newPar.test(strDouble);      
	   } 
	   
	   
		/*****************************/
		var req;
		function init() {
			if(window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}
		}
	   function  selectWankeTer(){   
		   var merchantNo = document.getElementsByName("merchant_id")[0].value; 
		   init();
	 		var url="<%=path%>/terminal.do?method=findmerchantNo&merchantNo="+merchantNo;   
	 		req.open("POST", url, true);
			req.onreadystatechange = findmerchantNoCallback;
			req.send(null);
		   } 
	   function findmerchantNoCallback() {
			 if(4 == req.readyState) {
				if(200 == req.status) {
					if(req.responseText!='false'){
					  document.getElementsByName("yard_mer_type")[0].style.display = "block";
					  document.forms[0].yard_mer_type.value=req.responseText;
					}
				}
			  }
		    }

	   
</script>


<body  onload="loadMy();">
	<html:form styleId="terminalForm"
		action="/terminal?method=addTerminalInfo" method="post">
		<table border="0" cellpadding="0" cellspacing="0"
			style="width: 100%; height: 99%;">
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
								&nbsp;&nbsp;当前位置： 商户终端管理 &gt; 终端管理
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
								所属商户
							</td>
							<td align="left" class="box2">
								<html:text property="mrchnoSelQ" maxlength="15"
									onchange="getMerchantBeanListH();"></html:text>
								<html:select property="merchant_id">
									<html:option value=" ">－请选择－</html:option>
									<logic:present name="mrchList">
										<html:optionsCollection name="mrchList" label="name"
											value="mrchno" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								终端号
							</td>
							<td align="left" class="box2">
								<html:text property="termcode" maxlength="8" onchange="checkTermcode();" onkeyup="this.value=this.value.replace(/\W/g,'').toUpperCase()"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								终端所在位置
							</td>
							<td align="left" class="box2">
								<html:text property="x_location" maxlength="15" onblur="selectWankeTer();"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								终端状态
							</td>
							<td align="left" class="box2">
								<html:select property="term_stat">
									<html:option value="">－请选择－</html:option>
									<html:option value="0">可用</html:option>
									<html:option value="1">停用</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
							万科商户类型
							</td>
							<td align="left" class="box2">
								<html:select property="yard_mer_type" style="width:120px; display: none;">
									<html:option value="0">万科车场</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								启用时间
							</td>
							<td align="left" class="box2">
								<html:text property="enable_date" maxlength="10"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"></html:text>
							</td>
						</tr>		
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								停用时间
							</td>
							<td align="left" class="box2">
								<html:text property="disabled_date" maxlength="10"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"></html:text>
							</td>
						</tr>							
						<!-- enckey -->
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								密钥生效日期
							</td>
							<td align="left" class="box2">
								<html:text property="actdate" maxlength="16"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								密钥生效时间
							</td>
							<td align="left" class="box2">
								<html:text property="acttime" maxlength="16"
									onclick="WdatePicker({dateFmt:'HHmmss'});"></html:text>
							</td>
						</tr>

						<!-- termpos_x -->
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								终端使用的电话号码
							</td>
							<td align="left" class="box2">
								<html:text property="pos_tel" maxlength="16"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								增加日期
							</td>
							<td align="left" class="box2">
								<html:text property="add_date" maxlength="16"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								裕福原有状态
							</td>
							<td align="left" class="box2">
								<html:text property="state" maxlength="5"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								省份
							</td>
							<td align="left" class="box2">
								<html:select property="province" onchange="getCity_no()">
									<html:option value="">－请选择－</html:option>
									<logic:present name="provinList">
										<html:optionsCollection name="provinList"
											label="province_city" value="aid" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								城市
							</td>
							<td align="left" class="box2">
								<html:select property="city_no" onchange="getZone()">
									<html:option value="">－请选择－</html:option>
									<logic:present name="city_noList">
										<html:optionsCollection name="city_noList"
											label="province_city" value="aid" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								区域
							</td>
							<td align="left" class="box2">
								<html:select property="zone">
									<html:option value="">－请选择－</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								结算商户
							</td>
							<td align="left" class="box2">
								<html:text property="mrchnoSelJSQ" maxlength="15"
									onblur="getMerchantBeanListJS();"></html:text>
								<html:select property="merchantJS_id" onchange="getMcaccxId();">
									<html:option value=" ">－请选择－</html:option>
									<logic:present name="mrchJSList">
										<html:optionsCollection name="mrchJSList" label="name"
											value="mrchno" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								划账归账的结算账号
							</td>
							<td align="left" class="box2">
								<html:select property="settle_mrch_acc_id">
									<html:option value="">－请选择－</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								费率(单位：%)
							</td>
							<td align="left" class="box2">
								<html:text property="x_timezone" maxlength="16"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								消费场景
							</td>
							<td align="left" class="box2">
								<html:select property="consump_category">
									<html:option value="">－请选择－</html:option>
									<logic:present name="consump_categoryList">
										<html:optionsCollection name="consump_categoryList"
											label="param_name" value="param_value" />
									</logic:present>
								</html:select>
							</td>
						</tr>
					</table>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td style="height: 23px;" align="center" class="box1">
								<input id="btTJ" class="button" type="button" onclick="commit()"
									value="保存" />
								&nbsp;&nbsp;&nbsp;
								<input class="button" type="button"  value="关闭" onClick="javascript:window.opener.query();window.close();">

							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>

</html:html>