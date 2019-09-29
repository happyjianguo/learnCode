<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/ngp-pageTag.tld" prefix="util"%>
<%
	String path = request.getContextPath();
	String sumAmt=request.getAttribute("sumAmt")==null?"":request.getAttribute("sumAmt").toString();
	String sumPanCount=request.getAttribute("sumPanCount")==null?"":request.getAttribute("sumPanCount").toString();
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
	<script src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=path%>/js/dwr.js"></script>
	
</head>
<script language="javascript">
	function query(){
		/* var addDT_startdate = document.getElementsByName("startdate")[0].value;
		var addDT_enddate = document.getElementsByName("enddate")[0].value; */
		var batch_stat = document.getElementsByName("batch_stat")[0].value;
		var crdproduct = document.getElementsByName("crdproduct")[0].value;
		var operator = document.getElementsByName("operator")[0].value;
		var addDT_start_period = document.getElementsByName("start_period")[0].value;
		var addDT_end_period = document.getElementsByName("end_period")[0].value;
		if(addDT_start_period!='' && addDT_end_period!='' && (getDate(addDT_start_period)-getDate(addDT_end_period)>0)){
		    alert("����ʱ�䲻��С�ڿ�ʼʱ��");
		    window.document.forms['depositForm'].elements['descrStartDate'].focus() ;
		    return false;
		}
		
		/* if(addDT_startdate!='' && addDT_enddate!='' && (getDate(addDT_startdate)-getDate(addDT_enddate)>0)){
		    alert("����ʱ�䲻��С�ڿ�ʼʱ��");
		    window.document.forms['depositAuditForm'].elements['startdate'].focus() ;
		    return false;
		} */ 
		if(operator==''){
			alert("������Ա����Ϊ�գ�");
			return false;
		}else if(addDT_start_period==''){
			alert("���ڿ�ʼʱ�䲻��Ϊ�գ�");
			return false;
		}else if(addDT_end_period==''){
			alert("���ڽ���ʱ�䲻��Ϊ�գ�");
			return false;
		}else if(batch_stat==''){
			alert("����״̬����Ϊ�գ�");
			return false;
		}else if(crdproduct==''){
			alert("����Ʒ����Ϊ�գ�");
			return false;
		}
		var depositAuditForm = document.all("depositAuditForm");
		depositAuditForm.action = "<%=path%>/depositAudit.do?method=getDepositList";
		depositAuditForm.submit();
	}
	function exportExcel(){	
		/* var addDT_startdate = document.getElementsByName("startdate")[0].value; 01
		var addDT_enddate = document.getElementsByName("enddate")[0].value; */
		var batch_stat = document.getElementsByName("batch_stat")[0].value;
		var crdproduct = document.getElementsByName("crdproduct")[0].value;
		var operator = document.getElementsByName("operator")[0].value;
		var addDT_start_period = document.getElementsByName("start_period")[0].value;
		var addDT_end_period = document.getElementsByName("end_period")[0].value;
		if(addDT_start_period!='' && addDT_end_period!='' && (getDate(addDT_start_period)-getDate(addDT_end_period)>0)){
		    alert("����ʱ�䲻��С�ڿ�ʼʱ��");
		    window.document.forms['depositForm'].elements['descrStartDate'].focus() ;
		    return false;
		}
		
		/* if(addDT_startdate!='' && addDT_enddate!='' && (getDate(addDT_startdate)-getDate(addDT_enddate)>0)){
		    alert("����ʱ�䲻��С�ڿ�ʼʱ��");
		    window.document.forms['depositAuditForm'].elements['startdate'].focus() ;
		    return false;
		} */ 
		if(operator==''){
			alert("������Ա����Ϊ�գ�");
			return false;
		}else if(addDT_start_period==''){
			alert("���ڿ�ʼʱ�䲻��Ϊ�գ�");
			return false;
		}else if(addDT_end_period==''){
			alert("���ڽ���ʱ�䲻��Ϊ�գ�");
			return false;
		}else if(batch_stat==''){
			alert("����״̬����Ϊ�գ�");
			return false;
		}else if(crdproduct==''){
			alert("����Ʒ����Ϊ�գ�");
			return false;
		}
		var depositAuditForm = document.all("depositAuditForm");
		depositAuditForm.action = "<%=path%>/depositAudit.do?method=exportExcel";
		depositAuditForm.submit();			
		//window.open('<%=path%>/depositAudit.do?method=exportExcel');
	
	}
	function resetClick()
	{
		/* 
		var objId=document.forms[0];
		for(var i=0; i<doc.length;i++){
			if (objId.elements[i].type == "text") {
				objId.elements[i].value = "";
			} else if (objId.elements[i].type == "password") {
				objId.elements[i].value = "";
			} else if (objId.elements[i].type == "radio") {
				objId.elements[i].checked = false;
			} else if (objId.elements[i].type == "checkbox") {
				objId.elements[i].checked = false;
			} else if (objId.elements[i].type == "select-one") {
				objId.elements[i].options[0].selected = true;
			}else if (objId.elements[i].type == "select-multiple") {
				for (var j = 0; j < objId.elements[i].options.length; j++) {
					objId.elements[i].options[j].selected = false;
				}
			} else if (objId.elements[i].type == "textarea") {
				objId.elements[i].value = "";
			}
		}
		 */
		//alert($("#batch_stat"));
		
		document.forms[0].operator.value="";
		document.forms[0].batch_stat.value="";
		document.forms[0].crdproduct.value="";
		document.forms[0].start_period.value="";
		document.forms[0].end_period.value="";
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
	function editClicks(id)
	{
		var url = "<%=path%>/depositAudit.do?method=showDepositInfo&id="+id+"&random=" + Math.random();
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
	function createBatchCheck(){
		/* var addDT_startdate = document.getElementsByName("startdate")[0].value;
		var addDT_enddate = document.getElementsByName("enddate")[0].value; */
		var batch_stat = document.getElementsByName("batch_stat")[0].value;
		var crdproduct = document.getElementsByName("crdproduct")[0].value;
		var operator = document.getElementsByName("operator")[0].value;
		var addDT_start_period = document.getElementsByName("start_period")[0].value;
		var addDT_end_period = document.getElementsByName("end_period")[0].value;
		if(addDT_start_period!='' && addDT_end_period!='' && (getDate(addDT_start_period)-getDate(addDT_end_period)>0)){
		    alert("����ʱ�䲻��С�ڿ�ʼʱ��");
		    window.document.forms['depositForm'].elements['descrStartDate'].focus() ;
		    return false;
		}
		
		/* if(addDT_startdate!='' && addDT_enddate!='' && (getDate(addDT_startdate)-getDate(addDT_enddate)>0)){
		    alert("����ʱ�䲻��С�ڿ�ʼʱ��");
		    window.document.forms['depositAuditForm'].elements['startdate'].focus() ;
		    return false;
		} */ 
		if(operator==''){
			alert("������Ա����Ϊ�գ�");
			return false;
		}else if(addDT_start_period==''){
			alert("���ڿ�ʼʱ�䲻��Ϊ�գ�");
			return false;
		}else if(addDT_end_period==''){
			alert("���ڽ���ʱ�䲻��Ϊ�գ�");
			return false;
		}else if(batch_stat==''){
			alert("����״̬����Ϊ�գ�");
			return false;
		}else if(crdproduct==''){
			alert("����Ʒ����Ϊ�գ�");
			return false;
		}
		if(batch_stat!='01'){
			alert("����˵����ݲ����ٴ���ˣ�");
			return false;
		}
		document.getElementById("batchbutton").disabled=true;
		if(confirm("ȷ��Ҫȫ�������"))
 		{	
 			depositAuditForm.action = "<%=path%>/depositAudit.do?method=createBatchCheck&msgtype=0028";
 			depositAuditForm.submit();
 		}else{
 			document.getElementById("batchbutton").disabled=false;
 		}
	}
	
</script>
<shiro:lacksPermission name="cardbatch:depositAudit:view">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="cardbatch:depositAudit:view">
<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
	<html:form action="/depositAudit.do?method=getDepositList" method="post"
		styleId="depositAuditForm">
		<bean:define id="menu_level" name="menu_level" />
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
								&nbsp;&nbsp;��ǰλ�ã��������� &gt; ������ֵ��˹���</td>
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
								������Ա
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="operator" maxlength="20" size="10" />
								<span class="help-inline"><font color="red">*</font> </span>
							</td>
							<%-- <td style="white-space: nowrap" align="right">
								����ʱ��
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="startdate" maxlength="20" size="15"
									onclick="WdatePicker({dateFmt:'yyyyMMddHHmmss'});" />
								-
								<html:text property="enddate" maxlength="20" size="15"
									onclick="WdatePicker({dateFmt:'yyyyMMddHHmmss'});" />
							</td> --%>
							
							<td style="white-space: nowrap" align="right">
								����
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="start_period" maxlength="20" size="15"
									onclick="WdatePicker({dateFmt:'yyyyMMdd'});" />
									<span class="help-inline"><font color="red">*</font> </span>
								-
								<html:text property="end_period" maxlength="20" size="15"
									onclick="WdatePicker({dateFmt:'yyyyMMdd'});" />
									<span class="help-inline"><font color="red">*</font> </span>
							</td>
							
						<!-- </tr>
						<tr class="serch"> -->
							<td style="white-space: nowrap" align="right">
								����״̬
							</td>
							<td style="white-space: nowrap" align="left">
								<html:select property="batch_stat">	
									<html:option value="">����ѡ��</html:option>
									<html:option value="00">�Ѵ���</html:option>
									<html:option value="01">δ����</html:option>
									<html:option value="02">�ѳ���</html:option>
									<html:option value="03">�ѳ���</html:option>
								</html:select>
								<span class="help-inline"><font color="red">*</font> </span>
							</td>
							<td style="white-space: nowrap" align="right">
								����Ʒ
							</td>							
							<td height="25" align="left">
								<html:select property="crdproduct" >
									<html:option value="">����ѡ��</html:option>
									<logic:present name="cardProductList">
										<html:optionsCollection name="cardProductList" label="descr" value="crdproduct" />
									</logic:present>
								</html:select>
								<span class="help-inline"><font color="red">*</font> </span>
							</td>
						</tr>
						<tr class="serch">
							<!-- <td height="25" style="white-space: nowrap"align="right">
								<input type="checkbox" name="selectAll" onclick="checkAll(this, 'checkItem')" />
								�Ƿ�ȫѡ
							</td> -->
							<td height="25" align="right"></td>
							<td height="25" align="right"></td>
							<td height="25" align="right"></td>
							<td height="25" align="right"></td>
							<td height="25" align="right">
								<input id=batchbutton type="button"  value="ȫ�����" onclick="javascript:createBatchCheck();"/>
<!-- 								<input id=batchbutton type="button"  value="ȫ�����" onclick="javascript:createBatchCheck('selectItems','checkItem','createBatchCheck');"/> -->
							</td>					
							<td height="25" align="right">
								<input type="button" class="button" onclick='return query()'
									style="background-image: url(<%=path%>/image1/border/Check_button.gif)" />							
							</td>	
							<td height="25" align="right">						
								<input type="button" class="button" onclick='return resetClick()'
									style="background-image: url(<%=path%>/image1/border/Clear_button.gif)" />	
							</td>								
							<td height="25" align="right">
								<a href="javascript:;" onClick='return exportExcel()'><img alt="" src="<%=path%>/images/icon-import.png"> </a>
							</td>													
						</tr>						
						<tr>
						<td colspan="13">
							<display:table name="openCardList" partialList="true" size="pageBean.totalRecords" pagesize="10" style="width:100%" class="dpTable" cellpadding="0" cellspacing="0" id="displayTable" requestURI="/depositAudit.do">
								<%-- 
								<display:column  title="ѡ��" style="width:3%;text-align:center;" headerClass="sortable" sortable="true" >
								<c:if test="${(displayTable.pay_type=='1'||displayTable.pay_type=='3'||displayTable.pay_type=='4'||displayTable.pay_type=='6')&&displayTable.batch_stat=='01'}">
									<input type="checkbox" name="checkItem" value="<c:out value="${displayTable.id}#${displayTable.father_order}#end"/>">		
								</c:if>
								</display:column> 
								--%>
								<display:column title="���" style="text-align:center" property="id" headerClass="sortable" sortable="true" />
								<display:column title="��������" style="text-align:center"  headerClass="sortable" sortable="true">
									<c:if test="${displayTable.txncode=='28'}">
										<c:if test="${displayTable.batch_stat=='00'}">��ֵ����</c:if>
										<c:if test="${displayTable.batch_stat=='01'}">��ֵ����</c:if>
										<c:if test="${displayTable.batch_stat=='02'}">��ֵ����</c:if>
										<c:if test="${displayTable.batch_stat=='03'}">��ֵ����</c:if>									
									</c:if>
									<c:if test="${displayTable.txncode=='20'}">
										<c:if test="${displayTable.batch_stat=='00'}">��ֵ����</c:if>
										<c:if test="${displayTable.batch_stat=='01'}">��ֵ����</c:if>
										<c:if test="${displayTable.batch_stat=='02'}">��ֵ����</c:if>
										<c:if test="${displayTable.batch_stat=='03'}">��ֵ����</c:if>									
									</c:if>								
								</display:column>
								<display:column title="������" style="text-align:center" property="father_order" headerClass="sortable" sortable="true" />								
								<display:column title="��ʼ����" style="text-align:center" property="pan_start" headerClass="sortable" sortable="true" />
								<display:column title="��������" style="text-align:center" property="pan_end" headerClass="sortable" sortable="true" />
								<display:column title="����" style="text-align:center" property="pan_count" headerClass="sortable" sortable="true" />
								<display:column title="��ֵ���" style="text-align:center" property="amt_each_crd" headerClass="sortable" sortable="true" />
								<display:column title="֧����ʽ" style="text-align:center" property="pay_type_desc" headerClass="sortable" sortable="true" />
								<display:column title="����״̬" style="text-align:center"  headerClass="sortable" sortable="true">
									<c:if test="${displayTable.batch_stat=='00'}">�Ѵ���</c:if>
									<c:if test="${displayTable.batch_stat=='01'}">δ����</c:if>
									<c:if test="${displayTable.batch_stat=='02'}">�ѳ���</c:if>
									<c:if test="${displayTable.batch_stat=='03'}">�ѳ���</c:if>															
								</display:column>
								<display:column title="��ֵ����" style="text-align:center"  headerClass="sortable" sortable="true">
									<c:if test="${displayTable.cashin_type=='1'}">��ͨ������ֵ </c:if>	       
									<c:if test="${displayTable.cashin_type=='2'}">���ÿ����� </c:if>		  
									<c:if test="${displayTable.cashin_type=='3'}">���ּƻ���ֵ </c:if>		
									<c:if test="${displayTable.cashin_type=='4'}">�����������ֳ�ֵ </c:if>   
									<c:if test="${displayTable.cashin_type=='5'}">&nbsp; </c:if>			
									<c:if test="${displayTable.cashin_type=='6'}">���ÿ��״γ�ֵ </c:if>	
									<c:if test="${displayTable.cashin_type=='7'}">ϲ����ֵ </c:if>			
									<c:if test="${displayTable.cashin_type=='9'}">���Ż��ֳ�ֵ </c:if>		
									<c:if test="${displayTable.cashin_type=='10'}">�������ֳ�ֵ </c:if>		
									<c:if test="${displayTable.cashin_type=='11'}">ʵ���˻���ֵ </c:if>		
									<c:if test="${displayTable.cashin_type=='12'}">���ﷵ���ֳ�ֵ </c:if>																
								</display:column>								
								<display:column title="����" style="text-align:center" property="acct_period" headerClass="sortable" sortable="true" />
								<display:column title="���۵�" style="text-align:center" property="sales_point" headerClass="sortable" sortable="true" />
								<display:column title="������Ա" style="text-align:center" property="operator" headerClass="sortable" sortable="true" />
								<display:column title="��������" style="text-align:center" property="time" headerClass="sortable" sortable="true" />
								<display:column title="������" style="text-align:center" property="descr_u" headerClass="sortable" sortable="true" />
								<display:column title="��������" style="text-align:center" property="descr_t" headerClass="sortable" sortable="true" />
								<shiro:hasPermission name="cardbatch:depositAudit:edit">
									<display:column title="�鿴" style="width:5%;text-align:center">
										<a href="javascript:;" onclick="editClicks('<c:out value="${displayTable.id}"/>')">�鿴</a>
									</display:column>
								</shiro:hasPermission>
							</display:table>
							<!-- ��Diaplay Tag����ʾ -->		
					
							<input type="hidden" name="id" />
							<input type="hidden" name="selectItems" />
						</td>
						</tr>
						<tr>
							<td align="center" colspan="3">
								�ϼ�������<%=sumPanCount%>
							</td>
							<td align="center" colspan="3">
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
