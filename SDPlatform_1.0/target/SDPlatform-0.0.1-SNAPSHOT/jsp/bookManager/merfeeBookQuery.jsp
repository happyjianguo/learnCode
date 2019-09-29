<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>商户手续费应收单列表</title>
	<fmt:setBundle basename="MyResource"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<fmt:message key='StylePath' />style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />common.js"></script>
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />listPage.js"></script>
	<script language="javascript" src="<fmt:message key='JavaScriptPath' />meizzDate.js"></script>
	<script language="JavaScript" src="<fmt:message key='JavaScriptPath' />GetDate.js"></script>
    <script type="text/javascript" src="<fmt:message key='JavaScriptPath' />jquery-1.4.min.js" ></script>
    <script type="text/javascript" src="<fmt:message key='JavaScriptPath' />DatePicker/WdatePicker.js" ></script>
	
	<script language="javascript">
	function init()
	{
		<c:if test="${!empty param._merno}" >
		var _merno = '<c:out value="${param._merno}"/>';
		document.forms[0]._merno.value=_merno;
		</c:if>	
		<c:if test="${!empty param._bookdate}" >
		var _bookdate = '<c:out value="${param._bookdate}"/>';
		document.forms[0]._bookdate.value=_bookdate;
		</c:if>
	}

	function openDetail(id)
	{
		window.showModalDialog("merfeeBook.do?method=queryDetail&read=true&id="+id, "_blank", 'dialogWidth=800px;dialogHeight=440px;scroll=yes;resizable=no;status=no;center=yes;');
	}
	function queryDetail(id)
	{
		document.forms[0].method.value="queryDetail";
		document.forms[0]._traceNo.value=id;
		document.forms[0].submit();
	}
	function queryAll(){
		document.forms[0].method.value="queryAll";
		return true;
	}
	//删除后面空格
  	String.prototype.RTrim   =   function(){   
  		return   this.replace(/(\s*$)/g,"");   
  	} 
	</script>
</head>

<body onload="init()">

	<!--------------以下Table为路径-------->
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30" class="place">
				<img src="<fmt:message key='CommonImagePath' />place_btn.gif" width="12" height="11">
				当前位置：收单系统管理平台 >> 单据管理 >> 商户手续费应收单
			</td>

		</tr>
		<tr>
			<td height="10">
			</td>
		</tr>
	</table>
	<!--------------Table为路径结束-------->


	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				商户手续费应收单列表
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:errors />
				<html:form action="/merfeeBook" method="post">
					<input type="hidden" name="method" value="queryAll" />
					<!----------以下Table为查询form-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="10%">
								商户编号  :
							</td>
							<td class="table2_td" width="40%">
								<input type="text" name="_merno"  maxlength="20"  style="width:135px;" onkeyup="this.value=this.value.replace(/\D/g,'');" />
							</td>
							<td class="table2_td_title" width="10%">
								单据生成日:
							</td>
							<td class="table2_td" width="40%">
							<input name="_bookdate" size="19" maxlength="12" onclick="WdatePicker({dateFmt:'yyyyMMdd'})" class="Wdate" readonly="true"/>
							</td>
						</tr>
						<tr>
							<td colspan="4" class="table2_btn">
								<input name="imageField" type="image" src="<fmt:message key='CommonImagePath' />btnQuery.gif" alt="查询" width="65" height="20" border="0" onclick="return queryAll();">
								&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" onclick="resetClick()"><img src="<fmt:message key='CommonImagePath' />btnClear.gif" alt="清空" width="65" height="20" border="0"></a>
							</td>
						</tr>
					</table>
					<!----------Table为查询form结束-------->
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table3_border">
						<tr>
							<td class="table3_title">
								查询结果
							</td>
						</tr>
					</table>
					<!-- 用Diaplay Tag来显示 -->
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/merfeeBook.do">
						<display:column title="商户编号" style="width:10%;text-align:center" property="merno" headerClass="sortable" sortable="true" />
						<display:column title="手续费应收单状态" style="width:10%;text-align:center" headerClass="sortable" sortable="true" >
							<c:if test="${displayTable.status eq 0}">初登记(待审核)</c:if>
							<c:if test="${displayTable.status eq 1}">已收</c:if>
							<c:if test="${displayTable.status eq 2}">审核通过(待收)</c:if>
							<c:if test="${displayTable.status eq 3}">审核拒绝</c:if>
							<c:if test="${displayTable.status eq 4}">删除</c:if>
						</display:column>
						<display:column title="轧差退货交易笔数" style="width:10%;text-align:center" property="trannum" headerClass="sortable" sortable="true" />
						<display:column title="轧差退货交易金额(分)" style="width:10%;text-align:center" property="tranamt" headerClass="sortable" sortable="true" />
						<display:column title="应收手续费金额 (分)" style="width:10%;text-align:center" property="fee" headerClass="sortable" sortable="true" />
						<display:column title="单据生成日" style="width:6%;text-align:center" property="bookdate" headerClass="sortable" sortable="true" />
						<display:column title="操作" style="width:5%;text-align:center">
						<c:if test="${currentUserData.groupMap.x010902x00=='yes'}"> 
							<a href="javascript:;" onclick="openDetail('${displayTable.id}')" >详细</a>
						</c:if>
						</display:column>
					</display:table>
					<!-- 用Diaplay Tag来显示 -->		
			</td>
		</tr>
	</table>
	<%@include file="../common/getDisplayParams1.jsp"%>
	</html:form>
	<br>
</body>
</html:html>
