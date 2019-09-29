<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>游戏模板分类管理-新增游戏模板分类</title>
<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<li>游戏模板分类管理</li>
				<li class="active" id="bill_check">新增游戏模板分类</li>
			</ol>
		</div>
		<div class="mainbox">
			<div class="row">
				<form:form id="addform" modelAttribute="cloudGameTemplateTypeModel" action="${ctxPath}/gametmptype/adddata" methodParam="post"
					cssClass="form-horizontal">
					<div class="form-group">
						<label class="col-sm-4 control-label">游戏名称</label>
						<div class="col-sm-3 select-list">
							<select id="gameid" name="gameid" class="form-control select actionid" >
								<option value="">请选择</option>
								<c:forEach items="${cloudGameTemplateTypeModel.game }" var="gamelist">
									<option value="${gamelist.id }">${gamelist.playname}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">模版分类名称</label>
						<div class="col-sm-3">
							<form:input path="tmptypename" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-8">
							<button type="submit" class="btn btn-primary">确定</button>
							<a href="${ctxPath}/gametmptype/init"><button type="button" class="btn default cancel" >取消</button></a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
	<script type="text/javascript">
		$(document).ready( function () {
			$('#addform').validate({
				rules : {
					tmptypename : {
						notnull : true,
						required : true,
						qlength : 20
					},
					gameid : {
						notnull : true,
						required : true
					}
				}
			});
		});
	</script>
</body>
</html>