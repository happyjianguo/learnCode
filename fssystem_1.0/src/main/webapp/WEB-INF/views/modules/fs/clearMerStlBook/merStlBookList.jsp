<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*,org.apache.commons.lang3.StringUtils,org.apache.commons.lang3.ObjectUtils" %>

<html>
<head>
	<title>结算初表信息</title>
	<meta name="decorator" content="default"/>
<script type="text/javascript">
	function exportExcel(flag) {
		$("#form").attr("action","${pageContext.request.contextPath}/MerStlBookList/exportExcel?flagOnline="+flag);
		$("#form").submit();
	}
	
	function query(flag) {
		$("#form").attr("action","${pageContext.request.contextPath}/MerStlBookList/getList?flagOnline="+flag);
		if($("#startStlDate").val()>$("#endStlDate").val()){
			alert("结算日期开始日期必须大于终止日期！");
			document.getElementById("startStlDate").click();
		}else{
			$("#form").submit();
		}
	}	

	var toShowDetail = function(id) {
		var laySum = $.layer({
			type : 2,
			title : false,
			zIndex : -1,
			shade : [ 0.2, '#000' ],
			closeBtn : [ 1, true ],
			area : [ '1000px', '480px' ],
			iframe : {
				src : '/MerStlBookList/getDetailList?id=' + id
					    + '&random='
						+ Math.random()
			}
		});
	}	
	var resetQuery = function() {
		$("#merNo").val("");
		$("#merName").val("");
		$("#startStlDate").val("");
		$("#endStlDate").val("");
		$("#scene").select().val("");
	}
	function CurentDayTime(flag) {
		var now = new Date();
		var year = now.getFullYear(); 	//年
		var month = now.getMonth() + 1; //月
		
		var day = now.getDate(); 		//日
		if(flag!=null&&flag=="-1"){
			day = now.getDate()-1; 
		}else if(flag!=null&&flag=="+1"){
			day = now.getDate()+1; 
		}		
		var clock = year + "";
		if (month < 10)
			clock += "0";
		clock += month + "";
		if (day < 10)
			clock += "0";
		clock += day + "";
	 	
		return (clock);
	}
	
</script>
</head>
<body>
	<div class="sh_main">
		<div class="shm_contab">
			<div class="poup_right">
				<div class="shmcr_cont01">
					<div class="sh_title">结算初表</div>
					<div class="shmc_tab2">
						<form id="form" action="${pageContext.request.contextPath}/ClearMerStlBook/getList" method="post">
							<input type="hidden" id="curPage" name="curPage" value="${curPage}" /> 
							<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td style="text-align: right;">商户编号：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.merNo}" name="merNo" id="merNo" maxlength="15"/>
									</td>
									<td style="text-align: right;">商户名称：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.merName}" name="merName" id="merName" maxlength="15"/>
									</td>
									<td style="text-align: right;"></td>
									<td style="text-align: left;">
									</td>
									<td style="text-align: right;">消费场景</td>																	
									<td style="text-align: left;">
										<select id="scene" name="scene" class="inputext_style" value="${query.scene}">
											<option value="">--请选择--</option>										
											<c:forEach var="model" items="${consumpCategoryList}">
												<option value="${model.paramValue}" <c:if test="${query.scene eq model.paramValue}">selected="selected"</c:if>>
													${model.paramName}
												</option>
											</c:forEach>
										</select>																			
									</td>																														
								</tr>
								<tr>
									<td style="text-align: right;">结算日期开始：</td>
									<td style="text-align: left;">
										<input type="text" width="10" class="inputext_style" value="${query.startStlDate}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" id="startStlDate" name="startStlDate" />
									</td>
									<td style="text-align: right;">
										结算日期结束：
									</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.endStlDate}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" id="endStlDate" name="endStlDate" />														
									</td>
									<td style="text-align: right;"></td>
									<td style="text-align: left;">								
									</td>																										
									<td style="text-align: right;"  colspan="2">
									</td>	
								</tr>								
								
								<tr>									
									<td style="text-align: right;"></td>
									<td style="text-align: left;">
									</td>
									<td style="text-align: right;"></td>
									<td style="text-align: left;">
									</td>
									<td style="text-align: right;"></td>
									<td style="text-align: left;">								
									</td>																										
									<td style="text-align: right;" colspan="2">
										<input type="button" value="查询" class="button" onclick="query('<c:out value="${query.flagOnline }"/>')"/>
										<input type="button" value="导出" class="button" onclick="exportExcel('<c:out value="${query.flagOnline }"/>')"/>
										<input type="button" value="清空" class="button" onClick='resetQuery()'/>
									</td>									
								</tr>
							</table>
						</form>
						<table width="100%" border="1" cellspacing="0" cellpadding="0">
							<tr>
								<th>序号</th>									
								<th>商户编号</th>
								<th>商户名称</th>
								<th>所属帐期</th>
								<th>消费场景</th>
								<th>笔数</th>								
								<th>金额（元）</th>
								<th>手续费率（%）</th>
								<th>手续费（元）</th>																
								<th>净额（元）</th>																
								<th>手续费是否单独结</th>
								<th>实际结算金额（元）</th>								
								<th>结算账号</th>
								<th>结算银行</th>
								<th>开户名称</th>
								<th>结算日期</th>
								<th>是否合并</th>
								<th>明细</th>
							</tr>
							<c:if test="${ ClearMerStlBookList != null  }">
								<c:forEach items="${ClearMerStlBookList}" var="info" varStatus="index">
									<tr class="cow">
										<td>${((curPage-1) * pageSize) + index.count }</td>									
										<td>${info.merNo}</td>
										<td>${info.merName}</td>
										<td>${info.startStlDate}-${info.endStlDate}</td>
										<td>${info.sceneName}</td>
										<td>${info.consumNum}</td>
										<td>${info.consumAmt}</td>										
										<td>${info.feeOrder}</td>
										<td>${info.fee}</td>
										<td>${info.netAmt}</td>
										<td>
											<c:if test="${info.feestlType=='0'}">否</c:if>	
											<c:if test="${info.feestlType=='1'}">是</c:if>	
										</td>
										<td>${info.tranAmt}</td>
										<td>${info.accNo}</td>
										<td>${info.bankName}</td>										
										<td>${info.branchName}</td>	
										<td>${info.stlDate}</td>	
										<td>
											<c:if test="${info.amtConsumpCategory=='0'}">否</c:if>	
											<c:if test="${info.amtConsumpCategory=='1'}">是</c:if>											
										</td>															
										<td>
											<a href="javascript:toShowDetail('${info.id}')">明细</a>																																									
										</td>																				
								</c:forEach>
								<c:if test="${fn:length(ClearMerStlBookList)==0}">
									<td colspan="18" style="text-align: center"><span style="color: red">***没有相关记录***</span></td>
								</c:if>
								<tr>
									<td colspan="18" class="page">${pageBar}</td>
								</tr>
							</c:if>
							<c:if test="${ ClearMerStlBookList == null  }">
								<tr>
									<td colspan="18" style="text-align: center"><span style="color: red">***请查询***</span></td>
								</tr>
							</c:if>							
							<c:if test="${ ClearMerStlBookList != null  }">
								<tr>
									<td colspan="18" style="text-align: center">
										<span style="color: red">
										合计
										&nbsp;&nbsp;&nbsp;&nbsp;笔数:${consum_num}
										&nbsp;&nbsp;&nbsp;&nbsp;金额：${consum_amt}
										&nbsp;&nbsp;&nbsp;&nbsp;手续费：${fee}
										&nbsp;&nbsp;&nbsp;&nbsp;净额：${net_amt}
										&nbsp;&nbsp;&nbsp;&nbsp;实际结算金额：${tran_amt}								
										
										</span>
									</td>
								</tr>
							</c:if>
						</table>
						
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- <div class="sh_footer"></div> -->
</body>
</html>
