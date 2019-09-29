
function ajax(name, value, urls, megs) {
    var code = value;
    code = trim_x(code);
    if (trim_x(code).length === 0) {
        return 1;
    }
    var url = urls;
    var date = "action=checkajax&" + name + "=" + code;
    var meg = megs;
    loadHTMLFromServer(url, date, meg);
}

function loadHTMLFromServer(url, data, handler) {
    var xmlRequest;
    try {
        xmlRequest = new ActiveXObject("Msxml2.XMLHTTP");
    }
    catch (e) {
        try {
            xmlRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }
        catch (ee) {
            xmlRequest = new XMLHttpRequest();
        }
    }

    xmlRequest.onreadystatechange = function () {
        if (xmlRequest.readyState == 4 && xmlRequest.status == 200 && handler != null) {
            handler(xmlRequest.responseText);
        }
    };
    data = encodeURI(data);
    data = encodeURI(data);
    var newUrl = addURLParameter(url, "id=" + getSystemDateTime());
    xmlRequest.open("POST", newUrl, false);
    xmlRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlRequest.send(data);
}

function addURLParameter(url, param) {
    if (url.indexOf("?") > 0) {
        url += "&" + param;
    } else {
        url += "?" + param;
    }
    return url;
}

function getSystemDateTime() {
    var systemDateTime = new Date();
    var year = systemDateTime.getFullYear();
    var month = systemDateTime.getMonth();
    var day = systemDateTime.getDate();
    var hour = systemDateTime.getHours();
    var minute = systemDateTime.getMinutes();
    var second = systemDateTime.getSeconds();
    var retValue = "";
    retValue += year;
    retValue += "-" + getTwoNumber(month + 1);
    retValue += "-" + getTwoNumber(day);
    retValue += "-" + getTwoNumber(hour);
    retValue += "-" + getTwoNumber(minute);
    retValue += "-" + getTwoNumber(second);
    return retValue;
}

function getTwoNumber(strValue) {
    if (("" + strValue).length == 1) {
        strValue = "0" + strValue;
    }
    return strValue;
}
function trim_x(str) {
    if (str == "") {
        return "";
    }
    //去掉首尾空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}

function removeAllData(name) {
    var sel_size = document.getElementById(name).options.length;
    if (sel_size > 0) {
        for (var i = 0; i < sel_size; i++) {
            document.getElementById(name).options.remove(0);
        }
    }
}

function addNewOption(name, value) {
    if (value[1] != "" && value[0] != "") {
        document.getElementById(name).add(new Option(value[1], value[0]));
    }
}

function setObjVal(ajax) {
	var value = trim_x(ajax);
	
	value = value.replace(/[\r\n]/g,"");
	var field = value.split(",");
	
    for (var i = 0; i < field.length; i++) {
        sub = field[i].split("=");
        
        document.getElementById(trim_x(sub[0])).value = trim_x(sub[1]);     
    }
}

function setAlert(ajax) {
	var value = trim_x(ajax);

	if (value != "0") {
		document.getElementById("ts01").style.display = "block";
		document.getElementById("ts01").innerHTML="<img src='/bfbposp/images/cha.png' />该编号已经存在";
		document.getElementById("hval").value = "1";
	}else {
		document.getElementById("ts01").style.display = "none";
		document.getElementById("hval").value = "0";
	}
}

function setList(ajax) {
	var value = trim_x(ajax);

	var field = value.split("!");

    for (var i = 0; i < field.length-1; i++) {
        sub = field[i].split("~");

        document.getElementById("area_code").options.add(new Option(sub[1],sub[0])); 
    }
}

function checkMsg(ajax) {
    var value = trim_x(ajax);
    if (value == "") {
        document.getElementById("yyshop_no").style.display = "none";
    }
    if (value == "0") {
        return;
    } else {
        var table = value.split("|");
        removeAllData("yyshop_no");
        for (var i = 0; i < table.length; i++) {
            sub = table[i].split(",");
            addNewOption("yyshop_no", sub);
        }
    }
}
