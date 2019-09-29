<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%String path = request.getContextPath();
String jsonMrchList = (String)request.getAttribute("jsonMrchList");%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html lang="true">
<head>
<html:base />

<title>商户账户信息页面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
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
<script src="<%=path%>/js/calendar1.js"></script>
<script src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>

</head>
<script language="javascript">
/* function getAcc_x_city_no(){
	var province_val = document.getElementsByName("acc_x_province")[0].value;
	acc_x_city_no = document.getElementsByName("acc_x_city_no")[0];
	document.getElementsByName("acc_x_city_no")[0].options.length = 0;
	acc_x_city_no.innerHTML = "";
	acc_x_city_no.add(new Option("－请选择－", ""));
	instManage.getCityByFid(province_val, function(data) {
		for (i = 0; i < data.length; i++) {
			acc_x_city_no.add(new Option(data[i].province_city, data[i].aid));
		}
	});
} */
function load() {
}
function tagdis()
{
	var origLength;
	origLength = document.all.length;
	for(var i=0;i<origLength;i++) {
		
		if (document.all[i].tagName == "INPUT" ||
			document.all[i].tagName == "SELECT") {
			if (document.all[i].type == "button") continue;
			document.all[i].disabled="disabled";
		}
	}
	load();
}	
</script>

<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0" onload="tagdis();">
	<html:form styleId="mrch_acc_xForm"
		action="/mrchaccx.do?method=preQueryMrchaccx"
		enctype="multipart/form-data" method="post"
		style="text-decoration:underline">
		<table border="0" cellpadding="0" cellspacing="0" width="100%"
			height="99%">
			<tr>
				<td align="center" valign="top">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td width="28" height="10"></td>
						</tr>
						<tr>
							<td align="left" width="28" height="28"
								style=" background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">

							</td>
							<td height="28"
								style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;">
								&nbsp;&nbsp;当前位置： 商户终端管理 &gt; 查询商户账户</td>
							<td width="7" height="28"
								style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) ">
							</td>
						</tr>
						<tr>
							<td width="28" height="5" colspan="3"></td>
						</tr>
					</table>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">

						<tr>
							<td style="width: 110px;" align="right" class="box1">
								结算账户开户名
							</td>
							<td align="left" class="box2">
								<html:text property="acc_name" maxlength="16" disabled="false"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								商户账号简称
							</td>
							<td align="left" class="box2">
								<html:text property="short_nick_name" maxlength="16" disabled="false"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								商户账号全称
							</td>
							<td align="left" class="box2">
								<html:text property="acc_nick_name" maxlength="16" disabled="false"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								联行号
							</td>
							<td align="left" class="box2">
								<html:text property="bank_no" maxlength="10" disabled="false"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								是否是北京开户行
							</td>
							<td align="left" class="box2">
								<html:select property="is_bj_acct" disabled="false">
									<html:option value="1">是</html:option>
									<html:option value="0">否</html:option>	
								</html:select>
							</td>
						</tr>						
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								结算银行
							</td>
							<td align="left" class="box2">
								<html:select property="bis" disabled="false">
									<html:option value="">－请选择－</html:option>
									<logic:present name="bankList">
										<html:optionsCollection name="bankList"
											label="bank_name" value="bank_code" />
									</logic:present>
								</html:select>
							</td>
						</tr>	
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								结算账户类型
							</td>
							<td align="left" class="box2">
								<html:select property="merchant_x_acc_type1" disabled="false" >
									<html:option value="12">银行账户</html:option>
									<html:option value="11">支付账户</html:option>									
								</html:select>
							</td>
						</tr>					
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								结算账号
							</td>
							<td align="left" class="box2">
								<html:text property="accno" maxlength="20" disabled="false"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								开户银行名称
							</td>
							<td align="left" class="box2">
								<html:text property="bank_name" maxlength="25" disabled="false"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								收款账户属性
							</td>
							<td align="left" class="box2">
								<html:select property="pay_account_type" disabled="false">
									<html:option value="00">00-对公账户</html:option>
									<html:option value="01">01-对私账户</html:option>									
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								开户行所在省份
							</td>
							<td align="left" class="box2">
								<html:select property="acc_x_province" disabled="false">
									<html:option value="">－请选择－</html:option>
									<logic:present name="provinList">
										<html:optionsCollection name="provinList"
											label="province_city" value="aid" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								开户行所在市
							</td>
							<td align="left" class="box2">
								<logic:present name="city_noList">
									<html:select property="acc_x_city_no" disabled="true">
										<html:option value="">－请选择－</html:option>
										<html:optionsCollection name="city_noList"
											label="province_city" value="aid" />
									</html:select>
								</logic:present>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								附言
							</td>
							<td align="left" class="box2">
								<html:text property="acc_x_text" style="width:500px;" disabled="false" maxlength="38"></html:text><font color="red">*附言只能是付款原因、款项用途说明，不支持特殊字符</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								账号添加日期
							</td>
							<td align="left" class="box2">
								<html:text property="acc_add_date" maxlength="16"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" disabled="false"></html:text>
							</td>
						</tr>

						<tr>
							<td style="width: 110px;" align="right" class="box1">
								是否单独结算
							</td>
							<td align="left" class="box2">
								<html:select property="individual" disabled="false">
									<html:option value="">－请选择－</html:option>
									<html:option value="0">0－否</html:option>
									<html:option value="1">1－是</html:option>

								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								上次结算日期
							</td>
							<td align="left" class="box2">
								<html:text property="last_settle_date" maxlength="16"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" disabled="false"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								所属商户
							</td>
							<td align="left" class="box2">
								<logic:present name="mrchList">
									<html:select property="mrchno" disabled="false">
										<html:optionsCollection name="mrchList" label="name"
											value="mrchno" />
									</html:select>
								</logic:present>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;height: 80px;" align="right" class="box1">
								简介
							</td>
							<td align="left" class="box2">
								<html:textarea property="acc_introd" rows="5" cols="25" disabled="true"></html:textarea>
							</td>
						</tr>
					</table>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td height="23" align="center" class="box1">
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


