<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>修改商户补充信息管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- 样式所需导入的链接 -->
<%@ include file="../inner/css.jsp"%>
<!-- 样式所需导入的链接 -->
<script type="text/javascript">
	var edit = function(merchantId) {
		var laySum = $.layer({
			type : 2,
			title : false,
			zIndex : -1,
			shade : [ 0.2, '#000' ],
			closeBtn : [ 1, true ],
			area : [ '800px', '480px' ],
			iframe : {
				src : '${ctx }/merchantChangeSDMT/edit?merchantId=' + merchantId
					    + '&random='
						+ Math.random()
			}
		});
	}
	/* var show = function(merchantId) {
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
	} */
	/* var synchronous = function(merchantId,synchronousType) {
		$.ajax({
			url : '${ctx }/merchantSDMT/synchronous',
			type : "post",
			data : {
				merchantId : merchantId,
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
			}
		})
	}; */
	var resetQuery = function() {
		$("#merchantId").val("");
		$("#mrchtName").val("");
		$("#addDate").val("");
		$("#addDateEnd").val("");
		$("#isTerminalAddFlag").val("");
	};
</script>
</head>
<shiro:lacksPermission name="SDMTPlatform:merchantChangeSDMT:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="SDMTPlatform:merchantChangeSDMT:view">
<body>
	<hr style="height: 1px; border:none; border-top:1px dashed #CCCCCC;" /> 
	<div class="sh_main">
		<div class="shm_contab">
			<div class="poup_right">
				<div class="shmcr_cont01">
					<div class="sh_title">商户信息</div>
					<div class="shmc_tab2">
						<form id="form" action="${ctx }/merchantChangeSDMT/getList" method="post">
							<input type="hidden" id="curPage" name="curPage" value="${curPage}" /> <input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td style="text-align: right;">商户编号：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.merchantId}" onkeyup="this.value=this.value.replace(/\D/g,'')" name="merchantId" id="merchantId" maxlength="15"/>
									</td>
									<td style="text-align: right;">商户名称：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.mrchtName}" name="mrchtName" id="mrchtName" maxlength="8"/>
									</td>
									<td style="text-align: right;">添加时间：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.addDate}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" name="addDate" id="addDate" />
									</td>	
									<td style="text-align: right;">:</td>								
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.addDateEnd}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" name="addDateEnd" id="addDateEnd" />											
									
									</td>
								</tr>
								<tr>
									<td style="text-align: right;">是否通过终端同步添加商户：</td>
									<td style="text-align: left;">
										<select id="isTerminalAddFlag" name="isTerminalAddFlag" class="inputext_style" value="${query.isTerminalAddFlag}">
											<option value="">--请选择--</option>
											<option value="1" <c:if test="${query.isTerminalAddFlag eq '1'}">selected="selected"</c:if>>是</option>
										</select>
									</td>
									<td style="text-align: right;"></td>
									<td style="text-align: left;"></td>
									<td style="text-align: right;"></td>
									<td style="text-align: left;"></td>
									<td style="text-align: right;" colspan="2">
										<input type="submit" value="查询" class="button" />
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
								<th>增加时间</th>
								<th>操作</th>
							</tr>
							<c:if test="${ merchantSDMTList != null  }">
								<c:forEach items="${merchantSDMTList}" var="info" varStatus="index">
									<tr class="cow">
										<td>${((curPage-1) * pageSize) + index.count }</td>
										<td>${info.merchantId}</td>
										<td>${info.mrchtName}</td>
										<td>${info.addDate}</td>
										<td>
											<a href="javascript:edit('${info.merchantId}')">编辑</a>
											<%-- <shiro:hasPermission name="SDMTPlatform:merchantChangeSDMT:edit">
												<a href="javascript:edit('${info.merchantId}')">编辑</a>
											</shiro:hasPermission> --%>
										</td>
								</c:forEach>
								<c:if test="${fn:length(merchantSDMTList)==0}">
									<td colspan="13" style="text-align: center"><span style="color: red">***没有相关记录***</span></td>
								</c:if>
								<tr>
									<td colspan="13" class="page">${pageBar}</td>
								</tr>
							</c:if>
							<c:if test="${ merchantSDMTList == null  }">
								<tr>
									<td colspan="13" style="text-align: center"><span style="color: red">***请查询***</span></td>
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
</html>
