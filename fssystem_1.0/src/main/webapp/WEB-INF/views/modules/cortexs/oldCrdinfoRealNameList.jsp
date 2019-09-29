<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>实名认证信息管理</title>
		<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		
		$("#exportExcel").click(function() {
			$("#form").attr("action","${pageContext.request.contextPath}/OldCrdinfoRealName/exportExcel");
			$("#form").submit();
		});
		
		$("#exportExcelAll").click(function() {
			$("#form").attr("action","${pageContext.request.contextPath}/OldCrdinfoRealName/exportExcelAll");
			$("#form").submit();
		});
		
		$('#query').click(function() {
			$("#form").attr("action","${pageContext.request.contextPath}/OldCrdinfoRealName/list");
			$("#form").submit();
		});		
	});	
	
	function resetQuery() {
		$("#custName").val("");
		$("#idNum").val("");
		$("#begainTrueName").val("");
		$("#endTrueName").val("");
	}

	//删除左右两端的空格
	function trim(str){ 
		return str.replace(/(^\s*)|(\s*$)/g, "");
	}
</script>
	</head>
	<body>
		<div class="sh_main">
			<div class="shm_contab">
				<div class="poup_right">
					<div class="shmcr_cont01">
						<div class="sh_title">
							实名认证信息列表
						</div>
						<div class="shmc_tab2">
							<form id="form" action="${pageContext.request.contextPath}/OldCrdinfoRealName/list"
								method="post">
								<input type="hidden" id="curPage" name="curPage"
									value="${curPage}" />
								<input type="hidden" id="pageSize" name="pageSize"
									value="${pageSize}" />
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td style="text-align: right;">姓名：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style" value="${query.custName}" name="custName" id="custName" maxlength="64"/>
										</td>
										<td style="text-align: right;">身份证号：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style" value="${query.idNum}" name="idNum" id="idNum" maxlength="20"/>
										</td>
										<td style="text-align: right;">实名日期：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style"
												value="${query.begainTrueName}" readonly="readonly"
												onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true,readOnly:true})"
												name="begainTrueName" id="begainTrueName" />-
											<input type="text" class="inputext_style"
												value="${query.endTrueName}" readonly="readonly"
												onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true,readOnly:true})"
												name="endTrueName" id="endTrueName" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right;" colspan="6">
											<input type="button" value="查询" class="button" id="query" />
											<input type="button" value="列表导出" class="button"
												id="exportExcel" />
											<input type="button" value="全部导出" class="button"
												id="exportExcelAll" />
											<input type="button" value="清空" class="button"
												onclick='resetQuery()' />
										</td>
									</tr>
								</table>
							</form>
							<table width="100%" border="1" cellspacing="0" cellpadding="0">
								<tr>
									<th>序号</th>
									<th>福卡卡号</th>
									<th>姓名</th>
									<th>手机号</th>
									<th>身份证号</th>
									<th>地址</th>
									<th>邮箱信息</th>
								</tr>
								<c:if test="${oldCrdinfoList != null}">
									<c:forEach items="${oldCrdinfoList}" var="info"
										varStatus="index">
										<tr class="cow">
											<td>
												${((curPage-1) * pageSize) + index.count }
											</td>
											<td>
												${info.pan}
											</td>
											<td>
												${info.custName}
											</td>											
											<td>
												${info.cellPhone}
											</td>
											<td>
												${info.idNum}
											</td>
											<td>
												${info.address}
											</td>
											<td>
												${info.mailBox}
											</td>
									</c:forEach>
									<c:if test="${fn:length(oldCrdinfoList)==0}">
										<td colspan="18" style="text-align: center">
											<span style="color: red">***没有相关记录***</span>
										</td>
									</c:if>
									<tr>
										<td colspan="18" class="page">
											${pageBar}
										</td>
									</tr>
								</c:if>
								<c:if test="${ oldCrdinfoList == null  }">
									<tr>
										<td colspan="18" style="text-align: center">
											<span style="color: red">***请查询***</span>
										</td>
									</tr>
								</c:if>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
