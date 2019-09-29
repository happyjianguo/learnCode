<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
	<title>消费信息统计</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		
		function exportExcel() {
			var startDt = $("#startDt").val();
			var endDt = $("#endDt").val();
			if(startDt!='' && endDt!='' && (getDate(startDt)-getDate(endDt)>0)){
			    alert("截止日期不能小于起始日期");
			    return false;
			}
			
			$("#form").attr("action","${pageContext.request.contextPath}/ClearMermccBook/exportExcelTotal");
			$("#form").submit();
		}
		
		function query() {
			var startDt = $("#startDt").val();
			var endDt = $("#endDt").val();
			if(startDt!='' && endDt!='' && (getDate(startDt)-getDate(endDt)>0)){
			    alert("截止日期不能小于起始日期");
			    return false;
			}
			
			$("#form").attr("action","${pageContext.request.contextPath}/ClearMermccBook/list");
			$("#form").submit();
		}
		
		var getCityList = function() {
			 
	    	$.ajax({
	    		url : '${pageContext.request.contextPath}/ClearMerStlFinalBook/getAreaListByFid',
	    		type : "post",
	    		data : {
	    			fid : $("#consumProvince").val()
	    		},
	    		dataType : "json",
	    		success : function(data) {
	    			
					$("#consumCity option").remove();
	    			
	    			$("#consumCity").append("<option value=''>--请选择--</option>");	
	    			if (data.length != 0) {
	    				$.each(data, function(i, info) {
	    					$("#consumCity").append(
	    							"<option title='"+info.id+"' value="+info.id+" >"+info.provinceCity + "</option>");	
	    				});
	    			}
	    		}
	    	})
		};
	
	    function resetQuery(){
	    	$("#startDt").val('');
	    	$("#endDt").val('');
	    	$("#consumProvince").select().val('');
	    	$("#consumCity").html("<option value=''>--请选择--</option>");
	    }
	    
		function getDate(date){
			 var dates = date.split("-");
			 var dateReturn = '';
			 
			 for(var i=0; i<dates.length; i++){
			  dateReturn+=dates[i];
			 }
			 return dateReturn;
		}
	</script>
</head>
<body>
	<div class="sh_main">
		<div class="shm_contab">
			<div class="poup_right">
				<div class="shmcr_cont01" style="width:auto">
					<div class="sh_title">区域消费统计</div>
					<div class="shmc_tab2">
						<form id="form" action="${pageContext.request.contextPath}/ClearMermccBook/list" method="post">
							<input type="hidden" id="curPage" name="curPage" value="${curPage}" /> 
							<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
							
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td style="text-align: right;">起始日期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${queryModel.startDt}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" name="startDt" id="startDt" />
									</td>
									<td style="text-align: right;">截止日期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${queryModel.endDt}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" name="endDt" id="endDt" />
									</td>
									<td style="text-align: right;">消费区域：</td>																	
									<td style="text-align: left;" colspan="3">
										<select id="consumProvince" name="consumProvince" class="inputext_style" value="${query.consumProvince}" onchange="getCityList();">
											<option value="">--请选择--</option>
											<c:forEach var="model" items="${provinceList}">
												<option value="${model.id}" <c:if test="${queryModel.consumProvince eq model.id}">selected="selected"</c:if>>
													${model.provinceCity}
												</option>
											</c:forEach>
										</select>																			
										--
										<select id="consumCity" name="consumCity" class="inputext_style" >
											<option value="">--请选择--</option>
											<c:forEach var="model" items="${cityList}">
												<option value="${model.id}" <c:if test="${queryModel.consumCity eq model.id}">selected="selected"</c:if>>
													${model.provinceCity}
												</option>
											</c:forEach>									
										</select>
									</td>
								</tr>
								<tr>
									<td style="text-align: right;" colspan="6">
										<input type="button" value="查询" class="button" onclick="query();"/>
										<input type="button" value="导出" class="button" onclick="exportExcel();"/>
										<input type="button" value="清空" class="button" onclick="resetQuery();"/>
									</td>	
																
								</tr>
							</table>
						</form>
						<table width="100%" border="1" cellspacing="0" cellpadding="0">
							<tr>
								<th>
									序号
								</th>
								<th>
									区域
								</th>
								<th>
									笔数
								</th>
								<th>
									金额(元)
								</th>
							</tr>
							<c:if test="${queryList != null}">
								<c:forEach items="${queryList}" var="info"
									varStatus="index">
									<tr class="cow">
										<td>
											${((curPage-1) * pageSize) + index.count }
										</td>
										<td>
											${info.consumCityName}
										</td>
										<td>
											${info.tranNum}
										</td>
										<td>
											<fmt:formatNumber value="${info.tranAmt}"  pattern="#,###,###,###,###,##0.##"
												minFractionDigits="2"/>
										</td>
									</tr>
								</c:forEach>
								<tr class="cow">
									<td colspan="6" align="center">
										<font color="red">总笔数：${total.tranNum}&emsp;&emsp;
										总金额(元)：<fmt:formatNumber value="${total.tranAmt}"  pattern="#,###,###,###,###,##0.##"
												minFractionDigits="2"/></font>
									</td>
								</tr>
								<c:if test="${fn:length(queryList)==0}">
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
							<c:if test="${queryList == null}">
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
