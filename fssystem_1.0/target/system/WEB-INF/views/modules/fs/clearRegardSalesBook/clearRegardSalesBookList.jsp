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
		$("#form").attr("action","${pageContext.request.contextPath}/ClearRegardSalesBook/exportExcel");
		fillCityName()
		$("#form").submit();
	}
	
	function query(flag) {
		$("#form").attr("action","${pageContext.request.contextPath}/ClearRegardSalesBook/page");
		fillCityName()
		$("#form").submit();
	}	
	function fillCityName(){
		var cityName=$("#s2id_salesCity").find('span:eq(0)').html();
		$("#salesCityName").val(cityName);
		var crdproduct=$("#s2id_crdproduct").find('span:eq(0)').html();
		$("#cardTypeName").val(crdproduct);
	}
	var toShowDetail = function(salesCity) {
		var startDt=$("#startDt").val();
		var endDt=$("#endDt").val();
		var crdproductText = $("#s2id_crdproduct").find('span:eq(0)').html();
		var crdproductValue;
		
	    $('#crdproduct option:contains(' + crdproductText + ')').each(function(){  
	      if ($(this).text() == crdproductText) {  
	    	 crdproductValue=$(this).val();  
	         
	      }  
	    });
	    
	    var salesProvinceText = $("#s2id_salesProvince").find('span:eq(0)').html();
		var salesProvinceValue;
	    $('#salesProvince option:contains(' + salesProvinceText + ')').each(function(){  
	      if ($(this).text() == salesProvinceText) {  
	    	  salesProvinceValue=$(this).val();  
	         
	      }  
	    });
  
		
		
		
		var laySum = $.layer({
			type : 2,
			title : false,
			zIndex : -1,
			shade : [ 0.2, '#000' ],
			closeBtn : [ 1, true ],
			area : [ '1000px', '480px' ],
			iframe : {
				src : '/ClearRegardSalesBook/getDetailList?salesCity=' + salesCity
					    + '&startDt='+startDt+'&crdproduct='+crdproductValue+'&salesProvince='
					    +salesProvinceValue+ '&endDt='+endDt +'&random='
						+ Math.random()
			}
		});
	}	

	
	 var getCityList = function() {
		 
	    	$.ajax({
	    		url : '${pageContext.request.contextPath}/ClearMerStlFinalBook/getAreaListByFid',
	    		type : "post",
	    		data : {
	    			fid : $("#salesProvince").val()
	    		},
	    		dataType : "json",
	    		success : function(data) {
	    			
					$("#salesCity option").remove();
	    			
	    			$("#salesCity").append("<option value=''>--请选择--</option>");	
	    			$("#s2id_salesCity").find('span:eq(0)').html('--请选择--');
	    			if (data.length != 0) {
	    				
	    				$.each(data, function(i, info) {
	    					$("#salesCity").append(
	    							"<option title='"+info.id+"' value="+info.id+" >"+info.provinceCity + "</option>");	
	    				});
	
	            		<c:if test="${!empty query.salesCity}" >
		            		var queryAreaCode = '<c:out value="${query.salesCity}"/>';
		            		//alert("queryAreaCode:"+queryAreaCode);
		            		document.forms[0].salesCity.value=queryAreaCode;
		            		$("#s2id_salesCity").find('span:eq(0)').html('${query.salesCityName}');
	            		</c:if>
	    			}
	    		}
	    	})
	};

    function init(){
        var salesProvince=$("#salesProvince").val();
        
        if(salesProvince!=null&&salesProvince!=''){
        	getCityList();        	
        }
    }
    
</script>
</head>
<body>
	<div class="sh_main">
		<div class="shm_contab">
			<div class="poup_right">
				<div class="shmcr_cont01">
					<div class="sh_title">卡区域消费表</div>
					<div class="shmc_tab2">
						<form id="form" action="${pageContext.request.contextPath}/ClearMerStlBook/getList" method="post">
							<input type="hidden" id="curPage" name="curPage" value="${curPage}" /> 
							<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
							<input type="hidden" id="salesCityName" name="salesCityName" value="${queryModel.salesCityName}" />
							<input type="hidden" id="cardTypeName" name="cardTypeName" value="${queryModel.cardTypeName}" />
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									
									<td style="text-align: right;">起始日期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.startDt}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" name="startDt" id="startDt" />
									</td>
									<td style="text-align: right;">截止日期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.endDt}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" name="endDt" id="endDt" />
									</td>
									<td style="text-align: right;">卡类型:</td>
									<td style="text-align: left;">			
										<select id="crdproduct" name="crdproduct" class="inputext_style" value="${query.crdproduct}">
											<option value="">--请选择--</option>
											<c:forEach var="model" items="${cardType}">
												<option value="${model.key}" <c:if test="${query.crdproduct eq model.key}">selected="selected"</c:if>>
													${model.value}
												</option>
											</c:forEach>
										</select>
									</td>
									
																												
								</tr>
								<tr>									
									
									<td style="text-align: right;">购卡区域：</td>																	
									<td style="text-align: left;" colspan="3">
										<select id="salesProvince" name="salesProvince" class="inputext_style" value="${query.salesProvince}" onchange="getCityList();">
											<option value="">--请选择--</option>
											<c:forEach var="model" items="${provinceList}">
												<option value="${model.id}" <c:if test="${query.salesProvince eq model.id}">selected="selected"</c:if>>
													${model.provinceCity}
												</option>
											</c:forEach>
										</select>																			
										--
										<select id="salesCity" name="salesCity" class="inputext_style" >
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
						<table id="table" border="1" cellspacing="0" cellpadding="0">
							<tr>
								<th width="100px">消费区域</th>
								<c:forEach items="${comCity}" var="info" varStatus="index">
									<th colspan="2">${info.value}</th>
								</c:forEach>
								
								<th colspan="2">合计</th>
								
								<th rowspan="2">详情</th>
							</tr>
							<tr>
								<th width="100px">购卡区域</th>	
								<c:forEach items="${comCity}" var="info" varStatus="index">
									<th width="50px">笔数</th>
									<th width="100px">金额</th>
								</c:forEach>
								<th style="width:50px">笔数</th>
								<th style="width:100px">金额</th>	
							</tr>
							
							<%-- 遍历购卡区域作为每一行 --%>
							<c:forEach items="${salesCity}" var="salesCity1" varStatus="allIndex">
								<tr class="cow">
									<td>
										${salesCity1.value }
									</td>
									<%-- 遍历消费区域作为每一行的每个单元格 --%>
									<c:forEach items="${allMap}" var="allMap" varStatus="listIndex">
										
										<c:if test="${salesCity1.key eq allMap.key }">
											
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
										
										<c:if test="${sumDataMap.key eq salesCity1.key}">
											<td width="50px">${fns:getIndexString(sumDataMap.value,":",0)}</td>
											<td width="150px">${fns:getIndexString(sumDataMap.value,":",1)}</td>
										</c:if>
									 </c:forEach>
									 	
									 <td width="50px">
										 <a href="javascript:toShowDetail('${salesCity1.key}')">查询</a>																																									
									 </td>
								</tr>
																												
							</c:forEach>
							
	
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
