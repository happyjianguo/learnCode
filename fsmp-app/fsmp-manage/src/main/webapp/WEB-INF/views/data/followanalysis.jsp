<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>数据管理-用户关注分析</title>
<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
</head>
<body style="background: #f3f3f3;">
	<div class="centent">
		<div class="sidebar-right">
			<div class="main-content">
			<div class="mainbox">
				<div class="query">
					<form id="query">
						<!-- <div class="query-tit">查询条件</div> -->
						<div class="query-text">
							<span>公众号</span>
							<select id="appid" name="appid" class="form-control select">
								<option value="" selected>请选择</option>
								<c:forEach var="acc" items="${account}">
									<option value="${acc.appid}" ${acc.appid == defaultappid ? 'selected': ''}>${acc.nickName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="datetime datetime-t">
							<div class="input-append date date2 ">
								<span class="span_d">至</span> <input type="text" id="date_2"
									class="date_2 form_datetime" name="endTime"
									value="${dateVO.endDay}" readonly="readonly" />
							</div>
							<div class="input-append date date1">
								<span class="span_d">日期范围</span> <input type="text" id="date_1"
									class="date_1 form_datetime" name="beginTime"
									value="${dateVO.startDay}" readonly="readonly">
							</div>
						</div>
						<div class="query-btn">
							<button type="button" class="btn btn-primary" id="ok">查询</button>
							<!-- <button type="button" class="btn btn-primary reset" id="resetBtn">重置</button> -->
						</div>
					</form>
				</div>
					<div class="tablebox">
						<div class="follow_info">
							<span class="follow follow_term2">累积关注总人数：<span id="follow_term1"></span></span> 
							<span class="follow follow_term1">关注总人数：<span id="follow_term2"></span></span> 
							<span class="follow follow_term3">取消关注总人数：<span id="follow_term3"></span></span>
						</div>
						<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
						<div class="follow_line clear">
							<div id="main" style="height: 600px"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- sidebar-right end -->
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
	<script type="text/javascript">
		//日期控件
		$('.form_datetimepicker').datetimepicker({
		    format: 'yyyy-mm-dd hh:ii',
		    language: "zh",
		    minuteStep: 1,
		    autoclose: true,
		    todayBtn: true
		});
		$(function() {
			pageinit();
		});
		var myChart;
		// 使用
		function pageinit() {
			// 基于准备好的dom，初始化echarts图表
			myChart = echarts.init(document.getElementById('main'));

			var option = {
				title : {
					text : '关注分析'//,
				//subtext : '纯属虚构'
				},
				tooltip : {
					trigger : 'axis'//触发类型，默认数据触发，见下图，可选为：'item' | 'axis'
				},
				legend : {
					data : [ '累计关注人数', '关注人数', '取消关注人数' ]
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
					name : '累计关注人数',
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
					name : '关注人数',
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
				}, {
					name : '取消关注人数',
					type : 'line',
					//data : [ 0, 2, 2, 5, 3, 2, 0 ],
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
				} ]
			};
			// 为echarts对象加载数据 
			myChart.setOption(option);
			myChart.hideLoading();
			getWholedate();
			getChartData();//aja后台交互   
		}
		function getChartData() {
			//获得图表的options对象
			var options = myChart.getOption();
// 			myChart.setOption(options, true)
			var data = {
					startDate : $("#date_1").val(),
					endDate : $("#date_2").val(),
					appid : $("#appid").val()
				};
			//通过Ajax获取数据
			$.ax({
				async : false, //同步执行
				url : "${ctxPath}/accountgzanalysis/ajax/analysisDataSource",
				data : data,
				dataType : "json", //返回数据形式为json
				successfn : function(result) {
					if (result) {
						options.legend.data = result.legend;
						options.xAxis[0].data = result.category;
						options.series[0].data = result.series[0].data;
						options.series[1].data = result.series[1].data;
						options.series[2].data = result.series[2].data;
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
		function getWholedate() {
			var data = {
					startDate : $("#date_1").val(),
					endDate : $("#date_2").val(),
					appid : $("#appid").val()
					};
			$.ax({
				async : false, //同步执行
				url : "${ctxPath}/accountgzanalysis/ajax/analysisWholeData",
				data : data,
				dataType : "json", //返回数据形式为json
				successfn : function(result) {
					if (result) {
						$("#follow_term1").text(result.subscribeTotal);
						$("#follow_term2").text(result.subscribeNow);
						$("#follow_term3").text(result.subscribeCancle);
					}
				},
				errorfn : function(errorMsg) {
					myChart.hideLoading();
				}
			});
		}
		$("#ok").on("click", function() {
			if($("#date_1").val()>$("#date_2").val()){
				$("#errtextmsg").text("开始时间不能大于结束时间，请重新选择！");
				$('#myModal-errmsg').modal('show');
				return false
			}	;
			if($("#appid").val().length==0){
				$("#errtextmsg").text("请选择公众号！");
				$('#myModal-errmsg').modal('show');
				return false
			}	;
			getWholedate();
			getChartData();
		});
			$("#resetBtn").on("click",  function() {
			$("#date_1").val("${dateVO.startDay}");
			$("#date_2").val("${dateVO.endDay}");
			/* $("#appid").val(""); */
		});
	</script>
</body>
</html>