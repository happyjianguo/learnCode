<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>公告</title>
</head>
<body>
	<div class="main-content">
			<div class="mainbox">
				<div class="tablebox">
				<div class="table-tit">
					<div class="tit-left" >
						公告
					</div>
				</div>
				<table id="table_id" class="tablemain">
					<thead>
						<tr class="tit-top">
						    <th scope="col">公告id</th>
						    <th scope="col">公告名</th>
							<th scope="col">公告值</th>
							<!-- <th scope="col">创建时间</th>
							<th scope="col">更新时间</th> -->
						</tr>
					</thead>
					<tbody id="tbody"></tbody>
				</table> 
			</div>
		</div>
		
 	</div>			
		<script type="text/javascript" >
			var table;
			$(function() {
				table = $('#table_id').DataTable({
					"ajax": function (data, callback, settings) {
	                	$.ax({
	                		 url : "${ctxPath}/notice/ajax/getdata",
	                		data : function() {
								return serializeForm($('#query'));
							},
					        successfn : function(result) {
					        	callback(result);
					        }
	                	});
	                  },
					"columns" : [
							{
								'data' : 'noticeid' ,'visible' : false
							},
							{
								'data' : 'noticename'
							},
							{
								'data' : 'noticevalue'
							}/* ,
							{
								'data' : 'createtime'
							},
							{
								'data' : 'updatetime'
							} */
							
					 ]
				});
				
				/*条件查询*/
				$(".query-btn").on("click", "#queryBtn", function() {
					 table.ajax.reload();
				});
				/*重置查询条件*/
				$(".query-btn").on("click", "#resetBtn", function() {
					$('#noticenames').val("");
				});
				
				$("#myModal").on('hidden.bs.modal', function (e){
					 $("#eform1").validate().resetForm();
					 $('#eform1')[0].reset();
				});
				$("#myModal2").on('hidden.bs.modal',function(e){
					 $("#eform2").validate().resetForm();
					 $('#eform2')[0].reset();
				}); 
				/*单个新增*/
			   	$(".tit-btn").on("click",".addNotice",function(){
			        $('#noticename11').val('');
			        $('#noticevalue11').val('');
			    });
			    /*单个修改*/
			    $(".tablemain").on("click",".editNotice",function(){
			    	var data = table.row( $(this).parents('tr') ).data();
			        $('#noticeid22').val(data.noticeid);		    	
			        $('#noticename22').val(data.noticename);
			        $('#noticevalue22').val(data.noticevalue);
			    });
			    /*单个删除*/
				$(".tablemain").on("click",".delNotice",function(){
					var data = table.row( $(this).parents('tr') ).data();
					$("#noticeid").val(data.noticeid);
					$("#delmsg").html(data.noticename);
				});
			    $("#myModal-delete").on("click",".delMsgid",function(){
			    	 $.ax({	 							
 							url: "${ctxPath}/notice/deldata",
 							data:{noticeid:$('#noticeid').val()},
 					        successfn : function(result) {
 					        	/* alert(JSON.stringify(result)); */
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
 					        errorfn : function() {
 					        	$("#errtextmsg").text("网络异常!");
 					        	$('#myModal-errmsg').modal('show');
 					        }							
 						}); 
			    });
			  	//新增表单验证，提交
			    $(document).ready(function() {
					$('#eform1').validate({
						//debug: true,
						rules : {
							noticename : {
								isRightfulString :true,
								notnull:true,
								required : true,
								maxlength : 20
							},
							noticevalue : {
								isRightfulString :true,
								notnull:true,
								required : true,
								maxlength : 60
							}
						},
	 					submitHandler: function(form) {
	 						var params = serializeForm($('#eform1'));
							$.ax({
								url: "${ctxPath}/notice/addnotice",
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
			  	//修改表单验证，提交
				$(document).ready(function() {
					$('#eform2').validate({
						rules : {
							noticename : {
								isRightfulString :true,
								notnull:true,
								required : true,
								maxlength : 20
							},
							noticevalue : {
								isRightfulString :true,
								notnull:true,
								required : true,
								maxlength : 60
							}					
						},
						submitHandler: function(form) {
							var params = serializeForm($('#eform2'));
							$.ax({
								url: "${ctxPath}/notice/editinotice",
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
					
				});
			});
		</script>
</body>
</html>