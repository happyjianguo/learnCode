<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>内管系统</title>
</head>
<body>
<input type="hidden" id="ctx" value="${ctxPath}">
<input type="hidden" id="csrfheader" value="${csrfheader}">
<input type="hidden" id="csrftoken" value="${csrftoken}">
<input type="hidden" id="appid" value="${appid}">
    <!-- sidebar-right end-->    
    <div class="main-content">     
	    <div class="mainbox">
	       <div class="table-tit">
                <div class="tit-left">群发消息</div>
	        </div>
	        <div class="user_group ">
	            <span class="current-group">当前分组</span>
	            <select id="initGroupName" name="initGroupName" class="form-control group_name" style="width: 150px;"></select>        
	        </div>
	        <!-- 图文文字切换 -->
	        <div class="frm-centent" style="display: block;">                       
	            <div class="frm-title">
	                <ul>
	                    <li class="selectNews"><a href="javascript:;"><i class="fa fa-credit-card"></i>图文消息</a></li>
	                    <li class="cru selectText"><a href="javascript:;"><i class="fa fa-pencil"></i>文字</a></li>
	                </ul>
	            </div>
	            <div class="frm-box">
	                <div class="frm-tab source-pick" style="display: none;">
	                    <div class="media-cover selectMedia">
	                        <a href="" data-toggle="modal">
	                            <i class="fa fa-plus"></i>
	                            <em>从消息管理中选择</em>
	                        </a>
	                    </div>
	                </div>
	                <div class="frm-tab" style="display: block;">
		                <div class="user_group ">
			                <span class="current-group">消息名称</span>
			                <select id="msgName" name="msgName" class="form-control group_name" style="width: 150px;"></select>
			            </div>
	                    <textarea class="inputbox" id="msgInputText" maxlength="600" readonly="readonly"></textarea>
	                </div>
	            </div><!-- frm-box-->	            
	        </div>
	        <div class="frmbut-release">
                <button type="button" class="btn btn-primary" id="sendMsgBtn">发送</button>
            </div>
	    </div>    
    </div>
<!-- Modal-->
<!-- 图文选择时的弹窗 -->
	<div class="modal fade" id="mediaModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document" style="width: 750px;">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title" id="myModalLabel">选择素材</h4>
	            </div>
	            <div class="modal-body">
	                <div class="library">                   
	                    <div class="brary-main clear newsData">
	                    </div>
	                </div>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-primary brarybtn">确定</button>
	                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>	                
	            </div>
	        </div>      
	    </div>
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
<!-- Modal end-->
<script type="text/javascript" src="<c:url value='/resources/js/wx/sendMessage.js?v=${jsv}'/>"></script>
</body>
</html>