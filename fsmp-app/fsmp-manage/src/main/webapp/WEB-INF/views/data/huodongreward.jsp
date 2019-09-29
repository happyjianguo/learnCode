<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>内管系统-数据管理</title>
</head>
<body>
	<div class="main-content">
			<div class="mainbox">
				<div class="query">
					<form id="query">
						<div class="query-tit">查询条件</div>
						<div class="query-text">
							<span>渠道</span>
							<select id="channel" name="channel" class="form-control select">
								<option value="" selected>请选择</option>
								<option value="0">微信</option>
								<!-- 需要时启用 -->
								<!-- <option value="1">APP</option> -->
							</select>
						</div>
						<div class="query-text" id="account">
							<span>公众号</span>
							<select id="appid" name="appid" class="form-control select">
								<option value="" selected>请选择</option>
							</select>
						</div>
						<div class="query-text" id="game">
							<span>活动</span>
							<select id="gameid" name="gameid" class="form-control select" >
								<option value="" selected>请选择</option>
							</select>
						</div>
						<div class="query-text">
							<span>日期范围</span>
							<div class="col-sm-6">
								<input type="text" value="${dateVO.startDay}" id="date_1" readonly class="form_datetimepicker">
						    </div>
						</div>
						<div class="query-text">
							<span>至</span>
							<div class="col-sm-6">
								<input type="text" value="${dateVO.endDay}" id="date_2" readonly class="form_datetimepicker">
						    </div>
						</div>
						<div class="query-btn">
							<button type="button" class="btn btn-primary" id="ok">查询</button>
							<!-- <button type="button" class="btn btn-primary reset" id="resetBtn">重置</button> -->
						</div>
					</form>
				</div>
				<div class="tablebox">
					<table id="table_id" class="tablemain">
							<thead>
								  <tr class="tit-top">
								    <th scope="col">奖品设置ID</th>
								    <th scope="col">奖品ID</th>
								    <th scope="col">活动ID</th>
								    <th scope="col">奖品名称</th>
								    <th scope="col">奖品类型</th>
								    <th scope="col">奖品总数量</th>
								    <th scope="col">已领取奖品数量</th>
								    <th scope="col">中奖概率（%）</th>
								    <th scope="col">中奖位置</th>
								    <th scope="col">创建时间</th>
								    <th scope="col">更新时间</th>
								  </tr>
							</thead>
						 	<tbody id="tbody"></tbody>
						</table>
				</div>
			</div>
		</div>
	</div>	
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>		
		<script type="text/javascript" >
		var table;
		$(function() {
			//初始化隐藏总行机构和分行机构下拉框
			document.getElementById("account").style.display="none";//隐藏
			document.getElementById("game").style.display="none";//隐藏  
			//渠道改变触发
		    $(".query-text").on("change","#channel",function(){
		    	//渠道改变下拉框值置空
		    	$("#appid").val('');
				$("#gameid").val('');
				$("#date_1").val("${dateVO.startDay}");
				$("#date_2").val("${dateVO.endDay}");
		    	//初始化隐藏总行机构和分行机构下拉框
				document.getElementById("account").style.display="none";//隐藏
				document.getElementById("game").style.display="none";//隐藏  
		    	initChannel();
		    });
			//公众号改变触发
		    $(".query-text").on("change","#appid",function(){
		    	getgameid();
		    });
			/*初始化加载页面*/
		    getData();
		});
		/* 根据渠道显示查询条件 */
		function initChannel(){
			/* 微信渠道 */
			if($("#channel").val()=='0'){
				/* 初始化隐藏总行机构和分行机构下拉框 */
				document.getElementById("account").style.display="";//显示
				document.getElementById("game").style.display="";//显示	
				getappid();
			/* app渠道 */
			}else if($("#channel").val()=='1'){
				document.getElementById("game").style.display="";//显示
				getgameid();
			}else if($("#channel").val()==''){
				document.getElementById("account").style.display="none";//隐藏
				document.getElementById("game").style.display="none";//隐藏  
			}
		}
		/* 获取公众号信息 */
    	function getappid(){
    		$.ax({
        		url  :"${ctxPath}/huodongdata/ajax/initappid",
        		//data : {orgid:$('#orgid').val()},
		        successfn : function(result) {	
	        		$("#appid").find("option").not("[value='']").remove(); 
		        	$.each(result.data, function(i, item){ 
	        			$("#appid").append("<option value="+item.appid+">"+item.nickName+"</option>");
		        	});  	
		        }
        	});
    	}
		/* 获取活动 */
    	function getgameid(){
    		$.ax({
        		url  :"${ctxPath}/huodongdata/ajax/initgameid",
        		data : {appid:$('#appid').val(),orgid:$('#orgid').val(),channel:$("#channel").val()},
		        successfn : function(result) {	
	        		$("#gameid").find("option").not("[value='']").remove(); 
		        	$.each(result.data, function(i, item){ 
	        			$("#gameid").append("<option value="+item.gameid+">"+item.gamename+"</option>");
		        	});  	
		        }
        	});
    	}
		$("#ok").on("click", function() {
			if($("#channel").val().length==0){
				$("#errtextmsg").text("请选择渠道！");
				$('#myModal-errmsg').modal('show');
				return false
			}	;
			if($("#channel").val().length!=0){
				if($("#channel").val() == 0){
					if($("#appid").val().length==0){
						$("#errtextmsg").text("请选择公众号！");
						$('#myModal-errmsg').modal('show');
						return false
					}	;
					if($("#gameid").val().length==0){
						$("#errtextmsg").text("请选择活动！");
						$('#myModal-errmsg').modal('show');
						return false
					}	;
				}else{
					if($("#gameid").val().length==0){
						$("#errtextmsg").text("请选择活动！");
						$('#myModal-errmsg').modal('show');
						return false
					}	;
				}
			}	;
			getData();
		});
		//日期控件
		$('.form_datetimepicker').datetimepicker({
		    format: 'yyyy-mm-dd hh:ii',
		    language: "zh",
		    minuteStep: 1,
		    autoclose: true,
		    todayBtn: true
		});
		/* 得到数据 */
		function getData(){
			table = $('#table_id').DataTable({
				"ajax": function (data, callback, settings) {
                   	$.ax({
                   		 url : "${ctxPath}/huodongreward/ajax/getdata",
                   		 data : {
	                   			startDate : $("#date_1").val(),
	         					endDate : $("#date_2").val(),
	         					gameid : $("#gameid").val()
         						},
   				         successfn : function(result) {
   				        	callback(result);
   				         },
	   				      errorfn : function(errorMsg) {
								$("#errtextmsg").text("网络异常!");
								$('#myModal-errmsg').modal('show');
						}
                   	});
                  },
				"columns" : [
						{
							'data' : 'prizeparamid' ,'visible' : false
						},
						{
							'data' : 'prizeid' ,'visible' : false
						},
						{
							'data' : 'gameid' ,'visible' : false
						},
						{
							'data' : 'prizename'
						},
						{
							'data' : function(obj){
								var prizetype = obj.prizetype;
								if(prizetype == 'hf'){
									prizetype = "话费";
								}else if(prizetype == 'll'){
									prizetype = "流量";
								}else if(prizetype == 'sw'){
									prizetype = "实物";
								}
								return prizetype;
							}
						},
						{
							'data' : 'prizetotalnum'
						},
						{
							'data' : 'prizenum'
						},
						{
							'data' : 'winrate'
						},
						{
							'data' : 'prizeposition' ,'visible' : false
						},
						{
							'data' : 'createtime' ,'visible' : false
						},
						{
							'data' : 'updatetime'
						}
				 ]
			});
		}
		$("#resetBtn").on("click",  function() {
			document.getElementById("account").style.display="none";//隐藏
			document.getElementById("game").style.display="none";//隐藏  
			$("#orgid").val("");
			$("#channel").val("");
			$("#gameid").val("");
			$("#appid").val("");
		});
		</script>
</body>
</html>