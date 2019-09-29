<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/ngp-pageTag.tld" prefix="util"%>
<%
	String path = request.getContextPath();
	String sumAmt=request.getAttribute("sumAmt")==null?"":request.getAttribute("sumAmt").toString();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style_a.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/cssem.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/new_look.css" />
	<script src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
</head>
<script language="javascript">
	function query(){
		var userForm = document.all("cardForm");
		var pan_start = document.getElementsByName("pan_start")[0].value;
		var pan_end = document.getElementsByName("pan_end")[0].value;
		
		if(pan_start!='' && pan_end==''){
			if(pan_start.length<15){
				alert("开始卡号不能小于15位");
				document.getElementsByName("pan_start")[0].focus() ;
			    return false;
			}else if(pan_start.length==15){
				pan_start=pan_start+'0';
			}
		}else if(pan_start=='' && pan_end!=''){
			if(pan_end.length<15){
				alert("结束卡号不能小于15位");
				document.getElementsByName("pan_end")[0].focus() ;
			    return false;
			}else if(pan_end.length==15){
				pan_end=pan_end+'9';
			}
		}else if(pan_start!='' && pan_end!=''){
			if(pan_start.length<15){
				alert("开始卡号不能小于15位");
				document.getElementsByName("pan_start")[0].focus() ;
			    return false;
			}
			if(pan_end.length<15){
				alert("结束卡号不能小于15位");
				document.getElementsByName("pan_end")[0].focus() ;
			    return false;
			}
			if(pan_start.length==15){
				pan_start=pan_start+'0';
			}
			if(pan_end.length==15){
				pan_end=pan_end+'9';
			}
			if(pan_end<pan_start){
				alert("结束卡号不能小于开始卡号");
				document.getElementsByName("pan_start")[0].value="";
			    document.getElementsByName("pan_start")[0].focus() ;
			    return false;
			}    
		}else{
		}
		
		userForm.action = "<%=path%>/queryCard.do?method=getCardListkQ&pan_start="+pan_start+"&pan_end="+pan_end;
		<%-- userForm.action = "<%=path%>/queryCard.do?method=getCardList&pan_start="+pan_start+"&pan_end="+pan_end; --%>
		userForm.submit();
	}

	function queryOne(id)
	{
		var url = "<%=path%>/queryCard.do?method=getCardById&id="+id+"&random=" + Math.random();
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

	function queryAcctById(id)
	{
		var url = "<%=path%>/queryAcct.do?method=getAcctById&id="+id+"&random=" + Math.random();
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
	
	function queryCustById(id){
		//		window.showModalDialog("<%=path%>/queryCust.do?method=getCustById&Id="+id, "_blank", 'dialogWidth=800px;dialogHeight=440px;scroll=yes;resizable=no;status=no;center=yes;');
		var userForm = document.all("cardForm");
		document.forms[0].id.value=id;
		userForm.action = "<%=path%>/queryCust.do?method=getCustById&id="+id;
		userForm.submit();
	}
</script>
<shiro:lacksPermission name="cardbatch:card:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="cardbatch:card:view">
<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
<html:form action="/queryCard?method=getCardList" method="post" styleId="cardForm">
<html:hidden property="id"/>
	<table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%" >
		<tr>
			<td align="center" valign="top" >
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td  align="left"   width="28" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">
					</td>
					<td   height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;当前位置： 信息查询 &gt; 卡查询 </td>
					<td   width="7" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) "> </td>
				</tr>
				</table>
				<table cellpadding="0" cellspacing="0" width="100%" align="left"  >
					<tr class="serch">
						<td align="right">开始卡号</td>
						<td align="left">
						<html:text property="pan_start" maxlength="16" size="16"  onkeyup="this.value=this.value.replace(/\D/g,'')"/></td>
						<td align="right" >结束卡号</td>
						<td align="left">
						<html:text property="pan_end" maxlength="16" size="16"  onkeyup="this.value=this.value.replace(/\D/g,'')"/></td>
						<td align="right">卡产品：</td>
						<td align="left">
						<logic:present name="cardProductList">
							<html:select property="crdproduct_id" style="width:100px;" >
								<html:option value="">－请选择－</html:option>
								<html:optionsCollection name="cardProductList" label="descr" value="id" />
							</html:select>
						</logic:present>
						</td>
						<td align="right">状态：</td>
						<td align="left">
						<logic:present name="cardStatusList">
							<html:select property="statcode" style="width:100px;" >
								<html:option value="">－请选择－</html:option>
								<html:optionsCollection name="cardStatusList" label="descr" value="statcode" />
							</html:select>
						</logic:present></td>
						<td height="25" align="left"></td>
					</tr>
					<tr class="serch">
						<td align="right">批次：</td>
						<td align="left"><html:text property="batch" maxlength="20" size="16"/></td>
						<td align="right">激活日期：</td>
						<td align="left"><html:text property="effdate" size="16" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/></td>
						<td align="right"></td>
						<td align="left" ></td>
						<td align="right" ></td>
						<td height="25" align="left">
							<input type="button" class="button" onClick='return query()'  style="background-image: url(<%=path%>/image1/border/Check_button.gif)">									
						</td>
					</tr>
					<tr>
						<td colspan="8">
							<!-- 用Diaplay Tag来显示 -->
							<display:table name="cardList" partialList="true" size="pageBean.totalRecords" pagesize="10" style="width:100%" class="dpTable" cellpadding="0" cellspacing="0" id="displayTable" requestURI="/queryCard.do">
								<display:column title="卡号" style="text-align:center" property="pan" headerClass="sortable" sortable="true" />
								<display:column title="卡产品" style="text-align:center" property="crdproduct_name" headerClass="sortable" sortable="true" />
								<display:column title="状态" style="text-align:center" property="statcode_name" headerClass="sortable" sortable="true" />
								<display:column title="金额" style="text-align:center" property="avlbal" headerClass="sortable" sortable="true" />
								<display:column title="批次" style="text-align:center" property="batch" headerClass="sortable" sortable="true" />
								<display:column title="激活日期" style="text-align:center" property="effdate" headerClass="sortable" sortable="true" />
								<display:column title="账户详情" style="text-align:center" sortable="true">
									<a href="javascript:;" onclick="queryAcctById('<bean:write name="displayTable" property="accdet_id"/>')" ><bean:write name="displayTable" property="accdet_id"/></a>
								</display:column>
								<display:column title="卡明细" style="text-align:center" sortable="false">
									<a href="javascript:;" onclick="queryOne('<bean:write name="displayTable" property="id"/>')" ><img border="0" src="<%=path %>/image1/border/query.png" /></a>
								</display:column>
							</display:table>
							<!-- 用Diaplay Tag来显示 -->	
						</td>
					</tr>
					<tr>
							<td align="right" colspan="3">
								合计金额：<%=sumAmt%>
							</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</html:form>
</body>
</shiro:hasPermission>

</html:html>
