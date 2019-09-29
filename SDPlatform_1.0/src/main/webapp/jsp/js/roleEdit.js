/**
 * @author Administrator
 */
function checkboxopt(ss){ //切记!所有有中文的地方,下一行一定要空出来,否则不能执行
//-------------------------------------------------------------
ss=ss.substring(0,ss.indexOf("_"));

for(i=0;i<document.all("permission").length;i++){//遍历所有的checkbox,找出对应的那一行出来进行判断
	
	if(document.all("permission")[i].value.substring(0,document.all("permission")[i].value.indexOf("_"))==ss){
		//alert(ss);
		if(document.all("permission")[i].checked){//选中操作权限就同时选中其对应的菜单和上级菜单
		
			document.all(ss).checked="checked";
			document.all(document.all(ss).value).checked="checked";
			document.all(document.all(document.all(ss).value).value).checked="checked";
			document.all(document.all(document.all(document.all(ss).value).value).value).checked="checked";
			document.all(document.all(document.all(document.all(document.all(ss).value).value).value).value).checked="checked";
			
			return;	
			
			
		}else{
			document.all(ss).checked="";
		}
		
	}
}
//-------------------------------------------------------------

	
	
}
function checkboxmenu(ss){
	h="00";//设置默认的操作代码为查询"00"
	
if(document.all(ss).checked){//菜单选中
		
	hh=ss+"_"+h;
	
//---------------------------------------------------------------
for(i=0;i<document.all("permission").length;i++){//遍历所有的checkbox,找出对应的那一个出来进行打钩作为默认值

	if(document.all("permission")[i].value.substring(0,document.all("permission")[i].value.indexOf("_")+3)==hh){
		//alert("AAAAAAAAAAA");
		
		document.all("permission")[i].checked="checked";
		
		document.all(document.all(ss).value).checked="checked";
		document.all(document.all(document.all(ss).value).value).checked="checked";
		document.all(document.all(document.all(document.all(ss).value).value).value).checked="checked";
		
	}else{//当点击"用户管理"状态为选中时,选中所有的下级菜单和其默认菜单和上级菜单
		
		//alert("BBBBBBBBBBBBBBBBBB");
		
		for(i=0;i<document.all("permission").length;i++){//当点击"资料维护"状态为选中时, 其下的"银行资料"这一级菜单选中的默认选项"查询"选中
			
			if(document.all("permission")[i].value.substring(0,ss.length)==ss){
				if(document.all("permission")[i].value.indexOf(h)!=-1){
					document.all("permission")[i].checked="checked";
					document.all(document.all("permission")[i].value.substring(0,document.all("permission")[i].value.indexOf("_"))).checked="checked";
				}
			}
		}
		
		
		for(i=1;i<10;i++){//当点击"资料维护"状态未选中时, 其下的"银行资料"这一级菜单要未选中,默认最大有10个子菜单
		
		


			//if(document.all(ss+"0"+i)!=null){
				//alert(document.all(ss+"0"+i).value);
			document.all(ss+"0"+i).checked="checked";
			//}
		}
		
		
		document.all(document.all(ss).value).checked="checked";
		document.all(document.all(document.all(ss).value).value).checked="checked";
//		document.all(document.all(document.all(document.all(ss).value).value).value).checked="checked";
	}

}
	
}else{//菜单未选中
	
	p=0;
	for(i=0;i<document.all("permission").length;i++){//

		if(document.all("permission")[i].value.substring(0,document.all("permission")[i].value.indexOf("_") )==ss){
		
			document.all("permission")[i].checked="";
			p=1;
		}
	
	}
	if(p!=1){// 判断"用户管理 "那一层菜单没有选中的状态
	
//		alert("AA");
		for(i=0;i<document.all("permission").length;i++){//遍历所有的checkbox,找出用户管理菜单对应所有的选项清空选中
		

		if(document.all("permission")[i].value.substring(0,ss.length)==ss){
			
			document.all("permission")[i].checked="";
//			alert();
			document.all(document.all("permission")[i].value.substring(0,document.all("permission")[i].value.indexOf("_"))).checked="";
		}
	
	}
	for(i=1;i<10;i++){//当点击"资料维护"状态为未选中时, 其下的"银行资料"这一级菜单也要未选中,默认最大有10个子菜单
////		alert(ss+"0"+i);

			//if(document.all(ss+"0"+i)!=null){
				//alert(document.all(ss+"0"+i).value);
			document.all(ss+"0"+i).checked="";
			//}
		}
	
	
	}
}

}
//------------------------------------------------------------------
