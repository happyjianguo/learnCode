<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>内管系统-数据分析</title>
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
					<!-- <div class="query-tit">查询条件</div> -->
					<div class="query-text">
						<span>公众号</span>
						<select id="appid" name="appid" class="form-control">
							<option value="" selected>请选择</option>
							<c:forEach var="acc" items="${account}">
								<option value="${acc.appid}" ${acc.appid == defaultappid ? 'selected': ''}>${acc.nickName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="query-btn">
						<button type="button" class="btn btn-primary" id="ok">查询</button>
					</div>
				</div>
					<div class="tablebox">
						<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
						<div class="follow_line clear">
							<div id="main" style="height: 600px"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- sidebar-right end-->
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
	<script type="text/javascript">
	$(function() {
		getData();
	});
	$("#ok").on("click", function() {
		if($("#appid").val().length==0){
			$("#errtextmsg").text("请选择公众号！");
			$('#myModal-errmsg').modal('show');
			return false
		}	;
		getData();
	});
	function getData(){
		//获取数据
		var data = {appid : $("#appid").val()};
		var myChart = echarts.init(document.getElementById("main"));
		var option;
		//通过Ajax获取数据
		$.ax({
			async : false, //同步执行
			url : "${ctxPath}/accountuseranalysis/ajax/getData",
			data : data,
			dataType : "json", //返回数据形式为json
			successfn : function(result) {
				option = {
					    title : {
					        text: '关注地区分布',
					        subtext: '',
					        x:'center'
					    },
					    tooltip : {
					        trigger: 'item'
					    },
					    legend: {
					        orient: 'vertical',
					        x:'left',
					        data:['男','女']
					    },
					    dataRange: {
					        min: 0,
					        max: jQuery.parseJSON(result.max),
					        x: 'left',
					        y: 'bottom',
					        text:['人数',''],           // 文本，默认为数值文本
					        calculable : true
					    },
					    toolbox: {
					        show: true,
					        orient : 'vertical',
					        x: 'right',
					        y: 'center',
					        feature : {
					            mark : {show: true},
					            dataView : {show: true, readOnly: false},
					            restore : {show: true},
					            saveAsImage : {show: true}
					        }
					    },
					    roamController: {
					        show: true,
					        x: 'right',
					        mapTypeControl: {
					            'china': true
					        }
					    },
					    series : [
					        {
					            name: '男',
					            type: 'map',
					            mapType: 'china',
					            roam: false,
					            itemStyle:{
					                normal:{label:{show:true}},
					                emphasis:{label:{show:true}}
					            },
					            data: jQuery.parseJSON(result.man)
					        },
					        {
					            name: '女',
					            type: 'map',
					            mapType: 'china',
					            itemStyle:{
					                normal:{label:{show:true}},
					                emphasis:{label:{show:true}}
					            },
					            data: jQuery.parseJSON(result.woman)
					        }
					    ]
					};
				// 为echarts对象加载数据 
				myChart.setOption(option);
				myChart.hideLoading();
			},
			errorfn : function(errorMsg) {
				$("#errtextmsg").text("网络异常!");
				$('#myModal-errmsg').modal('show');
			}
		});
	}
	</script>
</body>
</html>