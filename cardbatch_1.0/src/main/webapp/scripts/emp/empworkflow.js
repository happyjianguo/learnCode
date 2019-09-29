	var contextPath = "/EMP2.2Demo";    //应用名称
	
	var doWithdraw = function(button) {
		echain_withdraw(button,"withdrawUser.do","getSelectWithdrawUserPage.do");	
	}
	
	var doSave = function(button) {
		try{
			if(!canSave()) return;
		}catch(e){};
		echain_save(button,"saveWorkFlow.do");	
	}
	
	var doSubmitWorkFlow = function(button) {
		var formObj = button.form;
		if(!formObj.onsubmit())return;
		try{
			if(!canSubmit()) return;
		}catch(e){};
		//&&document.getElementById("savebeforesubmit").value=='000' 暂时去掉
		if(document.getElementById("savebeforesubmit")){
			//提交前先保存，目的为了计算后续路由条件
			var handleSuccess = function(o){				
				echain_submit(button,"submitWorkFlow.do","getSelectNextNodePage.do");				
			};		
			var handleFailure = function(o){	
			};		
			var callback = {
				success:handleSuccess,
				failure:handleFailure
			};			
			
			var postData = YAHOO.util.Connect.setForm(formObj);			
			url = contextPath + "/saveWorkFlow2Blank.do";
			var obj1 = YAHOO.util.Connect.asyncRequest('POST',url, callback,postData);
			if(document.getElementById("button_save"))
				document.getElementById("button_save").disabled = true;
			if(document.getElementById("button_submit"))
				document.getElementById("button_submit").disabled = true;
			if(document.getElementById("button_jump"))
				document.getElementById("button_jump").disabled = true;
		}
		else{
			echain_submit(button,"submitWorkFlow.do","getSelectNextNodePage.do");
		}		
	}
	
	var doBatchSubmit = function(button) {
		echain_submit(button,"batchsubmitWorkFlow.do","getSelectNextNodePage.do");
	}
	var doJump = function(button) {
		var formObj = button.form;
		if(!formObj.onsubmit())return;
		try{
			if(!canSubmit()) return;
		}catch(e){};
		//&&document.getElementById("savebeforesubmit").value=='000' 暂时去掉
		if(document.getElementById("savebeforesubmit")){
			//提交前先保存，目的为了计算后续路由条件
			var handleSuccess = function(o){				
				echain_submit(button, "jumpWorkFlow.do","getSelectWFNodePage.do");				
			};		
			var handleFailure = function(o){	
			};		
			var callback = {
				success:handleSuccess,
				failure:handleFailure
			};			
			
			var postData = YAHOO.util.Connect.setForm(formObj);			
			url = contextPath + "/saveWorkFlow2Blank.do";
			var obj1 = YAHOO.util.Connect.asyncRequest('POST',url, callback,postData);
			if(document.getElementById("button_save"))
				document.getElementById("button_save").disabled = true;
			if(document.getElementById("button_submit"))
				document.getElementById("button_submit").disabled = true;
			if(document.getElementById("button_jump"))
				document.getElementById("button_jump").disabled = true;
		}
		else{
			echain_submit(button, "jumpWorkFlow.do","getSelectWFNodePage.do");
		}		
	}
	
	var doCallback = function(button) {
		var formObj = button.form;
		if(!formObj.onsubmit())return;
		try{
			if(!canSubmit()) return;
		}catch(e){};
		//&&document.getElementById("savebeforesubmit").value=='000' 暂时去掉
		if(document.getElementById("savebeforesubmit")){
			//提交前先保存，目的为了计算后续路由条件
			var handleSuccess = function(o){				
				echain_submit(button,"callBackWorkFlow.do","getSelectWFTreatedNodePage.do");				
			};		
			var handleFailure = function(o){	
			};		
			var callback = {
				success:handleSuccess,
				failure:handleFailure
			};			
			
			var postData = YAHOO.util.Connect.setForm(formObj);			
			url = contextPath + "/saveWorkFlow2Blank.do";
			var obj1 = YAHOO.util.Connect.asyncRequest('POST',url, callback,postData);
		}
		else{
			echain_submit(button,"callBackWorkFlow.do","getSelectWFTreatedNodePage.do");
		}		
	}
	
	var doCancel = function(button) {
		try{
			if(!canCancel()) return;
		}catch(e){};
		echain_common_op(button,"cancelWorkFlow.do","getSuggestContentPage.do");
	}	
	
	var doAgain = function(button) {
		try{
			if(!canAgain()) return;
		}catch(e){};
		echain_common_op(button,"requestAgainWorkFlow.do","getSuggestContentPage.do");
	}
	
	var doReturnback = function(button) {
		try{
			if(!canReturnback()) return;
		}catch(e){};
		echain_common_op(button,"returnBackWorkFlow.do","getSuggestContentPage.do");
	}	
	
	var doTrack = function(button) {
		echain_track(button,"echain/studio/eChainMonitor.jsp");
	}
	
	var doViewcomment = function(button) {
		echain_viewcomment(button,"viewSuggestContent.do");
	}
	
	//-----------------------------------------------------------------------------
	//流程通用提交的动作（如提交、批量提交、跳转、打回）
	//属性:element(按钮对象);submitUrl(提交的url);selectUrl(打开选择下一节点及处理人界面的url)
	//-----------------------------------------------------------------------------
	function echain_submit(element,submitUrl,selectUrl){			
		var formObj = element.form;
		var instanceid = document.getElementById("instanceid").value;
		var nodeid = document.getElementById("nodeid").value;
		var currentuserid = document.getElementById("currentuserid").value;
		var sessionId = document.getElementsByName("EMP_SID")[0].value;
		if(document.getElementById("button_save"))
				document.getElementById("button_save").disabled = true;
		if(document.getElementById("button_submit"))
				document.getElementById("button_submit").disabled = true;
		if(document.getElementById("button_jump"))
				document.getElementById("button_jump").disabled = true;
		//打开选择下一节点及处理人的界面
		var url = contextPath + "/" + selectUrl +"?instanceid="+instanceid+"&nodeid="+nodeid+"&currentuserid="+currentuserid+"&EMP_SID="+sessionId;
		var retObj = window.showModalDialog(url,'selectPage','dialogHeight:400px;dialogWidth:600px;help:no;resizable:no;status:no;');
		
		//返回数组:[状态:true/false;意见;下一节点;下一处理人];若没有返回值,或返回状态不为true,则表示取消
		if(retObj == null){
			if(document.getElementById("button_save"))
				document.getElementById("button_save").disabled = false;
			if(document.getElementById("button_submit"))
				document.getElementById("button_submit").disabled = false;
			if(document.getElementById("button_jump"))
				document.getElementById("button_jump").disabled = false;
			return;
		}
		var status = retObj[0];
		if(status != true){
			if(document.getElementById("button_save"))
				document.getElementById("button_save").disabled = false;
			if(document.getElementById("button_submit"))
				document.getElementById("button_submit").disabled = false;
			if(document.getElementById("button_jump"))
				document.getElementById("button_jump").disabled = false;
			return;
		}
		document.getElementById("nextnodeid").value = retObj[2];
		if(retObj[3] != null)
			document.getElementById("nextnodeuser").value = retObj[3];
		
		//提交节点
		url = contextPath + "/" +submitUrl;
		formObj.action = url;
		formObj.submitbutton.click();
	};
	
	//-----------------------------------------------------------------------------
	//流程撤销办理人的动作
	//属性:element(按钮对象);submitUrl(提交的url);selectUrl(打开选择下一节点及处理人界面的url)
	//-----------------------------------------------------------------------------
	function echain_withdraw(element,submitUrl,selectUrl){			
		var formObj = element.form;
		var instanceid = document.getElementById("instanceid").value;
		var nodeid = document.getElementById("nodeid").value;
		var currentuserid = document.getElementById("currentuserid").value;
		var sessionId = document.getElementsByName("EMP_SID")[0].value;

		//打开选择下一节点及处理人的界面
		var url = contextPath + "/" + selectUrl +"?instanceid="+instanceid+"&nodeid="+nodeid+"&currentuserid="+currentuserid+"&EMP_SID="+sessionId;
		var retObj = window.showModalDialog(url,'selectPage','dialogHeight:400px;dialogWidth:600px;help:no;resizable:no;status:no;');
		
		//返回数组:[状态:true/false;意见;下一节点;下一处理人];若没有返回值,或返回状态不为true,则表示取消
		if(retObj == null){
			return;
		}
		var status = retObj[0];
		if(status != true){
			return;
		}
		document.getElementById("nextnodeuser").value = retObj[2];
		//提交节点
		url = contextPath + "/" +submitUrl;
		formObj.action = url;
		formObj.submitbutton.click();
	};
	
	//-----------------------------------------------------------------------------
	//流程保存的动作
	//属性:element(按钮对象);saveUrl(保存的url)
	//-----------------------------------------------------------------------------
	function echain_save(element,saveUrl){
		var formObj = element.form;
		url = contextPath + "/" + saveUrl;
		formObj.action = url;
		formObj.submitbutton.click();
	};
	
	//-----------------------------------------------------------------------------
	//流程通用操作（如撤办、退回、拿回、手工催办等）
	//属性:element(按钮对象);opUrl(通用操作的url);contentUrl(打开输入意见页面的url)
	//-----------------------------------------------------------------------------
	function echain_common_op(element,opUrl,contentUrl){
		var formObj = element.form;
		var instanceid = document.getElementById("instanceid").value;
		var nodeid = document.getElementById("nodeid").value;
		var currentuserid = document.getElementById("currentuserid").value;
		var sessionId = document.getElementsByName("EMP_SID")[0].value;
		//打开输入意见的页面
		var url = contextPath + "/" + contentUrl + "?EMP_SID="+sessionId;
		var retObj = window.showModalDialog(url,'setContentPage','dialogHeight:400px;dialogWidth:600px;help:no;resizable:no;status:no;');
		//返回数组:[状态:true/false;意见];若没有返回值,或返回状态不为true,则表示取消
		if(retObj == null)
			return;
		var status = retObj[0];
		if(status != true)
			return;
		//设置action并submit
		url = contextPath + "/" + opUrl + "?instanceid="+instanceid+"&nodeid="+nodeid+"&currentuserid="+currentuserid+"&EMP_SID="+sessionId;
		formObj.action = url;
		formObj.submitbutton.click();
	};
	
	//-----------------------------------------------------------------------------
	//流程跟踪的动作
	//属性:element(按钮对象);trackUrl(流程跟踪的url)
	//-----------------------------------------------------------------------------
	function echain_track(element,trackUrl){
		var instanceid = document.getElementById("instanceid").value;
		var currentuserid = document.getElementById("currentuserid").value;
		var sessionId = document.getElementsByName("EMP_SID")[0].value;
		var url = contextPath + "/" + trackUrl + "?EMP_SID=" + sessionId+"&instanceid="+instanceid+"&currentuserid="+currentuserid;
		window.open(url,"流程跟踪", "height=768, width=1024, top=0, left=0, toolbar=no, menubar=no, scrollbars=auto, resizable=yes,location=no, status=no");
		//window.showModalDialog(url,'流程跟踪','dialogHeight:400px;dialogWidth:750px;help:no;resizable:no;status:no;location:no');
	};
	
	//-----------------------------------------------------------------------------
	//查看意见的动作
	//属性:element(按钮对象);viewcommentUrl(查看意见的url)
	//-----------------------------------------------------------------------------
	function echain_viewcomment(element,viewcommentUrl){
		var instanceid = document.getElementById("instanceid").value;
		var currentuserid = document.getElementById("currentuserid").value;
		var sessionId = document.getElementsByName("EMP_SID")[0].value;
		var url = contextPath + "/" + viewcommentUrl + "?EMP_SID=" + sessionId+"&instanceid="+instanceid+"&currentuserid="+currentuserid;
		window.showModalDialog(url,'viewCommentPage','dialogHeight:400px;dialogWidth:710px;help:no;resizable:no;status:no;location:no');
	};