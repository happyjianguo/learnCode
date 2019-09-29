<%@page import="org.springframework.context.annotation.Import"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>内管系统-微信公众号管理</title>

<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<div class="main-content">
		<%-- <form:form action="${ctxPath}/wxmanage/goauth" method="post"
				id="eform">
			<button type="submit" class="btn btn-primary btn-lg btn-block"
					value="tijiao">添加公众号</button>
		</form:form> --%>
		<span class="btn-box">
			<a href="${ctxPath}/wxmanage/getwxauthhtml">
				<i class="fa fa-plus"></i>
				添加微信公众号
				<%-- <fmt:message key="button.add"/> --%>
			</a>
		</span>
		<div class="mainbox">
			<div class="tablebox">
				<table id="table_id" class="tablemain">
					<thead>
						<tr class="tit-top">
							<th scope="col">公众号id</th>
							<th scope="col">公众号头像</th>
							<th scope="col">公众号名称</th>
							<th scope="col">公众号原始id</th>
							<!-- <th scope="col">微信号</th> -->
							<th scope="col">公众号类型</th>
							<!-- <th scope="col">默认公众号</th> -->
							<th scope="col">操作</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
		</div>
		<!-- 	提示框 -->
		<div class="modal fade" id="delete-confirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">提示信息</h4>
					</div>
					<div class="modal-body">
						<div class="modal-prompt deleteAlert">
							<i class="fa fa-check"></i><span id="delete-textmsg"></span>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default deleteCancle"
							data-dismiss="modal">关闭</button>
						<button type="button" id="delbut" class="btn btn-primary deleteConfirm ">确认</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
		<script type="text/javascript">
 var table;
	$(function() {
		table = $('#table_id').DataTable({
	        "serverSide": true,//打开后台分页
	        "dataSrc": "aaData", 
			"ajax": function (dataModel, callback, settings) {
				var obj = serializeForm($('#queryform'));
				$.extend(dataModel, obj);
				$.ax({
         			"url":"${ctxPath}/wxmanage/getall",
         			"data" : dataModel,
         			successfn : function(result) {
			        	callback(result);
			        }
         		});
			},
        	"columns": [
				{ 'data': 'appid' ,'visible' : false}, 
				{ 'data': function(obj){
					return "<img src="+obj.headImg+" class='user-head'>";
				}}, 
	            { 'data': 'nickName' }, 
	            { 'data': 'userName' },
	            /* { 'data': 'alias' }, */
	            { 'data': function(obj){
					if (obj.serviceType === "0") {
							sReturn = "订阅号";
						} else if(obj.serviceType === "2") {
							sReturn = "服务号";
						}else{
							sReturn = "订阅号";
						}
						return sReturn;
					} 
	            },
	            /* { 
	            	'data': function(obj){
	            		var defaultappid = obj.defaultappid;
	            		var appid = obj.appid;
	            		if(appid === defaultappid){
	            			return "<span><a class='undefaultitem' title='默认' href='javascript:;'><i class='fa fa-edit'></i>默认</a></span>";
	            		}else{
	            			return "<span><a class='defaultitem' title='非默认' href='javascript:;'><i class='fa fa-edit'></i>非默认</a></span>";
	            		}
	            	}
	            }, */
	            { 
	            	'data': function(obj){
	            		var defaultappid = obj.defaultappid;
	            		var appid = obj.appid;
	            		if(appid === defaultappid){
	            			return "<span><a class='undefaultitem' title='默认' href='javascript:;'><i class='fa fa-edit'></i>默认</a></span>"+
	            			"<span><a class='deleteitem' title='删除' href='javascript:;'><i class='fa fa-edit'></i>删除</a>";
	            		}else{
	            			return "<span><a class='defaultitem' title='非默认' href='javascript:;'><i class='fa fa-edit'></i>非默认</a></span>"+
	            			"<span><a class='deleteitem' title='删除' href='javascript:;'><i class='fa fa-edit'></i>删除</a>";
	            		}
	            		//return "<span><a class='deleteitem' title='删除' href='javascript:;'><i class='fa fa-edit'></i>删除</a>";
	            	/* +"<bottom class='fucmanage btn btn-primary'>功能管理</bottom></span>"; */
	            	}
	            }
        	]
		});
		/*取消默认操作*/
		$("#table_id").on("click", ".undefaultitem", function() {
			var data = table.row($(this).parents('tr')).data();
			var formdata = {};
			formdata.appid=data.appid;
			$("#delete-textmsg").text("确定取消该默认公众号？");
			$('#delete-confirm').modal('show');
			$("#delete-confirm").off("click", "#delbut");
			$("#delete-confirm").on("click", "#delbut",function(){
				$.ax({
					url: "${ctxPath}/wxmanage/undefaultitem",
					data:formdata,
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
			});
		});
		/*默认操作*/
		$("#table_id").on("click", ".defaultitem", function() {
			var data = table.row($(this).parents('tr')).data();
			var formdata = {};
			formdata.appid=data.appid;
			$("#delete-textmsg").text("确定将该公众号设为默认公众号？");
			$('#delete-confirm').modal('show');
			$("#delete-confirm").off("click", "#delbut");
			$("#delete-confirm").on("click", "#delbut",function(){
				$.ax({
					url: "${ctxPath}/wxmanage/defaultitem",
					data:formdata,
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
			});
		});
		/*单个修改*/
		$("#table_id").on("click", ".deleteitem", function() {
			var data = table.row($(this).parents('tr')).data();
			var formdata = {};
			formdata.appid=data.appid;
			$("#delete-textmsg").text("确定删除该公众号？");
			$('#delete-confirm').modal('show');
			$("#delete-confirm").off("click", "#delbut");
			$("#delete-confirm").on("click", "#delbut",function(){
				$.ax({
					url: "${ctxPath}/wxmanage/del",
					data:formdata,
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
			
			});
				});
			});
		</script>
</body>
</html>