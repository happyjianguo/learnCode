<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>系统管理-修改用户</title>
<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<li>系统管理</li>
				<li class="active" id="bill_check">用户管理</li>
			</ol>
		</div>
		<div class="mainbox">
			<div class="row">
				<form:form id="usereditform" modelAttribute="userManageModel" action="${ctxPath}/imuser/editdata" methodParam="post"
					cssClass="form-horizontal">
					<div class="form-group">
						<div class="col-sm-4">
							<form:input path="userid" type="hidden" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">用户名</label>
						<div class="col-sm-4">
							<form:input path="username" cssClass="form-control" readonly="true"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">姓名</label>
						<div class="col-sm-4">
							<form:input path="cname" cssClass="form-control" />
							<!-- <input type="email" class="form-control" id="inputEmail3" placeholder="Email"> -->
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">手机号</label>
						<div class="col-sm-4">
							<form:input path="phoneno" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">邮箱</label>
						<div class="col-sm-4">
							<form:input path="email" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">用户角色</label>
						<div class="col-sm-4">
							<select id="roleid" name="roleids" class="form-control selectpicker" multiple="multiple">
								<c:forEach var="role" items="${userManageModel.roleList}">								  
								   <option value="${role.roleId}"  <c:forEach var="roleid" items="${userManageModel.roleids}">
								   ${roleid eq role.roleId  ? 'selected="selected"':''}
								   </c:forEach>										
									>${role.roleName}</option>																	
								   	</c:forEach>																																																													
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-8">
							<button type="submit" formaction="${ctxPath}/imuser/initPwd" form="usereditform" class="btn btn-primary">重置密码</button>
							<button type="submit" class="btn btn-primary">确定</button>
							<a href="${ctxPath}/imuser/init"><button type="button" class="btn default cancel" >取消</button></a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
<%-- 	<%@ include file="/WEB-INF/views/common/conmm.jsp"%> --%>
	<script type="text/javascript">
		//新增管理员表单验证，提交
		$(document).ready( function () {
			$('#usereditform').validate({
				rules : {
					username : {
						isRightfulString : true,
						notnull : true,
						required : true,
						maxlength : 20
					},
					cname : {
						isRightfulString : true,
						notnull : true,
						required : true,
						maxlength : 60
					},
					phoneno : {
						mobile : true
					},
					email : {
						email : true,
						notnull:true,
						required:true,
						maxlength : 40
					}
				}
			});
		});
	</script>
</body>
</html>