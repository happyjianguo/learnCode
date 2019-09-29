<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>活动模板管理-修改活动模板</title>
<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<li>活动模板管理</li>
				<li class="active" id="bill_check">修改活动模板</li>
			</ol>
		</div>
		<div class="mainbox">
					<div class="tablebox">
			<div class="row">
				<form:form id="form" modelAttribute="cloudGameTemplateModel" action="${ctxPath}/gametemplate/editdata" methodParam="post"
					cssClass="form-horizontal">
					<div class="form-group">
						<div class="col-sm-3">
							<form:input path="tempid" type="hidden" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">文件名称</label>
						<div class="col-sm-3">
							<form:input id="tempname" path="tempname" readonly="true" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">模版名称</label>
						<div class="col-sm-3">
							<form:input id="templatename" path="templatename" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">图片</label>
						<div class="col-sm-3">
							<form:input id="pictrueurl" path="pictrueurl" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">使用次数</label>
						<div class="col-sm-3">
							<form:input id="usetimes" path="usetimes" readonly="true" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">游戏编号</label>
						<div class="col-sm-3">
							<form:input id="gameid" path="gameid" readonly="true" cssClass="form-control" />
						</div>
					</div>
					<%-- 
					<div class="form-group">
						<label class="col-sm-4 control-label">创建时间</label>
						<div class="col-sm-3">
							<form:input id="createtime" path="createtime" readonly="true" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">更新时间</label>
						<div class="col-sm-3">
							<form:input id="updatetime" path="updatetime" readonly="true" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">状态</label>
						<div class="col-sm-3">
							<form:input id="tempstatus" path="tempstatus" readonly="true" cssClass="form-control" />
						</div>
					</div> 
					--%>
					
					<div class="form-group">
						<label class="col-sm-4 control-label">状态</label>
						<div class="col-sm-3">
						      <select id="tempstatus" name="tempstatus" class="form-control select">
						      	<option value="0" ${'0' eq cloudGameTemplateModel.tempstatus ? 'selected="selected"':''}>未生效</option>
								<option value="1" ${'1' eq cloudGameTemplateModel.tempstatus ? 'selected="selected"':''}>生效</option>
						      </select>
						</div>
					</div> 
					
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-8">
							<button type="submit" class="btn btn-primary">确定</button>
							<!-- <button id="cancel" type="button" class="btn default cancel" >取消</button> -->
						</div>
					</div>
				</form:form>
			</div>
		</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
	<script type="text/javascript">
		$(document).ready( function () {
			/* $("#cancel").on("click",function(){
				alert('ss');
		    	submiturl("${ctxPath}/gameinit/editinfo",$(this).attr("data-val"));
		    }); */
			var validator = $('#form').validate({
				rules : {
				}
			});
		});
	</script>
</body>
</html>