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
		<div class="list-nav">
			<ol class="breadcrumb">
				<li>数据管理</li>
				<li class="active" id="bill_check">单个活动数据分析</li>
			</ol>
		</div>
			<div class="mainbox">
				<div class="query">
					<form id="query">
						<div class="query-tit">查询条件</div>
						<div class="query-text">
							<span>机构</span>
							<select id="orgid" name="orgid" class="form-control select" >
								<option value="" selected>请选择</option>
								<c:forEach var="acc" items="${org}">
									<option value="${acc.id}">${acc.orgname}</option>
								</c:forEach>
							</select>
						</div>
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
					<!-- <div class="table-tit">
						<div class="tit-left" >
							单个活动数据分析
						</div>
					</div> -->
					<div class="follow_info">
						<span class="follow follow_term3">总数<span id="follow_term1"></span></span> 
						<span class="follow follow_term2">总PV：<span id="follow_term2"></span></span> 
						<span class="follow follow_term1">总UV：<span id="follow_term3"></span></span>
					</div>
					<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
					<div class="follow_line clear">
					<div id="main" style="height: 600px"></div>
				</div>
			</div>
		</div>
	</div>			
		<script type="text/javascript" >
		$(function() {
			//初始化隐藏总行机构和分行机构下拉框
			document.getElementById("account").style.display="none";//隐藏
			document.getElementById("game").style.display="none";//隐藏  
			//渠道改变触发
		    $(".query-text").on("change","#channel",function(){
		    	//初始化隐藏总行机构和分行机构下拉框
				document.getElementById("account").style.display="none";//隐藏
				document.getElementById("game").style.display="none";//隐藏  
				$("#gameid").val("");
				$("#appid").val("");
		    	initChannel();
		    });
			//机构改变触发
		    $(".query-text").on("change","#orgid",function(){
		    	document.getElementById("account").style.display="none";//隐藏
				document.getElementById("game").style.display="none";//隐藏  
				$("#channel").val("");
				$("#gameid").val("");
				$("#appid").val("");
				$("#date_1").val("${dateVO.startDay}");
				$("#date_2").val("${dateVO.endDay}");
		    	getappid();
		    });
			//公众号改变触发
		    $(".query-text").on("change","#appid",function(){
		    	getgameid();
		    });
			pageinit();
		});
		//日期控件
		$('.form_datetimepicker').datetimepicker({
		    format: 'yyyy-mm-dd hh:ii',
		    language: "zh",
		    minuteStep: 1,
		    autoclose: true,
		    todayBtn: true
		});
		//根据渠道显示查询条件
		function initChannel(){
			//微信渠道
			if($("#channel").val()=='0'){
				//初始化隐藏总行机构和分行机构下拉框
				document.getElementById("account").style.display="";//显示
				document.getElementById("game").style.display="";//显示	
			//app渠道
			}else if($("#channel").val()=='1'){
				document.getElementById("game").style.display="";//显示
				getgameid();
			}else if($("#channel").val()==''){
				document.getElementById("account").style.display="none";//隐藏
				document.getElementById("game").style.display="none";//隐藏  
			}
		}
		//获取公众号信息
    	function getappid(){
    		$.ax({
        		url  :"${ctxPath}/huodongdata/ajax/initappid",
        		data : {orgid:$('#orgid').val()},
		        successfn : function(result) {	
	        		$("#appid").find("option").not("[value='']").remove(); 
		        	$.each(result.data, function(i, item){ 
	        			$("#appid").append("<option value="+item.appid+">"+item.nickName+"</option>");
		        	});  	
		        }
        	});
    	}
		//获取活动
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
		
		var myChart;
		// 使用
		function pageinit() {
			// 基于准备好的dom，初始化echarts图表
			myChart = echarts.init(document.getElementById('main'));

			var option = {
				title : {
					text : '单个活动数据'//,
					//subtext : '纯属虚构'
				},
				tooltip : {
					trigger : 'axis'//触发类型，默认数据触发，见下图，可选为：'item' | 'axis'
				},
				legend : {
					//data : [ '累计关注人数', '关注人数', '取消关注人数' ]
					data : [ 'PV', 'UV' ]
				},
				toolbox : {
					show : true,
					feature : {
						mark : {
							show : true
						}, //辅助线
						dataZoom : {
							show : true
						}, //区域缩放
						dataView : {
							show : true,
							readOnly : false
						},//数据
						magicType : {
							show : true,
							type : [ 'line', 'bar' ]
						}, //可转换的表
						restore : {
							show : true
						}, //还原数据
						saveAsImage : {
							show : true
						}
					//保存图片
					}
				},
				calculable : true,
				notMerge : true,//不合并
				xAxis : [ {
					type : 'category',
					boundaryGap : false,
					data : [ 0 ]
				} ],
				yAxis : [ {
					type : 'value',
					axisLabel : {
						formatter : '{value}/人'
					}
				} ],
				series : [ {
					name : 'PV',
					type : 'line',
					data : [ 0 ],
					markPoint : {
						data : [ {
							type : 'max',
							name : '最大值'
						}, {
							type : 'min',
							name : '最小值'
						} ]
					},
					markLine : {
						data : [
						// 						         {
						// 							type : 'average',
						// 							name : '平均值'
						// 						} 
						]
					}
				}, {
					name : 'UV',
					type : 'line',
					data : [ 0 ],
					markPoint : {
						data : [ {
							type : 'max',
							name : '最大值'
						}, {
							type : 'min',
							name : '最小值'
						} ]
					},
					markLine : {
						data : [
						// 						        {
						// 							type : 'average',
						// 							name : '平均值'
						// 						} 
						]
					}
				}
				/* , 
				{
					name : '取消关注人数',
					type : 'line',
					data : [ 1, 2, 2, 5, 3, 2, 0 ],
					markPoint : {
						data : [ {
							type : 'max',
							name : '最大值'
						}, {
							type : 'min',
							name : '最小值'
						} ]
					},
					markLine : {
						data : [
						// 						        {
						// 							type : 'average',
						// 							name : '平均值'
						// 						} 
						]
					}
				}  */
				]
			};
			// 为echarts对象加载数据 
			myChart.setOption(option);
			myChart.hideLoading();
			getData();
		}
		$("#ok").on("click", function() {
			if($("#date_1").val()>$("#date_2").val()){
				$("#errtextmsg").text("开始时间不能大于结束时间，请重新选择！");
				$('#myModal-errmsg').modal('show');
				return false
			}	;
			if($("#orgid").val().length==0){
				$("#errtextmsg").text("请选择机构！");
				$('#myModal-errmsg').modal('show');
				return false
			}	;
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
		//得到数据
		function getData(){
			//获得图表的options对象
			var options = myChart.getOption();
			var data = {
					startDate : $("#date_1").val(),
					endDate : $("#date_2").val(),
					gameid : $("#gameid").val()
				};
			$.ax({
				async : false, //同步执行
				url : "${ctxPath}/huodongdata/ajax/getData",
				data : data,
				dataType : "json", //返回数据形式为json
				successfn : function(result) {
					if(result){
						$("#follow_term2").text(result.data.pv);
						$("#follow_term3").text(result.data.uv);
						options.legend.data = result.data.echartData.legend;
						options.xAxis[0].data = result.data.echartData.category;
						options.series[0].data = result.data.echartData.series[0].data;
						options.series[1].data = result.data.echartData.series[1].data;
						myChart.setOption(options,true);//不合并加载数据
						myChart.hideLoading();
					}
				},
				errorfn : function(errorMsg) {
					$("#errtextmsg").text("网络异常!");
					$('#myModal-errmsg').modal('show');
					myChart.hideLoading();
				}
			});
		}
		$("#resetBtn").on("click",  function() {
		$("#date_1").val("${dateVO.startDay}");
		$("#date_2").val("${dateVO.endDay}");
		/* $("#appid").val(""); */
		});
		</script>
</body>
</html>