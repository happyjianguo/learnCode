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

	<title>商户账户修改页面</title>
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

	function getAcc_x_city_no(){
		var province_val = document.getElementsByName("acc_x_province")[0].value;
		acc_x_city_no = document.getElementsByName("acc_x_city_no")[0];
		document.getElementsByName("acc_x_city_no")[0].options.length = 0;
		acc_x_city_no.innerHTML = "";
		acc_x_city_no.add(new Option("－请选择－", ""));
		/* instManage.getCityByFid(province_val, function(data) { */
		instManage.getAreaXCityByFid(province_val, function(data) {
			for (i = 0; i < data.length; i++) {
				acc_x_city_no.add(new Option(data[i].province_city, data[i].aid));
			}
		});
	}
	function trim(str){ //删除左右两端的空格
	    return str.replace(/(^\s*)|(\s*$)/g, "");
	}	
	function commit() {
		if (!checkelement("mrch_acc_xForm", "acc_name", "结算账户开户名")) {
			return false;
		}   
		if (!checkelement("mrch_acc_xForm", "accno", "结算账号")) {
			return false;
		}   
		if (!checkelement("mrch_acc_xForm", "acc_nick_name", "商户账号名称")) {
			return false;
		}   
		if (!checkelement("mrch_acc_xForm", "short_nick_name", "商户账号简称")) {
			return false;
		}   
		if (!checkelement("mrch_acc_xForm", "bank_no", "联行号")) {
			return false;
		}  
		if (!isnumberonly3("mrch_acc_xForm", "bank_no", "联行号")) {
			return false;
		}    
		if (!checkelement("mrch_acc_xForm", "bis", "结算银行")) {
			return false;
		} 			
		if (!checkelement("mrch_acc_xForm", "bank_name", "开户银行名称")) {
			return false;
		}   
		if (!checkelement("mrch_acc_xForm", "acc_add_date", "账号添加日期")) {
			return false;
		}   
		if (!checkelement("mrch_acc_xForm", "individual", "是否单独结算")) {
			return false;
		}   
		if (!checkelement("mrch_acc_xForm", "last_settle_date", "上次结算日期")) {
			return false;
		}  
		if(trim(document.getElementsByName("mrchno")[0].value)==""&&trim(document.getElementsByName("mrchnoSelQ")[0].value)==""){
			alert("所属商户不能为空");
			return false;
		} 
		var acc_introd=document.getElementsByName("acc_introd")[0].value;
		if(acc_introd.length>240){
			alert("简介不能超过240个字符");
			return false;
		}
		var pay_account_type=document.getElementsByName("pay_account_type")[0].value;
		var acc_x_province=document.getElementsByName("acc_x_province")[0].value;
		var acc_x_city_no=document.getElementsByName("acc_x_city_no")[0].value;
		var acc_x_text=document.getElementsByName("acc_x_text")[0].value;
		if(pay_account_type == '00'){
			if (!checkelement("mrch_acc_xForm", "acc_x_province", "开户行所在省份")) {
				return false;
			}   
			if (!checkelement("mrch_acc_xForm", "acc_x_city_no", "开户行所在市")) {
				return false;
			}  
		}
		if (!checkelement("mrch_acc_xForm", "acc_x_text", "附言")) {
			return false;
		}
		var re = /^[A-Za-z0-9\u4e00-\u9fa5]+$/;
		if (!acc_x_text.match(re)) {
			alert('附言只能是付款原因、款项用途说明，不支持特殊字符');
			return false;
		}
		$("#btTJ").attr("disabled","true");
		
		document.mrch_acc_xForm.submit();
		
	
	}
	
	function setMrchid(obj) {
	
		
		var data = eval(<%=jsonMrchList%>);

		for(var i=0; i<data.length; i++){
			if (obj.value == data[i].mrchno) {
				//alert("merchant_id:"+document.getElementsByName("merchant_id")[0].value+" inst_id:" +document.getElementsByName("inst_id")[0].value);
				document.getElementsByName("merchant_id")[0].value=data[i].id;
				document.getElementsByName("inst_id")[0].value=data[i].inst_id;
			}
		} 
		
	}
	
	//根据bankNameSelQ获取银行的可选项
	function getTBankInfoListByBankName(){
		var bankNameSelQ = document.getElementsByName("bankNameSelQ")[0].value;
		if(trim(bankNameSelQ)!=''){
			bis = document.getElementsByName("bis")[0];
			document.getElementsByName("bis")[0].options.length = 0;
			bis.innerHTML = "";
			bis.add(new Option("－请选择－", ""));
			tBankInfoManage.getTBankInfoListByBankName(bankNameSelQ, function(data) {
				for (i = 0; i < data.length; i++) {
					bis.add(new Option(data[i].bank_name, data[i].bank_code));
				}
			});			
		}

	}

	//根据mrchnoSelQ获取mrchno的可选项
	function getMerchantBeanListH(){
		var mrchnoSelQ = document.getElementsByName("mrchnoSelQ")[0].value;
		if(trim(mrchnoSelQ)!=''){			
			mrchno = document.getElementsByName("mrchno")[0];
			document.getElementsByName("mrchno")[0].options.length = 0;
			mrchno.innerHTML = "";
			mrchno.add(new Option("－请选择－", ""));
			instManage.getMerchantBeanListByMrchNoOrName(null, null,'1',mrchnoSelQ,'yes',null, function(data) {
				for (i = 0; i < data.length; i++) {
					mrchno.add(new Option(data[i].name, data[i].mrchno));
				}
			});
		}
	}
	
	window.onload=function(){//用window的onload事件，窗体加载完毕的时候
		var bankNameSelQ=$("select[name$='bis']").find("option:selected").text();
		var bankCode=document.getElementsByName("bis")[0].value;//;
		document.getElementsByName("bis")[0].options.length = 1;
		document.getElementsByName("bis")[0].options[0].value=bankCode;
		document.getElementsByName("bis")[0].options[0].text=bankNameSelQ;
		document.getElementsByName("bankNameSelQ")[0].value = bankNameSelQ;
	}
</script>

<body>
	<html:form styleId="mrch_acc_xForm"
		action="/mrchaccx.do?method=modMrchaccx"
		enctype="multipart/form-data" method="post">
		<html:hidden property="id" />
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
								style="  width:28px; height:28px; background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">

							</td>
							<td
								style="height:28px;  background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;">
								&nbsp;&nbsp;当前位置： 商户终端管理 &gt; 修改商户账户
							</td>
							<td
								style=" width:7px; height:28px; background:url(<%=path%>/image1/Navigation_bar/right1.gif) ">
							</td>
						</tr>
						<tr>
							<td style="width: 28px; height: 5px" colspan="3"></td>
						</tr>
					</table>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">

						<tr>
							<td style="width: 110px;" align="right" class="box1">
								结算账户开户名
							</td>
							<td align="left" class="box2">
								<html:text property="acc_name" maxlength="66"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								商户账号简称
							</td>
							<td align="left" class="box2">
								<html:text property="short_nick_name" maxlength="30"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								商户账号全称
							</td>
							<td align="left" class="box2">
								<html:text property="acc_nick_name" maxlength="30"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								联行号
							</td>
							<td align="left" class="box2">
								<html:text property="bank_no" maxlength="16" onkeyup="this.value=this.value.replace(/\D/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								是否是北京开户行
							</td>
							<td align="left" class="box2">
								<html:select property="is_bj_acct">
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
							<html:text property="bankNameSelQ" maxlength="15" onchange="getTBankInfoListByBankName();"/>
								<html:select property="bis">
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
								<html:select property="merchant_x_acc_type1">
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
								<%-- <html:text property="accno" maxlength="30" onkeyup="this.value=this.value.replace(/\D/g,'')"></html:text> --%>
								<html:text property="accno" onblur="checkAccNo();" maxlength="30" onkeyup="this.value=this.value.replace(/\D/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								开户银行名称
							</td>
							<td align="left" class="box2">
								<html:text property="bank_name" maxlength="30"></html:text>
							</td>
						</tr>
						
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								收款账户属性
							</td>
							<td align="left" class="box2">
								<html:select property="pay_account_type">
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
								<html:select property="acc_x_province" onchange="getAcc_x_city_no()">
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
								<html:select property="acc_x_city_no">
									<html:option value="">－请选择－</html:option>
									<logic:present name="city_noList">
										<html:optionsCollection name="city_noList"
											label="province_city" value="aid" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								附言
							</td>
							<td align="left" class="box2">
								<html:text property="acc_x_text" style="width:500px;" maxlength="38"></html:text><font color="red">*附言只能是付款原因、款项用途说明，不支持特殊字符</font>
							</td>
						</tr>
						
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								增加日期
							</td>
							<td align="left" class="box2">
								<html:text property="acc_add_date" maxlength="16"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"></html:text>
							</td>
						</tr>

						<tr>
							<td style="width: 110px;" align="right" class="box1">
								是否单独结算
							</td>
							<td align="left" class="box2">
								<html:select property="individual">
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
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								所属商户
							</td>
							<td align="left" class="box2">
								<html:text property="mrchnoSelQ" maxlength="15" onchange="getMerchantBeanListH();"></html:text>
								<logic:present name="mrchList">
									<html:select property="mrchno">
										<html:option value=" ">－请选择－</html:option>
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
								<html:textarea property="acc_introd" rows="5" cols="25" disabled="false"></html:textarea>
							</td>
						</tr>
					</table>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td style="height: 23px;" align="center" class="box1">
								<input  id="btTJ"  class="button" type="button" onclick="commit()"
										value="保存" />
								&nbsp;&nbsp;&nbsp;
								<input class="button" type="button"  value="关闭" 
								onclick="history.go(-1);" />

							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>


