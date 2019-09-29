loadJS = function(url){var script = "<scr"+"ipt type='text/javascript' src='" + url + "'></scr"+"ipt>";document.write(script);}
loadJS("/bfbposp/dwr/engine.js");
loadJS("/bfbposp/dwr/util.js");
loadJS("/bfbposp/dwr/interface/riskRule_servcie.js");

// 根据办事处查询规则名称
function getRuleName(){
//	var ruleZone = document.getElementById('ruleZone').value;
//	var deptName = document.getElementById('deptName');
//	deptName.value = ruleZoneObj.options[ruleZoneObj.selectedIndex].text;
var from = document.transqueryForm.value;
var sdate = document.transqueryForm.start_date.value;
    var r_name = document.transqueryForm.rulename.value;
 
  /* if(r_name!=""){
  
   riskRule_servcie.getRuleNameById (r_name,function(data){
   rule_name = document.getElementById("rule_name");
		
				rule_name.add(new Option(data[0].rule_name,data[0].rulename_id));  
		
   });
   }*/
  
	riskRule_servcie.getRuleName(function(data) {
	 var r_name = document.transqueryForm.rulename.value;
		rule_name = document.getElementById("rule_name");
		document.getElementById("rule_name").options.length=0;
		rule_name.add(new Option("------请选择------","")); 
		if(data.length > 0){
			for(i=0;i<data.length;i++){
			if(data[i].rulename_id == r_name){
			rule_name.add( new Option(data[i].rule_name,data[i].rulename_id));
			var bankSelects = rule_name.options;
			
				bankSelects[i+1].selected = true;
               getRuleAndTermTypeByRuleName(rule_name)
				}else{
				rule_name.add( new Option(data[i].rule_name,data[i].rulename_id));
			}
				  
			}
		}else{
			rule_name.add(new Option("------请选择------",""));   	
		}
		
	});	
	

}

//根据规则名称ID查找规则类型和终端类型
function getRuleAndTermTypeByRuleName(obj){
	var rule_name = obj.value ;
	riskRule_servcie.getRuleAndTermTypeByRuleName(rule_name,function(data){
		rule_type = document.getElementById("rule_type");
		rule_termtype = document.getElementById("rule_termtype");
		rule_typecn = document.getElementById("rule_typecn");
		rule_termtypecn = document.getElementById("rule_termtypecn");
		if(data.length > 0){
			rule_type.value = data[0].rule_type;
			rule_termtype.value = data[0].rule_termtype;
			rule_typecn.value = data[0].rule_typecn;
			rule_termtypecn.value = data[0].rule_termtypecn;
		}else if(rule_name==""){
			rule_type.value = "";
			rule_termtype.value = "";
			rule_typecn.value = "";
			rule_termtypecn.value = "";
		}else{
			rule_type.value = "";
			rule_termtype.value = "";
			rule_typecn.value = "";
			rule_termtypecn.value = "";
		}
	});	
}


//风险配置修改
function getEditRuleDecByType(rule_type,rulename_id){
	var paraStr = new Array(); //定义一数组
	var str ='<table width="90%" align="center" border="0" cellspacing="0" cellpadding="0">'
			+'<tr style="padding-top: 10px;"> '
			+'	<th class="box1" style="width: 35px;">选择</th>    '
			+'	<th class="box1" style="width: 50px;">序号</th>    '
			+'	<th class="box1" >配置描述</th>'
			+'</tr>';
	if (rulename_id!=null && rulename_id!="" && rule_type!=null && rule_type!="") {
		// 查询规则列表
		riskRule_servcie.getRuleNameAndDetail(rulename_id,rule_type,function(map_data){
			// 循环规则配置结果集
			for(i=0;i<map_data.ruleList.length;i++){
				var desc = map_data.ruleList[i].rule_desc;		// 规则描述字段
				var flag = '';	// 标记是否勾选
				var para = '<input type="text" name="para" size="6">';
				for(n=0;n<map_data.detailList.length;n++){			
					if(map_data.detailList[n].rule_id == map_data.ruleList[i].rule_id){
						var paraVal = map_data.detailList[n].rule_para;
						if(paraVal!=null && paraVal!=''){
							paraStr=paraVal.split(','); 	//字符分割   
							
							var desc_temp = new StringBuffer();
							if(paraStr.length > 0){
								for(s=0;s<paraStr.length;s++){		
									para = '<input type="text" name="para" size="6" value="'+paraStr[s]+'"/>';
									if(desc.length < desc.indexOf("para") +4){
									}else{
										temp = desc.substring(0,desc.indexOf("para") +4);
										desc = desc.substring(desc.indexOf("para") +4,desc.length);
										desc_temp.append(temp.replaceAll("para",para));
									}	
								}
								desc = desc_temp.toString() + desc;
							}else{
								para = '<input type="text" name="para" size="6" value="'+map_data.detailList[n].rule_para+'"/>';
								desc = desc.replaceAll("para",para);
							}
						}
						flag = 'checked';
					}
				}
				if(flag!='checked'){
					desc = desc.replaceAll('para',para);
				}
				
				str += '<tr>';
				if(flag=='checked'){
					str += '<td class="box1"><input id="rule'+map_data.ruleList[i].rule_id+'" type="checkbox" name="rule_id" value="'+map_data.ruleList[i].rule_id+'" checked="checked" onclick="checkRule(rule'+map_data.ruleList[i].rule_id+',div'+i+'>)"/></td>';
				} else {
					str += '<td class="box1"><input id="rule'+map_data.ruleList[i].rule_id+'" type="checkbox" name="rule_id" value="'+map_data.ruleList[i].rule_id+'" onclick="checkRule(rule'+map_data.ruleList[i].rule_id+',div'+i+')"/></td>';
				}
				str += '<td class="box1" align="center">'+map_data.ruleList[i].rule_id+'</td>';
				str += '<td class="box2"><div id="div'+i+'">'+desc+'</div></td>';
				str +='</tr>';
			}
			str += ' </table>';
			document.getElementById("ruleCfgList").innerHTML = str;
			checkCtrl();
		});
	}
}

//风险配置添加，根据规则类型，显示相应的列表
function getRuleDecByType(){
	var rule_type = document.getElementById("rule_type").value;
	var str ='<table width="90%" align="center" border="0" cellspacing="0" cellpadding="0">'
			+'<tr style="padding-top: 10px;"> '
			+'	<th class="box1" style="width: 35px;">选择</th>    '
			+'	<th class="box1" style="width: 50px;">序号</th>    '
			+'	<th class="box1">配置描述</th>'
			+'</tr>';
	if (rule_type!=null && rule_type!="") {
		riskRule_servcie.getRiskRuleListByRuleType(rule_type,function(data){
			if(data.length > 0){		
				for (i=0; i<data.length; i++){	
//alert('i='+data.length+';data.length='+data.length+';data[i].rule_id='+data[i].rule_id);
					str += '<tr id="rule_id_'+data[i].rule_id+'">'
						+'<td class="box1"><input id="rule'+data[i].rule_id+'" type="checkbox" name="rule_id" value="'+data[i].rule_id+'" onclick="checkRule(rule'+data[i].rule_id+',div'+i+')"/></td>' 
						+'<td class="box1" align="center">'+data[i].rule_id+'</td>';
					var desc = data[i].rule_desc;
					var para = '<input type="text" name="para" size="6"/>';
					desc = desc.replaceAll("para",para); 
					str += '<td class="box2"><div id="div'+i+'">'+desc+'</div></td>'
						+'</tr>';
				}			
			}else{
			}
			str += ' </table>';

			document.getElementById("ruleCfgList").innerHTML = str;
			checkCtrl();
		});
	}
}


// 根据checkbox是否勾选，控制文本框是否可编辑
function checkCtrl(){
//	var boxes =document.getElementsByName('rule_id'); 
//	for(i=0;i<boxes.length;i++){
//		var divid = document.getElementById('div'+i);
//		var texts = divid.getElementsByTagName("input");
//		for(var n = 0; n < texts.length; n++){
//	        texts[n].readOnly=true;		
//		}
//	} 

	var boxes =document.getElementsByName('rule_id'); 	
	for(i=0;i<boxes.length;i++){
		var flag = true;
		if(boxes[i].checked ){
			flag = false;
		}
		var divid = document.getElementById('div'+i);
		var texts = divid.getElementsByTagName("input");
		for(var n = 0; n < texts.length; n++) {
	        texts[n].readOnly=flag;		
		}
	}  
}

// 复选框参数配置控制
function checkRule(ruleid,div){
	
	var texts = div.getElementsByTagName("input");
	if(texts.length > 0){
		if(ruleid.checked){
			for(var i = 0; i < texts.length; i++){
				texts[i].readOnly=false;
			}
		}else{
			for(var i = 0; i < texts.length; i++){
				texts[i].value="";
				texts[i].readOnly=true;
			}
		}
	}
}


// replaceALL方法
String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {  
    if (!RegExp.prototype.isPrototypeOf(reallyDo)) {  
        return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);  
    } else {  
        return this.replace(reallyDo, replaceWith);  
    }  
} 

function StringBuffer() {
    this.__strings__ = new Array;
}

StringBuffer.prototype.append = function (str) {
    this.__strings__.push(str);
};

StringBuffer.prototype.toString = function () {
    return this.__strings__.join("");
};


/*
String.prototype.replaceAll = function(s1,s2) { 
    return this.replace(new RegExp(s1,"gm"),s2); 
}
*/


