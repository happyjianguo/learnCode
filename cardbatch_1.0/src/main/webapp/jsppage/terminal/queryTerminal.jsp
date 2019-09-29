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

	<title>终端商户查看页面</title>
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

var req;
function init() {
	if(window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
}
function  selectWankeTer(){    
	  var merchantNoTemp = document.getElementsByName("merchant_id")[0].value;
	      merchantNo=merchantNoTemp.split("(")[0];
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
			if(req.responseText=='false'){
			  document.getElementsByName("yard_mer_type")[0].style.display = "none";
			  document.forms[0].yard_mer_type.value="";
			
			}
		}
	  }
    }

</script>


<body onload="selectWankeTer();">
	<html:form styleId="terminalForm"
		action="/terminal?method=getTerminalList" method="post">
		<html:hidden property="merchant_id" />
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
							<td style="width: 110px;" align="right" class="box1">
								所属商户
							</td>
							<td align="left" class="box2">
								<html:text property="merchant_id" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								终端号
							</td>
							<td align="left" class="box2">
								<html:text property="termcode" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								终端所在位置
							</td>
							<td align="left" class="box2">
								<html:text property="x_location" maxlength="20" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								终端状态
							</td>
							<td align="left" class="box2">
								<html:select property="term_stat" disabled="true">
									<html:option value="0">可用</html:option>
									<html:option value="1">停用</html:option>
									<html:option value="2">销户</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
							万科商户类型
							</td>
							<td align="left" class="box2">
								<html:select property="yard_mer_type" disabled="true">
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
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" disabled="true"></html:text>
							</td>
						</tr>		
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								停用时间
							</td>
							<td align="left" class="box2">
								<html:text property="disabled_date" maxlength="10"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" disabled="true"></html:text>
							</td>
						</tr>						
						<!-- enckey -->
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								密钥生效日期
							</td>
							<td align="left" class="box2">
								<html:text property="actdate" maxlength="16"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								密钥生效时间
							</td>
							<td align="left" class="box2">
								<html:text property="acttime" maxlength="16"
									onclick="WdatePicker({dateFmt:'HHmmss'});" disabled="true"></html:text>
							</td>
						</tr>

						<!-- termpos_x -->
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								终端使用的电话号码
							</td>
							<td align="left" class="box2">
								<html:text property="pos_tel" maxlength="16" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								增加日期
							</td>
							<td align="left" class="box2">
								<html:text property="add_date" maxlength="16"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								裕福原有状态
							</td>
							<td align="left" class="box2">
								<html:text property="state" maxlength="16" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								省份
							</td>
							<td align="left" class="box2">
								<logic:present name="provinList">
									<html:select property="province" disabled="true">
										<html:optionsCollection name="provinList"
											label="province_city" value="aid" />
									</html:select>
								</logic:present>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								城市
							</td>
							<td align="left" class="box2">
								<logic:present name="city_noList">
									<html:select property="city_no" disabled="true">
										<html:optionsCollection name="city_noList"
											label="province_city" value="aid" />
									</html:select>
								</logic:present>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								区域
							</td>
							<td align="left" class="box2">
								<logic:present name="zoneList">
									<html:select property="zone" disabled="true">
										<html:optionsCollection name="zoneList" label="province_city"
											value="aid" />
									</html:select>
								</logic:present>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								划账归账的结算账号
							</td>
							<td align="left" class="box2">									
								<html:text property="settle_mrch_acc_id" disabled="true"></html:text>	
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								费率(单位：%)
							</td>
							<td align="left" class="box2">
								<html:text property="x_timezone" maxlength="16" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								消费场景
							</td>
							<td align="left" class="box2">
								<html:select property="consump_category" disabled="true">
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
								<input class="button" type="button"  value="关闭" onClick="javascript:window.close()">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>

</html:html>