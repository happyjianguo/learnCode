<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>内管系统</title>
</head>
<body>
	<input type="hidden" id="ctx" value="${ctxPath}">
	<input type="hidden" id="csrfheader" value="${csrfheader}">
	<input type="hidden" id="csrftoken" value="${csrftoken}">
	<input type="hidden" id="startDay" value="${dateVO.startDay}">
	<input type="hidden" id="endDay" value="${dateVO.endDay}">
	<input type="hidden" id="appid" value="${appid}">
	<div class="sidebar-right">
		<div class="main-content">
			<div class="mainbox">
				<div class="query">
					<!-- <div class="query-tit">查询条件</div> -->
					<div class="query-text">
						<span>用户昵称</span><input id="queryNickName" type="text" class="form-control">
					</div>
					<div class="query-text">
						<span>OPENID</span><input id="queryOpenid" type="text" class="form-control">
					</div>
					<div class="datetime">					
						<div class="input-append date  date2">
							<span class="span_d">至</span>
							<input id="date_2" type="text" class="date_2 form_datetime" readonly="readonly" value="${dateVO.endDay}"/>
						</div>
						<div class="input-append date date1">
							<span class="span_d">日期范围</span> <input id="date_1" type="text" class="date_1 form_datetime" readonly="readonly" value="${dateVO.startDay}"/>
						</div>
					</div>
					<div class="query-btn">
						<button type="button" class="btn btn-primary" id="queryBtn">查询</button>
						<button type="button" class="btn btn-primary reset" id="resetBtn">重置</button>
					</div>
				</div>
				<div class="tablebox box1">
					<div class="table-tit">
						<div class="tit-left">用户分组</div>
						<div class="query-btn">
						<span class="current-group">当前分组</span> 
						<select id="initGroupName" name="initGroupName" class="form-control group_name"style="width: 150px;">
						</select>
						<button type="button" class="btn btn-define" data-toggle="modal" id="modifyBtn">
							<i class="fa fa-edit"></i>修改组名
						</button>
						<button type="button" class="btn btn-define" data-toggle="modal" id="moveBtn">
							<i class="fa fa-users"></i>移动用户
						</button>
						<button type="button" class="btn btn-primary" data-toggle="modal" id="addBtn">
							<i class="fa fa-plus"></i>添加分组
						</button>
						<button type="button" class="btn btn-danger" data-toggle="modal" id="delBtn">
							<i class="fa fa-trash-o"></i>删除分组
						</button>
						</div>
					</div>
					<!-- 
					<div class="user_group ">
						<div class="query-btn">
						<span class="current-group">当前分组</span> 
						<select id="initGroupName" name="initGroupName" class="form-control group_name"style="width: 150px;">
						</select>
						<button type="button" class="btn btn-define" data-toggle="modal" id="modifyBtn">
							<i class="fa fa-edit"></i>修改组名
						</button>
						<button type="button" class="btn btn-define" data-toggle="modal" id="moveBtn">
							<i class="fa fa-users"></i>移动用户
						</button>
						<button type="button" class="btn btn-primary" data-toggle="modal" id="addBtn">
							<i class="fa fa-plus"></i>添加分组
						</button>
						<button type="button" class="btn btn-danger" data-toggle="modal" id="delBtn">
							<i class="fa fa-trash-o"></i>删除分组
						</button>
						</div> 
					</div>
					-->
					<table id="tableList" width="100%" border="0" align="center"
						 valign="middle" class="tablemain">
						<thead>
							<tr class="tit-top">
								<th scope="col"><input type="checkbox" value=""
									id="checkAll"></th>
								<th scope="col">OPENID</th>
								<th scope="col">用户昵称</th>
								<th scope="col">性别</th>
								<th scope="col">地址</th>
								<th scope="col">组别</th>
								<th scope="col">关注时间</th>
								<th scope="col">组ID</th>
								<th scope="col">公众号ID</th>
								<th scope="col">操作</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- sidebar-right end-->
	<!-- 新增分组页面 -->
	<form:form id="eform1">
		<div class="modal fade" id="addGroupModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="addGroupLabel">新增</h4>
					</div>
					<form class="form-horizontal" id="addGroupForm">
						<div class="modal-body">
							<div class="form-group">
								<label class="col-sm-2 control-label">分组名称</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="addGroupName" name="addGroupName" placeholder="请输入组名">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-10">
									<input type="hidden" class="form-control" id="appid" value="${appid}" name="appid">
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary" id="addGroupConfirmBtn">确定</button>
							<button type="button" class="btn btn-default" id="addGroupCloseBtn">取消</button>								
						</div>
					</form>
				</div>
			</div>
		</div>
	</form:form>
	<!-- 修改分组名称页面 -->
	<div class="modal fade" id="modifyGroupModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="modifyGroupLabel">修改</h4>
				</div>
				<form class="form-horizontal" id="modifyGroupForm">
					<div class="modal-body">
						<div class="form-group">
							<label class="col-sm-2 control-label">原组名</label>
							<div class="col-sm-10">
								<select id="oldGroupId" name="oldGroupId" class="form-control"></select>
							</div>
						</div>
						<div class="form-group">
								<div class="col-sm-10">
									<input type="hidden" class="form-control" id="appid" value="${appid}" name="appid">
								</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">新组名</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="newGroupName" name="newGroupName" placeholder="请输入组名">
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary" id="modifyGroupConfirmBtn">确定</button>
						<button type="button" class="btn btn-default" id="modifyGroupCloseBtn">取消</button>							
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- 删除分组 -->
	<div class="modal fade" id="delGroupModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="delGroupLabel">删除</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="delGroupForm">
						<div class="form-group">
							<label class="col-sm-2 control-label">分组名称</label>
							<div class="col-sm-10">
								<select id="delGroupId" name="delGroupId" class="form-control"></select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-10">
								<input type="hidden" appid" name="appid" value="${appid }" class="form-control"/>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						id="delGroupConfirmBtn">确定</button>
					<button type="button" class="btn btn-default" id="delGroupCloseBtn">取消</button>						
				</div>
			</div>
		</div>
	</div>
	<!-- 移动分组 -->
	<div class="modal fade" id="moveGroupModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="moveGroupLabel">移动用户</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="moveGroupForm">
						<div class="form-group">
							<label class="col-sm-4 control-label">将所选用户移动到</label>
							<div class="col-sm-6">
								<select id="toGroupId" name="toGroupId" class="form-control"></select>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="moveConfirmBtn">确定</button>
					<button type="button" class="btn btn-default" id="moveCloseBtn">取消</button>					
				</div>
			</div>
		</div>
	</div>
	<!--查询详情-->
	<div class="modal fade" id="myModa2" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">查看详情</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-4 control-label">OPENID</label>
							<div class="col-sm-6">
								<input type="text" id=OpenID name=OpenID class="form-control" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">用户昵称</label>
							<div class="col-sm-6">
								<input type="text" id="nickName" name="nickName" class="form-control" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">性别</label>
							<div class="col-sm-6">
								<input type="text" id="sex" name="sex" class="form-control" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">组别</label>
							<div class="col-sm-6">
								<input type="text" id="group" name="group" class="form-control" readonly="readonly">
							</div>
						</div>
						<div class="form-group  showCardNum">
							<label class="col-sm-4 control-label">关注时间</label>
							<div class="col-sm-6">
								<input type="text" id="subScribeTime" name="subScribeTime" class="form-control" readonly="readonly">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>					
				</div> 
			</div>
		</div>
	</div>
	<!--群发消息-->
	<div class="modal fade" id="sendModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">群发消息</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<div class="mainbox">
							<div class="frm-centent" style="display: block;">
								<div class="frm-title">
									<ul>
										<li class=""><a href="javascript:;"><i class="icon-credit-card"></i>图文消息</a></li>
										<li class="cru"><a href="javascript:;"><i class="fa fa-edit"></i>文字</a></li>
										<li class=""><a href="javascript:;"><i class="icon-picture"></i>图片</a></li>
									</ul>
								</div>
								<div class="frm-box">
									<div class="frm-tab" style="display: none;">
										<div class="media-cover">
											<a href="javascript:;" data-toggle="modal" data-target="#myModal"> <i class="fa fa-plus"></i> <em>从素材库中选择</em></a>
										</div>
									</div>
									<div class="frm-tab" style="display: block;">
										<textarea class="inputbox" maxlength="200"></textarea>
										<div class="frm-tab-bottom">
											<span>还可以输入<em id="lsheng">200</em>字</span>
										</div>
									</div>
									<div class="frm-tab" style="display: none;">
										<div class="media-cover">
											<a href="javascript:;"> <i class="fa fa-plus"></i> <em>从素材库中选择</em></a>
										</div>
										<div class="media-cover">
											<a href="javascript:;"> <i class="fa fa-plus"></i> <input type="file" style="display: none;"> <em>上传图片</em></a>
										</div>
									</div>
								</div>
								<!-- frm-box-->
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>					
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
	<!--日期控件-->
	<script type="text/javascript"src="<c:url value='/resources/js/wx/userGroup.js?v=${jsv}'/>"></script>
	<!-- myModa2 end-->
</body>
</html>
