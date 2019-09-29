<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>手动调账详情</title>
	<meta name="decorator" content="default"/>

<script type="text/javascript">
$(document).ready(function() {
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
					<div class="sh_title">手动调账详情</div>
					<div class="sh_cm">
						<form id="form" action="" method="post" >
							<input type="hidden" id="idId" name="id" value="${info.id}"/>
							<input type="hidden" id="voidTraceCode" name="voidTraceCode" value="${info.voidTraceCode}"/>
							<table width="100%" border="0" class="tab">
								<tr>
									<td style="text-align: left;">商户号：</td>
									<td style="text-align: left;">
										<input type="text" id="merNo" name="merNo" value="${info.merNo}" disabled="disabled" />
									</td>
									<td style="text-align: left;">商户名称：</td>
									<td style="text-align: left;">
										<input type="text" id="merName" name="merName" value="${info.merName}" disabled="disabled"/>
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">终端号：</td>
									<td style="text-align: left;">
										<input type="text" id="terminalId" name="terminalId" value="${info.terminalId}" disabled="disabled"/>
									</td>
									<td style="text-align: left;">卡号：</td>
									<td style="text-align: left;">
										<input type="text" id="cardNo" name="cardNo" value="${info.cardNo}" disabled="disabled"/>
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">原交易流水号：</td>
									<td style="text-align: left;">
										<input type="text" id="voidTraceNo" name="voidTraceNo" value="${info.voidTraceNo}" disabled="disabled"/>
									</td>
									<td style="text-align: left;">原交易日期：</td>
									<td style="text-align: left;">
										<input type="text" id="voidSysDate" name="voidSysDate" value="${info.voidSysDate}" disabled="disabled"/>
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">调账状态：</td>
									<td style="text-align: left;">
										<select id="respCode" name="respCode" class="input-mini" style="border: 1px solid #cacaca;" disabled="disabled">
											<option value="0" <c:if test="${info.respCode == '0' }">selected="selected"</c:if>>成功</option>
											<option value="1" <c:if test="${info.respCode == '1' }">selected="selected"</c:if>>失败</option>
											<option value="2" <c:if test="${info.respCode == '2' }">selected="selected"</c:if>>作废</option>
										</select>
										-
										<input type="text" id="respMsg" name="respMsg" value="${info.respMsg}" disabled="disabled" />
									</td>
									<td style="text-align: left;">TLOG_ID：</td>
									<td style="text-align: left;">
										<input type="text" id="tlogId" name="tlogId" value="${info.tlogId}" disabled="disabled"/>
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">调账金额：</td>
									<td style="text-align: left;" width:90%;" colspan="3" rowspan="1">
										<select id="chargeFlag" name="chargeFlag" class="input-mini" style="border: 1px solid #cacaca;" disabled="disabled">
											<option value="1" <c:if test="${info.chargeFlag == '1' }">selected="selected"</c:if>>加钱</option>
											<option value="0" <c:if test="${info.chargeFlag == '0' }">selected="selected"</c:if>>减钱</option>
										</select>
										-
										<input type="text" id="chargeAmt" name="chargeAmt" value="${info.chargeAmt}" disabled="disabled"/>
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">备注：</td>
									<td style="text-align: left;">
										<textarea rows="2" cols="8" id="remarks" name="remarks" disabled="disabled">${info.remarks}</textarea>
									</td>
								</tr>
								<c:if test="${info.picRoute != null}">
									<tr>
										<td style="text-align: left;width:10%;">图片预览：</td>
										<td style="text-align: left;width:90%;" colspan="3" rowspan="1">
											<div id="imgId" style="height:200px; width:100%; overflow:auto" >
												<img alt="" id="imgSrc" name="imgSrc" src="${info.picRoute}" style="overflow:scroll;border: 1px solid #cacaca;" />
										   	</div>
										</td>
									</tr>
								</c:if>
							</table>
							<div class="sh_title">原交易信息：</div>
							<div class="sh_cm">
								<table width="100%" border="0" class="tab">
									<tr>
										<td style="text-align: left;">原交易时间：</td>
										<td style="text-align: left;">
											<input type="text" id="voidSysTime" name="voidSysTime" value="${info.voidSysTime}" disabled="disabled" />
										</td>
										<td style="text-align: left;">原交易金额：</td>
										<td style="text-align: left;">
											<input type="text" id="voidAmt" name="voidAmt" value="${info.voidAmt}" disabled="disabled" />
										</td>
									</tr>
									<tr>
										<td style="text-align: left;">原交易状态：</td>
										<td style="text-align: left;">
											<input type="text" id="voidRespCode" name="voidRespCode" value="${info.voidRespCode}" disabled="disabled" />
										</td>
									</tr>
									<tr>
										<td style="text-align: center;" colspan="4">
											<input type="button" id="closeBtn" value="关闭" class="button" />										
										</td>
									</tr>
								</table>
							</div>	
						</form>
						<div class="clear"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>