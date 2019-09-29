<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>公告管理-发布公告</title>
<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<li>公告管理</li>
				<li class="active" id="bill_check">发布公告</li>
			</ol>
		</div>
		<div class="mainbox">
			<div class="row">
				<form:form id="noticeeditform" modelAttribute="noticeManageModel" action="${ctxPath}/notice/fabudata" methodParam="post"
					cssClass="form-horizontal">
					<div class="form-group">
						<div class="col-sm-3">
							<form:input path="noticeid" type="hidden" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">公告名</label>
						<div class="col-sm-3">
							<form:input path="noticename" cssClass="form-control" readonly="true"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">公告值</label>
						<div class="col-sm-3">
							<form:textarea path="noticevalue" cssClass="form-control" cols="20" rows="10" readonly="true"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">状态</label>
						<div class="col-sm-3">
						      <select id="status" name="status" class="form-control select" disabled="disabled">
						      	<option value="0" ${'0' eq noticeManageModel.status ? 'selected="selected"':''}>未发布</option>
						      </select>
						    </div>
					</div>
					<div class="form-group">
							<label class="col-sm-4 control-label">起止时间</label>
							<div class="col-sm-6">
								<div class="input-append date date1" style="float:left; width:130px;">								 
								    <input type="text" id="begintime" name = "begintime" class="date_1 form_datetime_nl" value="${noticeManageModel.begintime}" disabled="disabled" readonly="readonly"  style="margin-top:0">     
								</div>						
								<div class="input-append date date2" style="float:left">
									<span class="span_d">至</span>
								    <input type="text" id="endtime" name = "endtime" class="date_2 form_datetime_nl" value="${noticeManageModel.endtime}" disabled="disabled" readonly="readonly"  style="margin-top:0"/>
								</div>							
							</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-8">
							<button type="submit" class="btn btn-primary">确定</button>
							<a href="${ctxPath}/notice/init"><button type="button" class="btn default cancel" >取消</button></a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>