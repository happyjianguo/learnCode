<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>卡类型管理</title>
		<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		
		$('#query').click(function() {
			$("#form").attr("action","${pageContext.request.contextPath}/CardKindComesource/getList");
			$("#form").submit();
		});		
		
	});	
	
	/* function toShow(id) {
		window.location.href="${pageContext.request.contextPath}/CardKindComesource/toShow?id="+id
	}	 */
	
	var toShow = function(id) {
		var laySum = $.layer({
			type : 2,
			title : false,
			zIndex : -1,
			shade : [ 0.2, '#000' ],
			closeBtn : [ 1, true ],
			area : [ '800px', '400px' ],
			iframe : {
				src : '${pageContext.request.contextPath}/CardKindComesource/toShow?id=' + id
					    + '&random=' + Math.random()
			}
		});
	}
	
	function toDelete(id){
		$.jBox.confirm("确认删除该卡类型来源信息吗?","系统提示",function(v,h,f){
 			if(v=="ok"){
 				$.ajax({
 					url : '${pageContext.request.contextPath}/CardKindComesource/delete?random='+ Math.random(),
 					type : "post",
 					data : {
 						id:id
 					},
 					success : function(data) {
 						if (data.result == '0') {
 							layer.alert(data.resultMsg, 1, function() {
 								$("#form").attr("action","${pageContext.request.contextPath}/CardKindComesource/getList");
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
						<div class="sh_title">卡类型来源信息查询</div>
						<div class="shmc_tab2">
							<form id="form" action="${pageContext.request.contextPath}/CardKindComesource/getList"
								method="post">
								<input type="hidden" id="curPage" name="curPage" value="${curPage}" />
								<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td style="text-align: right;">卡产品：</td>
										<td style="text-align: left;">
											<input type="text" name="oldKindidLike" id="oldKindidLike" value="${query.oldKindidLike }" maxlength="20" class="inputext_style"/>
										</td>
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
										<td style="text-align: right;">数据库来源：</td>
										<td style="text-align: left;">
											<select id = "dataBaseType" name="dataBaseType" class="inputext_style">
												<option value="">--请选择--</option>
												<option value="1" <c:if test="${query.dataBaseType == '1' }">selected="selected"</c:if>>旧库1</option>
												<option value="2" <c:if test="${query.dataBaseType == '2' }">selected="selected"</c:if>>旧库2</option>
												<option value="3" <c:if test="${query.dataBaseType == '3' }">selected="selected"</c:if>>新卡库</option>
												<option value="4" <c:if test="${query.dataBaseType == '4' }">selected="selected"</c:if>>旧库3</option>
											</select>
										</td>
										<td style="text-align: right;" colspan="4">
											<input type="button" value="查询" class="button" id="query" />
											<shiro:hasPermission name="cortex:CardKindComesource:edit">
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
									<th>数据库来源</th>
									<th>卡产品</th>
									<shiro:hasPermission name="cortex:CardKindComesource:edit">
										<th>操作</th>
									</shiro:hasPermission>
								</tr>
								<c:if test="${cardKindComesourceList != null}">
									<c:forEach items="${cardKindComesourceList}" var="info"
										varStatus="index">
										<tr class="cow">
											<td>${((curPage-1) * pageSize) + index.count }</td>
											<td>${info.cardkindname}</td>
											<td>${info.databasesourcename}</td>
											<td>${info.oldKindid}</td>
											<shiro:hasPermission name="cortex:CardKindComesource:edit">
												<td>
													<a href="javascript:toShow('${info.id}')">修改</a>
													<a href="javascript:toDelete('${info.id}')">删除</a>
												</td>
											</shiro:hasPermission>
										</tr>	
									</c:forEach>
									<c:if test="${fn:length(cardKindComesourceList)==0}">
										<td colspan="18" style="text-align: center">
											<span style="color: red">***没有相关记录***</span>
										</td>
									</c:if>
									<tr>
										<td colspan="18" class="page">${pageBar}</td>
									</tr>
								</c:if>
								<c:if test="${cardKindComesourceList == null}">
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
