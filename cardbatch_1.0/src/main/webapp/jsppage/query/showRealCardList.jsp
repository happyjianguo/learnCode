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
		var addDT_startdate = document.getElementsByName("effdateStart")[0].value;
		var addDT_enddate = document.getElementsByName("effdateEnd")[0].value;
		var pan_start = document.getElementsByName("pan_start")[0].value;
		var pan_end = document.getElementsByName("pan_end")[0].value;
		if(addDT_startdate!='' && addDT_enddate!='' && (getDate(addDT_startdate)-getDate(addDT_enddate)>0)){
		    alert("����ʱ�䲻��С�ڿ�ʼʱ��");
		    window.document.forms['cardForm'].elements['effdateStart'].focus() ;
		    return false;
		}

		if(pan_start!='' && pan_end==''){
			if(pan_start.length<15){
				alert("��ʼ���Ų���С��15λ");
				document.getElementsByName("pan_start")[0].focus() ;
			    return false;
			}else if(pan_start.length==15){
				pan_start=pan_start+'0';
			}
		}else if(pan_start=='' && pan_end!=''){
			if(pan_end.length<15){
				alert("�������Ų���С��15λ");
				document.getElementsByName("pan_end")[0].focus() ;
			    return false;
			}else if(pan_end.length==15){
				pan_end=pan_end+'9';
			}
		}else if(pan_start!='' && pan_end!=''){
			if(pan_start.length<15){
				alert("��ʼ���Ų���С��15λ");
				document.getElementsByName("pan_start")[0].focus() ;
			    return false;
			}
			if(pan_end.length<15){
				alert("�������Ų���С��15λ");
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
				alert("�������Ų���С�ڿ�ʼ����");
				document.getElementsByName("pan_start")[0].value="";
			    document.getElementsByName("pan_start")[0].focus() ;
			    return false;
			}    
		}else{
		}
		
		var userForm = document.all("cardForm");
		userForm.action = "<%=path%>/queryCard.do?method=getRealCardListkQ&pan_start="+pan_start+"&pan_end="+pan_end;
		<%-- userForm.action = "<%=path%>/queryCard.do?method=getRealCardList&pan_start="+pan_start+"&pan_end="+pan_end; --%>
		userForm.submit();
	}
	//dataת��
	function getDate(date){
	 var dates = date.split("-");
	 var dateReturn = '';
	 
	 for(var i=0; i<dates.length; i++){
	  dateReturn+=dates[i];
	 }
	 return dateReturn;
	}	
	function queryOne(id)
	{
		var url = "<%=path%>/queryCard.do?method=getRealCardById&id="+id+"&random=" + Math.random();
		var iWidth = 1046; //�������ڵĿ��;
		var iHeight = 600; //�������ڵĸ߶�;
		var iTop = (window.screen.availHeight - 30 - iHeight) / 2; //��ô��ڵĴ�ֱλ��;
		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; //��ô��ڵ�ˮƽλ��;
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
		var iWidth = 1046; //�������ڵĿ��;
		var iHeight = 600; //�������ڵĸ߶�;
		var iTop = (window.screen.availHeight - 30 - iHeight) / 2; //��ô��ڵĴ�ֱλ��;
		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; //��ô��ڵ�ˮƽλ��;
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
	function queryCustById(id)
	{
		var url = "<%=path%>/queryCust.do?method=getCustById&id="+id+"&random=" + Math.random();
		var iWidth = 1046; //�������ڵĿ��;
		var iHeight = 600; //�������ڵĸ߶�;
		var iTop = (window.screen.availHeight - 30 - iHeight) / 2; //��ô��ڵĴ�ֱλ��;
		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; //��ô��ڵ�ˮƽλ��;
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
</script>
<shiro:lacksPermission name="cardbatch:realCard:view">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="cardbatch:realCard:view">
<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
<html:form action="/queryCard?method=getRealCardList" method="post" styleId="cardForm">
<html:hidden property="id"/>
	<table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%" >
		<tr>
			<td align="center" valign="top" >
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td  align="left"   width="28" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">
					</td>
					<td   height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;��ǰλ�ã� ��Ϣ��ѯ &gt; ����ѯ </td>
					<td   width="7" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) "> </td>
				</tr>
				</table>
				<table cellpadding="0" cellspacing="0" width="100%" align="left"  >
					<tr class="serch">
						<td align="right">��ʼ����</td>
						<td align="left">
						<html:text property="pan_start" maxlength="16" size="16"  onkeyup="this.value=this.value.replace(/\D/g,'')"/></td>
						<td align="right" >��������</td>
						<td align="left">
						<html:text property="pan_end" maxlength="16" size="16"  onkeyup="this.value=this.value.replace(/\D/g,'')"/></td>
						<td align="right">�ֻ��ţ�</td>
						<td align="left" ><html:text property="cell_phone" maxlength="20" size="16"/></td>
						<td align="right">����Ʒ��</td>
						<td align="left">
						<logic:present name="cardProductList">
							<html:select property="crdproduct_id" style="width:100px;" >
								<html:option value="">����ѡ��</html:option>
								<html:optionsCollection name="cardProductList" label="descr" value="id" />
							</html:select>
						</logic:present>
						</td>
						<td align="right">״̬��</td>
						<td align="left">
						<logic:present name="cardStatusList">
							<html:select property="statcode" style="width:100px;" >
								<html:option value="">����ѡ��</html:option>
								<html:optionsCollection name="cardStatusList" label="descr" value="statcode" />
							</html:select>
						</logic:present></td>
						<td height="25" align="left"></td>
					</tr>
					<tr class="serch">
						<td align="right">������</td>
						<td align="left" ><html:text property="cust_name" maxlength="20" size="16"/></td>
						<td align="right">���Σ�</td>
						<td align="left"><html:text property="batch" maxlength="20" size="16"/></td>
						<td align="right">��������  �ӣ�</td>
						<td align="left"><html:text property="effdateStart" size="16" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/></td>
						<td align="right">����</td>
						<td align="left"><html:text property="effdateEnd" size="16" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/></td>
						<td align="right"></td>
						<td height="25" align="left">
							<input type="button" class="button" onClick='return query()'  style="background-image: url(<%=path%>/image1/border/Check_button.gif)">									
						</td>
					</tr>
					<tr>
						<td colspan="10">
							<!-- ��Diaplay Tag����ʾ -->
							<display:table name="cardList" partialList="true" size="pageBean.totalRecords" pagesize="10" style="width:100%" class="dpTable" cellpadding="0" cellspacing="0" id="displayTable" requestURI="/queryCard.do">
								<display:column title="����" style="text-align:center" property="cust_name" headerClass="sortable" sortable="true" />
								<display:column title="�ֻ���" style="text-align:center" property="cell_phone" headerClass="sortable" sortable="true" />
								<display:column title="����" style="text-align:center" property="pan" headerClass="sortable" sortable="true" />
								<display:column title="����Ʒ" style="text-align:center" property="crdproduct_name" headerClass="sortable" sortable="true" />
								<display:column title="״̬" style="text-align:center" property="statcode_name" headerClass="sortable" sortable="true" />
								<display:column title="���" style="text-align:center" property="avlbal" headerClass="sortable" sortable="true" />
								<display:column title="����" style="text-align:center" property="batch" headerClass="sortable" sortable="true" />
								<display:column title="��������" style="text-align:center" property="effdate" headerClass="sortable" sortable="true" />
								<display:column title="�˻�����" style="text-align:center" sortable="true">
									<a href="javascript:;" onclick="queryAcctById('<bean:write name="displayTable" property="accdet_id"/>')" ><bean:write name="displayTable" property="accdet_id"/></a>
								</display:column>
								<display:column title="�ͻ�����" style="text-align:center" sortable="true">
									<a href="javascript:;" onclick="queryCustById('<bean:write name="displayTable" property="custdet_id"/>')" ><bean:write name="displayTable" property="custdet_id"/></a>
								</display:column>
								<display:column title="����ϸ" style="text-align:center" sortable="false">
									<a href="javascript:;" onclick="queryOne('<bean:write name="displayTable" property="id"/>')" ><img border="0" src="<%=path %>/image1/border/query.png" /></a>
								</display:column>
							</display:table>
							<!-- ��Diaplay Tag����ʾ -->	
						</td>
					</tr>
					<tr><td/>
							<td align="right" colspan="3">
								�ϼƽ�<%=sumAmt%>
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
