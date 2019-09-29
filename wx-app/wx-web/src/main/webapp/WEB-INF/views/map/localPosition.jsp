<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/com/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<!-- 网点查询-->
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no" />
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=3Ln0phTGidnrxcAdflPjqLPw"></script>
<%-- <title><fmt:message key="wechat.ebank" /></title> --%>
<%@ include file="/WEB-INF/views/com/cssjs.jsp"%>
<%-- <link rel="stylesheet" type="text/css" media="all" href="<c:url value='/resources/css/carddetail.css'/>" /> --%>
<%-- <script type="text/javascript" src="<c:url value='/resources/js/carddetail.js'/>" ></script> --%>
<title>到达路线</title>
<style type="text/css">
html {
	height: 100%;
	width: 100%;
}

body {
	height: 100%;
	width: 100%;
	margin: 0px;
	padding: 0px;
	margin: 0px;
}

#BaiduMap {
	height: 50%;
	width: 100%;
}

#wqySelect {
	width: 100%;
	height: 10%;
	margin-top: 10px;
}

#car {
	width: 33%;
	height: 100%;
	text-align: center;
	float: left;
	color: white;
	font-size: large;
	margin-left: 1%;
}

#car span {
	position: relative;
	top: 30%;
}

#bus {
	width: 33%;
	height: 100%;
	text-align: center;
	float: left;
	color: white;
	font-size: large;
}

#bus span {
	position: relative;
	top: 30%;
}

#walk {
	width: 33%;
	height: 100%;
	text-align: center;
	float: left;
	color: white;
	font-size: large;
}

#walk span {
	position: relative;
	top: 30%;
}

#result {
	width: 100%;
	clear: both;
}
/* tab*/
#wqySelect span {
	height: 40px;
	line-height: 40px;
	background: #eaeaea;
	display: block;
	text-decoration: none;
	color: #000;
	border-bottom: 1px solid #c3c3c3;
}

#wqySelect span a {
	display: block;
	text-decoration: none;
}

#wqySelect span.selectTag a {
	display: block;
	font-size: 1em;
	font-weight: bold;
	height: 40px;
	line-height: 40px;
	display: block;
	text-decoration: none;
	background: #ccc;
}
</style>
</head>
<body>
	<div id="BaiduMap"></div>
	<div id="wqySelect">
		<div id="car">
			<span class="selectTag"><a onClick="selectTag('table0',this)"
				hraf="">驾车</a></span>
		</div>
		<div id="bus">
			<span><a onClick="selectTag('table1',this)">公交</a></span>
		</div>
		<div id="walk">
			<span><a onClick="selectTag('table2',this)">步行</a></span>
		</div>
	</div>
	<div id="result"></div>
	<input type="hidden" id="currentLocalx" name="currentLocalx" value="${pathPosition.currentLocalx}">
	<input type="hidden" id="currentLocaly" name="currentLocaly" value="${pathPosition.currentLocaly}">
	<input type="hidden" id="destLocalx" name="destLocalx" value="${pathPosition.destLocalx}">
	<input type="hidden" id="destLocaly" name="destLocaly" value="${pathPosition.destLocaly}">
	<input type="hidden" id="descript" name="descript" value="${pathPosition.netStation.descript}">
	<script type=text/javascript>
	var descript = $("#descript").val();
	$(function() {
		var userLocalX = $("#currentLocalx").val();
		var userLocalY = $("#currentLocaly").val();
		var localX = $("#destLocalx").val();
		var localY = $("#destLocaly").val();
		var centerLocalX = (parseFloat(userLocalX) + parseFloat(localX)) / 2.0;
		var centerLocalY = (parseFloat(userLocalY) + parseFloat(localY)) / 2.0;
		$("#bus").click(function() {
			//设置行车的起点终点 并将路线结果展示到result中
			var transit = new BMap.TransitRoute(map, {
				renderOptions : {
					map : map,
					panel : "result"
				}
			});
			map.clearOverlays();
			transit.search(myP1, myP2);
		});
		$("#car").click(function() {
			var driving = new BMap.DrivingRoute(map, {
				renderOptions : {
					map : map,
					panel : "result",
					autoViewport : true
				}
			});
			map.clearOverlays();
			driving.search(myP1, myP2);
		});

		$("#walk").click(function() {
			var walking = new BMap.WalkingRoute(map, {
				renderOptions : {
					map : map,
					panel : "result",
					autoViewport : true
				}
			});
			map.clearOverlays();
			walking.search(myP1, myP2);
		});
		var map = new BMap.Map("BaiduMap"); // 创建Map实例 
		var point = new BMap.Point(centerLocalX, centerLocalY);
		
		var myP1 = new BMap.Point(userLocalX, userLocalY); //起点  
		var myP2 = new BMap.Point(localX, localY); //终点  
		var distance = map.getDistance(myP1, myP2);
		var level = getZoomLevel(distance);
		map.centerAndZoom(point, level); // 初始化地图,设置中心点坐标和地图级别  
		setLable(myP1, map, "我的位置");
		var marker = setLable(myP2, map, "目的位置");
		setMessage(marker, myP2, map);

	});
	function getZoomLevel(distance){
		
		if(distance < 1*1000){
			return 16; 
		}else if(distance < 2*1000){
			return 15; 
		}else if(distance < 5*1000){
			return 14; 
		}else if(distance < 10*1000){
			return 13;
		}else if(distance < 20*1000){
			return 12;
		}else if(distance < 50*1000){
			return 11;
		}else if(distance < 100*1000){
			return 10;
		}else if(distance < 200*1000){
			return 9;
		}else if(distance < 500*1000){
			return 7;
		}else if(distance < 1000*1000){
			return 6;
		}else if(distance < 2*1000*1000){
			return 5;
		}else if(distance < 5*1000*1000){
			return 4;
		}else{
			return 3;
		}
	}
	function setLable(point, map, title) {
		var opts = {
			position : point, // 指定文本标注所在的地理位置    
			offset : new BMap.Size(-27, -50)
		//设置文本偏移量  
		}
		var label = new BMap.Label(title, opts); // 创建文本标注对象  
		label.setStyle({
			color : "red",
			fontSize : "12px",
			height : "10px",
			lineHeight : "5px",
			fontFamily : "微软雅黑"
		});
		var marker = new BMap.Marker(point); // 创建标注  
		map.addOverlay(marker);
		map.addOverlay(label);

		return marker;

	}

	function setMessage(marker, point, map) {
		var optsion = {
			width : 200, // 信息窗口宽度
			height : 100, // 信息窗口高度 
			title : "建设银行", // 信息窗口标题 
			enableMessage : true,//设置允许信息窗发送短息
			message : "中国建设银行欢迎您"
		}
		var infoWindow = new BMap.InfoWindow(
				descript,
				optsion); // 创建信息窗口对象   
		marker.addEventListener("click", function() {
			map.openInfoWindow(infoWindow, point); //开启信息窗口  
		});
	}
	//tab切换
	function selectTag(showContent, selfObj) {
		// 操作标签
		var tag = document.getElementById("wqySelect").getElementsByTagName(
				"span");
		var taglength = tag.length;
		for (i = 0; i < taglength; i++) {
			tag[i].className = "";
		}
		selfObj.parentNode.className = "selectTag";
	}
</script>
</body>
</html>