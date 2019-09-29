
<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%String path = request.getContextPath();%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>详情页面</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style_a.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/cssem.css" />
	<script type="text/javascript" src="<%=path%>/js/checkform.js"></script>
	<script type="text/javascript" src="<%=path%>/js/dwr.js"></script>
	<script type="text/javascript" src="<%=path%>/js/eposcc.js"></script>
	<script type="text/javascript" src="<%=path%>/js/textselect.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
</head>
<script language="javascript">
	//window.history.forward(1);
	//var flag = false;
	
	function opencardsend(id){
		$("#btSH").attr("disabled","true");
		$("#btCX").attr("disabled","true");
		window.location.href="<%=path %>/opencard.do?method=OpenCard&id="+id+"&msgtype=0014";
	}
	function opencardcancel(id){
		$("#btSH").attr("disabled","true");
		$("#btCX").attr("disabled","true");
		window.location.href="<%=path %>/opencard.do?method=OpenCard&id="+id+"&msgtype=0038";
	}
	
</script>
<center>
	<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
		
			<table border="0" cellpadding="0" cellspacing="0" width="100%" height="99%" >
				<tr>
					<td align="center" valign="top">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
				    <tr>
						<td width="28" height="10"></td>
					</tr>
					<tr>
						<td  align="left"   width="28" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">
						
						</td>
						<td   height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;当前位置：批量管理 &gt; &gt; 批量开卡管理 &gt; &gt;操作</td>
						<td   width="7" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) "> </td>
					</tr>
					 <tr>
						<td width="28" height="5" colspan="3"></td>
					</tr>
					</table>
						<table border="0" cellpadding="0" cellspacing="0" width="80%"  class = "box1">
							<tr>
								<td width="20%" align="right" class="box1">
									编号：
								</td>
								<td width="50%" align="left" class="box2">
								
									<bean:write name="opencardBean" property="id"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td width="20%" align="right" class="box1">
									消息类型：
								</td>
								<td width="50%" align="left" class="box2">
								
									<bean:write name="opencardBean" property="txncode"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td  width="20%" align="right" class="box1">
									时间：
								</td>
								<td width="50%" align="left" class="box2">									
									<bean:write name="opencardBean" property="time"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td  width="20%" align="right" class="box1">
									账期：
								</td>
								<td width="50%" align="left" class="box2">									
									<bean:write name="opencardBean" property="acct_period"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td  width="20%" align="right" class="box1">
									流水号：
								</td>
								<td width="50%" align="left" class="box2">									
									<bean:write name="opencardBean" property="stan"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td  width="20%" align="right" class="box1">
									开始卡号：
								</td>
								<td width="50%" align="left" class="box2">									
									<bean:write name="opencardBean" property="pan_start"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td  align="right" class="box1">
							结束卡号：
								</td>
								<td align="left" class="box2">
									<bean:write name="opencardBean" property="pan_end"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									张数：
								</td>
								<td align="left" class="box2">									
									<bean:write name="opencardBean" property="pan_count"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									清算金额：									
								</td>
								<td align="left" class="box2">									
									<bean:write name="opencardBean" property="amt_each_crd"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>	
							<tr>
								<td  align="right" class="box1">
									清算币种：									
								</td>
								<td align="left" class="box2">									
									<bean:write name="opencardBean" property="currbill"/>
								</td>
								<td align="left" class="box2" width="40%">
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									交易汇率：									
								</td>
								<td align="left" class="box2">									
									<bean:write name="opencardBean" property="rateset"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									交易金额：									
								</td>
								<td align="left" class="box2">									
									<bean:write name="opencardBean" property="amttxn"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									交易币种：									
								</td>
								<td align="left" class="box2">									
									<bean:write name="opencardBean" property="curtxn"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>	
							<tr>
								<td  align="right" class="box1">
									卡产品：									
								</td>
								<td align="left" class="box2">									
									<bean:write name="opencardBean" property="crdproduct"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>	
							<tr>
								<td  width="20%" align="right" class="box1">
									父订单：
								</td>
								<td width="50%" align="left" class="box2">									
									<bean:write name="opencardBean" property="father_order"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td  align="right" class="box1">
							子订单：
								</td>
								<td align="left" class="box2">
									<bean:write name="opencardBean" property="children_order"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									支付方式：
								</td>
								<td align="left" class="box2">									
									<bean:write name="opencardBean" property="pay_type_desc"/>
								</td>
								<td align="left" class="box2" width="40%">									
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									支付方式详细信息：									
								</td>
								<td align="left" class="box2">									
									<bean:write name="opencardBean" property="pay_desc"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>
							
							<tr>
								<td  align="right" class="box1">
									购卡单位名称：
								</td>
								<td align="left" class="box2">									
									<bean:write name="opencardBean" property="payer_name"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									售卡点：									
								</td>
								<td align="left" class="box2">									
									<bean:write name="opencardBean" property="sales_point"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>	
							<tr>
								<td  align="right" class="box1">
									地区：									
								</td>
								<td align="left" class="box2">
									
									<bean:write name="opencardBean" property="area"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>		
							<tr>
								<td  align="right" class="box1">
									操作员：
									
								</td>
								<td align="left" class="box2">									
									<bean:write name="opencardBean" property="operator"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>	
							<tr>
								<td  align="right" class="box1">
									备注（支票号）：
								</td>
								<td align="left" class="box2">
									
								<bean:write name="opencardBean" property="summary"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>						
							<tr>
								<td  align="right" class="box1">
									商户号：
								</td>
								<td align="left" class="box2">
									
									<bean:write name="opencardBean" property="mrcht_id"/>
								</td>
							</tr>
							<tr>
								<td  align="right" class="box1">
									处理状态：
								</td>
								<td align="left" class="box2">	
									<logic:equal name="opencardBean" property="batch_stat" value="00">已处理</logic:equal>
									<logic:equal name="opencardBean" property="batch_stat" value="01">未处理</logic:equal>
									<logic:equal name="opencardBean" property="batch_stat" value="02">已退卡</logic:equal>				
									<logic:equal name="opencardBean" property="batch_stat" value="03">已撤销</logic:equal>								
								</td>
							</tr>
							<tr>
								<td  align="right" class="box1">
									审核人员：
								</td>
								<td align="left" class="box2">									
									<bean:write name="opencardBean" property="descr_u"/>
								</td>
							</tr>
							<tr>
								<td  align="right" class="box1">
									审核时间：
								</td>
								<td align="left" class="box2">									
									<bean:write name="opencardBean" property="descr_t"/>
								</td>
							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="80%"  class = "box1">
							<tr>
								<!-- 成功的非实时开卡 可以审核与撤销 -->
								<logic:notEqual name="opencardBean" property="isopen_flag" value="1">
									<logic:equal name="opencardBean" property="batch_stat" value="01">
										<logic:equal name="opencardBean" property="is_enablement" value="1">
											<td height="23" align="center" class="box1">									
												&nbsp;&nbsp;&nbsp;
												<input id="btSH" class="button" type="button"  value="审核" onClick="return opencardsend(<bean:write name="opencardBean" property="id"/>)">
											</td>
											<td height="23" align="center" class="box1">									
												&nbsp;&nbsp;&nbsp;
												<input id="btCX" class="button" type="button"  value="撤销" onClick="return opencardcancel(<bean:write name="opencardBean" property="id"/>)">
											</td>
										</logic:equal>
									</logic:equal>
								</logic:notEqual>
								<%-- 
								<logic:notEqual name="opencardBean" property="isopen_flag" value="1">
									<logic:equal name="opencardBean" property="batch_stat" value="01">
										<td height="23" align="center" class="box1">									
											&nbsp;&nbsp;&nbsp;
											<input id="btSH" class="button" type="button"  value="审核" onClick="return opencardsend(<bean:write name="opencardBean" property="id"/>)">
										</td>
										<td height="23" align="center" class="box1">									
											&nbsp;&nbsp;&nbsp;
											<input id="btCX" class="button" type="button"  value="撤销" onClick="return opencardcancel(<bean:write name="opencardBean" property="id"/>)">
										</td>
									</logic:equal>
								</logic:notEqual> 
								--%>
								<td height="23" align="center" class="box1">									
									&nbsp;&nbsp;&nbsp;
									<input class="button" type="button"  value="关闭" onClick="javascript:window.close()">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>		
	</body>
</center>
</html:html>
