	//隐藏菜单
	function hiddenMenu() {
		myMenu.style.visibility = "hidden";
	}
	
	function hiddenDiv(op){
		var ops = op || [];
		for(var i=0 ; i<ops.length ; i++){
			document.getElementById(ops[i]).style.visibility = "hidden";	
		}
	}
	
	//高亮度鼠标经过的菜单条项目
	function highlightMenu() {
		//如果鼠标经过的对象是menuitems，就重新设置背景色与前景色
		//event.srcElement.className表示事件来自对象的名称，必须首先判断这个值！
		if (event.srcElement.className == "menuitems") {
			event.srcElement.style.backgroundColor = "highlight";
			event.srcElement.style.color = "white";
	
			//将链接信息显示到状态行
			//event.srcElement.url表示事件来自对象表示的链接URL
			if (display_url)
				window.status = event.srcElement.url;
	   }
	}
	
	//恢复菜单条项目的正常显示
	function lowlightMenu() {
		if (event.srcElement.className == "menuitems") {
			event.srcElement.style.backgroundColor = "";
			event.srcElement.style.color = "black";
			window.status = "";
	   }
	}
	
	//右键下拉菜单功能跳转
	function jumptoMenuItem() {
		//转到新的链接位置
		var seltext = window.document.selection.createRange().text
		if (event.srcElement.className == "menuitems" && !event.srcElement.disabled) {
		//如果存在打开链接的目标窗口，就在那个窗口中打开链接
			if (event.srcElement.getAttribute("target") != null){
				var inputWin;
				if(event.srcElement.getAttribute("target") == "_blank"){
					if(inputWin==null || inputWin.closed){
						inputWin = window.open(event.srcElement.url, event.srcElement.getAttribute("target"),'dependent,width=350,height=146,left=350,top=300');
						inputWin.focus();
					}else{
						inputWin.close();
	 					inputWin = window.open(event.srcElement.url, event.srcElement.getAttribute("target"),'dependent,width=350,height=146,left=350,top=300');
	    					inputWin.focus();
					}
				}else{
					if(inputWin==null || inputWin.closed){
						inputWin = window.open(event.srcElement.url, event.srcElement.getAttribute("target"));
						inputWin.focus();
					}else{
						inputWin.close();
	 					inputWin = window.open(event.srcElement.url, event.srcElement.getAttribute("target"));
	    					inputWin.focus();
					}					
				}
			}else {//否则，在当前窗口打开链接
				if(event.srcElement.url != null)
					window.location = event.srcElement.url;
				
			}
	   }
	}
	
	function showDiv(opid){
			var op = document.getElementById(opid);
			var rightedge = document.body.clientWidth-event.clientX;
			var bottomedge = document.body.clientHeight-event.clientY;
		
			if (rightedge <op.offsetWidth)
				op.style.left = document.body.scrollLeft + event.clientX - op.offsetWidth;
			else
				op.style.left = document.body.scrollLeft + event.clientX;
		
			if (bottomedge <op.offsetHeight)
				op.style.top = document.body.scrollTop + event.clientY - op.offsetHeight;
			else
				op.style.top = document.body.scrollTop + event.clientY;
		
			//设置菜单可见
			op.style.visibility = "visible";
			return false;
	}