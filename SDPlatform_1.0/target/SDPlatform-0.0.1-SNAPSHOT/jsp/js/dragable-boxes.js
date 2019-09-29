	/************************************************************************************************************
	(C) www.dhtmlgoodies.com, January 2006
	
	This is a script from www.dhtmlgoodies.com. You will find this and a lot of other scripts at our website.	
	
	Version:	1.0	: January 16th - 2006
				1.1 : January 31th - 2006 - Added cookie support - remember rss sources
				1.2 : July 13th - 2006 - Fixed a problem in the createRSSBoxesFromCookie function
				
	Terms of use:
	You are free to use this script as long as the copyright message is kept intact. However, you may not
	redistribute, sell or repost it without our permission.
	
	Thank you!
	
	www.dhtmlgoodies.com
	Alf Magne Kalleland
	
	************************************************************************************************************/		
	
	/* USER VARIABLES */
	
	var numberOfColumns = 1;	// Number of columns for dragable boxes
	var columnParentBoxId = 'container';	// Id of box that is parent of all your dragable boxes
	var src_rightImage = 'images/arrow_right.gif';
	var src_downImage = 'images/arrow_down.gif';
	var src_refreshSource = 'images/refresh.gif';
	var src_smallRightArrow = 'images/small_arrow.gif';
	var url='';
	var localWidth='';
	var localHeight='';
	var top='';
	var left='';
	var isFrame=false;
	var transparencyWhenDragging = false;
	var txt_editLink = 'max';
	var txt_editLink_stop = 'min';
	var autoScrollSpeed = 4;	// Autoscroll speed	- Higher = faster	
	var dragObjectBorderWidth = 1;	// Border size of your RSS boxes - used to determine width of dotted rectangle
	
	var useCookiesToRememberRSSSources = false;
	
	var nameOfCookie = 'dragable_rss_boxes';	// Name of cookie
	
	/* END USER VARIABLES */
	
	
	
	var columnParentBox;
	var dragableBoxesObj;
	
	var ajaxObjects = new Array();
	
	var boxIndex = 0;	
	var autoScrollActive = true;
	var dragableBoxesArray = new Array();
	var titleSide='sdfd';
	var dragDropCounter = -1;
	var dragObject = true;
	var dragObjectNextSibling = false;
	var dragObjectParent = false;
	var destinationObj = false;
	
	var mouse_x;
	var mouse_y;
	
	var el_x;
	var el_y;	
	
	var okToMove = true;

	var documentHeight = false;
	var documentScrollHeight = false;
	var dragableAreaWidth =true;
		
	var opera = navigator.userAgent.toLowerCase().indexOf('opera')>=0?true:false;
	var cookieCounter=0;
	var cookieRSSSources = new Array();
	
	var staticObjectArray = new Array();
	var i=1;
	
	/*
	These cookie functions are downloaded from 
	http://www.mach5.com/support/analyzer/manual/html/General/CookiesJavaScript.htm
	*/	

	// This function has been slightly modified

		
	function initDragDropBox(e)
	{
		
		dragDropCounter = 1;
		if(document.all)e = event;
		
		if (e.target) source = e.target;
			else if (e.srcElement) source = e.srcElement;
			if (source.nodeType == 3) // defeat Safari bug
				source = source.parentNode;
		
		if(source.tagName.toLowerCase()=='img' || source.tagName.toLowerCase()=='a' || source.tagName.toLowerCase()=='input' || source.tagName.toLowerCase()=='td' || source.tagName.toLowerCase()=='tr' || source.tagName.toLowerCase()=='table')return;
		
	
		mouse_x = e.clientX;
		mouse_y = e.clientY;	
		
		var numericId = this.id.replace(/[^0-9]/g,'');
		
	  
		dragObject = this.parentNode;
		 el_x = getLeftPos(this.parentNode)/1+dragObject.style.width/2;
		el_y = getTopPos(this.parentNode)/1 - document.documentElement.scrollTop+10;
		i++;
		dragObject.style.zIndex=i+"";
		if(dragObject.style.width=='1000px')return;
		documentScrollHeight = document.documentElement.scrollHeight + 100 + dragObject.offsetHeight;
		
		
		if(dragObject.nextSibling){
			dragObjectNextSibling = dragObject.nextSibling;
			if(dragObjectNextSibling.tagName!='DIV')dragObjectNextSibling = dragObjectNextSibling.nextSibling;
		}
		dragObjectParent = dragableBoxesArray[numericId]['parentObj'];			
		dragDropCounter = 0;
		initDragDropBoxTimer();	
		
		return false;
	}
	
	
	function initDragDropBoxTimer()
	{
		if(dragDropCounter>=0 && dragDropCounter<10){
			
			dragDropCounter++;
			setTimeout('initDragDropBoxTimer()',1);
			return;
		}
		if(dragDropCounter==10){
			
			mouseoutBoxHeader(false,dragObject);
		}
		
	}

	function moveDragableElement(e){
		
		if(document.all)e = event;
		
		if(dragDropCounter<10)return;
		
		if(document.body!=dragObject.parentNode){
			
			dragObject.style.width = (dragObject.offsetWidth - (dragObjectBorderWidth*2)) + 'px';
			dragObject.style.position = 'absolute';	
			dragObject.style.textAlign = 'left';
			if(transparencyWhenDragging){
			
				dragObject.style.filter = 'alpha(opacity=70)';
				dragObject.style.opacity = '1.0';
			}	
			
			document.body.appendChild(dragObject);

		}
		
		if(e.clientY<50 || e.clientY>(documentHeight-50)){
			if(e.clientY<50 && !autoScrollActive){
				
				autoScrollActive = true;
				autoScroll((autoScrollSpeed*-1),e.clientY);
			}
			
			if(e.clientY>(documentHeight-50) && document.documentElement.scrollHeight<=documentScrollHeight && !autoScrollActive){
				autoScrollActive = true;
				autoScroll(autoScrollSpeed,e.clientY);
			}
		}else{
			
			autoScrollActive = false;
		}		

		
		var leftPos = e.clientX;
		var topPos = e.clientY + document.documentElement.scrollTop;
		 el_x=leftPos;
         el_y=topPos;
		dragObject.style.left = el_x-localWidth/2-10 + 'px';
		dragObject.style.top = el_y-10  + 'px';
								
		if(!okToMove)return;
		okToMove = false;

		destinationObj = false;	 
		
		var objFound = true;
		var tmpParentArray = new Array();

		
	}
	
	
function stop_dragDropElement()
	{		
		dragDropCounter = -1;
		if(dragObject)return;
		var numericId = dragObject.id.replace(/[^0-9]/g,'');
		dragObject.style.left = el_x-dragObject.style.width/2-10 + 'px';	
		dragObject.style.top = el_y-10  + 'px';
		autoScrollActive = false;
		dragObject = false;
		dragObjectNextSibling = false;
		destinationObj = false;
		
		

		documentHeight = document.documentElement.clientHeight;	
	}

	function getTopPos(inputObj)
	{		
	  var returnValue = inputObj.offsetTop;
	  while((inputObj = inputObj.offsetParent) != null){
	  	if(inputObj.tagName!='HTML')returnValue += inputObj.offsetTop;
	  }
	  return returnValue;
	}
	
	function getLeftPos(inputObj)
	{
	  var returnValue = inputObj.offsetLeft;
	  while((inputObj = inputObj.offsetParent) != null){
	  	if(inputObj.tagName!='HTML')returnValue += inputObj.offsetLeft;
	  }
	  return returnValue;
	}
		
	
	function createColumns()
	{
		if(!columnParentBoxId){
			alert('No parent box defined for your columns');
			return;
		}
		columnParentBox = document.getElementById(columnParentBoxId);
		var sumWidth = 1000;
		for(var no=0;no<numberOfColumns;no++){
			var div = document.createElement('DIV');;
			div.style.cssText = 'float:left;width:'+262+'px;padding:0px;margin:0px;';
			div.style.height='100%';
			div.style.styleFloat='left';
			
			div.style.padding = '20px';//---------------------------
			div.style.margin = '20px';//---------------------------

			div.id = 'dragableBoxesColumn' + (no+1);
			columnParentBox.appendChild(div);
			
			var clearObj = document.createElement('HR');	
			clearObj.style.clear = 'both';
			clearObj.style.visibility = 'hidden';
			
		}
		
		
		
		var clearingDiv = document.createElement('DIV');
		columnParentBox.appendChild(clearingDiv);
		clearingDiv.style.clear='both';
		
	}
	
	function mouseoverBoxHeader()
	{
		if(dragDropCounter==10)return;
		var id = this.id.replace(/[^0-9]/g,'');
		document.getElementById('dragableBoxExpand' + id).style.visibility = 'visible';		
		document.getElementById('dragableBoxRefreshSource' + id).style.visibility = 'visible';		
		document.getElementById('dragableBoxCloseLink' + id).style.visibility = 'visible';
		if(document.getElementById('dragableBoxEditLink' + id))document.getElementById('dragableBoxEditLink' + id).style.visibility = 'visible';
		
	}
	function mouseoutBoxHeader(e,obj)
	{
		if(!obj)obj=this;
		
		var id = obj.id.replace(/[^0-9]/g,'');
		document.getElementById('dragableBoxExpand' + id).style.visibility = 'hidden';		
		document.getElementById('dragableBoxRefreshSource' + id).style.visibility = 'hidden';		
		document.getElementById('dragableBoxCloseLink' + id).style.visibility = 'hidden';		
		
		if(document.getElementById('dragableBoxEditLink' + id))document.getElementById('dragableBoxEditLink' + id).style.visibility = 'hidden';		
		
	}
	
	function refreshRSS()
	{
		reloadRSSData(this.id.replace(/[^0-9]/g,''));
		
	}
	
	function showHideBoxContent()
	{   
		var numericId = this.id.replace(/[^0-9]/g,'');
		var obj = document.getElementById('dragableBoxContent' + numericId);
		obj.style.display = this.src.indexOf(src_rightImage)>=0?'none':'block';
		this.src = this.src.indexOf(src_rightImage)>=0?src_downImage:src_rightImage
		
	}
	
	function mouseover_CloseButton()
	{
		this.className = 'closeButton_over';	
		
	}
	
	function highlightCloseButton()
	{
		this.className = 'closeButton_over';	
	}
	
	function mouseout_CloseButton()
	{
		this.className = 'closeButton';	
	}
	
	function closeDragableBox(e,inputObj)
	{
		if(!inputObj)inputObj = this;
		var numericId = inputObj.id.replace(/[^0-9]/g,'');
		document.getElementById('dragableBox' + numericId).style.display='none';			
		
		
	}
	
	function editRSSContent()
	{
		var numericId = this.id.replace(/[^0-9]/g,'');		
		var drObj = document.getElementById('dragableBox' + numericId);
		i++;
		drObj.style.zIndex=i;
		var obj = document.getElementById('dragableBoxContent' + numericId);
      
		if(obj.style.height=='800px'){
			
			this.innerHTML = txt_editLink_stop;
			drObj.style.left=dragableBoxesArray[numericId]['left'];
			drObj.style.top=dragableBoxesArray[numericId]['top'];
			obj.style.height =localHeight+'px';
			drObj.style.width = localWidth+'px';
		}else{			
			dragableBoxesArray[numericId]['left']=drObj.style.left;
		    dragableBoxesArray[numericId]['top']=drObj.style.top;
			this.innerHTML = txt_editLink;
			drObj.style.left='0px';
			drObj.style.top='0px';

			obj.style.height = '800px';
			drObj.style.width = '1000px';
		}
		
	}
	
	
	function showStatusBarMessage(numericId,message)
	{
		document.getElementById('dragableBoxStatusBar' + numericId).innerHTML = message;
		
	}
	
	function addBoxHeader(parentObj,externalUrl)
	{
		var div = document.createElement('DIV');
		div.className = 'dragableBoxHeader';
		div.style.cursor = 'move';
		div.id = 'dragableBoxHeader' + boxIndex;
		div.onmouseover = mouseoverBoxHeader;
		div.onmouseout = mouseoutBoxHeader;
		div.onmousedown = initDragDropBox;
		
		var image = document.createElement('IMG');
		image.id = 'dragableBoxExpand' + boxIndex;
		image.src = src_rightImage;
		image.style.visibility = 'hidden';	
		image.style.cursor = 'pointer';
		image.onmousedown = showHideBoxContent;	
		div.appendChild(image);
		
		var textSpan = document.createElement('SPAN');
		textSpan.id = 'dragableBoxHeader_txt' + boxIndex;
		div.appendChild(textSpan);
				
		parentObj.appendChild(div);	

		var closeLink = document.createElement('A');
		closeLink.style.cssText = 'float:right';
		closeLink.style.styleFloat = 'right';
		closeLink.id = 'dragableBoxCloseLink' + boxIndex;
		closeLink.innerHTML = 'x';
		closeLink.className = 'closeButton';
		closeLink.onmouseover = mouseover_CloseButton;
		closeLink.onmouseout = mouseout_CloseButton;
		closeLink.style.cursor = 'pointer';
		closeLink.style.visibility = 'hidden';
		closeLink.onmousedown = closeDragableBox;
		div.appendChild(closeLink);

			
		var image = document.createElement('IMG');
		image.src = src_refreshSource;
		image.id = 'dragableBoxRefreshSource' + boxIndex;
		image.style.cssText = 'float:right';
		image.style.styleFloat = 'right';
		image.style.visibility = 'hidden';
		image.onclick = refreshRSS;
		image.style.cursor = 'pointer';
		if(!externalUrl)image.style.display='none';
		div.appendChild(image);
		
	
		

		

	}

	
	function addRSSEditContent(parentObj)
	{

		var editLink = document.createElement('A');
		editLink.href = '#';
		editLink.onclick = cancelEvent;
		editLink.style.cssText = 'float:right';
		editLink.style.styleFloat = 'right';
		editLink.id = 'dragableBoxEditLink' + boxIndex;
		editLink.innerHTML = txt_editLink;
		editLink.className = 'dragableBoxEditLink';
		editLink.style.cursor = 'pointer';
		editLink.style.visibility = 'hidden';
		editLink.onmousedown = editRSSContent;
		parentObj.appendChild(editLink);	
				
		var editBox = document.createElement('DIV');
		editBox.style.clear='both';
		editBox.id = 'dragableBoxEdit' + boxIndex;
		editBox.style.display='none';
		var content = '<form><table cellpadding="1" cellspacing="1"><tr><td>Source:<\/td><td><input type="text" id="rssUrl[' + boxIndex + ']" value="' + dragableBoxesArray[boxIndex]['rssUrl'] + '" size="25" maxlength="255"><\/td><\/tr>'
		+ '<tr><td>Items:<\/td><td width="30"><input type="text" id="maxRssItems[' + boxIndex + ']" onblur="this.value = this.value.replace(/[^0-9]/g,\'\');if(!this.value)this.value=' + dragableBoxesArray[boxIndex]['maxRssItems'] + '" value="' + dragableBoxesArray[boxIndex]['maxRssItems'] + '" size="2" maxlength="2"><\/td><\/tr><tr><td>Fixed height:<\/td><td><input type="text" id="heightOfBox[' + boxIndex + ']" onblur="this.value = this.value.replace(/[^0-9]/g,\'\');if(!this.value)this.value=' + dragableBoxesArray[boxIndex]['heightOfBox'] + '" value="' + dragableBoxesArray[boxIndex]['heightOfBox'] + '" size="2" maxlength="3"><\/td><\/tr><tr>'
		+'<tr><td>Reload every:<\/td><td width="30"><input type="text" id="minutesBeforeReload[' + boxIndex + ']" onblur="this.value = this.value.replace(/[^0-9]/g,\'\');if(!this.value || this.value/1<5)this.value=' + dragableBoxesArray[boxIndex]['minutesBeforeReload'] + '" value="' + dragableBoxesArray[boxIndex]['minutesBeforeReload'] + '" size="2" maxlength="3">&nbsp;minute<\/td><\/tr>'
		+'<tr><td><input type="button" onclick="saveFeed(' + boxIndex + ')" value="Save"><\/td><\/tr><\/table><\/form>';
		editBox.innerHTML = content;
		parentObj.appendChild(editBox);		
		
	}
	
	
	function addBoxContentContainer(parentObj,heightOfBox)
	{
		var div = document.createElement('DIV');
		div.className = 'dragableBoxContent';
		if(opera)div.style.clear='none';
		div.id = 'dragableBoxContent' + boxIndex;
		parentObj.appendChild(div);			
		if(heightOfBox && heightOfBox/1>40){
			div.style.height = localHeight + 'px';//------------------¸ß¶È
			div.style.top=250+'px';
			div.style.left=300+'px';
			div.setAttribute('heightOfBox',heightOfBox);
			div.heightOfBox = heightOfBox;	
			if(document.all)div.style.overflowY = 'auto';else div.style.overflow='-moz-scrollbars-vertical;';
			if(opera)div.style.overflow='auto';
		}		
	}
	
	function addBoxStatusBar(parentObj)
	{
		var div = document.createElement('DIV');
		div.className = 'dragableBoxStatusBar';
		div.id = 'dragableBoxStatusBar' + boxIndex;
		
		parentObj.appendChild(div);	
		
		
	}
	
	function createABox(columnIndex,heightOfBox,externalUrl,uniqueIdentifier)
	{
		boxIndex++;
		
		var div = document.createElement('DIV');
		div.className = 'dragableBox';
		div.id = 'dragableBox' + boxIndex;
		addBoxHeader(div,externalUrl);
		addBoxContentContainer(div,heightOfBox);
		addBoxStatusBar(div);
		
		var obj = document.getElementById('dragableBoxesColumn' + columnIndex);		
		var subs = obj.getElementsByTagName('DIV');		
			div.style.position='absolute';
			div.style.top=top+'px';//-------------?
			div.style.left=left+'px';//-------------
			div.style.width=localWidth+'px';//---------------------¿í
			obj.appendChild(div);
			
		
		dragableBoxesArray[boxIndex] = new Array();
		dragableBoxesArray[boxIndex]['obj'] = div;
		dragableBoxesArray[boxIndex]['parentObj'] = div.parentNode;
		dragableBoxesArray[boxIndex]['uniqueIdentifier'] = uniqueIdentifier;
		dragableBoxesArray[boxIndex]['heightOfBox'] = heightOfBox;
		
		staticObjectArray[uniqueIdentifier] = boxIndex;
		
		return boxIndex;
		
	}
	
	function showRSSData(ajaxIndex,boxIndex)
	{
		
		
		document.getElementById('dragableBoxHeader_txt' + boxIndex).innerHTML = '<span>' + dragableBoxesArray[ajaxIndex+1]['title'] + '&nbsp;<\/span><span class="rssNumberOfItems">(' + dragableBoxesArray[ajaxIndex+1]['titleSide'] + ')<\/span>';	// title
		var string = '<table cellpadding="1" cellspacing="0">';
		string = string + '<\/table>';
		document.getElementById('dragableBoxContent' + boxIndex).innerHTML = string;
		showStatusBarMessage(boxIndex,'');
		ajaxObjects[ajaxIndex] = false;
	}
	
	function reloadRSSData(numericId)
	{
		var ajaxIndex = ajaxObjects.length-1;
		ajaxObjects[ajaxIndex] = new sack();
		
		showStatusBarMessage(numericId,'Loading data...');
		ajaxObjects[ajaxIndex].requestFile = 'http://www.google.cn';	// Specifying which file to get----------------
		ajaxObjects[ajaxIndex].onCompletion = function(){ showRSSData(ajaxIndex,numericId); };	// Specify function that will be executed after file has been found
		ajaxObjects[ajaxIndex].runAJAX(dragableBoxesArray[ajaxIndex+1]['rssUrl'],numericId,dragableBoxesArray[ajaxIndex+1]['isFrame']);		// Execute AJAX function			
        
		

	}
		
	function createARSSBox(url,columnIndex,heightOfBox,maxRssItems,minutesBeforeReload,uniqueIdentifier,title,isFrame)
	{
        
		if(!heightOfBox)heightOfBox = '0';
		if(!minutesBeforeReload)minutesBeforeReload = '0';

		var tmpIndex = createABox(columnIndex,heightOfBox,true);
        
		
		dragableBoxesArray[tmpIndex]['rssUrl'] = url;
        dragableBoxesArray[tmpIndex]['title'] = uniqueIdentifier;
		dragableBoxesArray[tmpIndex]['titleSide'] = titleSide;
		dragableBoxesArray[tmpIndex]['isFrame'] = isFrame;
		
		var tmpInterval = false;
		

		dragableBoxesArray[tmpIndex]['intervalObj'] = tmpInterval;
		
		alert("d"+document.getElementById('dragableBoxHeader1'));
			
		addRSSEditContent(document.getElementById('dragableBoxHeader' + tmpIndex))
			
		if(!document.getElementById('dragableBoxContent' + tmpIndex).innerHTML)document.getElementById('dragableBoxContent' + tmpIndex).innerHTML = 'loading RSS data';

		if(url.length>0 && url!='undefined'){
			
			var ajaxIndex = ajaxObjects.length;
			ajaxObjects[ajaxIndex] = new sack();
			if(!maxRssItems)maxRssItems = 100;
			ajaxObjects[ajaxIndex].requestFile = 'readRSS.php?rssURL=' + escape(url) + '&maxRssItems=' + maxRssItems;	// Specifying which file to get
			ajaxObjects[ajaxIndex].onCompletion = function(){ showRSSData(ajaxIndex,tmpIndex); };	
			// Specify function that will be executed after file has been found
			ajaxObjects[ajaxIndex].runAJAX(dragableBoxesArray[tmpIndex]['rssUrl'],tmpIndex,dragableBoxesArray[tmpIndex]['isFrame']);		// Execute AJAX function
			
		}else{
			hideHeaderOptionsForStaticBoxes(tmpIndex);
		}			
	}
	

	
	function cancelSelectionEvent(e)
	{
		if(document.all)e = event;
		if (e.target) source = e.target;
			else if (e.srcElement) source = e.srcElement;
			if (source.nodeType == 3) // defeat Safari bug
				source = source.parentNode;
		if(source.tagName.toLowerCase()=='input')return true;
						
		if(dragDropCounter>=0)return false; else return true;	
		
	}
	
	function cancelEvent()
	{
		return false;
	}
	
	function initEvents()
	{
		document.body.onmousemove = moveDragableElement;
		document.body.onmouseup = stop_dragDropElement;
		document.body.onselectstart = cancelSelectionEvent;
		document.body.ondragstart = cancelEvent;	
		
		documentHeight = document.documentElement.clientHeight;	
		
	}
	
	
	function createFeed(url,title,titleSide,localWidth,left,top,localHeight,isFrame)
	{
	initDragableBoxesScript();
	this.url = url;
		var title =title;
		var height = "111";
		var items = 'dfsadf';
		 
		 this.titleSide=titleSide;
		 this.localWidth = localWidth;
		 this.left=left;
		 this.top=top;
		 this.localHeight = localHeight; 
		 this.isFrame=isFrame;
		var reloadInterval = '33';
		if(isNaN(height) || height/1<40)height = true;	
		if(isNaN(reloadInterval) || reloadInterval/1<5)reloadInterval = false;
		createARSSBox(url,1,height,items,reloadInterval,title,titleSide,isFrame);	
	}
	
	
	/* You customize this function */
		
	
	function initDragableBoxesScript()
	{
		
		// Always the first line of this function
		initEvents();	// Always the third line of this function
		createColumns();
		// Create RSS boxes from cookies
		// Create default boxes.
		
	}

	
	
	