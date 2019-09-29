<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>系统管理-消息管理</title>
</head>
<body>
	<form:form id="eform">
		<div class="sidebar-right">
			<div class="main-content">
				<div class="mainbox">
					<div class="query">
						<!-- <div class="query-tit">查询条件</div> -->
						<div class="query-text">
							<span>消息名称</span> 
							<input type="text" id="qmsgname"class="form-control">
						</div>
						<div class="query-text">
							<span>消息类型</span> <select id="qmsgtype" class="form-control"
								style="width: 93px;">
								<option value="" selected="selected">全部</option>
								<option value="0">文本</option>
								<option value="4">图文</option>
							</select>
						</div>
						<div class="query-btn">
							<button type="button" class="btn btn-primary" id="queryBtn">查询</button>
							<button type="button" class="btn btn-primary reset" id="resetBtn">重置</button>
						</div>
					</div>
					<div class="tablebox">
						<div class="table-tit">
							<div class="tit-left">自定义消息管理</div>
							<div class="tit-btn">
								<span>
									<button id="addTextMsgBtn" type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
										<i class="fa fa-plus"></i>新增文本消息
									</button>
								</span> <span>
									<button id="addPicMsgBtn" type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal2">
										<i class="fa fa-plus"></i>新增图文消息
									</button>
								</span>
							</div>
						</div>
						<table id="table_id" width="100%" border="0" align="center"
							valign="middle" class="tablemain">
							<thead>
								<tr class="tit-top">
									<th scope="col"><input type="checkbox" value="" id="checkAll"></th>
									<th scope="col">appid</th>
									<th scope="col">消息id</th>
									<th scope="col">消息名称</th>
									<th scope="col">消息类型</th>
									<th scope="col">素材id</th>
									<th scope="col">图文素材</th>
									<th scope="col">消息内容</th>
									<th scope="col">创建时间</th>
									<th scope="col">操作</th>
								</tr>
							</thead>
							<tbody id="tbody"></tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		</div>
	</form:form>
	<!-- sidebar-right end-->
	<!--add textarea-->
	<form:form id="eform1" modelAttribute="wxdmessagemodel1">
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="eform1myModalLabel">新增文本</h4>
					</div>
					<div class="modal-body">
						<!-- <form class="form-horizontal" id="addTextMsg" name="addTextMsg"> -->
						<input type="hidden" id="eform1msgid" name="msgId"
							class="form-control"> <input type="hidden"
							id="eform1msgtype" name="msgType" class="form-control">
						<input type="hidden" id="eform1platformid" name="appid"
							class="form-control">
						<div class="form-group">
							<label class="col-sm-2 control-label">消息名称</label>
							<div class="col-sm-8">
								<input type="text" id="eform1msgname" name="msgName"
									class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">消息内容</label>
							<div class="col-sm-8">
								<textarea id="eform1msgcontent" name="content"
									class="form-control" rows="6"></textarea>
							</div>
						</div>
						<!-- </form> -->
					</div>
					<div class="modal-footer">
						<button type="submit" id="confirmBtn" class="btn btn-primary">确定</button>
						<button type="button" id="closeBtn" class="btn btn-default"
							data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
	</form:form>
	<form:form id="eform2" modelAttribute="wxdmessagemodel1" action="">
		<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="eform2myModalLabel">新增图文</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal">
							<input type="hidden" id="eform2msgid" name="msgId" class="form-control"> <input type="hidden" id="eform2msgtype" name="msgType" class="form-control">
							<input type="hidden" id="eform2platformid" name="appid" class="form-control">
							<div class="form-group">
								<label class="col-sm-2 control-label">消息名称</label>
								<div class="col-sm-8">
									<input type="text" id="eform2msgname" name="msgName" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">选择素材</label>
								<div class="frm-box frm-box-sc">
									<div class="frm-tab source-pick">
										<div class="media-cover selectMedia">
											<a href="" id="meterialid" data-toggle="modal" data-target="#mediaModal"> <i class="fa fa-plus"></i> <em>从素材库中选择</em> </a>
										</div>
									</div>
									<input type="hidden" id="eform2mediaId" name="mediaId">
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="submit" id="confirmBtn" class="btn btn-primary">确定</button>
						<button type="button" id="closeBtn" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
	</form:form>
	<!-- Modal end-->
	<!-- Modal-->
	<form:form id="eform3" modelAttribute="wxdmessagemodel3" method="post" action="">
		<div class="modal fade" id="myModal-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<input type="hidden" id="delmsgid" name="delmsgid">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">提示信息</h4>
					</div>
					<div class="modal-body">
						<div class="modal-prompt deleteAlert">
							<i class="fa fa-exclamation"></i><span>确认删除消息<span id="delmsg"></span>吗？</span>								
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary deleteConfirm delMsgid">确定</button>
						<button type="button" class="btn btn-default deleteCancle" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
	</form:form>
	<!-- Modal-->
	<div class="modal fade" id="mediaModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" style="width: 750px;">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">选择素材</h4>
				</div>
				<div class="modal-body">
					<div class="library">
						<div class="brary-main clear newsData"></div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary brarybtn">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
	<!-- Modal end-->
	<!-- Modal end-->
		<script type="text/javascript" >
		var table;
		$(function() {
			table = $('#table_id').DataTable({
				"ajax": function (data, callback, settings) {
	            	$.ax({
	    				url : "${ctxPath}/message/ajax/findByMessage",
	    				data : function(){
	    					var obj = new Object();
	    					obj.appid = "${appid2}";
	    					obj.msgName = $.trim($('#qmsgname').val());
	    					obj.msgType = $('#qmsgtype').val();
	    					return obj;
	    				},
	    				successfn : function(result) {
				        	callback(result);
				        },
				        errorfn : function(result) {
							$("#errtextmsg").text("网络异常!");
							$('#myModal-errmsg').modal('show');
						}
	    			});
	              },
	              "columns" : [
	   						{
	   							"defaultContent" : "<input type='checkbox' name='subBox' onClick='checkOne()' class='roleCheck'>","visible" : false
	   						},
	   						{
	   							'data' : 'appid',"visible" : false
	   						},
	   						{
	   							'data' : 'msgId',"visible" : false
	   						},
	   						{
	   							'data' : 'msgName'
	   						},
	   						{
	   							'data' : function(obj) {
	   								var sReturn = obj.msgType;
	   								if (sReturn == "0") {
	   									sReturn = "文本";
	   								} else if (sReturn == "4") {
	   									sReturn = "图文";
	   								}
	   								return sReturn;
	   							}
	   						},
	   						{
	   							'data' : 'mediaId',"visible" : false
	   						},
	   						{
	   							'data' : 'mediaName',"visible" : false
	   						},
	   						{
	   							'data' : 'content',"visible" : false
	   						},
	   						{
	   							'data' : 'createTime',"visible" : false
	   						},
	   						{
	   							'data' : function(obj) {
	   								var sReturn = obj.msgType;
	   								if (sReturn == "0") {
	   									sReturn = "<td>"
	   											+ "<span><button type='button' class='btn btn-primary editMsg' data-toggle='modal' data-target='#myModal'><i class='fa fa-edit'></i>编辑</button></span>"
	   											+ "<span><button type='button' class='btn btn-primary delMsg' data-toggle='modal' data-target='#myModal-delete'><i class='fa fa-trash-o'></i>删除</button></span>"
	   											+ "</td>";
	   								} else if (sReturn == "4") {
	   									sReturn = "<td>"
	   											+ "<span><button type='button' class='btn btn-primary editMsg' data-toggle='modal' data-target='#myModal2'><i class='fa fa-edit'></i>编辑</button></span>"
	   											+ "<span><button type='button' class='btn btn-primary delMsg' data-toggle='modal' data-target='#myModal-delete'><i class='fa fa-trash-o'></i>删除</button></span>"
	   											+ "</td>";
	   								}
	   								return sReturn;
	   							}
	   						}
				]
			});
		});
		/*条件查询*/
		$(".query-btn").on("click", "#queryBtn", function() {
			 table.ajax.reload();
		});
		/*点击添加文本按钮时，清空输入框数据（因与文本消息修改使用同一model），修改action为添加文本消息请求路径*/
		$(".tablebox").on("click","#addTextMsgBtn",function() {
			$("#eform1")[0].reset();
			$('#eform1myModalLabel').text('新增文本消息');
			$('#eform1msgtype').val('0');
			$('#eform1platformid').val('${appid2}');
			url='${ctxPath}/message/ajax/textMsgAdd'
		});
		$('#eform1').validate({
			rules : {
				msgName : {
					notnull : true,
					isRightfulString : true,
					required : true,
					mlength : 20
				},
				content : {
					required : true,
					mlength : 500
				}
			},
			submitHandler : function(form) {
				var params = serializeForm($('#eform1'));
				addMsg(params,url);
			}
		});
		$("#myModal").on('hidden.bs.modal', function() {
			$("#eform1").validate().resetForm();
			$("#eform1")[0].reset();
			$(".select").prop("disabled", "false");
		});
		$("#myModal2").on('hidden.bs.modal', function() {
			$("#eform2").validate().resetForm();
			$("#eform2")[0].reset();
			$(".select").prop("disabled", "false");
		});
		/*点击添加图文按钮时，清空输入框数据（因与图文消息修改使用同一model），修改action为添加图文消息请求路径*/
		$(".tablebox").on("click","#addPicMsgBtn",function() {
			$("#eform2")[0].reset();
			$("#eform1")[0].reset();
			$("#box-del").prev(".brarybox").remove();
			$("#box-del").remove();
			$(".media-cover").show();
		$('#eform2myModalLabel').text('新增图文消息');
			$('#eform2msgtype').val('4');
			$('#eform2mediaId').val('');
			$('#eform2platformid').val('${appid2}');
			 url='${ctxPath}/message/ajax/picMsgAdd'
		});
		$('#eform2').validate({
			rules : {
				msgName : {
					notnull : true,
					isRightfulString : true,
					required : true,
					mlength : 20
				},
				mediaId : {
					notnull : true,
					required : true
				}
			},
			submitHandler : function(form) {
				var params = serializeForm($('#eform2'));
				addMsg(params,url);
			}
		});
		/*单个修改,初始化数据，修改action为修改路径*/
		$(".tablemain").on("click",".editMsg",function() {
			var data = table.row($(this).parents('tr')).data();
			if (data.msgType == '0') {
				$('#eform1myModalLabel').text('修改文本消息');
				$('#eform1msgid').val(data.msgId);
				$('#eform1msgtype').val(data.msgType);
				$('#eform1msgname').val(data.msgName);
				$('#eform1msgname').attr("disabled",true);
				$('#eform1msgcontent').val(data.content);
			} else if (data.msgType == '4') {
				$('#eform2myModalLabel').text('修改图文消息');
				$('#eform2msgid').val(data.msgId);
				$('#eform2msgtype').val(data.msgType);
				$('#eform2msgname').val(data.msgName);
				$('#eform2msgname').attr("disabled",true);
				$('#eform2mediaId').val(data.mediaId);
				$.ax({
					url : "${ctxPath}/menu/selectMaterialByMsgid",
					data : {
						"appid" : '${appid2}',
						"msgid" : data.msgId
					},
					successfn : function(data) {
						if (data.result == 1) {
							materialData = eval(data.obj);
							$.each(materialData,function(index,item) {
								var htmlData = "";
								//获取图文明细
								var matDetail = materialData[index].detailModels;
								htmlData = htmlData
										+ '<div class="brarybox" onmouseover="mouseover(this);" onmouseout="mouseout(this);">';
								htmlData = htmlData
										+ '<input type="hidden" id="imagemsgId" value="' + materialData[index].materialId + '" >';
								htmlData = htmlData
										+ '<dl><dt>'
										+ materialData[index].materialName
										+ '</dt>';
								htmlData = htmlData
										+ '<dd>'
										+ materialData[index].updateTime
										+ '</dd></dl>';
								$.each(matDetail,function(index,item) {
									if (index == 0) {
										//第一个素材明细
										htmlData = htmlData
												+ '<div class="material-img"><img src=${ctxPath}/material/show?url=' + matDetail[index].thumbMediaUrl + '>';
										htmlData = htmlData
												+ '<span>'
												+ matDetail[index].materialTitle
												+ '</span></div>';
									} else {
										// 除第一个以外的素材明细
										htmlData = htmlData
												+ '<div class="brary-template"><ul>';
										htmlData = htmlData
												+ '<li class="brary-template-t">';
										htmlData = htmlData
												+ '<span>'
												+ matDetail[index].materialTitle
												+ '</span>';
										htmlData = htmlData
												+ '<i><img src=${ctxPath}/material/show?url=' + matDetail[index].thumbMediaUrl + '></i>';
										htmlData = htmlData
												+ '</li></ul></div>';
									}
								});
								htmlData = htmlData+ '<div class="braryboxbg" onclick="braryboxbgclick(this);"><span><i class="fa fa-check okTag" id="tag1"></i></span></div>';
								htmlData = htmlData+ '</div>';
								$(".source-pick").children('.brarybox').remove();
								$(".source-pick").children('#box-del').remove();
								$(".source-pick").append(htmlData);
								$(".source-pick").append('<span id="box-del">删除</span>');
								$(".media-cover").hide();	
							});
						};
					}
				});
			}
			url='${ctxPath}/message/ajax/Msgedit';
		});
		/*单个删除*/
		$("#table_id").on("click", ".delMsg", function() {
			var data = table.row($(this).parents('tr')).data();
			$("#delmsgid").val(data.msgId);
			$("#delmsg").html(data.msgName);
		});
		$("#myModal-delete").on("click", ".delMsgid", function() {
			var msgId = $("#delmsgid").val();
			var params = {
				"delmsgid" : msgId
			};
			$("#eform3")[0].reset();
			deletMsg(params);
		});
		function deletMsg(params) {
			$.ax({
				url : '${ctxPath}/message/ajax/delete',
				data : params,
				successfn : function(result) {
					//1 关闭窗口
					$("#textmsg").empty();
					$('.modal').modal('hide');
					if (result.result == 1) {
						table.ajax.reload();
						$("#textmsg").empty();
						$("#textmsg").text(result.msg);
						$('#myModal-confirm').modal('show');
					} else if (result.result == 0) {
						$("#errtextmsg").text(result.msg);
						$('#myModal-errmsg').modal('show');
					} else {
						$("#errtextmsg").text(result.msg);
						$('#myModal-errmsg').modal('show');
					}
				},
				errorfn : function(result) {
					$('.modal').modal('hide');
					$("#errtextmsg").text("网络异常!");
					$('#myModal-errmsg').modal('show');
				}
			});
		};
		function addMsg(params,url) {
			$.ax({
				url : url,
				data : params,
				successfn : function(result) {
					//1 关闭窗口
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
				errorfn : function(result) {
					$('.modal').modal('hide');
					$("#errtextmsg").text("网络异常!");
					$('#myModal-errmsg').modal('show');
				}
			});
		}
		</script>
		<script type="text/javascript">
		$(function() {
			var $checked = $(".inlineCheckbox")
			$checked.click(function() {
				if ($checked.is(':checked')) {
					$(".select").prop("disabled", false);
				} else {
					{
						$(".select").prop("disabled", "disabled");
					}
				}
			});
		});
	</script>
	<script type="text/javascript">
		var materialData;
		var datahtml = '';
		// 选择图文消息
		$(document).ready(function() {
				$.ax({
					url : "${ctxPath}/material/selectMaterialNewsListByPlatformId",
					data : {"appid" : '${appid2}'},
					successfn : function(data) {
						if (data.result == 1) {
							materialData = eval(data.obj);
							$.each(
								materialData,
								function(index,
										item) {
									var htmlData = "";
									//获取图文明细
									var matDetail = materialData[index].detailModels;
									htmlData = htmlData
											+ '<div class="brarybox" onmouseover="mouseover(this);" onmouseout="mouseout(this);">';
									htmlData = htmlData
											+ '<input type="hidden" id="imagemsgId" value="' + materialData[index].materialId + '" >';
									htmlData = htmlData
											+ '<dl><dt>'
											+ materialData[index].materialName
											+ '</dt>';
									htmlData = htmlData
											+ '<dd>'
											+ materialData[index].updateTime
											+ '</dd></dl>';
									$.each(
										matDetail,
										function(index,item) {
											if (index == 0) {
												//第一个素材明细
												htmlData = htmlData
														+ '<div class="material-img"><img src=${ctxPath}/material/show?url=' + matDetail[index].thumbMediaUrl + '>';
												htmlData = htmlData
														+ '<span>'
														+ matDetail[index].materialTitle
														+ '</span></div>';
											} else {
												// 除第一个以外的素材明细
												htmlData = htmlData
														+ '<div class="brary-template"><ul>';
												htmlData = htmlData
														+ '<li class="brary-template-t">';
												htmlData = htmlData
														+ '<span>'
														+ matDetail[index].materialTitle
														+ '</span>';
												htmlData = htmlData
														+ '<i><img src=${ctxPath}/material/show?url=' + matDetail[index].thumbMediaUrl + '></i>';
												htmlData = htmlData
														+ '</li></ul></div>';
											}
										});
									htmlData = htmlData	+ '<div class="braryboxbg" onclick="braryboxbgclick(this);"><span><i class="fa fa-check okTag" id="tag1"></i></span></div>';
									htmlData = htmlData + '</div>';
									datahtml = datahtml + htmlData;
									$(".newsData").append(htmlData);
								});
						 };
					},
					errorfn : function(data){
						$("#errtextmsg").text("网络异常!");
						$('#myModal-errmsg').modal('show');
					}
				});
		});
		//点击图文选择加号
		$('#meterialid').click(function() {
			$(".newsData").children().remove();
			$(".newsData").append(datahtml);
		});
		//从素材库选择图文消息(确定按钮)
		$(".brarybtn").click(function() {
			var e = $(".braryboxbg").not(":hidden");
			if (e.length <= 0) {
				$("#errtextmsg").text("请选择素材！");
				$('#myModal-errmsg').modal('show');
				return;
			} else {
				var del = '<span id="box-del">删除</span>';
				var addbox = e.parent(".brarybox");
				var imageid = e.parent(".brarybox").children('input').val();
				$("#eform2mediaId").val(imageid);
				addbox.find(".braryboxbg").replaceWith("");
				$(".source-pick").append(addbox);
				$(".source-pick").append(del);
				$(".close2").click();
				$(".media-cover").hide();
				$("#mediaModal").modal('hide');
			}
		});
		//删除图文消息
		$(".source-pick").on("click", "#box-del", function() {
			var e = $(".braryboxbg").not(":hidden");
			$("#eform2mediaId").val('');
			$("#box-del").prev(".brarybox").remove();
			$("#box-del").remove();
			$(".media-cover").show();
		});
		/*图文素材*/
		function mouseover(e) {
			var bg = $(e).children(".braryboxbg");
			bg.show();
			$(".braryboxbg").not(bg).not(
					$(".okTag").not(":hidden").parent().parent("div")).hide();
		}
		function mouseout(el) {
			var e = $(el).find(".okTag");
			if (e.is(":hidden")) {
				$(el).children(".braryboxbg").hide();
			} else {
				$(el).children(".braryboxbg").show();
			}
		}
		function braryboxbgclick(el) {
			$(".braryboxbg").not($(el)).hide();
			var e = $(el).find(".okTag");
			$(".okTag").not(e).hide();
			if (e.is(":hidden")) {
				e.show();
			} else {
				e.hide();
			}
		}
		//查询重置按钮
		$(".query-btn").on("click", "#resetBtn", function() {
			$("#qmsgname").val("");
			$("#qmsgtype").val("");
		});
	</script>
</body>
</html>