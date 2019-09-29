var nodes = new Array();	//存放节点
var nodes_html = new Array();	//存放节点所在单元格内的HTML
var node_line = new Array();	//节点与行号的哈希表
var line = 0;

//节点类，即菜单项
function Node(id,parent,content,level,url,target,image){
	this.id = id;
	this.parent = parent;
	this.content = content;
	this.url = url;
	this.level = level;
	this.target = target;
	this.image = image;
}

//生成菜单
function makeMenuTree(){
	//nodes[0] = new Node(0,0,"","","","");
	var cp = getCellpadding();
	document.write("<TABLE id=menu cellpadding="+cp+" cellspacing=0>");		
	insertNode(nodes[0],0);
	document.write("</TABLE>");
	storeHTML();
	fold(nodes[0]);				//只显示一级菜单
	unfold(nodes[1]);
}

//为了美观，根据菜单数量设置cellpadding
function getCellpadding(){
	var a = 0,b = 1;
	for(var i=1;i<nodes.length;i++){
		if(nodes[i].parent==0) b++;
		else a++;
	}
	return Math.ceil(a/b);
}

//递归插入每个节点，即菜单项
function insertNode(node,spaceAmount){
	for(var i=1;i<nodes.length;i++)
		if(nodes[i].parent==node.id){
			//图像、URL加载与处理
			var node_url = "#";
			if(nodes[i].url!="")
				node_url = nodes[i].url;

			var node_target = "_self";
			if(nodes[i].target!="")
				node_target = nodes[i].target
			
			var node_image = "";
			if(nodes[i].image!="")
				node_image = "<IMG SRC='"+nodes[i].image+"'WIDTH='15' HEIGHT='15' BORDER='0' ALT=''>";					 

			//开始生成菜单项
			if(isLeaf(nodes[i])){
				document.write("<TR><TD class=childlink>"+spaces(spaceAmount)+node_image+"<a class=childlink href='"+node_url+"' target='"+node_target+"' onmousedown='javascript:menuOperation(nodes["+i+"]);this.blur();'>"+nodes[i].content+"</a></TD></TR>");
				node_line[line++] = nodes[i];
			}
			else{					
				if(nodes[i].parent==0)
					document.write("<TR><TD>"+node_image+"<a class=parentlink href='"+node_url+"' target='"+node_target+"' onmousedown='javascript:menuOperation(nodes["+i+"]);this.blur();'>"+nodes[i].content+"</a></TD></TR>");
				else
					document.write("<TR><TD class=childlink>"+spaces(spaceAmount)+node_image+"<a class=parentlink href='"+node_url+"' target='"+node_target+"' onmousedown='javascript:menuOperation(nodes["+i+"]);this.blur();'>"+nodes[i].content+"</a></TD></TR>");
				node_line[line++] = nodes[i];
				insertNode(nodes[i],spaceAmount+1);
			}
		}
}

//判断是否为叶节点，即最后一级菜单
function isLeaf(node){
	if(node.parent==0) return false;	//一级目录
	var b = true;
	for(var i=1;i<nodes.length;i++)			
		if(nodes[i].parent==node.id){
			b = false;
			break;
		}
	return b;
}

//返回n个空格
function spaces(n){
	var s = "";
	for(var i=0;i<n;i++) s += "&nbsp;";
	return s;
}

//保存每一行的HTML代码
function storeHTML(){
	for(var i=0;i<document.all.menu.cells.length;i++)
		nodes_html[i] = document.all.menu.cells[i].innerHTML;
}

//判断点击菜单的操作是展开还是合并的操作
function menuOperation(node){
	if(window.event.button==2)
		showMenu(node);
	else{
		for(var i=0;i<node_line.length;i++)
			if(node_line[i]==node) break;
		if(document.all.menu.cells[i+1] && document.all.menu.cells[i+1].innerHTML==""){
			fold(node);
			unfold(node);
		}
		else
			fold(node);
	}
}

//菜单展开
function unfold(node){
	//if(isLeaf(node)) return;
	for(var i=0;i<document.all.menu.cells.length;i++)
		if(node_line[i].parent==node.id)
			document.all.menu.cells[i].innerHTML = nodes_html[i];
}

//菜单合并
function fold(node){
	//if(isLeaf(node)) return;
	var tag = node.parent;
	var paths = new Array();	//存放节点的祖先
	var n = 0;

	while(tag!=nodes[0].id){	//根节点
		for(var i=1;i<nodes.length;i++)
			if(nodes[i].id==tag){
				paths[n++] = tag;
				tag = nodes[i].parent;
				break;
			}
	}
	paths[n++] = nodes[0].id;

	//开始合并操作
	for(var i=0;i<document.all.menu.cells.length;i++)
		if(!exists(node_line[i],paths))
			document.all.menu.cells[i].innerHTML = "";
}

//判断节点node是否在其祖先paths中
function exists(node,paths){
	var b = false;
	for(var i=0;i<paths.length;i++)
		if(node.parent==paths[i]){
			b = true;
			break;
		}
	return b;
}