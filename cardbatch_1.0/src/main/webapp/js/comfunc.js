//??????????????????????????
function changePic(obj,num,path){
    var tag = document.getElementsByName(obj);
    var pic = path+obj+num+".gif";
    tag[0].src = pic;
}

//????????????????????Excel
function AllAreaExcel(){
	var oXL = new ActiveXObject("Excel.Application"); 
	var oWB = oXL.Workbooks.Add(); 
	var oSheet = oWB.ActiveSheet;
	var sel=document.body.createTextRange();
	var PrintA = document.getElementById("PrintA");
	sel.moveToElementText(PrintA);
	sel.select();
	sel.execCommand("Copy");
	oSheet.Paste();
	oXL.Visible = true;
}

//??????????????????????????Excel
function CellAreaExcel(){
	var oXL = new ActiveXObject("Excel.Application");
	var oWB = oXL.Workbooks.Add();
	var oSheet = oWB.ActiveSheet;
	var PrintA = document.getElementById("PrintA");
	var Lenr = PrintA.rows.length;
	for (i=0;i<Lenr;i++){
		var Lenc = PrintA.rows(i).cells.length;
		for (j=0;j<Lenc;j++){
			oSheet.Cells(i+1,j+1).value = PrintA.rows(i).cells(j).innerText;
		}
  	}
	oXL.Visible = true;
}
//????????????
function printIt() {
	htmlcode=window.document.body.innerHTML;
	sprnstr="<!--startprint-->";
	eprnstr="<!--endprint-->";
	var prnhtml=htmlcode.substr(htmlcode.indexOf(sprnstr)+17);
	prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
	window.document.body.innerHTML=prnhtml;
	window.print();
	window.document.body.innerHTML=htmlcode;
}

//????????????
function saveStr(str,flag,defaultname) {
	htmlcode=window.document.body.innerHTML;
	window.document.body.innerText=str;
	window.document.execCommand('saveas',flag,defaultname);
	window.document.body.innerHTML=htmlcode;
}



