<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>终端信息管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- 样式所需导入的链接 -->
<%@ include file="../inner/css.jsp"%>
<!-- 样式所需导入的链接 -->
<script type="text/javascript">
	var edit = function(merchantId,termCode) {
		var laySum = $.layer({
			type : 2,
			title : false,
			zIndex : -1,
			shade : [ 0.2, '#000' ],
			closeBtn : [ 1, true ],
			area : [ '800px', '420px' ],
			iframe : {
				src : '${ctx }/terminalSDMT/edit?merchantId='+ merchantId +"&termCode=" + termCode + '&random=' + Math.random()
			}
		});
	}
	var show = function(merchantId,termCode) {
		var laySum = $.layer({
			type : 2,
			title : false,
			zIndex : -1,
			shade : [ 0.2, '#000' ],
			closeBtn : [ 1, true ],
			area : [ '800px', '500px' ],
			iframe : {
				src : '${ctx }/terminalSDMT/show?merchantId='+ merchantId +"&termCode=" + termCode + '&random=' + Math.random()
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
			area : [ '800px', '420px' ],
			iframe : {
				src : '${ctx }/terminalSDMT/toAdd?random='+ Math.random()
			}
		});
	}
	/* var boo = true;
	var synchronous = function(merchantId,termCode,synchronousType) {
		if(boo){
	        boo = false;
	        $.ajax({
				url : '${ctx }/terminalSDMT/synchronous',
				type : "post",
				async: false,
				data : {
					merchantId: merchantId,
					termCode: termCode,
					synchronousType: synchronousType
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
					boo = true;
				},
				error: function(e) { 
					boo = true;
				} 
			})
	    } 
	}; */
 	var synchronous = function(merchantId,termCode,synchronousType) {
		$("#"+synchronousType+termCode).attr("disabled","disabled");
		$.ajax({
			url : '${ctx }/terminalSDMT/synchronous',
			type : "post",
			data : {
				merchantId: merchantId,
				termCode: termCode,
				synchronousType: synchronousType
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
				$("#"+synchronousType+termCode).removeAttr("disabled");
			},
			error: function(e) { 
				$("#"+synchronousType+termCode).removeAttr("disabled");
			}
		})
	}; 
	var showMerchant = function(merchantId) {
		var laySum = $.layer({
			type : 2,
			title : false,
			zIndex : -1,
			shade : [ 0.2, '#000' ],
			closeBtn : [ 1, true ],
			area : [ '800px', '480px' ],
			iframe : {
				src : '${ctx }/merchantSDMT/show?merchantId=' + merchantId
					    + '&random='
						+ Math.random()
			}
		});
	};
	var resetQuery = function() {
		$("#merchantId").val("");
		$("#termCode").val("");
		$("#addDate").val("");
		$("#termAddress").val("");
		$("#sdFlag").val("");
		$("#xFlag").val("");
		$("#xBakFlag").val("");
		$("#yufuFlag").val("");
	};
	var btnImports = function(){
		$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
			bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
	};
</script>
</head>
<shiro:lacksPermission name="SDMTPlatform:terminal:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="SDMTPlatform:terminal:view">
<body>
	<!-- <div id="importBox" class="hide"> -->
	<div id="importBox"  style='display:none;'>
		<form id="importForm" action="${ctx }/terminalSDMT/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:10px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx }/terminalSDMT/template">下载模板</a>
			<h2>日期时间相关字段请使用yyyymmdd格式，终端类型为common，输入框请使用文本格式，输入数字前加’</h2>
		</form>
	</div>
	<hr style="height: 1px; border:none; border-top:1px dashed #CCCCCC;" /> 
	<div class="sh_main">
		<div class="shm_contab">
			<div class="poup_right">
				<div class="shmcr_cont01">
					<div class="sh_title">终端信息</div>
					<div class="shmc_tab2">
						<form id="form" action="${ctx }/terminalSDMT/getList" method="post">
							<input type="hidden" id="curPage" name="curPage" value="${curPage}" /> <input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td style="text-align: right;">商户编号：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.merchantId}" name="merchantId" id="merchantId" maxlength="15"/>
									</td>
									<td style="text-align: right;">终端编号：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.termCode}" name="termCode" id="termCode" maxlength="8"/>
									</td>
									<td style="text-align: right;">添加时间：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.addDate}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" name="addDate" id="addDate" maxlength="8"/>
									</td>
									<td style="text-align: right;">终端所在位置：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.termAddress}" name="termAddress" id="termAddress" maxlength="10"/>
									</td>																														
								</tr>
								<tr>								
									<td style="text-align: right;">收单系统同步标志：</td>
									<td style="text-align: left;">
										<select id="sdFlag" name="sdFlag" class="inputext_style" value="${query.sdFlag}">
											<option value="">--请选择--</option>
											<option value="0" <c:if test="${query.sdFlag eq '0'}">selected="selected"</c:if>>未同步</option>
											<option value="1" <c:if test="${query.sdFlag eq '1'}">selected="selected"</c:if>>同步成功</option>
											<option value="2" <c:if test="${query.sdFlag eq '2'}">selected="selected"</c:if>>同步失败</option>
										</select>
									</td>
									<td style="text-align: right;">X平台同步标志：</td>
									<td style="text-align: left;">
										<select id="xFlag" name="xFlag" class="inputext_style" value="${query.xFlag}">
											<option value="">--请选择--</option>
											<option value="0" <c:if test="${query.xFlag eq '0'}">selected="selected"</c:if>>未同步</option>
											<option value="1" <c:if test="${query.xFlag eq '1'}">selected="selected"</c:if>>同步成功</option>
											<option value="2" <c:if test="${query.xFlag eq '2'}">selected="selected"</c:if>>同步失败</option>
										</select>
									</td>
									<td style="text-align: right;">X平台备库同步标志：</td>
									<td style="text-align: left;">
										<select id="xBakFlag" name="xBakFlag" class="inputext_style" value="${query.xBakFlag}">
											<option value="">--请选择--</option>
											<option value="0" <c:if test="${query.xBakFlag eq '0'}">selected="selected"</c:if>>未同步</option>
											<option value="1" <c:if test="${query.xBakFlag eq '1'}">selected="selected"</c:if>>同步成功</option>
											<option value="2" <c:if test="${query.xBakFlag eq '2'}">selected="selected"</c:if>>同步失败</option>
										</select>
									</td>									
									<td style="text-align: right;">老福卡同步标志：</td>
									<td style="text-align: left;">
										<select id="yufuFlag" name="yufuFlag" class="inputext_style" value="${query.yufuFlag}">
											<option value="">--请选择--</option>
											<option value="0" <c:if test="${query.yufuFlag eq '0'}">selected="selected"</c:if>>未同步</option>
											<option value="1" <c:if test="${query.yufuFlag eq '1'}">selected="selected"</c:if>>同步成功</option>
											<option value="2" <c:if test="${query.yufuFlag eq '2'}">selected="selected"</c:if>>同步失败</option>
										</select>
									</td>
								</tr>
								<tr>
									<td style="text-align: center;" colspan="8">
										<input type="submit" value="查询" class="button" />
										<input type="button" value="清空" class="button" onClick='resetQuery()'/>
										<shiro:hasPermission name="SDMTPlatform:terminal:add">																			
											<input type="button" value="添加" class="button" onClick='toAdd()'/>
											<input type="button" value="导入" class="button" id="btnImport" onClick='btnImports()'/>
										</shiro:hasPermission>
									</td>									
								</tr>
							</table>
						</form>
						<%-- <sys:message content="${message}"/> --%>
						<input type="hidden" value="${message}" id="callbackmessage"/>
						<table width="100%" border="1" cellspacing="0" cellpadding="0">
							<tr>
								<th>序号</th>
								<th>商户编号</th>
								<th>终端编号</th>
								<th>终端设备型号</th>
								<th>终端使用的电话号码</th>
								<th>费率(单位：%)</th>							
								<th>增加时间</th>
								<th>操作</th>
								<th>同步收单系统</th>
								<th>同步X平台</th>
								<th>同步老福卡系统</th>
							</tr>
							<c:if test="${ terminalSDMTList != null  }">
								<c:forEach items="${terminalSDMTList}" var="info" varStatus="index">
									<tr class="cow">
										<td>${((curPage-1) * pageSize) + index.count }</td>
										<td>
											<a href="javascript:showMerchant('${info.merchantId}')">${info.merchantId}</a>
										</td>
										<td>${info.termCode}</td>
										<td>${info.edcType}</td>
										<td>${info.termTel}</td>
										<td>${info.xTimezone}</td>
										<td>${info.addDate}</td>
										<td>
											<c:if test="${info.edcType!='APP'}">
												<c:if test="${!(info.sdFlag=='1' and info.xFlag=='1' and info.xBakFlag=='1' and info.yufuFlag=='1')}">	
													<shiro:hasPermission name="SDMTPlatform:terminal:synchronous">											
														<a href="javascript:edit('${info.merchantId}','${info.termCode}')">编辑</a>
													</shiro:hasPermission>
												</c:if>	
												<c:if test="${(info.sdFlag=='1' and info.xFlag=='1' and info.xBakFlag=='1' and info.yufuFlag=='1')}">											
													<a href="javascript:show('${info.merchantId}','${info.termCode}')">查看</a>
												</c:if>	
											</c:if>	
											<c:if test="${info.edcType=='APP'}">
												<c:if test="${!(info.sdFlag=='1' and info.xFlag=='1' and info.xBakFlag=='1')}">	
													<shiro:hasPermission name="SDMTPlatform:terminal:synchronous">											
														<a href="javascript:edit('${info.merchantId}','${info.termCode}')">编辑</a>
													</shiro:hasPermission>
												</c:if>	
												<c:if test="${(info.sdFlag=='1' and info.xFlag=='1' and info.xBakFlag=='1')}">											
													<a href="javascript:show('${info.merchantId}','${info.termCode}')">查看</a>
												</c:if>	
											</c:if>										
										</td>
										<td>
											<c:if test="${info.sdFlag=='1'}">
												已同步
											</c:if>
											<c:if test="${info.sdFlag!='1'}">
												<c:if test="${info.sdFlag=='0'}">
													未同步
												</c:if>
												<c:if test="${info.sdFlag=='2'}">
													同步失败
												</c:if>			
												<shiro:hasPermission name="SDMTPlatform:terminal:synchronous">																					
													<%-- <a href="javascript:synchronous('${info.merchantId}','${info.termCode}','SD')">同步</a> --%>
													<button type="button" id="SD${info.termCode}" onclick="synchronous('${info.merchantId}','${info.termCode}','SD')">同步</button>
													
												</shiro:hasPermission>
											</c:if>																				
										</td>
										<td>
											<c:if test="${info.xFlag=='1'}">
												已同步
											</c:if>
											<c:if test="${info.xFlag!='1'}">
												<c:if test="${info.xFlag=='0'}">
													未同步
												</c:if>
												<c:if test="${info.xFlag=='2'}">
													同步失败
												</c:if>			
												<shiro:hasPermission name="SDMTPlatform:terminal:synchronous">									
													<%-- <a href="javascript:synchronous('${info.merchantId}','${info.termCode}','X')">同步</a> --%>
													<button type="button" id="X${info.termCode}" onclick="synchronous('${info.merchantId}','${info.termCode}','X')">同步</button>
												</shiro:hasPermission>
											</c:if>																				
										</td>										
										<td>
											<c:if test="${info.edcType!='APP'}">
												<c:if test="${info.yufuFlag=='1'}">
													已同步
												</c:if>
												<c:if test="${info.yufuFlag!='1'}">
													<c:if test="${info.yufuFlag=='0'}">
														未同步
													</c:if>
													<c:if test="${info.yufuFlag=='2'}">
														同步失败
													</c:if>
													<shiro:hasPermission name="SDMTPlatform:terminal:synchronous">										
														<%-- <a href="javascript:synchronous('${info.merchantId}','${info.termCode}','YUFU')">同步</a> --%>
														<button type="button" id="YUFU${info.termCode}" onclick="synchronous('${info.merchantId}','${info.termCode}','YUFU')">同步</button>
													</shiro:hasPermission>
												</c:if>
											</c:if>	
											<c:if test="${info.edcType=='APP'}">
												——
											</c:if>																			
										</td>																				
								</c:forEach>
								<c:if test="${fn:length(terminalSDMTList)==0}">
									<td colspan="14" style="text-align: center"><span style="color: red">***没有相关记录***</span></td>
								</c:if>
								<tr>
									<td colspan="14" class="page">${pageBar}</td>
								</tr>
							</c:if>
							<c:if test="${ terminalSDMTList == null  }">
								<tr>
									<td colspan="14" style="text-align: center"><span style="color: red">***请查询***</span></td>
								</tr>
							</c:if>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="sh_footer"></div>
</body>
</shiro:hasPermission>
<script type="text/javascript">
	function isNull(data){ 
		return (data == "" || data == undefined || data == null); 
	}
	var callbackmessage = document.getElementById("callbackmessage").value;
	if(!isNull(callbackmessage)){
		alert(callbackmessage);
	}
</script>
</html>
