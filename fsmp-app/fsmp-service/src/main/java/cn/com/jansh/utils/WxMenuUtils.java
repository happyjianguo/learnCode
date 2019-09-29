package cn.com.jansh.utils;

import java.util.ArrayList;
import java.util.List;

import cn.com.jansh.entity.weixin.WXDMenu;


/**
 * 微信菜单转出菜单树
 * @author panc
 *
 */
public class WxMenuUtils {
public static List<WXDMenu> list2Tree(List<WXDMenu> mList){
		
		List<WXDMenu> mTree = new ArrayList<WXDMenu>();
		for (WXDMenu menu1 : mList) {
			boolean mark = false;
			for (WXDMenu menu2 : mList) {
				if (menu1.getParentid() != null
						&& menu1.getParentid().equals(menu2.getMenuid())) {
					mark = true;
					if (menu2.getChild() == null) {
						menu2.setChild(new ArrayList<WXDMenu>());
					}
					menu2.getChild().add(menu1);
					break;
				}
			}
			if (!mark) {
				mTree.add(menu1);
			}
		}
		return mTree;
	}
}
