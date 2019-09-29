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
		<span class="btn-box">
			<a href="${ctxPath}/mcmanage/gameindex">
				<i class="fa fa-plus"></i>
				添加活动
				<%-- <fmt:message key="button.add"/> --%>
			</a>
		</span>
		<div class="mainbox">
			<%-- <div class="row">
				<span> <a href="${ctxPath}/mcmanage/gameindex"
					class="btn btn-primary"> <i class="fa fa-plus"></i> <fmt:message
							key="button.add" />
				</a>
				</span>
			</div> --%>
			<form:form action="${ctxPath}/mcmanage/gameindex" id="editform">
				<input type="hidden" id="gameid" name="gameid">
			</form:form>
			<table id="table_id" class="tablemain">
				<thead>
					<tr class="tit-top">
						<th scope="col">活动id</th>
						<th scope="col">模板名称</th>
						<th scope="col">部署路径</th>
						<th scope="col">活动标题</th>
						<th scope="col">模板名称</th>
						<th scope="col">活动渠道</th>
						<th scope="col">开始时间</th>
						<th scope="col">结束时间</th>
						<th scope="col">活动状态</th>
						<th scope="col">创建人</th>
						<th scope="col">创建日期</th>
						<th scope="col">操作</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
			<!-- 	提示框 -->
		<div class="modal fade" id="release-confirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">提示信息</h4>
					</div>
					<div class="modal-body">
						<div class="modal-prompt">
							<img id="qrcode">
							<form:form id="qrform" action="${ctxPath}/mcmanage/downQR" method="post" >
								<input type="hidden" name="gameid" id="qrid">
							</form:form>
							<input id="downqr" class="btn btn-primary" value="下载二维码" type="button">
						</div>
						<div class="modal-prompt deleteAlert">
							<input id="release-textmsg" type="text" value="disabled" readonly="readonly">
							<input id="copyURI"  data-clipboard-action="copy" data-clipboard-target="#release-textmsg"  value="复制链接" type="button" class="btn btn-primary">
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default deleteCancle"
							data-dismiss="modal">关闭</button>
						<!-- <button type="button" id="release" class="btn btn-primary deleteConfirm ">确认</button> -->
					</div>
				</div>
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
						<button type="button" id="deleteConfirm" class="btn btn-primary deleteConfirm ">确认</button>
					</div>
				</div>
			</div>
		</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
		<script type="text/javascript">	
			$(function() {
				var wechatGameURI = "${gameURI}";
				
				var clipboard = new Clipboard('#copyURI');
			    clipboard.on('success',
			    	function(e) {
			        	alert("复制成功");			        	
			    });
			    clipboard.on('error',
			    	function(e) {
			       		console.log(e);
			    });
				
				$("#table_id").on("click",".code",function(){
					var data = table.row($(this).parents('tr')).data();
					$("#qrcode").attr("src","${ctxPath}/mcmanage/showQR?gameid="+data.gameid);
					$("#downqr").off("click");
					$("#downqr").on("click",function(){
						$("#qrid").val(data.gameid);
						$("#qrform").submit();
					});
					var tempname = data.tempname;
					var index =  tempname.indexOf("_");
					if(index > 0 ){
						tempname = tempname.substring(0,index)+"/";
					}
					$("#release-textmsg").val(wechatGameURI+tempname+data.tempname+"?gameid="+data.gameid);
					$('#release-confirm').modal('show');
					
				});
				
				
				
				$("#table_id").on("click",".release",function(){
					var data = table.row($(this).parents('tr')).data();
					$("#delete-textmsg").text("确定要发布活动 "+data.gamename+" 吗？");
					$('#delete-confirm').modal('show');
					$("#delete-confirm").off("click","#deleteConfirm");
					$("#delete-confirm").on("click","#deleteConfirm",function(){
						$('#delete-confirm').modal('hide');
						$.ax({
							"url" : "${ctxPath}/mcmanage/release",
							"data" : {"gameid":data.gameid,"status":"1"},
							successfn : function(result) {
								if (result.result == 1) {
									table.ajax.reload();
									$("#qrcode").attr("src","${ctxPath}/mcmanage/showQR?gameid="+data.gameid);
									$("#downqr").off("click");
									$("#downqr").on("click",function(){
										$("#qrid").val(data.gameid);
										$("#qrform").submit();
									});
									var tempname = data.tempname;
									var index =  tempname.indexOf("_");
									if(index > 0 ){
										tempname = tempname.substring(0,index)+"/";
									}
									$("#release-textmsg").val(wechatGameURI+tempname+data.tempname+"?gameid="+data.gameid);
									/* $("#copyURI").trigger("click");
									$("#copyURI").trigger("click"); */
									$('#release-confirm').modal('show');
								} else {
									$("#errtextmsg").text(result.msg);
									$('#myModal-errmsg').modal('show');
								}
							}
						});
					});
				});
				
				$("#table_id").on("click",".repeal",function(){
					var data = table.row($(this).parents('tr')).data();
					$("#delete-textmsg").text("确定要撤销活动 "+data.gamename+" 吗？");
					$('#delete-confirm').modal('show');
					$("#delete-confirm").off("click","#deleteConfirm");
					$("#delete-confirm").on("click","#deleteConfirm",function(){
						$('#delete-confirm').modal('hide');
						$.ax({
							"url" : "${ctxPath}/mcmanage/release",
							"data" : {"gameid":data.gameid,"status":"2"},
							successfn : function(result) {
								if (result.result == 1) {
									table.ajax.reload();
									$("#textmsg").text(result.msg);
									$('#myModal-confirm').modal('show');
								}else{
									$("#errtextmsg").text(result.msg);
									$('#myModal-errmsg').modal('show');
								}
							}
						});
					});
				});
				
				$("#table_id").on("click",".modify",function(){
					var data = table.row($(this).parents('tr')).data();
					$("#gameid").val(data.gameid);
					$("#editform").attr("action","${dzpURI}"+data.deploypath);
					$("#editform").submit();
				});
				$("#table_id").on("click",".delete",function(){
					var data = table.row($(this).parents('tr')).data();
					$("#delete-textmsg").text("确定要删除该活动吗？");
					$('#delete-confirm').modal('show');
					$("#delete-confirm").off("click","#deleteConfirm");
					$("#delete-confirm").on("click","#deleteConfirm",function(){
						$('#delete-confirm').modal('hide');
						$.ax({
							"url" : "${ctxPath}/mcmanage/delete",
							"data" : {"gameid":data.gameid},
							successfn : function(result) {
								if (result.result == 1) {
									table.ajax.reload();
									$("#textmsg").text(result.msg);
									$('#myModal-confirm').modal('show');
								} else {
									$("#errtextmsg").text(result.msg);
									$('#myModal-errmsg').modal('show');
								}
							}
						});
					});
				});
				
				table = $('#table_id').DataTable({
					"serverSide" : true,//打开后台分页
					"dataSrc" : "aaData",
					"ajax" : function(dataModel, callback, settings) {
						/* var obj = serializeForm($('#queryform'));
						$.extend(dataModel, obj); */
						$.ax({
							"url" : "${ctxPath}/mcmanage/getdata",
							"data" : dataModel,
							successfn : function(result) {
								callback(result);
							}
						});
					},
					"columns" : [ {
						'data' : 'gameid',
						'visible' : false
					}, {
						'data' : 'tempname',
						'visible' : false
					}, {
						'data' : 'deploypath',
						'visible' : false
					},{
						'data' : 'gamename'
					}, {
						'data' : 'templatename'
					}, {
						'data' : function(obj) {
							if (obj.channel == "0") {
								return "微信";
							} else {
								return "应用";
							}
						}
					}, {
						'data' : 'begintime'
					}, {
						'data' : 'endtime'
					}, {
						'data' : function(obj) {
							if(obj.status == "0") {
								return "<div style='color:#ff0000'>未发布</div>";
							} else if(obj.status == "1"){
								return "<div style='color:#00ce05'>已发布</div>";
							} else	{
								return "已结束";
							}
						}
					}, {
						'data' : 'operator'
					}, {
						'data' : 'createtime'
					}, { 
						'data': function(obj){
		            	return	"<span><a class='release' title='发布' href='javascript:;'><i class='fa fa-file-text-o'></i></a></span>"+
		            			"<span><a class='code' title='二维码' href='javascript:;'><i class='fa fa-puzzle-piece'></i></a></span>"+
		            			"<span><a class='repeal' title='下线' href='javascript:;'><i class='fa fa-paper-plane-o'></i></a></span>"+
		            			"<span><a class='modify'  title='编辑' href='javascript:;'><i class='fa fa-edit'></i></a></span>"+
		            			"<span><a class='delete'  title='删除' href='javascript:;'><i class='fa fa-trash-o'></i></a></span>";
		            	}
		            } ]
				});
			});			
		</script>
</body>
</html>