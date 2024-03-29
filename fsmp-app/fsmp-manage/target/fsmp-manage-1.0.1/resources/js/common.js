/**
 * 序列化表单，提交数据
 * @param form
 * @return
 */
function serializeForm(form){
	var obj = {};
	$.each(form.serializeArray(),function(index){
		if(obj[this['name']]){
			obj[this['name']] = obj[this['name']] + ','+this['value'];
		} else {
			obj[this['name']] =this['value'];
		}
	});
	return obj;
}
/**
 *校验特殊字符
 *
 */
function stringCheck(value){
	return  /^[A-Za-z0-9\u4e00-\u9fa5_-]+$/.test(value) ;
}
/**
 *获取字符串长度
 *
 */
function getStringLength(str){
	var len = 0;
	for (var i=0; i<str.length; i++) { 
		var c = str.charCodeAt(i); 
	    //单字节加1 
		if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) { 
			len++; 
	    }else { 
	    	len+=3; 
	    } 
	}
	return len;
}
function getLimitText(str,num){
	var len = 0;
	for (var i=0; i<str.length; i++) { 
		var c = str.charCodeAt(i); 
	    //单字节加1 
		if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) { 
			len++; 
	    }else { 
	    	len+=3; 
	    } 
		if(len>num){
			return str.substring(0,i);
		}
	}
	return str;
}
//获取当前日期
function getCurrentDate(){
   var mydate = new Date();
   var str = "" + mydate.getFullYear() + "-";
   str += (mydate.getMonth()+1) + "-";
   str += mydate.getDate();
   return str;
 }
//比较两个日期大小
function compareDate(date1, date2){
	var begin = new Date(date1.replace("-", "/").replace("-", "/"));
  	var end = new Date(date2.replace("-", "/").replace("-", "/"));
  	if(end > begin){
  		return 1
  	}else if(end == begin){
  		return 0
  	}else{
  		return -1;
  	}
}
//跳转URL
function jumpurl(action){
	var token = $("meta[name='_csrf']").attr("content");
    var form = $("<form></form>");
    form.attr('action',action);
    form.attr('method','post');
    var csrfinput = $("<input type='hidden' name='_csrf' />")
    csrfinput.attr('value',token)
    form.append(csrfinput)
    form.appendTo("body")
    form.css('display','none')
    form.submit();
    return true;
}

//跳转统带参数
function submiturl(action,val){
	var token = $("meta[name='_csrf']").attr("content");
    var form = $("<form></form>");
    form.attr('action',action);
    form.attr('method','post');
    var csrfinput = $("<input type='hidden' name='_csrf' />")
    csrfinput.attr('value',token)
    form.append(csrfinput)
    if(val){
    	var jsonval = {};
    	if($.type(val) === "string"){
    		var jsonval = $.parseJSON(val);
    	}else{
    		jsonval = val;
    	}
        for(var key in jsonval){
        	var valinput = $("<input type='hidden' name='"+key+"' />")
            valinput.attr('value',jsonval[key])
            form.append(valinput)
        }
    }
    form.appendTo("body")
    form.css('display','none')
    form.submit();
    return true;
}

//获取URI
function getClearUrl(url) {
	try {
		if (url.indexOf('http://') >= 0) {
			url = url.substring(url.indexOf('//') + 2);
		}
		if (url.indexOf('/') >= 0) {
			url = url.substring(url.indexOf('/'));
		}
	} catch (e) {
	}
	return url;
}

//激活菜单
function activateLeftMenu(domain) {
	var cookiemenu = getCookie("leftMenuSelected");
	var menuSelectedid = cookiemenu == null ? 0 : cookiemenu;
	var tempflag = true;
	if(menuSelectedid == 0){
		//激活主菜单
		var currenturl = getClearUrl(document.location.toString());
		$.each($('#main-menu').find('.link'),function(e) {
			var linkhref = $(this).find('a').attr('href');
			if(linkhref == currenturl){
				setCookie("leftMenuSelected", $(this).find('a').attr('id'), 0, domain);
				$(this).parent().addClass('open');
				tempflag = false;
				return false;
			}
		});
		if(tempflag){
			//激活子菜单
			$.each($('#main-menu').find(".submenu li"),function(e) {
				var menuhref = $(this).find('a').attr('href');
				if(menuhref == currenturl){
					setCookie("leftMenuSelected", $(this).find('a').attr('id'), 0, domain);
					$(this).addClass('cur');
					$(this).parent().parent().addClass('open');
					$(this).parent().show();
					return false;
				}
			});
		}
	}else{
		//激活主菜单
		$.each($('#main-menu').find('.link'),function(e) {
			var linkid = $(this).find('a').attr('id');
			if(linkid == menuSelectedid){
				$(this).parent().addClass('open');
				tempflag = false;
				return false;
			}
		});
		if(tempflag){
			//激活子菜单
			$.each($('#main-menu').find(".submenu li"),function(e) {
				var menuid = $(this).find('a').attr('id');
				if(menuid == menuSelectedid){
					$(this).addClass('cur');
					$(this).parent().parent().addClass('open');
					$(this).parent().show();
					return false;
				}
			});
		}
	}
}

//设置Cookie
function setCookie(name, value, expires, path, domain, secure) {
	try {
		var str = name + "=" + escape(value);
		if (expires > 0) {// 为0时不设定过期时间，浏览器关闭时cookie自动消失
			var date = new Date();
			var ms = expires * 24 * 3600 * 1000;
			date.setTime(date.getTime() + ms);
			str += "; expires=" + date.toGMTString();
		}
		str += ((path) ? "; path=" + path : "")
				+ ((domain) ? "; domain=" + domain : "")
				+ ((secure) ? "; secure" : "");
		document.cookie = str;
	} catch (ee) {
	}
}

// 获取Cookie
function getCookie(name) {
	try {
		var prefix = name + "="
		var start = document.cookie.indexOf(prefix)
		if (start == -1) {
			return null;
		}
		var end = document.cookie.indexOf(";", start + prefix.length)
		if (end == -1) {
			end = document.cookie.length;
		}
		var value = document.cookie.substring(start + prefix.length, end)
		return unescape(value);
	} catch (ee) {
	}
}
//删除cookie
function delCookie(name, path, domain, secure){
	try {
		var str = name + "=";
		//为了删除指定名称的cookie，可以将其过期时间设定为一个过去的时间 
		var date = new Date();
	    date.setTime(date.getTime() - 10000);
		str += "; expires=" + date.toGMTString();
		
		str += ((path) ? "; path=" + path : "")
				+ ((domain) ? "; domain=" + domain : "")
				+ ((secure) ? "; secure" : "");
		document.cookie = str;
	} catch (ee) {
	}
}

/**
* 对金额进行转换，将金额转换为以元为单位，小数点后有两位
* 例：输入域1234，转换后隐含域为1234.00
* @param {String} 表单名称
* @param {String} 要转换的金额输入框名称
* @param {String} 金额隐藏域名称
*/
function convertMoney(txtmoney){
	var re = /,/g;
	    
    var txt_money = $("#"+txtmoney);
	var txt_money_val = $.trim(txt_money.val());
	if (txt_money_val == ""){
		//alert("请输入正确的金额数字!");
		txt_money.val("");
		txt_money.focus();
		return;
	}
	
	
	//删除开头的0
	txt_money_val = txt_money_val.replace(/^0*,+/g,"");
	//删除结尾的0
	if(txt_money_val.indexOf(".") > 0){
		txt_money_val = txt_money_val.replace(/\.*0*$/g,"");
	}
	//删除，号
	txt_money_val = txt_money_val.replace(/,/g,"");
	//在小数点前面自动补0(例如：".25" 自动转换成"0.25")
	var tonumber = txt_money_val.replace(re,"").charAt(0) == "."? "0" + txt_money_val:txt_money_val;

	if (tonumber !=="" && tonumber!=null){
		rep = / /g;
		var amt = tonumber.replace(rep,"");
		var moneyreg = /^([0-9]+|[0-9]{1,3}(,[0-9]{1,3})*)(\.[0-9]{1,2})?$/g;
		if(!moneyreg.test(amt)){
			//alert("2请输入正确的金额数字!");
			return;
		}
	
		re = /,/g;
		var amt1 = amt.replace(re,"");

		var amt2=parseFloat(amt1);		
		if(amt2<0){
			//alert("输入的金额不能小于或等于零,请重新输入!");
			txt_money.focus();
			return;
		}else{		//大于0的正数;
			if(amt1.indexOf(".")!=-1){				
				var str = amt1.substr(amt1.indexOf(".")+1);				
				if(str.length==1){
					amt1=amt1 + "0";
				}else if(str.length<1){
					amt1=amt1 + "00";
				}
			}else{
				amt1=amt1 + ".00";
			}
			
			var temp=amt1.substring(0,amt1.indexOf("."));
			if (temp.length > 14 || amt1 > '99999999999999.99'){
			    //alert("输入的金额太大，请重新输入!");
			    txt_money.focus();
			    return;
			}
			
			txt_money.val(comma(temp) + amt1.substring(amt1.indexOf(".")));
			return;							
		}
    }
}
/**
* 对金额进行转换，将金额转换为以元为单位，小数点后有两位
* 例：输入域1234，转换后隐含域为1234.000
* @param {String} 表单名称
* @param {String} 要转换的金额输入框名称
* @param {String} 金额隐藏域名称
*/
function convertMoney2(txtmoney){
	var re = /,/g;
	    
    var txt_money = $("#"+txtmoney);
	var txt_money_val = $.trim(txt_money.val());
	if (txt_money_val == ""){
		//alert("请输入正确的金额数字!");
		txt_money.val("");
		txt_money.focus();
		return;
	}
	
	
	//删除开头的0
	txt_money_val = txt_money_val.replace(/^0*,+/g,"");
	//删除结尾的0
	if(txt_money_val.indexOf(".") > 0){
		txt_money_val = txt_money_val.replace(/\.*0*$/g,"");
	}
	//删除，号
	txt_money_val = txt_money_val.replace(/,/g,"");
	//在小数点前面自动补0(例如：".25" 自动转换成"0.25")
	var tonumber = txt_money_val.replace(re,"").charAt(0) == "."? "0" + txt_money_val:txt_money_val;

	if (tonumber !=="" && tonumber!=null){
		rep = / /g;
		var amt = tonumber.replace(rep,"");
		var moneyreg = /^([0-9]+|[0-9]{1,3}(,[0-9]{1,3})*)(\.[0-9]{1,3})?$/g;
		if(!moneyreg.test(amt)){
			//alert("2请输入正确的金额数字!");
			return;
		}
	
		re = /,/g;
		var amt1 = amt.replace(re,"");

		var amt2=parseFloat(amt1);		
		if(amt2<0){
			//alert("输入的金额不能小于或等于零,请重新输入!");
			txt_money.focus();
			return;
		}else{		//大于0的正数;
			if(amt1.indexOf(".")!=-1){				
				var str = amt1.substr(amt1.indexOf(".")+1);				
				if(str.length==1){
					amt1=amt1 + "00";
				}
				else if(str.length==2){
					amt1=amt1 + "0";
				}
				else if(str.length<1){
					amt1=amt1 + "000";
				}
			}else{
				amt1=amt1 + ".000";
			}
			
			var temp=amt1.substring(0,amt1.indexOf("."));
			if (temp.length > 14 || amt1 > '9999999999999.999'){
			    //alert("输入的金额太大，请重新输入!");
			    txt_money.focus();
			    return;
			}
			
			txt_money.val(comma(temp) + amt1.substring(amt1.indexOf(".")));
			return;							
		}
    }
}

/**
* 表现形式增加逗号，只对整数部分做处理
* @param {Integer} 需转换数值	
* @return {String} 转换后的字符串
*/
function comma(number) {
	number = '' + number;
	if (number.length > 3) {
		var mod = number.length % 3;
		var output = (mod > 0 ? (number.substring(0,mod)) : '');
		for (i=0 ; i < Math.floor(number.length / 3); i++) {
			if ((mod == 0) && (i == 0)){
				output += number.substring(mod+ 3 * i, mod + 3 * i + 3);
			}else{
				output += ',' + number.substring(mod + 3 * i, mod + 3 * i + 3);
			}
		}
		return output;
	}
	else{
		return number;
	}
}
/**
 * js 数字转金额两位小数
 */
function changePrice2money(s)
{
    if (/[^-0-9\.]/.test(s)) 
    	return "invalid value";
    
    s = s.replace(/^(\d*)$/, "$1.");
    s = (s + "00").replace(/(\d*\.\d\d)\d*/, "$1");
    s = s.replace(".", ",");
    var re = /(\d)(\d{3},)/;
    
    while (re.test(s))
        s = s.replace(re, "$1,$2");
    
    s = s.replace(/,(\d\d)$/, ".$1");
    return s.replace(/^\./, "0.")
}
/**
 * js 数字转金额三位小数
 */
function changePrice3money(s){
	return fmoney(s, 3)
}
function fmoney(s, n) {
	n = n > 0 && n <= 20 ? n : 2;
	s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
	t = "";
	for (i = 0; i < l.length; i++) {
		t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	}
	return t.split("").reverse().join("") + "." + r;
}
//Html编码获取Html转义实体
function htmlEncode(value){
  return $('<div/>').text(value).html();
}
function HTMLEncode(html) 
{ 
var temp = document.createElement ("div"); 
(temp.textContent != null) ? (temp.textContent = html) : (temp.innerText = html); 
var output = temp.innerHTML; 
temp = null; 
return output; 
} 

function HTMLDecode(text) 
{ 
var temp = document.createElement("div"); 
temp.innerHTML = text; 
var output = temp.innerText || temp.textContent; 
temp = null; 
return output; 
} 