	//���ز˵�
	function hiddenMenu() {
		myMenu.style.visibility = "hidden";
	}
	
	function hiddenDiv(op){
		var ops = op || [];
		for(var i=0 ; i<ops.length ; i++){
			document.getElementById(ops[i]).style.visibility = "hidden";	
		}
	}
	
	//��������꾭���Ĳ˵�����Ŀ
	function highlightMenu() {
		//�����꾭���Ķ�����menuitems�����������ñ���ɫ��ǰ��ɫ
		//event.srcElement.className��ʾ�¼����Զ�������ƣ����������ж����ֵ��
		if (event.srcElement.className == "menuitems") {
			event.srcElement.style.backgroundColor = "highlight";
			event.srcElement.style.color = "white";
	
			//��������Ϣ��ʾ��״̬��
			//event.srcElement.url��ʾ�¼����Զ����ʾ������URL
			if (display_url)
				window.status = event.srcElement.url;
	   }
	}
	
	//�ָ��˵�����Ŀ��������ʾ
	function lowlightMenu() {
		if (event.srcElement.className == "menuitems") {
			event.srcElement.style.backgroundColor = "";
			event.srcElement.style.color = "black";
			window.status = "";
	   }
	}
	
	//�Ҽ������˵�������ת
	function jumptoMenuItem() {
		//ת���µ�����λ��
		var seltext = window.document.selection.createRange().text
		if (event.srcElement.className == "menuitems" && !event.srcElement.disabled) {
		//������ڴ����ӵ�Ŀ�괰�ڣ������Ǹ������д�����
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
			}else {//�����ڵ�ǰ���ڴ�����
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
		
			//���ò˵��ɼ�
			op.style.visibility = "visible";
			return false;
	}