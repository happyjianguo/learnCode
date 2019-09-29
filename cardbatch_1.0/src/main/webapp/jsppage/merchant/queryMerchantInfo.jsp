<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%
String path = request.getContextPath();
String bus_id_pic=(String)request.getAttribute("bus_id_pic");
String bus_id_pic_path=bus_id_pic;

String tax_id_pic=(String)request.getAttribute("tax_id_pic");
String tax_id_pic_path=tax_id_pic;

String org_id_pic=(String)request.getAttribute("org_id_pic");
String org_id_pic_path=org_id_pic;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html lang="true">
<head>
	<html:base />

	<title>商户查询页面</title>
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

</script>
<body>
	<html:form styleId="merchantForm"
		action="/merchant?method=addmerchantBeanInfo"
		enctype="multipart/form-data" method="post">
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
								&nbsp;&nbsp;当前位置： 商户终端管理 &gt; 查询商户
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
								商户号
							</td>
							<td align="left" class="box2">
								<html:text property="mrchno" maxlength="20" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								商户名称
							</td>
							<td align="left" class="box2">
								<html:text property="mrcht_name" maxlength="16" disabled="true"></html:text>
								&nbsp;&nbsp;&nbsp;
								  万科商户类型 
								<html:select property="yard_mer_type" disabled="true">
									<html:option value="">－请选择－</html:option>
									<html:option value="0">万科车场</html:option>
								</html:select> 
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								商户摘要
							</td>
							<td align="left" class="box2">
								<html:text property="mrch_snippet" maxlength="256" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								ISO对应MCC码
							</td>
							<td align="left" class="box2">
								<logic:present name="MCCList">
									<html:select property="acptbus" disabled="true">
										<html:option value="">－请选择－</html:option>
										<html:optionsCollection name="MCCList" label="descr"
											value="id" />
									</html:select>
								</logic:present>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								注册地址联系人
							</td>
							<td align="left" class="box2">
								<html:text property="contact3" maxlength="16" disabled="true"></html:text>
							</td>
						</tr>

						<tr>
							<td style="width: 110px;" align="right" class="box1">
								邮寄地址联系电话
							</td>
							<td align="left" class="box2">
								<html:text property="telno1" maxlength="16" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								商户状态
							</td>
							<td align="left" class="box2">
								<html:select property="mrchstat" disabled="true">
									<html:option value="">－请选择－</html:option>
									<html:option value="0">可用</html:option>
									<html:option value="1">不可用</html:option>
									<html:option value="2">黑名单</html:option>
									<html:option value="3">销户</html:option>
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
						<tr>
							<td colspan="2" class="box1">
								商户补充信息
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								商户联系地址
							</td>
							<td align="left" class="box2">
								<html:text property="address" maxlength="16" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								增加时间
							</td>
							<td align="left" class="box2">
								<html:text property="add_date" maxlength="16"
									onclick="WdatePicker({dateFmt:'yyyyMMddHHmmss'});"
									disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								省份
							</td>
							<td align="left" class="box2">
								<logic:present name="provinList">
									<html:select property="province" disabled="true">
										<html:option value="">－请选择－</html:option>
										<html:optionsCollection name="provinList"
											label="province_city" value="aid" />
									</html:select>
								</logic:present>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								城市
							</td>
							<td align="left" class="box2">
								<logic:present name="city_noList">
									<html:select property="city_no" disabled="true">
										<html:option value="">－请选择－</html:option>
										<html:optionsCollection name="city_noList"
											label="province_city" value="aid" />
									</html:select>
								</logic:present>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								区域
							</td>
							<td align="left" class="box2">
								<logic:present name="zoneList">
									<html:select property="zone" disabled="true">
										<html:option value="">－请选择－</html:option>
										<html:optionsCollection name="zoneList" label="province_city"
											value="aid" />
									</html:select>
								</logic:present>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								是否属于北京
							</td>
							<td align="left" class="box2">
								<html:select property="is_bj" disabled="true">
									<html:option value="1">是</html:option>
									<html:option value="0">否</html:option>
								</html:select>
							</td>
						</tr>							
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								商户类型
							</td>
							<td align="left" class="box2">
								<html:select property="type_yf" disabled="true">
									<logic:present name="cardbatch_mer_typeList">
										<html:optionsCollection name="cardbatch_mer_typeList"
											label="param_name" value="param_value" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								代办手续经办人姓名
							</td>
							<td align="left" class="box2">
								<html:text property="agent" maxlength="16" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								办理手续经办人证件类型
							</td>
							<td align="left" class="box2">
								<html:select property="id_type" disabled="true">
									<html:option value="1">居民身份证</html:option>
									<html:option value="2">户口本</html:option>
									<html:option value="3">军人身份证</html:option>
									<html:option value="4">武装警察身份证</html:option>
									<html:option value="5">往来内地通行证</html:option>
									<html:option value="6">往来大陆通行证</html:option>
									<html:option value="7">护照</html:option>
									<html:option value="8">其他</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								办理手续经办人证件号码
							</td>
							<td align="left" class="box2">
								<html:text property="id_no" maxlength="20" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								办理手续经办人证件有效期
							</td>
							<td align="left" class="box2">
								<html:text property="id_validity" maxlength="16"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								法定代表人（负责人）姓名
							</td>
							<td align="left" class="box2">
								<html:text property="legal_rep" maxlength="16" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								法定代表人证件类型
							</td>
							<td align="left" class="box2">
								<html:select property="lr_id_type" disabled="true">
									<html:option value="1">居民身份证</html:option>
									<html:option value="2">户口本</html:option>
									<html:option value="3">军人身份证</html:option>
									<html:option value="4">武装警察身份证</html:option>
									<html:option value="5">往来内地通行证</html:option>
									<html:option value="6">往来大陆通行证</html:option>
									<html:option value="7">护照</html:option>
									<html:option value="8">其他</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								法定代表人证件号码
							</td>
							<td align="left" class="box2">
								<html:text property="lr_id_no" maxlength="20" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								法定代表人证件有效期
							</td>
							<td align="left" class="box2">
								<html:text property="lr_id_validity" maxlength="16"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" disabled="true"></html:text>
							</td>
						</tr>						
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								控股股东或实际控制人姓名
							</td>
							<td align="left" class="box2">
								<html:text property="man_name" maxlength="16" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								控股股东或实际控制人证件类型
							</td>
							<td align="left" class="box2">
								<html:select property="id_type1" disabled="true">
									<html:option value="1">居民身份证</html:option>
									<html:option value="2">户口本</html:option>
									<html:option value="3">军人身份证</html:option>
									<html:option value="4">武装警察身份证</html:option>
									<html:option value="5">往来内地通行证</html:option>
									<html:option value="6">往来大陆通行证</html:option>
									<html:option value="7">护照</html:option>
									<html:option value="8">其他</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								控股股东或实际控制人证件号码
							</td>
							<td align="left" class="box2">
								<html:text property="id_no1" maxlength="20" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								控股股东或实际控制人证件有效期
							</td>
							<td align="left" class="box2">
								<html:text property="id_deadline1" maxlength="16"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" disabled="true"></html:text>
							</td>
						</tr>						
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								营业执照号
							</td>
							<td align="left" class="box2">
								<html:text property="bus_lic_no" maxlength="30" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								营业执照名称
							</td>
							<td align="left" class="box2">
								<html:text property="acc_x_name" maxlength="20" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								营业执照年检时间
							</td>
							<td align="left" class="box2">
								<html:text property="bus_lic_validity" maxlength="16"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								税务登记证编号
							</td>
							<td align="left" class="box2">
								<html:text property="tax_id" maxlength="30" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								税务登记证年检时间
							</td>
							<td align="left" class="box2">
								<html:text property="tax_id_validity" maxlength="16"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								组织机构证编号
							</td>
							<td align="left" class="box2">
								<html:text property="org_id" maxlength="30" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								组织机构证年检时间
							</td>
							<td align="left" class="box2">
								<html:text property="org_validity" maxlength="16"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								营业执照图片
							</td>
							<td align="left" class="box2">
								<logic:present name="bus_id_pic">
									<a href="<%=bus_id_pic_path%>"> <font color="blue">查看图片</font>
									</a>
								</logic:present>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								税务登记证图片
							</td>
							<td align="left" class="box2">
								<logic:present name="tax_id_pic">
									<a href="<%=tax_id_pic_path%>"> <font color="blue">查看图片</font>
									</a>
								</logic:present>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								组织机构证图片
							</td>
							<td align="left" class="box2">
								<logic:present name="org_id_pic">
									<a href="<%=org_id_pic_path%>"> <font color="blue">查看图片</font>
									</a>
								</logic:present>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								企业账号
							</td>
							<td align="left" class="box2">
								<html:text property="mx_accno" maxlength="16" disabled="true"></html:text>
							</td>
						</tr>

						<tr>
							<td style="width: 110px;" align="right" class="box1">
								结算级别
							</td>
							<td align="left" class="box2">
								<html:select property="classify" disabled="true">
									<html:option value="">－请选择－</html:option>
									<html:option value="0">结算商户</html:option>
									<html:option value="1">交易商户</html:option>
									<html:option value="2">正常商户</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								父商户
							</td>
							<td align="left" class="box2">
								<logic:present name="mrchList">
									<html:select property="fmrchno" disabled="true">
										<html:optionsCollection name="mrchList" label="name" value="mrchno" />
									</html:select>
								</logic:present>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								商户机构名称
							</td>
							<%-- <td align="left" class="box2">
								<html:select property="merchant_org" disabled="true">
									<html:optionsCollection name="merchantOrgList" label="orgName" value="orgId" />
								</html:select>
							</td> --%>
							<td align="left" class="box2">
								<html:select property="merchant_org" disabled="true">
									<html:option value="">－请选择－</html:option>
									<logic:present name="merchantOrgList">
										<html:optionsCollection name="merchantOrgList" label="orgName" value="orgId" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								分润机构名称
							</td>
							<td align="left" class="box2">
								<html:select property="merchant_fenrunorg" disabled="true">
									<html:option value="">－请选择－</html:option>
									<logic:present name="merchantFenrunOrgList">
										<html:optionsCollection name="merchantFenrunOrgList" label="orgName" value="orgId" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								邮箱
							</td>
							<!-- <td align="left" class="box2"> -->
							<td  align="left" class="box2">
								<html:text property="merchant_email"  style="width:500px;" disabled="true" maxlength="2000"></html:text>
								<%-- <html:text property="merchant_email"  disabled="true" onchange="getOnchangeEmail();" maxlength="2000"></html:text> --%>
							</td>
						</tr>					
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								结算员
							</td>
							<td align="left" class="box2">
								<html:text property="settlement_person" maxlength="20"  disabled="true"></html:text>
							</td>
						</tr>					
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								是否发送对账明细
							</td>
							<td align="left" class="box2">
								<html:select property="is_send_billdetail"  disabled="true">
									<html:option value="1">是</html:option>
									<html:option value="0">否</html:option>
								</html:select>
							</td>
						</tr>	
						
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								企业经营范围
							</td>
							<td align="left" class="box2">
								<html:text property="merchant_x_operate" style="width:500px;"  maxlength="165" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								商户分类
							</td>
							<td align="left" class="box2">
								<html:select property="merchant_x_type" disabled="true">
									<html:option value="12">单位</html:option>
									<html:option value="11">自然人</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								注册资本金
							</td>
							<td align="left" class="box2">
								<html:text property="merchant_x_reg_amt" maxlength="18" disabled="true" ></html:text>  万元  ——
								币种
								<%-- <html:select property="merchant_x_code" disabled="true" >
									<html:option value="RMB">人民币-RMB</html:option>
									<html:option value="USD">美元-USD</html:option>
								</html:select> --%>
								<html:select property="merchant_x_code" disabled="true">
									<logic:present name="merchant_x_code_typeList">
										<html:optionsCollection name="merchant_x_code_typeList"
											label="param_name" value="param_value" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								节假日结算日期是否合并
							</td>
							<td align="left" class="box2">
								<html:select property="combine_flag" disabled="true">
									<html:option value="1">合并</html:option>
									<html:option value="0">不合并</html:option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td colspan="2" class="box1">
								商户结算信息
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								结算周期
							</td>
							<td align="left" class="box2">
								<html:select property="fs_cycle"  disabled="true">
									<html:option value="">－请选择－</html:option>
									<logic:present name="fs_cycleList">
										<html:optionsCollection name="fs_cycleList"
											label="param_name" value="param_value" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								结算参数
							</td>
							<td align="left" class="box2">
								<html:text property="fs_cycle_param" maxlength="28"  disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								上次结算日期
							</td>
							<td align="left" class="box2">
								<html:text property="last_settle_date" maxlength="16"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"  disabled="true"></html:text>
							</td>
						</tr>	
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								是否按照不同消费场景结算
							</td>
							<td align="left" class="box2">
								<html:select property="is_consump_category"  disabled="true">
									<html:option value="0">否</html:option>
									<html:option value="1">是</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								是否合并打款
							</td>
							<td align="left" class="box2">
								<html:select property="amt_consump_category"  disabled="true">
									<html:option value="0">否</html:option>
									<html:option value="1">是</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								是否按照卡类别分组
							</td>
							<td align="left" class="box2">
								<html:select property="is_card_type_group" disabled="true">
									<html:option value="0">否</html:option>
									<html:option value="1">是</html:option>
								</html:select>
							</td>
						</tr>						
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								是否按父商户结算
							</td>
							<td align="left" class="box2">
								<html:select property="is_fmrchno_decide"  disabled="true">
									<html:option value="0">否</html:option>
									<html:option value="1">是</html:option>
								</html:select>
							</td>
						</tr>								
						<tr>
							<td colspan="2" class="box1">
								商户开票信息
							</td>
						</tr>	
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								是否开票
							</td>
							<td align="left" class="box2">
								<html:select property="is_kp" disabled="true">
									<html:option value="1">是</html:option>
									<html:option value="0">否</html:option>
								</html:select>
							</td>
						</tr>											
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								开票周期
							</td>
							<td align="left" class="box2">
								<html:select property="fs_kp_cycle" disabled="true">
									<html:option value="">－请选择－</html:option>
									<logic:present name="fs_kp_cycleList">
										<html:optionsCollection name="fs_kp_cycleList"
											label="param_name" value="param_value" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								开票周期参数
							</td>
							<td align="left" class="box2">
								<html:text property="fs_kp_cycle_param" maxlength="28" disabled="true"></html:text>
							</td>
						</tr>		
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								上次开票日期
							</td>
							<td align="left" class="box2">
								<html:text property="last_kp_date" maxlength="16"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" disabled="true"></html:text>
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


