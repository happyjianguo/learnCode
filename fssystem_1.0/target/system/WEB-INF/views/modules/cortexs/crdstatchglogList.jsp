<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>卡状态管理</title>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() {
				$("#form").attr("action","${pageContext.request.contextPath}/cortexs/crdStatChgLog/list");				
				$("#form").submit();
			});	
			
			$("#form").validate({
				onsubmit : true,// 是否在提交是验证
				onfocusout : false,// 是否在获取焦点时验证
				onkeyup : false,// 是否在敲击键盘时验证
				rules : {
					crdNo : {required : true, number : true,
						minlength:16,  //设置最小长度  
          			  	maxlength:16,}
				},
				messages: {
					crdNo: {required: "请填写卡号.",number:"请输入16位数字的卡号",minlength:"请输入16位数字的卡号"}

				}
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#form").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${pageContext.request.contextPath}/cortexs/crdStatus/">卡状态设置列表</a></li>
<%-- 		<shiro:hasPermission name="cortexs:tTranType:edit"><li><a href="${pageContext.request.contextPath}/cortexs/crdStatus/form">交易类型设置添加</a></li></shiro:hasPermission>
 --%>	</ul>
	
<div class="poup_right">
	
		
	<div class="shmc_tab2">
	<form id="form" action="${pageContext.request.contextPath}/cortexs/crdStatChgLog/" method="post">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td style="text-align: right;">卡号：</td>
				<td style="text-align: left;">
					<input type="text" name=crdNo id="crdNo" value="${Crdstatchglog.crdNo }" maxlength="200" class="inputext_style"/>
				</td>
				
				<td>
					<input id="btnSubmit" class="button" type="submit" value="查询"/>
				</td>
			</tr>
		</table>
	</form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>交易类型</th>
				<th>原状态</th>
				<th>当前状态</th>
				<th>更新时间</th>
				<th>备注</th>
				<th>登录名</th>
				<th>卡号</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="crdstatchgLog">
			<tr>
				<td>
					${crdstatchgLog.tranType}
				</td>
				<td>
					<%-- 
					${crdstatchgLog.oriStatus} --%>
					<c:if test="${crdstatchgLog.oriStatus=='94' }">94-合卡冻结 </c:if>
					<c:if test="${crdstatchgLog.oriStatus=='92' }">92-异号补卡冻结 </c:if>
					<c:if test="${crdstatchgLog.oriStatus=='91' }">91-消磁补卡冻结 </c:if>
					<c:if test="${crdstatchgLog.oriStatus=='90' }">90-拆卡冻结 </c:if>
					<c:if test="${crdstatchgLog.oriStatus=='93' }">93-未使用 </c:if>
					<c:if test="${crdstatchgLog.oriStatus=='97' }">97-金额冻结 </c:if>
					<c:if test="${crdstatchgLog.oriStatus=='98' }">98- 其他(冻结)</c:if>
					<c:if test="${crdstatchgLog.oriStatus=='95' }">95-作废 </c:if>
					<c:if test="${crdstatchgLog.oriStatus=='00' }">00 -正常 </c:if>
					<c:if test="${crdstatchgLog.oriStatus=='01' }">01- 密码尝试超限</c:if>
					<c:if test="${crdstatchgLog.oriStatus=='02' }">02- 未发行</c:if>
					<c:if test="${crdstatchgLog.oriStatus=='03' }">03- 卡过期</c:if>
					<c:if test="${crdstatchgLog.oriStatus=='04' }">04- 挂失</c:if>
					<c:if test="${crdstatchgLog.oriStatus=='05' }">05- 失窃</c:if>
					<c:if test="${crdstatchgLog.oriStatus=='06' }">06- 客户注销</c:if>
					<c:if test="${crdstatchgLog.oriStatus=='07' }">07- 银行取消</c:if>
					<c:if test="${crdstatchgLog.oriStatus=='08' }">08- 欺诈使用</c:if>
					<c:if test="${crdstatchgLog.oriStatus=='20' }">20- 等待激活</c:if>
					<c:if test="${crdstatchgLog.oriStatus=='99' }">99- 未激活</c:if>
					<c:if test="${crdstatchgLog.oriStatus=='96' }">96- 黑名单</c:if>
				</td>
				<td>
					<%-- ${crdstatchgLog.curStatus} --%>
					<c:if test="${crdstatchgLog.curStatus=='94' }">94-合卡冻结 </c:if>
					<c:if test="${crdstatchgLog.curStatus=='92' }">92-异号补卡冻结 </c:if>
					<c:if test="${crdstatchgLog.curStatus=='91' }">91-消磁补卡冻结 </c:if>
					<c:if test="${crdstatchgLog.curStatus=='90' }">90-拆卡冻结 </c:if>
					<c:if test="${crdstatchgLog.curStatus=='93' }">93-未使用 </c:if>
					<c:if test="${crdstatchgLog.curStatus=='97' }">97-金额冻结 </c:if>
					<c:if test="${crdstatchgLog.curStatus=='98' }">98- 其他(冻结)</c:if>
					<c:if test="${crdstatchgLog.curStatus=='95' }">95-作废 </c:if>
					<c:if test="${crdstatchgLog.curStatus=='00' }">00 -正常 </c:if>
					<c:if test="${crdstatchgLog.curStatus=='01' }">01- 密码尝试超限</c:if>
					<c:if test="${crdstatchgLog.curStatus=='02' }">02- 未发行</c:if>
					<c:if test="${crdstatchgLog.curStatus=='03' }">03- 卡过期</c:if>
					<c:if test="${crdstatchgLog.curStatus=='04' }">04- 挂失</c:if>
					<c:if test="${crdstatchgLog.curStatus=='05' }">05- 失窃</c:if>
					<c:if test="${crdstatchgLog.curStatus=='06' }">06- 客户注销</c:if>
					<c:if test="${crdstatchgLog.curStatus=='07' }">07- 银行取消</c:if>
					<c:if test="${crdstatchgLog.curStatus=='08' }">08- 欺诈使用</c:if>
					<c:if test="${crdstatchgLog.curStatus=='20' }">20- 等待激活</c:if>
					<c:if test="${crdstatchgLog.curStatus=='99' }">99- 未激活</c:if>
					<c:if test="${crdstatchgLog.curStatus=='96' }">96- 黑名单</c:if>
				</td>				
				<td>
					<%-- <s:date name="${crdstatchgLog.updateTime}" format="yyyy-MM-dd hh:MM:ss"/> 
					${crdstatchgLog.updateTime} --%>
					<fmt:formatDate value="${crdstatchgLog.updateTime}" pattern="yyyy-MM-dd hh:MM:ss"/>
				</td>
				<td>
					${crdstatchgLog.content}
				</td>
				<td>
					${crdstatchgLog.loginName}
				</td>
				<td>
					${crdstatchgLog.crdNo}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
	
</div>
	
</body>
</html>