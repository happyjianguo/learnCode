var secretExtentId="002001";
var secretDateId="003001";

function checkAll(e, itemName)
{
	var aa = document.getElementsByName(itemName);
	
	for (var i=0; i<aa.length; i++)
		aa[i].checked = e.checked;
}
function checkAllIframe(e, itemName,name)
{
	var aa = window[name].document.getElementsByName(itemName);
	
	for (var i=0; i<aa.length; i++)
		aa[i].checked = e.checked;
}

function check(obj1,obj2){			
		var flag=0;	
		
		var xh = obj1.split(":");		
		var xh1 = obj2.split(":");		
		
		var myRegExp=/^\d{2}:\d{2}$/;	

		if(obj1.match(myRegExp)){
				if(checktime(xh[0],xh[1])){
					flag=1;								
			}			
		}
		if(obj2.match(myRegExp)){
				if(checktime(xh1[0],xh1[1])){
					if(flag==0){
							flag==0;
						}
					else{
						flag=1;
					}
				}else{
					flag=0;
				}
		}else{
			flag=0;
		}
		if(flag==1){
			
			return true;
		}
		else{
			
			return false;
		}
	}
	
function checktime(hh,mi){
		var flag=0;
		if(hh.charAt(0)>=0 && hh.charAt(0)<=2){
			if(hh.charAt(0)==0 || hh.charAt(0)==1){
				if(hh.charAt(1)>=0 && hh.charAt(1)<=9){
					if(mi.charAt(0)>=0 && mi.charAt(0)<=5){			
						if(mi.charAt(1)>=0 && mi.charAt(1)<=9){							
							flag=1;
					}	
				}
			}
		}else if(hh.charAt(0)==2){
			if(hh.charAt(1)>=0 && hh.charAt(1)<=3){
					if(mi.charAt(0)>=0 && mi.charAt(0)<=5){			
						if(mi.charAt(1)>=0 && mi.charAt(1)<=9){
							flag=1;
					}	
				}
			}
		}
	}
		if(flag==0){
			return false;
		}else{
			return true;
		}		
	}
	
// Opinion select ????????
function openOpinionSelect(op)
{ 	
		var sUrl = "IndividualOpinion.do?method=queryIndividualOpinionForSelect&opinion="+op;
		window.open (sUrl, "newwindow", "height=270, width=415,top=200,left=400,toolbar=no,menubar=no,scrollbars=yes, location=no, status=no,title=no");
}
function openSelectNextPeo(url)
{ 	
	window.open (url, "newwindow", "height=300, width=280,top=200,left=400,toolbar=no,menubar=no,scrollbars=yes, location=no, status=no,title=no");
}
function openEditAttachmentName(url)
{ 	
	window.open (url, "newwindow", "height=250, width=280,top=250,left=400,");
}
	// type=029001 driver
	function openDriverOnly()
	{ 	
		var sUrl = "SelectCarInfo.do?method=SelectDriverInfo";// ?????
		window.open (sUrl, "newwindow", "height=300, width=280,top=200,left=400,toolbar=no,menubar=no,scrollbars=yes, location=no, status=no,title=no");
	}
	// all driver
	function openAllDriverOnly()
	{ 	
		var sUrl = "SelectCarInfo.do?method=SelectDriverInfo";
		window.open (sUrl, "newwindow", "height=300, width=280,top=200,left=400,toolbar=no,menubar=no,scrollbars=yes, location=no, status=no,title=no");
	}
		// driver for app ????????????
	function openDriverForappOnly()
	{ 	
		var sUrl = "SelectCarInfo.do?method=SelectDriverInfoForApp";
		window.open (sUrl, "newwindow", "height=300, width=280,top=200,left=400,toolbar=no,menubar=no,scrollbars=yes, location=no, status=no,title=no");
	}
	
	// car_sign ??????????
	function opencarOnly()
	{ 	
		var sUrl = "SelectCarInfo.do?method=GetCarinfoList";
		window.open (sUrl, "newwindow", "height=430, width=280,top=200,left=400,toolbar=no,menubar=no,scrollbars=yes, location=no, status=no,title=no");
	}
	// car_sign ??????????
	function opencarForappOnly()
	{ 	
		var sUrl = "SelectCarInfo.do?method=GetCarinfoListForApp";
		window.open (sUrl, "newwindow", "height=430, width=280,top=200,left=400,toolbar=no,menubar=no,scrollbars=yes, location=no, status=no,title=no");
	}

	
	// ??
	function openwinOnly()
	{ // v2.0
		// var sUrl = "jsp/org/show_Org.jsp?type=1";
		var sUrl = "selectOrgAndUser.do?method=GetOrgList";
		window.open (sUrl, "newwindow", "height=320, width=275,top=200,left=400,toolbar=no,menubar=no,scrollbars=yes, location=no, status=no,title=no");
	}								

	function outiWebOffice(mClientUrl){
		var iwebStr = '<OBJECT id="WebOffice" width="100%" height="100%" classid="clsid:8B23EA28-723C-402F-92C4-59BE0E063499" codebase="'+mClientUrl+'"></OBJECT>';
		document.write(iwebStr);
	}
	function printFunction(){
		window.print();
	}	
	// ??101??????????????
	function selCompanyInfo()
	{
		var sUrl = "inspectionInformation.do?method=queryOnezeroone";
		window.open (sUrl, "newwindow", "height=300, width=400,top=200,left=400,toolbar=no,menubar=no,scrollbars=yes, location=no, status=no,title=no");
	}
	// ????????
	function viewPostilCom(sUrl){
		window.open(sUrl,'fullscreen','fullscreen,scrollbars') 
		// window.open (sUrl, "newwindow", "height=768,
		// width=1024,top=50,left=50,toolbar=no,menubar=no,scrollbars=yes,
		// location=no, status=no,title=no");
	}