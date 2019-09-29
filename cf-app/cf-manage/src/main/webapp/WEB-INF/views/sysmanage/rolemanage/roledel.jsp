<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>系统管理-删除用户</title>
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
				<li class="active" id="bill_check">删除角色</li>
			</ol>
		</div>
		<div class="mainbox">
			<div class="row">
				<form:form id="useraddform" modelAttribute="roleManageModel" action="${ctxPath}/role/deldata" methodParam="post"
					cssClass="form-horizontal">
					<div class="form-group">
						<div class="col-sm-4">
							<form:input path="roleId" type="hidden" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">用户名</label>
						<div class="col-sm-3">
							<form:input path="roleName" cssClass="form-control" readonly="true"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">资源权限</label>
						<div class="col-sm-3">
							<div class="left listbox">
								<ul id="treeDemo" class="ztree" ></ul>
							</div>
						</div>
					</div>					
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-8">
							<button type="submit" class="btn btn-primary">确定</button>
							<a href="${ctxPath}/role/init"><button type="button" class="btn default cancel" >取消</button></a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
	<script type="text/javascript">
		$(document).ready( function () {
			var setting = {
				check: {
					enable: true,
					chkStyle: "checkbox",
					chkboxType: { "Y": "ps", "N": "ps" }			 
				},
				data: {
					key:{
						name:"cnname"
					},
					simpleData: {
						enable: true,
						idKey: "resourceid",
						pIdKey: "parentid",
						rootPId: "0"
					}
				},
				view:{
					showIcon:false
				},
				callback: {
					beforeCheck: function(treeId, treeNode) {
						//按钮取消选中时回调
						if(treeNode.level>=2 && treeNode.checked){
							var treeObj = $.fn.zTree.getZTreeObj(treeId);
							treeObj.checkNode(treeNode, false, false);
							return false;
						}
					}
				}
			};
			var zNodes = ${roleManageModel.resourceList};
			var treeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);		
			var nodes = treeObj.transformToArray(treeObj.getNodes());
			for (var i=0, l=nodes.length; i < l; i++) {
				treeObj.setChkDisabled(nodes[i], true);
			}
		});
	</script>
</body>
</html>