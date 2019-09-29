var nodes = new Array();	//��Žڵ�
var nodes_html = new Array();	//��Žڵ����ڵ�Ԫ���ڵ�HTML
var node_line = new Array();	//�ڵ����кŵĹ�ϣ��
var line = 0;

//�ڵ��࣬���˵���
function Node(id,parent,content,level,url,target,image){
	this.id = id;
	this.parent = parent;
	this.content = content;
	this.url = url;
	this.level = level;
	this.target = target;
	this.image = image;
}

//���ɲ˵�
function makeMenuTree(){
	//nodes[0] = new Node(0,0,"","","","");
	var cp = getCellpadding();
	document.write("<TABLE id=menu cellpadding="+cp+" cellspacing=0>");		
	insertNode(nodes[0],0);
	document.write("</TABLE>");
	storeHTML();
	fold(nodes[0]);				//ֻ��ʾһ���˵�
	unfold(nodes[1]);
}

//Ϊ�����ۣ����ݲ˵���������cellpadding
function getCellpadding(){
	var a = 0,b = 1;
	for(var i=1;i<nodes.length;i++){
		if(nodes[i].parent==0) b++;
		else a++;
	}
	return Math.ceil(a/b);
}

//�ݹ����ÿ���ڵ㣬���˵���
function insertNode(node,spaceAmount){
	for(var i=1;i<nodes.length;i++)
		if(nodes[i].parent==node.id){
			//ͼ��URL�����봦��
			var node_url = "#";
			if(nodes[i].url!="")
				node_url = nodes[i].url;

			var node_target = "_self";
			if(nodes[i].target!="")
				node_target = nodes[i].target
			
			var node_image = "";
			if(nodes[i].image!="")
				node_image = "<IMG SRC='"+nodes[i].image+"'WIDTH='15' HEIGHT='15' BORDER='0' ALT=''>";					 

			//��ʼ���ɲ˵���
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

//�ж��Ƿ�ΪҶ�ڵ㣬�����һ���˵�
function isLeaf(node){
	if(node.parent==0) return false;	//һ��Ŀ¼
	var b = true;
	for(var i=1;i<nodes.length;i++)			
		if(nodes[i].parent==node.id){
			b = false;
			break;
		}
	return b;
}

//����n���ո�
function spaces(n){
	var s = "";
	for(var i=0;i<n;i++) s += "&nbsp;";
	return s;
}

//����ÿһ�е�HTML����
function storeHTML(){
	for(var i=0;i<document.all.menu.cells.length;i++)
		nodes_html[i] = document.all.menu.cells[i].innerHTML;
}

//�жϵ���˵��Ĳ�����չ�����Ǻϲ��Ĳ���
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

//�˵�չ��
function unfold(node){
	//if(isLeaf(node)) return;
	for(var i=0;i<document.all.menu.cells.length;i++)
		if(node_line[i].parent==node.id)
			document.all.menu.cells[i].innerHTML = nodes_html[i];
}

//�˵��ϲ�
function fold(node){
	//if(isLeaf(node)) return;
	var tag = node.parent;
	var paths = new Array();	//��Žڵ������
	var n = 0;

	while(tag!=nodes[0].id){	//���ڵ�
		for(var i=1;i<nodes.length;i++)
			if(nodes[i].id==tag){
				paths[n++] = tag;
				tag = nodes[i].parent;
				break;
			}
	}
	paths[n++] = nodes[0].id;

	//��ʼ�ϲ�����
	for(var i=0;i<document.all.menu.cells.length;i++)
		if(!exists(node_line[i],paths))
			document.all.menu.cells[i].innerHTML = "";
}

//�жϽڵ�node�Ƿ���������paths��
function exists(node,paths){
	var b = false;
	for(var i=0;i<paths.length;i++)
		if(node.parent==paths[i]){
			b = true;
			break;
		}
	return b;
}