<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>卡类管理</title>
	<fmt:setBundle basename="MyResource"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<fmt:message key='StylePath' />style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />common.js"></script>
	<script language="javascript" src="<fmt:message key='JavaScriptPath' />meizzDate.js"></script>
	<script language="JavaScript" src="<fmt:message key='JavaScriptPath' />GetDate.js"></script>
	<script language="javascript">
	
	function saveClick()
	{
		document.forms[0].method.value="createCardType";
		//alert(validateCardTypeForm(document.forms[0]));
		
		return validateCardTypeForm(document.forms[0]);
		
	}
	function backClick()
	{
		document.forms[0].method.value="queryCardType";
	}
	</script>
</head>
<shiro:lacksPermission name="posp:cardtype:add">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:cardtype:add">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				新增卡类
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="cardTypeForm" />
				<html:errors />
				<html:form action="/cardType"   enctype="multipart/form-data">

					<html:hidden property="method" value="createCardType" />

					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">


						<tr>
							<td class="table2_td_title">
								卡标识号:

							</td>
							<td class="table2_td">
								<html:text property="cardId" size="19" maxlength="19" />
								
							</td>
						
							<td class="table2_td_title">
								卡号长度:

							</td>
							<td class="table2_td">
								<html:text property="cardLen" size="19" maxlength="5" />
								
							</td>
						</tr><tr>
							<td class="table2_td_title">
								卡号标识:

							</td>
							<td class="table2_td">
								<html:text property="cardNoId" size="19" maxlength="19" />
								
							</td>
						
							<td class="table2_td_title">
								中文名称:

							</td>
							<td class="table2_td">
								<html:text property="cardName" size="19" maxlength="20" />
								
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								卡类型:
				</td>
							<td class="table2_td">
							<html:select styleId="cardType" property="cardType">


<c:forEach var="model" items="${cardTypeList}">

<option value="<c:out value="${model.id.cardType}"/>"><c:out value="${model.id.typeName}" /></option>
</c:forEach>

</html:select>
								<!--<html:text property="cardType" size="19" maxlength="2" />-->
								
							</td>
						
							<td class="table2_td_title">
								银行类型:

							</td>
							<td class="table2_td">
							<html:select property="bankType">
								<c:forEach var="model" items="${bankTypeList}">
							     <option value="<c:out value="${model.bankType}"/>"><c:out value="${model.typeName}" />
							     </option>
							   </c:forEach>
								</html:select>
								<!--<html:text property="bankType" size="19" maxlength="4" />-->
								
							</td>
						</tr><tr>
							<td class="table2_td_title">
								机构代码:

							</td>
							<td class="table2_td">
								<html:text property="bankCode" size="19" maxlength="11" />
								
							</td>
						
							<td class="table2_td_title">
								自发卡属性:

							</td>
							<td class="table2_td">
								<html:select property="cardMode"><html:option value="">无</html:option><html:option value="01">Master个人普通卡</html:option><html:option value="02">Master个人金卡</html:option><html:option value="03">Master员工普通卡</html:option><html:option value="04">Master员工金卡</html:option><html:option value="05">Master单位普通卡</html:option><html:option value="06">Master单位金卡</html:option><html:option value="07">Master专用卡（普通卡）</html:option><html:option value="11">Visa个人普通卡</html:option><html:option value="12">Visa个人金卡</html:option><html:option value="13">Visa员工普通卡</html:option><html:option value="14">Visa员工金卡</html:option><html:option value="15">Visa单位普通卡</html:option><html:option value="16">Visa单位金卡</html:option></html:select>
							</td>
						</tr>
<tr>
							<td class="table2_td_title">
								身份鉴定:

							</td>
							<td class="table2_td">
								<html:select property="pinMode"><html:option value="0">不输入</html:option><html:option value="1">只输密码</html:option><html:option value="2">只输身份证</html:option><html:option value="3">只输身份证或密码</html:option><html:option value="4">同时输身份证或密码</html:option></html:select>
							</td>
						
							<td class="table2_td_title">
								手工方式:

							</td>
							<td class="table2_td">
								<html:select property="inputMode"><html:option value="0">刷卡</html:option><html:option value="1">手工</html:option></html:select>
							</td>
						</tr><tr>
							<td class="table2_td_title">
								卡标识磁道:

							</td>
							<td class="table2_td">
								<html:select property="cardIdTrack"><html:option value="2">2磁</html:option><html:option value="3">3磁</html:option></html:select>
							</td>
						
							<td class="table2_td_title">
								卡标识起始位置:

							</td>
							<td class="table2_td">
								<html:text property="cardIdOff" size="19" maxlength="2" />
								
							</td>
						</tr><tr>
							<td class="table2_td_title">
								卡号磁道:

							</td>
							<td class="table2_td">
								<html:select property="cardNoTrack"><html:option value="2">2磁</html:option><html:option value="3">3磁</html:option></html:select>
							</td>
						
							<td class="table2_td_title">
								卡号起始位置:

							</td>
							<td class="table2_td">
								<html:text property="cardNoOff" size="19" maxlength="2" />
								
							</td>
						</tr><tr>
							<td class="table2_td_title">
								有效期起始位置:

							</td>
							<td class="table2_td">
								<html:text property="expDateOff" size="19" maxlength="5" />
								
							</td>
						
							<td class="table2_td_title">
								卡号检验规则:

							</td>
							<td class="table2_td">
							<html:select property="chkCardValid"><html:option value="0">标准</html:option><html:option value="1">非标准</html:option></html:select>
							</td>
						</tr><tr>
							<td class="table2_td_title">
								是否本地卡:

							</td>
							<td class="table2_td">
								<html:select property="ifLocal"><html:option value="Y">是</html:option><html:option value="N">否</html:option></html:select>
							</td>
						
							<td class="table2_td_title">
								离线标志:

							</td>
							<td class="table2_td">
								<html:select property="ifOffline"><html:option value="Y">支持</html:option><html:option value="N">不支持</html:option></html:select>
							</td>
						</tr>

						<tr>
							<td align="center" colspan="4" class="table2_btn">
								<input type="image" src="<fmt:message key='CommonImagePath' />btnSave.gif" onclick="return saveClick()">
								&nbsp;
								<input type="image" src="<fmt:message key='CommonImagePath' />btnBack.gif" onclick="return backClick()">


							</td>
						</tr>
					</table>

					<!-- 维护视图状态的隐藏域 -->
					<%@include file="../common/getDisplayParams1.jsp"%>
					<input type="hidden" name="search_sort" value="<c:out value="${param.search_sort}"/>" />
					<!-- 维护视图状态的隐藏域 -->
				</html:form>

			</td>
		</tr>
	</table>
</body>
</shiro:hasPermission>
</html:html>