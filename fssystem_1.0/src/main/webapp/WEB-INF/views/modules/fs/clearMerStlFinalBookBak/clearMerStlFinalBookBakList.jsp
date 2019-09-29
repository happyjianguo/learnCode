<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>结算终表合并信息</title>
	
	<meta name="decorator" content="default"/>
<script type="text/javascript">
	$(document).ready(function() {
			
	});
	function exportExcel(flag) {
		//alert("/ClearMerStlFinalBookBak/exportExcel?flagOnline="+flag)
		$("#form").attr("action","${pageContext.request.contextPath}/ClearMerStlFinalBookBak/exportExcel?flagOnline="+flag);
		$("#form").submit();
	}
	function exportExcelYL(flag) {
		$("#form").attr("action","${pageContext.request.contextPath}/ClearMerStlFinalBookBak/exportExcelYL?flagOnline="+flag);
		$("#form").submit();
	}
	
	function query(flag){
		$("#form").attr("action","${pageContext.request.contextPath}/ClearMerStlFinalBookBak/getList?flagOnline="+flag);
		if($("#stlDate").val()>$("#stlDate1").val()){
			alert("结算日期结算日期开始日期必须大于终止日期！");
			document.getElementById("stlDate1").click();
		}else{
			$("#form").submit();
		}
	}
	function batchPrint() {
		var settlementPerson = $("#settlementPerson").val();
		var url = '${pageContext.request.contextPath}/ClearMerStlFinalBookBak/batchPrint?merNo='+$("#merNo").val()+"&merName="+$("#merName").val()+
		"&stlDate="+$("#stlDate").val()+"&payoutDate="+$("#payoutDate").val()+"&payoutJunl="+$("#payoutJunl").val()+
		"&seqNo="+$("#seqNo").val()+"&payoutStatus="+$("#payoutStatus").val()+ '&random='+ Math.random()+"&stlDate1="+$("#stlDate1").val()+
		"&bjFlag=" + $("#bjFlag").val() + "&flagOnline=0";
		if(null != settlementPerson) {
			url = url + "&settlementPerson=" + settlementPerson;
		}
		var iWidth = 1046; //弹出窗口的宽度;
		var iHeight = 600; //弹出窗口的高度;
		var iTop = (window.screen.availHeight - 30 - iHeight) / 2; //获得窗口的垂直位置;
		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; //获得窗口的水平位置;
		var winSize = 'height='
				+ iHeight
				+ ',width='
				+ iWidth
				+ ',top='
				+ iTop
				+ ',left='
				+ iLeft
				+ ',toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no';
		window.open(url, "", winSize);	
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
				src : '/ClearMerStlFinalBookBak/toShow?ClearMerStlFinalBookId=' + id
					    + '&random='
						+ Math.random()
			}
		});
	}
	
	var resetQuery = function() {
		$("#merNo").val("");
		$("#merName").val("");
		$("#stlDate").val(CurentDayTime("0"));
		$("#stlDate1").val(CurentDayTime("0"));
		$("#payoutDate").val("");
		$("#payoutJunl").val("");
		$("#seqNo").val("");
		$("#settlementPerson").val("");
		
		$("#payoutStatus").select().val("");
		$("#bjFlag").select().val("");
		$("#provinceCode").select().val("");
		$("#cityCode").html("<option value=''>--请选择--</option>");
		$("#cityCode").select().val("");
		$("#stlNeedFlag").select().val("");
		$("#exelusiveCardFlag").select().val("");
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
		}else if(flag!=null&&flag=="0"){
			
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
    var shenhe =function (type, online) { 
   		var checkstr="";
   		var url="";
        $("input:checkbox[name=crdproductCB]:checked").each(function(i){
        if(i!=0) {    
          //将所选的各项记录的id形成字符串，并以“，”分割
          checkstr+=",";    
        }    
         checkstr += $(this).val();    
        }); 
        //alert("checkstr:"+checkstr);
		if(checkstr==""){
			alert("请至少选中一项，再点击按钮");
			return -1;
		}        	
        url='${pageContext.request.contextPath}/ClearMerStlFinalBookBak/shenhe';
		//alert("url:"+url);		
		$.ajax({
			url : url,
			type : "post",
			data : {
				idListStr: checkstr,
				flag:type
			},
			dataType : "text",
			success : function(data) {
				alert(data);
				query(online);
			}
		});		
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
					<div class="sh_title">结算终表合并</div>
					<div class="shmc_tab2">
						<form id="form" action="${pageContext.request.contextPath}/ClearMerStlFinalBookBak/getList" method="post">
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
									<td style="text-align: right;">结算日期：</td>
									<td style="text-align: left;" colspan="3">
										<input type="text" width="10" class="inputext_style" value="${query.stlDate}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" id="stlDate" name="stlDate" />
										--
										<input type="text" class="inputext_style" value="${query.stlDate1}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" id="stlDate1" name="stlDate1" />
									</td>																														
								</tr>
								<tr>									
									<td style="text-align: right;">打款流水</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.payoutJunl}" name="payoutJunl" id="payoutJunl" maxlength="8"/>									
									</td>
									<td style="text-align: right;">凭证号</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.seqNo}" name="seqNo" id="seqNo" maxlength="20"/>																		
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
									<td style="text-align: right;">结算员：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.settlementPerson}" name="settlementPerson" id="settlementPerson" maxlength="100"/>
									</td>	
								</tr>
								<tr>																	
									<td style="text-align: right;" colspan="8">
										<input type="button" value="查询" class="button" onClick="query('<c:out value="${query.flagOnline }"/>')"/>										
										<input type="button" value="导出" class="button" onClick="exportExcel('<c:out value="${query.flagOnline }"/>')"/>
										<input type="button" value="导出银联" class="button" onClick="exportExcelYL('<c:out value="${query.flagOnline }"/>')"/>
										<input type="button" value="打印" class="button" onClick="batchPrint()" />												
										<input type="button" value="清空" class="button" onClick='resetQuery()'/>
										
										<shiro:hasPermission name="fssystem:ClearMerStlFinalBookBak:shenhe">
											<c:if test="${query.payoutStatus=='0'}">
												<input type="button" value="审核通过" class="button" onClick="shenhe('0', '${query.flagOnline}')"/>	
											</c:if>
										</shiro:hasPermission>
										<shiro:hasPermission name="fssystem:ClearMerStlFinalBookBak:dakuan">
											<c:if test="${query.payoutStatus=='1'}">
												<input type="button" value="打款成功" class="button" onClick="shenhe('1', '${query.flagOnline}')"/>	
											</c:if>
										</shiro:hasPermission>
										<shiro:hasPermission name="fssystem:ClearMerStlFinalBookBak:zuofei">
											<c:if test="${query.payoutStatus=='2'}">
												<input type="button" value="作废" class="button" onClick="shenhe('2', '${query.flagOnline}')"/>	
											</c:if>		
										</shiro:hasPermission>																																					
									</td>	
								</tr>
								
							</table>
						</form>
						<table width="100%" border="1" cellspacing="0" cellpadding="0">
							<tr>
								<c:if test="${query.payoutStatus=='0' or query.payoutStatus=='1' or query.payoutStatus=='2'}">								
									<th>
										<input type="checkbox" name="chk_all" onclick="selectAll();" />								
									</th>	
								</c:if>								
								<th>序号</th>									
								<th>商户编号</th>
								<th>商户名称</th>
								<th>省份</th>
								<th>城市</th>	
								<th>北京外省</th>
								<th>是否是专属卡</th>	
								<th>卡类型</th>
								<th>是否需要结算</th>																															
								<th>所属帐期</th>
								<th>金额（元）</th>
								<th>笔数</th>
								<th>手续费（元）</th>																
								<th>净额（元）</th>																
								<th>实际结算金额（元）</th>								
								<th>结算账号</th>
								<th>结算银行</th>
								<th>开户名称</th>
								<th>联行号</th>
								<th>银行编号</th>
								<th>是否同城</th>
								<th>结算日期</th>
								<th>打款状态</th>							
							</tr>
							<c:if test="${ ClearMerStlFinalBookList != null  }">
								<c:forEach items="${ClearMerStlFinalBookList}" var="info" varStatus="index">
									<tr class="cow">
										<c:if test="${query.payoutStatus=='0' or query.payoutStatus=='1' or query.payoutStatus=='2'}">								
											<td>
												<input type="checkbox" name="crdproductCB" value="${info.id}" />
											</td>									
										</c:if>
										<td>${((curPage-1) * pageSize) + index.count }</td>	
										<td>${info.merNo}</td>
										<td>${info.merName}</td>
										<td>${info.provinceName}</td>
										<td>${info.cityName}</td>
										<td>
											<c:if test="${info.bjFlag=='0'}">外省</c:if>	
											<c:if test="${info.bjFlag=='1'}">北京</c:if>											
										</td>
										<td>
											<c:if test="${info.exelusiveCardFlag=='0'}">否</c:if>	
											<c:if test="${info.exelusiveCardFlag=='1'}">是</c:if>											
										</td>
										<td>
											${info.cardTypeName}
																					
										</td>
										<td>
											<c:if test="${info.stlNeedFlag=='0'}">不需要</c:if>	
											<c:if test="${info.stlNeedFlag=='1'}">需要</c:if>											
										</td>
										<td>${info.startStlDate}-${info.endStlDate}</td>
										<td>${info.consumAmt}</td>										
										<td>${info.consumNum}</td>
										<td>${info.fee}</td>
										<td>${info.netAmt}</td>
										<td>${info.tranAmt}</td>
										<td>${info.accNo}</td>
										<td>${info.accBankName}</td>										
										<td>${info.payoutJunl}</td>	
										<td>${info.bankUnion}</td>
										<td>${info.bankCode}</td>
										<td>
											<c:if test="${info.beijingFlag=='0'}">否</c:if>	
											<c:if test="${info.beijingFlag=='1'}">是</c:if>											
										</td>
										<td>${info.stlDate}</td>
										<td>
											<c:if test="${info.payoutStatus=='0'}">未打款</c:if>	
											<c:if test="${info.payoutStatus=='1'}">审核通过</c:if>
											<c:if test="${info.payoutStatus=='2'}">审核拒绝</c:if>
											<c:if test="${info.payoutStatus=='3'}">打款成功</c:if>											
											<c:if test="${info.payoutStatus=='8'}">作废</c:if>											
										</td>																		
								</c:forEach>
								<c:if test="${fn:length(ClearMerStlFinalBookList)==0}">
									<td colspan="24" style="text-align: center"><span style="color: red">***没有相关记录***</span></td>
								</c:if>
								<tr>
									<td colspan="24" class="page">${pageBar}</td>
								</tr>
							</c:if>
							<c:if test="${ ClearMerStlFinalBookList == null  }">
								<tr>
									<td colspan="24" style="text-align: center"><span style="color: red">***请查询***</span></td>
								</tr>
							</c:if>							
							<c:if test="${ ClearMerStlFinalBookList != null  }">
								<tr>
									<td colspan="24" style="text-align: center">
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
