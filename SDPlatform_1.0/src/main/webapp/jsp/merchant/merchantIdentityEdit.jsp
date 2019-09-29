<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.*" isELIgnored="false"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
	<title>�̻������Ϣ</title>
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
	<script type="text/javascript" language="JavaScript" src="<fmt:message key='JavaScriptPath' />jquery-1.4.min.js" ></script>
	<script type="text/javascript" language="JavaScript" src="<fmt:message key='JavaScriptPath' />DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript" language="JavaScript" src="<fmt:message key='JavaScriptPath' />ajaxfileupload.js" ></script>
	<script language="javascript">
	
	function saveClick()
	{
		document.forms[0].method.value="saveItem";
		var biFile =$("input[name='biFile']").val()  
		var extend = biFile.substring(biFile.lastIndexOf(".")+1); 
	    if(extend==""){ 
	    }else{ 
			if(!(extend=="jpg"||extend=="jpeg"||extend=="gif"||extend=="png"||extend=="bmp"||
					extend=="JPG"||extend=="JPEG"||extend=="GIF"||extend=="PNG"||extend=="BMP")){ 
			   alert("���ϴ�ͼƬ��ʽ��"); 
			   return false;
			} 
	    }
		return validateMerchantIdentityForm(document.forms[0]);
	}
	function backClick()
	{
		document.forms[0].method.value="queryAll";
	}
  	function showPic(id)
  	{
		window.showModalDialog("merchantIdentity.do?method=downFile&path="+id, "_blank", 'dialogWidth=800px;dialogHeight=440px;scroll=yes;resizable=no;status=no;center=yes;');
  	}
	</script>
</head>
<shiro:lacksPermission name="posp:merchantIdentity:edit">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:merchantIdentity:edit">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle"> 
				�޸��̻����
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="merchantIdentityForm" />
				<html:errors />
				<html:form action="/merchantIdentity" enctype="multipart/form-data">

					<html:hidden property="method" value="saveItem" />
					
					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title">
								�̻����:
							</td>
							<td class="table2_td">
								<html:text property="merchantId" size="30" maxlength="15" disabled="true" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
							    <html:hidden property="merchantId" />
							</td>
							<td class="table2_td_title">
								�̻���:
							</td>
							<td class="table2_td">
								<html:text property="merchantCname" size="30" maxlength="15" disabled="true" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
							    <html:hidden property="merchantCname" />
							</td>
						</tr>
						<tr height="25" class="table2_td_title"><td colspan="4"><b>Ӫҵִ��/��ҵ��λ����֤��</b></td></tr>
	
						<tr>
							<td class="table2_td_title">
								֤������:
							</td>
							<td class="table2_td">
								<html:text property="biCardNo" size="30" maxlength="20"/>
							</td>
							<td class="table2_td_title" rowspan="3" colspan="2">
							<c:if test="${!empty merchantIdentityForm.biCardPic}" >
								<a href="javascript:;" onclick="showPic('${merchantIdentityForm.biCardPic}')"><img alt="Ӫҵִ��" width="200px" height="100px" src="merchantIdentity.do?method=downFile&path=${merchantIdentityForm.biCardPic}"></a>
							</c:if>
							<c:if test="${empty merchantIdentityForm.biCardPic}" >
								<img alt="Ӫҵִ��" width="200px" height="100px" src="merchantIdentity.do?method=downFile&path=404.gif">
							</c:if>	
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								֤����Ч��:
							</td>
							<td class="table2_td">
								<html:text property="biCardDate" onclick="WdatePicker({dateFmt:'yyyyMMdd'})" styleClass="Wdate" readonly="true"/>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								֤���ϴ�:
							</td>
							<td class="table2_td">
								<html:file property="biFile" />
								<html:hidden property="biCardPic"/>
							</td>
						</tr>
						<tr height="25" class="table2_td_title"><td colspan="4"><b>˰��Ǽ�֤</b></td></tr>
						<tr>
							<td class="table2_td_title">
								֤������:
							</td>
							<td class="table2_td">
								<html:text property="taxCardNo" size="30" maxlength="20"/>
							</td>
							<td class="table2_td_title" rowspan="3" colspan="2">
							<c:if test="${!empty merchantIdentityForm.taxCardPic}" >
								<a href="javascript:;" onclick="showPic('${merchantIdentityForm.taxCardPic}')"><img alt="˰��Ǽ�֤" width="200px" height="100px" src="merchantIdentity.do?method=downFile&path=${merchantIdentityForm.taxCardPic}"></a>
							</c:if>
							<c:if test="${empty merchantIdentityForm.taxCardPic}" >
								<img alt="˰��Ǽ�֤" width="200px" height="100px" src="merchantIdentity.do?method=downFile&path=404.gif">
							</c:if>	
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								֤����Ч��:
							</td>
							<td class="table2_td">
								<html:text property="taxCardDate" onclick="WdatePicker({dateFmt:'yyyyMMdd'})" styleClass="Wdate" readonly="true"/>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								֤���ϴ�:
							</td>
							<td class="table2_td">
								<html:file property="taxFile" />
								<html:hidden property="taxCardPic"/>
							</td>
						</tr>
						<tr height="25" class="table2_td_title"><td colspan="4"><b>��֯��������</b></td></tr>
						<tr>
							<td class="table2_td_title">
								֤������:
							</td>
							<td class="table2_td">
								<html:text property="orgCardNo" size="30" maxlength="20"/>
							</td>
							<td class="table2_td_title" rowspan="3" colspan="2">
							<c:if test="${!empty merchantIdentityForm.orgCardPic}" >
								<a href="javascript:;" onclick="showPic('${merchantIdentityForm.orgCardPic}')"><img alt="��֯��������" width="200px" height="100px" src="merchantIdentity.do?method=downFile&path=${merchantIdentityForm.orgCardPic}"></a>
							</c:if>
							<c:if test="${empty merchantIdentityForm.orgCardPic}" >
								<img alt="��֯��������" width="200px" height="100px" src="merchantIdentity.do?method=downFile&path=404.gif">
							</c:if>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								֤����Ч��:
							</td>
							<td class="table2_td">
								<html:text property="orgCardDate" onclick="WdatePicker({dateFmt:'yyyyMMdd'})" styleClass="Wdate" readonly="true"/>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								֤���ϴ�:
							</td>
							<td class="table2_td">
								<html:file property="orgFile" />
								<html:hidden property="orgCardPic"/>
							</td>
						</tr>						
						<tr height="25" class="table2_td_title"><td colspan="4"><b>�عɹɶ���ʵ�ʿ�����</b></td></tr>
						<tr>
							<td class="table2_td_title">
								����:
							</td>
							<td class="table2_td">
								<html:text property="shareName" size="30" maxlength="20" />
							</td>
							<td class="table2_td_title">
								֤������:
							</td>
							<td class="table2_td">
								<html:select property="shareCardType" style="width:195px;" value="${merchantIdentityForm.shareCardType}">
									<html:option value="0">���֤</html:option>
									<html:option value="1">����</html:option>
									<html:option value="2">��ʱ���֤</html:option>
									<html:option value="3">���ڱ�</html:option>
									<html:option value="4">����֤</html:option>
									<html:option value="5">�侯֤</html:option>
									<html:option value="6">�۰ľ��������ڵ�ͨ��֤</html:option>
									<html:option value="7">̨�����������½ͨ��֤</html:option>
									<html:option value="8">������Ч����֤��</html:option>
									<html:option value="9">������Ч֤��</html:option>
								</html:select>							
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								֤������:
							</td>
							<td class="table2_td">
								<html:text property="shareCardNo" size="30" maxlength="20"/>
					        </td>
							<td class="table2_td_title">
								֤����Ч��:
							</td>
							<td class="table2_td">
								<html:text property="shareCardDate" onclick="WdatePicker({dateFmt:'yyyyMMdd'})" styleClass="Wdate" readonly="true" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��ϵ��ʽ:
							</td>
							<td class="table2_td">
								<html:text property="shareCardTel" size="30" maxlength="20"/>
					        </td>
							<td class="table2_td_title">
							</td>
							<td class="table2_td">
							</td>
						</tr>
						<tr height="25" class="table2_td_title"><td colspan="4"><b>���������ˣ������ˣ�</b></td></tr>
						<tr>
							<td class="table2_td_title">
								����:
							</td>
							<td class="table2_td">
								<html:text property="legalName" size="30" maxlength="20"/>
							</td>
							<td class="table2_td_title">
								֤������:
							</td>
							<td class="table2_td">
								<html:select property="legalCardType" style="width:195px;" value="${merchantIdentityForm.legalCardType}">
									<html:option value="0">���֤</html:option>
									<html:option value="1">����</html:option>
									<html:option value="2">��ʱ���֤</html:option>
									<html:option value="3">���ڱ�</html:option>
									<html:option value="4">����֤</html:option>
									<html:option value="5">�侯֤</html:option>
									<html:option value="6">�۰ľ��������ڵ�ͨ��֤</html:option>
									<html:option value="7">̨�����������½ͨ��֤</html:option>
									<html:option value="8">������Ч����֤��</html:option>
									<html:option value="9">������Ч֤��</html:option>
								</html:select>							
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								֤������:
							</td>
							<td class="table2_td">
								<html:text property="legalCardNo" size="30" maxlength="20"/>
					        </td>
							<td class="table2_td_title">
								֤����Ч��:
							</td>
							<td class="table2_td">
								<html:text property="legalCardDate" onclick="WdatePicker({dateFmt:'yyyyMMdd'})" styleClass="Wdate" readonly="true" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��ϵ��ʽ:
							</td>
							<td class="table2_td">
								<html:text property="legalCardTel" size="30" maxlength="20"/>
					        </td>
							<td class="table2_td_title">
							</td>
							<td class="table2_td">
							</td>
						</tr>
						<tr height="25" class="table2_td_title"><td colspan="4"><b>��Ȩ����������</b></td></tr>
						<tr>
							<td class="table2_td_title">
								����:
							</td>
							<td class="table2_td">
								<html:text property="attnName" size="30" maxlength="20"/>
							</td>
							<td class="table2_td_title">
								֤������:
							</td>
							<td class="table2_td">
								<html:select property="attnCardType" style="width:195px;" value="${merchantIdentityForm.attnCardType}">
									<html:option value="0">���֤</html:option>
									<html:option value="1">����</html:option>
									<html:option value="2">��ʱ���֤</html:option>
									<html:option value="3">���ڱ�</html:option>
									<html:option value="4">����֤</html:option>
									<html:option value="5">�侯֤</html:option>
									<html:option value="6">�۰ľ��������ڵ�ͨ��֤</html:option>
									<html:option value="7">̨�����������½ͨ��֤</html:option>
									<html:option value="8">������Ч����֤��</html:option>
									<html:option value="9">������Ч֤��</html:option>
								</html:select>							
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								֤������:
							</td>
							<td class="table2_td">
								<html:text property="attnCardNo" size="30" maxlength="20"/>
					        </td>
							<td class="table2_td_title">
								֤����Ч��:
							</td>
							<td class="table2_td">
								<html:text property="legalCardDate" onclick="WdatePicker({dateFmt:'yyyyMMdd'})" styleClass="Wdate" readonly="true" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��ϵ��ʽ:
							</td>
							<td class="table2_td">
								<html:text property="attnCardTel" size="30" maxlength="20"/>
					        </td>
							<td class="table2_td_title">
							</td>
							<td class="table2_td">
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�̻��ȼ�:
							</td>
							<td class="table2_td">
								<html:select property="classType" style="width:195px;"  value="${merchantIdentityForm.classType}">
									<html:option value="A">A</html:option>
									<html:option value="B">B</html:option>
									<html:option value="C">C</html:option>
									<html:option value="D">D</html:option>
								</html:select>					        
							</td>
							<td class="table2_td_title">
								�޸�ԭ��
							</td>
							<td class="table2_td">
								<html:textarea property="reason"></html:textarea>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4" class="table2_btn">
								<input type="image" src="<fmt:message key='CommonImagePath' />btnSave.gif" onsubmit="saveClick();return false;">
								&nbsp;
								<input type="image" src="<fmt:message key='CommonImagePath' />btnBack.gif" onclick="backClick();">
							</td>
						</tr>
					</table>
					<INPUT TYPE="hidden" name="mccMark" value="N">
					<INPUT TYPE="hidden" name="divflag" id="divflag" value="N">
					<!-- ά����ͼ״̬�������� -->
					<%@include file="../common/getDisplayParams1.jsp"%>
					<!-- ά����ͼ״̬�������� -->
				</html:form>

			</td>
		</tr>
	</table>



</body>
</shiro:hasPermission>
</html:html>



