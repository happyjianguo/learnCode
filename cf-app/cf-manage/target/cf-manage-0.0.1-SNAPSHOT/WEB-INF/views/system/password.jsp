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
				<li class="active" id="bill_check">修改密码</li>
			</ol>
		</div>
		<div class="mainbox">
			<form:form id="eform" action="${ctxPath}/password/edit" method="post" class="form-horizontal"> 	 
			  <div class="form-group">
			    <label class="col-sm-4 control-label">原密码</label>
			    <div class="col-sm-3">
			      <input type="password" id="opwd" name="opwd" class="form-control">
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-4 control-label">新密码</label>
			    <div class="col-sm-3">
			      <input type="password" id="pwd1" name="pwd1" class="form-control">
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-4 control-label">重复新密码</label>
			    <div class="col-sm-3">
			      <input type="password" id="pwd2" name="pwd2" class="form-control">
			    </div>
			  </div>
			  <div class="col-sm-offset-4 col-sm-8
			  ">
			  	<button type="submit" class="btn btn-primary" >确定</button>
			  </div>  
			</form:form>
		</div>			
	</div>
		<script type="text/javascript">
		//修改表单验证，提交
			$(document).ready(function() {
				$('#eform').validate({
					//debug: true,
					rules : {
						opwd : {
							pwsCheck: true,
							required : true,
							maxlength: 30,
							minlength: 6
						},
						pwd1 : {
							pwsCheck: true,
							required : true,
							maxlength: 30,
							minlength: 6
						},
						pwd2 : {
							pwsCheck: true,
							required : true,
							maxlength: 30,
							minlength: 6
						}
					}
				});
			});
		</script>
	</body>
</html>