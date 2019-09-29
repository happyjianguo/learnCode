<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*,org.apache.commons.lang3.StringUtils,org.apache.commons.lang3.ObjectUtils" %>

<html>
<head>
	<title>网点售卡统计</title>
	<meta name="decorator" content="default"/>
<script type="text/javascript">
	function  DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2006-12-18格式    
	    var  aDate,  oDate1,  oDate2,  iDays;    
	    aDate  =  sDate1.split("-");    
	    oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0]);    //转换为12-18-2006格式    
	    aDate  =  sDate2.split("-");    
	    oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0]);    
	    iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24);    //把相差的毫秒数转换为天数   
	    return  iDays + 1;   
	}
	function exportExcel() {
		$("#form").attr("action","${pageContext.request.contextPath}/StatisticsSellCard/exportExcel");
		if($("#startStlDate").val()=='' || $("#endStlDate").val() ==''){
			alert("日期不能为空");
			return false;
		}
		if($("#startStlDate").val()>$("#endStlDate").val()){
			alert("结算日期开始日期必须大于终止日期！");
			document.getElementById("startStlDate").click();
		}else{
			var endStlDate = $("#endStlDate").val();
			var endd = endStlDate.substring(0,4)+"-"+endStlDate.substring(4,6)+"-"+endStlDate.substring(6,8);
			var startStlDate = $("#startStlDate").val();
			var startt = startStlDate.substring(0,4)+"-"+startStlDate.substring(4,6)+"-"+startStlDate.substring(6,8);
			var num = DateDiff(endd,startt);
			if(num > 90){
				alert("请导出90天以内的数据");
				return false;
			}else{
				$("#form").submit();
			}
		}
		//$("#form").submit();
	}
	
	function query() {
		$("#form").attr("action","${pageContext.request.contextPath}/StatisticsSellCard/getList");
		if($("#startStlDate").val()>$("#endStlDate").val()){
			alert("结算日期开始日期必须大于终止日期！");
			document.getElementById("startStlDate").click();
		}else{
			$("#form").submit();
		}
	}	
	var resetQuery = function() {
		$("#adminName").val("");
		$("#adminRealname").val("");
		$("#stlDate").val(CurentDayTime("-1"));
		$("#pointid").val("");
		$("#endStlDate").val("");
		$("#startStlDate").val("");
		
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
					<div class="sh_title">网点售卡统计</div>
					<div class="shmc_tab2">
						<form id="form" action="${pageContext.request.contextPath}/StatisticsSellCard/getList" method="post">
							<input type="hidden" id="curPage" name="curPage" value="${curPage}" /> 
							<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td style="text-align: right;">售卡员用户名：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.adminName}" name="adminName" id="adminName" maxlength="15"/>
									</td>
									<td style="text-align: right;">售卡员姓名：</td>
									<td style="text-align: left;">
 										<input type="text" class="inputext_style" value="${query.adminRealname}" name="adminRealname" id="adminRealname" maxlength="15"/>
									</td>
									<td style="text-align: right;"></td>
									<td style="text-align: left;">
									</td>
									<td style="text-align: right;">起始日期：</td>
									<td style="text-align: left;">
										<input type="text" width="10" class="inputext_style" readonly="readonly"
<%-- 										<input type="text" width="10" class="inputext_style" value="${query.startStlDate}" readonly="readonly" --%>
										<input type="text" width="10" class="inputext_style" value="${query.startStlDate}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" id="startStlDate" name="startStlDate" />
									</td>
								</tr>
								<tr>
									<td style="text-align: right;">分公司、售卡网点：</td>																	
									<td style="text-align: left;" colspan="3">
										<select id="pointid" name="pointid" style="width:550px;border: 1px solid #cacaca;" value="${query.pointid}">
											<option value="">--请选择--</option>										
											<c:forEach var="model" items="${consumpCategoryList}">
												<option value="${model.pointId}" <c:if test="${query.pointid eq model.pointId}">selected="selected"</c:if>>
													${model.showname}
												</option>
											</c:forEach>
										</select>																			
									</td>
									<td style="text-align: right;"></td>
									<td style="text-align: left;">								
									</td>																										
									<td style="text-align: right;">
										截止日期：
									</td>
									<td style="text-align: left;">
										<!-- <input type="text" class="inputext_style" readonly="readonly" -->
										<input type="text" class="inputext_style" value="${query.endStlDate}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" id="endStlDate" name="endStlDate" />														
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
										<input type="button" value="查询" class="button" onclick="query()"/>
										<input type="button" value="导出" class="button" onclick="exportExcel()"/>
<%-- 										<input type="button" value="查询" class="button" onclick="query('<c:out value="${query.flagOnline }"/>')"/>
										<input type="button" value="导出" class="button" onclick="exportExcel('<c:out value="${query.flagOnline }"/>')"/> --%>
										<input type="button" value="清空" class="button" onClick='resetQuery()'/>
									</td>									
								</tr>
							</table>
						</form>
						<table width="100%" border="1" cellspacing="0" cellpadding="0">
							<tr>
								<th>序号</th>									
								<th>售卡员用户名</th>
								<th>售卡员姓名</th>
								<th>售卡网点编号</th>
								<th>售卡网点名称</th>
								<th>售卡日期</th>
								<th>售卡总金额</th>
								<!-- <th>备付金余额</th> -->
							</tr>
							<c:if test="${ StatisticsSellCardList != null  }">
								<c:forEach items="${StatisticsSellCardList}" var="info" varStatus="index">
									<tr class="cow">
										<td>${((curPage-1) * pageSize) + index.count }</td>	
										<td>${info.adminName}</td>
										<td>${info.adminRealname}</td>
										<td>${info.pointid}</td>
										<td>${info.pointName}</td>
										<td>${info.outcardverifytime}</td>
										<td>${info.cardtotalprice}</td>										
										<%-- <td>${info.provisions}</td> --%>
								</c:forEach>
								<c:if test="${fn:length(StatisticsSellCardList)==0}">
									<td colspan="18" style="text-align: center"><span style="color: red">***没有相关记录***</span></td>
								</c:if>
								<tr>
									<td colspan="18" class="page">${pageBar}</td>
								</tr>
							</c:if>
							<c:if test="${ StatisticsSellCardList == null  }">
								<tr>
									<td colspan="18" style="text-align: center"><span style="color: red">***请查询***</span></td>
								</tr>
							</c:if>							
							<c:if test="${ StatisticsSellCardList != null  }">
								<tr>
									<td colspan="18" style="text-align: center">
										<span style="color: red">
										合计
										&nbsp;&nbsp;&nbsp;&nbsp;售卡总金额：${sale_amt}
										<%-- &nbsp;&nbsp;&nbsp;&nbsp;备付金剩余总金额：${tran_amt} --%>								
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
