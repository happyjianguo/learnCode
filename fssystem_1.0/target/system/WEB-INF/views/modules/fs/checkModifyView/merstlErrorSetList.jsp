<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>卡余额调整</title>
		<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		
		$('#query').click(function() {
			$("#form").attr("action","${pageContext.request.contextPath}/MerstlErrorSet/getList");
			$("#form").submit();
		});		
		
	});	
	
	var toShow = function(id) {
		var laySum = $.layer({
			type : 2,
			title : false,
			zIndex : -1,
			shade : [ 0.2, '#000' ],
			closeBtn : [ 1, true ],
			area : [ '800px', '400px' ],
			iframe : {
				src : '${pageContext.request.contextPath}/MerstlErrorSet/toShow?id=' + id
					    + '&random=' + Math.random()
			}
		});
	}
	
	/* function toDelete(id){
		$.jBox.confirm("确认删除该卡余额调整信息吗?","系统提示",function(v,h,f){
 			if(v=="ok"){
 				$.ajax({
 					url : '${pageContext.request.contextPath}/MerstlErrorSet/delete?random='+ Math.random(),
 					type : "post",
 					data : {
 						id:id
 					},
 					success : function(data) {
 						if (data.result == '0') {
 							layer.alert(data.resultMsg, 1, function() {
 								$("#form").attr("action","${pageContext.request.contextPath}/MerstlErrorSet/getList");
 								$("#form").submit();
 							});
 						} else {
 							layer.alert(data.resultMsg);
 						}
 					}
 				});
			}
		},{buttonsFocus:1});
		$('.jbox-body .jbox-icon').css('top','55px');
	} */
	
</script>
	</head>
	<body>
		<div class="sh_main">
			<div class="shm_contab">
				<div class="poup_right">
					<div class="shmcr_cont01">
						<div class="sh_title">卡余额调整信息</div>
						<div class="shmc_tab2">
							<form id="form" action="${pageContext.request.contextPath}/MerstlErrorSet/getList"
								method="post">
								<input type="hidden" id="curPage" name="curPage" value="${curPage}" />
								<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td style="text-align: right;"></td>
										<td style="text-align: left;"></td>
										<td style="text-align: right;"></td>
										<td style="text-align: left;"></td>
										<td style="text-align: right;" colspan="4">
											<input type="button" value="查询" class="button" id="query" />
											<shiro:hasPermission name="fs:MerstlErrorSet:edit">
												<%-- <input id="btnAdd" type="button" value="添加" class="button" 
													onclick="toShow(' ');"/> --%>
											</shiro:hasPermission>
										</td>
									</tr>
								</table>
							</form>
							<table width="100%" border="1" cellspacing="0" cellpadding="0">
								<tr>
									<th>序号</th>
									<th>卡张数调整</th>
									<th>卡余额调整</th>
									<shiro:hasPermission name="fs:MerstlErrorSet:edit">
										<th>操作</th>
									</shiro:hasPermission>
								</tr>
								<c:if test="${merstlErrorSetList != null}">
									<c:forEach items="${merstlErrorSetList}" var="info"
										varStatus="index">
										<tr class="cow">
											<td>${((curPage-1) * pageSize) + index.count }</td>
											<td>${info.errorCardNum}</td>
											<td>${info.errorCardAmt}</td>
											<shiro:hasPermission name="fs:MerstlErrorSet:edit">
												<td>
													<a href="javascript:toShow('${info.id}')">修改</a>
													<%-- <a href="javascript:toDelete('${info.id}')">删除</a> --%>
												</td>
											</shiro:hasPermission>
									</c:forEach>
									<c:if test="${fn:length(merstlErrorSetList)==0}">
										<td colspan="18" style="text-align: center">
											<span style="color: red">***没有相关记录***</span>
										</td>
									</c:if>
									<tr>
										<td colspan="18" class="page">${pageBar}</td>
									</tr>
								</c:if>
								<c:if test="${merstlErrorSetList == null}">
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
