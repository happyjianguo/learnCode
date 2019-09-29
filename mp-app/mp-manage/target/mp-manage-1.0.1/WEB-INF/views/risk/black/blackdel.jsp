<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>黑名单管理-删除黑名单</title>
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<li>黑名单管理</li>
				<li class="active" id="bill_check">删除黑名单</li>
			</ol>
		</div>
		<div class="mainbox">
			<div class="row">
				<form:form id="blackaddform" modelAttribute="blacklistModel" action="${ctxPath}/blackmanage/deldata" methodParam="post"
					cssClass="form-horizontal">
					<div class="form-group">
						<div class="col-sm-4">
							<form:input path="blackid"  type="hidden" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">游戏名称</label>
						<div class="col-sm-3">
							<form:input path="playname"  readonly="true" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">黑名单类型</label>
						<div class="col-sm-3 select-list" >
							<select id="blacktype" name="blacktype" class="form-control select blacktype" disabled="disabled">
								<option value="1" ${'1' eq blacklistModel.blacktype ? 'selected="selected"':''}>手机号码</option>
								<option value="2" ${'2' eq blacklistModel.blacktype ? 'selected="selected"':''}>IP</option>
								<option value="3" ${'3' eq blacklistModel.blacktype ? 'selected="selected"':''}>OPENID</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">黑名单值</label>
						<div class="col-sm-3">
							<form:input path="blackvalue" cssClass="form-control" id="blackvalue" readonly="true"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">状态</label>
						<div class="col-sm-3 select-list" >
							<select id="status" name="status" class="form-control select blacktype" disabled="disabled">
								<option value="0" ${'0' eq blacklistModel.status ? 'selected="selected"':''}>过期</option>
								<option value="1" ${'1' eq blacklistModel.status ? 'selected="selected"':''}>生效</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">过期时间</label>
						<div class="col-sm-6">
							<input type="hidden" id="begintime" name = "begintime" class="date_1 form_datetime_nl" value="" readonly="readonly"  style="margin-top:0" />     
							<div class="input-append date date2" style="float:left">
							    <input type="text" id="overtime" name = "overtime" class="date_2 form_datetime_nl" value="${blacklistModel.overtime}" readonly="readonly"  style="margin-top:0" disabled="disabled" />
							</div>							
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">说明</label>
						<div class="col-sm-3">
							<form:textarea path="remark" cssClass="form-control" cols="20" rows="10" readonly="true" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-8">
							<button type="submit" class="btn btn-primary">确定</button>
							<a href="${ctxPath}/blackmanage/init"><button type="button" class="btn default cancel" >取消</button></a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		/* $(document).ready( function () {			
			$('#blackaddform').validate({
				rules : {
					blacktype : {
						isRightfulString : true,
						notnull : true,
						required : true,
						maxlength : 60
					},
					actionid : {
						notnull:true,
						required:true,
						maxlength : 40
					}
					/* overtime : {
						notnull:true,
						required:true
					} */
					/* remark : {
						notnull:true,
						required:true
					} */
			/*	}
			});
		}); */
	</script>
</body>
</html>