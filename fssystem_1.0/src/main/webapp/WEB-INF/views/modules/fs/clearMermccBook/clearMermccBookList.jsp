<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*,org.apache.commons.lang3.StringUtils,org.apache.commons.lang3.ObjectUtils" %>

<html>
<head>
	<title>卡区域消费信息</title>
	<meta name="decorator" content="default"/>
<script type="text/javascript">
	$(document).ready(function() {
		init()
		
		$("#table").width(parseInt("${consumCityLenght}")*150+150+100+50);

	});
	function exportExcel(flag) {
		$("#form").attr("action","${pageContext.request.contextPath}/ClearMermccBook/exportExcel");
		fillCityName()
		$("#form").submit();
	}
	
	function query(flag) {
		$("#form").attr("action","${pageContext.request.contextPath}/ClearMermccBook/page");
		fillCityName()
		$("#form").submit();
	}	

	function fillCityName(){
		var cityName=$("#s2id_consumCity").find('span:eq(0)').html();
		$("#consumCityName").val(cityName);
		var typeName=$("#s2id_consumType").find('span:eq(0)').html();
	
		$("#consumTypeName").val(typeName);
	}
	var toShowDetail = function(consumCity) {
		var startDt=$("#startDt").val();
		var endDt=$("#endDt").val();
		
		/* var consumCityText = $("#s2id_consumCity").find('span:eq(0)').html();
		var consumCityValue;
		
	    $('#consumCity option:contains(' + consumCityText + ')').each(function(){  
	      if ($(this).text() == consumCityText) {  
	    	  consumCityValue=$(this).val();  
	      }  
	    });
	    
	    var consumProvinceText = $("#s2id_consumProvince").find('span:eq(0)').html();
		var consumProvinceValue;
	    $('#consumProvince option:contains(' + consumProvinceText + ')').each(function(){  
	      if ($(this).text() == consumProvinceText) {  
	    	  consumProvinceValue=$(this).val();  
	      }  
	    }); */
		var consumProvinceValue = $("#consumProvince").val();
	    var consumCityValue = $("#consumCity").val();
	    var urlStr = '/ClearMermccBook/getDetailList?consumType=' + consumCity
					+'&random=' + Math.random();
	    if(startDt != null){
	    	urlStr = urlStr + '&startDt=' + startDt;
	    }
	    if(endDt != null){
	    	urlStr = urlStr + '&endDt=' + endDt;
	    }
	    if(consumProvinceValue != null){
	    	urlStr = urlStr + '&consumProvince=' + consumProvinceValue;
	    }
	    if(consumCityValue != null){
	    	urlStr = urlStr + '&consumCity=' + consumCityValue;
	    }
	    
		var laySum = $.layer({
			type : 2,
			title : false,
			zIndex : -1,
			shade : [ 0.2, '#000' ],
			closeBtn : [ 1, true ],
			area : [ '1000px', '480px' ],
			iframe : {
				src : urlStr
			}
		});
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
	    			$("#s2id_consumCity").find('span:eq(0)').html('--请选择--');
	    			if (null != data && data.length != 0) {
	    				
	    				$.each(data, function(i, info) {
	    					$("#consumCity").append(
	    							"<option title='"+info.id+"' value="+info.id+" >"+info.provinceCity + "</option>");	
	    				});
						
	            		<c:if test="${!empty queryModel.consumCity}" >
	            		
		            		var queryAreaCode = '<c:out value="${queryModel.consumCity}"/>';
		            		document.forms[0].consumCity.value=queryAreaCode;
   								
		            		$("#s2id_consumCity").find('span:eq(0)').html('${queryModel.consumCityName}');
		            		
		            		
		          
	            		</c:if>
	    			}
	    		}
	    	})
	};

    function init(){
        var consumProvince=$("#consumProvince").val();
        
        if(consumProvince!=null&&consumProvince!=''){
        	getCityList();        	
        }
    }
	
</script>
</head>
<body>
	<div class="sh_main">
		<div class="shm_contab">
			<div class="poup_right">
				<div class="shmcr_cont01" style="width:auto">
					<div class="sh_title">区域消费类型统计</div>
					<div class="shmc_tab2">
						<form id="form" action="${pageContext.request.contextPath}/ClearMermccBook/getList" method="post">
							<input type="hidden" id="curPage" name="curPage" value="${curPage}" /> 
							<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
							<input type="hidden" id="consumCityName" name="consumCityName" value="${queryModel.consumCityName}" />
							<input type="hidden" id="consumTypeName" name="consumTypeName" value="${queryModel.consumTypeName}" />
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
									<td style="text-align: right;">商户类型:</td>
									<td style="text-align: left;">			
										<select id="consumType" name="consumType" class="inputext_style" value="${queryModel.consumType}">
											<option value="">--请选择--</option>
											<c:forEach items="${consumTypes}" var="consumType" varStatus="allIndex">
												<option value="${consumType.key }" <c:if test="${queryModel.consumType eq consumType.key}">selected="selected"</c:if>>${consumType.value }</option>
											</c:forEach>
										</select>
									</td>
																												
								</tr>
								<tr>									
									
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
										</select>																			
									</td>
										
									<td style="text-align: right;"></td>																									
									<td style="text-align: left;">
										<input type="button" value="查询" class="button" onclick="query()"/>
										<input type="button" value="导出" class="button" onclick="exportExcel()"/>
									
									</td>	
																
								</tr>
							</table>
						</form>
						<table id="table"  border="1" cellspacing="0" cellpadding="0">
							<tr>
								<th width="100px">商户所在区域</th>
								<c:forEach items="${comCity}" var="info" varStatus="index">
									<th colspan="2">${info.value}</th>
								</c:forEach>
								
								<th colspan="2">合计</th>
								
								<th rowspan="2">详情</th>
							</tr>
							<tr>
								<th width="100px">消费类型</th>	
								<c:forEach items="${comCity}" var="info" varStatus="index">
									<th width="50px">笔数</th>
									<th width="100px">金额</th>
								</c:forEach>
								<th style="width:50px">笔数</th>
								<th style="width:100px">金额</th>	
							</tr>
							<c:if test="${queryListLenght ne 0 }">
								<%-- 遍历购卡区域作为每一行 --%>
								<c:forEach items="${consumTypes1}" var="consumType1" varStatus="allIndex">
									<tr class="cow">
										<td>
											${consumType1.value }
										</td>
										<%-- 遍历消费区域作为每一行的每个单元格 --%>
										<c:forEach items="${allMap}" var="allMap" varStatus="listIndex">
											
											<c:if test="${consumType1.key eq allMap.key }">
												
												<c:forEach items="${allMap.value}" var="list" varStatus="dataListIndex">
													
													<c:forEach items="${comCity}" var="comCity" varStatus="allIndex">
														
														<c:if test="${comCity.key eq list.key }">
														
															<c:forEach items="${list.value}" var="dataMap" varStatus="dataMapIndex">
																	
																	<c:if test="${comCity.key eq dataMap.key }">
																		<td >${fns:getIndexString(dataMap.value,":",0)}</td>
																		<td>${fns:getIndexString(dataMap.value,":",1)}</td>
																	</c:if>
																	
															</c:forEach>
														</c:if>
													</c:forEach>
												</c:forEach>
												
											</c:if>
																			
										</c:forEach>
										
										<c:forEach items="${sumDataMap}" var="sumDataMap" varStatus="dataMapIndex">
											
											<c:if test="${sumDataMap.key eq consumType1.key}">
												<td width="50px">${fns:getIndexString(sumDataMap.value,":",0)}</td>
												<td width="100px">${fns:getIndexString(sumDataMap.value,":",1)}</td>
											</c:if>
										 </c:forEach>
										 	
										 <td width="50px">
											 <a href="javascript:toShowDetail('${consumType1.key}')">查询</a>																																									
										 </td>
									</tr>
																													
								</c:forEach>
							</c:if>
	
							<c:if test="${queryListLenght eq 0 }">
								<tr>
									<td colspan="18" style="text-align: center"><span style="color: red">***请查询***</span></td>
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
