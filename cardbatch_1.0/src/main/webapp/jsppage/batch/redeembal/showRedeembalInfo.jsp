
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
	window.history.forward(1);
	var flag = false;
	function redeembalsend(id){
		$("#btSH").attr("disabled","true");
		$("#btCX").attr("disabled","true");
		window.location.href="<%=path %>/redeembal.do?method=Redeembal&id="+id+"&msgtype=0052";
	}
	function redeembalcancel(id){
		$("#btSH").attr("disabled","true");
		$("#btCX").attr("disabled","true");
		window.location.href="<%=path %>/redeembal.do?method=Redeembal&id="+id+"&msgtype=0053";
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
						<td   height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;当前位置：批量管理 &gt; &gt; 批量赎回管理 &gt; &gt;操作</td>
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
								
									<bean:write name="redeembalBean" property="id"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td width="20%" align="right" class="box1">
									消息类型：
								</td>
								<td width="50%" align="left" class="box2">
									<bean:write name="redeembalBean" property="txncode"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td  width="20%" align="right" class="box1">
									申请日期：
								</td>
								<td width="50%" align="left" class="box2">									
									<bean:write name="redeembalBean" property="txtime"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td  width="20%" align="right" class="box1">
									账期：
								</td>
								<td width="50%" align="left" class="box2">									
									<bean:write name="redeembalBean" property="acct_period"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td  width="20%" align="right" class="box1">
									流水号：
								</td>
								<td width="50%" align="left" class="box2">									
									<bean:write name="redeembalBean" property="stan"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td  width="20%" align="right" class="box1">
									赎回卡号：
								</td>
								<td width="50%" align="left" class="box2">									
									<bean:write name="redeembalBean" property="pan"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td align="right" class="box1">
									赎回金额：
								</td>
								<td align="left" class="box2">
									<bean:write name="redeembalBean" property="amt"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									清算币种：									
								</td>
								<td align="left" class="box2">									
									<bean:write name="redeembalBean" property="currbill"/>
								</td>
								<td align="left" class="box2" width="40%">
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									交易汇率：									
								</td>
								<td align="left" class="box2">									
									<bean:write name="redeembalBean" property="rateset"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									交易金额：									
								</td>
								<td align="left" class="box2">									
									<bean:write name="redeembalBean" property="amttxn"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									交易币种：									
								</td>
								<td align="left" class="box2">									
									<bean:write name="redeembalBean" property="curtxn"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>	
							<tr>
								<td  align="right" class="box1">
									卡产品：									
								</td>
								<td align="left" class="box2">									
									<bean:write name="redeembalBean" property="crdproduct"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>							
							<tr>
								<td  align="right" class="box1">
									父订单：
								</td>
								<td align="left" class="box2">									
									<bean:write name="redeembalBean" property="father_order"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									子订单：									
								</td>
								<td align="left" class="box2">									
									<bean:write name="redeembalBean" property="children_order"/>
								</td>
								<td align="left" class="box2" width="40%">
								</td>	
							</tr>	
							
							<tr>
								<td  align="right" class="box1">
									售卡点：
								</td>
								<td align="left" class="box2">
									<bean:write name="redeembalBean" property="sales_point"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									地区：
								</td>
								<td align="left" class="box2">									
									<bean:write name="redeembalBean" property="area"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									证件类型：									
								</td>
								<td align="left" class="box2">									
									<bean:write name="redeembalBean" property="id_type"/>
								</td>
								<td align="left" class="box2" width="40%">
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									证件号码：									
								</td>
								<td align="left" class="box2">
									<bean:write name="redeembalBean" property="id_number"/>
								</td>
								<td align="left" class="box2" width="40%"></td>	
							</tr>		
							<tr>
								<td  align="right" class="box1">
									手机号:
								</td>
								<td align="left" class="box2">
									<bean:write name="redeembalBean" property="cell_phone"/>
								</td>
								<td align="left" class="box2" width="40%"></td>	
							</tr>						
							<tr>
								<td  align="right" class="box1">
									电话：
								</td>
								<td align="left" class="box2">
									<bean:write name="redeembalBean" property="phone"/>
								</td>
								<td align="left" class="box2" width="40%"></td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									赎回类型：
								</td>
								<td align="left" class="box2">									
									<logic:equal name="redeembalBean" property="rb_type" value="1">实名赎回</logic:equal>
									<logic:equal name="redeembalBean" property="rb_type" value="2">卡余额赎回</logic:equal> 
									<logic:equal name="redeembalBean" property="rb_type" value="3">合并赎回</logic:equal>
									<logic:equal name="redeembalBean" property="rb_type" value="4">利息赎回</logic:equal>
									<logic:equal name="redeembalBean" property="rb_type" value="5">购卡返积分</logic:equal>
									<logic:equal name="redeembalBean" property="rb_type" value="6">黄金赎回</logic:equal>
									<logic:equal name="redeembalBean" property="rb_type" value="7">基金赎回</logic:equal>
									<logic:equal name="redeembalBean" property="rb_type" value="8">基金利息赎回</logic:equal>
									<logic:equal name="redeembalBean" property="rb_type" value="9">购物返积分赎回</logic:equal>
									<logic:equal name="redeembalBean" property="rb_type" value="10">联名卡送积分赎回</logic:equal>
									
								</td>
								<td align="left" class="box2" width="40%"></td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									银行名称：
								</td>
								<td align="left" class="box2">									
									<bean:write name="redeembalBean" property="bank_name"/>
								</td>
								<td align="left" class="box2" width="40%"></td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									支行名称：
								</td>
								<td align="left" class="box2">									
									<bean:write name="redeembalBean" property="branch_name"/>
								</td>
								<td align="left" class="box2" width="40%"></td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									银行卡号：
								</td>
								<td align="left" class="box2">									
									<bean:write name="redeembalBean" property="bank_pan"/>
								</td>
								<td align="left" class="box2" width="40%"></td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									银行开户姓名：
								</td>
								<td align="left" class="box2">									
									<bean:write name="redeembalBean" property="card_acceptor_name"/>
								</td>
								<td align="left" class="box2" width="40%"></td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									银行卡持卡人身份证号：
								</td>
								<td align="left" class="box2">									
									<bean:write name="redeembalBean" property="card_acceptor_id"/>
								</td>
								<td align="left" class="box2" width="40%"></td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									利息金额：
								</td>
								<td align="left" class="box2">									
									<bean:write name="redeembalBean" property="interest"/>
								</td>
								<td align="left" class="box2" width="40%"></td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									手续费：
								</td>
								<td align="left" class="box2">									
									<bean:write name="redeembalBean" property="fee"/>
								</td>
								<td align="left" class="box2" width="40%"></td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									是否到账：
								</td>
								<td align="left" class="box2">									
									<bean:write name="redeembalBean" property="ispaid"/>
								</td>
								<td align="left" class="box2" width="40%"></td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									备注：
								</td>
								<td align="left" class="box2">									
									<bean:write name="redeembalBean" property="summary"/>
								</td>
								<td align="left" class="box2" width="40%"></td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									操作员：
								</td>
								<td align="left" class="box2">									
									<bean:write name="redeembalBean" property="operator"/>
								</td>
								<td align="left" class="box2" width="40%"></td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									商户号：
								</td>
								<td align="left" class="box2">									
									<bean:write name="redeembalBean" property="mrcht_id"/>
								</td>
								<td align="left" class="box2" width="40%"></td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									订单状态：
								</td>
								<td align="left" class="box2">									
									<logic:equal name="redeembalBean" property="batch_stat" value="00">已处理</logic:equal>
									<logic:equal name="redeembalBean" property="batch_stat" value="01">未处理</logic:equal> 
									<logic:equal name="redeembalBean" property="batch_stat" value="03">已撤销</logic:equal>
								</td>
								<td align="left" class="box2" width="40%"></td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									描述：
								</td>
								<td align="left" class="box2">									
									<bean:write name="redeembalBean" property="descr"/>
								</td>
								<td align="left" class="box2" width="40%"></td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									审核人员：
								</td>
								<td align="left" class="box2">									
									<bean:write name="redeembalBean" property="descr_u"/>
								</td>
							</tr>
							<tr>
								<td  align="right" class="box1">
									审核时间：
								</td>
								<td align="left" class="box2">									
									<bean:write name="redeembalBean" property="descr_t"/>
								</td>
							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="80%"  class = "box1">
							<tr>
								<!-- 非现金赎回可以审核与撤销 -->
								<logic:notEqual name="redeembalBean" property="ispaid" value="1">
									<logic:equal name="redeembalBean" property="batch_stat" value="01">
										<td height="23" align="center" class="box1">									
											&nbsp;&nbsp;&nbsp;
											<input id="btSH" class="button" type="button"  value="赎回" onClick="return redeembalsend(<bean:write name="redeembalBean" property="id"/>)">
										</td>
										<td height="23" align="center" class="box1">									
											&nbsp;&nbsp;&nbsp;
											<input id="btCX" class="button" type="button"  value="赎回撤销" onClick="return redeembalcancel(<bean:write name="redeembalBean" property="id"/>)">
										</td>
									</logic:equal>
								</logic:notEqual>
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
