<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/ngp-pageTag.tld" prefix="util"%>
<%
	String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html lang="true">
<head>
	<html:base />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/new_look.css" />
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/style.css" />
	<script type="text/javascript" src="<%=path%>/js/eposcc.js"></script>
	<script src="<%=path%>/js/calendar.js"></script>
	<script src="<%=path%>/js/jquery-1.8.3.min.js"></script>
	<script src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
	<%-- <script type="text/javascript" src="<%=path%>/js/dwr.js"></script> --%>
</head>
<script language="javascript">
	$(document).ready(function() {
		$('#selectAll').click(function() { 				
			var checkBoxs = document.getElementsByName("selectMer");
			if($("#selectAll").prop("checked")){
				$('input:checkbox').each(function() {
				    $(this).attr('checked', true);
				});
			}else{
				$('input:checkbox').each(function () {
			        $(this).attr('checked',false);
				});
			}
		});			
	});

	function addConfirm()
	{
		var url = "<%=path%>/merchant.do?method=preAddMcCheck&random=" + Math.random();
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
	function preQueryMerchant(id)
	{
		var url = "<%=path%>/merchant.do?method=preQueryMerchant&id="+id+"&random=" + Math.random();
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
	function preModMerchant(id)
	{
		var url = "<%=path%>/merchant.do?method=preModMerchant&id="+id+"&random=" + Math.random();
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
	function preModMrchTerminal(id)
	{
		var url = "<%=path%>/merchant.do?method=preModMrchTerminal&id="+id+"&random=" + Math.random();
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
	function query(){
		var addDT_startdate = document.getElementsByName("addDT_startdate")[0].value;
		var addDT_enddate = document.getElementsByName("addDT_enddate")[0].value;
		if(addDT_startdate!='' && addDT_enddate!='' && (getDate(addDT_startdate)-getDate(addDT_enddate)>0)){
		    alert("����ʱ�䲻��С�ڿ�ʼʱ��");
		    window.document.forms['merchantForm'].elements['addDT_startdate'].focus() ;
		    return false;
		} 
		var merchantForm = document.all("merchantForm");
		merchantForm.action = "<%=path%>/merchant.do?method=getMerchantList";
		merchantForm.submit();
	}
	function exportExcel(){
		var addDT_startdate = document.getElementsByName("addDT_startdate")[0].value;
		var addDT_enddate = document.getElementsByName("addDT_enddate")[0].value;
		if(addDT_startdate!='' && addDT_enddate!='' && (getDate(addDT_startdate)-getDate(addDT_enddate)>0)){
		    alert("����ʱ�䲻��С�ڿ�ʼʱ��");
		    window.document.forms['merchantForm'].elements['addDT_startdate'].focus() ;
		    return false;
		} 
		var merchantForm = document.all("merchantForm");
		merchantForm.action = "<%=path%>/merchant.do?method=exportExcel";
		merchantForm.submit();
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
	
	function allotOrg(selectMers,selectMer){
	 	var aa = document.getElementsByName("selectMer");
	 	var bb = document.all["selectMers"];
	
	 	bb.value = "";
	 	flag = false;
	 	for (var i=0; i<aa.length; i++){
	 		if(aa[i].checked){
	 			flag = true;
	 			if(bb.value==""){
	 				bb.value = aa[i].value;
	 			}else
	 				bb.value = bb.value + "|" + aa[i].value;
	 		}
	 	}
	 	if(flag){
			window.location.href = "<%=path%>/merchant.do?method=preAllotMerOrg&merchantIds="+bb.value+"&random=" + Math.random();
	 	}else
	 		alert("������ѡ��һ���̻�!");
		}
	
	function allotSettlement_person(selectMers,selectMer){
	 	var aa = document.getElementsByName("selectMer");
	 	var bb = document.all["selectMers"];
	
	 	bb.value = "";
	 	flag = false;
	 	for (var i=0; i<aa.length; i++){
	 		if(aa[i].checked){
	 			flag = true;
	 			if(bb.value==""){
	 				bb.value = aa[i].value;
	 			}else
	 				bb.value = bb.value + "|" + aa[i].value;
	 		}
	 	}
	 	if(flag){
			window.location.href = "<%=path%>/merchant.do?method=preAllotSettlement_person&merchantIds="+bb.value+"&random=" + Math.random();
	 	}else
	 		alert("������ѡ��һ���̻�!");
		}
	function allotMerchant_fenrunorg(selectMers,selectMer){
	 	var aa = document.getElementsByName("selectMer");
	 	var bb = document.all["selectMers"];
	
	 	bb.value = "";
	 	flag = false;
	 	for (var i=0; i<aa.length; i++){
	 		if(aa[i].checked){
	 			flag = true;
	 			if(bb.value==""){
	 				bb.value = aa[i].value;
	 			}else
	 				bb.value = bb.value + "|" + aa[i].value;
	 		}
	 	}
	 	if(flag){
			window.location.href = "<%=path%>/merchant.do?method=preallotMerchant_fenrunorg&merchantIds="+bb.value+"&random=" + Math.random();
	 	}else
	 		alert("������ѡ��һ���̻�!");
		}
		
</script>
<shiro:lacksPermission name="cardbatch:merchant:view">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="cardbatch:merchant:view">
<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
	<html:form action="/merchant?method=getMerchantList" method="post"
		styleId="merchantForm">
		<bean:define id="menu_level" name="menu_level" />
		
		<html:hidden property="selectMers" />
		
		<table border="0" cellpadding="0" cellspacing="0" width="100%"
			height="100%">
			<tr>
				<td align="center" valign="top" height="87%">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td width="28" height="10"></td>
						</tr>
						<tr>
							<td align="left" width="28" height="28"
								style=" background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">

							</td>
							<td height="28"
								style="background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;">
								&nbsp;&nbsp;��ǰλ�ã� �̻��ն���Ϣ���� &gt; �̻���Ϣ����
							</td>
							<td width="7" height="28"
								style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) ">
							</td>
						</tr>
						<tr>
							<td width="28" height="5" colspan="3"></td>
						</tr>
					</table>
					<table cellpadding="0" border="0" cellspacing="0" width="100%"
						 style="padding: 0px;" align="left">
						<tr class="serch">
							<td style="white-space: nowrap" align="right">
								�̻���
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="mrchnoQ" maxlength="15" size="16" />
							</td>
							<td style="white-space: nowrap" align="right">
								�̻�����
							</td>
							<td style="white-space: nowrap" align="left">
								<html:select property="type_yf">
									<html:option value="">����ѡ��</html:option>								
									<logic:present name="cardbatch_mer_typeList">
										<html:optionsCollection name="cardbatch_mer_typeList"
											label="param_name" value="param_value" />
									</logic:present>
								</html:select>								
							</td>
							<td style="white-space: nowrap" align="right">
								�̻�״̬
							</td>
							<td style="white-space: nowrap" align="left">
								<html:select property="mrchstat">
									<html:option value="">����ѡ��</html:option>
									<html:option value="0">����</html:option>
									<html:option value="1">������</html:option>
									<html:option value="2">������</html:option>
									<html:option value="3">����</html:option>
								</html:select>
							</td>
							<td style="white-space: nowrap" align="right">
								����ʱ��
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="addDT_startdate" maxlength="20" size="15"
									onclick="WdatePicker({dateFmt:'yyyyMMddHHmmss'});" />
								-
								<html:text property="addDT_enddate" maxlength="20" size="15"
									onclick="WdatePicker({dateFmt:'yyyyMMddHHmmss'});" />
							</td>
							<td style="white-space: nowrap" align="right">
								 ����̻�����  
							</td>
							<td style="white-space: nowrap" align="left">
								 <html:select property="yard_mer_type">
									<html:option value="">����ѡ��</html:option>
									<html:option value="0">��Ƴ���</html:option>
								</html:select> 
							</td>
						</tr>
						<tr class="serch">
							<td style="white-space: nowrap" align="right">
								��ϵ��
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="contact3" maxlength="20" size="16" />
							</td>

							<td style="white-space: nowrap" align="right">
								�绰
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="telno1" maxlength="20" size="16" />
							</td>

							<td style="white-space: nowrap" align="right">
								�̻�����
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="mrcht_name" maxlength="200" size="16" />
							</td>
							<td style="white-space: nowrap" align="right">
								����
							</td>
							<td style="white-space: nowrap" align="left">
								<logic:present name="provinList">
									<html:select property="province" onchange="getCity_no()">
										<html:option value="">����ѡ��</html:option>
										<html:optionsCollection name="provinList"
											label="province_city" value="aid" />
									</html:select>
								</logic:present>
								<html:select property="city_no" onchange="getZone()">
									<html:option value="">����ѡ��</html:option>
									<logic:present name="city_noList">
										<html:optionsCollection name="city_noList"
											label="province_city" value="aid" />
									</logic:present>
								</html:select>
								<html:select property="zone">
									<html:option value="">����ѡ��</html:option>
									<logic:present name="areaList">
										<html:optionsCollection name="areaList"
											label="province_city" value="aid" />
									</logic:present>
								</html:select>
							</td>
							<td height="25" align="left">
								<input type="button" class="button" onclick='return query()'
									style="background-image: url(<%=path%>/image1/border/Check_button.gif)" />
								<a href="javascript:;" onClick='return exportExcel()'><img alt="" src="<%=path%>/images/icon-import.png"> </a>									
							</td>
							<shiro:hasPermission name="cardbatch:merchant:add">
								<td align="center">
									<input type="button" class="button"
										onClick='return addConfirm()'
										style="background-image: url(<%=path%>/image1/border/New_button.gif)">
									<input type="button" class="button" onclick='allotOrg()' 
										style="background-image: url(<%=path%>/image1/border/Allot_button.gif)">
								</td>
							</shiro:hasPermission>
						</tr>
						<tr class="serch">
							<td style="white-space: nowrap" align="right">
							</td>
							<td style="white-space: nowrap" align="left">
								<shiro:hasPermission name="cardbatch:merchant:add">
									<input type="button" id="test1" onclick='allotSettlement_person()' value="�����޸Ľ���Ա"/>  
								</shiro:hasPermission>
							</td>
							<%-- <td style="white-space: nowrap" align="right">
								<shiro:hasPermission name="cardbatch:merchant:add">
									<input type="button" id="test2" onclick='allotMerchant_fenrunorg()' value="�����޸ķ������"/>  
								</shiro:hasPermission>
							</td> --%>
							<td style="white-space: nowrap" align="right">
							</td>
							<td style="white-space: nowrap" align="left">
							</td>
							<td style="white-space: nowrap" align="right">
							</td>
							<td style="white-space: nowrap" align="left">
							</td>
							<td style="white-space: nowrap" align="right">
							</td>
							<td style="white-space: nowrap" align="left">
							</td>
							<td style="white-space: nowrap" align="right">
							</td>
							<td style="white-space: nowrap" align="left">
							</td>
						</tr>
						<tr>
						<td colspan="13">
							<display:table name="merchantList" partialList="true" size="pageBean.totalRecords" pagesize="10" style="width:100%" class="dpTable" cellpadding="0" cellspacing="0" id="displayTable" requestURI="/merchant.do">
								<display:column title="<input type='checkbox' name='selectAll' id='selectAll'/> ȫѡ" style="text-align:center">
									<c:if test="${displayTable.merchant_org == null or displayTable.merchant_org eq ''}">
										<input type="checkbox" name="selectMer" id="selectMer" value="${displayTable.mrchno}" />
									</c:if>
									<c:if test="${displayTable.merchant_org != null and displayTable.merchant_org ne ''}"></c:if>
								</display:column>
								<display:column title="�̻�����" style="text-align:center" property="merchant_org" headerClass="sortable" sortable="true" />
								<%-- <display:column title="�������" style="text-align:center" property="merchant_fenrunorg" headerClass="sortable" sortable="true" /> --%>
								<display:column title="�̻���" style="text-align:center" property="mrchno" headerClass="sortable" sortable="true" />
								<display:column title="�̻�����" style="text-align:center" property="mrcht_name" headerClass="sortable" sortable="true" />
								<display:column title="�̻�����" style="text-align:center" property="type_yf_desc" headerClass="sortable" sortable="true" />
								<display:column title="�̻�״̬" style="text-align:center"  headerClass="sortable" sortable="true">
									<c:if test="${displayTable.mrchstat=='0' }">���� </c:if>
									<c:if test="${displayTable.mrchstat=='1' }">������ </c:if>
									<c:if test="${displayTable.mrchstat=='2' }">������</c:if>
									<c:if test="${displayTable.mrchstat=='3' }">����</c:if>
								</display:column>
								<display:column title="ISO��ӦMCC��" style="text-align:center" property="acptbus" headerClass="sortable" sortable="true" />
								<display:column title="��ϵ��" style="text-align:center" property="contact3" headerClass="sortable" sortable="true" />
								<display:column title="�绰" style="text-align:center" property="telno1" headerClass="sortable" sortable="true" />
								<display:column title="����ʱ��" style="text-align:center" property="add_date" headerClass="sortable" sortable="true" />
								<display:column title="�鿴" style="width:5%;text-align:center">
									<a href="javascript:;" onclick="preQueryMerchant('<c:out value="${displayTable.id}"/>')">
										<img border="0" src="<%=path%>/image1/border/query.png" />
									</a>
								</display:column>							
								<shiro:hasPermission name="cardbatch:merchant:edit">
									<display:column title="�޸�" style="width:5%;text-align:center">
										<c:if test="${displayTable.mrchstat!='3' }">
										 <a href="<%=path%>/merchant.do?method=preModMerchant&id=${displayTable.id}">�޸�</a>
										</c:if>
									</display:column>									
								</shiro:hasPermission>
								<display:column title="�����޸��ն���Ϣ" style="width:5%;text-align:center">
									<c:if test="${displayTable.mrchstat!='3' }">
									<a href="/merchant.do?method=preModMrchTerminal&id=${displayTable.id}">�����޸��ն���Ϣ</a>
									</c:if>
								</display:column>																
							</display:table>
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
