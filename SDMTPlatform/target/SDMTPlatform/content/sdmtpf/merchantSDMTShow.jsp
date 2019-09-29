<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>查看商户信息</title>
<!-- 样式所需导入的链接 -->
<%@ include file="../inner/css.jsp"%>
<!-- 样式所需导入的链接 -->
<script type="text/javascript">
$().ready(function() {
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	$('#closeBtn').click(function() {
		parent.layer.close(index);
	});
});

</script>
</head>
<body>
	<div class="sh_main">
		<div class="shm_contab">
			<div class="poup_right">
				<div class="shmcr_cont01">
					<div class="sh_title">查看商户信息</div>
					<div class="sh_cm">
						<form id="form" action="${ctx }/merchantSDMT/show" method="post">
							<table width="100%" border="0" class="tab">
								<tr>
									<td style="text-align: left;">商户编号：</td>
									<td style="text-align: left;">
										${info.merchantId}							
									</td>
									<td style="text-align: left;">商户名称：</td>
									<td style="text-align: left;">
										${info.mrchtName}							
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">商户名称(英)：</td>
									<td style="text-align: left;">
										${info.merchantEname}
									</td>
									<td style="text-align: left;">简称(中)：</td>
									<td style="text-align: left;">
										${info.abbrCname}
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">简称(英)：</td>
									<td style="text-align: left;">
										${info.abbrEname}
									</td>
									<td style="text-align: left;">MCC：</td>
									<td style="text-align: left;">				
										<select id="mccId" name="mccId" class="inputext_style" value="${info.mccId}" disabled="true">
											<c:forEach var="model" items="${mccList}">
												<option value="${model.id}" <c:if test="${model.id eq info.mccId}">selected="selected"</c:if>>
													${model.id}---${model.descr}
												</option>
											</c:forEach>
										</select>												
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">地址：</td>
									<td style="text-align: left;">
										${info.address}
									</td>
									<td style="text-align: left;">省份：</td>
									<td style="text-align: left;">
										<select id="province" name="province" class="inputext_style" onchange="getCityList();" value="${info.province}" disabled="true">
											<c:forEach var="model" items="${proviceList}">
												<option value="${model.id}" <c:if test="${model.id eq info.province}">selected="selected"</c:if>>
													${model.id}---${model.provinceCity}
												</option>
											</c:forEach>
										</select>																	
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">联系电话：</td>
									<td style="text-align: left;">
										${info.telephone}
									</td>
									<td style="text-align: left;">城市：</td>
									<td style="text-align: left;">
										<select id="cityNo" name="cityNo" class="inputext_style" onchange="getZoneList();" value="${info.cityNo}" disabled="true">
											<c:forEach var="model" items="${cityList}"> 
												<option value="${model.id}" <c:if test="${model.id eq info.cityNo}">selected="selected"</c:if>>
													${model.id}---${model.provinceCity}
												</option>
											</c:forEach>
										</select>																		
									</td>	
								</tr>								
								<tr>
									<td style="text-align: left;">联系人：</td>
									<td style="text-align: left;">
										${info.manager}
									</td>
									<td style="text-align: left;">区域：</td>
									<td style="text-align: left;">
										<select id="zone" name="zone" class="inputext_style" value="${info.zone}" disabled="true"> 
											<c:forEach var="model" items="${zoneList}">
												<option value="${model.id}" <c:if test="${model.id eq info.zone}">selected="selected"</c:if>>
													${model.id}---${model.provinceCity}
												</option>
											</c:forEach>
										</select>																
									</td>								
								</tr>								
								<tr>
									<td style="text-align: left;">增加时间：</td>
									<td style="text-align: left;">${info.addDate}
									</td>
									<td style="text-align: left;"></td>
									<td style="text-align: left;">
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">结算账户开户名：</td>
									<td style="text-align: left;">
										${info.accName}
									</td>
									<td style="text-align: left;">结算账号：</td>
									<td style="text-align: left;">
										${info.accNo}
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">是否是北京开户行:</td>
									<td style="text-align: left;">
										<select id="isBjAcct" name="isBjAcct" class="inputext_style" value="${info.isBjAcct}" disabled="true">
											<option value="0" <c:if test="${info.isBjAcct eq '0'}">selected="selected"</c:if>>否</option>
											<option value="1" <c:if test="${info.isBjAcct eq '1'}">selected="selected"</c:if>>是</option>
										</select>																	
									</td>
									<td style="text-align: left;">结算银行：</td>
									<td style="text-align: left;">
										<select id="bis" name="bis" class="inputext_style" value="${info.bis}" disabled="true">
											<option value="">--请选择--</option>
											<c:forEach var="model" items="${bankList}">
												<option value="${model.bankCode}"  <c:if test="${model.bankCode eq info.bis}">selected="selected"</c:if>>
													${model.bankCode}---${model.bankName}
												</option>
											</c:forEach>
										</select>																		
									</td>
								</tr>									
								<tr>
									<td style="text-align: left;">开户银行名称：</td>
									<td style="text-align: left;">
										${info.bankName}
									</td>
									<td style="text-align: left;">商户账号全称：</td>
									<td style="text-align: left;">
										${info.accNickName}
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">商户账号简称：</td>
									<td style="text-align: left;">
										${info.shortNickName}
									</td>
									<td style="text-align: left;">开户银行行号：</td>
									<td style="text-align: left;">
										${info.bankNo}
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">是否单独结算：</td>
									<td style="text-align: left;">
										<select id="individual" name="individual" class="inputext_style" disabled="true">
											<option value="0" <c:if test="${info.individual eq '0'}">selected="selected"</c:if>>否</option>
											<option value="1" <c:if test="${info.individual eq '1'}">selected="selected"</c:if>>是</option>
										</select>																	
									</td>
									<td style="text-align: left;">上次结算日期：</td>
									<td style="text-align: left;">${info.lastSettleDate}																
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">商户类型：</td>
									<td style="text-align: left;">
									<select id="typeYf" name="typeYf" class="inputext_style" value="${info.typeYf}" disabled="true" >
										<c:forEach var="model" items="${typeYfList}">
											<option value="${model.paramValue}" <c:if test="${model.paramValue eq info.typeYf}">selected="selected"</c:if>>${model.paramName}</option>
										</c:forEach>
									</select>
									</td>
									<td style="text-align: left;">办理手续经办人姓名：</td>
									<td style="text-align: left;">${info.agent}
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">办理手续经办人证件类型：</td>
									<td style="text-align: left;">
										<select id="idType" name="idType" class="inputext_style" disabled="true">
									         <option value="1" <c:if test="${info.idType eq '1'}">selected="selected"</c:if>>居民身份证</option>
									         <option value="2" <c:if test="${info.idType eq '2'}">selected="selected"</c:if>>户口本</option>
									         <option value="3" <c:if test="${info.idType eq '3'}">selected="selected"</c:if>>军人身份证</option>
									         <option value="4" <c:if test="${info.idType eq '4'}">selected="selected"</c:if>>武装警察身份证</option>
									         <option value="5" <c:if test="${info.idType eq '5'}">selected="selected"</c:if>>往来内地通行证</option>
									         <option value="6" <c:if test="${info.idType eq '6'}">selected="selected"</c:if>>往来大陆通行证</option>
									         <option value="7" <c:if test="${info.idType eq '7'}">selected="selected"</c:if>>护照</option>
									         <option value="8" <c:if test="${info.idType eq '8'}">selected="selected"</c:if>>其他</option>
										</select>																	
									</td>
									<td style="text-align: left;">办理手续经办人证件号码：</td>
									<td style="text-align: left;">${info.idNo}
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">办理手续经办人证件有效期：</td>
									<td style="text-align: left;">${info.idValidity}
									</td>
									<td style="text-align: left;">法定代表人（负责人）姓名：</td>
									<td style="text-align: left;">${info.legalRep}
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">法定代表人证件类型：</td>
									<td style="text-align: left;">
										<select id="lrIdType" name="lrIdType" class="inputext_style" disabled="true">
									         <option value="1" <c:if test="${info.lrIdType eq '1'}">selected="selected"</c:if>>居民身份证</option>
									         <option value="2" <c:if test="${info.lrIdType eq '2'}">selected="selected"</c:if>>户口本</option>
									         <option value="3" <c:if test="${info.lrIdType eq '3'}">selected="selected"</c:if>>军人身份证</option>
									         <option value="4" <c:if test="${info.lrIdType eq '4'}">selected="selected"</c:if>>武装警察身份证</option>
									         <option value="5" <c:if test="${info.lrIdType eq '5'}">selected="selected"</c:if>>往来内地通行证</option>
									         <option value="6" <c:if test="${info.lrIdType eq '6'}">selected="selected"</c:if>>往来大陆通行证</option>
									         <option value="7" <c:if test="${info.lrIdType eq '7'}">selected="selected"</c:if>>护照</option>
									         <option value="8" <c:if test="${info.lrIdType eq '8'}">selected="selected"</c:if>>其他</option>
										</select>																			
									</td>
									<td style="text-align: left;">法定代表人证件号码：</td>
									<td style="text-align: left;">${info.lrIdNo}
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">法定代表人证件有效期：</td>
									<td style="text-align: left;">${info.lrIdValidity}																
									</td>
									<td style="text-align: left;">企业账号：</td>
									<td style="text-align: left;">
									${info.enterpriseNo}</td>
								</tr>
								<tr>
									<td style="text-align: left;">控股股东或实际控制人姓名：</td>
									<td style="text-align: left;">
									${info.manName}</td>
									<td style="text-align: left;">控股股东或实际控制人证件种类：</td>
									<td style="text-align: left;">
									<select id="idType1" name="idType1" class="inputext_style" disabled="true">
											 <option value="1" <c:if test="${info.idType1 eq '1'}">selected="selected"</c:if>>居民身份证</option>
									         <option value="2" <c:if test="${info.idType1 eq '2'}">selected="selected"</c:if>>户口本</option>
									         <option value="3" <c:if test="${info.idType1 eq '3'}">selected="selected"</c:if>>军人身份证</option>
									         <option value="4" <c:if test="${info.idType1 eq '4'}">selected="selected"</c:if>>武装警察身份证</option>
									         <option value="5" <c:if test="${info.idType1 eq '5'}">selected="selected"</c:if>>往来内地通行证</option>
									         <option value="6" <c:if test="${info.idType1 eq '6'}">selected="selected"</c:if>>往来大陆通行证</option>
									         <option value="7" <c:if test="${info.idType1 eq '7'}">selected="selected"</c:if>>护照</option>
									         <option value="8" <c:if test="${info.idType1 eq '8'}">selected="selected"</c:if>>其他</option>
									</select></td>
								</tr>
								<tr>
									<td style="text-align: left;">控股股东或实际控制人证件号码：</td>
									<td style="text-align: left;">
									${info.idNo1}</td>
									<td style="text-align: left;">控股股东或实际控制人证件有效期截止日：</td>
									<td style="text-align: left;">
									${info.idDeadline1}</td>
								</tr>
								<tr>
									<td style="text-align: left;">营业执照号：</td>
									<td style="text-align: left;">
									${info.busLicNo}</td>
									<td style="text-align: left;">营业执照名称：</td>
									<td style="text-align: left;">
									${info.accXName}</td>
								</tr>								
								<tr>
									<td style="text-align: left;">营业执照年检时间：</td>
									<td style="text-align: left;">${info.busLicValidity}
									</td>
									<td style="text-align: left;">税务登记证编号：</td>
									<td style="text-align: left;">${info.taxId}
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">税务登记证年检时间：</td>
									<td style="text-align: left;">${info.taxIdValidity}																
									</td>
									<td style="text-align: left;">组织机构证编号：</td>
									<td style="text-align: left;">${info.orgId}
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">组织机构证年检时间：</td>
									<td style="text-align: left;">${info.orgValidity}																	
									</td>
									<td style="text-align: left;"></td>
									<td style="text-align: left;">
									</td>
								</tr>
								<tr>
									<td><div class="sh_title">商户补充信息</div></td><td><div class="sh_title"></div></td>
									<td><div class="sh_title"></div></td><td><div class="sh_title"></div></td>
								</tr>
								<tr>
									<td style="text-align: left;">合同签订日期：</td>
									<td style="text-align: left;">${info.contractStartDate}</td>
									<td style="text-align: left;">合同续约日期：</td>
									<td style="text-align: left;">${info.contractRenewalDate}</td>
								</tr>
								<tr>
									<td style="text-align: left;">商户公司名称：</td>
									<td style="text-align: left;">${info.merchantCompanyName}</td>
									<td style="text-align: left;">续约方式：</td>
									<td style="text-align: left;">
										<select id="renewalType" name="renewalType" class="inputext_style" value="${info.renewalType}" disabled="disabled">
											<c:forEach var="model" items="${renewalTypeList}">
												<option value="${model.value}" <c:if test="${model.value eq info.renewalType}">selected="selected"</c:if>>
													${model.label}
												</option>
											</c:forEach>
										</select>
									</td>		
								</tr>
								<tr>
									<td style="text-align: left;">保函到期日期：</td>
									<td style="text-align: left;">${info.guaranteeLastDate}</td>
									<td style="text-align: left;">保函金额：</td>
									<td style="text-align: left;">${info.guaranteeAmt}</td>
								</tr>
								<tr>
									<td style="text-align: left;">市场联系人：</td>
									<td style="text-align: left;">${info.marketContactPerson}</td>
									<td style="text-align: left;">市场联系人电话：</td>
									<td style="text-align: left;">${info.marketContactMobile}
									</td>
								</tr>
								<%-- <tr>
									<td style="text-align: left;">财务联系人：</td>
									<td style="text-align: left;">${info.financialContactPerson}
									</td>
									<td style="text-align: left;">财务联系人电话：</td>
									<td style="text-align: left;">${info.financialContactMobile}
									</td>
								</tr> --%>
								<tr>
									<td style="text-align: left;">押金：</td>
									<td style="text-align: left;">${info.merchantDeposit}</td>
									<td style="text-align: left;">区域：</td>
									<td style="text-align: left;">
										<select id="merchantArea" name="merchantArea" class="inputext_style" value="${info.merchantArea}" disabled="disabled">
											<c:forEach var="model" items="${merchantAreaList}">
												<option value="${model.value}" <c:if test="${model.value eq info.merchantArea}">selected="selected"</c:if>>
													${model.label}
												</option>
											</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">区域负责人：</td>
									<td style="text-align: left;">
									<select id="storeManager" name="storeManager" class="inputext_style" value="${info.storeManager}" disabled="disabled">
											<c:forEach var="model" items="${storeManagerList}">
												<option value="${model.id}" <c:if test="${model.id eq info.storeManager}">selected="selected"</c:if>>
													${model.managerName}
												</option>
											</c:forEach>
										</select>
									</td>
									<td style="text-align: left;">商户顾问：</td>
									<td style="text-align: left;">
										<select id="merchantAdvisor" name="merchantAdvisor" class="inputext_style" value="${info.merchantAdvisor}" disabled="disabled">
											<c:forEach var="model" items="${managerList}">
												<option value="${model.id}" <c:if test="${model.id eq info.merchantAdvisor}">selected="selected"</c:if>>
													${model.managerName}
												</option>
											</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">合同结束日期：</td>
									<td style="text-align: left;">${info.contractEndDate}</td>
									<td style="text-align: left;">父商户号：</td>
									<td style="text-align: left;">${info.fmrchno}</td>
								</tr>
								
								<tr>
									<td style="text-align: left;">企业经营范围：</td>
									<td style="text-align: left;">
										${info.merchant_x_operate}
									</td>
									<td style="text-align: left;">商户分类：</td>
									<td style="text-align: left;">
									<select id="merchant_x_type" name="merchant_x_type" class="inputext_style" disabled="true" value="${info.merchant_x_type}">
											<option value="12" <c:if test="${info.merchant_x_type eq '12'}">selected="selected"</c:if>>单位</option>
									        <option value="11" <c:if test="${info.merchant_x_type eq '11'}">selected="selected"</c:if>>自然人</option>
									</select>
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">注册资本金：</td>
									<td style="text-align: left;">
										${info.merchant_x_reg_amt}
									</td>
									<td style="text-align: left;">注册资本金币种：</td>
									<td style="text-align: left;">
									<select id="merchant_x_code" name="merchant_x_code" class="inputext_style" value="${info.merchant_x_code}" disabled="true">
										<c:forEach var="model" items="${merchant_x_code_typeList}">
											<option value="${model.paramValue}" <c:if test="${model.paramValue eq info.merchant_x_code}">selected="selected"</c:if>>
												${model.paramName}
											</option>
										</c:forEach>
									</select>
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">结算账户类型：</td>
									<td style="text-align: left;">
									<select id="merchant_x_acc_type1" name="merchant_x_acc_type1" class="inputext_style" value="${info.merchant_x_acc_type1}" disabled="true">
											<option value="12" <c:if test="${info.merchant_x_acc_type1 eq '12'}">selected="selected"</c:if>>银行账户</option>
									        <option value="11" <c:if test="${info.merchant_x_acc_type1 eq '11'}">selected="selected"</c:if>>支付账户</option>
									</select>
									</td>
									<td style="text-align: left;"></td>
									<td style="text-align: left;"></td>
								</tr>
								<tr>
									<td style="text-align: center;" colspan="4">
										<input type="button" id="closeBtn" value="关闭" class="button" />										
									</td>
								</tr>
							</table>
						</form>
						<div class="clear"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>