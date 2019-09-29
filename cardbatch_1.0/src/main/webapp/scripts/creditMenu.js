
	var menuTree;	//存放该用户的菜单结构
	var trees={};	//存放Ext Tree的集合
	var toolBar;
	var mainMenuBar;
	var leftMenuBar;
	var mainContentArea;
	
	function getMenuData(sUrl, mainMenuBarId, leftMenuBarId){
		mainMenuBar = document.getElementById(mainMenuBarId);
		leftMenuBar = document.getElementById(leftMenuBarId);
		
		var callback = {
                method: 'GET',
                url: sUrl,
                success: handleSuccess,
                failure: handleFailure,
                scope: this,
                argument: { mainMenuBar : mainMenuBar, leftMenuBar : leftMenuBar }
            };          
		var obj1 = Ext.Ajax.request(callback);
	};


	/**
	  ajax http 请求的成功回调函数
	*/
	var handleSuccess = function(response){
		
		var argument = response.argument;
		var json = response.responseText;
		try{
            var o = eval("("+json+")");
            menuTree = o;
            showMainMenuBar(argument.mainMenuBar);			
            showLeftMenuBar(argument.leftMenuBar, menuTree[0].id);
        }catch(e){
        	handleFailure(response);
        }
	};	
	
	/**
	  ajax http 请求的失败回调函数
	*/

	var handleFailure = function(o){
		if(o.responseText !== undefined){
			alert("菜单展现发生异常，菜单的内容为："+o.responseText);
		}
	};
	
	function showMainMenuBar(mainMenuBar) {
		toolBar = new Ext.Toolbar();
		toolBar.render(mainMenuBar.id);
		
		for(var i=0;i<menuTree.length;i++){
			var menuData = menuTree[i];
			toolBar.add({
				id: menuData.id,
        		text: menuData.label,
        		enableToggle: true,
        		toggleHandler: onMainMenuToggle,
        		pressed: i==0?true:false,
        		minWidth:92
    		});
			if (i!=menuTree.length-1) {
				toolBar.addSeparator();
			}
		}
		
	};
	
	function onMainMenuToggle(menuNode, pressed){
		
		if(pressed == false){
			menuNode.getEl().addClass("x-btn-pressed");
            menuNode.pressed = true;
            return;
		}
		toolBar.items.each(function(item){
			if (item instanceof Ext.Toolbar.Button) {
				item.getEl().removeClass("x-btn-pressed");
				item.pressed = false;
			}
        });
        menuNode.getEl().addClass("x-btn-pressed");
        menuNode.pressed = true;
        showLeftMenuBar(leftMenuBar, menuNode.id);
    };
	
	
	function showLeftMenuBar(leftMenuBar, parentMenuId) {
		for(var i in trees){
			var tree = trees[i];
			tree.getEl().setDisplayed(false);
		}
		var tree = trees[parentMenuId];
		if(tree != null){
			tree.getEl().setDisplayed(true);
		}else{
			var jsonObj = null;
			for(var j=0;j<menuTree.length;j++){
				if(menuTree[j].id == parentMenuId){
					jsonObj = menuTree[j];
					break;
				}
			}
			if(jsonObj != null){
				var treeDiv = document.createElement("DIV");
				treeDiv.id = jsonObj.id+"_treeDiv";
				treeDiv.style.width = "100%";
				treeDiv.style.height = "100%";
				leftMenuBar.appendChild(treeDiv);
				
				tree = new Ext.tree.TreePanel({
        				el: treeDiv.id,			//填充区域
        				rootVisible:true,		//根节点是否可见
        				useArrows:true,
        				autoScroll:true,		//自动滚动
        				animate:true,			//动画效果
        				enableDD:false,			//节点是否可以拖拽
        				containerScroll: true
    			});
    			trees[jsonObj.id] = tree;
    			
       			var root = new Ext.tree.TreeNode({
            			text: jsonObj.label,
            			draggable:false,
            			id: jsonObj.id
        		});
        		tree.setRootNode(root);
        		
    			if(jsonObj.children != null){
    				createSubTree(tree.getRootNode(), jsonObj);
    			}
    			
    			// render the tree
    			tree.render();
    			
    			tree.getRootNode().expand();
			}else{
				
			}
		}
	};
		
	function createSubTree(treeNode, parentObj){
		var children = parentObj.children;
		for( var i=0; i<children.length; i++) {
			var jsonObj = children[i];
			
			var node = new Ext.tree.TreeNode({
					text: jsonObj.label, 
					id: jsonObj.id
			});
			
			var action = jsonObj.action;
			if (action){
				action = encodeURI(action);
				if (action.indexOf("?")>-1) {
					action+="&menuId="+jsonObj.id;
				} else {
					action+="?menuId="+jsonObj.id;
				}
				
				node.attributes.actInfo={href: action};
				
				node.on("click",function(nodeObj,event){
					if(mainContentArea == null){
						mainContentArea = Ext.get('infoframe');
					}
					mainContentArea.dom.src = nodeObj.attributes.actInfo.href;
				});
				//hrefTarget: 'infoframe'
			}
			
			treeNode.appendChild(node);
			
			if (jsonObj.children && jsonObj.children.length>0) {
				createSubTree(node, jsonObj);
			}
		}
	};



	
	function setWait(){
		var wait = document.getElementById("pleaseWaitDiv");
		if (!wait) {
			wait = document.createElement("DIV");
			wait.id = "pleaseWaitDiv";
			wait.innerHTML = "<img src='images/wait.gif' style='vertical-align:middle;'>请稍候……";
			wait.style.position = "absolute";
			wait.style.top = "380px";
			wait.style.left = "520px";
			wait.style.backgroundColor = "#FFFFFF";		
			wait.style.border = "solid 1px #000000";
			wait.style.padding = "10px 20px";
			wait.style.fontSize = "12px";
			document.body.appendChild(wait);
		} else {
			wait.style.display = "block";
		}
	};
	
	function removeWait(){
		var wait = document.getElementById("pleaseWaitDiv");
		if (wait)
			wait.style.display = "none";
	};
	