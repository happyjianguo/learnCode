package cn.com.jansh.service.weixin;

import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import cn.com.jansh.entity.weixin.WXDMenu;

/**
 * 菜单管理接口
 * @author gll
 * @version 1.0
 */
public interface IWXDMenuService {
	public List<WXDMenu> getUnSyncMenus(WXDMenu model);
	/**
	 * 同步微信自定义菜单到服务器
	 * @return
	 */
	public boolean syscMenus(WXDMenu model,String appid);
	/**
	 * 初始化获取按钮
	 * 
	 * @return
	 */
	public List<WXDMenu> getAllMenus(String platformId);
	
	/**
	 * 更新按钮
	 * 
	 * @param data
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws Exception 
	 */
	public boolean updateMenus(String data) throws JsonParseException, JsonMappingException, IOException, Exception;
	
	/**
	 * 删除公众号下的按钮
	 * 
	 * @param platformId
	 */
	List<WXDMenu> getChildMenus(String parentId, WXDMenu model);
}
