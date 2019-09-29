var addModifyFlg;
var deleteId;
$(function(){
	$("#addStationBtn").click(function(){
		document.getElementById("netId").readOnly=false;
		$("#myModalLabel").html("新增")
		addModifyFlg = "add";
	});
	$("#closeBtn").click(function(){
		$('#eform1')[0].reset();
//		$(".modal").hide();
		$("#closeBtn").attr("data-dismiss","modal"); 
	});
	$("#myModal").on('hidden.bs.modal',function(){
		$("#eform1").validate().resetForm();
		$("#eform1")[0].reset();
	});
	var table;
	table=$('#tableList').DataTable({
		 	"autoWidth": false,
		 	"paging": true,
            "aaSorting": [[ 0, "esc" ]],//设置第1个元素为默认排           
            "ajax" : {
            	"error":function (XMLHttpRequest, textStatus, errorThrown) {
            		if(XMLHttpRequest.readyState != 0){
						$("#errtextmsg").text("网络异常!");
						$('#myModal-errmsg').modal('show');
					}
            	},
				"url" : $("#ctx").val()+"/netStation/ajax/getData",
				"type": "POST",
				"beforeSend":function (XMLHttpRequest) {
					XMLHttpRequest.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
				},
				"data" : function(d) {
					var netName = $('#netName').val();
					var netStatus = $('#netStatus').val();
					if (netName != "") {
						d.netName = $.trim(netName); //添加额外的参数传给服务器
					}
					if (netStatus != "") {
						d.netStatus = $.trim(netStatus); //添加额外的参数传给服务器
					}
				}
			},
            "columns": 
            [  
			   { 'data': 'netId'},
               { 'data': 'netName'},
               { 'data': 'parentNetId' ,"visible": false},
               { 
            	   'data':  function(obj) {
                       var sReturn = obj.netStatus;
                       if ( sReturn == "0" ) {
                           sReturn = "营业网点";
                       }
//                       else if(sReturn == "2"){
//                    	   sReturn = "自助银行";
//                       }
                       else if(sReturn == "1"){
                    	   sReturn = "ATM";
                       }
                       return sReturn;
                   }
               },
               { 'data': 'netAddress'},
               { 'data': 'phoneNo'},
               { 'data': 'longitude',"visible": false },
               { 'data': 'latitude',"visible": false },
               { 'data': function(obj) {
                   var longitude = obj.longitude;
                   var latitude = obj.latitude;
                   return longitude+","+latitude}
               },
               { 'data': 'descript'},
               { 'defaultContent': 
                   "<td>"+
			    		"<span><button type='button' class='btn btn-primary editNetStation' data-toggle='modal' data-target='#myModal'><i class='fa fa-edit'></i>编辑</button></span>"+
			    		"<span><button type='button' class='btn btn-primary delNetStation'><i class='fa fa-trash-o'></i>删除</button></span>"+
			    	"</td>"
            	}
            ] ,
			
	    });
	/*单个修改*/
    $(".tablemain").on("click",".editNetStation",function(){
    	document.getElementById("netId").readOnly=true;
    	addModifyFlg = "modify";
    	$("#myModalLabel").html("更新");
    	var data = table.row( $(this).parents('tr') ).data();
        $('#netId').val(data.netId);
        $('#netName1').val(data.netName);
        $('#netStatus').val(data.netStatus);
        $('#netAddress').val(data.netAddress);
        $('#phoneNo').val(data.phoneNo);
        $('#longitude').val(data.longitude);
        $('#latitude').val(data.latitude);
        $('#descript').val(data.descript);
    });
    /*单个删除*/
	$(".tablemain").on("click",".delNetStation",function(){
		var data = table.row( $(this).parents('tr') ).data();
		deleteId = data.netId;
		deleteDialogShow(data.netId);
		
	});
	// 新增表单校验
	$('#eform1').validate({
		// debug: true,
		rules : {
			netId : {
				isRightfulString :true,
				notnull :true,
				required : true,
				maxlength : 40
			},
			netName : {
				isRightfulString :true,
				notnull :true,
				required : true,
				maxlength : 60
			},
			netStatus :{
				isRightfulString :true,
				required : true,
				maxlength : 2
			},
			netAddress : {
				notnull :true,
				required : true,
				maxlength : 200
			},
			longitude : {
				notnull :true,
				required : true,
				maxlength : 15
			},
			latitude : {
				notnull :true,
				required : true,
				maxlength : 15
			},
			descript : {
				notnull :true,
				required : true,
				maxlength : 500
			},
			phoneNo : {
				mobile:true,
				notnull :true,
				required : true,
				maxlength : 11
			}
		},
		submitHandler: function(form) {
			var params = serializeForm($('#eform1'));
			$('#eform1')[0].reset();
			var addConfirmUrl = "";
			var textMsg="";
			if (addModifyFlg == "add") {
				addConfirmUrl = $("#ctx").val() + '/netStation/ajax/addNetStation';
				textMsg="新增";
			} else if (addModifyFlg == "modify") {
				addConfirmUrl = $("#ctx").val() + '/netStation/ajax/modifyNetStationInfo';
				textMsg="更新";
			}
			$.ajax({
				type: 'post' ,
				url: addConfirmUrl,
				cache:false ,
	   			data:params,
	   			beforeSend:function (XMLHttpRequest) {
					XMLHttpRequest.setRequestHeader($("#csrfheader").val(), $("#csrftoken").val());
				},
	            dataType:'json' ,
			    success:function(result){
					//1 关闭窗口
			    	$('#myModal').modal('hide');
			    	$("#textmsg").empty();
			    	table.ajax.reload();
			    	var resultflag = result.result;
			    	if(addModifyFlg == "modify"){
			    		if(result.result == 1){
			    			resultflag = 0;
			    		}else{
			    			resultflag = 1;
			    		}
			    	}
					if(resultflag == 1 ){
						$("#textmsg").text(result.msg);
						$('#myModal-confirm').modal('show');
					}else{
						$("#errtextmsg").text(result.msg);
						$('#myModal-errmsg').modal('show');
					}
					
				},
				error:function(result){
					$("#errtextmsg").text("网络异常!");
					$('#myModal-errmsg').modal('show');
				}
			});
			},
		
	});
	//删除dialog显示
	function deleteDialogShow(params){
		$(".deleteAlert span").text("确认删除网点 "+params+" 吗？");
		$("#myModal-delete").modal('show');
	} 
	//点击确认删除按钮
	$(".deleteConfirm").click(function(){
		batchDeleteNet({"netId":deleteId});
	});
	function batchDeleteNet(param){
		$.ax({
			url: $("#ctx").val() + '/netStation/ajax/delNetStationById',
			data:param,
			successfn: function(result){
				$('#myModal-delete').modal('hide');
				$("#textmsg").empty();
				if(result.success){
					table.ajax.reload();
					$("#textmsg").text(result.msg);
					$('#myModal-confirm').modal('show');
	    	    } else {
	    	    	$("#errtextmsg").text(result.msg);
	    	    	$('#myModal-errmsg').modal('show');
	    	    }
				
			},
			errorfn:function(result){
				$('#myModal-delete').modal('hide');
				$("#errtextmsg").text("网络异常!");
				$('#myModal-errmsg').modal('show');
			}
		});
	}
	/*条件查询*/
	$("#queryBtn").on("click",  function() {
		table.ajax.reload();
	});
	/*条件重置*/
	$("#resetBtn").on("click",  function() {
		$("#netName").val("");
		$("#netStatus").val("");
	});
});