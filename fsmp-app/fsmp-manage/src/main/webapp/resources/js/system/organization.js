var addModifyFlg;
var deleteId;
var table;
$(function() {
	// 新增按钮押下
	$("#addOrganBtn").click(function() {
		document.getElementById("orgId").readOnly=false;
		$("#myModalLabel").html("新增")
		$(".parentOrgid").prop("disabled",false);
		$(".uncleOrgid").prop("disabled",false);
		$(".parentOrgid option").prop("selected",false);
		$(".uncleOrgid>option[value!='']").remove();
		queryParentsOrgs("");
		addModifyFlg = "add";
	});
	// 关闭按钮押下
	$("#closeBtn").click(function() {
		$('#eform1')[0].reset();
		// $(".modal").hide();
		$("#closeBtn").attr("data-dismiss", "modal");
	});
	//查询重置按钮
	$(".query-btn").on("click","#resetBtn",function() {
		$("#queryOrgId").val("");
		$("#queryOrgName").val("");
	});
	//设置新增dialog的隐藏监听
	$('#myModal').on('hidden.bs.modal', function (e) {
		$('#eform1')[0].reset();
		$('#eform1').validate().resetForm();
		});
	
	$(".parentOrgid").change(function(e){
		var parentId = $(".parentOrgid option:selected").val();
		if(parentId !="0"){
			var params = {"parentId":parentId};
			queryUncleOrgs(params,"");
		}
	});
	
	// 检索按钮押下
	$("#queryBtn").click(function() {
		table.ajax.reload();
	});
	// 初始化datatables数据
	refreshTable();
	// 获取datatables数据方法
	function refreshTable() {
			table = $('#tableList')
						.DataTable(
						{
							"aaSorting" : [ [ 0, "esc" ] ],// 设置第1个元素为默认排
							"aoColumnDefs" : [ {
								"bSearchable" : false, // bSearchable：是否可搜索；
								"bVisible" : true, // bVisible：是否可见；
								"aTargets" : [ 0,1,3,5 ], // aTargets：哪一列;
							},
							{
				                  "targets": [2,4],//隐藏列
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
								"url" :$("#ctx").val() + "/organize/ajax/getData",
								"type":"POST",
								"beforeSend" : function(XMLHttpRequest) {
									XMLHttpRequest.setRequestHeader($(
											"#csrfheader").val(), $(
											"#csrftoken").val());
								},
								"data" : function(d){
									var orgName = $("#queryOrgName").val();
									var orgId = $("#queryOrgId").val();
								if (orgName != "") {
									d.orgName = $.trim(orgName); //添加额外的参数传给服务器
								}
								if (orgId != "") {
									d.orgId = $.trim(orgId); //添加额外的参数传给服务器
								}
								}
							},
							"columns" : [
									{
										'data' : 'orgId'
									},
									{
										'data' : 'orgName'
									},
									{
										'data' : 'parentOrgid'
									},
									{
										'data' : 'parentName'
									},
									{
										'data' : 'uncleOrgid'
									},
									{
										'data' : 'uncleName'
									},
//									{
//										'data' : 'descript'
//									},
//									{
//										'data' : 'address'
//									},
//									{
//										'data' : function(obj) {
//											var sReturn = obj.status;
//											if (sReturn == "1") {
//												sReturn = "激活";
//											} else if (sReturn == "0") {
//												sReturn = "失效";
//											}
//											return sReturn;
//										}
//									},
									{
										'defaultContent' : "<td>"
												+ "<span><button type='button' class='btn btn-primary editOrganization' data-toggle='modal' data-target='#myModal'><i class='fa fa-edit'></i>编辑</button></span>&nbsp;"
												+ "<span><button type='button' class='btn btn-primary delOrganization'><i class='fa fa-trash-o'></i>删除</button></span>"
												+ "</td>"
									}

							/*
							 * { "defaultContent": "<input type=\"button\"
							 * value=\"qqqqqq\">"}
							 */
							],

						});
		}

	/* 单个修改 */
	$(".tablemain").on("click", ".editOrganization", function() {
		document.getElementById("orgId").readOnly=true;
		$("#myModalLabel").html("更新");
		addModifyFlg = "modify";
		var data = table.row($(this).parents('tr')).data();
		$('#orgId').val(data.orgId);
		$('#orgName').val(data.orgName);

		$(".parentOrgid").prop("disabled",true);
		$(".uncleOrgid").prop("disabled",true);
		$(".parentOrgid option").prop("selected",false);
		queryParentsOrgs(data.parentOrgid);
		var parentId = data.parentOrgid;
		if(parentId !="0"){
			var params = {"parentId":parentId};
			queryUncleOrgs(params,data.uncleOrgid);
		}
		$('#status').val(data.status);
//		$('#address').val(data.address);
		$('#descript').val(data.descript);
	});
	/* 单个删除 */
	$(".tablemain").on("click", ".delOrganization", function() {
		var data = table.row($(this).parents('tr')).data();
		deleteId = data.orgId;
		deleteDialogShow(data.orgName);
	});
	// 新增表单校验
	$('#eform1').validate({
		// debug: true,
		rules : {
			orgId : {
				orgIdCheck:true,
				notnull:true,
				required : true,
				maxlength : 30
			},
			orgName : {
				isRightfulString :true,
				notnull:true,
				required : true,
				maxlength : 100
			},
			parentOrgid : {
				required : true,
				maxlength : 30
			},
			uncleOrgid : {
				maxlength : 30
			}
			/*,
			status : {
				notnull:true,
				required : true,
				maxlength : 2
			},
			
			descript : {
				maxlength : 120
			}*/
		},
		submitHandler: function(form) {
			
			var orgName = $("#orgName").val();
			var parent = $(".parentOrgid option:selected").val();
			var uncle = $(".uncleOrgid option:selected").val();
			var params = {"orgId": $("#orgId").val(),"orgName" : orgName,"parentOrgid":parent,"uncleOrgid":uncle,"status":"1"};	
			var addConfirmUrl = "";
			var showMes="";
			if (addModifyFlg == "add") {
				addConfirmUrl = $("#ctx").val()
						+ '/organize/ajax/addOrganization';
				showMes="新增";
			} else if (addModifyFlg == "modify") {
				addConfirmUrl = $("#ctx").val()
						+ '/organize/ajax/modifyOrganizationInfo';
				showMes="修改";
			}
			$.ax({
				url : addConfirmUrl,
				cache : false,
				data : params,
				dataType : 'json',
				successfn : function(result) {
					// 1 关闭窗口
					$('.modal').modal('hide');
					if (result.success) {
						$("#textmsg").empty();
						table.ajax.reload();
						$("#textmsg").text(result.msg);
						$('#myModal-confirm').modal('show');
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
		},
	});
	// 删除dialog显示
	function deleteDialogShow(params) {
		$(".deleteAlert span").text("确认删除机构 " + params + " 吗？");
		$("#myModal-delete").modal('show');
	}
	// 点击确认删除按钮
	$(".deleteConfirm").click(function() {
		batchDeleteOrgan({
			"orgId" : deleteId
		});
	});
	function batchDeleteOrgan(param) {
		$.ax({
			url : $("#ctx").val() + '/organize/ajax/delOrganizationById',
			data : param,
			successfn : function(result) {
				$('.modal').modal('hide');
				$("#textmsg").empty();
    	    	table.ajax.reload();
				if (result.success) {
					$("#textmsg").text("删除机构成功");
					$('#myModal-confirm').modal('show');
					table.ajax.reload();
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
	}
	function queryParentsOrgs(parentId){
		$(".parentOrgid>option[value!='0']").remove();
		$.ax({
			url:$("#ctx").val() + "/organize/ajax/getParentOrgans",
			cache : false,
	        dataType : 'json',
			successfn:function(result){
				$(result.data).each(function(){
					var id = $(this).attr("orgId");
					var name = $(this).attr("orgName");				
					$(".parentOrgid").append(" <option value="+id+">"+name+"</option>");
					if(id == parentId){
						$(".parentOrgid option[value="+parentId+"]").prop("selected",true);
					}
				});
			},
	        errorfn : function(){
	        	$("#errtextmsg").text("网络异常!");
	        	$('#myModal-errmsg').modal('show');
	        }			
		});
	}
	function queryUncleOrgs(params,uncleId){
		$(".uncleOrgid>option[value!='']").remove();
		$.ax({
			url:$("#ctx").val() + "/organize/ajax/getUncleOrgans",
			cache : false,
			data:params,
	        dataType : 'json',
			successfn:function(result){
				$(result.data).each(function(){
					var id = $(this).attr("orgId");
					var name = $(this).attr("orgName");			
					$(".uncleOrgid").append(" <option value="+id+">"+name+"</option>");
					if(uncleId == id){
						$(".uncleOrgid option[value='"+uncleId+"']").prop("selected",true);
					}
				});
			},
	        errorfn : function(){
	        	$("#errtextmsg").text("网络异常!");
	        	$('#myModal-errmsg').modal('show');
	        }			
		});
	}
});