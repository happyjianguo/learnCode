<%@ page contentType="text/html;charset=GBK" isErrorPage="true"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page import="cn.yufu.posp.common.util.ExpUtil"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<title>�յ�ϵͳ����ƽ̨</title>
<style type="text/css">
<!--
.mud {
	FONT-SIZE: 12px;
	LINE-HEIGHT: 19px
}

.style1 {
	color: #0483B2;
	FONT-SIZE: 20px;
}

.style2 {
	color: #0483B2;
	FONT-SIZE: 12px;
}
-->
</style>
	</head>
	<body>
		<table width="98%" border="0" cellspacing="0" cellpadding="0" class="mud">
			<tr>
				<td width="17%">
					<img src="<fmt:message key='CommonImagePath' />warn.png"
						width="120" height="119">
				</td>
				<td width="4%" align="center">
					<img height=67 alt=""
						src="<fmt:message key='CommonImagePath' />bodybg.gif" width=1>
				</td>
				<td width="79%">
					<table height=51 cellSpacing=3 cellPadding=0 width="95%"
						align=center border=0>
						<tbody>
							<tr>
								<td>
									<table cellSpacing=2 cellPadding=0 width="87%" border=0>
										<tbody>
											<tr>
												<td width="97%">
													<strong><font class="style1">�����ʵĹ��ܳ����쳣 
													</strong></font>
												</td>
											</tr>
											<tr>
												<td colSpan=2>
													<img height=1 alt=""
														src="<fmt:message key='CommonImagePath' />szt1-1_75.gif"
														width=367>
												</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table cellSpacing=2 cellPadding=0 width="87%" border=0>
										<tbody>
											<TR>
												<TD width="3%">
													<img src="<fmt:message key='CommonImagePath' />bullet.gif">
												</TD>
												<TD width="97%">
													<strong><font class="style2">��鿴�Ƿ������������� 
													</strong></font>
												</TD>
											</TR>
											<tr>
												<td width="3%">
													<img src="<fmt:message key='CommonImagePath' />bullet.gif">
												</td>
												<td width="97%" height="20px;">
													1.ֱ�����������ַ������URL����
												</td>
											</tr>
											<tr>
												<td width="3%">
													<img src="<fmt:message key='CommonImagePath' />bullet.gif">
												</td>
												<td width="97%" height="20px;">
													2.�����Ƿ�����
												</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td width="17%"></td>
				<td width="4%" align="center"></td>
				<td width="79%">
					<table height=51 cellSpacing=3 cellPadding=0 width="95%"
						align=center border=0>
						<tbody>
							<tr>
								<td>
									<table cellSpacing=2 cellPadding=0 width="87%" border=0>
										<tbody>
											<tr>
												<td width="97%">
													<strong><font class="style1">�쳣��Ϣ: 
													</strong></font>
												</td>
											</tr>
											<tr>
												<td colSpan=2>
													<img height=1 alt=""
														src="<fmt:message key='CommonImagePath' />szt1-1_75.gif"
														width=367>
												</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table cellSpacing=2 cellPadding=0 width="87%" border=0>
										<tbody>
											<tr>
												<td width="3%">
													<img src="<fmt:message key='CommonImagePath' />bullet.gif">
												</td>
												<td width="97%" height="20px;">
													�쳣���룺<%=request.getAttribute("javax.servlet.error.status_code")%></td>
											</tr>
											<tr>
												<td width="3%">
													<img src="<fmt:message key='CommonImagePath' />bullet.gif">
												</td>
												<td width="97%" height="20px;">
													�쳣���ͣ�<%=request.getAttribute("javax.servlet.error.exception_type")%></td>
											</tr>
											<tr>
												<td width="3%">
													<img src="<fmt:message key='CommonImagePath' />bullet.gif">
												</td>
												<td width="97%" height="20px;">
													�쳣������<%=ExpUtil.getTrsMessage(exception)%></td>
											</tr>
											<tr>
												<td width="3%">
													<img src="<fmt:message key='CommonImagePath' />bullet.gif">
												</td>
												<td width="97%" height="20px;">
													�쳣���ݣ�<%=ExpUtil.outputExceptionToJsp(exception)%></td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</table>

	</body>
</html>