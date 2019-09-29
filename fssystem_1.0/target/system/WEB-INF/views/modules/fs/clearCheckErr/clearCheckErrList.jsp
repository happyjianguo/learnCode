<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../../include/taglib.jsp"%>

<html>
<head>
	<title>差错流水管理</title>
	
	<script type="text/javascript">
	
	
	$(document).ready(function() {
		$('#exportExcel').click(function() {
			$("#form").attr("action","${pageContext.request.contextPath}/ClearCheckErr/exportExcel");
			$("#form").submit();
		});
		$('#query').click(function() {
			var addDT_startdate = $("#startDt").val();
			var addDT_enddate =$("#endDt").val();
			if(addDT_startdate!='' && addDT_enddate!='' && (getDate(addDT_startdate)-getDate(addDT_enddate)>0)){
			    alert("结束日期不能小于开始日期");
			    window.document.forms['form'].elements['startDt'].focus() ;
			    return false;
			} 				
			$("#form").attr("action","${pageContext.request.contextPath}/ClearCheckErr/getList");
			$("#form").submit();
		});		
	});

	var toEdit = function(id) {
		var laySum = $.layer({
			type : 2,
			title : false,
			zIndex : -1,
			shade : [ 0.2, '#000' ],
			closeBtn : [ 1, true ],
			area : [ '1000px', '480px' ],
			iframe : {
				src : '/ClearCheckErr/toEdit?ClearCheckErrId=' + id
					    + '&random='
						+ Math.random()
			}
		});
	}
	var toShow = function(id) {
		var laySum = $.layer({
			type : 2,
			title : false,
			zIndex : -1,
			shade : [ 0.2, '#000' ],
			closeBtn : [ 1, true ],
			area : [ '1000px', '480px' ],
			iframe : {
				src : '/ClearCheckErr/toShow?ClearCheckErrId=' + id
					    + '&random='
						+ Math.random()
			}
		});
	}
	var toAdd = function() {
		var laySum = $.layer({
			type : 2,
			title : false,
			zIndex : -1,
			shade : [ 0.2, '#000' ],
			closeBtn : [ 1, true ],
			area : [ '1000px', '480px' ],
			iframe : {
				src : '/ClearCheckErr/toAdd?random='+ Math.random()
			}
		});
	}
	var toFirstCheck = function(id) {
		var laySum = $.layer({
			type : 2,
			title : false,
			zIndex : -1,
			shade : [ 0.2, '#000' ],
			closeBtn : [ 1, true ],
			area : [ '1000px', '480px' ],
			iframe : {
				src : '/ClearCheckErr/toFirstCheck?ClearCheckErrId=' + id
					    + '&random='
						+ Math.random()
			}
		});
	}
	var toSecondCheck = function(id) {
		var laySum = $.layer({
			type : 2,
			title : false,
			zIndex : -1,
			shade : [ 0.2, '#000' ],
			closeBtn : [ 1, true ],
			area : [ '1000px', '480px' ],
			iframe : {
				src : '/ClearCheckErr/toSecondCheck?ClearCheckErrId=' + id
					    + '&random='
						+ Math.random()
			}
		});
	}
	var invalid = function(id) {
		if(confirm("确认作废差错流水"+id+"?")){
			$.ajax({
				url : '/ClearCheckErr/invalid',
				type : "post",
				data : {
					ClearCheckErrId: id
				},
				dataType : "json",
				success : function(data) {
					if(data.result=='-1'){
						layer.alert(data.resultMsg, 3, function() {
							$("#form", window.document).submit();
						});
					}else{
						layer.alert(data.resultMsg, 1, function() {
							$("#form", window.document).submit();
						});
					}
				}
			})
		}
	};
	
	var resetQuery = function() {
		$("#merNo").val("");
		$("#merName").val("");
		$("#startDt").val(CurentDayTime("-1"));
		$("#endDt").val(CurentDayTime("+1"));
		$("#stlFlag").val("");
		$("#dcFlag").val("");
		$("#genType").val("");
		$("#checkTime").val("");
		$("#operDt1").val("");
		$("#operDt2").val("");
		$("#status").val("");
		
	}
	function CurentDayTime(flag) {
		var now = new Date();
		var year = now.getFullYear(); 	//年
		var month = now.getMonth() + 1; //月
		if(flag!=null&&flag=="-1"){
			month = now.getMonth(); //月
		}else if(flag!=null&&flag=="+1"){
			month = now.getMonth()+2; //月
		}		
		var day = now.getDate(); 		//日
		var hour = now.getHours();		//时
		var minute = now.getMinutes();	//分
		var second = now.getSeconds();	//秒
		var clock = year + "";
		if (month < 10)
			clock += "0";
		clock += month + "";
		if (day < 10)
			clock += "0";
		clock += day + "";
	 	if(hour<10){
	 		clock += "0";
	 	}
		clock += hour + "";
		if(minute<10){
			clock += "0";
		}
		clock += minute + "";
		if(second<10){
			clock += "0";
		}
		clock += second + "";
		return (clock);
	}
	//data转换
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
				<div class="shmcr_cont01">
					<div class="sh_title">差错流水信息</div>
					<div class="shmc_tab2">
						<form id="form" action="${pageContext.request.contextPath}/ClearCheckErr/getList" method="post">
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
									<td style="text-align: right;">交易时间：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.startDt}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMddHHmmss',isShowClear:true,readOnly:true})" name="startDt" id="startDt" />
									</td>
									<td style="text-align: right;">至：</td>																	
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.endDt}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMddHHmmss',isShowClear:true,readOnly:true})" name="endDt" id="endDt" />																			
									</td>																														
								</tr>
								<tr>	
									<td style="text-align: right;">结算标识：</td>
									<td style="text-align: left;">
										<select id="stlFlag" name="stlFlag" class="inputext_style" value="${query.stlFlag}">
											<option value="">--请选择--</option>
											<option value="0" <c:if test="${query.stlFlag eq '0'}">selected="selected"</c:if>>初登记</option>
											<option value="1" <c:if test="${query.stlFlag eq '1'}">selected="selected"</c:if>>需要结算</option>
											<option value="2" <c:if test="${query.stlFlag eq '2'}">selected="selected"</c:if>>无需结算</option>
										</select>									
									</td>								
									<td style="text-align: right;">借贷标识：</td>
									<td style="text-align: left;">
										<select id="dcFlag" name="dcFlag" class="inputext_style" value="${query.dcFlag}">
											<option value="">--请选择--</option>
											<option value="D" <c:if test="${query.dcFlag eq 'D'}">selected="selected"</c:if>>借记</option>
											<option value="C" <c:if test="${query.dcFlag eq 'C'}">selected="selected"</c:if>>贷记</option>
										</select>
									</td>
									<td style="text-align: right;">来源标识：</td>
									<td style="text-align: left;">
										<select id="genType" name="genType" class="inputext_style" value="${query.genType}">
											<option value="">--请选择--</option>
											<option value="0" <c:if test="${query.genType eq '0'}">selected="selected"</c:if>>自动</option>
											<option value="1" <c:if test="${query.genType eq '1'}">selected="selected"</c:if>>手工</option>
										</select>
									</td>
									<td style="text-align: right;">状态标识：</td>
									<td style="text-align: left;">
										<select id="status" name="status" class="inputext_style" value="${query.status}">
											<option value="">--请选择--</option>
											<option value="0" <c:if test="${query.status eq '0'}">selected="selected"</c:if>>初登记</option>
											<option value="1" <c:if test="${query.status eq '1'}">selected="selected"</c:if>>一审完成</option>
											<option value="2" <c:if test="${query.status eq '2'}">selected="selected"</c:if>>二审完成</option>
											<option value="3" <c:if test="${query.status eq '3'}">selected="selected"</c:if>>作废</option>											
										</select>
									</td>								
								</tr>
								<tr>
									<td style="text-align: right;">一审日期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.operDt1}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMddHHmmss',isShowClear:true,readOnly:true})" name="operDt1" id="operDt1" />
									</td>								
									<td style="text-align: right;">二审日期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.operDt2}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMddHHmmss',isShowClear:true,readOnly:true})" name="operDt2" id="operDt2" />
									</td>									
									<td style="text-align: right;">对账日期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.checkTime}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMddHHmmss',isShowClear:true,readOnly:true})" name="checkTime" id="checkTime" />
									</td>																		
									<td style="text-align: right;" colspan="2">
										<input type="submit" value="查询" class="button" id="query"/>
										<input type="button" value="导出" class="button" id="exportExcel" />
										<input type="button" value="清空" class="button" onClick='resetQuery()'/>
										<shiro:hasPermission name="fssystem:clearCheckErr:add">
											<input type="button" value="添加" class="button" onClick='toAdd()'/>
										</shiro:hasPermission>
									</td>									
								</tr>
							</table>
						</form>
						<table width="100%" border="1" cellspacing="0" cellpadding="0">
							<tr>
								<th>序号</th>									
								<th>商户编号</th>
								<th>商户名称</th>
								<th>终端号</th>
								<th>卡号</th>
								<th>交易类型</th>
								<th>交易日期</th>
								<th>交易时间</th>
								<th>卡核心交易金额（元）</th>
								<th>收单交易金额（元）</th>
								<th>交易手续费（元）</th>																
								<th>结算标识</th>								
								<th>调整标识</th>
								<th>借贷标识</th>
								<th>来源标识</th>
								<th>状态标识</th>
								<th>操作</th>
							</tr>
							<c:if test="${ ClearCheckErrList != null  }">
								<c:forEach items="${ClearCheckErrList}" var="info" varStatus="index">
									<tr class="cow">
										<td>${((curPage-1) * pageSize) + index.count }</td>									
										<td>${info.merNo}</td>
										<td>${info.merName}</td>
										<td>${info.termNo}</td>
										<td>${info.cardNo}</td>
										<td>${info.tranTypeDesc}</td>
										<td>${info.tranDate}</td>
										<td>${info.tranTime}</td>
										<td>${info.coretranAmt}</td>
										<td>${info.acqtranAmt}</td>
										<td>${info.fee}</td>										
										<td>
											<c:if test="${info.stlFlag=='0'}">初登记</c:if>	
											<c:if test="${info.stlFlag=='1'}">需要结算</c:if>	
											<c:if test="${info.stlFlag=='2'}">无需结算</c:if>																					
										</td>
										<td>
											<c:if test="${info.adjustFlag=='0'}">初登记</c:if>	
											<c:if test="${info.adjustFlag=='1'}">需要调账</c:if>	
											<c:if test="${info.adjustFlag=='2'}">无需调账</c:if>																					
										</td>										
										<td>
											<c:if test="${info.dcFlag=='D'}">借记</c:if>	
											<c:if test="${info.dcFlag=='C'}">贷记</c:if>	
										</td>
										<td>
											<c:if test="${info.genType=='0'}">系统录入</c:if>	
											<c:if test="${info.genType=='1'}">手工录入</c:if>										
										</td>
										<td>
											<c:if test="${info.status=='0'}">初登记</c:if>	
											<c:if test="${info.status=='1'}">一审完成</c:if>		
											<c:if test="${info.status=='2'}">二审完成</c:if>	
											<c:if test="${info.status=='3'}">作废</c:if>																			
										</td>										
										<td>
											<a href="javascript:toShow('${info.id}')">查看</a>
											<shiro:hasPermission name="fssystem:clearCheckErr:edit">	
												<c:if test="${info.genType=='1' and info.status=='0'}">
													<a href="javascript:toEdit('${info.id}')">编辑</a>											
												</c:if>
											</shiro:hasPermission>
											<shiro:hasPermission name="fssystem:clearCheckErr:yishen">											
												<c:if test="${info.status=='0'}">
													<a href="javascript:toFirstCheck('${info.id}')">一审</a>
												</c:if>	
											</shiro:hasPermission>
											<shiro:hasPermission name="fssystem:clearCheckErr:ershen">
												<c:if test="${info.status=='1' and info.stlFlag!='0' and info.adjustFlag=='1'}">
													<a href="javascript:toSecondCheck('${info.id}')">二审</a>
												</c:if>	
											</shiro:hasPermission>
											<shiro:hasPermission name="fssystem:clearCheckErr:zuofei">
												<c:if test="${info.status=='0'}">
													<a href="javascript:invalid('${info.id}')">作废</a>
												</c:if>		
											</shiro:hasPermission>																																								
										</td>																				
								</c:forEach>
								<c:if test="${fn:length(ClearCheckErrList)==0}">
									<td colspan="18" style="text-align: center"><span style="color: red">***没有相关记录***</span></td>
								</c:if>
								<tr>
									<td colspan="18" class="page">${pageBar}</td>
								</tr>
							</c:if>
							<c:if test="${ ClearCheckErrList == null  }">
								<tr>
									<td colspan="18" style="text-align: center"><span style="color: red">***请查询***</span></td>
								</tr>
							</c:if>							
							<c:if test="${ ClearCheckErrList != null  }">
								<tr>
									<td colspan="18" style="text-align: center">
										<span style="color: red">
										合计
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;										
										卡核心交易金额:${coretranSumAmt}
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;										
										收单交易金额:${acqtranSumAmt}
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										交易金额手续费：${sumFee}
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
