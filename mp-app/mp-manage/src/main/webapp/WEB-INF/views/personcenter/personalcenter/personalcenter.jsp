<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<title>内管系统-密码修改</title>
		
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>
		<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<li>个人中心</li>
				<li class="active" id="bill_check">个人信息</li>
			</ol>
		</div>
		<div class="mainbox">
			<div class="row">
				<form:form id="usereditform" modelAttribute="userManageModel" action="${ctxPath}/personalcenter/update" methodParam="post"
					cssClass="form-horizontal">
					<div class="form-group">
						<div class="col-sm-4">
							<form:input path="userid" type="hidden" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">用户名</label>
						<div class="col-sm-3">
							<form:input path="username" cssClass="form-control" readonly="true"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">姓名</label>
						<div class="col-sm-3">
							<form:input path="cname" cssClass="form-control" readonly="true"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">手机号</label>
						<div class="col-sm-3">
							<form:input path="phoneno" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">邮箱</label>
						<div class="col-sm-3">
							<form:input path="email" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">角色名</label>
						<div class="col-sm-3">
						<p class="form-control-static">
							<c:forEach var="role" items="${userManageModel.roleList}" varStatus="vs">
								<c:if test="${!vs.first}">,</c:if>
								${role.roleName}
							</c:forEach>
						</p>
							
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-8">
							<button type="submit" class="btn btn-primary">确定</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
		<script type="text/javascript">
		//修改表单验证，提交
			$(document).ready(function() {
				$('#usereditform').validate({
					rules : {
						phoneno : {
							mobile : true,
							required : true
						},
						email : {
							email : true,
							required : true,
							maxlength : 40
						}
					}
				});
			});
		</script>
	</body>
</html>