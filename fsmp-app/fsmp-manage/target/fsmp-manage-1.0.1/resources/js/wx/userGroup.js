var groupSelType = "init";
var checkLength = 0;
var table;
var changeGroupId;
$(function(){
	tabs();
	//新增分组页面关闭按钮
	$("#addGroupCloseBtn").click(function(){
		$('#eform1')[0].reset();
		$("#addGroupCloseBtn").attr("data-dismiss","modal"); 
	});
	//修改分组页面关闭按钮
	$("#modifyGroupCloseBtn").click(function(){
		$('#modifyGroupForm')[0].reset();
		$("#modifyGroupCloseBtn").attr("data-dismiss","modal"); 
	});
	$("#addGroupModal").on('hidden.bs.modal',function(){
		$("#eform1").validate().resetForm();
		$("#eform1")[0].reset();
	});
	$("#modifyGroupModal").on('hidden.bs.modal',function(){
		$("#modifyGroupForm").validate().resetForm();
		$("#modifyGroupForm")[0].reset();
	});
	//删除分组页面关闭按钮
	$("#delGroupCloseBtn").click(function(){
		$('#delGroupForm')[0].reset();
		$("#delGroupCloseBtn").attr("data-dismiss","modal"); 
	});
	//移动用户分组页面关闭按钮
	$("#moveCloseBtn").click(function(){
		$('#moveGroupForm')[0].reset();
		$("#moveCloseBtn").attr("data-dismiss","modal"); 
	});
	// 变更用户分组
	$("#initGroupName").change(function(){
		var params = {"groupFlg":$("#initGroupName").val()};
		var refreshUrl = $("#ctx").val() + "/userGroup/ajax/getUserList";
		groupSelType = $("#initGroupName").val();
		// 刷新datatables数据
		refreshTable(params, refreshUrl);
	});
	/*根据openId查看绑定详情*/
    $(".tablemain").on("click",".viewDetail",function(){
        var data = table.row( $(this).parents('tr') ).data();
        $(".showCardNum").nextAll().remove();
        $("#OpenID").val(data.openId);
		$("#nickName").val(data.nickName);
		if(data.sex == "2"){
			$("#sex").val("女");
		}else{
			$("#sex").val("男");
		}
		$("#group").val(data.groupName);
		$("#subScribeTime").val(data.subScribeTime);
		$('#myModa2').modal('show');
    });
    //查询按钮按下
    $("#queryBtn").click(function(){
    	table.ajax.reload();
    });
    //初始化datatables数据
    refreshTable("", $("#ctx").val() + "/userGroup/ajax/getData");
    //获取datatables数据方法
    function refreshTable(){
      table=$('#tableList').DataTable({
    	  "autoWidth": false,
    	  "paging": true,
    	  "aaSorting":[[1,"esc"]], //设置第1个元素为默认排
    	  "aoColumnDefs": [
    	      { "bSearchable": false, //bSearchable:是否可搜索；
    	    	"bVisible": true,     //bVisible:是否可见；
    	    	"aTargets": [2,4],    //aTargets:哪一列；
              },
             {
            	"targets": [7,8],     //隐藏列
            	"visible": false
             }
           ],
           "ajax": {
        	   "error":function (XMLHttpRequest, textStatus, errorThrown){
        		   if(XMLHttpRequest.readyState != 0){
        			   $("#errtextmsg").text("网络异常！");
        			   $('#myModal-errmsg').modal('show');       			         			   
        		   }
        	   },
        	     "url" : $("#ctx").val() + "/userGroup/ajax/getUserList",
        	     "type": "POST",
        	     "beforeSend": function (XMLHttpRequest) {
        	    	 XMLHttpRequest.setRequestHeader($("#csrfheader").val(), $("#csrftoken").val());
        	     },
        	     "data": function(d){
        	    	    var groupFlg = $("#initGroupName").val();
        	    		var nickName = $('#queryNickName').val();
        	    		var openId = $('#queryOpenid').val();
        	    		var startDate = $('#date_1').val();
        	    		var endDate = $('#date_2').val();
        	    		var appid = $('#appid').val();
        	    		var refreshType = "queryDatas";
        	    			if($("#date_1").val()>$("#date_2").val()){
        	    			$("#errtextmsg").text("开始时间不能大于结束时间，请重新选择！");
        	    			$('#myModal-errmsg').modal('show');
        	    			return false
        	    				};
        	    		if(groupFlg != ""){
        	    			d.groupFlg = $.trim(groupFlg);
        	    		}
        	    		if(nickName != ""){
        	    			d.nickName = $.trim(nickName);
        	    		}
        	    		if(openId != ""){
        	    			d.openId = $.trim(openId);
        	    		}
        	    		if(appid != ""){
        	    			d.appid = $.trim(appid);
        	    		}
        	    		d.startDate = startDate;
        	    		d.endDate = endDate;    	   
        	     }
           },        	     
     			"columns": [
    						{ "defaultContent": "<input type='checkbox' name='subBox' class='groupCheck'>"},
    						{ 'data': 'openId' },
    						{ 'data': 'nickName' }, 
    						{ 'data': function(obj) {
    							var sReturn = obj.sex;
    							if ( sReturn == "2" ) {//“2”表示女
    								sReturn = "女";
    							}else if(sReturn == "1"){
    								sReturn = "男";
    							}
    							return sReturn;
    						}},
    						{ 'data': function(obj) {
    							var sReturn = obj.country + obj.province + obj.city;
    							return sReturn;
    							},"visible": false}, 
    							{ 'data': 'groupName' }, 
    							{ 'data': 'subScribeTime' }, 
    							{ 'data': 'groupId' }, 
    							{ 'data': 'appid' }, 
    							{ 'defaultContent': 
    								"<td>"+
    								"<span><button type='button' class='btn btn-primary viewDetail' data-toggle='modal'><i class=''fa fa-pencil''></i>查看详情</button></span>&nbsp;"+
    								"</td>"
    							}
    					] ,        	     
      });
    }
    //刷新分组组别
	refreshSelected(groupSelType);
	//获取分组组别
	function refreshSelected(selType){
		//初始化页面的当前分组下拉列表
		$('#initGroupName option').remove();
		$.ax({
			url: $("#ctx").val() + "/userGroup/ajax/getGroupList",
			data:{"groupSelType":selType,"appid":$('#appid').val()},
			successfn: function(data){
				var json = eval(data); //数组  
				$.each(json, function (index, item) {  
		            //循环获取数据
		            var groupId = json[index].groupId;  
		            var groupName = json[index].groupName;  
		            $('#initGroupName').append("<option value=" + groupId + ">" + groupName + "</option>");
		            $("#initGroupName").val("allUser");
		        });
				$('.selectpicker').selectpicker('refresh');
			},
	        errorfn : function(){
	        	$("#errtextmsg").text("网络异常!");
	        	$('#myModal-errmsg').modal('show');
	        }			
		});
	}
	// 新增组名按钮按下，弹出新增组名页面进行新增
	$("#addBtn").click(function(){
		$("#addGroupModal").modal('show');
		$('#eform1')[0].reset();
	});
	$("#modifyGroupForm").validate({
		rules:{
			newGroupName:{
				isRightfulString :true,
				notnull :true,
				required : true,
				mlength : 100
			}
		},
		submitHandler: function(form) {
			var params = serializeForm($('#modifyGroupForm'));
			groupSelType = "init";
			$.ax({
				url: $("#ctx").val() + '/userGroup/ajax/modifyGroupName',
				cache:false ,
	   			data:params,
	            dataType:'json' ,
			    successfn:function(result){
					if(result.result == 1){
						//1 关闭窗口
				    	$('#modifyGroupModal').modal('hide');
				    	$("#textmsg").empty();
						$("#initGroupName").children('option').remove();
						table.ajax.reload();
						refreshSelected(groupSelType);
						$("#initGroupName").val("allUser");
						$("#textmsg").text(result.msg);
						$('#myModal-confirm').modal('show');
					}else{
						$("#errtextmsg").text(result.msg);
						$('#myModal-errmsg').modal('show');
					}
				},
				errorfn:function(result){
					$('.modal').modal('hide');
					$("#errtextmsg").text("网络异常!");
					$('#myModal-errmsg').modal('show');
				}
			});
		}
	});
	// 新增表单校验
	$('#eform1').validate({
		// debug: true,
		rules : {
			addGroupName : {
				isRightfulString :true,
				notnull :true,
				required : true,
				mlength : 30
			}
		},
		submitHandler: function(form) {
			var params = serializeForm($('#eform1'));
			$('#eform1')[0].reset();
			$('.modal').modal('hide');
			$.ax({
				url: $("#ctx").val() + '/userGroup/ajax/addNewGroup',
				cache:false ,
	   			data:params,
	            dataType:'json' ,
			    successfn:function(result){
					//1 关闭窗口
					if(result.result == 1){
						$("#initGroupName").val("allUser");
						$("#textmsg").empty();
						$("#textmsg").text(result.msg);
						$("#initGroupName").children('option').remove();
						$('#myModal-confirm').modal('show');
						refreshSelected(groupSelType);
					}else{
						$("#errtextmsg").text(result.msg);
						$('#myModal-errmsg').modal('show');
					}
				},
				errorfn:function(result){
					$('.modal').modal('hide');
					$("#errtextmsg").text("网络异常!");
					$('#myModal-errmsg').modal('show');
				}
			});
		},
	});
	// 修改组名按钮按下，弹出修改页面进行修改
	$("#modifyBtn").click(function(){
		groupSelType = "modify";
		$("#oldGroupId").children('option').remove();
		$("#modifyGroupModal").modal('show');
		$('#modifyGroupForm')[0].reset();
		/*获取组别列表*/
		$.ax({
			url: $("#ctx").val() + "/userGroup/ajax/getGroupList",
			data:{"groupSelType":groupSelType,"appid":$('#appid').val()},
			successfn: function(data){
				$("#initGroupName").val("allUser");
				var json = eval(data); //数组 
				$.each(json, function (index, item) {
		            //循环获取数据    
		            var groupId = json[index].groupId;
		            var groupName = json[index].groupName; 
		            $('#oldGroupId').append("<option value=" + groupId + ">" + groupName + "</option>");
		        });
				$('.selectpicker').selectpicker('refresh');
			},
	        errorfn : function(){
	        	$("#errtextmsg").text("网络异常!");
	        	$('#myModal-errmsg').modal('show');
	        }			
		});
	});
	// 删除组名按钮按下，弹出删除页面进行删除
	$("#delBtn").click(function(){
		groupSelType = "del";
		$('#delGroupForm')[0].reset();
		$("#delGroupId").children('option').remove();
		$("#delGroupModal").modal('show');
		/*获取组别列表*/
		$.ax({
			url: $("#ctx").val() + "/userGroup/ajax/getGroupList",
			data:{"groupSelType":groupSelType,"appid":$('#appid').val()},
			successfn: function(data){
				var json = eval(data); //数组 
				$.each(json, function (index, item) {
		            //循环获取数据    
		            var groupId = json[index].groupId;
		            var groupName = json[index].groupName; 
		            $('#delGroupId').append("<option value=" + groupId + ">" + groupName + "</option>");
		        });
				$('.selectpicker').selectpicker('refresh');
			},
	        errorfn : function(){
	        	$("#errtextmsg").text("网络异常!");
	        	$('#myModal-errmsg').modal('show');
	        }			
		});
	});
	// 删除分组名称确认按钮按下，向微信服务器发删除请求
	$("#delGroupConfirmBtn").click(function(){
		var params = serializeForm($('#delGroupForm'));
		$('#delGroupForm')[0].reset();
		groupSelType = "init";
		$.ax({
			url: $("#ctx").val() + '/userGroup/ajax/delgroup',
			cache:false ,
   			data:params,
            dataType:'json' ,
		    successfn:function(result){
				if(result.result == 1){
					$("#initGroupName").val("allUser");
					//1 关闭窗口
			    	$('#delGroupModal').modal('hide');
			    	$("#textmsg").empty();
					$("#initGroupName").children('option').remove();
					$("#textmsg").text("删除分组成功");
					$('#myModal-confirm').modal('show');
					table.ajax.reload();
					refreshSelected(groupSelType);
				}else{
					$("#errtextmsg").text(result.msg);
					$('#myModal-errmsg').modal('show');
				}
			},
			errorfn:function(result){
				$('.modal').modal('hide');
				$("#errtextmsg").text("网络异常!");
				$('#myModal-errmsg').modal('show');
			}
		});
	});
	var moveArray;
	// 移动用户分组按钮按下，弹出移动用户分组页面进行移动
	$("#moveBtn").click(function(){
		groupSelType = "move";
		$('#moveGroupForm')[0].reset();
		$("#toGroupId").children('option').remove();
		$("#moveGroupModal").modal('show');
		moveArray = new Array();
		//将选中的OPENID放到集合里
		$.each(table.rows('.ckecked').data(),function (i,v) {
			moveArray.push(this.openId);
        });
		/*获取组别列表*/
		$.ax({
			url: $("#ctx").val() + "/userGroup/ajax/getGroupList",
			data:{"groupSelType":groupSelType,"appid":$('#appid').val()},
			successfn: function(data){
				var json = eval(data); //数组 
				$.each(json, function (index, item) {
		            //循环获取数据    
		            var groupId = json[index].groupId;
		            var groupName = json[index].groupName; 
		            $('#toGroupId').append("<option value=" + groupId + ">" + groupName + "</option>");
		        });
				$('.selectpicker').selectpicker('refresh');
			},
	        errorfn : function(){
	        	$("#errtextmsg").text("网络异常!");
	        	$('#myModal-errmsg').modal('show');
	        }			
		});
	});
	// 移动分组名称确认按钮按下，向微信服务器发移动请求
	$("#moveConfirmBtn").click(function(){
		groupSelType = "init";
		document.getElementById("checkAll").checked = false;
		var userListMap = new Object();
		userListMap.userOpenIdList = moveArray;
		$.ax({
			url: $("#ctx").val() + '/userGroup/ajax/moveUserToNewGroup',
			cache:false ,
   			data:{"touser":JSON.stringify(userListMap),"toGroupId":$('#toGroupId').val(),"appid":$('#appid').val()},
            dataType:'json' ,
		    successfn:function(result){
				//1 关闭窗口
				if(result.result == 1){
					$("#initGroupName").val("allUser");
			    	$('.modal').modal('hide');
			    	$("#textmsg").empty();
			    	table.ajax.reload();
			    	$('#moveGroupForm')[0].reset();
					$("#initGroupName").children('option').remove();
					$("#textmsg").text(result.msg);
					$('#myModal-confirm').modal('show');
					$("#initGroupName").val("allUser");
					refreshSelected(groupSelType);
				}else{
					$("#errtextmsg").text(result.msg);
					$('#myModal-errmsg').modal('show');
				}
			},
			errorfn:function(result){
				$('.modal').modal('hide');
				$("#errtextmsg").text("网络异常!");
				$('#myModal-errmsg').modal('show');
			}
		});
	});
	var openIdArr;
	//添加群发消息点击事件
	$("#sendGroupBtn").click(function(){
		var length = $(".ckecked").length;
		var array = new Array();
		if(length < 2){
			$("#errtextmsg").text("请选择至少两个用户进行消息群发!");
			$('#myModal-errmsg').modal('show');
			return;
		}
		$.each(table.rows('.ckecked').data(),function (i,v) {
			openIdArr.push(this.openId);;
        });
		$('#sendModal').modal('show');
	});
	// 全选按钮按下
	$("#checkAll").click(function(){
		checkAll();
	});
	//某行被选中
	$(".tablemain").on("click",".groupCheck",function(){
        $(this).parents("tr").toggleClass('ckecked');
    });
//全选事件
function checkAll() {
	var eles = $("input[name=subBox]");
	if ($("#checkAll").is(":checked")) {
		//选中每一个checkbox
		eles.each(function(){
			$(this).prop("checked",true);
			$(this).parents("tr").addClass('ckecked');
		});
	} else {
		eles.each(function(){
			$(this).removeAttr("checked",false);
			$(this).parents("tr").removeClass('ckecked');
		});
    }
}
function tabs(){
	$(".frm-group span").click(function(){
		var thisIndex = $(this).parent(".frm-group").children("span").index(this);
		$(this).addClass ('cru').siblings().removeClass ('cru');		
		$(".frm-centent").hide();
		$(".frm-centent:eq("+thisIndex+")").slideDown(0);
	});
	$('.frm-group span:eq(0)').trigger("click");
	$(".frm-title ul li").click(function(){
		var thisIndex = $(this).parent("ul").children("li").index(this);
		$(this).addClass ('cru').siblings().removeClass ('cru');		
		$(".frm-box .frm-tab").hide();
		$(".frm-box .frm-tab:eq("+thisIndex+")").slideDown(0);
	});
	$('.frm-title ul li:eq(0)').trigger("click");
	$(".mobile-nav li span").click(function(){		
		$(this).siblings(".mobile-nav-list").show();
		$(".mobile-nav li span").not(this).siblings(".mobile-nav-list").hide();		
	});
	$(".mobile-nav ul li").eq(0).children("span").trigger("click");
	$(".brarybox").mousemove(function(){
		$(this).children(".braryboxbg").show()		
	});
	$(".brarybox").mouseout(function(){
		$(this).children(".braryboxbg").hide()	
	});	
}
$(".query-btn").on("click", "#resetBtn", function() {
	$("#queryNickName").val("");
	$("#queryOpenid").val("");
	$("#date_1").val($("#startDay").val());
	$("#date_2").val($("#endDay").val());
});
})
