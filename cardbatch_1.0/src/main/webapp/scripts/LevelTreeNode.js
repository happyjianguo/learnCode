//扩展叶子节点，该节点没有子节点不需要展开
Ext.ux.LevelTreeNode = function(config) 
{
	this.level=config.level;
	Ext.ux.LevelTreeNode.superclass.constructor.apply(this, arguments);
};
Ext.extend(Ext.ux.LevelTreeNode, Ext.tree.TreeNode, 
	{
		
	}
);
//扩展异步树节点，该节点类型有子节点
Ext.ux.AsyncLevelTreeNode = function(config) 
{
	this.level=config.level;
	Ext.ux.AsyncLevelTreeNode.superclass.constructor.apply(this, arguments);
};
Ext.extend(Ext.ux.AsyncLevelTreeNode, Ext.tree.AsyncTreeNode, 
	{
		
	}
);