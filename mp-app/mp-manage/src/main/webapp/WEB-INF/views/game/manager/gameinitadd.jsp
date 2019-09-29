<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>活动管理-新增活动</title>
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
				<li class="active" id="bill_check">新增活动</li>
			</ol>
		</div>
		<div class="mainbox">
			<div class="row">
				<form:form id="addform" modelAttribute="cloudGameInitModel" action="${ctxPath}/gameinit/addgame" methodParam="post"
					cssClass="form-horizontal">
					<div class="form-group">
						<label class="col-sm-4 control-label">游戏ID</label>
						<div class="col-sm-4">
						 <form:input path="id" cssClass="form-control" />
<!--  							<input type="text" id="id" name="id" class="form-control" > -->
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">游戏名称</label>
						<div class="col-sm-4">
							<form:input path="playname" cssClass="form-control" />
<!-- 							<input type="text" id="playname" name="playname" class="form-control"> -->
						</div>
					</div>
					<div class="form-group">
					    <label class="col-sm-4 control-label">序号</label>
					    <div class="col-sm-4">
					      <form:input path="indexnum" cssClass="form-control" />
<!-- 					      <input type="text" id="indexnum" name="indexnum" class="form-control" > -->
					    </div>
					  </div>
					<div class="form-group">
						<label class="col-sm-4 control-label">渠道</label>
						<div class="col-sm-4">
							<select id="channel" name="channel" class="form-control select" >
								<option value="0">微信</option>
								<option value="1">APP</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">路径</label>
						<div class="col-sm-4">
							<form:input path="deploypath" cssClass="form-control" />
<!-- 							<input type="text" id="deploypath" name="deploypath" class="form-control"> -->
						</div>
					</div>
					
					<div class="form-group" id="wxactionurlid">
						<label class="col-sm-4 control-label">微信活动数据接口</label>
						<div class="col-sm-4">
							<form:input path="wxactionurl" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group" id="appactionurlid">
						<label class="col-sm-4 control-label">APP活动数据接口</label>
						<div class="col-sm-4">
							<form:input path="appactionurl" cssClass="form-control" />
						</div>
					</div>
					
					<div class="form-group" id="wxrewardurlid">
						<label class="col-sm-4 control-label">微信活动奖品接口</label>
						<div class="col-sm-4">
							<form:input path="wxrewardurl" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group" id="apprewardurlid">
						<label class="col-sm-4 control-label">APP活动奖品接口</label>
						<div class="col-sm-4">
							<form:input path="apprewardurl" cssClass="form-control" />
						</div>
					</div>
					
					<div class="form-group">
								<label class="col-sm-4 control-label">状态</label>
								<div class="col-sm-4">
										<form:select path="status" class="form-control select">
									      	<option value="0">未上线</option>
											<option value="1">上线</option>
											<option value="2">下线</option>
											<option value="3">推荐</option>
									    </form:select>
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
			$('#addform').validate({
				rules : {
					id : {
						pwsCheck:true,
						notnull : true,
						required : true,
						maxlength : 20
					},
					playname : {
						notnull : true,
						required : true,
						maxlength : 60
					},
					indexnum : {
						intinteger : true,
						notnull : true,
						required : true,
						maxlength : 10
					},
					deploypath : {
						notnull:true,
						required:true,
						maxlength : 50
					},
					appactionurl : {
						maxlength : 500
					},
					wxactionurl : {
						maxlength : 500
					},
					apprewardurl : {
						maxlength : 500
					},
					wxrewardurl : {
						maxlength : 500
					}
				}
			});
			
			//初始隐藏APP渠道的
			$("#appactionurlid").css("display", "none"); 
			$("#apprewardurlid").css("display", "none");
			
			$("#channel").change(function(){
				var chanval = $('#channel').val();
				if('1'==chanval) {
					$("#appactionurlid").css("display", ""); 
					$("#apprewardurlid").css("display", "");
					$("#wxrewardurlid").css("display", "none"); 
					$("#wxactionurlid").css("display", "none");
				}else{
					$("#appactionurlid").css("display", "none"); 
					$("#apprewardurlid").css("display", "none");
					$("#wxrewardurlid").css("display", ""); 
					$("#wxactionurlid").css("display", "");
				}
			});
		});
	</script>
</body>
</html>