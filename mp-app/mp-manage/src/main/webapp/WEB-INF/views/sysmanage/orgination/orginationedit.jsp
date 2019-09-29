<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>机构管理-修改机构</title>
<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<li>机构管理</li>
				<li class="active" id="bill_check">修改机构</li>
			</ol>
		</div>
		<div class="mainbox">
			<div class="row">
				<form:form id="orginationeditform" modelAttribute="cloudplatformorginationModel" action="${ctxPath}/orgination/editdata" methodParam="post"
					cssClass="form-horizontal">
					<div class="form-group">
						<div class="col-sm-4">
							<form:input path="id" type="hidden" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">机构名</label>
						<div class="col-sm-4">
							<form:input path="orgname" cssClass="form-control" readonly="true"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">机构状态</label>
							<div class="col-sm-4">
							      <select id="orgstatus" name="orgstatus" class="form-control select">
							      	<option value="0" ${'0' eq cloudplatformorginationModel.orgstatus ? 'selected="selected"':''} >未生效</option>
									<option value="1" ${'1' eq cloudplatformorginationModel.orgstatus ? 'selected="selected"':''} >生效</option>
							      </select>
							</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">父级机构</label> 
						<div class="col-sm-4">
							<form:input path="parentorgname" cssClass="form-control" readonly="true"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">机构备注</label>
						<div class="col-sm-4">
							<form:textarea path="note" cssClass="form-control" cols="20" rows="10" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-8">
							<button type="submit" class="btn btn-primary">确定</button>
							<a href="${ctxPath}/orgination/init"><button type="button" class="btn default cancel" >取消</button></a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
	<script type="text/javascript">
		$(document).ready( function () {			
			$('#orginationeditform').validate({
				rules : {
					orgname : {
						notnull : true,
						isRightfulString :true,
						qlength : 50
					},
					note : {
						maxlengthname : 395
					}
				}
			});
		});
	</script>
</body>
</html>