<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>卡类型码表</title>
		<meta name="decorator" content="default" />
		<script type="text/javascript">
			$(document).ready(function() {
				
				$('#query').click(function() {
					$("#form").attr("action","${pageContext.request.contextPath}/wankeCardBin/list");
					$("#form").submit();
				});	
				
				$('#exportExcel').click(function() {
					$("#form").attr("action","${pageContext.request.contextPath}/wankeCardBin/exportExcel");
					$("#form").submit();
				});
			});	
			
			function resetQuery() {
				$("#cardtypename").val("");
				$("#cardtypeIdName").val("");
			}
			
			var toShow = function(id) {
				var laySum = $.layer({
					type : 2,
					title : false,
					zIndex : -1,
					shade : [ 0.2, '#000' ],
					closeBtn : [ 1, true ],
					area : [ '800px', '300px' ],
					iframe : {
						src : '${pageContext.request.contextPath}/wankeCardBin/toShow?cardId=' + id
							    + '&random=' + Math.random()
					}
				});
			}
			
			function toDelete(id){
				if(confirm("确认删除该卡类型信息吗？")){
					$.ajax({
						url : '${pageContext.request.contextPath}/wankeCardBin/delete?cardId=' + id + '&random='+ Math.random(),
						type : "post",
						success : function(data) {
							if (data.result == '0') {
								alert(data.resultMsg);
								$("#form").attr("action","${pageContext.request.contextPath}/wankeCardBin/list");
								$("#form").submit();
							} else {
								alert(data.resultMsg);
							}
						}
					});
				}
			}
		</script>
	</head>
	<body>
		<div class="sh_main">
			<div class="shm_contab">
				<div class="poup_right">
					<div class="shmcr_cont01">
						<div class="sh_title">卡类型列表</div>
						<div class="shmc_tab2">
							<form id="form" action="${pageContext.request.contextPath}/wankeCardBin/list"
								method="post">
								<input type="hidden" id="curPage" name="curPage" value="${curPage}" />
								<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td style="text-align: right;">万科卡BIN：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style" value="${queryModel.cardBin}" name="cardBin" id="cardBin" maxlength="9"/>
										</td>
										<td style="text-align: right;" colspan="8">
											<input type="button" value="查询" class="button" id="query" />
											<input type="button" value="导出" class="button" id="exportExcel" />
											<shiro:hasPermission name="fs:wankeCardBin:edit">
												<input type="button" value="增加" class="button" onclick="toShow(' ')" />
											</shiro:hasPermission>
											<input type="button" value="清空" class="button" onClick="ClearForm('form')"/>
										</td>
									</tr>
								</table>
							</form>
							<table width="100%" border="1" cellspacing="0" cellpadding="0">
								<tr>
									<th>序号</th>
									<th>卡BIN-ID</th>
									<th>万科卡BIN</th>
									<shiro:hasPermission name="fs:wankeCardBin:edit">
										<th>操作</th>
									</shiro:hasPermission>
								</tr>
								<c:if test="${wankeCardBinList != null}">
									<c:forEach items="${wankeCardBinList}" var="info"
										varStatus="index">
										<tr class="cow">
											<td>
												${((curPage-1) * pageSize) + index.count }
											</td>
											<td>${info.cardId}</td>
											<td>${info.cardBin}</td>
											<shiro:hasPermission name="fs:wankeCardBin:edit">
												<td>
													<a href="javascript:toShow('${info.cardId}')">修改</a>
													<a href="javascript:toDelete('${info.cardId}')">删除</a>
												</td>
											</shiro:hasPermission>
									</c:forEach>
									<c:if test="${fn:length(wankeCardBinList)==0}">
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
								<c:if test="${wankeCardBinList == null}">
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
