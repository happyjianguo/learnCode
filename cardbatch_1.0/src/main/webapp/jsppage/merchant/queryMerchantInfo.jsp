<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%
String path = request.getContextPath();
String bus_id_pic=(String)request.getAttribute("bus_id_pic");
String bus_id_pic_path=bus_id_pic;

String tax_id_pic=(String)request.getAttribute("tax_id_pic");
String tax_id_pic_path=tax_id_pic;

String org_id_pic=(String)request.getAttribute("org_id_pic");
String org_id_pic_path=org_id_pic;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html lang="true">
<head>
	<html:base />

	<title>�̻���ѯҳ��</title>
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/style_a.css" />
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/cssem.css" />
	<script type="text/javascript" src="<%=path%>/js/checkform.js"></script>
	<script type="text/javascript" src="<%=path%>/js/textselect.js"></script>
	<script type="text/javascript" src="<%=path%>/js/md5.js"></script>
	<script type="text/javascript" src="<%=path%>/js/dwr.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
	<script src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>

</head>
<script type="text/javascript">

</script>
<body>
	<html:form styleId="merchantForm"
		action="/merchant?method=addmerchantBeanInfo"
		enctype="multipart/form-data" method="post">
		<table border="0" cellpadding="0" cellspacing="0"
			style="width: 100%; height: 99%;">
			<tr>
				<td align="center" valign="top">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td style="width: 28px; height: 10px;"></td>
						</tr>
						<tr>
							<td align="left"
								style="  width:28px; height:28px; background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">

							</td>
							<td
								style="height:28px;  background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;">
								&nbsp;&nbsp;��ǰλ�ã� �̻��ն˹��� &gt; ��ѯ�̻�
							</td>
							<td
								style=" width:7px; height:28px; background:url(<%=path%>/image1/Navigation_bar/right1.gif) ">
							</td>
						</tr>
						<tr>
							<td style="width: 28px; height: 5px" colspan="3"></td>
						</tr>
					</table>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�̻���
							</td>
							<td align="left" class="box2">
								<html:text property="mrchno" maxlength="20" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�̻�����
							</td>
							<td align="left" class="box2">
								<html:text property="mrcht_name" maxlength="16" disabled="true"></html:text>
								&nbsp;&nbsp;&nbsp;
								  ����̻����� 
								<html:select property="yard_mer_type" disabled="true">
									<html:option value="">����ѡ��</html:option>
									<html:option value="0">��Ƴ���</html:option>
								</html:select> 
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�̻�ժҪ
							</td>
							<td align="left" class="box2">
								<html:text property="mrch_snippet" maxlength="256" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								ISO��ӦMCC��
							</td>
							<td align="left" class="box2">
								<logic:present name="MCCList">
									<html:select property="acptbus" disabled="true">
										<html:option value="">����ѡ��</html:option>
										<html:optionsCollection name="MCCList" label="descr"
											value="id" />
									</html:select>
								</logic:present>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								ע���ַ��ϵ��
							</td>
							<td align="left" class="box2">
								<html:text property="contact3" maxlength="16" disabled="true"></html:text>
							</td>
						</tr>

						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�ʼĵ�ַ��ϵ�绰
							</td>
							<td align="left" class="box2">
								<html:text property="telno1" maxlength="16" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�̻�״̬
							</td>
							<td align="left" class="box2">
								<html:select property="mrchstat" disabled="true">
									<html:option value="">����ѡ��</html:option>
									<html:option value="0">����</html:option>
									<html:option value="1">������</html:option>
									<html:option value="2">������</html:option>
									<html:option value="3">����</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								����ʱ��
							</td>
							<td align="left" class="box2">
								<html:text property="enable_date" maxlength="10"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" disabled="true"></html:text>
							</td>
						</tr>		
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								ͣ��ʱ��
							</td>
							<td align="left" class="box2">
								<html:text property="disabled_date" maxlength="10"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td colspan="2" class="box1">
								�̻�������Ϣ
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�̻���ϵ��ַ
							</td>
							<td align="left" class="box2">
								<html:text property="address" maxlength="16" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								����ʱ��
							</td>
							<td align="left" class="box2">
								<html:text property="add_date" maxlength="16"
									onclick="WdatePicker({dateFmt:'yyyyMMddHHmmss'});"
									disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								ʡ��
							</td>
							<td align="left" class="box2">
								<logic:present name="provinList">
									<html:select property="province" disabled="true">
										<html:option value="">����ѡ��</html:option>
										<html:optionsCollection name="provinList"
											label="province_city" value="aid" />
									</html:select>
								</logic:present>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								����
							</td>
							<td align="left" class="box2">
								<logic:present name="city_noList">
									<html:select property="city_no" disabled="true">
										<html:option value="">����ѡ��</html:option>
										<html:optionsCollection name="city_noList"
											label="province_city" value="aid" />
									</html:select>
								</logic:present>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								����
							</td>
							<td align="left" class="box2">
								<logic:present name="zoneList">
									<html:select property="zone" disabled="true">
										<html:option value="">����ѡ��</html:option>
										<html:optionsCollection name="zoneList" label="province_city"
											value="aid" />
									</html:select>
								</logic:present>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�Ƿ����ڱ���
							</td>
							<td align="left" class="box2">
								<html:select property="is_bj" disabled="true">
									<html:option value="1">��</html:option>
									<html:option value="0">��</html:option>
								</html:select>
							</td>
						</tr>							
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�̻�����
							</td>
							<td align="left" class="box2">
								<html:select property="type_yf" disabled="true">
									<logic:present name="cardbatch_mer_typeList">
										<html:optionsCollection name="cardbatch_mer_typeList"
											label="param_name" value="param_value" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								������������������
							</td>
							<td align="left" class="box2">
								<html:text property="agent" maxlength="16" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��������������֤������
							</td>
							<td align="left" class="box2">
								<html:select property="id_type" disabled="true">
									<html:option value="1">�������֤</html:option>
									<html:option value="2">���ڱ�</html:option>
									<html:option value="3">�������֤</html:option>
									<html:option value="4">��װ�������֤</html:option>
									<html:option value="5">�����ڵ�ͨ��֤</html:option>
									<html:option value="6">������½ͨ��֤</html:option>
									<html:option value="7">����</html:option>
									<html:option value="8">����</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��������������֤������
							</td>
							<td align="left" class="box2">
								<html:text property="id_no" maxlength="20" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��������������֤����Ч��
							</td>
							<td align="left" class="box2">
								<html:text property="id_validity" maxlength="16"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								���������ˣ������ˣ�����
							</td>
							<td align="left" class="box2">
								<html:text property="legal_rep" maxlength="16" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								����������֤������
							</td>
							<td align="left" class="box2">
								<html:select property="lr_id_type" disabled="true">
									<html:option value="1">�������֤</html:option>
									<html:option value="2">���ڱ�</html:option>
									<html:option value="3">�������֤</html:option>
									<html:option value="4">��װ�������֤</html:option>
									<html:option value="5">�����ڵ�ͨ��֤</html:option>
									<html:option value="6">������½ͨ��֤</html:option>
									<html:option value="7">����</html:option>
									<html:option value="8">����</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								����������֤������
							</td>
							<td align="left" class="box2">
								<html:text property="lr_id_no" maxlength="20" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								����������֤����Ч��
							</td>
							<td align="left" class="box2">
								<html:text property="lr_id_validity" maxlength="16"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" disabled="true"></html:text>
							</td>
						</tr>						
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�عɹɶ���ʵ�ʿ���������
							</td>
							<td align="left" class="box2">
								<html:text property="man_name" maxlength="16" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�عɹɶ���ʵ�ʿ�����֤������
							</td>
							<td align="left" class="box2">
								<html:select property="id_type1" disabled="true">
									<html:option value="1">�������֤</html:option>
									<html:option value="2">���ڱ�</html:option>
									<html:option value="3">�������֤</html:option>
									<html:option value="4">��װ�������֤</html:option>
									<html:option value="5">�����ڵ�ͨ��֤</html:option>
									<html:option value="6">������½ͨ��֤</html:option>
									<html:option value="7">����</html:option>
									<html:option value="8">����</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�عɹɶ���ʵ�ʿ�����֤������
							</td>
							<td align="left" class="box2">
								<html:text property="id_no1" maxlength="20" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�عɹɶ���ʵ�ʿ�����֤����Ч��
							</td>
							<td align="left" class="box2">
								<html:text property="id_deadline1" maxlength="16"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" disabled="true"></html:text>
							</td>
						</tr>						
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								Ӫҵִ�պ�
							</td>
							<td align="left" class="box2">
								<html:text property="bus_lic_no" maxlength="30" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								Ӫҵִ������
							</td>
							<td align="left" class="box2">
								<html:text property="acc_x_name" maxlength="20" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								Ӫҵִ�����ʱ��
							</td>
							<td align="left" class="box2">
								<html:text property="bus_lic_validity" maxlength="16"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								˰��Ǽ�֤���
							</td>
							<td align="left" class="box2">
								<html:text property="tax_id" maxlength="30" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								˰��Ǽ�֤���ʱ��
							</td>
							<td align="left" class="box2">
								<html:text property="tax_id_validity" maxlength="16"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��֯����֤���
							</td>
							<td align="left" class="box2">
								<html:text property="org_id" maxlength="30" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��֯����֤���ʱ��
							</td>
							<td align="left" class="box2">
								<html:text property="org_validity" maxlength="16"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								Ӫҵִ��ͼƬ
							</td>
							<td align="left" class="box2">
								<logic:present name="bus_id_pic">
									<a href="<%=bus_id_pic_path%>"> <font color="blue">�鿴ͼƬ</font>
									</a>
								</logic:present>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								˰��Ǽ�֤ͼƬ
							</td>
							<td align="left" class="box2">
								<logic:present name="tax_id_pic">
									<a href="<%=tax_id_pic_path%>"> <font color="blue">�鿴ͼƬ</font>
									</a>
								</logic:present>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��֯����֤ͼƬ
							</td>
							<td align="left" class="box2">
								<logic:present name="org_id_pic">
									<a href="<%=org_id_pic_path%>"> <font color="blue">�鿴ͼƬ</font>
									</a>
								</logic:present>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��ҵ�˺�
							</td>
							<td align="left" class="box2">
								<html:text property="mx_accno" maxlength="16" disabled="true"></html:text>
							</td>
						</tr>

						<tr>
							<td style="width: 110px;" align="right" class="box1">
								���㼶��
							</td>
							<td align="left" class="box2">
								<html:select property="classify" disabled="true">
									<html:option value="">����ѡ��</html:option>
									<html:option value="0">�����̻�</html:option>
									<html:option value="1">�����̻�</html:option>
									<html:option value="2">�����̻�</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								���̻�
							</td>
							<td align="left" class="box2">
								<logic:present name="mrchList">
									<html:select property="fmrchno" disabled="true">
										<html:optionsCollection name="mrchList" label="name" value="mrchno" />
									</html:select>
								</logic:present>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�̻���������
							</td>
							<%-- <td align="left" class="box2">
								<html:select property="merchant_org" disabled="true">
									<html:optionsCollection name="merchantOrgList" label="orgName" value="orgId" />
								</html:select>
							</td> --%>
							<td align="left" class="box2">
								<html:select property="merchant_org" disabled="true">
									<html:option value="">����ѡ��</html:option>
									<logic:present name="merchantOrgList">
										<html:optionsCollection name="merchantOrgList" label="orgName" value="orgId" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�����������
							</td>
							<td align="left" class="box2">
								<html:select property="merchant_fenrunorg" disabled="true">
									<html:option value="">����ѡ��</html:option>
									<logic:present name="merchantFenrunOrgList">
										<html:optionsCollection name="merchantFenrunOrgList" label="orgName" value="orgId" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								����
							</td>
							<!-- <td align="left" class="box2"> -->
							<td  align="left" class="box2">
								<html:text property="merchant_email"  style="width:500px;" disabled="true" maxlength="2000"></html:text>
								<%-- <html:text property="merchant_email"  disabled="true" onchange="getOnchangeEmail();" maxlength="2000"></html:text> --%>
							</td>
						</tr>					
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								����Ա
							</td>
							<td align="left" class="box2">
								<html:text property="settlement_person" maxlength="20"  disabled="true"></html:text>
							</td>
						</tr>					
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�Ƿ��Ͷ�����ϸ
							</td>
							<td align="left" class="box2">
								<html:select property="is_send_billdetail"  disabled="true">
									<html:option value="1">��</html:option>
									<html:option value="0">��</html:option>
								</html:select>
							</td>
						</tr>	
						
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��ҵ��Ӫ��Χ
							</td>
							<td align="left" class="box2">
								<html:text property="merchant_x_operate" style="width:500px;"  maxlength="165" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�̻�����
							</td>
							<td align="left" class="box2">
								<html:select property="merchant_x_type" disabled="true">
									<html:option value="12">��λ</html:option>
									<html:option value="11">��Ȼ��</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								ע���ʱ���
							</td>
							<td align="left" class="box2">
								<html:text property="merchant_x_reg_amt" maxlength="18" disabled="true" ></html:text>  ��Ԫ  ����
								����
								<%-- <html:select property="merchant_x_code" disabled="true" >
									<html:option value="RMB">�����-RMB</html:option>
									<html:option value="USD">��Ԫ-USD</html:option>
								</html:select> --%>
								<html:select property="merchant_x_code" disabled="true">
									<logic:present name="merchant_x_code_typeList">
										<html:optionsCollection name="merchant_x_code_typeList"
											label="param_name" value="param_value" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�ڼ��ս��������Ƿ�ϲ�
							</td>
							<td align="left" class="box2">
								<html:select property="combine_flag" disabled="true">
									<html:option value="1">�ϲ�</html:option>
									<html:option value="0">���ϲ�</html:option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td colspan="2" class="box1">
								�̻�������Ϣ
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��������
							</td>
							<td align="left" class="box2">
								<html:select property="fs_cycle"  disabled="true">
									<html:option value="">����ѡ��</html:option>
									<logic:present name="fs_cycleList">
										<html:optionsCollection name="fs_cycleList"
											label="param_name" value="param_value" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�������
							</td>
							<td align="left" class="box2">
								<html:text property="fs_cycle_param" maxlength="28"  disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�ϴν�������
							</td>
							<td align="left" class="box2">
								<html:text property="last_settle_date" maxlength="16"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"  disabled="true"></html:text>
							</td>
						</tr>	
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�Ƿ��ղ�ͬ���ѳ�������
							</td>
							<td align="left" class="box2">
								<html:select property="is_consump_category"  disabled="true">
									<html:option value="0">��</html:option>
									<html:option value="1">��</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�Ƿ�ϲ����
							</td>
							<td align="left" class="box2">
								<html:select property="amt_consump_category"  disabled="true">
									<html:option value="0">��</html:option>
									<html:option value="1">��</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�Ƿ��տ�������
							</td>
							<td align="left" class="box2">
								<html:select property="is_card_type_group" disabled="true">
									<html:option value="0">��</html:option>
									<html:option value="1">��</html:option>
								</html:select>
							</td>
						</tr>						
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�Ƿ񰴸��̻�����
							</td>
							<td align="left" class="box2">
								<html:select property="is_fmrchno_decide"  disabled="true">
									<html:option value="0">��</html:option>
									<html:option value="1">��</html:option>
								</html:select>
							</td>
						</tr>								
						<tr>
							<td colspan="2" class="box1">
								�̻���Ʊ��Ϣ
							</td>
						</tr>	
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�Ƿ�Ʊ
							</td>
							<td align="left" class="box2">
								<html:select property="is_kp" disabled="true">
									<html:option value="1">��</html:option>
									<html:option value="0">��</html:option>
								</html:select>
							</td>
						</tr>											
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��Ʊ����
							</td>
							<td align="left" class="box2">
								<html:select property="fs_kp_cycle" disabled="true">
									<html:option value="">����ѡ��</html:option>
									<logic:present name="fs_kp_cycleList">
										<html:optionsCollection name="fs_kp_cycleList"
											label="param_name" value="param_value" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��Ʊ���ڲ���
							</td>
							<td align="left" class="box2">
								<html:text property="fs_kp_cycle_param" maxlength="28" disabled="true"></html:text>
							</td>
						</tr>		
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�ϴο�Ʊ����
							</td>
							<td align="left" class="box2">
								<html:text property="last_kp_date" maxlength="16"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" disabled="true"></html:text>
							</td>
						</tr>												
					</table>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td style="height: 23px;" align="center" class="box1">
									<input class="button" type="button"  value="�ر�" onClick="javascript:window.close()">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>


