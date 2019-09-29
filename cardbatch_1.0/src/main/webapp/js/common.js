function trim(temp){
    while (temp.charAt(0)==" "){
       temp=temp.substring(1);
    }
    while (temp.charAt(temp.length-1)==" "){
       temp=temp.substring(0,temp.length-1);
    }
    
    return temp;
}

function showElementById(obj){
    document.getElementById(obj).style.display = "";
}

function hiddenElementById(obj){
    document.getElementById(obj).style.display = "none";
}

//se she wu ru
function rount(amt){
	var amount = "" + amt;
	var index = amount.indexOf(".");
	if(index < 0)
		return amount;
	var length = amount.length;
	if((length - index) > 3){
		if(amount.charAt(index + 3) >= '5'){
			var tmp = amount.substring(index+1,index + 3);
			if(tmp == "99")
				amount = parseInt(amount.substring(0,index)) +1;
			else
				amount = amount.substring(0,index) + "." + (parseInt(tmp) + 1);
		}else if(amount.charAt(index + 3) < '5')
			amount = amount.substring(0,index + 3);
	}
	return amount;
}

function lzero(str,len){
    var sRtn = "";

    for (var i = 0; i < (parseInt(len) - str.length); i++){
        sRtn = sRtn + "0";
    }

    return sRtn + str;
}

function rzero(str,len){
    var sRtn = "";

    for (var i = 0; i < (parseInt(len) - str.length); i++){
        sRtn = sRtn + "0";
    }

    return str + sRtn;
}

function getXmlHttp(){
    var xmlHttp;
    if (window.ActiveXObject){
    	xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }else if(window.XMLHttpRequest){
    	xmlHttp = new XMLHttpRequest();
    }
               
    return xmlHttp;
}

function confirmDel(msg){
    return window.confirm(msg);
}

function SetRowColor(){
	var oRow = null;
	if(window.event.srcElement.tagName == "TD"){
		oRow = window.event.srcElement.parentNode;
	}
	if(window.event.srcElement.tagName == "TR"){
		oRow = window.event.srcElement;
	}
	if (oRow != null) {
		if(oLastClickRow != null){
			if(oLastClickRow == oRow){
		    	return;
			}
			oLastClickRow.bgColor = "#ffffff";
		}
		oRow.bgColor = "#80ffff";
		oLastClickRow = oRow;
	}
}
function ResetRow(){
	if(oLastClickRow != null){
		oLastClickRow.bgColor = "#ffffff";
		oLastClickRow = null;
	}
}

function RemoveRow(op){ 
	var iRows = op.rows.length; 
	for(var i=0;i<iRows-1;i++){ 
		op.deleteRow(1); 
	} 
}

function openwindow(opurl,title,style){
	var inputWin;
	if(inputWin==null || inputWin.closed){
		inputWin = window.open(opurl,title,style);
		inputWin.focus();
	}else{
		inputWin.close();
		inputWin = inputWin = window.open(opurl,title,style);
		inputWin.focus();
	}
	
	return inputWin;
}

function chooseFolder(){
	var savePath = "";
	var objSrc=new ActiveXObject("Shell.Application").BrowseForFolder(0,'Please Check Fold:',0,'');
	if(objSrc!=null){
		savePath=objSrc.Items().Item().Path;				
	}
	return savePath;
}