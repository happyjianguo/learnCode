var addModifyFlg;
var deleteId;
$(function() {
	$("#addPlatformBtn").click(function() {
		document.getElementById("platformId").readOnly=false;
		$("#myModalLabel").html("新增");
		addModifyFlg = "add";
	});
	$("#closeBtn").click(function() {
		$('#eform1')[0].reset();
		$(".modal").hide();
		$("#closeBtn").attr("data-dismiss", "modal");
	});
	$('#reqUrl').keyup(function(){
		$("#reqUrlh").text($(this).val());
	});
	var table;
	table = $('#tableList')
			.DataTable(
					{
						"aaSorting" : [ [ 0, "esc" ] ],// 设置第1个元素为默认排
						"aoColumnDefs" : [ {
							"bSearchable" : false, // bSearchable：是否可搜索；
							"bVisible" : true, // bVisible：是否可见；
							"aTargets" : [ 2, 4 ], // aTargets：哪一列;
						}, {
							"targets" : [ 6 ],// 隐藏列
							"visible" : false
						} ],

						"ajax" : {
							"error":function (XMLHttpRequest, textStatus, errorThrown) {
								if(XMLHttpRequest.readyState != 0){
									$("#errtextmsg").text("网络异常!");
									$('#myModal-errmsg').modal('show');
								}
		                	},
		                	"url":$("#ctx").val() + "/platform/ajax/getData"},
						"columns" : [
								{
									'data' : 'platformId'
								},
								{
									'data' : 'wxName'
								},
								{
									'data' : 'appid'
								},
								{
									'data' : 'secret'
								},
								{
									'data' : 'token'
								},
								// { 'data': 'country' },
								// { 'data': 'province' },
								// { 'data': 'city' },
								// { 'data': 'updatetime' },
								{
									'data' : 'reqUrl'
								},
								// { 'data': 'signature' },
								{
									'data' : 'verifyInfo'
								},
								{
									'defaultContent' : "<td>"
											+ "<span><button type='button' class='btn btn-primary editPlatform' data-toggle='modal' data-target='#myModal'><i class='fa fa-edit'></i>编辑</button></span>"
											+ "<span><button type='button' class='btn btn-primary delPlatform'><i class='fa fa-trash-o'></i>删除</button></span>"
											+ "</td>"
								} ],
					});
	/* 单个修改 */
	$(".tablemain").on("click", ".editPlatform", function() {
		document.getElementById("platformId").readOnly=true;
		addModifyFlg = "modify";
		$("#myModalLabel").html("更新");
		var data = table.row($(this).parents('tr')).data();
		$('#platformId').val(data.platformId);
		$('#wxName').val(data.wxName);
		$('#appid').val(data.appid);
		$('#secret').val(data.secret);
		$('#token').val(data.token);
		$('#reqUrl').val(data.reqUrl);
		$("#reqUrlh").text(data.reqUrl);
		$('#verifyInfo').val(data.verifyInfo);
	});
	/* 单个删除 */
	$(".tablemain").on("click", ".delPlatform", function() {
		var data = table.row($(this).parents('tr')).data();
		deleteId = data.platformId;
		deleteDialogShow(data.platformId);
	});
	$("#myModal").on("hidden.bs.modal",function(e){
		$("#eform1").validate().resetForm();
		$("#eform1")[0].reset();
	});
	// 新增表单校验
	$('#eform1').validate(
			{
				// debug: true,
				rules : {
					platformId : {
						pwsCheck :true,
						notnull :true,
						required : true,
						maxlength : 40
					},
					wxName : {
						isRightfulString :true,
						notnull :true,
						required : true,
						maxlength : 100
					},
					appid : {
						pwsCheck :true,
						required : true,
						maxlength : 50
					},
					secret : {
						pwsCheck :true,
						required : true,
						maxlength : 50
					},

					token : {
						pwsCheck :true,
						required : true,
						maxlength : 32
					},
					reqUrl : {
						orgIdCheck :true,
						required : true,
						maxlength : 20
					},verifyInfo: {
						maxlength : 1000
					},

				},
				submitHandler : function(form) {
					var params = serializeForm($('#eform1'));
					var addConfirmUrl = "";
					showMes="";
					if (addModifyFlg == "add") {
						addConfirmUrl = $("#ctx").val() + '/platform/ajax/add';
						showMes="新增";
					} else if (addModifyFlg == "modify") {
						addConfirmUrl = $("#ctx").val()
								+ '/platform/ajax/modifyPlatformById';
						showMes="修改";
					}
					$.ax({
						url : addConfirmUrl,
						cache : false,
						data : params,
						dataType : 'json',
						successfn : function(result) {
							// 1 关闭窗口
							if (result.success) {
								$('.modal').modal('hide');
								$("#textmsg").empty();
								table.ajax.reload();
								$("#textmsg").text(result.msg);
								$('#myModal-confirm').modal('show');
								$("#eform1")[0].reset();
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
			});
	function deleteDialogShow(params) {
		$(".deleteAlert span").text("确认删除公众号 " + params + " 吗？");
		$("#myModal-delete").modal('show');
	}
	// 点击确认删除按钮
	$(".deleteConfirm").click(function() {
		batchDeletePlatform({
			"platformId" : deleteId
		});
	});
	function batchDeletePlatform(param) {
		$.ax({
			url : $("#ctx").val() + '/platform/ajax/delPlatformById',
			data : param,
			successfn : function(result) {
				$('.modal').modal('hide');
				$("#textmsg").empty();
				if (result.success) {
					$("#textmsg").text("删除公众号成功")
					$('#myModal-confirm').modal('show');
				}else{
					$("#errtextmsg").text(result.msg);
					$('#myModal-errmsg').modal('show');
				}
				table.ajax.reload();
			},
			errorfn : function(result) {
				$('.modal').modal('hide');
				$("#errtextmsg").text("网络异常!");
				$('#myModal-errmsg').modal('show');
			}
		});
	}
});
function delTr(o) {
	$(o).parents('tr').each(
			function(i) {
				var td = $(this).find("td");
				$.ax({
					url : $("#ctx").val() + '/platform/ajax/delPlatformById',
					cache : false,
					data : {
						'platformId' : td.eq(1).text()
					},
					dataType : 'json',
					successfn : function(result) {
						// 1 关闭窗口
						if(result.success){
							$("#textmsg").text(result.msg);
							$('#myModal-confirm').modal('show');
						}else{
							$("#errtextmsg").text(result.msg);
							$('#myModal-errmsg').modal('show');
						}
						
					},
					errorfn : function(result) {
						$("#errtextmsg").text("网络异常!");
						$('#myModal-errmsg').modal('show');
					}
				});
			});
}
function modifyTr(o) {
	addModifyFlg = "modify";
	$('#addPlatform')[0].reset();
	$("#myModalLabel").html("更新");
	$(o).parents('tr').each(function(i) {
		var td = $(this).find("td");
		$("#wxName").val(td.eq(0).text());
		$("#platformId").val(td.eq(1).text());
		$("#appid").val(td.eq(2).text());
		$("#secret").val(td.eq(3).text());
		$("#token").val(td.eq(4).text());
		$("#reqUrl").val(td.eq(5).text());
		$("#signature").val(td.eq(6).text());
		$("#verifyInfo").val(td.eq(7).text());
	});
	$("#platformId").attr("readonly", "readonly");
	$(o).attr({
		"data-target" : "#myModal",
		"data-toggle" : "modal"
	});
}

