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
				<li class="active" id="bill_check">参数管理</li>
			</ol>
		</div>
		<div class="mainbox">
			<div class="row">
				<form:form id="editform" modelAttribute="pubsSysBase" action="${ctxPath}/base/update" methodParam="post"
					cssClass="form-horizontal">
					<div class="form-group">
						<label class="col-sm-4 control-label">参数名称</label>
						<div class="col-sm-4">
							<form:input path="sysBaseId" cssClass="form-control" readonly="true"/>
<!-- 							<input type="text" disabled="disabled" class="form-control sysBaseIdU" name="sysBaseIdU" value=""> -->
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">参数值</label>
						<div class="col-sm-4">
							<form:input path="sysBaseValue" cssClass="form-control"/>
<!-- 							<input type="text" class="form-control sysBaseValueU" name="sysBaseValueU"> -->
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">参数描述</label>
						<div class="col-sm-4">
							<form:input path="sysBaseMemo" cssClass="form-control" readonly="true"/>
<!-- 							<input type="text" class="form-control sysBaseMemoU" name="sysBaseMemoU"> -->
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-8">
							<button type="submit" class="btn btn-primary">确定</button>
							<a href="${ctxPath}/base/init"><button type="button" class="btn default cancel" >取消</button></a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
	<script type="text/javascript">
		$(document).ready( function () {
			$('#editform').validate({
				rules : {
					sysBaseId : {
						required : true,
						maxlength : 32
					},
					sysBaseValue : {
						required : true,
						maxlength : 500
					},
					sysBaseMemo : {						
						required : true,
						maxlength : 120
					}
				}
			});
		});
	</script>
</body>
</html>