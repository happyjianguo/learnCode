function isTime(str){
    var a = str.match(/^(\d{0,2}):(\d{0,2}):(\d{0,2})$/);
    
    if (a == null)
        return false;
    if (a[1]>=24 || a[2]>=60 || a[3]>=60)
    	return false;

    return true;
}
function isHourMinute(str){
    var a = str.match(/^(\d{0,2}):(\d{0,2})$/);
    
    if (a == null)
        return false;
    if (a[1]>=24 || a[2]>=60)
    	return false;

    return true;
}
function isMinute(str){
		var a = str.match(/^(\d{0,2})·ÖÖÓ$/);
		
		
		if (a == null)
        return false;

    return true;
}
function isMinusMinute(str){
		var a = str.match(/^(\.{0,1})(\d{0,2})·ÖÖÓ$/);
		
		    
		if (a == null)
        return false;

    return true;
}
function isMinusPercent(str){
		var a = str.match(/^(\.{0,1})(\d{0,2})%$/);
		
		    
		if (a == null)
        return false;

    return true;
}

function isPercent(str){
		var a = str.match(/^(\d{0,3})\.(\d{0,2})%$/);
		
		if (a == null)
        return false;
    if (a[1]>100)
    	return false;
		if (a[1]>=100 && a[2]>0)
		  return false;
    return true;
}
function isYearToMinute(str){
    var a = str.match(/^(\d{0,4})-(\d{0,2})-(\d{0,2}) (\d{0,2}):(\d{0,2})$/);
    
    if (a == null)
        return false;
    if ( a[2]>=13 || a[3]>=32 || a[4]>=24 || a[5]>=60)
        return false;

    return true;
}

function isDateTime(str){
    var a = str.match(/^(\d{0,4})-(\d{0,2})-(\d{0,2}) (\d{0,2}):(\d{0,2}):(\d{0,2})$/);
    
    if (a == null)
        return false;
    if ( a[2]>=13 || a[3]>=32 || a[4]>=24 || a[5]>=60 || a[6]>=60)
        return false;
    
    return true;
}

function isDate(str){
    var a = str.match(/^(\d{0,4})-(\d{0,2})-(\d{0,2})$/);
    
    if (a == null)
        return false;
    if ( a[2]>=13 || a[3]>=32 || a[4]>=24) return false;

    return true;
}

function isZY(str){
    var a = str.substring(0,str.indexOf("-")+1).match(/^(\d{0,6})-$/);
    
    if (a == null)
        return false;
 
    return true;
}

function isNumber(checkStr){
    var checkOK = "0123456789";
    var allValid = true;
    var checkCode = 0;
    for (i = 0; i < checkStr.length; i++) {
        ch = checkStr.charAt(i);
        if (checkOK.indexOf(ch) == -1) {
            allValid = false;
            break;
	}
    }
    return(allValid);
}



function validate(obj,type){
    var range = obj.createTextRange(); 
    var text = range.text;
    var selrange = document.selection.createRange();
    var seltext = selrange.text;
    var startpos = 0,endpos = 0;
    
    while(selrange.compareEndPoints("StartToStart",range)>0){ 
        selrange.moveStart("character",-1); 
        startpos ++;
    }
    while(selrange.compareEndPoints("EndToStart",range)>0){ 
        selrange.moveEnd("character",-1); 
        endpos ++;
    }
    if(event.keyCode >=96 && event.keyCode <=105){
        var keytext = String.fromCharCode(event.keyCode - 48);
        text = text.substring(0,startpos) + keytext + text.substring(endpos,text.length);
    }else if(event.keyCode>=48){ 
        var keytext = String.fromCharCode(event.keyCode);
        text = text.substring(0,startpos) + keytext + text.substring(endpos,text.length);
    }else if(event.keyCode == 46){//delete
        if(startpos == endpos)
            text = text.substring(0,startpos) + text.substring(startpos+1,text.length);
        else
            text = text.substring(0,startpos) + text.substring(endpos,text.length);
    }else if(event.keyCode == 8){
        if(startpos == endpos)
            text = text.substring(0,startpos-1) + text.substring(startpos,text.length);
        else
            text = text.substring(0,startpos) + text.substring(endpos,text.length);
    }

    if(event.keyCode == 45){
        event.returnValue = false;
        return;
    }

    var valid;
    switch(type){
        case 10:
            valid = isDate(text);
            break;
        case 2:
            valid = isTime(text);
            break;
        case 3:
            valid = isDateTime(text);
            break;
        case 4:
            valid = isYearToMinute(text);
            break;
        case 5:
        	valid = isHourMinute(text);
        	break;
        case 6:
        	valid = isMinute(text);
        	break;
        case 7:
          valid = isPercent(text);
          break;
        case 8:
        	valid = isMinusMinute(text);
        	break;
        case 9:
        	valid = isMinusPercent(text);
        	break;	
        case 1:
          valid = isNumber(text);
          break;
        case 11:
          valid = isZY(text);
          break;
        default:
            valid = false;
    }

    if(!valid){
        event.returnValue = false;
    }
}