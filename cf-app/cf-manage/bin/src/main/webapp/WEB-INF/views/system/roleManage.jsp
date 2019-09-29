<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>角色管理</title>
</head>
<body>
		<div class="main-content">
			<div class="list-nav">
				<ol class="breadcrumb">
					<li>系统管理</li>
					<li class="active" id="bill_check">角色管理</li>
				</ol>
			</div>
			<div class="mainbox">				
				<div class="tablebox">
					<div class="table-tit">
						<div class="tit-left" >
							角色管理
						</div>
						<div class="tit-btn">
							<span><button type="button" class="btn btn-primary roleItemAdd" data-toggle="modal" >新增</button></span>							
<!-- 							<span><button type="button" class="btn btn-danger batchDelete"><i class="fa fa-trash-o"></i>批量删除</button></span> -->
						</div>
					</div>
					<table class="tablemain">
					  <thead>
					  <tr class="tit-top">
					    <th scope="col"><input type="checkbox" value=""  id="checkAll"></th>
					    <th scope="col">角色id</th>
					    <th scope="col">角色名称</th>
					    <th scope="col">状态</th>
					    <th scope="col">更新时间</th>
					    <th scope="col">创建时间</th>
					    <th scope="col">资源列表</th>
					    <th scope="col">操作</th>
					  </tr>
					  </thead>
					 
					</table>
				</div>
			</div>			
		</div>
<!-- Modal-->
<form:form id="eform1" class="form-horizontal">  
	<div class="modal fade" id="myModal-add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="myModalLabel">新增</h4>
				</div>
				<div class="modal-body">
					  <div class="form-group">
					    <label class="col-sm-4 control-label">角色名称</label>
					    <div class="col-sm-6">
					      <input type="text" class="form-control roleName-add" name="roleNameadd" >
					    </div>
					  </div>
					    
					  <div class="form-group">
					    <label  class="col-sm-4 control-label">资源权限</label>
					    <div class="col-sm-6 resource-add">
					    </div>
					  </div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary roleAdd">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>					
				</div>
			</div>
		</div>
	</div>
</form:form>
<!-- Modal end-->

<!-- Modal-->
<form:form id="eform2" class="form-horizontal">  
<div class="modal fade" id="myModal-update" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="myModalLabel">更新</h4>
			</div>
			<div class="modal-body">
				  <div class="form-group">
				    <label class="col-sm-4 control-label">角色名称</label>
				    <div class="col-sm-6">
				      <input type="text" class="form-control roleName-update" name="roleNameupdate" readonly = "readonly">
				    </div>
				  </div>
				  <div class="form-group">
				    <label  class="col-sm-4 control-label">资源权限</label>
				    <div class="col-sm-6 resource-update">
				    </div>
				  </div>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-primary roleUpdate">确定</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>				
			</div>
		</div>
	</div>
</div>
</form:form>
<!-- Modal end-->

<!-- Modal-->
<div class="modal fade" id="myModal-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="myModalLabel">提示信息</h4>
			</div>
			<div class="modal-body">
			   <div class="modal-prompt deleteAlert"><i class="icon-warning-sign"></i><span>确认删除吗？</span></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary deleteConfirm">确定</button>
                <button type="button" class="btn btn-default deleteCancle" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>
<!-- Modal end-->
<cx:script>
 <script type="text/javascript">
 			$(function(){
 				var table=$(".tablemain").DataTable({ 
	                "ajax": function (data, callback, settings) {
	                	$.ax({
	                		"url":"${ctxPath}/role/allRoles",
					        successfn : function(result) {
					        	callback(result);
					        }
	                	});
	                  },
	                "columns": [  
					   { "defaultContent": "<input type='checkbox' name='subBox' onClick='checkOne()' class='roleCheck'>","visible": false},
					   { 'data': 'roleId' },
	                   { 'data': 'roleName' }, 
	                   { 'data': 'status' }, 
	                   { 'data': 'updateTime' }, 
	                   { 'data': 'createTime' },
	                   { 'data': 'reSourceIds'},
	                   { "defaultContent": "<span><button type='button' class='btn btn-primary roleItemUpdate'><i class='fa fa-edit'></i>编辑</button></span>"+
					    	"<span><button type='button' class='btn btn-primary roleItemDelete'><i class='fa fa-trash-o'></i>删除</button></span>"}
	                ]
			    });
 				//checkAll	
 				function checkAll() {
 				var eles = document.getElementsByName("subBox");
 				var i = 0;
 				// 如果是全选状态，则取消所有的选择
 				if (isSelectAll() == true) {
 					for ( i = 0; i < eles.length; i++) {
 						eles[i].checked = false;
 				    }
 				    document.getElementById("checkAll").checked = false;
 				} else {
 					// 否则选中每一个checkbox
 				        for ( i = 0; i < eles.length; i++) {
 				            eles[i].checked = true;
 				        }
 				    }
 				}
 				// 判断当前是否为全选状态
 				function isSelectAll() {
 				    var isSelected = true;
 				    var eles = document.getElementsByName("subBox");
 				    for (var i = 0; i < eles.length; i++) {
 						if (eles[i].checked != true) {
 							isSelected = false;
 				        }
 				    }
 				    return isSelected;
 				}
 				// 选择任意一个非全选checkbox
 				function checkOne() {
 				    if (isSelectAll()) {
 						document.getElementById("checkAll").checked = true;
 				    } else {
 						document.getElementById("checkAll").checked = false;
 					}
 				}
 			//查询全部资源
			function queryAllResources() {
			    $.ax( {
			        url : '${ctxPath}/role/ajax/resourceList',
			        cache : false,
			        dataType : 'json',
			        successfn : function(result) {
			        	if(result.errorCode === "000000"){
			        		$(result.data).each(function(i){
			        			if($(this).attr("resourceId") =="main"||$(this).attr("resourceId") =="pwdEd"){
			        				$(".resource-add").append("<label class='checkbox-inline' style='display:none'><input type='checkbox' checked='checked' id='"+$(this).attr("resourceId")+"' value='"+$(this).attr("cnName")+"'>"+$(this).attr("cnName")+"</label>");
					        		$(".resource-update").append("<label class='checkbox-inline' style='display:none'><input type='checkbox' checked='checked' id='"+$(this).attr("resourceId")+"' value='"+$(this).attr("cnName")+"'>"+$(this).attr("cnName")+"</label>");
			        			}else{
			        				$(".resource-add").append("<label class='checkbox-inline'><input type='checkbox' id='"+$(this).attr("resourceId")+"' value='"+$(this).attr("cnName")+"'>"+$(this).attr("cnName")+"</label>");
					        		$(".resource-update").append("<label class='checkbox-inline'><input type='checkbox' id='"+$(this).attr("resourceId")+"' value='"+$(this).attr("cnName")+"'>"+$(this).attr("cnName")+"</label>");
			        			}
				        	});
			        	}else{
			        		alert(result.errorMsg);
			        	}
			        	
			        },
			        errorfn : function(result) {
			        	alert("获取资源出现异常");
			        }
			    });
			}
 			//查询全部的平台
 			function queryAllPlatform(){
 				$.ax( {
			        url : '${ctxPath}/role/ajax/platforms',
			        cache : false,
			        dataType : 'json',
			        successfn : function(result) {
			        	if(result.result==1){
			        		$(result.obj).each(function(){
			        			var platformId = $(this).attr("platformId");
			        			var wxName = $(this).attr("wxName");
			        			$("#platformId-add").append("<option value="+platformId+">"+wxName+"</option>");
			        			$(".platformId-update").append("<option value="+platformId+">"+wxName+"</option>");
			        		});
			        	}else{
			        		$("#textmsg").empty();
								$("#textmsg").text("查询公众号成功");
							$('.modal').modal('hide');
							$('#myModal-confirm').modal('show');
			        	    	table.ajax.reload();
			        	}
			        },
			        errorfn : function(result) {
			        	$("#textmsg").empty();
						$("#textmsg").text("查询公众失败");
						$('.modal').modal('hide');
						$('#myModal-confirm').modal('show');
		        	    	table.ajax.reload();
			        }
			    });
			}
  			//添加角色功能
			function addRole(param) {
			    $.ax( {
			        url : '${ctxPath}/role/ajax/save',
			        cache : false,
			        data:param,
			        dataType : 'json',
			        successfn : function(result) {
			        	modalHide();
			        	$("#textmsg").empty();
		        	    	table.ajax.reload();
			        	if(result.success){
							$("#textmsg").text("角色新增成功");
			        	}else{
			        		$("#textmsg").text("角色新增失败");
			        	}
						$('#myModal-confirm').modal('show');

			        },
			        errorfn : function(result) {
			        	modalHide();
			        	$("#textmsg").empty();
							$("#textmsg").text("角色新增失败");
						$('.modal').modal('hide');
						$('#myModal-confirm').modal('show');
		        	    	table.ajax.reload();
			        }
			    });
			}
			//更新角色功能
			function updateRole(param) {
			    $.ax( {
			        url : '${ctxPath}/role/ajax/update',
			        cache : false,
			        data:param,
			        dataType : 'json',
			        successfn : function(result) {
			        	$("#textmsg").empty();
						$('.modal').modal('hide');
	        	    	table.ajax.reload();
			        	if(result.success){
			        		$("#textmsg").text(result.msg);
			        	}else{
			        		$("#textmsg").text(result.msg);
			        	}
					$('#myModal-confirm').modal('show');
			        },
			        errorfn : function(result) {
			        	$("#textmsg").empty();
						$("#textmsg").text("修改角色失败");
						$('.modal').modal('hide');
						$('#myModal-confirm').modal('show');
	        	    	table.ajax.reload();
			        }
			    });
			}
			var batchDeleteId = null ;
  			//批量删除功能  单个和批次删除调用同一个方法只是参数不一样
			function batchDelete(param) {
			   $.ax( {
			        url : '${ctxPath}/role/ajax/batchDelete',
			        cache : false,
			        data:param,
			        dataType : 'json',
			        successfn : function(result) {
			        	$("#textmsg").empty();
			        	$('.modal').modal('hide');
	        	    	table.ajax.reload();
			        	if(result.result==1){
			        		$("#textmsg").text(result.msg);
			        	}else{
			        		$("#textmsg").text(result.msg);
			        	}
			        	$('#myModal-confirm').modal('show');
			        },
			        errorfn : function(result) {
			        	$("#textmsg").empty();
						$("#textmsg").text("修改角色失败");
						$('.modal').modal('hide');
						$('#myModal-confirm').modal('show');
	        	    	table.ajax.reload();
			        }
			    });
			}
  			//
  			var updateId = undefined;
  			//新增或者更新
  			
  			$('#eform1').validate({
					//debug: true,
					 rules : {
						 roleNameadd : {
							isRightfulString :true,
							notnull:true,
							required : true,
							maxlength : 120,
							isRightfulString:true
						},
						platformIdadd : {
							notnull:true,
							required : true,
							maxlength : 40
						} 
					},
		  			submitHandler: function(form) {
		  				addAction();
		  			}
				});
  			$('#eform2').validate({
				//debug: true,
				 rules : {
					 roleNameupdate : {
						notnull:true,
						required : true,
						maxlength : 120,
						isRightfulString:true
					},
					platformIdupdate : {
						notnull:true,
						required : true,
						maxlength : 40,
					} 
				},
	  			submitHandler: function(form) {
	  				updateAction(updateId);
	  			}
			});  
  			
  			function addAction(){
  				var roleName = $(".roleName-add").val();
				var platformId = $("#platformId-add option:selected").val();
				var resource = $(".resource-add input:checked");
				var ids = "";
				$(resource).each(function(){
					ids +=$(this).attr("id")+",";
				});
				ids = ids.substring(0, ids.length-1);
				var param = {"roleName":roleName,"platformId":platformId,"createTime":getCurrentDate(),"status":"1","reSourceIds":ids};
				addRole(param);
  			};
  			
  			function updateAction(updateId){
				var roleName = $(".roleName-update").val();
				var platformId = $(".platformId-update option:selected").val();
				var resource = $(".resource-update input:checked");
				var ids = "";
				$(resource).each(function(){
					ids +=$(this).attr("id")+",";
				});
				ids = ids.substring(0, ids.length-1);
				var param = {"roleId":updateId,"roleName":roleName,"platformId":platformId,"status":"1","reSourceIds":ids};
				updateRole(param);
  			}
  			function modalHide(){
  				$('.modal').modal('hide');
  			}
  			
			$(".deleteConfirm").click(function(){
				batchDelete({"ids":batchDeleteId});
			});
			//设置删除dialog的隐藏监听
			$('#myModal-delete').on('hidden.bs.modal', function (e) {
				});
			//设置新增dialog的隐藏监听
			$('#myModal-add').on('hidden.bs.modal', function (e) {
				$(".roleName-add").val("");
 				$(".resource-add").find("input").attr("checked",false);
				$("#platformId-add option").attr("selected",false);
				$("#eform1").validate().resetForm();
				});
			//设置更新dialog的隐藏监听
			$('#myModal-update').on('hidden.bs.modal', function (e) {
				$("#eform2").validate().resetForm();
				});
			//设置添加按钮的点击事件
			$(".batchDelete").click(function(){
				var boxs =  $(".ckecked");
				if(boxs.length==0){
					alert("至少选择一条记录进行删除操作！");
					return;
				}
				var ids = "" ;
				var roleNames = "" ;
				$.each(table.rows('.ckecked').data(),function (i,v) {
					ids+=this.roleId+",";
		        	roleNames+=this.roleName+"、";
		        });
				batchDeleteId=ids.substring(0, ids.length-1);
				roleNames= roleNames.substring(0, roleNames.length-1);
				$(".deleteAlert span").text("确定删除角色 "+roleNames+" 吗？");
				$('#myModal-delete').modal('show');
			});
			$("#checkAll").click(function(){
				checkAll();
			}); 
			//点击删除按钮 删除角色
			$(".tablemain").on("click",".roleItemDelete",function(){
					var data = table.row( $(this).parents("tr") ).data();
					$(".deleteAlert span").text("确定删除角色 "+data.roleName+" 吗？");
					$('#myModal-delete').modal('show');
					batchDeleteId =data.roleId ;
			});
			//点击新增按钮弹出dialog 将id改变
			$(".roleItemAdd").click(function(){
				$('#myModal-add').modal('show');
				updateId = null;
			});
			//点击编辑按钮弹出dialog 将id改变
			$(".tablemain").on("click",".roleCheck",function(){
				$(this).parents("tr").toggleClass('ckecked');
				checkOne();
			});
			//点击编辑按钮弹出dialog 将id改变
			$(".tablemain").on("click",".roleItemUpdate",function(){
					var data = table.row($(this).parents("tr")).data();
					$(".roleName-update").val(data.roleName);
					$("#myModal-update").find("option[value="+data.platformId+"]").attr("selected","selected");
					if(data.reSourceIds.length>0){
						var resources = data.reSourceIds.split(",");
						var resourcesLength = resources.length;
						for (var i = 0; i < resourcesLength; i++) {
							$(".resource-update").find("input[id="+resources[i]+"]").attr("checked","checked");
						}
					}
					updateId = data.roleId;
					$('#myModal-update').modal('show');
			});
 			queryAllResources();
			//queryAllPlatform();
 		});
</script>
</cx:script>
</body>
</html>