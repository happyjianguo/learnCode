<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员管理-新增会员</title>
<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<li>会员管理</li>
				<li class="active" id="bill_check">会员管理</li>
			</ol>
		</div>
		<div class="mainbox">
			<div class="row">
				<form:form id="useraddform" modelAttribute="registerReviewModel" action="${ctxPath}/membermanage/adduser" methodParam="post"
					cssClass="form-horizontal">
					<div class="form-group">
						<label class="col-sm-4 control-label">手机号</label>
						<div class="col-sm-4">
						<form:input path="phoneno" cssClass="form-control" />
<!-- 							<input type="text" id="phoneno" name="phoneno" class="form-control"> -->
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">用户名</label>
						<div class="col-sm-4">
						    <form:input path="username" cssClass="form-control" />
<!--  							<input type="text" id="username" name="username" class="form-control" value=""> -->
						</div>
					</div>
					<div class="form-group">
					    <label class="col-sm-4 control-label">密码</label>
					    <div class="col-sm-4">
<%-- 					    <form:input path="passwd" cssClass="form-control" /> --%>
					      <input type="password" id="passwd" name="passwd" class="form-control" value="">
					    </div>
					  </div>
<!-- 					  <div class="form-group"> -->
<!-- 					    <label class="col-sm-4 control-label">重复新密码</label> -->
<!-- 					    <div class="col-sm-3"> -->
<!-- 					      <input type="password" id="pwd2" name="pwd2" class="form-control"> -->
<!-- 					    </div> -->
<!-- 					  </div> -->
					<div class="form-group">
						<label class="col-sm-4 control-label">姓名</label>
						<div class="col-sm-4">
						 <form:input path="cname" cssClass="form-control" />
<!-- 							<input type="text" id="cname" name="cname" class="form-control"> -->
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">性别</label>
						<div class="col-sm-4">
							<select id="mf" name="mf" class="form-control select" >
								<option value="1">男</option>
								<option value="2">女</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">邮箱</label>
						<div class="col-sm-4">
						<form:input path="email" cssClass="form-control" />
<!-- 							<input type="text" id="email" name="email" class="form-control"> -->
						</div>
					</div>
					<div class="form-group">
								<label class="col-sm-4 control-label">用户状态</label>
								<div class="col-sm-4">
										<select id="status" name="status" class="form-control select">
									      	<option value="0">失效</option>
											<option value="1" selected="selected">正常</option>
											<option value="2">冻结</option>
									    </select>
								</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">所属机构</label>
							<div class="col-sm-4">
								<select id="orgid" name="orgid" class="form-control select" >
									<option value="">-请选择-</option> 
									<c:forEach items="${registerReviewModel.cloudpforgList}" var="showList">
										<option value="${showList.id}">${showList.orgname}</option> 
									</c:forEach>
								</select>
							</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-8">
							<button type="submit" class="btn btn-primary">确定</button>
							<a href="${ctxPath}/membermanage/init"><button type="button" class="btn default cancel" >取消</button></a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
	<script type="text/javascript">
		$(document).ready( function () {			
			$('#useraddform').validate({
				rules : {
					username : {
						pwsCheck:true,
						notnull : true,
						required : true,
						maxlength : 20
					},
					cname : {
						isRightfulString : true,
						notnull : true,
						required : true,
						pwdMaxlength : 20
					},
					passwd : {
						notnull : true,
						required : true,
						pwdMaxlength : 30,
						pwdMinlength : 6
					},					
					phoneno : {
						mobile : true,
						notnull : true,
						required : true,
						maxlength : 11
					},
					email : {
						email : true,
						notnull:true,
						required:true,
						maxlength : 40
					},
					orgid : {
						notnull:true,
						required:true
					}
				}
			});
		});
	</script>
</body>
</html>