<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>实名认证信息管理</title>
		<meta name="decorator" content="default" />
	<script type="text/javascript">
		$(document).ready(function() {
			
			$('#exportExcel').click(function() {
				var begainTrueName = $("#begainTrueName").val();
				var endTrueName = $("#endTrueName").val();
				if(begainTrueName!='' && endTrueName!='' && (getDate(begainTrueName)-getDate(endTrueName)>0)){
				    alert("实名截止时间不能小于实名起始时间");
				    return false;
				}
				
				$("#form").attr("action","${pageContext.request.contextPath}/OldCrdinfoMember/exportExcel");
				$("#form").submit();
			});
			
			$('#query').click(function() {
				var begainTrueName = $("#begainTrueName").val();
				var endTrueName = $("#endTrueName").val();
				if(begainTrueName!='' && endTrueName!='' && (getDate(begainTrueName)-getDate(endTrueName)>0)){
				    alert("实名截止时间不能小于实名开始时间");
				    return false;
				}
				
				$("#form").attr("action","${pageContext.request.contextPath}/OldCrdinfoMember/list");
				$("#form").submit();
			});		
		});	
		
		function resetQuery() {
			$("#operator").select().val("");
			$("#memberName").select().val("");
			$("#begainTrueName").val(CurentDayTime("-1",null));
			$("#endTrueName").val(CurentDayTime(null,null));
		}
	
		//删除左右两端的空格
		function trim(str){ 
			return str.replace(/(^\s*)|(\s*$)/g, "");
		}
		
		function CurentDayTime(monthFlag,dateFlag) {
			var now = new Date();
			var year = now.getFullYear(); 	//年
			var month = now.getMonth() + 1; //月
			if(monthFlag!=null&&monthFlag=="-1"){
				month = now.getMonth(); //月
			}else if(monthFlag!=null&&monthFlag=="+1"){
				month = now.getMonth()+2; //月
			}		
			var day = now.getDate(); 		//日
			if(dateFlag!=null&&dateFlag=="-1"){
				day = now.getDate()-1; 
			}else if(dateFlag!=null&&dateFlag=="-7"){
				day = now.getDate()-7; 
			}else if(dateFlag!=null&&dateFlag=="+1"){
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
						<div class="sh_title">
							老福卡项目会员实名信息列表
						</div>
						<div class="shmc_tab2">
							<form id="form" action="${pageContext.request.contextPath}/OldCrdinfoMember/list"
								method="post">
								<input type="hidden" id="curPage" name="curPage" value="${curPage}" />
								<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
								
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td style="text-align: right;">所属口令：</td>
										<td style="text-align: left;">
											<select id="operator" name="operator" class="inputext_style">
												<option value="">--请选择--</option>										
												<c:forEach var="model" items="${operatorList}">
													<option value="${model}" <c:if test="${query.operator eq model}">selected="selected"</c:if>>
														${fn:split(model, ",")[1]}
													</option>
												</c:forEach>
											</select>		
										</td>
										<td style="text-align: right;">所属项目：</td>
										<td style="text-align: left;">
											<select id="memberName" name="memberName" class="inputext_style">
												<option value="">--请选择--</option>										
												<c:forEach var="model" items="${crdmemberList}">
													<option value="${model}" <c:if test="${query.memberName eq model}">selected="selected"</c:if>>
														${model}
													</option>
												</c:forEach>
											</select>
										</td>
										<td style="text-align: right;">实名日期：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style"
												value="${query.begainTrueName}" readonly="readonly"
												onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false,readOnly:true})"
												name="begainTrueName" id="begainTrueName" />-
											<input type="text" class="inputext_style"
												value="${query.endTrueName}" readonly="readonly"
												onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false,readOnly:true})"
												name="endTrueName" id="endTrueName" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right;" colspan="8">
											<input type="button" value="查询" class="button" id="query" />
											<input type="button" value="导出" class="button" id="exportExcel" />
											<input type="button" value="清空" class="button" onclick='resetQuery()' />
										</td>
									</tr>
								</table>
							</form>
							<table width="100%" border="1" cellspacing="0" cellpadding="0">
								<tr>
									<th>序号</th>
									<th>订单号</th>
									<th>所属项目</th>
									<th>卡BIN</th>
									<th>福卡卡号</th>
									<th>姓名</th>
									<th>手机号</th>
									<th>身份证号</th>
									<th>地址</th>
									<th>邮箱信息</th>
								</tr>
								<c:if test="${oldCrdinfoList != null}">
									<c:forEach items="${oldCrdinfoList}" var="info"
										varStatus="index">
										<tr class="cow">
											<td>
												${((curPage-1) * pageSize) + index.count }
											</td>
											<td>
												${info.indentNumber}
											</td>
											<td>
												${info.memberName}
											</td>
											<td>
												${fn:substring(info.pan, 0, 9)}
											</td>
											<td>
												${info.pan}
											</td>
											<td>
												${info.custName}
											</td>											
											<td>
												${info.cellPhone}
											</td>
											<td>
												${info.idNum}
											</td>
											<td>
												${info.address}
											</td>
											<td>
												${info.mailBox}
											</td>
										</tr>	
									</c:forEach>
									<tr class="cow">
										<td colspan="18" align="center">
											<font color="red">${query.begainTrueName}—${query.endTrueName}&emsp;&emsp;
											<c:if test="${query.operator != null and query.operator ne ''}">
												操作员：${fn:split(query.operator, ",")[1]}&emsp;&emsp;
											</c:if>
											<c:if test="${query.memberName != null and query.memberName ne ''}">
												所属会员：${query.memberName}&emsp;&emsp;
											</c:if>
											筛选结果：共${totalPage}页&emsp;&emsp;总计${counts}人次</font>
										</td>
									</tr>
									<c:if test="${fn:length(oldCrdinfoList)==0}">
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
								<c:if test="${ oldCrdinfoList == null  }">
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
