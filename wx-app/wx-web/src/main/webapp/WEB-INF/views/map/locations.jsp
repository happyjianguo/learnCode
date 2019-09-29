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
<title>网点查询</title>
<style>
.top img {
	width: 100%;
}
/* tab*/
.tit {
	overflow: hidden;
	background: #fff;
}

#table {
	color: #828282;
}

.table {
	display: none;
	overflow: hidden;
}

.info select {
	background: #FFFFFF;
}

.info {
	margin: 20px 0;
	padding: 16px 0;
	background: #fff;
	padding-left: 20px;
}

.info #show {
	color: #3399FF;
}

#s_province, #s_city, #s_county {
	width: 30%;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}

#table div.selectTag {
	display: block
}

#tags li {
	float: left;
	width: 50%;
	text-align: center;
}

#tags li a {
	font-size: 14px;
	font-weight: bold;
	height: 40px;
	line-height: 40px;
	display: block;
	text-decoration: none;
	color: #8a8a8a;
	border-bottom: 1px solid #c3c3c3;
}

#tags li.selectTag {
	float: left;
	width: 50%;
	text-align: center;
}

#tags li.selectTag a {
	color: #00a2f8;
	font-size: 16px;
	font-weight: bold;
	height: 40px;
	line-height: 40px;
	display: block;
	text-decoration: none;
	border-bottom: 2px solid #00a2f8;
}
/* 复选框*/
.checkbox {
	padding: 0 10px;
	font-size: 0.75em;
}

.checkbox span {
	color: #00a2f8;
	padding-left: 8px;
	color: #9a9a9a;
	display: inline-block;
}

.checkbox span a {
	color: #00a2f8;
}

.checkbox span i {
	color: #00a2f8;
}

.checkbox div {
	padding-bottom: 5px;
}

.regular-checkbox {
	display: none;
}

.regular-checkbox+label {
	background-color: #fafafa;
	border: 1px solid #cacece;
	width: 18px;
	height: 18px;
	border-radius: 1px;
	float: left;
	display: inline-block;
	position: relative;
}

.footer {
	color: #1f77ba;
	padding: 10% 0 8%; 0;
	text-align: center;
	font-size: 0.75em;
}

.footer span {
	width: 52%;
	display: inline-block;
}

.footer span:first-child {
	width: 22%;
	text-align: right;
}

.footer span:last-child {
	width: 22%;
	text-align: left;
}

.footer span img {
	width: 100%;
}

.list {
	background: #fff;
	font-size: 0.85em;
	padding: 0 10px;
	line-height: 30px;
	margin: 20px 0;
}

.list dl {
	border-bottom: 1px solid #eaeaea;
	padding: 5px 0;
	-webkit-margin-before: 0em;
	-webkit-margin-after: 0em;
}

.list dl dt span {
	float: right;
	color: #ccc;
}

.list dl dd {
	color: #666;
	-webkit-margin-start: 0px;
}

.list2 {
	background: #fff;
	font-size: 0.85em;
	padding: 0 10px;
	line-height: 30px;
	margin: 20px 0;
}

.list2 dl {
	border-bottom: 1px solid #eaeaea;
	padding: 5px 0;
	-webkit-margin-before: 0em;
	-webkit-margin-after: 0em;
}

.list2 dl dt span {
	float: right;
	color: #ccc;
}

.list2 dl dd {
	color: #666;
	-webkit-margin-start: 0px;
}

.search {
	padding: 35px 0 10px 0;
	height: auto;
	overflow: hidden;
}

.search-left {
	position: relative;
	border-bottom: 1px solid #999;
	width: 70%;
	height: 30px;
	float: left;
}

.search-left::before, .search-left::after {
	position: absolute;
	bottom: 0px;
	content: "";
	display: block;
	height: 5px;
	color: #999;
	border-style: solid;
	border-width: 0px 1px 0px 0px;
}

.search-left::before {
	left: 0px;
}

.search-left::after {
	right: 0px;
}

.search-left {
	float: left;
}

.search-txt {
	width: 90%;
	margin-left: 5%;
	border: 0;
}

.search-but {
	margin-left: 20px;
	background: #007aff;
	border: 0;
	color: #fff;
	font-size: 0.85em;
	width: 20%;
	height: 32px;
	line-height: 32px;
}
</style>
</head>
<body>
	<div class="centainer">
		<div class="tit">
			<ul id=tags>
				<li class="selectTag"><a onClick="selectTag('table0',this)"
					href="javascript:void(0)">周边查询</a></li>
				<li><a onClick="selectTag('table1',this)"
					href="javascript:void(0)">条件查询 </a></li>
			</ul>
		</div>
		<div class="table" id="table0" style="display: block;">
			<!-- -->
			<div id="BaiduMap"></div>
			<div id="result">
				<div class="list"></div>
			</div>
			<!-- -->
			<div class="box_bottom">
				<div class="checkbox">
					<div>
						<span>温馨提示： <a href="#">周边查询需要开启定位服务，请确认您的手机系统设置或使用条件搜索。
						</a></span>
					</div>
				</div>
			</div>
		</div>
		<div class="table" id="table1">
			<!-- -->
			<div class="info">
				<div>
					<select id="s_province" name="s_province"></select>&nbsp;&nbsp;<select
						id="s_city" name="s_city"></select>&nbsp;&nbsp; <select
						id="s_county" name="s_county"></select>
					<script type="text/javascript">
						
					</script>
				</div>
				<div class="search">
					<div class="search-left">
						<input type="text" class="search-txt" placeholder="请输入关键词" />
					</div>
					<input type="button" class="search-but" value="搜索" />
				</div>
				<div id="result">
					<div class="list2"></div>
				</div>
			</div>

			<!-- -->
			<div class="box_bottom">
				<div class="checkbox">
					<div>
						<span>温馨提示： <a href="#">周边查询需要开启定位服务，请确认您的手机系统设置或使用条件搜索。
						</a></span>
					</div>
				</div>
			</div>
		</div>
		<form:form action="${ctxPath}/baidLocate/localps" modelAttribute="pathPosition" method="post" id="eform">
			<input type="hidden" id="currentLocalx" name="currentLocalx" value="${pathPosition.currentLocalx}">
			<input type="hidden" id="currentLocaly" name="currentLocaly" value="${pathPosition.currentLocaly}">
			<input type="hidden" id="destLocalx" name="destLocalx" value="${pathPosition.destLocalx}">
			<input type="hidden" id="destLocaly" name="destLocaly" value="${pathPosition.destLocaly}">
		</form:form>
		<!--tall
		<div class="footer">
			<span><img src="img/f_l.png" /></span> <span>客户热线：4006-501-051</span>
			<span><img src="img/f_r.png" /></span>
		</div>
		-->
	</div>
</body>
<script type=text/javascript>
	$(function() {
		_init_area();
		var map = new BMap.Map("BaiduMap"); // 创建地图实例
		//页面完全加载后执行
		map.centerAndZoom(new BMap.Point(116.404, 39.915), 15); //设置中心点及缩放级别 
		var geolocation = new BMap.Geolocation();
		geolocation.getCurrentPosition(function(r) {
			if (this.getStatus() == BMAP_STATUS_SUCCESS) {
				var opts = {
					position : r.point, // 指定文本标注所在的地理位置    
					offset : new BMap.Size(0, -30)
				//设置文本偏移量  
				}
				var label = new BMap.Label("当前位置", opts); // 创建文本标注对象   
				label.setStyle({
					color : "red",
					fontSize : "12px",
					height : "20px",
					lineHeight : "20px",
					fontFamily : "微软雅黑"
				});
				map.addOverlay(label);
				btn(map, r.point.lng, r.point.lat);
				
				$(".search-but").click(function(){
					$(".list2 dl").remove();
					var point = $("#s_county option:selected").text();
					var key = $(".search-txt").val();
					btn2(key,point,map, r.point.lng, r.point.lat);
				});
				
			} else {
				alert('failed' + this.getStatus());
			}
		}, {
			enableHighAccuracy : true
		});
	});
	
	function btn2(key,point,map, localX, localY) {
		var options = {
			onSearchComplete : function(results) { // 判断状态是否正确    
				
				if (local.getStatus() == BMAP_STATUS_SUCCESS) {
					var element = [];
					for (var i = 0; i < results.getCurrentNumPois(); i++) {
						var pointA = new BMap.Point(localX, localY);
						var pointB = new BMap.Point(
								results.getPoi(i).point.lng,
								results.getPoi(i).point.lat);
						element[i] = map.getDistance(pointA, pointB);
					}
					var indexList = sort(element);
					for (var i = 0; i < results.getCurrentNumPois(); i++) {
						$(".list2")
								.append(
										"<dl class ='put1' localx="
												+ results.getPoi(indexList[i]).point.lng
												+ " localy="
												+ results.getPoi(indexList[i]).point.lat
												+ "><dt><span> 距离 "
												+ (element[i] / 1000.0)
														.toFixed(2)
												+ "公里</span>"
												+ results.getPoi(indexList[i]).title
												+ "</dt><dd>地址："
												+ results.getPoi(indexList[i]).address
												+ "<dd></dl>");
					}
				}
			}
		};
		$("div#result").on(
				"click",
				".put2",
				function(event) {
					var localx = $(this).attr("localx");
					var localy = $(this).attr("localy");
					
					$("#destLocalx").val(localx);
					$("#destLocaly").val(localy);
					$("#currentLocalx").val(localX);
					$("#currentLocaly").val(localY);
					$("#eform").submit();
				});
		var local = new BMap.LocalSearch(map, options);
		local.searchNearby(key+"银行", point, 2000);
	}
	
	function btn(map, localX, localY) {
		var options = {
			onSearchComplete : function(results) { // 判断状态是否正确    
				if (local.getStatus() == BMAP_STATUS_SUCCESS) {
					var element = [];
					for (var i = 0; i < results.getCurrentNumPois(); i++) {
						var pointA = new BMap.Point(localX, localY);
						var pointB = new BMap.Point(
								results.getPoi(i).point.lng,
								results.getPoi(i).point.lat);
						element[i] = map.getDistance(pointA, pointB);
					}
					var indexList = sort(element);
					for (var i = 0; i < results.getCurrentNumPois(); i++) {
						$(".list")
								.append(
										"<dl class ='put1' localx="
												+ results.getPoi(indexList[i]).point.lng
												+ " localy="
												+ results.getPoi(indexList[i]).point.lat
												+ "><dt><span> 距离 "
												+ (element[i] / 1000.0)
														.toFixed(2)
												+ "公里</span>"
												+ results.getPoi(indexList[i]).title
												+ "</dt><dd>地址："
												+ results.getPoi(indexList[i]).address
										 		+ "<dd></dl>");
					}
				}
			}
		};
		$("div#result").on(
				"click",
				".put1",
				function(event) {
					var localx = $(this).attr("localx");
					var localy = $(this).attr("localy");
					$("#destLocalx").val(localx);
					$("#destLocaly").val(localy);
					$("#currentLocalx").val(localX);
					$("#currentLocaly").val(localY);
					$("#eform").submit();
				});
		var local = new BMap.LocalSearch(map, options);
		var point = new BMap.Point(localX, localY);
		local.searchNearby("建设银行", point, 2000);
	}
	function sort(elements) {
		var esLength = elements.length;
		var indexList = [];
		for (var i = 0; i < esLength; i++) {
			indexList[i] = i;
		}
		for (var i = 0; i < esLength - 1; i++) {
			for (var j = 0; j < esLength - i - 1; j++) {
				if (elements[j] > elements[j + 1]) {
					var swap = elements[j];
					elements[j] = elements[j + 1];
					elements[j + 1] = swap;
					var indexSwap = indexList[j];
					indexList[j] = indexList[j + 1];
					indexList[j + 1] = indexSwap;
				}
			}
		}
		return indexList;
	}
	//tab切换
	function selectTag(showContent, selfObj) {
		// 操作标签
		var tag = document.getElementById("tags").getElementsByTagName("li");
		var taglength = tag.length;
		for (i = 0; i < taglength; i++) {
			tag[i].className = "";
		}
		selfObj.parentNode.className = "selectTag";
		// 操作内容
		for (i = 0; j = document.getElementById("table" + i); i++) {
			j.style.display = "none";
		}
		document.getElementById(showContent).style.display = "block";
	}
</script>
</html>