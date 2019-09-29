<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>活动管理-删除活动</title>
<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<li>活动管理</li>
				<li class="active" id="bill_check">删除活动</li>
			</ol>
		</div>
		<div class="mainbox">
			<div class="row">
				<form:form id="useraddform" modelAttribute="cloudGameInitModel" action="${ctxPath}/gameinit/deldata" methodParam="post"
					cssClass="form-horizontal">
					<div class="form-group">
						<label class="col-sm-4 control-label">游戏ID</label>
						<div class="col-sm-4">
						 <form:input path="id" cssClass="form-control" readonly="true"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">游戏名称</label>
						<div class="col-sm-4">
							<form:input path="playname" cssClass="form-control" readonly="true"/>
						</div>
					</div>
					<div class="form-group">
					    <label class="col-sm-4 control-label">序号</label>
					    <div class="col-sm-4">
					      <form:input path="indexnum" cssClass="form-control" readonly="true"/>
					    </div>
					  </div>
					<div class="form-group">
						<label class="col-sm-4 control-label">渠道</label>
						<div class="col-sm-4">
							<select id="channel" name="channel" class="form-control select" disabled="disabled">
							      	<option value="0" ${'0' eq cloudGameInitModel.channel ? 'selected="selected"':''} >微信</option>
									<option value="1" ${'1' eq cloudGameInitModel.channel ? 'selected="selected"':''} >APP</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">路径</label>
						<div class="col-sm-4">
							<form:input path="deploypath" cssClass="form-control" readonly="true"/>
						</div>
					</div>
					<div class="form-group">
								<label class="col-sm-4 control-label">状态</label>
								<div class="col-sm-4">
									<select id="status" name="status" class="form-control select" disabled="disabled">
								      	<option value="0" ${'0' eq cloudGameInitModel.status ? 'selected="selected"':''} >未上线</option>
										<option value="1" ${'1' eq cloudGameInitModel.status ? 'selected="selected"':''} >上线</option>
										<option value="2" ${'2' eq cloudGameInitModel.status ? 'selected="selected"':''} >下线</option>
										<option value="3" ${'3' eq cloudGameInitModel.status ? 'selected="selected"':''} >推荐</option>
									</select>
								</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-8">
							<button type="submit" class="btn btn-primary">确定</button>
							<a href="${ctxPath}/gameinit/init"><button type="button" class="btn default cancel" >取消</button></a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
	<script type="text/javascript">
		$(document).ready( function () {			
			
		});
	</script>
</body>
</html>