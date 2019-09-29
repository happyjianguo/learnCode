<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>卡类型管理</title>
		<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		
		$('#query').click(function() {
			$("#form").attr("action","${pageContext.request.contextPath}/Cardkindsof/getList");
			$("#form").submit();
		});		
		
	});	
	
	/* function toShow(id) {
		window.location.href="${pageContext.request.contextPath}/Cardkindsof/toShow?id="+id
	}	 */
	
	var toShow = function(id) {
		var laySum = $.layer({
			type : 2,
			title : false,
			zIndex : -1,
			shade : [ 0.2, '#000' ],
			closeBtn : [ 1, true ],
			area : [ '800px', '500px' ],
			iframe : {
				src : '${pageContext.request.contextPath}/Cardkindsof/toShow?cardnumber=' + id
					    + '&random=' + Math.random()
			}
		});
	}
	
	function toDelete(id){
		$.jBox.confirm("确认删除该卡类型信息吗?","系统提示",function(v,h,f){
 			if(v=="ok"){
 				$.ajax({
 					url : '${pageContext.request.contextPath}/Cardkindsof/delete?random='+ Math.random(),
 					type : "post",
 					data : {
 						cardnumber:id
 					},
 					success : function(data) {
 						if (data.result == '0') {
 							layer.alert(data.resultMsg, 1, function() {
 								$("#form").attr("action","${pageContext.request.contextPath}/Cardkindsof/getList");
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
						<div class="sh_title">卡类型信息查询</div>
						<div class="shmc_tab2">
							<form id="form" action="${pageContext.request.contextPath}/Cardkindsof/getList"
								method="post">
								<input type="hidden" id="curPage" name="curPage" value="${curPage}" />
								<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td style="text-align: right;">卡类型：</td>
										<td style="text-align: left;">
											<select id="cardnumber" name="cardnumber" class="inputext_style">
												<option value="">--请选择--</option>										
												<c:forEach var="model" items="${cardTypeList}">
													<option value="${model.cardnumber}" <c:if test="${query.cardnumber eq model.cardnumber}">selected="selected"</c:if>>
														${model.cardkindname}
													</option>
												</c:forEach>
											</select>	
										</td>
										<td style="text-align: right;">是否专属卡：</td>
										<td style="text-align: left;">
											<select id = "isExclusive" name="isExclusive" class="inputext_style">
												<option value="">--请选择--</option>
												<option value="0" <c:if test="${query.isExclusive == '0' }">selected="selected"</c:if>>否</option>
												<option value="1" <c:if test="${query.isExclusive == '1' }">selected="selected"</c:if>>是</option>
											</select>
										</td>
										<td style="text-align: right;">是否结算：</td>
										<td style="text-align: left;">
											<select id = "stlFlag" name="stlFlag" class="inputext_style">
												<option value="">--请选择--</option>
												<option value="0" <c:if test="${query.stlFlag == '0' }">selected="selected"</c:if>>不参与</option>
												<option value="1" <c:if test="${query.stlFlag == '1' }">selected="selected"</c:if>>参与</option>
											</select>
										</td>
										<td style="text-align: right;" colspan="4">
											<input type="button" value="查询" class="button" id="query" />
											<shiro:hasPermission name="cortex:cardkindsof:edit">
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
									<th>卡类型</th>
									<th>是否专属卡</th>
									<th>是否参与结算</th>
									<th>备注</th>
									<shiro:hasPermission name="cortex:cardkindsof:edit">
										<th>操作</th>
									</shiro:hasPermission>
								</tr>
								<c:if test="${cardkindsofList != null}">
									<c:forEach items="${cardkindsofList}" var="info"
										varStatus="index">
										<tr class="cow">
											<td>${((curPage-1) * pageSize) + index.count }</td>
											<td>${info.cardkindname}</td>
											<td>
												<c:if test="${info.isExclusive eq '0'}">否</c:if>
												<c:if test="${info.isExclusive eq '1'}">是</c:if>
											</td>
											<td>
												<c:if test="${info.stlFlag eq '0'}">不参与</c:if>
												<c:if test="${info.stlFlag eq '1'}">参与</c:if>
											</td>
											<td>${info.remark}</td>
											<shiro:hasPermission name="cortex:cardkindsof:edit">
												<td>
													<a href="javascript:toShow('${info.cardnumber}')">修改</a>
													<a href="javascript:toDelete('${info.cardnumber}')">删除</a>
												</td>
											</shiro:hasPermission>
										</tr>	
									</c:forEach>
									<c:if test="${fn:length(cardkindsofList)==0}">
										<td colspan="18" style="text-align: center">
											<span style="color: red">***没有相关记录***</span>
										</td>
									</c:if>
									<tr>
										<td colspan="18" class="page">${pageBar}</td>
									</tr>
								</c:if>
								<c:if test="${cardkindsofList == null}">
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
