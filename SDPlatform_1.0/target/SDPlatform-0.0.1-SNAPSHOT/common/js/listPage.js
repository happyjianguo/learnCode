	function resetClick()
	{
		document.forms[0].reset();
	}
	function searchClick(method)
	{
//		先注释不然非IE查询会有问题
//		var eleList = window.document.forms[0].elements;
//		for (var i=0;i<eleList.length;i++)
//		{		
//			var obj=eleList.elements[i];
//			var objName = obj.name;
//			if(objName.substring(0,2)=="d-")
//			{
//				obj.value = "1";
//			}
//		}
		
		document.forms[0].method.value=method;
		return true;
	}
	
	function editClick(id,method)
	{
		document.forms[0].method.value=method;
		document.forms[0].id.value=id;
		document.forms[0].submit();
	}
	
	
	function deleteClick(textName,itemName,method)
	{
	 
	 	var aa = document.getElementsByName(itemName);
	 	
	 	var bb = document.all[textName];
	
	 	bb.value = "";
	 	flag = false;
	 	for (var i=0; i<aa.length; i++)
	 	{
	 		if(aa[i].checked)
	 		{
	 			flag = true;
	 			
	 			if(bb.value=="")
	 			{
	 				bb.value = aa[i].value;
	 			}
	 			else
	 				bb.value = bb.value + "|" + aa[i].value;
	 		}
	 	}
	 	if(flag)
	 	{
	 		if(confirm("\u786e\u5b9a\u8981\u5220\u9664\u9009\u4e2d\u7684\u9009\u9879\u5417\uff1f"))
	 		{
	 			document.forms[0].method.value=method;
	 			document.forms[0].submit();
	 		}
	 	}
	 		
	 	else
	 		alert("\u8bf7\u9009\u62e9\u8981\u5220\u9664\u7684\u9009\u9879\uff01");
	}
	
	function addClick(method)
	{
		document.forms[0].method.value=method;
		document.forms[0].submit();
	}
	
	
	function deleteItems(item,method,msg1,msg2)
	{		
		var items = document.getElementsByName(item);
	 
	 	flag = false;
	 	
	 	for(var i = 0; i < items.length; i++)
	 	{
	 		if(items[i].checked)
	 		{
	 			flag = true;
	 			break;
	 		}
	 	}
	 	if(flag)
	 	{
	 		if(msg1.length==0)
	 			msg1 = "????????????????????????";
	 		if(confirm(msg1))
	 		{
	 			document.forms[0].method.value=method;
				document.forms[0].submit();
	 		}
	 	}
	 	else
	 	{
	 		if(msg2.length==0)
	 			msg2 = "????????????????????";
	 		
	 		alert(msg2);
	 	}
	}