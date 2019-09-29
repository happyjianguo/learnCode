<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.*" isELIgnored="false"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
	<title>日志详情</title>
	<fmt:setBundle basename="MyResource"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<fmt:message key='StylePath' />style.css" rel="stylesheet" type="text/css">
		<link href="<fmt:message key='StylePath' />testAjax.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" src="<fmt:message key='JavaScriptPath' />ajaxSignBankIdQuery.js"></script>
	<script language="JavaScript" src="<fmt:message key='JavaScriptPath' />ajaxMccParamQuery.js"></script>
	<script type="text/javascript"  language="JavaScript" src="<fmt:message key='JavaScriptPath' />data.js"></script>
	<script type="text/javascript" language="JavaScript"  src="<fmt:message key='JavaScriptPath' />common.js"></script>
	<script  type="text/javascript" language="javascript" src="<fmt:message key='JavaScriptPath' />meizzDate.js"></script>
	<script  type="text/javascript" language="JavaScript" src="<fmt:message key='JavaScriptPath' />GetDate.js"></script>
	<script language="javascript">
	
	function backClick()
	{
		document.forms[0].method.value="queryAll";
	}
	
   
    
    //删除后面空格
  	String.prototype.RTrim   =   function(){   
  		return   this.replace(/(\s*$)/g,"");   
  	} 
  	
  	 
	</script>
</head>

<body>
	<!--------------以下Table为路径-------->
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30" class="place">
				<img src="<fmt:message key='CommonImagePath' />place_btn.gif" width="12" height="11">    
				当前位置：收单系统管理平台 >> 日志管理 >> 日志详情
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
				日志详情
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="operateLogForm" />
				<html:errors />
				<html:form action="/log">

					<html:hidden property="method" value="saveItem" />
					
					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
							
						<tr>
							<td class="table2_td_title">
								日志ID:
							</td>
							<td class="table2_td">
								${ operateLogForm.logId}
							</td>
							<td class="table2_td_title">
								关键字:
							</td>
							<td class="table2_td">
								${operateLogForm.keyId}
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								类型:
							</td>
							<td class="table2_td">
							<c:if test="${operateLogForm.type eq '1'}" >
								敏感信息操作
							</c:if>	
							</td>
							<td class="table2_td_title">
							</td>
							<td class="table2_td">
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								操作人:
							</td>
							<td class="table2_td">
								${operateLogForm.createOper }
							</td>
							<td class="table2_td_title">
								操作时间:
							</td>
							<td class="table2_td">
								${operateLogForm.createDate }-${operateLogForm.createTime }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								原因:
							</td>
							<td class="table2_td" colspan="3">
								${operateLogForm.reason }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								新数据:
							</td>
							<td class="table2_td">
								${operateLogForm.newdata }
							</td>
							<td class="table2_td_title">
								原数据:
							</td>
							<td class="table2_td">
								${operateLogForm.olddata }
							</td>
						</tr>
						

						<tr>
							<td align="center" colspan="4" class="table2_btn">
								<input type="image" src="<fmt:message key='CommonImagePath' />btnBack.gif" onclick="return backClick()">
							</td>
						</tr>
					</table>
					<INPUT TYPE="hidden" name="mccMark" value="N">
					<INPUT TYPE="hidden" name="divflag" id="divflag" value="N">
					<!-- 维护视图状态的隐藏域 -->
					<%@include file="../common/getDisplayParams1.jsp"%>
					<!-- 维护视图状态的隐藏域 -->
				</html:form>

			</td>
		</tr>
	</table>



</body>

</html:html>



