<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>节假日结算合并管理</title>
		<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		
		$('#query').click(function() {
			$("#form").attr("action","${pageContext.request.contextPath}/CombineDate/getList");
			$("#form").submit();
		});		
		
	});	
	
	/* function toShow(id) {
		window.location.href="${pageContext.request.contextPath}/CombineDate/toShow?id="+id
	}	 */
	
	var toShow = function(id) {
		var laySum = $.layer({
			type : 2,
			title : false,
			zIndex : -1,
			shade : [ 0.2, '#000' ],
			closeBtn : [ 1, true ],
			area : [ '800px', '300px' ],
			iframe : {
				src : '${pageContext.request.contextPath}/CombineDate/toShow?id=' + id
					    + '&random=' + Math.random()
			}
		});
	}
	
	function toDelete(id){
		$.jBox.confirm("确认删除节假日结算合并信息吗?","系统提示",function(v,h,f){
			if(v=="ok"){
				$.ajax({
					url : '${pageContext.request.contextPath}/CombineDate/delete?random='+ Math.random(),
					type : "post",
					data : {
						id:id
					},
					success : function(data) {
						if (data.result == '0') {
							layer.alert(data.resultMsg, 1, function() {
								$("#form").attr("action","${pageContext.request.contextPath}/CombineDate/getList");
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
	} 
	
</script>
	</head>
	<body>
		<div class="sh_main">
			<div class="shm_contab">
				<div class="poup_right">
					<div class="shmcr_cont01">
						<div class="sh_title">节假日结算合并信息查询</div>
						<div class="shmc_tab2">
							<form id="form" action="${pageContext.request.contextPath}/CombineDate/getList"
								method="post">
								<input type="hidden" id="curPage" name="curPage" value="${curPage}" />
								<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td style="text-align: right;">开始日期：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style" value="${query.startDate}" name="startDate" id="startDate" maxlength="8"/>
										</td>
										<td style="text-align: right;">结束日期：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style" value="${query.endDate}" name="endDate" id="endDate" maxlength="8"/>
										</td>
										<td style="text-align: right;">处理状态：</td>
										<td style="text-align: left;">
											<select id = "doneFlag" name="doneFlag" class="inputext_style">
												<option value="">--请选择--</option>
												<option value="0" <c:if test="${query.doneFlag == '0' }">selected="selected"</c:if>>未处理</option>
												<option value="1" <c:if test="${query.doneFlag == '1' }">selected="selected"</c:if>>已处理</option>
											</select>
										</td>
										<td style="text-align: right;" colspan="4">
											<input type="button" value="查询" class="button" id="query" />
											<shiro:hasPermission name="fs:combineDate:edit">
												<input id="btnAdd" type="button" value="添加" class="button" 
													onclick="toShow(' ');"/>
											</shiro:hasPermission>
											<input type="button" value="清空" class="button" onClick="ClearForm('form')"/>
										</td>
									</tr>
								</table>
							</form>
							<table width="100%" border="1" cellspacing="0" cellpadding="0">
								<tr>
									<th>序号</th>
									<th>开始日期</th>
									<th>结束日期</th>
									<th>处理状态</th>
									<th>处理时间</th>
									<shiro:hasPermission name="fs:cardshare:edit">
										<th>操作</th>
									</shiro:hasPermission>
								</tr>
								<c:if test="${combineDateList != null}">
									<c:forEach items="${combineDateList}" var="info"
										varStatus="index">
										<tr class="cow">
											<td>${((curPage-1) * pageSize) + index.count }</td>
											<td>${info.startDate}</td>
											<td>${info.endDate}</td>
											<td>
												<c:if test="${info.doneFlag eq '0'}">未处理</c:if>
												<c:if test="${info.doneFlag eq '1'}">已处理</c:if>
											</td>
											<td>
												<fmt:formatDate value="${info.doneDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
											</td>
											<shiro:hasPermission name="fs:combineDate:edit">
												<td>
													<c:if test="${info.doneFlag ne '1'}">
														<a href="javascript:toShow('${info.id}')">修改</a>
														<a href="javascript:toDelete('${info.id}')">删除</a>
													</c:if>
												</td>
											</shiro:hasPermission>
										</tr>	
									</c:forEach>
									<c:if test="${fn:length(combineDateList)==0}">
										<td colspan="18" style="text-align: center">
											<span style="color: red">***没有相关记录***</span>
										</td>
									</c:if>
									<tr>
										<td colspan="18" class="page">${pageBar}</td>
									</tr>
								</c:if>
								<c:if test="${combineDateList == null}">
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
