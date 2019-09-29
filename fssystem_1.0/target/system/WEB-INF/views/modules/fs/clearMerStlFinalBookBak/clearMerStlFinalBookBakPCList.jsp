<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>结算终表合计信息</title>
	<meta name="decorator" content="default"/>
<script type="text/javascript">
$(document).ready(function() {
		$('#exportExcel').click(function() {
			$("#form").attr("action","${pageContext.request.contextPath}/ClearMerStlFinalBookBak/exportExcelPC");
			$("#form").submit();
		});		
	});
	function query(){
		$("#form").attr("action","${pageContext.request.contextPath}/ClearMerStlFinalBookBak/getListPC");
		
		if($("#stlDate").val()>$("#stlDate1").val()){
			alert("结算日期结算日期开始日期必须大于终止日期！");
			document.getElementById("stlDate1").click();
		}else{
			$("#form").submit();
		}	
	}
	var resetQuery = function() {
		$("#stlDate").val(CurentDayTime("0"));
		$("#stlDate1").val(CurentDayTime("0"));
		$("#payoutDate").val("");
		//$("#payoutStatus").val("");
		//$("#bjFlag").val("");
		//$("#stlNeedFlag").val("");		
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
		}else if(flag!=null&&flag=="0")	{
			day = now.getDate(); 
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
    var flag=true;
    function selectAll(){
 	   if(flag==true){
 		   $("input:checkbox[name=crdproductCB]").attr("checked","true");//全选
		   flag=false; 
	   }else{
		   $("input:checkbox[name=crdproductCB]").removeAttr("checked");//取消全选
		   flag=true; 
	   }  
    } 
    var getCityList = function() {
    	$.ajax({
    		url : '${pageContext.request.contextPath}/ClearMerStlFinalBookBak/getAreaListByFid',
    		type : "post",
    		data : {
    			fid : $("#provinceCode").val()
    		},
    		dataType : "json",
    		success : function(data) {
				$("#cityCode option").remove();
    			$("#cityCode").append("<option value=''>--请选择--</option>");		
    			if (data.length != 0) {
    				$.each(data, function(i, info) {
    					$("#cityCode").append(
    							"<option title='"+info.id+"' value="+info.id+" >"+info.provinceCity + "</option>");	
    				});

            		<c:if test="${!empty query.cityCode}" >
	            		var queryAreaCode = '<c:out value="${query.cityCode}"/>';
	            		//alert("queryAreaCode:"+queryAreaCode);
	            		document.forms[0].cityCode.value=queryAreaCode;
            		</c:if>
    			}
    		}
    	})
    };	

    function init(){
        var provinceCode=$("#provinceCode").val();        
        if(provinceCode!=null&&provinceCode!=''){
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
					<div class="sh_title">结算终表合计信息</div>
					<div class="shmc_tab2">
						<form id="form" action="${pageContext.request.contextPath}/ClearMerStlFinalBookBak/getListPC" method="post">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td style="text-align: right;">结算日期：</td>
									<td style="text-align: left;" colspan="3">
										<input type="text" class="inputext_style" value="${query.stlDate}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false,readOnly:true})" name="stlDate" id="stlDate" />
										--
										<input type="text" class="inputext_style" value="${query.stlDate1}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false,readOnly:true})" name="stlDate1" id="stlDate1" />
									</td>																																							
									<td style="text-align: right;">打款状态</td>
									<td style="text-align: left;">			
										<select id="payoutStatus" name="payoutStatus" class="inputext_style" value="${query.payoutStatus}">
											<option value="">--请选择--</option>
											<option value="0" <c:if test="${query.payoutStatus eq '0'}">selected="selected"</c:if>>未打款</option>
											<option value="1" <c:if test="${query.payoutStatus eq '1'}">selected="selected"</c:if>>审核通过</option>
											<option value="2" <c:if test="${query.payoutStatus eq '2'}">selected="selected"</c:if>>审核拒绝</option>
											<option value="3" <c:if test="${query.payoutStatus eq '3'}">selected="selected"</c:if>>打款成功</option>
											<option value="8" <c:if test="${query.payoutStatus eq '8'}">selected="selected"</c:if>>作废</option>
											
										</select>															
									</td>
									<td style="text-align: right;">北京外省</td>
									<td style="text-align: left;">			
										<select id="bjFlag" name="bjFlag" class="inputext_style" value="${query.bjFlag}">
											<option value="">--请选择--</option>
											<option value="0" <c:if test="${query.bjFlag eq '0'}">selected="selected"</c:if>>外省</option>
											<option value="1" <c:if test="${query.bjFlag eq '1'}">selected="selected"</c:if>>北京</option>
										</select>															
									</td>
								</tr>
								<tr>
									<td style="text-align: right;">省份</td>																	
									<td style="text-align: left;">
										<select id="provinceCode" name="provinceCode" class="inputext_style" value="${query.provinceCode}" onchange="getCityList();">
											<option value="">--请选择--</option>										
											<c:forEach var="model" items="${provinceList}">
												<option value="${model.id}" <c:if test="${query.provinceCode eq model.id}">selected="selected"</c:if>>
													${model.provinceCity}
												</option>
											</c:forEach>
										</select>																			
									</td>
									<td style="text-align: right;">城市</td>																	
									<td style="text-align: left;">
										<select id="cityCode" name="cityCode" class="inputext_style" >
											<option value="">--请选择--</option>										
										</select>																			
									</td>	
									
									<td style="text-align: right;">是否需要结算</td>
									<td style="text-align: left;">			
										<select id="stlNeedFlag" name="stlNeedFlag" class="inputext_style" value="${query.stlNeedFlag}">
											<option value="">--请选择--</option>
											<option value="0" <c:if test="${query.stlNeedFlag eq '0'}">selected="selected"</c:if>>无需结算</option>
											<option value="1" <c:if test="${query.stlNeedFlag eq '1'}">selected="selected"</c:if>>需要结算</option>
										</select>															
									</td>
									<td style="text-align: right;">是否是专属卡</td>
									<td style="text-align: left;">			
										<select id="exelusiveCardFlag" name="exelusiveCardFlag" class="inputext_style" value="${query.exelusiveCardFlag}">
											<option value="">--请选择--</option>
											<option value="0" <c:if test="${query.exelusiveCardFlag eq '0'}">selected="selected"</c:if>>否</option>
											<option value="1" <c:if test="${query.exelusiveCardFlag eq '1'}">selected="selected"</c:if>>是</option>
										</select>															
									</td>
								</tr>	
								<tr>	
									<td style="text-align: right;">打款日期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.payoutDate}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" name="payoutDate" id="payoutDate" />
									</td>
									<td style="text-align: right;">商户分类：</td>
									<td style="text-align: left;">
										<select id="flagOnline" name="flagOnline" class="inputext_style" value="${query.flagOnline}">
											<option value="">--请选择--</option>
											<option value="1" <c:if test="${query.flagOnline eq '1'}">selected="selected"</c:if>>线上商户</option>
											<option value="0" <c:if test="${query.flagOnline eq '0'}">selected="selected"</c:if>>线下商户</option>
										</select>	
									</td>
								</tr>
								<tr>																	
									<td style="text-align: right;" colspan="8">
										<input type="button" value="查询" class="button" onClick="query()"/>										
										<input type="button" value="导出" class="button" id="exportExcel" />
										<input type="button" value="清空" class="button" onClick='resetQuery()'/>																																				
									</td>	
								</tr>
							</table>
						</form>
						<table width="100%" border="1" cellspacing="0" cellpadding="0">
							<tr>							
								<th>序号</th>									
								<th>省份</th>
								<th>城市</th>	
								<th>金额（元）</th>
								<th>笔数</th>
								<th>手续费（元）</th>																
								<th>净额（元）</th>																
								<th>实际结算金额（元）</th>														
							</tr>
							<c:if test="${ ClearMerStlFinalBookList != null  }">
								<c:forEach items="${ClearMerStlFinalBookList}" var="info" varStatus="index">
									<tr class="cow">
										<td>${index.count }</td>	
										<td>${info.provinceName}</td>
										<td>${info.cityName}</td>
										<td>${info.consumAmt}</td>										
										<td>${info.consumNum}</td>
										<td>${info.fee}</td>
										<td>${info.netAmt}</td>
										<td>${info.tranAmt}</td>																	
								</c:forEach>
								<c:if test="${fn:length(ClearMerStlFinalBookList)==0}">
									<td colspan="22" style="text-align: center"><span style="color: red">***没有相关记录***</span></td>
								</c:if>
							</c:if>
							<c:if test="${ ClearMerStlFinalBookList == null  }">
								<tr>
									<td colspan="22" style="text-align: center"><span style="color: red">***请查询***</span></td>
								</tr>
							</c:if>							
							<c:if test="${ ClearMerStlFinalBookList != null  }">
								<tr>
									<td colspan="22" style="text-align: center">
										<span style="color: red">
										合计
										&nbsp;&nbsp;&nbsp;&nbsp;金额：${consum_amt}
										&nbsp;&nbsp;&nbsp;&nbsp;笔数:${consum_num}
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
	<script type="text/javascript">
		init();
	</script>	
</body>
</html>
