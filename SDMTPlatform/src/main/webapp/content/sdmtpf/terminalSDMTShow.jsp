<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>查看终端信息</title>
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
					<div class="sh_title">查看终端信息</div>
					<div class="sh_cm">
						<form id="form" action="${ctx }/terminalSDMT/show" method="post">
							<table width="100%" border="0" class="tab">
								<tr>
									<td style="text-align: left;">商户编号：</td>
									<td style="text-align: left;">
										${info.merchantId}
									</td>
									<td style="text-align: left;">商户名称：</td>
									<td style="text-align: left;">	
										${merchantName}
									</td>										
								</tr>
								<tr>
									<td style="text-align: left;">终端编号：</td>
									<td style="text-align: left;">
										${info.termCode}
									</td>								
									<td style="text-align: left;">终端设备型号：</td>
									<td style="text-align: left;">${info.edcType}												
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">参数下载标志：</td>
									<td style="text-align: left;">
								        <select class="inputext_style" id="downloadFlag" name="downloadFlag" value="${info.downloadFlag}" disabled="true">
											 <option value="0" <c:if test="${info.downloadFlag=='0'}">selected="selected"</c:if>>需要安装</option>
								             <option value="1" <c:if test="${info.downloadFlag=='1'}">selected="selected"</c:if>>无需安装</option>
								        </select>																	
									</td>
									<td style="text-align: left;">终端类型：</td>
									<td style="text-align: left;">${info.softVer}
									</td>									
								</tr>								
								<tr>
									<td style="text-align: left;">终端所在位置：</td>
									<td style="text-align: left;">${info.termAddress}
									</td>
									<td style="text-align: left;">省份：</td>
									<td style="text-align: left;">
										<select id="province" name="province" class="inputext_style" onchange="getCityList();" value="${info.province}" disabled="true">
											<option value="">--请选择--</option>
											<c:forEach var="model" items="${proviceList}">
												<option value="${model.id}" <c:if test="${model.id eq info.province}">selected="selected"</c:if>>
													${model.id}---${model.provinceCity}
												</option>
											</c:forEach>
										</select>																	
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">密钥生效日期：</td>
									<td style="text-align: left;">${info.actDate}																	
									</td>								
									<td style="text-align: left;">城市：</td>
									<td style="text-align: left;">
										<select id="cityNo" name="cityNo" class="inputext_style" onchange="getZoneList();" value="${info.cityNo}" disabled="true">
											<option value="">--请选择--</option>
											<c:forEach var="model" items="${cityList}"> 
												<option value="${model.id}" <c:if test="${model.id eq info.cityNo}">selected="selected"</c:if>>
													${model.id}---${model.provinceCity}
												</option>
											</c:forEach>
										</select>							
									</td>									
								</tr>								
								<tr>
									<td style="text-align: left;">密钥生效时间：</td>
									<td style="text-align: left;">${info.actTime}																	
									</td>
									<td style="text-align: left;">区域：</td>
									<td style="text-align: left;">
										<select id="zone" name="zone" class="inputext_style" value="${info.zone}" disabled="true">
											<option value="">--请选择--</option>
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
									<td style="text-align: left;"> ${info.addDate}
									</td>
									<td style="text-align: left;">终端使用的电话号码</td>
									<td style="text-align: left;">${info.termTel}
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">裕福原有状态：</td>
									<td style="text-align: left;">${info.state}	
									</td>								
									<td style="text-align: left;">费率(单位：%)：</td>
									<td style="text-align: left;">${info.xTimezone}
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">消费场景：</td>
									<td style="text-align: left;">
										<select id="consumpCategory" name="consumpCategory" class="inputext_style" value="${info.consumpCategory}" disabled="true">
											<option value="">--请选择--</option>
											<c:forEach var="model" items="${consumpList}">
												<option value="${model.paramValue}" <c:if test="${model.paramValue eq info.consumpCategory}">selected="selected"</c:if>>
													${model.paramValue}---${model.paramName}
												</option>
											</c:forEach>
										</select>						
									</td>
									<td style="text-align: left;"></td>
									<td style="text-align: left;"></td>									
								</tr>
								<tr>
									<td><div class="sh_title">终端补充信息</div></td><td><div class="sh_title"></div></td>
									<td><div class="sh_title"></div></td><td><div class="sh_title"></div></td>
								</tr>
								<tr>
									<td style="text-align: left;">升级日期：</td>
									<td style="text-align: left;">${info.upgradeDate}</td>
									<td style="text-align: left;">门店联系人：</td>
									<td style="text-align: left;">${info.storeContacts}
									</td>
									<%-- <td style="text-align: left;">升级版本号：</td>
									<td style="text-align: left;">${info.upgradeVersion}
									</td> --%>
								</tr>
								<tr>
									<td style="text-align: left;">区域：</td>
									<td style="text-align: left;">
										<select id="terminalArea" name="terminalArea" class="inputext_style" value="${info.terminalArea}" disabled="disabled">
											<option value="">--请选择--</option>
											<c:forEach var="model" items="${merchantAreaList}">
												<option value="${model.value}" <c:if test="${model.value eq info.terminalArea}">selected="selected"</c:if>>
													${model.label}
												</option>
											</c:forEach>
										</select>
									</td>
									<td style="text-align: left;">门店电话：</td>
									<td style="text-align: left;">${info.storePhone}
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">商户顾问：</td>
									<td style="text-align: left;">
										<select id="merchantAdvisor" name="merchantAdvisor" class="inputext_style" value="${info.merchantAdvisor}" disabled="disabled">
											<option value="">--请选择--</option>
											<c:forEach var="model" items="${managerList}">
												<option value="${model.id}" <c:if test="${model.id eq info.merchantAdvisor}">selected="selected"</c:if>>
													${model.managerName}
												</option>
											</c:forEach>
										</select>
									</td>
									<td style="text-align: left;">终端装机地址:</td>
									<td style="text-align: left;">
										${ info.terminalPosition}
									</td>
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