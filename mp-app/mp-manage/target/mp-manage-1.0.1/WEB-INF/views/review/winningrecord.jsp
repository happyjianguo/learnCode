<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<title>审核管理-订单审核</title>
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<i class="icon-cog"></i>
				<li>审核管理</li>
				<li class="active" id="bill_check">订单审核</li>
			</ol>
		</div>
		<div class="mainbox">
			<form:form cssClass="form-horizontal" action="${ctxPath}/winningrecord/init" methodParam="post" modelAttribute="cloudwinningrecordModel">
				<div class="query">
				 	<div class="query-tit">查询条件</div>
				 	<div class="query_1">
				 		<div class="query-text">
							<span>OPENID</span>
							<div class="col-sm-3">
								<form:input path="openid" cssClass="form-control"/>
							</div>
						</div>
						<div class="query-text">
							<span>手机号</span>
							<div class="col-sm-3">
								<form:input path="winnerphone" cssClass="form-control"/>
							</div>
						</div>
						<div class="query-text">
							<span>机构名称</span>
							<div class="col-sm-3">
								<select id="orgid" name="orgid" class="form-control select" >
									<option value="">-请选择-</option> 
									<c:forEach items="${cloudwinningrecordModel.cloudpforgList}" var="showList">
										<option value="${showList.id}" ${cloudwinningrecordModel.orgid eq showList.id ? 'selected="selected"':''}>${showList.orgname}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="query-text">
							<span>活动名称</span>
							<div class="col-sm-3">
								<select id="gameid" name="gameid" class="form-control select" >
									<option value="">-请选择-</option> 
									<c:forEach items="${cloudwinningrecordModel.gameCodeAndNameList}" var="gameList">
										<option value="${gameList.id}" ${cloudwinningrecordModel.gameid eq gameList.id ? 'selected="selected"':''}>${gameList.playname}</option>
									</c:forEach>
								</select>
							</div>
						</div>
				 		<div class="query-btn">
							<button type="submit" class="btn btn-primary" id="queryBtn">查询</button>
							<a href="${ctxPath}/winningrecord/init" class="btn btn-primary">重置</a>
						</div>
				 	</div>
				</div>
			</form:form>
			<div class="tablebox">
				 <div class="table-tit">
					<div class="tit-left" >
						订单流水列表
					</div>
					<div class="tit-btn">
							<span><button type="button" class="btn btn-primary batchDelete"><i class="fa fa-edit"></i>批量审核</button></span>
						</div>
				</div> 
				<table id="table_id" class="tablemain">
				  <thead>
					  <tr class="tit-top">
					    <th scope="col"></th>
					    <th scope="col">中奖记录ID</th>
					    <th scope="col">用户OPENID</th>
<!-- 					    <th scope="col">机构ID</th> -->
					    <th scope="col">机构名称</th>
<!-- 					    <th scope="col">活动ID</th>	 -->
					    <th scope="col">活动名称</th>	
					    <th scope="col">中奖手机号</th>	
					    <th scope="col">发货状态</th>	
<!-- 					    <th scope="col">奖品ID</th>	 -->
					    <th scope="col">奖品名称</th>	
					    <th scope="col">奖品类型</th>	
<!-- 					    <th scope="col">创建时间</th>	 -->
					    <th scope="col">审核状态</th>	
					    <th scope="col">审核意见</th>	
					    <th scope="col">审核人</th>	
					    <th scope="col">更新时间</th>	
<!-- 					    <th scope="col">复审人</th>	 -->
						<th scope="col">操作</th>	
					  </tr>
				  </thead> 
				  <tbody id="tbody">
				  	<c:forEach items="${cloudwinningrecordModel.cloudwinningrecordList}" var="cloudwinning">
					  		<tr>
					  		<td><input data-cwid="${cloudwinning.id}" type='checkbox' name='subBox' onClick='checkOne()' class='roleCheck'></td>
					  			<td>${cloudwinning.id}</td>
					  			<td>${cloudwinning.openid}</td>
<%-- 					  			<td>${cloudwinning.orgid}</td> --%>
					  			<td>${cloudwinning.orgname}</td>
<%-- 					  			<td>${cloudwinning.gameid}</td> --%>
					  			<td>${cloudwinning.playname}</td>
					  			<td>${cloudwinning.winnerphone}</td>
					  			<td>
					  				<c:choose>
					  					<c:when test="${cloudwinning.sendstatus eq '0'}">未发送</c:when>
					  					<c:when test="${cloudwinning.sendstatus eq '1'}">充值成功</c:when>
					  					<c:when test="${cloudwinning.sendstatus eq '2'}">充值失败</c:when>
					  					<c:when test="${cloudwinning.sendstatus eq '3'}">无此订单</c:when>
					  					<c:when test="${cloudwinning.sendstatus eq '4'}">已受理</c:when>
					  					<c:when test="${cloudwinning.sendstatus eq '5'}">套餐不存在</c:when>
					  					<c:when test="${cloudwinning.sendstatus eq '6'}">游戏预算不足</c:when>
					  					<c:when test="${cloudwinning.sendstatus eq '7'}">已发送</c:when>
					  					<c:when test="${cloudwinning.sendstatus eq '8'}">无需发送</c:when>
					  				</c:choose>
					  			</td>
<%-- 					  			<td>${cloudwinning.prizestyle}</td> --%>
					  			<td>${cloudwinning.prizename}</td>
					  			<td>
					  			<c:choose>
					  					<c:when test="${cloudwinning.prizetype eq 'xn'}">话费流量</c:when>
					  					<c:when test="${cloudwinning.prizetype eq 'sw'}">实物</c:when>
					  				</c:choose>
					  			</td>
<%-- 					  			<td>${cloudwinning.createtime}</td> --%>
					  			<td>
					  				<c:choose>
					  					<c:when test="${cloudwinning.preliminarystatus eq '0'}">待审核</c:when>
					  					<c:when test="${cloudwinning.preliminarystatus eq '1'}">审核通过</c:when>
					  					<c:when test="${cloudwinning.preliminarystatus eq '2'}">审核未通过</c:when>
<%-- 					  					<c:when test="${cloudwinning.preliminarystatus eq '3'}">复核通过</c:when> --%>
<%-- 					  					<c:when test="${cloudwinning.preliminarystatus eq '4'}">复核未通过</c:when> --%>
					  				</c:choose>
					  			</td>
					  			<td>${cloudwinning.reviewdes}</td>
					  			<td>${cloudwinning.preliminaryper}</td>
					  			<td>${cloudwinning.updatetime}</td>
<%-- 					  			<td>${cloudwinning.reviewper}</td> --%>
								<td>
						  			<c:if test="${cloudwinning.preliminarystatus eq '0'}">
										<span><a class="tableedit" title="审核" data-val='{"id":"${cloudwinning.id}"}' href="javascript:;"><i class='fa fa-edit'></i></a></span> 
									</c:if>
								</td>
					  		</tr>
					  	</c:forEach>
				  </tbody>
			</table>
			</div>
		</div>
	</div>
	
	<form id="query">
	<div class="modal fade" id="myModal-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="myModalLabel">提示信息</h4>
			</div>
			<div class="modal-body">
			  <label class="col-sm-4 control-label">审核状态</label>
						<div class="col-sm-4">
							<select id="preliminarystatus" name="preliminarystatus" class="form-control select" >
								<option value="1">审核通过</option>
								<option value="2">审核不通过</option>
							</select>
				        </div>
			</div>
			<div class="modal-body">
				<div class="form-group" id="falseDescDiv">
							<label class="col-sm-4 control-label">审核意见</label>
							<div class="col-sm-4">
							    <input type="text" id="reviewdes" name="reviewdes" class="form-control" value="${registerReviewModel.reviewdes}">
							</div>
				</div>
			</div>
			<div class="modal-body">
				<div class="form-group" id="falseDescDiv">
							<div class="col-sm-6">
							    <input type="hidden" id="idss" name="idss" class="form-control" value="${registerReviewModel.reviewdes}">
							</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary deleteConfirm">确定</button>
                <button type="button" class="btn btn-default deleteCancle" data-dismiss="modal">取消</button>
			</div>
			</div>
		</div>
	</div>
	</form>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
		<script type="text/javascript" >
		
			var table;
			var batchDeleteId = null;
			
			$(document).ready( function () {
				table=$('#table_id').DataTable({"ajax":false});
				//审核
			    $(".tablemain").on("click",".tableedit",function(){
			    	submiturl("${ctxPath}/winningrecord/reviewinit",$(this).attr("data-val"));
			    });
			});
			
			//审核页面确定按钮事件
			$(".deleteConfirm").click(function(){
				batchDelete({"ids":batchDeleteId});
			});
			//审核页面取消按钮事件
			$(".deleteCancle").click(function(){
				$("#idss").val("");
				$("#reviewdes").val("");
			});
			
			//点击编辑按钮弹出dialog 将id改变
			$(".tablemain").on("click",".roleCheck",function(){
				$(this).parents("tr").toggleClass('ckecked');
				checkOne();
			});
			
			//设置添加按钮的点击事件
			$(".batchDelete").click(function(){
				var boxs =  $(".ckecked");
				if(boxs.length==0){
					alert("至少选择一条记录进行删除操作！");
					return;
				}
				var ids = "" ;
				var roleNames = "" ;
				$.each($("#tbody").find("input:checked"),function (i,v) {
// 					alert($(this).data("cwid"));
					ids+=$(this).data("cwid")+",";
// 		        	roleNames+=this.roleName+"、";
		        });
				batchDeleteId=ids.substring(0, ids.length-1);
// 				alert(batchDeleteId);
// 				roleNames= roleNames.substring(0, roleNames.length-1);
				$(".deleteAlert span").text("确定删除角色 "+roleNames+" 吗？");
				$('#myModal-delete').modal('show');
			});
			
			function batchDelete(param) {
				$("#idss").val(batchDeleteId);
				   $.ax( {
				        url : '${ctxPath}/winningrecord/ajax/batchUpdate',
				        cache : false,
						data : function() {
								return serializeForm($('#query'));
							},
				        dataType : 'json',
				        successfn : function(result) {
				        	$('.modal').modal('hide');
				        	alert("更新成功");
				        	submiturl("${ctxPath}/winningrecord/init",$(this).attr("data-val"));
				        },
				        errorfn : function(result) {
							$('.modal').modal('hide');
							alert("更新失败");
							submiturl("${ctxPath}/winningrecord/init",$(this).attr("data-val"));
				        }
				    });
				}
			
				//checkAll	
				function checkAll() {
					var eles = document.getElementsByName("subBox");
					var i = 0;
					// 如果是全选状态，则取消所有的选择
					if (isSelectAll() == true) {
						for ( i = 0; i < eles.length; i++) {
							eles[i].checked = false;
					    }
					    document.getElementById("checkAll").checked = false;
					} else {
						// 否则选中每一个checkbox
					        for ( i = 0; i < eles.length; i++) {
					            eles[i].checked = true;
					        }
					    }
				}
				// 判断当前是否为全选状态
 				function isSelectAll() {
 				    var isSelected = true;
 				    var eles = document.getElementsByName("subBox");
 				    for (var i = 0; i < eles.length; i++) {
 						if (eles[i].checked != true) {
 							isSelected = false;
 				        }
 				    }
 				    return isSelected;
 				}
 				// 选择任意一个非全选checkbox
 				function checkOne() {
 				    if (isSelectAll()) {
 						document.getElementById("checkAll").checked = true;
 				    } else {
 						document.getElementById("checkAll").checked = false;
 					}
 				}
		</script>
</body>
</html>