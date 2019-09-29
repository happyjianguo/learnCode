function getDate(DateType,iRelatePub)
{
 var url,top,left,style,DataValue,temp;
 top=window.event.screenY+12;
 left=window.event.screenX-100;
 temp="";
// if (window.document.all.item(DateType).className=="ReadOnly")
 if (document.getElementById(DateType).className=="ReadOnly")
 		return;
 if (iRelatePub==null)
   iRelatePub=2;
 for (i=1;i<=iRelatePub;i++)
   temp+="js/";
 Url=temp+"Calendar.html";
 style="help:no;status:no;dialogWidth:11;dialogHeight:13;dialogTop:"+top+";dialogLeft:"+left;
 DataValue=window.showModalDialog(Url,null,style);
 if (DataValue!=null)
    document.getElementById(DateType).value=DataValue;
 document.getElementById(DateType).focus();
 return ((DataValue==null)?"":DataValue);
}

function GetDateChg(DateType,iRelatePub)
{
 var rcOrg,rcNew;
// rcOrg=window.document.all.item(DateType).value;
 rcOrg = document.getElementById(DateType).value;
 if (rcOrg==null)
	rcOrg="";
 rcNew=GetDate(DateType,iRelatePub);
 if (rcOrg!=rcNew && rcNew!="")
	anyChanged();
 return rcNew;
}


function getTime(DateType,iRelatePub)
{
 var url,top,left,style,DataValue,temp;
 top=window.event.screenY+12;
 left=window.event.screenX-100;
 temp="";
// if (window.document.all.item(DateType).className=="ReadOnly")
 if (document.getElementById(DateType).className=="ReadOnly")
 		return;
 if (iRelatePub==null)
   iRelatePub=2;
 for (i=1;i<=iRelatePub;i++)
   temp+="common/js/";
 Url=temp+"Calendar.html";
 style="help:no;status:no;dialogWidth:12;dialogHeight:16.5;dialogTop:"+top+";dialogLeft:"+left;
 DataValue=window.showModalDialog(Url,null,style);
 if (DataValue!=null)
    document.getElementById(DateType).value=DataValue;
 document.getElementById(DateType).focus();
 return ((DataValue==null)?"":DataValue);
}

function GetDateChg(DateType,iRelatePub)
{
 var rcOrg,rcNew;
// rcOrg=window.document.all.item(DateType).value;
 rcOrg = document.getElementById(DateType).value;
 if (rcOrg==null)
	rcOrg="";
 rcNew=GetDate(DateType,iRelatePub);
 if (rcOrg!=rcNew && rcNew!="")
	anyChanged();
 return rcNew;
}
