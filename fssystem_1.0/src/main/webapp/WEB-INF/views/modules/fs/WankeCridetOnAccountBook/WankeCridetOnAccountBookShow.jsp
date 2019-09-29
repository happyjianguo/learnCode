<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户日汇总信息</title>
	<meta name="decorator" content="default"/>
<script type="text/javascript">
$(document).ready(function() {
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	$('#closeBtn').click(function() {
		parent.layer.close(index);
	});
	$("#form").validate({
		onsubmit : true,// 是否在提交是验证
		onfocusout : false,// 是否在获取焦点时验证
		onkeyup : false,// 是否在敲击键盘时验证
		rules : {
		},
		//提交表单
		submitHandler : function(form) {
			var param = $("#form").serialize();
			url = $("#form").attr("action");
			$.ajax({
				url : url,
				type : "post",
				data : param,
				dataType : "json",
				success : function(data) {
					if (data.result == '0') {
						layer.alert(data.resultMsg, 1, function() {
							$("#form", window.parent.document).submit();
						});
					} else {
						layer.alert(data.resultMsg);
					}
				}
			});
		}
	});
});

</script>
</head>
<body>
	<div class="sh_main">
		<div class="shm_contab">
			<div class="poup_right">
				<div class="shmcr_cont01">
					<div class="sh_title">查看授信挂账明细单信息</div>
					<div class="sh_cm">
						<form id="form" action="${pageContext.request.contextPath}/WankeCridetOnAccountBook/getList" method="post">
							<table width="100%" border="0" class="tab">
								<tr>
									<td style="text-align: left;">商户编号：</td>
									<td style="text-align: left;">
										${info.merNo}
										<input type="hidden" id="id" name="id" class="inputext_style" value="${info.id}" />	
									</td>
									<td style="text-align: left;">商户名称：</td>
									<td style="text-align: left;">
										${info.merName}
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">终端号：</td>
									<td style="text-align: left;">
										${info.terNo}
									</td>
									<td style="text-align: left;">卡号：</td>
									<td style="text-align: left;">
										${info.pan}
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">持卡人类型：</td>
									<td style="text-align: left;">
										<c:if test="${info.cardType=='1'}">1-业主</c:if>	
										<c:if test="${info.cardType=='2'}">2-员工</c:if>																			
									</td>
									<td style="text-align: left;">挂账日期：</td>
									<td style="text-align: left;">
										${info.credetDate}
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">挂账时间：</td>
									<td style="text-align: left;">
										${info.credetTime}
									</td>
									<td style="text-align: left;">挂账金额：</td>
									<td style="text-align: left;">
										<fmt:formatNumber value="${info.creditAmt}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">还款金额：</td>
									<td style="text-align: left;">
									<fmt:formatNumber value="${info.repayAmt}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
									</td>
									<td style="text-align: left;">还款日期：</td>
									<td style="text-align: left;">
										${info.repayDate}
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">还款时间：</td>
									<td style="text-align: left;">
										${info.repayTime}
									</td>
									<td style="text-align: left;">剩余欠款：</td>
									<td style="text-align: left;">
										<fmt:formatNumber value="${info.debtAmt}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">卡项目编码(售卡网点)ID：</td>
									<td style="text-align: left;">
										${info.icSaleCodeid}
									</td>
									<td style="text-align: left;">卡项目编码(售卡网点)：</td>
									<td style="text-align: left;">
										${info.icSaleCode}
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">卡项目名称：</td>
									<td style="text-align: left;">
										${info.icSaleName}
									</td>
									<td style="text-align: left;">项目编码(充值与赎回)ID：</td>
									<td style="text-align: left;">
										${info.icItemCodeid}
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">项目编码 ：</td>
									<td style="text-align: left;">
										${info.icItemCode}
									</td>
									<td style="text-align: left;">项目名称：</td>
									<td style="text-align: left;">
										${info.icItemName}
									</td>
								</tr>
								
								<tr>
									<td style="text-align: left;">卡片所有者：</td>
									<td style="text-align: left;">
										${info.cardOwner}
									</td>
									<td style="text-align: left;">电话号码：</td>
									<td style="text-align: left;">
										${info.telphone}
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