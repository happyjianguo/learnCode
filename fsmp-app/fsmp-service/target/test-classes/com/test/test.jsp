<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>系统管理-公告管理</title>
</head>
<body>
		<div class="centent">
			<!-- sidebar-menu end-->
			<div class="sidebar-right">
				<div class="main-content">
					<div class="list-nav">
						<ol class="breadcrumb">
							<i class="icon-cog"></i>
							<li>系统管理</li>
							<li class="active" id="bill_check">公告管理</li>
						</ol>
					</div>
					 <div class="mainbox">
					 	<div class="query">
							<div class="query-tit">查询条件</div>
							<div class="query-text">
								<span>公告名</span>
								<input type="text" id="noticename" class="form-control">
							</div>
							<div class="query-btn">
								<button type="button" class="btn btn-primary" id="queryBtn">查询</button>
								<button type="button" class="btn btn-primary reset" id="resetBtn">重置</button>
							</div>
						</div>
						<div class="tablebox">
							<div class="table-tit">
								<div class="tit-left" >
									公告管理
								</div>
								<div class="tit-btn">
										<span><button type="button" class="btn btn-primary addNotice"  data-toggle="modal" data-target="#myModal"><i class="fa fa-plus"></i>新增</button></span>
								</div>
							</div> 
							<table id="table_id" class="tablemain">
								  <thead>
									  <tr class="tit-top">
									  	<!-- <th scope="col"><input type="checkbox" value=""  id="checkAll"></th> -->
					                    <th scope="col">公告id</th>
					                    <th scope="col">公告名</th>
									    <th scope="col">公告值</th>
									    <th scope="col">创建时间</th>
									    <th scope="col">更新时间</th>
									    <th scope="col">操作</th>
									  </tr>
								  </thead>
								  <tbody></tbody>
							</table>
						</div>
					</div>			
				</div>
			</div>
	<form:form id="eform1">
		<!-- Modal-->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">新增</h4>
					</div>
					<div class="modal-body">
					    <form class="form-horizontal">
						  <div class="form-group">
						    <label class="col-sm-4 control-label">公告名</label>
						    <div class="col-sm-6">
						      <input type="text" class="form-control roleName-update" id="noticename" name="noticename">
						    </div>
						  </div>
 						  <div class="form-group">
						    <label class="col-sm-4 control-label">公告值</label>
						    <div class="col-sm-6">
						      <input type="text" class="form-control roleName-update" id="noticevalue" name="noticevalue">
						    </div>
						  </div> 
						</form>
					</div>
					<div class="modal-footer">						
						<button type="submit" class="btn btn-primary" id="addnotice" >确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
		<!-- Modal end-->
	</form:form>
	
	<form:form id="eform2">	
		<!-- Modal-->
		<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">更新</h4>
					</div>
					<div class="modal-body">
					    <form class="form-horizontal">
					      <input type="hidden" class="form-control" id="noticeid" name="noticeid">
						  <div class="form-group">
						    <label class="col-sm-4 control-label">公告名</label>
						    <div class="col-sm-6">
						      <input type="text" class="form-control roleName-update" id="noticename" name="noticename">
						    </div>
						  </div>
 						  <div class="form-group">
						    <label class="col-sm-4 control-label">公告值</label>
						    <div class="col-sm-6">
						      <input type="text" class="form-control roleName-update" id="noticevalue" name="noticevalue">
						    </div>
						  </div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="submit" id ="editnoticeok" class="btn btn-primary" >确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
</form:form>	
		<!-- Modal end-->	
	<!-- Modal-->
	<form:form id="eform3"  modelAttribute="wxdmessagemodel3" method="post" action="">
		<div class="modal fade" id="myModal-delete" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<input type="hidden" class="form-control" id="noticeid" name="noticeid">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">确认信息</h4>
					</div>
					<div class="modal-body">
						<div class="modal-prompt deleteAlert">							
							<i class="fa fa-exclamation"></i><span>确认删除公告<span id="delmsg"></span>吗？</span>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary deleteConfirm delMsgid">确定</button>
					    <button type="button" class="btn btn-default deleteCancle"
							data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
	</form:form>
 <script type="text/javascript" >
			var table;
			$(document).ready( function () {
				/* table=$('#table_id').DataTable({
		                "aoColumnDefs": [
							{ 
							  "bSearchable": false, //bSearchable：是否可搜索；
							  "bVisible": true, //bVisible：是否可见；
							  "aTargets": [ 3,5 ] //aTargets：哪一列;
							},
  			                {
			                 "targets": [0],//隐藏列
			                  "visible": false
			                }    
 						],
		                "ajax" : {
		                	"error":function (XMLHttpRequest, textStatus, errorThrown) {
								if(XMLHttpRequest.readyState != 0){
									$("#errtextmsg").text("网络异常!");
									$('#myModal-errmsg').modal('show');
								}
		                	},
							"url" : "${ctxPath}/notice/getdata",
							"type": "Post",
							"data" : function(d) {
								var noticename = $.trim($('#noticename').val());
								if (noticename != "") {
									d.noticename = noticename; //添加额外的参数传给服务器
								}
							}
						},
		                
		                "columns": 
		                [  
						  	//{ "defaultContent": "<input type='checkbox' name='subBox' onClick='checkOne()' class='roleCheck'>"},
						   { 'data': 'noticeid' },
		                   { 'data': 'noticename' }, 
		                   { 'data': 'noticevalue' }, 
		                   { 'data': 'createtime' }, 
		                   { 'data': 'updatetime' }, 
		                   { 'defaultContent': 
		                           "<td>"+
							    		"<span><button type='button' class='btn btn-primary roleItemUpdate editNotice' data-toggle='modal' data-target='#myModal2' ><i class='fa fa-edit'></i>编辑</button></span>"+
							    		"<span><button type='button' class='btn btn-primary roleItemDelete delNotice' data-toggle='modal' data-target='#myModal-delete'><i class='fa fa-trash-o'></i>删除</button></span>"+
							    	"</td>"
		                   }
		                ] 
				    }); */
				/*条件查询*/
				/* $(".query-btn").on("click", "#queryBtn", function() {
					 table.ajax.reload();
				}); */
				/*重置查询条件*/
				/* $(".query-btn").on("click", "#resetBtn", function() {
					$('#imusername').val("");
					$("#imsname").val("");
					$("#urole option").removeAttr("selected");
				});
				
				 $("#myModal").on('hidden.bs.modal', function (e){
					 $("#eform1").validate().resetForm();
					 $('#eform1')[0].reset();
					 //$('.selectpicker').selectpicker('render');
				 });
				 $("#myModal2").on('hidden.bs.modal',function(e){
					 $("#eform2").validate().resetForm();
					 $('#eform2')[0].reset();
					 //$('.selectpicker').selectpicker('render');
				 }); */
				    
				 /*单个新增*/
				    /* $(".tit-btn").on("click",".addNotice",function(){
				        $('#username').val('');
				        $('#status').val('');
				        $('#cname').val('');
				        $('.selectpicker').selectpicker('refresh')
				        $('#parentorg').val('');
				        $('#org').val('');
				    }); */
				    /*单个修改*/
				   /*  $(".tablemain").on("click",".editNotice",function(){
				    	var data = table.row( $(this).parents('tr') ).data();	
				        $('#euserid').val(data.userid);				    	
				        $('#eusername').val(data.username);
				        $('#oeusername').val(data.username);
				        $('#estatus').val(data.status);
				        $('#ecname').val(data.cname);
				        $('#euserrole').val(data.roleid);
				        $('#eparentorg').val(data.parentorgid);
				        $('#eparentorg').change();
				        $('#eorg').val(data.orgid);
				        $('.selectpicker').selectpicker('render');
				        
				    }); */
				    /*单个删除*/
					/* $(".tablemain").on("click",".delNotice",function(){
						var data = table.row( $(this).parents('tr') ).data();
						$("#userids").val(data.userid);
						$("#delmsg").html(data.username);
					});
				    $("#myModal-delete").on("click",".delMsgid",function(){
				    	 $.ax({	 							
	 							url: "${ctxPath}/imuser/deldata",
	 							data:{userids:$('#userids').val()},
	 					        successfn : function(result) {
	 								$("#textmsg").empty();
	 								$('.modal').modal('hide');
	 				    	    	if(result.success){
	 				    	    		table.ajax.reload();
	 				    	    		$("#textmsg").text(result.msg);
			 							$('#myModal-confirm').modal('show');
	 				    	    	}else{
	 				    	    		$("#errtextmsg").text(result.msg);
		 					        	$('#myModal-errmsg').modal('show');
	 				    	    	}
		 							
	 					        },
	 					        errorfn : function() {
	 					        	$("#errtextmsg").text("网络异常!");
	 					        	$('#myModal-errmsg').modal('show');
	 					        }							
	 						}); 
				    });
				     */
			//新增管理员表单验证，提交
			/* $(document).ready(function() {
				$('#eform1').validate({
					//debug: true,
					rules : {
						username : {
							isRightfulString :true,
							notnull:true,
							required : true,
							maxlength : 20
						},
						cname : {
							isRightfulString :true,
							notnull:true,
							required : true,
							maxlength : 60
						},
						userid : {
							notnull:true,
							required : true
						},
						parentorgid  : {
							required : true
						},
						roleid : {
							notnull:true,
							required : true
						}
					
					},
 					submitHandler: function(form) {
 						var params = serializeForm($('#eform1'));
						$.ax({
							url: "${ctxPath}/imuser/addimuser",
							data : params,
							successfn: function(result){
								//成功时的回调方法
								$("#textmsg").empty();
								if(result.result==1){
									$("#textmsg").text(result.msg);
									$('.modal').modal('hide');
									$('#myModal-confirm').modal('show');
				        	    	table.ajax.reload();
								}else{
									$("#errtextmsg").text(result.msg);
						        	$('#myModal-errmsg').modal('show');
								}
								
							},
					        errorfn : function(){
					        	$("#errtextmsg").text("网络异常!");
					        	$('#myModal-errmsg').modal('show');
					        }							
						});
						return false;
					}
				});
			});
			 */
			//修改表单验证，提交
			/* $(document).ready(function() {
				$('#eform2').validate({
					//debug: true,
					rules : {
						username : {
							isRightfulString :true,
							notnull:true,
							required : true,
							maxlength : 20
						},
						cname : {
							isRightfulString :true,
							notnull:true,
							required : true,
							maxlength : 60
						},
						status : {
							notnull:true,
							required : true
						},
						roleid : {
							notnull:true,
							required : true
						},
						parentorgid  : {
							notnull:true,
							required : true
						}					
					},
					submitHandler: function(form) {
						var params = serializeForm($('#eform2'));
						$.ax({
							url: "${ctxPath}/imuser/editimuser",
							data:params,
							successfn: function(result){
								//成功时的回调方法
								$("textmsg").empty();
				        	    	$('.modal').modal('hide');
				        	    	if(result.result==1){
				        	    		table.ajax.reload();
					        	    	$("#textmsg").text(result.msg);
										$('#myModal-confirm').modal('show');
				        	    	}else{
				        	    		$("#errtextmsg").text(result.msg);
							        	$('#myModal-errmsg').modal('show');
				        	    	}
				        	    	
							},
					        errorfn : function(){
					        	$("#errtextmsg").text("网络异常!");
					        	$('#myModal-errmsg').modal('show');
					        }							
						}); 
					}
				});
				
			}); */
			});
		</script>
		
		<script type="text/javascript">
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
			$("#checkAll").click(function(){
				checkAll();
			}); 
			//点击编辑按钮弹出dialog 将id改变
			$(".tablemain").on("click",".roleCheck",function(){
				$(this).parents("tr").toggleClass('ckecked');
			});
			//全选全不选
			$(".tablemain").on("click",".roleCheck",function(){
				checkOne();
			});
		</script>
</body>
</html>